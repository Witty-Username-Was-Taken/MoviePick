package edu.uga.cs.ei.moviepick;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;
import java.io.BufferedReader;
import java.io.StringReader;
import java.net.URI;

public class Client {

    static URI link;

    static String movie1 = "<?xml version=\"1.0\"?><movie><id>13</id><title>Test</title><genre>Test</genre><description>Testing</description>2<rating></rating></movie>";


    public static void main(String[] args) {

        BufferedReader br = null;
        String output = null;

        try {

            System.out.println( "Creating a movie (XML):\n" + prettyPrintXML( movie1 ) );

            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target( "http://localhost:8080/cs8350_3_movies/api/movies/" );
            Response response = target.request().post( Entity.entity( movie1, MediaType.APPLICATION_XML ) );

            if( response.getStatus() != 201 ) {
                throw new RuntimeException( "POST Request failed: HTTP code: " + response.getStatus() );
            }
            else
                System.out.println( "Response status: " + response.getStatus() + ";  location: " + response.getLocation() );

            link = response.getLocation();

            response.close();

        /*ResteasyWebTarget target = client.target( "http://uml.cs.uga.edu:8080/students/api/student" );
        Response response = target.request().post( Entity.entity( movie1, MediaType.APPLICATION_XML ) );

        if( response.getStatus() != 201 ) {
            throw new RuntimeException( "POST Request failed: HTTP code: " + response.getStatus() );
        }
        else
            System.out.println( "Response status: " + response.getStatus() + ";  location: " + response.getLocation() );

        link = response.getLocation();

        response.close();*/
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
            final Node document = (Node) DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src).getDocumentElement();

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
