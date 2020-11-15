package com.desafio.rest.api;


import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.desafio.rest.dao.PhoneDao;
import com.desafio.rest.dao.UserDao;
import com.desafio.rest.model.Phone;
import com.desafio.rest.model.User;
import com.desafio.rest.utils.Secured;
import com.google.gson.JsonObject;



@Path("/users")
public class UserService {

	private UserDao userDao;
	
	@PostConstruct
	private void init( ) {
		userDao = new UserDao();
	}
	
	@PUT
	@Path("/edit/{id}")
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String editUser(User user) {
		String msg = "";
		try {
			User userExists = userDao.findOne(user.getEmail());
			if(userExists != null) {
				if(user.getPhones() != null) {
					for( Phone phone : user.getPhones()) {
						PhoneDao phoneDao = new PhoneDao();
						phoneDao.addPhone(phone, userExists.getId());
					}
				}
				userDao.editUser(user);
				msg = "Usuário editado com sucesso!";
			} else {
				try {
					save(user);
					msg = "Usuário criado";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			msg = "Algum erro ocorreu, tente novamente";
			e.printStackTrace();
		}
		return msg;	
	}
	
	@POST
	@Path("/add")
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(User user) throws Exception {
		Response r = null;
		String msg = "";
		JsonObject json = new JsonObject();
		int response = 200;
		
		try {
			if(user.getEmail() == null || user.getPassword() == null) {
				msg = "nome ou usuário vazio";
				json.addProperty("message", msg);
				r = Response.status(response).entity(json.toString()).type(MediaType.APPLICATION_JSON).build();
				return r;
			}
			
			if(userDao.findOne(user.getEmail()) != null) {
				msg = "usuário já cadastrado";
				response = 200;
				json.addProperty("message", msg);
				r = Response.status(response).entity(json.toString()).type(MediaType.APPLICATION_JSON).build();
				return r;
			}
		  
			int id = userDao.addUser(user);
			if(user.getPhones() != null) {
				if(user.getPhones().size() > 0 && !user.getPhones().get(0).getNumber().isEmpty()) {
					for (Phone phone : user.getPhones()) {
						PhoneDao phoneDao = new PhoneDao();
						phoneDao.addPhone(phone, id);
					}
				}
			}
			msg = "usuário cadastrado com sucesso";
			
		} catch (SQLException e) { 
			msg = "erro ao criar usuário";
			response = 500;
			e.printStackTrace(); 
		}
		json.addProperty("message", msg);
		r = Response.status(response).entity(json.toString()).type(MediaType.APPLICATION_JSON).build();
		return r;
	}
	
	@GET
	@Path("/list")
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> listUsers() {
		List<User> list = null;
		try {
			list = userDao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeUser(@PathParam("id") String id) throws Exception {
		
		String msg = "";
		int idUser = Integer.valueOf(id);
		JsonObject json = new JsonObject();
		int response = -1;
		
		try {
			if(idUser < 0) {
				throw new Exception("id cannot be negative");
			}
			userDao.removeUser(idUser);
			msg = "Usuário removido com sucesso!";
			response = 200;
			json.addProperty("message", msg);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("caiu no catch");
			response = 500;
			e.printStackTrace();
		}
		
		return Response.status(response).entity(json.toString()).type(MediaType.APPLICATION_JSON).build();
		
	}
	
	
}
