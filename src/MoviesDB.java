
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

    public MoviesDB(String filename) throws FileNotFoundException {
        movies = new Movie[10];
        this.n = 0;
        File f = new File(filename);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String[] values = s.nextLine().split(",");
            int id = Integer.parseInt(values[0].trim());
            String color = values[1].trim();
            String title = values[2].trim();
            int duration = Integer.parseInt(values[3].trim());
            String director = values[4].trim();
            String actor1 = values[5].trim();
            String actor2 = values[6].trim();
            String actor3 = values[7].trim();
            String imdbLink = values[8].trim();
            String language = values[9].trim();
            String country = values[10].trim();
            String rating = values[11].trim();
            int year = Integer.parseInt(values[12].trim());
            double score = Double.parseDouble(values[13].trim());
            Movie m = new Movie(id, color, title, duration, director, actor1, actor2, actor3, imdbLink, language, country, rating, year, score);
            this.movies[n] = m;
            n++;
        }
    }

    // Create new Red Black tree sorted by the given field
    public void addFieldIndex(String field) {
        switch (field) {
            case "title_year":
                MovieProperty rbtYear = new MovieProperty();
                for (Movie m : this.movies) {
                    int year = m.getYear();
                    rbtYear.add(year, m.getId());
                }
                fields.put(field, rbtYear);
                break;
            case "movie_title":
                MovieProperty rbtTitle = new MovieProperty();
                for (Movie m : this.movies) {
                    String title = m.getTitle();
                    rbtTitle.add(title, m.getId());
                }
                fields.put(field, rbtTitle);
                break;
            case "color":
                MovieProperty rbtColor = new MovieProperty();
                for (Movie m : this.movies) {
                    String color = m.getColor();
                    rbtColor.add(color, m.getId());
                }
                fields.put(field, rbtColor);
                break;
            case "duration":
                MovieProperty rbtDuration = new MovieProperty();
                for (Movie m : this.movies) {
                    int duration = m.getYear();
                    rbtDuration.add(duration, m.getId());
                }
                fields.put(field, rbtDuration);
                break;
            case "director_name":
                MovieProperty rbtDirector = new MovieProperty();
                for (Movie m : this.movies) {
                    String director = m.getDirector();
                    rbtDirector.add(director, m.getId());
                }
                fields.put(field, rbtDirector);
                break;
            case "actor_name":
                MovieProperty rbtActor = new MovieProperty();
                for (Movie m : this.movies) {
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
                for (Movie m : this.movies) {
                    String link = m.getImdbLink();
                    rbtLink.add(link, m.getId());
                }
                fields.put(field, rbtLink);
                break;
            case "language":
                MovieProperty rbtLanguage = new MovieProperty();
                for (Movie m : this.movies) {
                    String lang = m.getLanguage();
                    rbtLanguage.add(lang, m.getId());
                }
                fields.put(field, rbtLanguage);
                break;
            case "country":
                MovieProperty rbtCountry = new MovieProperty();
                for (Movie m : this.movies) {
                    String country = m.getCountry();
                    rbtCountry.add(country, m.getId());
                }
                fields.put(field, rbtCountry);
                break;
            case "content_rating":
                MovieProperty rbtRating = new MovieProperty();
                for (Movie m : this.movies) {
                    String rating = m.getContentRating();
                    rbtRating.add(rating, m.getId());
                }
                fields.put(field, rbtRating);
                break;
            case "imdb_score":
                MovieProperty rbtScore= new MovieProperty();
                for (Movie m : this.movies) {
                    double score = m.getImdbScore();
                    rbtScore.add(score, m.getId());
                }
                fields.put(field, rbtScore);
                break;
        }
    }

    //Returns the hash map for red black trees for each of the fields
    public HashMap<String, MovieProperty> getFieldMap(){
        return fields;
    }

    //Prints movie details given the movie id
    public void print(int id) {
        Movie m = this.movies[id];
        System.out.println(m);
    }

    public static void main(String[] args) throws FileNotFoundException {
        MoviesDB movieDB = new MoviesDB("/users/Simone/Downloads/Simple.csv");
        movieDB.addFieldIndex("year");
        movieDB.addFieldIndex("imdb_score");
        Query query=new And(new Equal("year",2012),new Equal("imdb_score",6.1));
        HashSet<Integer> result = (HashSet<Integer>) query.execute(movieDB.getFieldMap());
        if(result!=null)
            System.out.println(result);
        Iterator<Integer> idIterator = result.iterator();
        while(idIterator.hasNext()) {
            int id = idIterator.next();
            movieDB.print(id);
        }
    }

//    public static void main(String[] args) throws FileNotFoundException {
//        MoviesDB db = new MoviesDB("/users/Simone/Downloads/Simple.csv");
//        for (int i = 0; i < db.movies.length; i++) {
//            System.out.println(db.movies[i]);
//        }
//    }
}
