package MOBLIMA.src.menus;
import MOBLIMA.src.accounts.MovieAdmin;
import MOBLIMA.src.model.*;

import java.text.DecimalFormat;
import java.util.*;
import static MOBLIMA.src.DataManager.*;
import static MOBLIMA.src.model.Util.*;

/**
 * Main menu for admins.
 */
public class AdminMenu extends Menu {

    /**
     * Account of the admin when they log in.
     */
    private MovieAdmin admin;

    /**
     * If admin has logged in.
     */
    private boolean loggedIn = false;
    Scanner sc = new Scanner(System.in);

    /**
     * Constructor
     */
    public AdminMenu(){}

    /**
     * Method called when menu first opened.
     */
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

    /**
     * Displays the main Admin menu.
     * 6 options:
     * 1. Add movie to current listing.
     * 2. Update current movie listing.
     * 3. View all movies in current listing.
     * 4. View top 5 movies.
     * 5. Configure system settings.
     * 6. Log out.
     */
    protected void adminOptions(){
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
                updateData(movieList, "movielist.txt");
                break;
            case 2:
                updateMovie();
                updateData(movieList, "movielist.txt");
                break;
            case 3:
                printMovieList(true);
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

    /**
     * Method to add new movie to current listing.
     * When new movie added, set movie showing status.
     */
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
            movieList.add(movieToAdd);
        }
    }

    /**
     * Displays options to update movie in current listing.
     * 4 options:
     * 1. Update movie showing status.
     * 2. Update movie time slots.
     * 3. Update movie details.
     * 4. Go back.
     */
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
            printMovieList(true);
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

    /**
     * Method to update selected movie's showing status.
     * @param movie The selected movie.
     */
    protected void updateMovieShowingStatus(Movie movie){
        movie.setMovieShowingStatus();
        updateMovie();
    }

    /**
     * Displays options to update selected movie's time slots.
     * 3 options:
     * 1. Add movie time slots.
     * 2. Delete movie time slots.
     * 3. Go back.
     * @param movie The selected movie.
     */
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

    /**
     * Method to add new movie time slots to selected movie.
     * @param newMovieSlots The selected movie's time slots.
     * @param movie The selected movie.
     */
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
            cinema.setCineplex();
            System.out.println("Enter cinema number (1-3):");
            int cinemaNo = sc.nextInt();
            cinema.setCinemaNo(cinemaNo);
            cinema.setCinemaCode(cinema.getCineplex().toCode() + cinemaNo);
            MovieSlot movieslot = new MovieSlot(m, d, h, min, cinema, movie);
            movieslot.setMovieType();

            newMovieSlots.add(movieslot);
        }
        catch(InputMismatchException e){
            System.out.println("Invalid input!");
        }
    }

    /**
     * Method to delete movie time slots from selected movie.
     * @param newMovieSlots The selected movie's time slots.
     */
    protected void deleteMovieSlot(ArrayList<MovieSlot> newMovieSlots){
        int i = 1;
        printSep();
        for (MovieSlot e : newMovieSlots) {
            System.out.println(i++ + ") <" + e.getCinema().getCineplex() + ">: " + e.getaDate().getTime());
        }
        System.out.println("0) Back");
        printSep();
        System.out.println("Which movie slot would you like to delete?");
        int indexToDelete = sc.nextInt();
        if(indexToDelete == 0 || indexToDelete > newMovieSlots.size()) return;
        newMovieSlots.remove(indexToDelete-1);
    }

    /**
     * Displays options to update the selected movie's details.
     * 8 options:
     * 1. Update cast.
     * 2. Update director.
     * 3. Update synopsis.
     * 4. Update language.
     * 5. Update content rating.
     * 6. Update runtime.
     * 7. Update opening date.
     * 8. Go back.
     * @param movieDetails
     */
    protected void updateMovieDetails(MovieDetails movieDetails){
        boolean exit = false;

        while(!exit){
            printSep();
            System.out.println("1) Cast");
            System.out.println("2) Director");
            System.out.println("3) Synopsis");
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
                    movieDetails.setSynopsis(sc.nextLine());
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

    /**
     * Displays the top 5 movies either by rating and by ticket sales.
     */
    protected void viewTop5(){
        ArrayList<Movie> sortMovieList = movieList;
        sortMovieList.sort((o1, o2) -> Double.compare(o2.getMovieDetails().getRating(), o1.getMovieDetails().getRating()));
        printSep();
        DecimalFormat df = new DecimalFormat("#.#");

        System.out.println("Top 5 by ratings:");
        int i=1;
        for (Movie e: sortMovieList) {
            if(i == 5) break;

            if(e.getMovieDetails().getRating() == -1)
                System.out.println(i + ") " + e.getMovieTitle() + "<Rating: NA>");
            else
                System.out.println(i + ") " + e.getMovieTitle() + "<Rating: " + df.format(e.getMovieDetails().getRating()) + ">");
            i++;
        }

        System.out.println("\nTop 5 by ticket sales:");
        printSep();
        i=1;
        sortMovieList.sort((o1, o2) -> Integer.compare(o2.getTotalMovieSales(), o1.getTotalMovieSales()));
        for (Movie e: sortMovieList) {
            if (i == 5) break;

            System.out.println(i+") " + e.getMovieTitle() + ": Total ticket sales: " + e.getTotalMovieSales());
            i++;
        }

        printSep();

        adminOptions();
    }

    /**
     * Displays options for configuration of settings.
     * 6 options:
     * 1. Add public holiday.
     * 2. Delete public holiday.
     * 3. Change permissions for viewing top 5 list.
     * 4. Change ticket prices.
     * 5. Add new admin account.
     * 6. Go back.
     */
    protected void configSettings(){
        printSep();
        System.out.println("What would you like to do?");
        System.out.println("1) Add public holiday");
        System.out.println("2) Delete public holiday");
        System.out.println("3) Change top 5 movies option");
        System.out.println("4) Set ticket prices");
        System.out.println("5) Add new admin account");
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
            case 4:
                TicketDetails t = new TicketDetails();
                t.addTicketDetails(ticketList);
                t.updatePrice(ticketList);
                updateData(ticketList, "ticketlist.txt");
                ticketList = (ArrayList<TicketDetails>) readData("ticketlist.txt");
                break;
            case 5:
                System.out.println("Enter username:");
                String user = sc.next();
                System.out.println("Enter password:");
                String password = sc.next();
                MovieAdmin newAcc = new MovieAdmin(user, password);
                adminList.add(newAcc);
                updateData(adminList, "adminaccounts.txt");
                break;
        }
        adminOptions();
    }

    /**
     * Method to change if users are allowed to view top 5 movie list by
     * rating or by ticket sales.
     */
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

}
