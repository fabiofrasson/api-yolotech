package br.com.yolotech.api_spring.controllers;

import br.com.yolotech.api_spring.dao.CursoDao;
import br.com.yolotech.api_spring.models.Curso;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("curso")
public class CursoController {
    private CursoDao cursoDao = new CursoDao();

    // OK
    @GET
    @Path("criatabelacurso")
    @Produces(MediaType.APPLICATION_JSON)
    public Response criaTabelaCurso() {
        CursoDao cursoDao = new CursoDao();
        cursoDao.criaTabelaCurso();
        return Response.ok(new Gson().toJson("Tabela Curso criada com sucesso!")).build();
    }

    // OK
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCurso(Curso curso) {
        try {
            cursoDao.addCurso(curso);
            return Response.status(Response.Status.CREATED).entity(curso).build();
        } catch (Exception error) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error.getMessage()).build();
        }
    }

    // OK
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editCurso(Curso curso, @PathParam("id") int id) {
        try {
            curso.setId(id);
            cursoDao.editCurso(curso);
            return Response.status(Response.Status.OK).entity(curso).build();
        } catch (Exception error) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error.getMessage()).build();
        }
    }

    // OK
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCurso(@PathParam("id") int id) {
        cursoDao.deleteCurso(id);
        return Response.status(202).entity("Curso removido: " + id).build();
    }

    // OK
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Curso findById(@PathParam("id") int id) {
        return cursoDao.findById(id);
    }

    // OK
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCursos() {
        List<Curso> cursos = cursoDao.getCursos();
        return Response.ok(new Gson().toJson(cursos)).build();
    }
}
