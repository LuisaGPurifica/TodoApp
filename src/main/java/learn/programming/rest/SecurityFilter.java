package learn.programming.rest;

import java.io.IOException;
import java.security.Key;
import java.security.Principal;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import learn.programming.service.SecurityUtil;

@Provider
@Authz
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {

	@Inject
	private SecurityUtil securityUtil;

	public void filter(ContainerRequestContext requestContext) throws IOException {
		String authString = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		if (authString == null || authString.isEmpty() || !authString.startsWith("Bearer")) {
			throw new NotAuthorizedException(Response.status(Response.Status.UNAUTHORIZED).build());
		}

		String token = authString.substring(SecurityUtil.BEARER.length()).trim();

		try {
			Key key = securityUtil.getSecurityKey();
			final Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			final SecurityContext originalContext = requestContext.getSecurityContext();
			
			requestContext.setSecurityContext(new SecurityContext() {

				public Principal getUserPrincipal() {
					return new Principal() {

						public String getName() {
							return claimsJws.getBody().getSubject();
						}
						
					};
				}

				public boolean isUserInRole(String role) {
					return originalContext.isUserInRole(role);
				}

				public boolean isSecure() {
					return originalContext.isSecure();
				}

				public String getAuthenticationScheme() {
					return originalContext.getAuthenticationScheme();
				}
				
			});
			
			

		} catch (Exception e) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}

	}

}
