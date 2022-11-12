package MOBLIMA.src.model;
import MOBLIMA.src.enums.MovieShowingStatus;

import java.io.Serializable;
import java.util.*;

/**
 * Class that represents the movies.
 */
public class Movie implements Serializable {
    /**
     * Title of movie.
     */
    private String movieTitle;

    /**
     * Contains all the slots of the movie.
     */
    private ArrayList<MovieSlot> movieSlot;

    /**
     * Showing status of the movie.
     */
    private MovieShowingStatus movieShowingStatus;

    /**
     * Details of the movie.
     */
    private MovieDetails movieDetails;

    /**
     * Total tickets sold for the movie.
     */
    private int totalMovieSales;

    /**
     * Constructor.
     * @param movieTitle Movie title.
     */
    public Movie(String movieTitle){
        this.movieTitle = movieTitle;
        this.movieSlot = new ArrayList<MovieSlot>();
        this.movieDetails = new MovieDetails();
        this.totalMovieSales = 0;
    }

    /**
     * Gets movie title.
     * @return Movie title.
     */
    public String getMovieTitle() {
        return movieTitle;
    }

    /**
     * Sets movie title.
     * @param movieTitle Movie title.
     */
    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    /**
     * Gets movie showing status.
     * @return Movie showing status.
     */
    public String getMovieShowingStatus(){
        return movieShowingStatus.toString();
    }

    /**
     * Sets the movies showing status.
     */
    public void setMovieShowingStatus(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Set movie showing status: 1)COMING SOON 2)PREVIEW 3)NOW SHOWING 4)END SHOWING");
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                this.movieShowingStatus = MovieShowingStatus.COMING_SOON;
                break;
            case 2:
                this.movieShowingStatus = MovieShowingStatus.PREVIEW;
                break;
            case 3:
                this.movieShowingStatus = MovieShowingStatus.NOW_SHOWING;
                break;
            case 4:
                this.movieShowingStatus = MovieShowingStatus.END_SHOWING;
                break;
        }
    }

    public void setMovieShowingStatus(MovieShowingStatus m){this.movieShowingStatus = m;}

    /**
     * Gets movie slots.
     * @return ArrayList of the movie slots.
     */
    public ArrayList<MovieSlot> getMovieSlots(){
        return movieSlot;
    }

    /**
     * Sets movie slots.
     * @param movieSlot ArrayList of the movie slots.
     */
    public void setMovieSlot(ArrayList<MovieSlot> movieSlot){
        this.movieSlot = movieSlot;
    }

    /**
     * Gets the movie details.
     * @return Movie details.
     */
    public MovieDetails getMovieDetails(){
        return movieDetails;
    }

    /**
     * Sets the movie details.
     * @param m Movie details.
     */
    public void setMovieDetails(MovieDetails m){
        this.movieDetails = m;
    }

    /**
     * Gets the total number of tickets purchased.
     * @return Number of ticket sales for the movie.
     */
    public int getTotalMovieSales() {
        return totalMovieSales;
    }

    /**
     * Sets the total number of tickets purchased.
     * @param totalMovieSales Number of ticket sales for the movie.
     */
    public void setTotalMovieSales(int totalMovieSales) {
        this.totalMovieSales = totalMovieSales;
    }
}
