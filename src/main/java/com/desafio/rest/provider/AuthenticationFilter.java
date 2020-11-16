package com.desafio.rest.provider;

import com.desafio.rest.dao.TokenDao;
import com.desafio.rest.utils.Secured;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.sql.SQLException;

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
            validateToken(token, requestContext);

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
                .entity("Unauthorized")
                        .header(HttpHeaders.WWW_AUTHENTICATE,
                                AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
                        .build());
        return;
    }

    private void validateToken(String token, ContainerRequestContext requestContext) throws Exception {
        boolean result = false;
        try {
            System.out.println("o token chegou" + token);
            result = TokenDao.tokenIsValid(token);
            if (!result) {
                abortWithUnauthorized(requestContext);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
