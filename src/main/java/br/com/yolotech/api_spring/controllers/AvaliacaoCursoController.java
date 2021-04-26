package br.com.yolotech.api_spring.controllers;

import br.com.yolotech.api_spring.dao.AvaliacaoCursoDao;
import br.com.yolotech.api_spring.models.AvaliacaoCurso;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("avaliacao")
public class AvaliacaoCursoController {
    private AvaliacaoCursoDao avaliacaoCursoDao = new AvaliacaoCursoDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvaliacoesCursos() {
        List<AvaliacaoCurso> avaliacoesCursos = avaliacaoCursoDao.getAvaliacoesCursos();
        return Response.ok(new Gson().toJson(avaliacoesCursos)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AvaliacaoCurso findById(@PathParam("id") int id) { return avaliacaoCursoDao.findById(id); }

    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAvaliacaoCurso(AvaliacaoCurso avaliacaoCurso) {
        try {
            avaliacaoCursoDao.addAvaliacaoCurso(avaliacaoCurso);
            return Response.status(Response.Status.CREATED).entity(avaliacaoCurso).build();
        } catch (Exception error) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editAvaliacaoCurso(AvaliacaoCurso avaliacaoCurso, @PathParam("id") int id) {
        try {
            avaliacaoCurso.setId(id);
            avaliacaoCursoDao.editAvaliacaoCurso(avaliacaoCurso);
            return Response.status(Response.Status.OK).entity(avaliacaoCurso).build();
        } catch (Exception error) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAvaliacaoCurso(@PathParam("id") int id) {
        avaliacaoCursoDao.deleteAvaliacaoCurso(id);
        return Response.status(202).entity("Usuário removido: " + id).build();
    }
}
