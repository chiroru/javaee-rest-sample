package jp.co.ctc_g.javaee_rest_sample.presentation;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import jp.co.ctc_g.javaee_rest_sample.service.MovieService;
import jp.co.ctc_g.javaee_rest_sample.service.domain.Movie;

@Stateless
@Path("movies")
public class MovieResource {

    @Inject
    private MovieService service;

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public Movie find(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public List<Movie> findAll() {
        return service.findAll();
    }

    @POST
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public Movie regist(Movie m) {
        service.regist(m);
        return m;
    }

    @PUT
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public Movie update(Movie m) {
        service.update(m);
        return m;
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public Movie deleteById(@PathParam("id") Long id) {
        System.out.println("==================>[" + id + "]");
        Movie m = service.findById(id);
        service.removeById(id);
        return m;
    }
}
