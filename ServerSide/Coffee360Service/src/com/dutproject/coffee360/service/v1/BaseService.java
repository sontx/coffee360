package com.dutproject.coffee360.service.v1;

import javax.ws.rs.core.SecurityContext;

import com.dutproject.coffee360.service.v1.AuthenticationFilter.AuthenticationPrincipal;

public abstract class BaseService {
	protected int getAccountId(SecurityContext securityContext) {
		AuthenticationFilter.AuthenticationPrincipal authenticationPrincipal = (AuthenticationPrincipal) securityContext.getUserPrincipal();
		return authenticationPrincipal.getAccountId();
	}
}
