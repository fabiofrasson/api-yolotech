package br.com.yolotech.api_spring.controllers;

import br.com.yolotech.api_spring.dao.CategoriaDao;
import br.com.yolotech.api_spring.models.Categoria;
import br.com.yolotech.api_spring.models.Curso;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("categoria")
public class CategoriaController {
    private CategoriaDao categoriaDao = new CategoriaDao();

    // OK
    @GET
    @Path("criatabelacategoria")
    @Produces(MediaType.APPLICATION_JSON)
    public Response criaTabelaCategoria() {
        CategoriaDao categoriaDao = new CategoriaDao();
        categoriaDao.criaTabelaCategoria();
        return Response.ok(new Gson().toJson("Tabela Categoria criada com sucesso!")).build();
    }

    // OK
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCategoria(Categoria categoria) {
        try {
            categoriaDao.addCategoria(categoria);
            return Response.status(Response.Status.CREATED).entity(categoria).build();
        } catch (Exception error) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error.getMessage()).build();
        }
    }

    // OK
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editCategoria(Categoria categoria, @PathParam("id") int id) {
        try {
            categoria.setId(id);
            categoriaDao.editCategoria(categoria);
            return Response.status(Response.Status.OK).entity(categoria).build();
        } catch (Exception error) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error.getMessage()).build();
        }
    }

    // OK
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCategoria(@PathParam("id") int id) {
        categoriaDao.deleteCategoria(id);
        return Response.status(202).entity("Categoria removida: " + id).build();
    }

    // OK
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Categoria findById(@PathParam("id") int id) {
        return categoriaDao.findById(id);
    }

    // OK
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategorias() {
        List<Categoria> categorias = categoriaDao.getCategorias();
        return Response.ok(new Gson().toJson(categorias)).build();
    }
}
