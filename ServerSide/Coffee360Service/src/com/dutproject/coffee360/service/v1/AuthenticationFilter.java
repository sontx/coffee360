package com.dutproject.coffee360.service.v1;

import java.io.IOException;
import java.security.Principal;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.*;

import com.dutproject.coffee360.service.Secured;
import com.dutproject.coffee360.utils.TokensManager;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// Get the HTTP Authorization header from the request
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		// Check if the HTTP Authorization header is present and formatted
		// correctly
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new NotAuthorizedException("Authorization header must be provided");
		}

		// Extract the token from the HTTP Authorization header
		String token = authorizationHeader.substring("Bearer".length()).trim();

		try {

			// Validate the token
			TokensManager.getInstance().validateToken(token);
			int accountId = TokensManager.getInstance().getAccountIdByToken(token);
			requestContext.setSecurityContext(new AuthenticationSecurityContext(accountId));

		} catch (Exception e) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}

	}

	private static class AuthenticationSecurityContext implements SecurityContext {
		private AuthenticationPrincipal authenticationPrincipal;

		private AuthenticationSecurityContext(int accountId) {
			authenticationPrincipal = new AuthenticationPrincipal(accountId);
		}

		@Override
		public String getAuthenticationScheme() {
			return null;
		}

		@Override
		public Principal getUserPrincipal() {
			return authenticationPrincipal;
		}

		@Override
		public boolean isSecure() {
			return false;
		}

		@Override
		public boolean isUserInRole(String arg0) {
			return true;
		}
	}
	
	public static class AuthenticationPrincipal implements Principal {
		private int accountId;
		
		private AuthenticationPrincipal(int accountId) {
			this.accountId = accountId;
		}
		
		public int getAccountId() {
			return accountId;
		}
		
		@Override
		public String getName() {
			return accountId + "";
		}
		
	}
}
