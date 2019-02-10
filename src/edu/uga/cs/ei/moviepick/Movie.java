package edu.uga.cs.ei.moviepick;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    String title;
    String genre;
    String description;
    List<Rating> ratings = new ArrayList<Rating>();
    double averageRating;

    public Movie() {
        this.title = null;
        this.genre = null;
        this.description = null;
        this.ratings = null;
        this.averageRating = 0;
    }

    public Movie(String title, String genre, String description, List<Rating> ratings, double averageRating) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.ratings = ratings;
        this.averageRating = averageRating;
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

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public double calcAverageRating(List<Rating> ratings) {
        double sum = 0;
        for(Rating rating: ratings) {
            sum += rating.rating;
        }

        double average = (double) sum/ratings.size();

        return average;
    }
}
