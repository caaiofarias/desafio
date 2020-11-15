package com.desafio.rest.api;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.desafio.rest.dao.PhoneDao;
import com.google.gson.JsonObject;



@Path("/phones")
public class PhoneService {
private PhoneDao phoneDao;
	
	@PostConstruct
	private void init( ) {
		phoneDao = new PhoneDao();
	}
	
	@GET
	@Path("/delete/{idPhone}/{idUser}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removePhone(@PathParam("idPhone") int idPhone, @PathParam("idUser") int idUser) {
		String msg = "";
		JsonObject json = null;
		int response = -1;
		try {
			if(idUser < 0) {
				throw new Exception("id cannot be negative");
			}
			phoneDao.removePhone(idPhone, idUser);;
			msg = "Telefone removido com sucesso!";
			response = 200;
			//json = Json.createObjectBuilder().add("msg", msg).build();
		} catch (SQLException e) {
			msg = "erro ao remover telefone";
//			json = Json.createObjectBuilder().add("msg", "erro ao remover telefone").build();
			e.printStackTrace();
		} catch (Exception e) {
			msg = "Não foi possível obter valores da requisição";
//			json = Json.createObjectBuilder().add("msg", "Não foi possível obter valores da requisição").build();
			System.out.println("caiu no catch");
			response = 400;
			e.printStackTrace();
		}
		
		return Response.status(response).entity(msg).build();
	}
}
