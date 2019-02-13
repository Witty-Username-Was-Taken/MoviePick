package edu.uga.cs.ei.moviepick;

import java.util.ArrayList;
import java.util.List;

public class Loader {

    public static List<Movie> initialize() {

        List<Movie> list = new ArrayList<>();
        list.add(new Movie(1, "Lego", "Animation", "A Lego Movie", 4));
        list.add(new Movie(2, "Glass", "Fantasy", "A Night Shyamalan Fantasy thriller", 3));
        list.add(new Movie(3, "Miss Bala", "Drama", "Effects of narco trafficking", 1));
        list.add(new Movie(4, "Test 1", "Animation", "Test Movie 1", 4));
        list.add(new Movie(5, "Test 2", "Drama", "Test Movie 2", 3));
        return list;
    }

    public static List<Theater> initTheaters() {
        List<Theater> theaters = new ArrayList<Theater>();

        List<Movie> movies1 = new ArrayList<Movie>();
        movies1.add(new Movie(1, "Lego", "Animation", "A Lego Movie", 4));
        movies1.add(new Movie(2, "Glass", "Fantasy", "A Night Shyamalan Fantasy thriller", 3));

        List<Movie> movies2 = new ArrayList<Movie>();
        movies2.add(new Movie(3, "Miss Bala", "Drama", "Effects of narco trafficking", 1));
        movies2.add(new Movie(4, "Test 1", "Animation", "Test Movie 1", 4));


        List<Movie> movies3 = new ArrayList<Movie>();
        movies3.add(new Movie(5, "Test 2", "Drama", "Test Movie 2", 3));


        theaters.add(new Theater(1, "Beachwood", "Somewhere", movies1));
        theaters.add(new Theater(2, "AMC", "Over There", movies2));
        theaters.add(new Theater(3, "University 16", "I dunno", movies3));

        return theaters;
    }

}