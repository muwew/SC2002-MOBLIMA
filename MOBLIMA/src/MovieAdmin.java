package MOBLIMA.src;
import java.io.Serializable;
import java.util.*;

public class MovieAdmin extends Account implements Serializable {

    public MovieAdmin(String username, String password) {
        super(username, password);
    }

    public ArrayList<Movie> showMainMenu(ArrayList<Movie> movieList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("================================================");
        System.out.println("1) Add movie");
        System.out.println("2) Update existing movie");
        System.out.println("3) List top 5 movies");
        System.out.println("4) List current movie listing");
        System.out.println("0) Back");
        System.out.println("================================================");

        int choice = sc.nextInt();

        while(choice != 0) {
            switch (choice) {
                case 1:
                    movieList = addMovie(movieList);
                    break;
                case 2:
                    movieList = showEditMenu(movieList);
                    break;
                case 3:
                    listTop5();
                    break;
                case 4:
                    printMovieList(movieList);
                    break;
                case 5:
                    break;
            }
            System.out.println("================================================");
            System.out.println("1) Add movie");
            System.out.println("2) Update existing movie");
            System.out.println("3) List top 5 movies");
            System.out.println("4) List current movie listing");
            System.out.println("0) Back");
            System.out.println("================================================");

            choice = sc.nextInt();
        }

        return movieList;
    }

   public ArrayList<Movie> addMovie(ArrayList<Movie> movieList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter movie title: ");
        String title = sc.next();

        Movie movieToAdd = new Movie(title);

        for (int i = 0; i < movieList.size(); i++) {
           if(movieList.contains(movieToAdd)){
               System.out.println("Movie already exists!");
               return movieList;
           }
        }

        movieToAdd.setMovieShowingStatus();
        if(movieToAdd.getMovieShowingStatus() != "END_SHOWING")
            movieList.add(movieToAdd);

        return movieList;
    }

    public ArrayList<Movie> showEditMenu(ArrayList<Movie> movieList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("================================================");
        System.out.println("1) Update movie showing status");
        System.out.println("2) Update movie slots");
        System.out.println("0) Back");
        System.out.println("================================================");

        int choice = sc.nextInt();

        while(choice != 0) {
            switch (choice) {
                case 1:
                    movieList = updateMovieShowingStatus(movieList);
                    break;
                case 2:
                    movieList = movieSlotsMenu(movieList);
                    break;
            }

            System.out.println("================================================");
            System.out.println("1) Update movie showing status");
            System.out.println("2) Update movie slots");
            System.out.println("0) Back");
            System.out.println("================================================");

            choice = sc.nextInt();
        }

        return movieList;
    }

    public ArrayList<Movie> updateMovieShowingStatus(ArrayList<Movie> movieList) {
        Scanner sc = new Scanner(System.in);
        printMovieList(movieList);
        System.out.println("0) Back");
        System.out.println("Which movie would you like to update?");
        int choice = sc.nextInt();

        if(choice == 0) return movieList;
        if(choice > movieList.size()) {
            System.out.println("Movie does not exist!");
            return movieList;
        }

        movieList.get(choice-1).setMovieShowingStatus();
        if(movieList.get(choice-1).getMovieShowingStatus() == "END_SHOWING"){
            movieList.remove(choice-1);
        }
        System.out.println("Movie showing status successfully updated!");
        return movieList;
    }

    public ArrayList<Movie> movieSlotsMenu(ArrayList<Movie> movieList) {
        Scanner sc = new Scanner(System.in);
        printMovieList(movieList);
        System.out.println("0) Back");
        System.out.println("Which movie's slots would you like to edit?");
        int index = sc.nextInt();

        if(index == 0) return movieList;

        ArrayList<MovieSlot> movieSlots = movieList.get(index-1).getMovieSlots();

        System.out.println("1) Add movie slots");
        System.out.println("2) Delete movie slots");
        System.out.println("0) Back");

        int choice = sc.nextInt();

        while (choice != 0) {
            switch (choice) {
                case 1:
                    //add movie slot
                    System.out.println("Enter date and time of movie: ");
                    System.out.println("month <space> dayOfMonth <space> startHour(24h) <space> startMin");
                    int m = sc.nextInt();
                    int d = sc.nextInt();
                    int h = sc.nextInt();
                    int min = sc.nextInt();

                    Cinema cinema = new Cinema();
                    cinema.setCinemaClass();
                    cinema.setElite();
                    cinema.setLocation();
                    System.out.println("Enter cinema number:");
                    int cinemaNo = sc.nextInt();
                    cinema.setCinemaNo(cinemaNo);
                    MovieSlot movieslot = new MovieSlot(m, d, h, min, cinema);
                    movieslot.setMovieType();
                    movieSlots.add(movieslot);
                    break;
                case 2:
                    int i = 1;
                    for (MovieSlot e1 : movieSlots) {
                        System.out.println(i++ + ")" + e1.getCinema().getLocation() + ", " + e1.getaDate().getTime());
                    }
                    System.out.println("0) Back");
                    System.out.println("Which movie slot would you like to delete?");
                    int indexToDelete = sc.nextInt();
                    if(indexToDelete == 0) break;
                    movieSlots.remove(indexToDelete-1);
                    break;
                case 3:
                    break;
            }

            System.out.println("1) Add movie slots");
            System.out.println("2) Delete movie slots");
            System.out.println("0) Back");

            choice = sc.nextInt();
        }

        movieList.get(index-1).setMovieSlot(movieSlots);
        return movieList;
    }

    
    public void listTop5() {
    }

    public void printMovieList(ArrayList<Movie> movieList) {
        System.out.println("Current movie listing: ");
        int i=1;
        for(Movie e: movieList){
            System.out.println(i++ +") "+e.getMovieTitle()+" <"+e.getMovieShowingStatus()+">");
        }
    }
}