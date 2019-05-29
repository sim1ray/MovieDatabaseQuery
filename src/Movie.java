/*
 * Movie objects represent movies that have the following attributes:
 * title, if movie was shot in color/black and white, duration, director name, three
 * actor names, the IMDB link, language, country, rating, year released, and IMDB score.
 */

public class Movie {
    private Integer id;
    private String color;
    private String title;
    private Integer duration;
    private String director;
    private String actor1;
    private String actor2;
    private String actor3;
    private String imdbLink;
    private String language;
    private String country;
    private String contentRating;
    private Integer year;
    private Double imdbScore;

    // Constructor
    public Movie(Integer id, String color, String title, Integer duration, String director, String actor1, String actor2, String actor3, String imdbLink, String language, String country, String contentRating, Integer year, Double imdbScore) {
        this.id = id;
        this.color = color;
        this.title = title;
        this.duration = duration;
        this.director = director;
        this.actor1 = actor1;
        this.actor2 = actor2;
        this.actor3 = actor3;
        this.imdbLink = imdbLink;
        this.language = language;
        this.country = country;
        this.contentRating = contentRating;
        this.year = year;
        this.imdbScore = imdbScore;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor1() {
        return actor1;
    }

    public void setActor1(String actor1) {
        this.actor1 = actor1;
    }

    public String getActor2() {
        return actor2;
    }

    public void setActor2(String actor2) {
        this.actor2 = actor2;
    }

    public String getActor3() {
        return actor3;
    }

    public void setActor3(String actor3) {
        this.actor3 = actor3;
    }

    public String getImdbLink() {
        return imdbLink;
    }

    public void setImdbLink(String imdbLink) {
        this.imdbLink = imdbLink;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContentRating() {
        return contentRating;
    }

    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getImdbScore() {
        return imdbScore;
    }

    public void setImdbScore(Double imdbScore) {
        this.imdbScore = imdbScore;
    }

    @Override
    public String toString() {
        return  "id: " + id + '\n' +
                "color: " + color + '\n' +
                "title: " + title + '\n' +
                "duration: " + duration + "\n" +
                "director: " + director + '\n' +
                "actor1: " + actor1 + '\n' +
                "actor2: " + actor2 + '\n' +
                "actor3: " + actor3 + '\n' +
                "imdbLink: " + imdbLink + '\n' +
                "language: " + language + '\n' +
                "country: " + country + '\n' +
                "contentRating: " + contentRating + '\n' +
                "year: " + year + '\n' +
                "imdbScore: " + imdbScore;
    }
}
