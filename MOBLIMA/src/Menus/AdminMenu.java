package MOBLIMA.src.Menus;
import MOBLIMA.src.Accounts.MovieAdmin;
import MOBLIMA.src.Model.*;

import java.util.*;
import static MOBLIMA.src.DataManager.*;
import static MOBLIMA.src.Model.Util.*;

public class AdminMenu extends Menu {

    private ArrayList<Movie> movieList;
    private MovieAdmin admin;
    private boolean loggedIn = false;
    Scanner sc = new Scanner(System.in);


    public AdminMenu(){}

    protected void printMenu() {
        boolean exit = false;

        printSep();
        System.out.println("Welcome Admin! Please log into your account!");
        printSep();
        while (exit == false && loggedIn == false) {
            System.out.println("Enter username: (type 0 to quit)");
            String username = sc.next();
            if (username.equals("0")) exit = true;

            boolean match = false;
            for (MovieAdmin e : adminList) {
                if (e.getUsername().equals(username)) {
                    match = true;
                    System.out.println("Enter password: ");
                    String password = sc.next();
                    if (e.getPassword().equals(password)) {
                        loggedIn = true;
                        admin = e;
                        System.out.println("Succesfully logged in!");
                    } else {
                        System.out.println("Wrong password!");
                        match = true;
                    }
                }
            }
            if (exit) ;
            else if (!match) System.out.println("Account does not exist!");

        }

        if (exit) open(this, this.prevMenu);
        else if (loggedIn) {
            movieList = (ArrayList<Movie>) readData("movielist.txt");
            adminOptions();
        }
    }

    protected void adminOptions() {
        printSep();
        System.out.println("1) Add movie listing");
        System.out.println("2) Update movie listing");
        System.out.println("3) List current movie listing");
        System.out.println("4) List top 5 movies");
        System.out.println("5) Configure system settings");
        System.out.println("0) Log out");
        printSep();

        int choice = sc.nextInt();
        boolean exit = false;
        switch (choice) {
            case 1:
                sc.nextLine();
                addMovie();
                break;
            case 2:
                updateMovie();
                break;
            case 3:
                printMovieList();
                break;
            case 4:
                viewTop5();
                break;
            case 5:
                configSettings();
            case 0:
                exit = true;
                break;
        }

        if(exit == true){
            updateData(movieList, "movielist.txt");
            System.out.println("Logging out...");
            open(this, this.prevMenu);
        }
        else adminOptions();
    }

    protected void addMovie() {
        System.out.println("Enter movie title: (type 0 to quit)");
        String title = sc.nextLine();
        boolean valid = true;
        boolean exit = false;
        if (title.equals("0")) exit = true;
        Movie movieToAdd = new Movie(title);

        if (!exit) {
            for (int i = 0; i < movieList.size(); i++) {
                if (movieList.get(i).getMovieTitle().equals(title)) {
                    System.out.println("Movie already exists!");
                    valid = false;
                }
            }
        }

        if (exit) adminOptions();
        else if (valid) {
            movieToAdd.setMovieShowingStatus();
            if (movieToAdd.getMovieShowingStatus() != "END_SHOWING")
                movieList.add(movieToAdd);
        }
    }

    protected void updateMovie() {
        printSep();
        System.out.println("1) Update movie showing status");
        System.out.println("2) Update movie slots");
        System.out.println("3) Update movie details");
        System.out.println("0) Back");
        printSep();

        int choice = sc.nextInt();
        String[] options = {"showing status", "slots", "details"};
        boolean exit = false;

        if(choice != 0) {
            printMovieList();
            System.out.println("0) Back");
            printSep();

            System.out.println("Which movie's " + options[choice - 1] + " would you like to update?");
            int index = sc.nextInt();

            if (index != 0 && index <= movieList.size()) {
                switch (choice) {
                    case 1:
                        updateMovieShowingStatus(movieList.get(index - 1));
                        break;
                    case 2:
                        updateMovieSlots(movieList.get(index - 1));
                        break;
                    case 3:
                        updateMovieDetails(movieList.get(index - 1).getMovieDetails());
                        break;
                }
            }
        }
        else exit = true;

        if(exit) adminOptions();
        else updateMovie();
    }

    protected void updateMovieShowingStatus(Movie movie){
        movie.setMovieShowingStatus();
        updateMovie();
    }

    protected void updateMovieSlots(Movie movie){
        boolean exit = false;

        while(!exit){
            printSep();
            System.out.println("1) Add movie slots");
            System.out.println("2) Delete movie slots");
            System.out.println("0) Back");
            printSep();

            int choice = sc.nextInt();

            switch(choice){
                case 1:
                    addMovieSlot(movie.getMovieSlots(), movie);
                    break;
                case 2:
                    deleteMovieSlot(movie.getMovieSlots());
                    break;
                default:
                    exit = true;
                    break;
            }
        }

        if(exit) updateMovie();
    }

    protected void addMovieSlot(ArrayList<MovieSlot> newMovieSlots, Movie movie){
        try {
            System.out.println("Enter date and time of movie: ");
            System.out.println("dayOfMonth <space> month(number) <space> startHour(24h) <space> startMin");
            int d = sc.nextInt();
            int m = sc.nextInt()-1;
            int h = sc.nextInt();
            int min = sc.nextInt();

            Cinema cinema = new Cinema();
            cinema.setCinemaClass();
            cinema.setLocation();
            System.out.println("Enter cinema number (1-3):");
            int cinemaNo = sc.nextInt();
            cinema.setCinemaNo(cinemaNo);
            cinema.setCinemaCode(cinema.getLocation().toCode() + cinemaNo);
            MovieSlot movieslot = new MovieSlot(m, d, h, min, cinema, movie);
            movieslot.setMovieType();

            newMovieSlots.add(movieslot);
        }
        catch(InputMismatchException e){
            System.out.println("Invalid input!");
        }
    }

    protected void deleteMovieSlot(ArrayList<MovieSlot> newMovieSlots){
        int i = 1;
        printSep();
        for (MovieSlot e : newMovieSlots) {
            System.out.println(i++ + ") <" + e.getCinema().getLocation() + ">: " + e.getaDate().getTime());
        }
        System.out.println("0) Back");
        printSep();
        System.out.println("Which movie slot would you like to delete?");
        int indexToDelete = sc.nextInt();
        if(indexToDelete == 0 || indexToDelete > newMovieSlots.size()) return;
        newMovieSlots.remove(indexToDelete-1);
    }

    protected void updateMovieDetails(MovieDetails movieDetails){
        boolean exit = false;

        while(!exit){
            printSep();
            System.out.println("1) Cast");
            System.out.println("2) Director");
            System.out.println("3) Sypnosis");
            System.out.println("4) Language");
            System.out.println("5) Content Rating");
            System.out.println("6) Runtime");
            System.out.println("7) Opening Date");
            System.out.println("0) Back");
            printSep();

            int choice = sc.nextInt();

            if (choice == 0) exit = true;

            sc.nextLine();
            switch(choice){
                case 1:
                    System.out.println("Enter cast member's name: (type 0 to stop)");
                    ArrayList<String> cast = new ArrayList<>();
                    String input = sc.nextLine();
                    while(input != "0"){
                        cast.add(input);
                        input = sc.nextLine();
                    }
                    movieDetails.setCast(cast);
                    break;
                case 2:
                    System.out.println("Enter director name: ");
                    movieDetails.setDirector(sc.nextLine());
                    break;
                case 3:
                    System.out.println("Enter sypnosis: ");
                    movieDetails.setSypnosis(sc.nextLine());
                    break;
                case 4:
                    System.out.println("Enter language: ");
                    movieDetails.setLanguage(sc.nextLine());
                    break;
                case 5:
                    movieDetails.setMovieRated();
                    break;
                case 6:
                    System.out.println("Enter runtime (in minutes): ");
                    movieDetails.setRuntime(sc.nextInt());
                    break;
                case 7:
                    //sc.nextLine();
                    System.out.println("Enter opening date: dayOfMonth <space> month <space> year");
                    movieDetails.setOpeningDate(sc.nextLine());
                    break;
            }
        }


    }
    protected void viewTop5(){
        ArrayList<Movie> sortMovieList = movieList;
        sortMovieList.sort((o1, o2) -> Double.compare(o2.getMovieDetails().getRating(), o1.getMovieDetails().getRating()));
        printSep();
        System.out.println("Top 5 by ratings:");
        for (int i = 0; i < 5; i++) {
            Movie movie = sortMovieList.get(i);
            System.out.println((i + 1) + ") " + movie.getMovieTitle() + " <Rating: " + movie.getMovieDetails().getRating() + ">");
        }

        System.out.println("\nTop 5 by ticket sales:");
        HashMap<String, Integer> ticketCount = new HashMap<String, Integer>();
        for(TicketDetails e: ticketList){
            if(ticketCount.containsKey(e.getMovieTitle()))
                ticketCount.put(e.getMovieTitle(), ticketCount.get(e.getMovieTitle()) + 1);
        }

        if(ticketCount.size() < 5){
            System.out.println("Not enough data!");
        }
        else {
            Movie movieToView = null;
            ArrayList<String> top5List = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Map.Entry<String, Integer> maxEntry = null;
                for (Map.Entry<String, Integer> entry : ticketCount.entrySet()) {
                    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                        maxEntry = entry;
                    }
                }
                System.out.println((i + 1) + ") " + maxEntry.getKey() + "<Total ticket sales: " + maxEntry.getValue() + ">");
                top5List.add(maxEntry.getKey());
                ticketCount.remove(maxEntry.getKey());
            }
        }
        printSep();

        adminOptions();
    }
    protected void configSettings(){
        printSep();
        System.out.println("What would you like to do?");
        System.out.println("1) Add public holiday");
        System.out.println("2) Delete public holiday");
        System.out.println("3) Change top 5 movies option");
        System.out.println("0) Back");
        printSep();

        int choice = sc.nextInt();

        switch(choice){
            case 1:
                System.out.println("Enter dayOfMonth <space> month");
                int d = sc.nextInt();
                int m = sc.nextInt();
                System.out.println("Enter name of holiday");
                sc.nextLine();
                String name = sc.nextLine();
                PublicHoliday newPH = new PublicHoliday(m, d, name);
                phList.add(newPH);
                System.out.println("Succesfully added!");
                updateData(phList, "phlist.txt");
                phList = (ArrayList<PublicHoliday>) readData("phlist.txt");
                break;
            case 2:
                boolean found = false;
                int i=1;
                printSep();
                for(PublicHoliday e: phList){
                    System.out.println(i++ +") " + e.getName()
                            + "(" + e.getDate().get(Calendar.DAY_OF_MONTH) +"/" + e.getDate().get(Calendar.MONTH)+1 + ")");
                }
                System.out.println("0) Back");
                printSep();

                int index = sc.nextInt();
                if(index == 0) found = true;
                if(index != 0 && index <= phList.size()){
                    found = true;
                    phList.remove(index-1);
                    System.out.println("Successfully deleted!");
                }
                if(!found) System.out.println("Entry not found!");
                updateData(phList, "phlist.txt");
                phList = (ArrayList<PublicHoliday>) readData("phlist.txt");
                break;
            case 3:
                top5Options();
                break;
        }
        adminOptions();
    }

    protected void top5Options(){

        printSep();
        System.out.println("Top 5 movies options: ");
        System.out.println("1) rating: " + admin.isCanTop5ByRating());
        System.out.println("2) ticket sales: " + admin.isCanTop5BySales());
        System.out.println("0) Back");
        printSep();

        int choice = sc.nextInt();

        switch(choice){
            case 1:
                admin.setCanTop5ByRating(!admin.isCanTop5ByRating());
                break;
            case 2:
                admin.setCanTop5BySales(!admin.isCanTop5BySales());
                break;
            default:
                break;
        }
        updateData(adminList, "adminaccounts.txt");
        adminList = (ArrayList<MovieAdmin>) readData("adminaccounts.txt");
        configSettings();
    }

    protected void printMovieList() {
        printSep();
        System.out.println("Current movie listing: ");
        int i=1;
        for(Movie e: movieList){
            System.out.println(i++ +") "+e.getMovieTitle()+" <"+e.getMovieShowingStatus()+">");
        }
    }
}
