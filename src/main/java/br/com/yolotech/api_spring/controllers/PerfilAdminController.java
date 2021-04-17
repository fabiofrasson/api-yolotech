package br.com.yolotech.api_spring.controllers;

import br.com.yolotech.api_spring.dao.PerfilAdminDAO;
import br.com.yolotech.api_spring.models.PerfilAdmin;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("perfiladmin")
public class PerfilAdminController {

    private PerfilAdminDAO perfilAdminDAO = new PerfilAdminDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaPerfisAdmin() {
        List<PerfilAdmin> perfisAdmin = perfilAdminDAO.listaPerfisAdmin();
        return Response.ok(new Gson().toJson(perfisAdmin)).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PerfilAdmin procuraPorId(@PathParam("id") int idAdmin) {
        return perfilAdminDAO.procuraPorId(idAdmin);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserePerfilAdmin(PerfilAdmin perfilAdmin) {
        try {
            perfilAdminDAO.adicionaAdmin(perfilAdmin);
            return Response.status(Response.Status.CREATED).entity(perfilAdmin).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alteraPerfilAdmin(@PathParam("id") int id, PerfilAdmin perfilAdmin) {

        PerfilAdmin perfilAdmin1 = perfilAdminDAO.alteraPerfilAdmin(id, perfilAdmin);
        GenericEntity<PerfilAdmin> entity = new GenericEntity<PerfilAdmin>(perfilAdmin1, PerfilAdmin.class);
        return Response.ok(new Gson().toJson(perfilAdmin1)).build();

//        PerfilAdmin perfilAdmin1 = perfilAdminDAO.procuraPorId(id);
//
//        if (perfilAdmin1 == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//
//        try {
//            perfilAdmin.setIdAdmin(id);
//            perfilAdminDAO.alteraPerfilAdmin(perfilAdmin);
//            return Response.status(Response.Status.OK).entity(perfilAdmin).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
//        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletaPerfilAdmin(@PathParam("id") int id) {
        perfilAdminDAO.removePerfilAdmin(id);
        return Response.status(202).entity("Usuário removido: " + id).build();
    }
}