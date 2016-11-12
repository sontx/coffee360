package com.dutproject.coffee360.service.v1;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.*;

import com.dutproject.coffee360.model.bean.Account;
import com.dutproject.coffee360.model.bo.AuthenticationBO;
import com.dutproject.coffee360.service.Role;
import com.dutproject.coffee360.service.Secured;
import com.dutproject.coffee360.service.v1.AuthenticationFilter.AuthenticationPrincipal;

@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {
	private AuthenticationBO authenticationBO = new AuthenticationBO();
	
	@Context
	private ResourceInfo resourceInfo;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		// Get the resource class which matches with the requested URL
		// Extract the roles declared by it
		Class<?> resourceClass = resourceInfo.getResourceClass();
		List<Role> classRoles = extractRoles(resourceClass);

		// Get the resource method which matches with the requested URL
		// Extract the roles declared by it
		Method resourceMethod = resourceInfo.getResourceMethod();
		List<Role> methodRoles = extractRoles(resourceMethod);

		AuthenticationFilter.AuthenticationPrincipal authenticationPrincipal = (AuthenticationPrincipal) requestContext
				.getSecurityContext().getUserPrincipal();
		int accountId = authenticationPrincipal.getAccountId();
		
		try {

			// Check if the user is allowed to execute the method
			// The method annotations override the class annotations
			if (methodRoles.isEmpty()) {
				checkPermissions(classRoles, accountId);
			} else {
				checkPermissions(methodRoles, accountId);
			}

		} catch (Exception e) {
			e.printStackTrace();
			requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
		}
	}

	// Extract the roles from the annotated element
	private List<Role> extractRoles(AnnotatedElement annotatedElement) {
		if (annotatedElement == null) {
			return new ArrayList<Role>();
		} else {
			Secured secured = annotatedElement.getAnnotation(Secured.class);
			if (secured == null) {
				return new ArrayList<Role>();
			} else {
				Role[] allowedRoles = secured.value();
				return Arrays.asList(allowedRoles);
			}
		}
	}

	private void checkPermissions(List<Role> allowedRoles, int accountId) throws Exception {
		// Check if the user contains one of the allowed roles
		// Throw an Exception if the user has not permission to execute the
		// method
		if (!allowedRoles.isEmpty()) {
			int permission = authenticationBO.getPermissionByAccountId(accountId);
			
			if (permission == Account.PERMISSION_ADMIN && !hasRole(allowedRoles, Role.ROLE_ADMIN))
				throw new Exception("Do not have user permission");
			if (permission == Account.PERMISSION_USER && !hasRole(allowedRoles, Role.ROLE_USER))
				throw new Exception("Do not have admin permission");			
		}
			
	}
	
	private boolean hasRole(List<Role> allowedRoles, Role role) {
		for (Role irole : allowedRoles) {
			if (irole == role)
				return true;
		}
		return false;
	}
}