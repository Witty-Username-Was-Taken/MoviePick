package edu.uga.cs.ei.moviepick;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.w3c.dom.Element;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.StringReader;
import java.net.URI;

public class Client {

    static URI link;

    static String movie1 = "<?xml version=\"1.0\"?><movie><id>7</id><title>Test</title><genre>Test</genre><description>Testing</description><rating>2</rating></movie>";
    static String movie2 = "<?xml version=\"1.0\"?><movie><id>8</id><title>Test 2</title><genre>Test 2</genre><description>Testing 2</description><rating>3</rating></movie>";
    static String updateMovie = "<?xml version=\"1.0\"?><movie><id>5</id><title>The Prodigy</title><genre>Action</genre><description>\"A mother concerned about her young son's disturbing behavior thinks something supernatural may be affecting him.\"</description><rating>5/rating></movie>";


    public static void main(String[] args) {

        BufferedReader br = null;
        String output = null;

        try {

            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target( "http://localhost:8080/cs8350_3_movies/api/movie/" );
            Response response;

            // === Retrieve all movies using a GET request and XML representation ===
            // =========================================================================
            System.out.println();
            System.out.println( "Retrieving all movies (XML representation)" );

            // perform a GET request, asking for an XML representation
            response = target.request( MediaType.APPLICATION_XML ).get();

            if( response.getStatus() != 200 ) {
                throw new RuntimeException( "GET Request failed: HTTP code: " + response.getStatus() );
            }
            else {
                System.out.println( "Response status: " + response.getStatus() );

                String p = response.readEntity( String.class );
                System.out.println( prettyPrintXML( p ) );
            }
            response.close();

            // === Update the genre of an existing movie ===
            // =============================================
            System.out.println();
            System.out.println("Updating movie genre using PUT");

            target = client.target( "http://localhost:8080/cs8350_3_movies/api/movie/5" );
            response = target.request(MediaType.APPLICATION_XML).put(Entity.entity(updateMovie, MediaType.APPLICATION_XML));
            if(response.getStatus() != 204) {
                System.out.println("PUT Request failed. HTTP Code: " + response.getStatus());
            }

            else {
                System.out.println("Response status: " + response.getStatus());
            }
            response.close();

            response = target.request(MediaType.APPLICATION_XML).get();
            System.out.println(prettyPrintXML(response.readEntity(String.class)));

            response.close();

            // === Create a new movie ===
            // ==========================

            System.out.println( "Creating a movie (XML):\n" + prettyPrintXML( movie1 ) );

            target = client.target( "http://localhost:8080/cs8350_3_movies/api/movie" );
            response = target.request().post( Entity.entity( movie1, MediaType.APPLICATION_XML ) );

            if( response.getStatus() != 201 ) {
                System.out.println("POST Request failed: HTTP code: " + response.getStatus());
            }
            else
                System.out.println( "Response status: " + response.getStatus() + ";  location: " + response.getLocation() );

            link = response.getLocation();

            response.close();

            // === Create another new movie ===
            // ==========================

            System.out.println( "Creating another movie (XML):\n" + prettyPrintXML( movie2 ) );

            target = client.target( "http://localhost:8080/cs8350_3_movies/api/movie" );
            response = target.request().post( Entity.entity( movie2, MediaType.APPLICATION_XML ) );

            if( response.getStatus() != 201 ) {
                System.out.println("POST Request failed: HTTP code: " + response.getStatus());
            }
            else
                System.out.println( "Response status: " + response.getStatus() + ";  location: " + response.getLocation() );

            //link = response.getLocation();

            response.close();

            System.out.println("Getting created movie " + link);

            target = client.target(link);
            response = target.request().get();

            if( response.getStatus() != 200 ) {
                System.out.println("GET Request failed: HTTP code: " + response.getStatus());
            }
            else {
                System.out.println( "Response status: " + response.getStatus() );

                String p = response.readEntity( String.class );
                System.out.println( prettyPrintXML( p ) );
            }

            response.close();

            // === Delete the student using a DELETE request ===
            // =================================================
            System.out.println( "Deleting the movie: " + link );

            target = client.target( link );
            response = target.request().delete();

            if( response.getStatus() != 200 && response.getStatus() != 204 ) {
                System.out.println("DELETE Request failed: HTTP code: " + response.getStatus() );
            }
            else
                System.out.println( "Response status: " + response.getStatus() );
            response.close();

            // === Attempt to retrieve the deleted movie using a GET request using XML representation
            // === This request SHOULD fail
            System.out.println();
            System.out.println( "Retrieving a movie (XML representation): " + link );

            target = client.target( link );
            response = target.request( MediaType.APPLICATION_XML ).get();

            if( response.getStatus() == 404 ) {
                System.out.println( "GET Request failed: This movie does not exist: " + link );
            }
            else if( response.getStatus() != 200 ) {
                System.out.println("GET Request failed: HTTP code: " + response.getStatus() );
            }
            else {
                System.out.println( "Response status: " + response.getStatus() );

                String p = response.readEntity( String.class );
                System.out.println( p );
            }
            response.close();

            // === GET call on the search page with no parameter ===
            // =====================================================
            target = client.target("http://localhost:8080/cs8350_3_movies/api/movie/search");
            response = target.request(MediaType.APPLICATION_XML).get();

            System.out.println("");
            System.out.println("Response from /search with no parameters:");
            System.out.println(response.readEntity(String.class));

            response.close();

            // === GET call on the search page with a rating search ===
            // ========================================================
            target = client.target("http://localhost:8080/cs8350_3_movies/api/movie/search?title=Glass");
            response = target.request(MediaType.APPLICATION_XML).get();
            System.out.println();
            System.out.println("Response from /search with parameters title = Glass");
            System.out.println(response.readEntity(String.class));

            response.close();

            // === GET call for all theaters and their information ===
            // =======================================================
            target = client.target("http://localhost:8080/cs8350_3_movies/api/movie/theaters");
            response = target.request(MediaType.APPLICATION_XML).get();
            System.out.println();
            System.out.println("GET all theater information with http://localhost:8080/cs8350_3_movies/api/movie/theaters");
            System.out.println(prettyPrintXML(response.readEntity(String.class)));

            response.close();

            // === GET call for a specific theater ===
            // =======================================
            target = client.target("http://localhost:8080/cs8350_3_movies/api/movie/theaters/1");
            response = target.request(MediaType.APPLICATION_XML).get();
            System.out.println("GET theater information where id = 1: http://localhost:8080/cs8350_3_movies/api/movie/theaters/1");
            System.out.println(prettyPrintXML(response.readEntity(String.class)));
        }

        catch( Exception e ) {
            System.out.println( e );
            e.printStackTrace();
        }

    }

    public static String prettyPrintXML(String input)
    {
        try
        {
            final InputSource src = new InputSource(new StringReader(input));
            final Element document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src).getDocumentElement();

            final DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
            final DOMImplementationLS impl = (DOMImplementationLS) registry
                    .getDOMImplementation("LS");
            final LSSerializer writer = impl.createLSSerializer();

            writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE);
            writer.getDomConfig().setParameter("xml-declaration", true);

            return writer.writeToString(document);
        } catch (Exception e) {
            e.printStackTrace();
            return input;
        }
    }
}
