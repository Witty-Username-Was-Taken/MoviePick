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


@Path("/movie")
public class MovieService {

    int nextId = 6;
    List<Movie> list = Loader.initialize();
    List<Theater> theaterList = Loader.initTheaters();

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_XML)
    public Response getAMovie(
            @QueryParam("genre") String genre,
            @QueryParam("rating") int rating,
            @QueryParam("title") String title) {
        System.out.println("Received query parameters: title=" + title);
        System.out.println("Received query parameters: rating=" + rating);
        System.out.println("Received query parameters: genre=" + genre);
        //List<Movie> list = Loader.initialize();

        if (list.isEmpty()) {
            list = Loader.initialize();
        }

        System.out.println("In the getAMovie method");

        GenericEntity<List<Movie>> entity;
        List<Movie> totalResults = new ArrayList<Movie>();

        try {

            for (Movie movie : list) {
                System.out.println("Movie title: " + movie.getTitle());
                if((movie.getTitle().equals(title) || movie.getGenre().equals(genre) || movie.getRating() == rating) && !totalResults.contains(movie)) {
                    System.out.println("Got a hit!");
                    totalResults.add(movie);
                }
            }

        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }

        entity = new GenericEntity<List<Movie>>(totalResults){};
        Response response = Response.ok(entity).build();

        return response;
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getMovieXML() {
        //return Response.ok().entity(initialize()).build();

        GenericEntity<List<Movie>> entity = new GenericEntity<List<Movie>>(list){};
        Response response = Response.ok( entity ).build();


        return response;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Movie getMovieByID(@PathParam("id") Integer id) {

        Movie result = null;

        try {
            for (Movie movie : list) {
                if (movie.getId() == id) {
                    System.out.println("Got a result in GET /{id}: " + movie.getId() + " " + id);
                    result = movie;
                }
            }

            if (result == null) {
                // if you'd like to log this exception in JBoss log file, use WebApplicationException
                // otherwise, use NoLogWebApplicationException, which will not log the exception
                // throw new WebApplicationException( Response.Status.NOT_FOUND );
                throw new NoLogWebApplicationException(Response.Status.NOT_FOUND);
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace();
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

    @GET
    @Path("/theaters/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getTheaterResultXML(@PathParam("id") Integer id) {
        Theater result = null;

        try {
            for (Theater theater : theaterList) {
                if (theater.getId() == id) {
                    System.out.println("Got a result in GET /{id}: " + theater.getId() + " " + id);
                    result = theater;
                }
            }

            if (result == null) {
                // if you'd like to log this exception in JBoss log file, use WebApplicationException
                // otherwise, use NoLogWebApplicationException, which will not log the exception
                // throw new WebApplicationException( Response.Status.NOT_FOUND );
                throw new NoLogWebApplicationException(Response.Status.NOT_FOUND);
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }

        Response response = Response.ok(result).build();

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

        try {
            for (Movie temp : list) {
                if (temp.getId() == id) {
                    current = temp;
                    temp.setTitle(movie.getTitle());
                    temp.setDescription(movie.getDescription());
                    temp.setShowTimes(movie.getShowTimes());
                    temp.setGenre(movie.getGenre());
                    temp.setRating(movie.getRating());
                }
            }

            if (current == null) {
                throw new NoLogWebApplicationException(Response.Status.NOT_FOUND);
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }

        return Response.noContent().build();
    }

    @DELETE
    @Path( "/{id}" )
    public Response deleteStudent( @PathParam("id") Integer id ) {
        int count = 0;
        for (Movie movie : list) {
            System.out.println("Movie ID: " + movie.getId());
            if (movie.getId() == id) {
                System.out.println("Found a match!");
                list.set(count, null);
                break;
            }
            count ++;
        }

        System.out.println("Returning");
        return Response.noContent().build();
    }
}