package com.desafio.rest.api;

import com.desafio.rest.dao.TokenDao;
import com.desafio.rest.dao.UserDao;
import com.desafio.rest.model.Session;
import com.desafio.rest.model.Token;
import com.desafio.rest.utils.RandomToken;
import com.google.gson.JsonObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.sql.SQLException;

@Path("/authentication")
public class Authentication {


    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("email") String email, @FormParam("password") String password) throws SQLException {
        JsonObject json = new JsonObject();
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            json.addProperty("message", "não foi possível obter valores na requisição");
            return Response.seeOther(URI.create("/index.jsp")).entity(json.toString()).build();
        }

        UserDao userDao = new UserDao();
        int id = userDao.logIn(email, password);
        if (id < 0) {
            json.addProperty("message", "Usuário e/ou senha errado.");
            return Response.status(200).entity(json.toString()).build();
        }
        
        String token = storeToken(id);
        
        json.addProperty("token", token);
        Session.instance.setValue("token", token);
        return Response.
                temporaryRedirect(URI.create("/home.jsp"))
                .cookie(new NewCookie("token", token, "/", "", 1, null, 24 * 60 * 60, false))
                .cookie(new NewCookie("username",
                        userDao.findOne(email).getName(),
                        "/",
                        "",
                        2,
                        null,
                        24 * 60 * 60,
                        false))
                .status(308).build();
    }

	private String storeToken(int id) throws SQLException {
		TokenDao tokenDao = new TokenDao();
        Token modelToken = new Token(RandomToken.generateString(), id);
        int tokenId = tokenDao.findOneByIdUser(id);
        if(tokenId > 0) {
        	tokenDao.editToken(tokenId, modelToken.getValue());
        	return modelToken.getValue();
        } else {
        	tokenDao.addToken(modelToken);
        }
		return modelToken.getValue();
	}
}

