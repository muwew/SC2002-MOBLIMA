package MOBLIMA.src;
import java.util.*;

public class MovieDetails {
    private ArrayList<String> cast;
    private String director;
    private String sypnosis;
    private String language;
    private Rated movieRated;
    private int runtime;
    private String openingDate;
    private int rating;

    //constructor
    public MovieDetails(){
        this.cast = null;
        this.director = null;
        this.sypnosis = null;
        this.language = null;
        this.movieRated = null;
        this.runtime = -1;
        this.openingDate = null;
        this.rating = -1;
    }
    public MovieDetails(ArrayList<String> cast, String director, String sypnosis, String language, Rated movieRated, int runtime, String openingDate, int rating){
        this.cast = cast;
        this.director = director;
        this.sypnosis = sypnosis;
        this.language = language;
        this.movieRated = movieRated;
        this.runtime = runtime;
        this.openingDate = openingDate;
        this.rating = rating;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void printMovieDetails(){
        System.out.print("Cast:");
        for(String castMember : cast){
            System.out.print(castMember+", ");
        }
        System.out.println("\nDirector: "+director);
        System.out.println("Sypnosis:");
        System.out.println(sypnosis);
        System.out.println("Language: "+language);
        System.out.println("Rating: "+movieRated.toString());
        System.out.println("Runtime: "+runtime);
        //System.out.println("Opening Date: "+openingDate.);
        System.out.println("Overall reviewer rating: "+rating);

    }
}
