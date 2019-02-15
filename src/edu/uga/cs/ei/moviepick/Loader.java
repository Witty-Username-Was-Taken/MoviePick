package edu.uga.cs.ei.moviepick;

import java.util.ArrayList;
import java.util.List;

public class Loader {

    public static List<Movie> initialize() {

        List<Movie> list = new ArrayList<>();
        list.add(new Movie(1, "Lego Movie 2", "Animation", "It's been five years since everything was awesome and the citizens are facing a huge new threat: Lego Duplo invaders from outer space, wrecking everything faster than they can rebuild.", "11:45 am, 12:30pm, 2:30pm, 3:30pm, 5:30pm, 6:30pm, 9:30pm ", 4));
        list.add(new Movie(2, "Glass", "Fantasy", "Security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. ", "1:00pm, 4:00pm, 7:15pm, 10:30pm ", 3));
        list.add(new Movie(3, "Green Book", "Biography", "A working-class Italian-American bouncer becomes the driver of an African-American classical pianist on a tour of venues through the 1960s American South.", "12:45pm, 3:45pm, 6:45pm, 9:45pm", 4));
        list.add(new Movie(4, "What Men Want", "Comedy", "A woman is boxed out by the male sports agents in her profession, but gains an unexpected edge over them when she develops the ability to hear men's thoughts.", "12:00pm, 3:00pm, 4:00pm, 6:15pm, 7:00pm, 9:00pm",2));
        list.add(new Movie(5, "The Prodigy", "Thriller", "A mother concerned about her young son's disturbing behavior thinks something supernatural may be affecting him.", "11:30am, 5:00pm, 7:45pm, 10:15pm ",3));
        return list;
    }

    public static List<Theater> initTheaters() {
        List<Theater> theaters = new ArrayList<Theater>();

        List<Movie> movies1 = new ArrayList<Movie>();
        movies1.add(new Movie(1, "Lego Movie 2", "Animation", "It's been five years since everything was awesome and the citizens are facing a huge new threat: Lego Duplo invaders from outer space, wrecking everything faster than they can rebuild.", "11:45 am, 12:30pm, 2:30pm, 3:30pm, 5:30pm, 6:30pm, 9:30pm ", 4));
        movies1.add(new Movie(2, "Glass", "Fantasy", "Security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. ", "1:00pm, 4:00pm, 7:15pm, 10:30pm ", 3));

        List<Movie> movies2 = new ArrayList<Movie>();
        movies2.add(new Movie(3, "Green Book", "Biography", "A working-class Italian-American bouncer becomes the driver of an African-American classical pianist on a tour of venues through the 1960s American South.", "12:45pm, 3:45pm, 6:45pm, 9:45pm", 4));
        movies2.add(new Movie(4, "What Men Want", "Comedy", "A woman is boxed out by the male sports agents in her profession, but gains an unexpected edge over them when she develops the ability to hear men's thoughts.", "12:00pm, 3:00pm, 4:00pm, 6:15pm, 7:00pm, 9:00pm",2));


        List<Movie> movies3 = new ArrayList<Movie>();
        movies3.add(new Movie(5, "The Prodigy", "Thriller", "A mother concerned about her young son's disturbing behavior thinks something supernatural may be affecting him.", "11:30am, 5:00pm, 7:45pm, 10:15pm ",3));


        theaters.add(new Theater(1, "AMC DINE-IN Athens 12", "1575 Lexington Rd, Athens, GA 30605", movies1));
        theaters.add(new Theater(2, "GTC Beechwood Cinemas", "196 Alps Rd, Athens, GA 30606 ", movies2));
        theaters.add(new Theater(3, "University 16 Cinemas", "1793 Oconee Connector, Athens, GA 30606 ", movies3));

        return theaters;
    }

}
