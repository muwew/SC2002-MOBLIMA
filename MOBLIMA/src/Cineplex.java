package MOBLIMA.src;
import java.util.*;

public class Cineplex {
    //private ArrayList<Movie> movieList;
    //private ArrayList<MovieSlot> movieSlotDetails;
    private Location location;
    private String cinemaCode;

    // Constructor
    public Cineplex () {
        this(null, "XXX");
    }

    public Cineplex (Location location, String cinemaCode) {
//        this.movieList = null;
//        this.movieSlotDetails = null;
        this.location = location;
        this.cinemaCode = cinemaCode;
    }

    // Getters and setters
    public Location getLocation() {
        return location;
    }

    public void setLocation() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter location:\n1)JEM 2)WestGate 3)Jurong West");
        int choice = sc.nextInt();

        if(choice == 1)
            this.location = Location.JEM;
        else if(choice == 2)
            this.location = Location.WEST_MALL;
        else
            this.location = Location.JURONG_POINT;
    }

    public String getCinemaCode() {
        return cinemaCode;
    }

    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }

    // Methods
//    private void addMovieSlot (MovieSlot movieSlot) {
//        this.movieSlotDetails.add(movieSlot);
//        System.out.println("Movie slot successfully added to movie slot list of cineplex at location " + this.location.toString());
//        System.out.println("Details of this movie slot are as follows: ");
//        movieSlotDetails.printMovieSlotDetails();
//    }
//
//    private void removeMovieSlot (MovieSlot movieSlot) {
//        this.movieSlotDetails.remove(movieSlot);
//        System.out.println("Movie slot successfully deleted from movie slot list of cineplex at location " + this.location.toString());
//        System.out.println("Details of this movie slot are as follows: ");
//        movieSlotDetails.printMovieSlotDetails();
//    }
//
//    private void updateMovieSlot (MovieSlot oldMovieSlot, MovieSlot updatedMovieSlot) {
//        removeMovieSlot(oldMovieSlot);
//        addMovieSlot(updatedMovieSlot);
//    }
//
//    private void addMovie (Movie movie) {
//        this.movieList.add(movie);
//        System.out.println("Movie " + movie.getMovieName() + " has been successfully added to movie list of cineplex at location " + this.location.toString());
//        System.out.println("The updated list of movies are as follows: ");
//        printMovieList();
//    }
//
//    private void removeMovie (Movie movie) {
//        this.movieList.remove(movie);
//        System.out.println("Movie " + movie.getMovieName() + " has been successfully removed from movie list of cineplex at location " + this.location.toString());
//        System.out.println("The updated list of movies are as follows: ");
//        printMovieList();
//    }
//
//    private void printMovieList () {
//        for (Movie i: movieList) {
//            System.out.println(i.getMovieName());
//        }
//    }
}
