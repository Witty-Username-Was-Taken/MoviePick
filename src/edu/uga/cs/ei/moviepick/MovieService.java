package edu.uga.cs.ei.moviepick;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Path("/movies")
public class MovieService {

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAMovie(
            @QueryParam("genre") String genre,
            @QueryParam("rating") int rating,
            @QueryParam("title") String title) {
        System.out.println("Received query parameters: title=" + title);
        System.out.println("Received query parameters: rating=" + rating);
        System.out.println("Received query parameters: genre=" + genre);
        List<Movie> list = Loader.initialize();
        if (rating != 0) {
            List<Movie> result = findMoviesByRating(list, rating);
            if (result.size() > 0) {
                return Response.ok().entity(result).build();
            } else {
                return Response.ok().entity("Movie Not Found for rating " + rating).build();
            }
        }

        if (genre != null && !genre.isEmpty()) {
            List<Movie> result = findMoviesByGenre(list, genre);
            if (result.size() > 0) {
                return Response.ok().entity(result).build();
            } else {
                return Response.ok().entity("Movie Not Found for genre " + genre).build();
            }
        }

        if (title != null && !title.isEmpty()) {
            List<Movie> result = findMoviesByTitle(list, title);
            if (result.size() > 0) {
                return Response.ok().entity(result).build();
            } else{
                return Response.ok().entity("Movie Not Found for title " + title).build();
            }
        }
        return Response.ok().entity("Please check your Query Param. It should be /helloworld/search?title=Lego&rating=4&genre=Fantasy ").build();
    }

    private List<Movie> findMoviesByRating(List<Movie> list, int rating) {
        List<Movie> result = new ArrayList<>();
        Iterator<Movie> it = list.iterator();
        while (it.hasNext()) {
            Movie next = it.next();
            if (next.getRating() == rating) {
                result.add(next);
            }
        }
        return result;
    }

    private List<Movie> findMoviesByGenre(List<Movie> list, String genre) {
        List<Movie> result = new ArrayList<>();
        Iterator<Movie> it = list.iterator();
        while (it.hasNext()) {
            Movie next = it.next();
            if (next.getGenre().equalsIgnoreCase(genre)) {
                result.add(next);
            }
        }
        return result;
    }

    private List<Movie> findMoviesByTitle(List<Movie> list, String title) {
        List<Movie> result = new ArrayList<>();
        Iterator<Movie> it = list.iterator();
        while (it.hasNext()) {
            Movie next = it.next();
            if (next.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(next);
            }
        }
        return result;
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getMovieJson() {
        //return Response.ok().entity(initialize()).build();

        GenericEntity<List<Movie>> entity = new GenericEntity<List<Movie>>(Loader.initialize()){};
        Response response = Response.ok( entity ).build();

        return response;
    }

    @GET
    @Path("/theaters")
    @Produces(MediaType.APPLICATION_XML)
    public Response getMoviesXML() {

        GenericEntity<List<Theater>> entity = new GenericEntity<List<Theater>>(Loader.initTheaters()){};
        Response response = Response.ok( entity ).build();

        return response;
    }

}