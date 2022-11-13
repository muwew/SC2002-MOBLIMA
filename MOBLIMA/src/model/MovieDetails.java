package MOBLIMA.src.model;
import MOBLIMA.src.enums.Rated;

import java.io.Serializable;
import java.util.*;
import static MOBLIMA.src.model.Util.*;

/**
 * Class that stores the details of movies.
 */
public class MovieDetails implements Serializable {
    /**
     * All cast members in movie.
     */
    private ArrayList<String> cast;

    /**
     * Director of movie.
     */
    private String director;

    /**
     * Synopsis of movie.
     */
    private String synopsis;

    /**
     * Language of movie.
     */
    private String language;

    /**
     * Content rating of movie.
     */
    private Rated movieRated;

    /**
     * Runtime of movie.
     */
    private int runtime;

    /**
     * Opening date of movie.
     */
    private String openingDate;

    /**
     * Rating of movie.
     */
    private double rating;

    /**
     * All reviews of movie.
     */
    private ArrayList<Review> reviews;


    //constructor

    /**
     * Constructors.
     */
    public MovieDetails(){
        this.cast = null;
        this.director = "";
        this.synopsis = "";
        this.language = "";
        this.movieRated = Rated.G;
        this.runtime = -1;
        this.openingDate = "";
        this.rating = -1;
        reviews = new ArrayList<>();
    }
    public MovieDetails(ArrayList<String> cast, String director, String synopsis, String language, Rated movieRated, int runtime, String openingDate, double rating, ArrayList<Review> reviews){
        this.cast = cast;
        this.director = director;
        this.synopsis = synopsis;
        this.language = language;
        this.movieRated = movieRated;
        this.runtime = runtime;
        this.openingDate = openingDate;
        this.rating = rating;
        this.reviews = reviews;
    }

    //getters and setters

    /**
     * Gets the cast.
     * @return ArrayList of cast member's names.
     */
    public ArrayList<String> getCast() {
        return cast;
    }

    /**
     * Sets the cast.
     * @param cast ArrayList of cast.
     */
    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    /**
     * Gets director's name.
     * @return Director's name.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Sets director's name.
     * @param director Director's name.
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Gets synopsis.
     * @return Synopsis.
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * Sets synopsis.
     * @param synopsis Synopsis.
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * Gets language.
     * @return Language.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets language.
     * @param language Language.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Gets content rating.
     * @return Content rating.
     */
    public Rated getMovieRated() {
        return movieRated;
    }

    /**
     * Sets content rating.
     */
    public void setMovieRated() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Set movie showing status: 1)G 2)PG 3)PG13 4)NC16 5)M18 6)R21");
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                this.movieRated = Rated.G;
                break;
            case 2:
                this.movieRated = Rated.PG;
                break;
            case 3:
                this.movieRated = Rated.PG13;
                break;
            case 4:
                this.movieRated = Rated.NC16;
                break;
            case 5:
                this.movieRated = Rated.M18;
                break;
            case 6:
                this.movieRated = Rated.R21;
                break;
        }
    }

    
    /** 
     * @param movieRated
     */
    public void setMovieRated(Rated movieRated) {
        this.movieRated = movieRated;
    }

    /**
     * Gets runtime.
     * @return Runtime.
     */
    public int getRuntime() {
        return runtime;
    }

    /**
     * Sets runtime.
     * @param runtime Runtime.
     */
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    /**
     * Gets opening date.
     * @return Opening date.
     */
    public String getOpeningDate() {
        return openingDate;
    }

    /**
     * Sets opening date.
     * @param openingDate Opening date.
     */
    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    /**
     * Gets rating.
     * @return Rating.
     */
    public double getRating() {
        return rating;
    }

    /**
     * Gets all reviews.
     * @return ArrayList of all reviews.
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    /**
     * Sets reviews
     * @param reviews ArrayList of all reviews.
     */
    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * Sets rating.
     * @param rating Rating.
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Method to update the average rating of movie based on all reviews.
     */
    public void updateRating(){
        double rating=0;
        for(Review e: reviews){
            rating += e.getRating();
        }

        this.rating = rating / reviews.size();
    }

    /**
     * Method to print out all the movie details.
     */
    public void printMovieDetails(){
        System.out.print("Cast: ");
        if(cast != null ) {
            for (String castMember : cast) {
                if (castMember.equals(cast.get(cast.size() - 1)))
                    System.out.println(castMember);
                else
                    System.out.print(castMember + ", ");
            }
        }
        else
            System.out.println();
        System.out.println("Director: "+director);
        System.out.println("Synopsis: " + printLongString(synopsis, 70));
        System.out.println("Language: "+language);
        System.out.println("Rating: "+movieRated.toString());
        System.out.println("Runtime: "+runtime + " mins") ;
        System.out.println("Opening Date: "+openingDate);
        if(rating == -1)
            System.out.println("Overall reviewer rating : NA");
        else
            System.out.println("Overall reviewer rating: "+rating);

    }

}
