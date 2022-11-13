package MOBLIMA.src.menus;

import MOBLIMA.src.model.MovieAdmin;
import MOBLIMA.src.model.MovieGoer;
import MOBLIMA.src.enums.CinemaClass;
import MOBLIMA.src.model.*;

import java.text.DecimalFormat;
import java.util.*;

import static MOBLIMA.src.DataManager.*;
import static MOBLIMA.src.model.Util.*;

/**
 * Main menu for users
 */
public class UserMenu extends Menu {

    /**
     * If user has logged in.
     */
    private boolean loggedIn = false;

    /**
     * Account of the user when they log in.
     */
    private MovieGoer user;

    /**
     * Username of the user's account.
     * "Guest" if the user has not logged in.
     */
    String userName = "Guest";

    Scanner sc = new Scanner(System.in);

    /**
     * Constructor
     */
    public UserMenu(){}
    /**
     * Method called when menu first opened.
     */
    protected void printMenu(){
        userOptions();
    }

    /**
     * Displays the main MovieGoer menu.
     * 5 options:
     * 1. View all movies.
     * 2. View booking history (only if user is logged in).
     * 3. Log in.
     * 4. Sign up.
     * 5. Go back.
     */
    protected void userOptions(){
        printSep();
        System.out.println("Welcome " + userName + "! What would you like to do today?");
        System.out.println("1) View movies");
        System.out.println("2) View booking history");
        System.out.println("3) Member login");
        System.out.println("4) New Account Signup");
        System.out.println("0) Back");
        printSep();

        int choice = sc.nextInt();
        boolean exit = false;
        switch (choice) {
            case 1:
                viewMovieOptions();
                break;
            case 2:
                if(loggedIn) {
                    //send it to booking part
                    viewBookingHistory();
                }
                else{
                    System.out.println("You are not logged in!");
                }
                break;
            case 3:
                userLogin();
                break;
            case 4:
                userSignUp();
                userList = (ArrayList<MovieGoer>) readData("useraccounts.txt");
                break;
            case 0:
                exit = true;
                loggedIn = false;
                break;
        }

        if(exit == true){
            //updateData(userList, "useraccounts.txt");
            open(this, this.prevMenu);
        }
        else userOptions();
    }

    /**
     * Method to allow users to sign up.
     * Adds the MovieGoer account to the system.
     */
    protected void userSignUp(){
        System.out.println("Enter new username: ");
        String username = sc.next();
        System.out.println("Enter new password: ");
        String password = sc.next();
        System.out.println("Enter age: ");
        int age = sc.nextInt();
        System.out.println("Enter email address: ");
        String emailAddress = sc.next();
        System.out.println("Enter mobile number: ");
        int mobileNumber = sc.nextInt();

        // Create new MovieGoer instance
        MovieGoer guest = new MovieGoer(username, username, mobileNumber ,emailAddress, password, new ArrayList<>(), age);

        // Update accounts.txt
        ArrayList<MovieGoer> movieGoerList = (ArrayList<MovieGoer>) readData("useraccounts.txt");
        movieGoerList.add(guest);
        updateData(movieGoerList, "useraccounts.txt");
        System.out.println("Successfully signed up!");
    }

    /**
     * Method for members to view their booking history.
     * Displays the transaction ID of their bookings and allows them to
     * view more details when selected.
     */
    protected void viewBookingHistory(){
        int i = 1;
        printSep();
        System.out.println(user.getUsername() + "'s booking history:");
        for(Booking e: user.getBookingHistory()){
            System.out.println(i++ + ") Transaction ID: " + e.getTransactionID());
        }
        System.out.println("0) Back");
        printSep();
        System.out.println("Which transaction would you like to view:");

        int choice = sc.nextInt();

        if(choice != 0 && choice <= user.getBookingHistory().size()){
            printSep();
            Booking booking = user.getBookingHistory().get(choice-1);
            System.out.println("Transaction ID: " + booking.getTransactionID());
            System.out.println("Movie Title: " + booking.getMovieSlotBooked().getMovie().getMovieTitle());
            if(booking.getTimeslot().get(Calendar.MINUTE) == 0)
                System.out.println("Date of movie: " + booking.getTimeslot().get(Calendar.DAY_OF_MONTH) +"/" + booking.getTimeslot().get(Calendar.MONTH)
                    +"; "+ booking.getTimeslot().get(Calendar.HOUR_OF_DAY) +":"+ booking.getTimeslot().get(Calendar.MINUTE) + "0");
            else
                System.out.println("Date of movie: " + booking.getTimeslot().get(Calendar.DAY_OF_MONTH) +"/" + booking.getTimeslot().get(Calendar.MONTH)
                                +"; "+ booking.getTimeslot().get(Calendar.HOUR_OF_DAY) +":"+ booking.getTimeslot().get(Calendar.MINUTE));
            System.out.println("Number of seats booked: "+ booking.getNumberOfSeatsBooked());
            System.out.printf("Total price: %.2f\n",booking.getTotalPrice());
        }

        return;
    }

    /**
     * Displays the options to view movies and allows users to select movies.
     * 4 options:
     * 1. View all movies in current listing.
     * 2. View top 5 movies.
     * 3. Search for movies.
     * 4. Go back.
     */
    protected void viewMovieOptions(){
        printSep();
        System.out.println("View movies:");
        System.out.println("1) View current movie listing");
        System.out.println("2) View Top 5 Movies");
        System.out.println("3) Movie search");
        System.out.println("0) Back");
        printSep();

        int choice = sc.nextInt();

        switch(choice){
            case 1:
                printMovieList(false);
                System.out.println("0) Back");
                printSep();
                System.out.println("What movie would you like to select?");
                int index = sc.nextInt();
                if(index == 0) viewMovieOptions();

                if(index != 0 && index <= movieList.size())
                    viewMovie(movieList.get(index-1));
                break;
            case 2:
                viewTop5();
                break;
            case 3:
                sc.nextLine();
                movieSearch();
                break;
            default:
                userOptions();
                break;
        }
    }

    /**
     * Displays the top 5 movies either by rating or by ticket sales.
     * Ability to display by rating or by ticket sales determined by
     * admin.
     * Allows users to select movies.
     */
    protected void viewTop5(){
        printSep();
        System.out.println("Would you like to view the top 5 movies");
        System.out.println("1) by rating");
        System.out.println("2) by ticket sales");
        System.out.println("0) Back");
        printSep();

        MovieAdmin admin = adminList.get(0);
        DecimalFormat df = new DecimalFormat("#.#");
        int choice = sc.nextInt();
        ArrayList<Movie> sortMovieList = movieList;

        switch(choice){
            case 1:
                if(admin.isCanTop5ByRating()) {
                    printSep();
                    int i=1;
                    sortMovieList.sort((o1, o2) -> Double.compare(o2.getMovieDetails().getRating(), o1.getMovieDetails().getRating()));
                    for (Movie e: sortMovieList) {
                        if(i == 5) break;

                        if(e.getMovieDetails().getRating() == -1)
                            System.out.println(i + ") " + e.getMovieTitle() + "<Rating: NA>");
                        else
                            System.out.println(i + ") " + e.getMovieTitle() + "<Rating: " + df.format(e.getMovieDetails().getRating()) + ">");
                        i++;
                    }
                    System.out.println("0) Back");
                    printSep();

                    int index = sc.nextInt();
                    Movie movieToView;
                    if (index != 0 && index <= 5) {
                        movieToView = sortMovieList.get(index - 1);
                        viewMovie(movieToView);
                    }
                    else if(index == 0);
                    else{
                        System.out.println("Invalid choice!");
                    }
                }
                else{
                    System.out.println("Cannot view by rating!");
                }
                break;
            case 2:
                if(admin.isCanTop5BySales()){
                    printSep();
                    int i=1;
                    sortMovieList.sort((o1, o2) -> Integer.compare(o2.getTotalMovieSales(), o1.getTotalMovieSales()));
                    for (Movie e: sortMovieList) {
                        if (i == 5) break;

                        System.out.println(i+") " + e.getMovieTitle() + ": Total ticket sales: " + e.getTotalMovieSales());
                        i++;
                    }
                }
                else {
                    System.out.println("Cannot view by ticket sales!");
                }
                break;
            default:
                viewMovieOptions();
        }

        viewMovieOptions();
    }

    /**
     * Allows user to search for movie by the movie's title and opens the
     * options if found.
     */
    protected void movieSearch(){
        System.out.println("Enter movie title:");

        String movieTitle = sc.nextLine();

        for(Movie e: movieList) {
            if ((e.getMovieTitle().toLowerCase().contains(movieTitle.toLowerCase()))) {
                viewMovie(e);
                return;
            }
        }

        System.out.println("Movie does not exist!");
    }

    /**
     * Displays the options after movie is selected.
     * 4 options:
     * 1. View movie details.
     * 2. View movie time slots.
     * 3. Open review menu.
     * 4. Go back.
     * @param movieToView The selected movie.
     */
    protected void viewMovie(Movie movieToView){
        printSep();
        System.out.println("Movie selected: " + movieToView.getMovieTitle());
        System.out.println("1) View movie details");
        System.out.println("2) View movie time slots");
        System.out.println("3) Reviews");
        System.out.println("0) Back");
        printSep();
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                movieToView.getMovieDetails().printMovieDetails();
                break;
            case 2:
                viewMovieSlots(movieToView.getMovieSlots());
                break;
            case 3:
                //send it into reviewmenu
                reviewMenu(movieToView);
                break;
            default:
                viewMovieOptions();
        }
    }

    /**
     * Displays the review menu for the selected movie.
     * 3 options:
     * 1. View reviews.
     * 2. Add reviews.
     * 3. Go back.
     * @param movie The selected movie.
     */
    protected void reviewMenu(Movie movie){
        printSep();
        System.out.println("1) View reviews");
        System.out.println("2) Add review");
        System.out.println("0) Back");
        printSep();

        int choice = sc.nextInt();

        if(choice == 1){
            if(movie.getMovieDetails().getReviews().size() > 0) {
                printSep();
                System.out.println("All reviews: ");

                for (Review e : movie.getMovieDetails().getReviews()) {
                    System.out.println(e.getUser() + ": Rating: " + String.format("%.1f", e.getRating()));
                    System.out.println(e.getComment());
                }
                printSep();
            }
            else{
                System.out.println("There are currently no reviews for this movie!");
            }
        }
        else if(choice == 2){
            rateReviewMovie(movie);
            movieList = (ArrayList<Movie>) readData("movielist.txt");
        }
    }

    /**
     * Adds a review to the selected movie.
     * @param movie The selected movie.
     */
    protected void rateReviewMovie(Movie movie){

        System.out.println("Enter your rating: ");
        Double rating = sc.nextDouble();
        while(rating < 0 || rating > 5){
            System.out.println("Invalid input! Enter a rating from 0 - 5!");
            rating = sc.nextDouble();
        }

        System.out.println("Enter your review: ");
        sc.nextLine();
        String comment = sc.nextLine();

        Review review = new Review(userName ,rating, comment);

        ArrayList<Review> reviewList = movie.getMovieDetails().getReviews();
        reviewList.add(review);
        movie.getMovieDetails().setReviews(reviewList);

        movie.getMovieDetails().updateRating();
        System.out.println("Thank you for your input!");
        updateData(movieList, "movielist.txt");
    }

    /**
     * Displays the time slots for the selected movie, along with the location,
     * cinema type and movie type.
     * Upon selection of time slot, will go to booking menu.
     * @param movieSlots The list of MovieSlots for selected movie.
     */
    protected void viewMovieSlots(ArrayList<MovieSlot> movieSlots){
        int i = 1;
        ArrayList<MovieSlot> JEMSlots = new ArrayList<>();
        ArrayList<MovieSlot> JPSlots = new ArrayList<>();
        ArrayList<MovieSlot> WMSlots = new ArrayList<>();
        for (MovieSlot e : movieSlots) {
            if (e.getCinema().getCineplex().toString().equals("JEM")) {
                JEMSlots.add(e);
            } else if (e.getCinema().getCineplex().toString().equals("JURONG_POINT")) {
                JPSlots.add(e);
            } else {
                WMSlots.add(e);
            }
        }

        ArrayList<ArrayList<MovieSlot>> allCineplexSlots = new ArrayList<>();
        allCineplexSlots.add(JEMSlots);
        allCineplexSlots.add(JPSlots);
        allCineplexSlots.add(WMSlots);

        int j=1;
        if(allCineplexSlots.size() < 1){
            System.out.println("There are currently no show times!");
            return;
        }
        for(ArrayList<MovieSlot> locationSlots: allCineplexSlots){
            locationSlots.sort((o1, o2) -> o1.getaDate().compareTo(o2.getaDate()));

            if(locationSlots.size() > 0) {
                printSep();
                System.out.println("Time slots for <" + locationSlots.get(0).getCinema().getCineplex() + ">:");

                ArrayList<MovieSlot> platSlots = new ArrayList<>();
                ArrayList<MovieSlot> normSlots = new ArrayList<>();
                for (MovieSlot e1 : locationSlots) {
                    if(e1.getCinema().getCinemaClass() == CinemaClass.PLATINUM){
                        platSlots.add(e1);
                    }
                    else
                        normSlots.add(e1);
                }
                System.out.print("PLATINUM |\t");
                for(MovieSlot e1: platSlots){
                    if(e1.getaDate().get(Calendar.MINUTE) == 0){
                        System.out.print(j++ + ") [" + e1.getaDate().get(Calendar.DAY_OF_MONTH) +"/" + (e1.getaDate().get(Calendar.MONTH)+1)
                                +"]: "+ e1.getaDate().get(Calendar.HOUR_OF_DAY) +":"+ e1.getaDate().get(Calendar.MINUTE) +"0"
                                + " ("+ e1.getMovieType()+");" + "\t");
                    }
                    else
                        System.out.print(j++ + ") [" + e1.getaDate().get(Calendar.DAY_OF_MONTH) +"/" + (e1.getaDate().get(Calendar.MONTH)+1)
                                +"]: "+ e1.getaDate().get(Calendar.HOUR_OF_DAY) +":"+ e1.getaDate().get(Calendar.MINUTE)
                                + " ("+ e1.getMovieType()+");" + "\t");
                }
                System.out.println();
                System.out.print("NORMAL   |\t");
                for(MovieSlot e1: normSlots){
                    if(e1.getaDate().get(Calendar.MINUTE) == 0){
                        System.out.print(j++ + ") " + e1.getaDate().get(Calendar.DAY_OF_MONTH) +"/" + (e1.getaDate().get(Calendar.MONTH)+1)
                                +"; "+ e1.getaDate().get(Calendar.HOUR_OF_DAY) +":"+ e1.getaDate().get(Calendar.MINUTE) +"0"
                                + " ("+ e1.getMovieType()+")" + "\t");
                    }
                    else
                        System.out.print(j++ + ") " + e1.getaDate().get(Calendar.DAY_OF_MONTH) +"/" + (e1.getaDate().get(Calendar.MONTH)+1)
                                +"; "+ e1.getaDate().get(Calendar.HOUR_OF_DAY) +":"+ e1.getaDate().get(Calendar.MINUTE)
                                + " ("+ e1.getMovieType()+")" + "\t");
                }
                System.out.println();
            }
        }

        System.out.println("\n0) Back");
        printSep();
        System.out.println("Which time slot would you like to select?");
        ArrayList<MovieSlot> allSlotsList = new ArrayList<>();
        allSlotsList.addAll(JEMSlots);
        allSlotsList.addAll(JPSlots);
        allSlotsList.addAll(WMSlots);

        int index = sc.nextInt();
        if(index != 0 && index <= allSlotsList.size()){
            MovieSlot selectedMovieSlot = allSlotsList.get(index-1);
            if(!selectedMovieSlot.getMovie().getMovieShowingStatus().equals("COMING_SOON")) {
                //send to booking part
                if (loggedIn) {
                    Booking booking = new Booking(user, selectedMovieSlot);
                    BookingMenu bookingMenu = new BookingMenu(booking, user, movieList, userList);
                    open(this, bookingMenu);
                } else {
                    Booking booking = new Booking(selectedMovieSlot);
                    BookingMenu bookingMenu = new BookingMenu(booking, movieList, userList);
                    open(this, bookingMenu);
                }

                //aft booking need to read updated files
                movieList = (ArrayList<Movie>) readData("movielist.txt");
                ticketList = (ArrayList<TicketDetails>) readData("ticketlist.txt");
                userList = (ArrayList<MovieGoer>) readData("useraccounts.txt");
            }
            else{
                System.out.println("Invalid option! Cannot book movie yet!");
            }
        }
        else if(index == 0)
            return;
        else
            viewMovieSlots(movieSlots);

    }

    /**
     * User log in.
     */
    protected void userLogin(){
        boolean exit = false;
        if(loggedIn == false) {
            printSep();
            System.out.println("Welcome Guest! Please log into your account!");
            printSep();
        }
        else{
            System.out.println("You are already logged in!");
        }
        while (exit == false && loggedIn == false) {
            System.out.println("Enter username: (type 0 to quit)");
            String username = sc.next();
            if (username.equals("0")) exit = true;

            boolean match = false;
            for (MovieGoer e : userList) {
                if (e.getUsername().equals(username)) {
                    match = true;
                    System.out.println("Enter password: ");
                    String password = sc.next();
                    if (e.getPassword().equals(password)) {
                        loggedIn = true;
                        user = e;
                        userName = user.getUsername();
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

        userOptions();
    }

}