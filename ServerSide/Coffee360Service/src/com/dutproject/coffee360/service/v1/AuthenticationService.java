package com.dutproject.coffee360.service.v1;

import java.io.IOException;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dutproject.coffee360.model.bean.Account;
import com.dutproject.coffee360.model.bo.AuthenticationBO;
import com.dutproject.coffee360.utils.FacebookOAuth;

@Path("/v1/auth")
public class AuthenticationService {
	private AuthenticationBO authenticationBO = new AuthenticationBO();

	@POST
	@Produces(MediaType.APPLICATION_XML)
	@Consumes("application/x-www-form-urlencoded")
	public Response authenticate(@FormParam("username") String username, @FormParam("password") String password) {
		try {

			// Authenticate the user using the credentials provided
			Account account = authenticationBO.authenticateAdmin(username, password);

			if (account == null)
				throw new Exception();

			// Issue a token for the user
			String token = authenticationBO.issueToken(account);

			// Return the token on the response
			return Response.ok(token).build();

		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

	@GET
	@Path("/oauth")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.TEXT_PLAIN)
	public Response oauthCallback(@QueryParam("code") String code) {
		if (code == null || "".equals(code))
			return Response.status(Response.Status.NO_CONTENT).build();
		final String redirectUri = "http://sontx.no-ip.org:8080/Coffee360Service/rest/v1/auth/oauth";
		try {
			String accessToken = FacebookOAuth.getInstance().requestAccessToken(code, redirectUri);
			if (accessToken == null || accessToken.startsWith("{")) {
				System.out.println(accessToken);
				throw new IOException();
			}
			Map<String, String> profileData = FacebookOAuth.getInstance().getProfileData(accessToken);
			return Response.ok().build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
