package br.com.yolotech.api_spring.resources;

import br.com.yolotech.api_spring.dao.PerfilAdminDAO;
import br.com.yolotech.api_spring.entities.PerfilAdmin;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api")
public class PerfilAdminResource {

    @GET
    @Produces("application/json")
    public Response listaPerfisAdmin() {
        PerfilAdminDAO perfilAdminDAO = new PerfilAdminDAO();
        List<PerfilAdmin> perfisAdmin = perfilAdminDAO.listaPerfisAdmin();

        return Response.ok(new Gson().toJson(perfisAdmin)).build();
    }
}