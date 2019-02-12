package edu.uga.cs.ei.moviepick;// This client is using the new JAX-RS 2.0 client interface

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import java.net.URI;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.xml.sax.InputSource;

import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Link;

import javax.ws.rs.client.Entity;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

/*
* Class to populate datastore of movie and theater information
* using XML or JSON objects
 */

public class Loader {

    static final String student1 = "<?xml version=\"1.0\"?><movie><title>Lego Movie</title><genre>Animation</genre><description>Legos do what Legos do</description><rating>5</rating></movie>";

    static URI link;

    public static void main(String[] args) {

        BufferedReader br = null;
        String         output = null;

        try {

        // === Create student1 a using POST request (XML) ===
        // =================================================
        System.out.println( "Creating a movie (XML):\n" + prettyPrintXML( student1 ) );

        ResteasyClient client = new ResteasyClientBuilder().build();
        System.out.println("Made it here");
        ResteasyWebTarget target = client.target( "localhost:8080/cs8350_3_movies/movies" );
        Response response = target.request().post( Entity.entity( student1, MediaType.APPLICATION_XML ) );
        System.out.println("Made it past target");

        if( response.getStatus() != 201 ) {
            throw new RuntimeException( "POST Request failed: HTTP code: " + response.getStatus() );
        }
        else
            System.out.println( "Response status: " + response.getStatus() + ";  location: " + response.getLocation() );

        link = response.getLocation();

        response.close();

        }
        catch( Exception e ) {
            System.out.println( e );
            e.printStackTrace();
        }
    }

    public static String prettyPrintXML(String input) {

        try {
            final InputSource src = new InputSource(new StringReader(input));
            final Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src).getDocumentElement();

            final DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
            final DOMImplementationLS impl = (DOMImplementationLS) registry
                    .getDOMImplementation("LS");
            final LSSerializer writer = impl.createLSSerializer();

            writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE);
            writer.getDomConfig().setParameter("xml-declaration", true);

            return writer.writeToString(document);
        }

        catch (Exception e) {
            e.printStackTrace();
            return input;
        }
    }

}