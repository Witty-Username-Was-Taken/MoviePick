package edu.uga.cs.ei.moviepick;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Theater {

    private int id;
    private String name;
    private String address;
    private List<Movie> movies = new ArrayList<Movie>();

    public Theater() {
        this.id = 0;
        this.name = null;
        this.address = null;
        this.movies = null;
    }

    public Theater(int id, String name, String address, List<Movie> movies) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.movies = movies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
