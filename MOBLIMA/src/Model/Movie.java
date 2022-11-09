package MOBLIMA.src.Model;
import MOBLIMA.src.Enums.MovieShowingStatus;

import java.io.Serializable;
import java.util.*;
public class Movie implements Serializable {
    private String movieTitle;
    private ArrayList<MovieSlot> movieSlot;

    private MovieShowingStatus movieShowingStatus;
    private MovieDetails movieDetails;

    public Movie(String movieTitle){
        this.movieTitle = movieTitle;
        this.movieSlot = new ArrayList<MovieSlot>();
        this.movieDetails = new MovieDetails();
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieShowingStatus(){
        return movieShowingStatus.toString();
    }
    public void setMovieShowingStatus(MovieShowingStatus m){this.movieShowingStatus = m;}

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

    public ArrayList<MovieSlot> getMovieSlots(){
        return movieSlot;
    }

    public void setMovieSlot(ArrayList<MovieSlot> movieSlot){
        this.movieSlot = movieSlot;
    }

    public MovieDetails getMovieDetails(){
        return movieDetails;
    }
    public void setMovieDetails(MovieDetails m){
        this.movieDetails = m;
    }

}