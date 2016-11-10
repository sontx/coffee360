package com.dutproject.coffee360.service.v1;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dutproject.coffee360.model.bean.Account;
import com.dutproject.coffee360.model.bo.AuthenticationBO;

@Path("/v1/auth")
public class AuthenticationService {
	private AuthenticationBO authenticationBO = new AuthenticationBO();

	@POST
	@Produces(MediaType.APPLICATION_XML)
	@Consumes("application/x-www-form-urlencoded")
	public Response authenticate(
			@FormParam("username") String username, 
			@FormParam("password") String password) {
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
}
