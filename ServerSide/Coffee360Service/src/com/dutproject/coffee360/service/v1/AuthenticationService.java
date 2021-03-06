package com.dutproject.coffee360.service.v1;

import java.io.IOException;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dutproject.coffee360.config.Constants;
import com.dutproject.coffee360.model.bean.Account;
import com.dutproject.coffee360.model.bean.Credentials;
import com.dutproject.coffee360.model.bean.UserAccount;
import com.dutproject.coffee360.model.bo.AuthenticationBO;
import com.dutproject.coffee360.utils.FacebookOAuth;

@Path("/v1/auth")
public class AuthenticationService {
	private AuthenticationBO authenticationBO = new AuthenticationBO();

	@POST
	@Path("/admin")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response authenticateAdmin(Credentials credentials) {
		try {

			// Authenticate the user using the credentials provided
			Account account = authenticationBO.authenticateAdmin(credentials.getUsername(), credentials.getPassword());

			if (account == null)
				throw new Exception();

			// Issue a token for the user
			String token = authenticationBO.issueToken(account);

			// Return the token on the response
			return Response.ok(token).build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
	
	private String requestAccessToken(String code) throws IOException {
		String accessToken = FacebookOAuth.getInstance().requestAccessToken(code, Constants.OAUTH_REDIRECT_URI);
		if (accessToken == null || accessToken.startsWith("{")) {
			System.out.println(accessToken);
			throw new IOException();
		}
		return accessToken;
	}
	
	private UserAccount createUserAccountFromProfileData(String accessToken, Map<String, String> profileData) {
		UserAccount userAccount = new UserAccount();
		userAccount.setAccessToken(accessToken);
		userAccount.setUserName(profileData.get("email"));
		userAccount.setPermission(Account.PERMISSION_USER);
		userAccount.setFullName(profileData.get("name"));
		userAccount.setAddress(profileData.get("location"));
		userAccount.setGender(profileData.get("gender"));
		userAccount.setAvatarUrl(profileData.get("avatarUrl"));
		return userAccount;
	}
	
	@GET
	@Path("/oauth2")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response oauth2(@QueryParam("accessToken") String accessToken) {
		if (accessToken == null || "".equals(accessToken))
			return Response.status(Response.Status.NO_CONTENT).build();
		try {
			// get user account info from social network
			Map<String, String> profileData = FacebookOAuth.getInstance().getProfileData(accessToken);
			UserAccount userAccount = createUserAccountFromProfileData(accessToken, profileData);
			
			// check account is existing
			UserAccount existingAccount = authenticationBO.getAccountByUserName(userAccount.getUserName());
			
			// create new one if account is not exist
			if (existingAccount == null)
				existingAccount = authenticationBO.createNewUserAccount(userAccount);
			
			String tempAccessToken = authenticationBO.issueToken(existingAccount);
			existingAccount.setAccessToken(tempAccessToken);
			
			return Response.ok().entity(existingAccount).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}
	}

	@GET
	@Path("/oauth")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response oauthCallback(@QueryParam("code") String code) {
		if (code == null || "".equals(code))
			return Response.status(Response.Status.NO_CONTENT).build();
		try {
			// get user account info from social network
			String accessToken = requestAccessToken(code);
			Map<String, String> profileData = FacebookOAuth.getInstance().getProfileData(accessToken);
			UserAccount userAccount = createUserAccountFromProfileData(accessToken, profileData);
			
			// check account is existing
			UserAccount existingAccount = authenticationBO.getAccountByUserName(userAccount.getUserName());
			
			// create new one if account is not exist
			if (existingAccount == null)
				existingAccount = authenticationBO.createNewUserAccount(userAccount);
			
			String tempAccessToken = authenticationBO.issueToken(existingAccount);
			existingAccount.setAccessToken(tempAccessToken);
			
			return Response.ok().entity(existingAccount).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/user")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response authenticateUser(@QueryParam("token") String token) {
		try {

			// Authenticate the user using the credentials provided
			UserAccount account = authenticationBO.authenticateUser(token);

			if (account == null)
				throw new Exception();
			
			if (account.getAvatarUrl() == null || "".equals(account.getAvatarUrl())) {
				String accessToken = account.getAccessToken();
				String avatarUrl = FacebookOAuth.getInstance().getAvatarUrlByAccessToken(accessToken);
				account.setAvatarUrl(avatarUrl);
				authenticationBO.updateAvatarUrl(account.getId(), avatarUrl);
			}

			return Response.ok(account).build();

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
}
