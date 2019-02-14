package edu.uga.cs.ei.moviepick;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Movie {

    private int id;
    private String title;
    private String genre;
    private String description;
    private String showTimes;
    private int rating;

    public Movie() {
        this.id = 0;
        this.title = null;
        this.genre = null;
        this.description = null;
        this.showTimes = null;
        this.rating = 0;
    }

    public Movie(int id, String title, String genre, String description, String showTimes, int rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.showTimes = showTimes;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(String showTimes) {
        this.showTimes = showTimes;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
