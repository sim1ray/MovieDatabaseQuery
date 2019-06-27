/*
 * MoviesDB object contains a list of movies from a given csv file. It allows for data to be queried using
 * Query objects for each of the fields in the csv file. It constructs red black trees for each field to
 * accomplish this. The field names and corresponding red black tree are stored in a HashMap.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class MoviesDB<T extends Comparable<T>> {
    private String filename;
    private Movie[] movies;
    private HashMap<String, MovieProperty> fields = new HashMap<>();
    private int n;

    // Constructor
    // Creates MoviesDB object by reading given csv file
    public MoviesDB(String filename) throws FileNotFoundException {
        movies = new Movie[6000];
        this.n = 0;
        File f = new File(filename);
        Scanner s = new Scanner(f);
        s.nextLine();
        while (s.hasNextLine()) {
            String[] values = s.nextLine().split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
            Integer id = checkValidInteger(values[0]);
            String color = checkValidString(values[1]);
            String title = checkValidString(values[2]);
            Integer duration = checkValidInteger(values[3]);
            String director = checkValidString(values[4]);
            String actor1 = checkValidString(values[5]);
            String actor2 = checkValidString(values[6]);
            String actor3 = checkValidString(values[7]);
            String imdbLink = checkValidString(values[8]);
            String language = checkValidString(values[9]);
            String country = checkValidString(values[10]);
            String rating = checkValidString(values[11]);
            Integer year = checkValidInteger(values[12]);
            Double score = checkValidDouble(values[13]);

            // Construct Movie object
            Movie m = new Movie(id, color, title, duration, director, actor1, actor2, actor3, imdbLink, language, country, rating, year, score);
            this.movies[n] = m;
            n++;
        }
    }

    // Check if given String is valid
    public Integer checkValidInteger(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        s.trim();
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // Check if given string is a valid Double type
    public Double checkValidDouble(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        s.trim();
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // Check if given string is a valid Integer type
    public String checkValidString(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        return s.trim();
    }

    // Create new Red Black tree sorted by the given field
    public void addFieldIndex(String field) {
        switch (field) {
            case "year":
                MovieProperty rbtYear = new MovieProperty();
                for (int i = 0; i < this.n; i++) {
                    Movie m = this.movies[i];
                    Integer year = m.getYear();
                    rbtYear.add(year, m.getId());
                }
                fields.put(field, rbtYear);
                break;
            case "movie_title":
                MovieProperty rbtTitle = new MovieProperty();
                for (int i = 0; i < this.n; i++) {
                    Movie m = this.movies[i];
                    String title = m.getTitle();
                    rbtTitle.add(title, m.getId());
                }
                fields.put(field, rbtTitle);
                break;
            case "color":
                MovieProperty rbtColor = new MovieProperty();
                for (int i = 0; i < this.n; i++) {
                    Movie m = this.movies[i];
                    String color = m.getColor();
                    rbtColor.add(color, m.getId());
                }
                fields.put(field, rbtColor);
                break;
            case "duration":
                MovieProperty rbtDuration = new MovieProperty();
                for (int i = 0; i < this.n; i++) {
                    Movie m = this.movies[i];
                    Integer duration = m.getYear();
                    rbtDuration.add(duration, m.getId());
                }
                fields.put(field, rbtDuration);
                break;
            case "director_name":
                MovieProperty rbtDirector = new MovieProperty();
                for (int i = 0; i < this.n; i++) {
                    Movie m = this.movies[i];
                    String director = m.getDirector();
                    rbtDirector.add(director, m.getId());
                }
                fields.put(field, rbtDirector);
                break;
            case "actor_name":
                MovieProperty rbtActor = new MovieProperty();
                for (int i = 0; i < this.n; i++) {
                    Movie m = this.movies[i];
                    String actor = m.getActor1();
                    rbtActor.add(actor, m.getId());
                    actor = m.getActor2();
                    rbtActor.add(actor, m.getId());
                    actor = m.getActor3();
                    rbtActor.add(actor, m.getId());
                }
                fields.put(field, rbtActor);
                break;
            case "movie_imdb_link":
                MovieProperty rbtLink = new MovieProperty();
                for (int i = 0; i < this.n; i++) {
                    Movie m = this.movies[i];
                    String link = m.getImdbLink();
                    rbtLink.add(link, m.getId());
                }
                fields.put(field, rbtLink);
                break;
            case "language":
                MovieProperty rbtLanguage = new MovieProperty();
                for (int i = 0; i < this.n; i++) {
                    Movie m = this.movies[i];
                    String lang = m.getLanguage();
                    rbtLanguage.add(lang, m.getId());
                }
                fields.put(field, rbtLanguage);
                break;
            case "country":
                MovieProperty rbtCountry = new MovieProperty();
                for (int i = 0; i < this.n; i++) {
                    Movie m = this.movies[i];
                    String country = m.getCountry();
                    rbtCountry.add(country, m.getId());
                }
                fields.put(field, rbtCountry);
                break;
            case "content_rating":
                MovieProperty rbtRating = new MovieProperty();
                for (int i = 0; i < this.n; i++) {
                    Movie m = this.movies[i];
                    String rating = m.getContentRating();
                    rbtRating.add(rating, m.getId());
                }
                fields.put(field, rbtRating);
                break;
            case "imdb_score":
                MovieProperty rbtScore= new MovieProperty();
                for (int i = 0; i < this.n; i++) {
                    Movie m = this.movies[i];
                    Double score = m.getImdbScore();
                    rbtScore.add(score, m.getId());
                }
                fields.put(field, rbtScore);
                break;
        }
    }

    // Returns the hash map for red black trees for each of the fields
    public HashMap<String, MovieProperty> getFields(){
        return fields;
    }

    // Prints movie details given the movie id
    public void print(int id) {
        Movie m = this.movies[id-1];
        System.out.println(m + "\n");
        System.out.println("--------------------------------------------------------------------\n");
    }

    // Test Run
    public static void main(String[] args) throws FileNotFoundException {
        MoviesDB movieDB = new MoviesDB("/users/Simone/Downloads/movie_metadata.csv");
        movieDB.addFieldIndex("year");
        movieDB.addFieldIndex("imdb_score");
        movieDB.addFieldIndex("color");
        movieDB.addFieldIndex("actor_name");
        Query query = new And(new Equal("color", "Black and White"), new GT("imdb_score", 8.0));
        //Query query = new Equal("color", "Black and White");
        //Query query = new Not(new Or(new GTE("year",2009),new GT("imdb_score",6.5)));
        //Query query = new And(new GTE("year",2009),new GT("imdb_score",6.5));
        //Query query = new Or(new LT("year",2009),new GTE("imdb_score",7.8));
        //Query query = new And(new Equal("color", "Color"), new LTE("year", 2007), new Equal("actor_name", "James Franco"));
        HashSet<Integer> result = (HashSet<Integer>) query.execute(movieDB.getFields());
        if(result!=null)
            System.out.println(result + "\n");
        Iterator<Integer> idIterator = result.iterator();
        while(idIterator.hasNext()) {
            int id = idIterator.next();
            movieDB.print(id);
        }
    }

}
