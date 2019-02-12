package edu.uga.cs.ei.moviepick;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Path("/movies")
public class MovieService {

    private int nextId = 0;
    List<Movie> list = initialize();

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

    private List<Movie> initialize() {
        List<Movie> list = new ArrayList<>();
        list.add(new Movie("Lego", "Animation", "A Lego Movie", 4));
        list.add(new Movie("Glass", "Fantasy", "A Night Shyamalan Fantasy thriller", 3));
        list.add(new Movie("Miss Bala", "Drama", "Effects of narco trafficking", 1));
        list.add(new Movie("Test 1", "Animation", "Test Movie 1", 4));
        list.add(new Movie("Test 2", "Drama", "Test Movie 2", 3));
        return list;
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getMovieJson() {
        //return Response.ok().entity(initialize()).build();

        GenericEntity<List<Movie>> entity = new GenericEntity<List<Movie>>(initialize()){};
        Response response = Response.ok( entity ).build();

        return response;
    }

    /**
     * Create a new student using an XML representation.
     * @param is data for a Student (XML) to be created
     * @return a response encoding
     */
    @POST
    @Consumes( MediaType.APPLICATION_XML )
    public Response createStudentXML( Movie movie )
    {
        System.out.println( "StudentService.createStudentXML; nextId: " + nextId );

        // create a new id for the student
        Integer id = nextId++;

        // store the new student
        list.add(new Movie("Test 4", "Comedy", "Test Movie 4", 1));

        // return a response
        return Response.created( URI.create( "/student/" + id ) ).build();
    }
}
