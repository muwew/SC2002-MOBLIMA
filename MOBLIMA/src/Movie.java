package MOBLIMA.src;
import java.io.Serializable;
import java.util.*;
public class Movie implements Serializable {
    private String movieTitle;
    private ArrayList<MovieSlot> movieSlot;

    private MovieShowingStatus movieShowingStatus;

    public Movie(String movieTitle){
        this.movieTitle = movieTitle;
        this.movieSlot = new ArrayList<MovieSlot>();
<<<<<<< Updated upstream
        //this.movieShowingStatus = MovieShowingStatus.END_SHOWING;
=======
        this.movieDetails = new MovieDetails();
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
    @Override
    public boolean equals(Object object){
        boolean sameSame = false;
        if(object != null && object instanceof Movie){
            sameSame = this.movieTitle == ((Movie)object).movieTitle;
=======
    public MovieDetails getMovieDetails(){
        return movieDetails;
    }
    public void setMovieDetails(MovieDetails m){
        this.movieDetails = m;
    }
    public void setMovieDetails(){
        Scanner sc = new Scanner(System.in);
        MovieDetails movieDetails1 = new MovieDetails();
        System.out.println("Enter cast member's name: (type 0 to stop)");
        ArrayList<String> cast = new ArrayList<>();
        String input = sc.nextLine();
        while(input != "0"){
            cast.add(input);
            input = sc.nextLine();
>>>>>>> Stashed changes
        }

        return sameSame;
    }
}
