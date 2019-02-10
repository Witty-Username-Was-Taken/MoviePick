package edu.uga.cs.ei.moviepick;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Movie")
public class MovieService {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Movie getMovieXML(@PathParam("id") Integer id) {

    }
}
