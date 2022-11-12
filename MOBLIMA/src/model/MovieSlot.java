package MOBLIMA.src.model;
import MOBLIMA.src.enums.MovieType;

import java.io.Serializable;
import java.util.*;

/**
 * Class to represent the slot of movie.
 */
public class MovieSlot implements Serializable {
    /**
     * Date and time of movie.
     */
    private Calendar aDate;

    /**
     * Cinema where movie slot will be showed.
     */
    private Cinema cinema;

    /**
     * Movie type.
     */
    private MovieType movieType;

    /**
     * Movie.
     */
    private Movie movie;

    /**
     * Constructors
     * @param m Month of movie slot.
     * @param d Day of movie slot.
     * @param h Hour of movie slot.
     * @param min Minute of movie slot.
     * @param cinema Cinema for movie slot.
     * @param movie Movie.
     */
    public MovieSlot(int m, int d, int h, int min, Cinema cinema, Movie movie){
        this.aDate = new GregorianCalendar(2022, m-1, d, h, min);
        this.cinema = cinema;
        this.movieType = MovieType.DIGITAL;
        this.movie = movie;
    }

    /**
     * Gets movie type.
     * @return Movie type.
     */
    public MovieType getMovieType(){
        return movieType;
    }

    /**
     * Sets movie type.
     */
    public void setMovieType(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Movie type: 1)DIGITAL 2)THREE_D 3)BLOCKBUSTER 4)EXCLUSIVE 5)IMAX_THREE_D");
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                this.movieType = MovieType.DIGITAL;
                break;
            case 2:
                this.movieType = MovieType.THREE_D;
                break;
            case 3:
                this.movieType = MovieType.BLOCKBUSTER;
                break;
            case 4:
                this.movieType = MovieType.EXCLUSIVE;
                break;
            case 5:
                this.movieType = MovieType.IMAX_THREE_D;
                break;
        }
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    /**
     * Gets cinema.
     * @return Cinema.
     */
    public Cinema getCinema() {
        return cinema;
    }

    /**
     * Sets cinema.
     * @param cinema Cinema.
     */
    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    /**
     * Gets date of movie slot.
     * @return Date.
     */
    public Calendar getaDate() {
        return aDate;
    }

    /**
     * Sets date of movie slot.
     * @param aDate Date.
     */
    public void setaDate(Calendar aDate) {
        this.aDate = aDate;
    }

    /**
     * Gets movie of movie slot.
     * @return Movie.
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Sets movie of movie slot.
     * @param movie Movie.
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
