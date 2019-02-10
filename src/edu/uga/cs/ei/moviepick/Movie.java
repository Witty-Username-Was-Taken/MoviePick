package edu.uga.cs.ei.moviepick;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private String title;
    private String genre;
    private String description;
    private int rating;
    private List<Theater> theaterList = new ArrayList<Theater>;

    public Movie() {
        this.title = null;
        this.genre = null;
        this.description = null;
        this.rating = 0;
        this.theaterList = null;
    }

    public Movie(String title, String genre, String description, int rating, List<Theater> theaterList) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.rating = rating;
        this.theaterList = theaterList;
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

}
