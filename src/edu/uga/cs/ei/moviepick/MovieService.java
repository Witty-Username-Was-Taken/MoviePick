package edu.uga.cs.ei.moviepick;

import org.jboss.resteasy.spi.NoLogWebApplicationException;

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

    int nextId = 5;
    List<Movie> list = Loader.initialize();

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
        //List<Movie> list = Loader.initialize();
        System.out.println("In the getAMovie method");
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
        return Response.ok().entity("Please check your Query Param. It should be /movies/search?title=Lego&rating=4&genre=Fantasy ").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getMovieXML() {
        //return Response.ok().entity(initialize()).build();

        GenericEntity<List<Movie>> entity = new GenericEntity<List<Movie>>(Loader.initialize()){};
        Response response = Response.ok( entity ).build();

        return response;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Movie getMovieByID(@PathParam("id") Integer id) {

        Movie result = new Movie();
        for (Movie movie : list) {
            if (movie.getId() == id) {
                result = movie;
            }
        }

        if( result == null ) {
            // if you'd like to log this exception in JBoss log file, use WebApplicationException
            // otherwise, use NoLogWebApplicationException, which will not log the exception
            // throw new WebApplicationException( Response.Status.NOT_FOUND );
            throw new NoLogWebApplicationException( Response.Status.NOT_FOUND );
        }

        return result;
    }

    @GET
    @Path("/theaters")
    @Produces(MediaType.APPLICATION_XML)
    public Response getTheatersXML() {

        GenericEntity<List<Theater>> entity = new GenericEntity<List<Theater>>(Loader.initTheaters()){};
        Response response = Response.ok( entity ).build();

        return response;
    }

    @POST
    @Consumes( MediaType.APPLICATION_XML )
    public Response createMovieXML( Movie movie ) {
        System.out.println( "StudentService.createMovieXML; nextId: " + nextId );

        // create a new id for the movie
        Integer id = nextId++;
        movie.setId(id);

        // store the new student
        list.add(movie);

        // return a response
        return Response.created( URI.create( "/movie/" + id ) ).build();
    }

    @PUT
    @Path( "/{id}" )
    @Consumes( MediaType.APPLICATION_XML )
    public Response updateMovieXML( @PathParam( "id" ) Integer id, Movie movie ) {
        Movie current = new Movie();
        for (Movie temp: list) {
            if (temp.getId() == id) {
                current = temp;
                temp.setDescription( movie.getDescription() );
                temp.setGenre( movie.getGenre() );
                temp.setRating( movie.getRating() );
                temp.setTitle( movie.getTitle() );
            }
        }

        if (current == null) {
            throw new NoLogWebApplicationException( Response.Status.NOT_FOUND );
        }

        return Response.noContent().build();
    }

    @DELETE
    @Path( "/{id}" )
    public Response deleteStudent( @PathParam("id") Integer id ) {

        for (Movie movie : list) {
            System.out.println("Movie ID: " + movie.getId());
            if (movie.getId() == id) {
                System.out.println("Found a match!");
                list.remove(movie);
                break;
            }
        }

        System.out.println("Returning");
        return Response.noContent().build();
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
}