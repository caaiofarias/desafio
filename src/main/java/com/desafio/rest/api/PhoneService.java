package com.desafio.rest.api;

import com.desafio.rest.dao.PhoneDao;
import com.google.gson.JsonObject;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;


@Path("/phones")
public class PhoneService {
    private PhoneDao phoneDao;

    @PostConstruct
    private void init() {
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
            if (idUser < 0) {
                throw new Exception("id cannot be negative");
            }
            phoneDao.removePhone(idPhone, idUser);
            msg = "Telefone removido com sucesso!";
            response = 200;
        } catch (SQLException e) {
            msg = "Erro ao remover telefone";
            e.printStackTrace();
        } catch (Exception e) {
            msg = "Não foi possível obter valores da requisição";
            response = 400;
            e.printStackTrace();
        }

        return Response.status(response).entity(msg).build();
    }
}
