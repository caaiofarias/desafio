package com.desafio.rest.provider;
import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.desafio.rest.dao.AuthDao;
import com.desafio.rest.model.Session;
import com.desafio.rest.utils.Secured;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter {

    private static final String REALM = "example";
    private static final String AUTHENTICATION_SCHEME = "Bearer";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

    	
        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        
        System.out.println(authorizationHeader);
		
		  if (!isTokenBasedAuthentication(authorizationHeader)) {
		  abortWithUnauthorized(requestContext); 
		  return; 
		  }
		 
		
		  String token = authorizationHeader
		  .substring(AUTHENTICATION_SCHEME.length()).trim();
		 

        try {
            validateToken(token);

        } catch (Exception e) {
            abortWithUnauthorized(requestContext);
        }
    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                    .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE, 
                                AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
                        .build());
    }

    private void validateToken(String token) throws Exception {
    	try {
    		String tokenSesstion = (String) Session.instance.getValue("token");
    		System.out.println("token session " + tokenSesstion);
			boolean result = AuthDao.tokenIsValid(tokenSesstion);
			System.out.println("resultado " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}