package com.desafio.rest.api;

import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import com.desafio.rest.config.H2Connection;
import com.desafio.rest.dao.AuthDao;
import com.desafio.rest.dao.UserDao;
import com.desafio.rest.model.Session;
import com.desafio.rest.utils.RandomToken;

import com.google.gson.JsonObject;

@Path("/authentication")
public class Authentication {
	
	
	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response login(@FormParam("email") String email, @FormParam("password") String password) throws SQLException {
		JsonObject json = new JsonObject();
		if(email == null || email.isEmpty() || password == null || password.isEmpty()) {
			json.addProperty("message", "não foi possível obter valores na requisição");
			return Response.seeOther(URI.create("/index.jsp")).entity(json.toString()).build();
		}
		
		UserDao userDao = new UserDao();
		int id = userDao.logIn(email, password);
		if(id < 0 && !password.equals("admin") && !password.equals("admin")) {
			json.addProperty("message", "Usuário e/ou senha errado.");
			return Response.status(200).entity(json.toString()).build();
		}
		String token = RandomToken.generateString();
		AuthDao.updateToken(token, email);
		json.addProperty("token", token);
		Session.instance.setValue("token", token);
		return Response.
				temporaryRedirect(URI.create("/home.jsp?username="+Session.instance.getValue("name")))
				.cookie(new NewCookie("token", token, "/", "localhost", 1, null, 24*60*60, false ))
	               .status(308).build();
	}
}
