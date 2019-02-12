package edu.uga.cs.ei.moviepick;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Movie {

    private String title;
    private String genre;
    private String description;
    private int rating;

    public Movie() {
        this.title = null;
        this.genre = null;
        this.description = null;
        this.rating = 0;
    }

    public Movie(String title, String genre, String description, int rating) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.rating = rating;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
