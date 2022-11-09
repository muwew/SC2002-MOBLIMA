package MOBLIMA.src.Model;
import MOBLIMA.src.Enums.Rated;

import java.io.Serializable;
import java.util.*;
import static MOBLIMA.src.Model.Util.*;

public class MovieDetails implements Serializable {
    private ArrayList<String> cast;
    private String director;
    private String sypnosis;
    private String language;
    private Rated movieRated;
    private int runtime;
    private String openingDate;
    private double rating;
    private ArrayList<Review> reviews;


    //constructor
    public MovieDetails(){
        this.cast = null;
        this.director = "";
        this.sypnosis = "";
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
        this.sypnosis = synopsis;
        this.language = language;
        this.movieRated = movieRated;
        this.runtime = runtime;
        this.openingDate = openingDate;
        this.rating = rating;
        this.reviews = reviews;
    }

    //getters and setters
    public ArrayList<String> getCast() {
        return cast;
    }
    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getSypnosis() {
        return sypnosis;
    }

    public void setSypnosis(String sypnosis) {
        this.sypnosis = sypnosis;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Rated getMovieRated() {
        return movieRated;
    }

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

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public double getRating() {
        return rating;
    }

    public void setMovieRated(Rated movieRated) {
        this.movieRated = movieRated;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void updateRating(){
        double rating=0;
        for(Review e: reviews){
            rating += e.getRating();
        }

        this.rating = rating / reviews.size();
    }
    public void printMovieDetails(){
        System.out.print("Cast: ");
        for(String castMember : cast){
            if(castMember.equals(cast.get(cast.size()-1)))
                System.out.println(castMember);
            else
                System.out.print(castMember+", ");
        }
        System.out.println("Director: "+director);
        System.out.println("Sypnosis: " + printLongString(sypnosis, 70));
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
