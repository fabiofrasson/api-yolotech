package br.com.yolotech.api_spring.controllers;

import br.com.yolotech.api_spring.dao.ContaDao;
import br.com.yolotech.api_spring.models.Conta;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("conta")
public class ContaController {
    private ContaDao contaDao = new ContaDao();

    // OK
    @GET
    @Path("criatabelaconta")
    @Produces(MediaType.APPLICATION_JSON)
    public Response criaTabelaConta() {
        ContaDao contaDao = new ContaDao();
        contaDao.criaTabelaConta();
        return Response.ok(new Gson().toJson("Tabela Conta criada com sucesso!")).build();
    }

    // OK
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addConta(Conta conta) {
        try {
            contaDao.addConta(conta);
            return Response.status(Response.Status.CREATED).entity(conta).build();
        } catch (Exception error) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error.getMessage()).build();
        }
    }

    // OK
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editConta(Conta conta, @PathParam("id") int id) {
        try {
            conta.setId(id);
            contaDao.editConta(conta);
            return Response.status(Response.Status.OK).entity(conta).build();
        } catch (Exception error) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error.getMessage()).build();
        }
    }

    // OK
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteConta(@PathParam("id") int id) {
        contaDao.deleteConta(id);
        return Response.status(202).entity("Usuário removido: " + id).build();
    }

    // OK
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Conta findById(@PathParam("id") int id) {
        return contaDao.findById(id);
    }

    // OK
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContas() {
        List<Conta> contas = contaDao.getContas();
        return Response.ok(new Gson().toJson(contas)).build();
    }
}
