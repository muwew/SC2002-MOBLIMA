package MOBLIMA.src.Menus;

import MOBLIMA.src.Accounts.MovieAdmin;
import MOBLIMA.src.Accounts.MovieGoer;
import MOBLIMA.src.Model.*;

import java.util.*;

import static MOBLIMA.src.DataManager.*;
import static MOBLIMA.src.Model.Util.*;

public class UserMenu extends Menu {

    private boolean loggedIn = false;
    private MovieGoer user;
    String userName = "Guest";
    Scanner sc = new Scanner(System.in);

    public UserMenu(){}

    protected void printMenu(){
        userOptions();
    }

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
            System.out.printf("Total price: %.2f",booking.getTotalPrice());
            printSep();
        }

        return;
    }

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
                printMovieList();
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

    protected void viewTop5(){
        printSep();
        System.out.println("Would you like to view the top 5 movies");
        System.out.println("1) by rating");
        System.out.println("2) by ticket sales");
        System.out.println("0) Back");
        printSep();

        MovieAdmin admin = adminList.get(0);

        int choice = sc.nextInt();
        ArrayList<Movie> sortMovieList = movieList;

        switch(choice){
            case 1:
                if(admin.isCanTop5ByRating()) {
                    printSep();
                    sortMovieList.sort((o1, o2) -> Double.compare(o2.getMovieDetails().getRating(), o1.getMovieDetails().getRating()));
                    for (int i = 0; i < 5; i++) {
                        Movie movie = sortMovieList.get(i);
                        System.out.println(i + 1 + ") " + movie.getMovieTitle() + "<Rating: " + movie.getMovieDetails().getRating() + ">");
                    }
                    System.out.println("0) Back");
                    printSep();

                    int index = sc.nextInt();
                    Movie movieToView;
                    if (index != 0 && index <= 5) {
                        movieToView = sortMovieList.get(index - 1);
                        viewMovie(movieToView);
                    }
                }
                else{
                    System.out.println("Cannot view by rating!");
                }
                break;
            case 2:
                HashMap<String, Integer> ticketCount = new HashMap<String, Integer>();
                if(admin.isCanTop5BySales()){

                    for(TicketDetails e: ticketList){
                        if(ticketCount.containsKey(e.getMovieTitle()))
                            ticketCount.put(e.getMovieTitle(), ticketCount.get(e.getMovieTitle()) + 1);
                    }

                    if(ticketCount.size() < 5){
                        System.out.println("Not enough data!");
                        viewMovieOptions();
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
                            printSep();
                            System.out.println(i + 1 + ") " + maxEntry.getKey() + "<Total ticket sales: " + maxEntry.getValue() + ">");
                            top5List.add(maxEntry.getKey());
                            ticketCount.remove(maxEntry.getKey());
                        }
                        System.out.println("0) Back");
                        printSep();
                        int index = sc.nextInt();
                        if (index != 0 && index <= 5) {
                            for (Movie e : movieList) {
                                if (e.getMovieTitle().equals(top5List.get(index-1)))
                                    movieToView = e;
                            }
                            viewMovie(movieToView);
                        }
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

    protected void movieSearch(){
        System.out.println("Enter movie title:");

        String movieTitle = sc.nextLine();

        for(Movie e: movieList) {
            if ((e.getMovieTitle().contains(movieTitle))) {
                viewMovie(e);
                return;
            }
        }

        System.out.println("Movie does not exist!");
    }
    protected void printMovieList(){
        printSep();
        System.out.println("Current movie listing: ");
        int i=1;
        for(Movie e: movieList){
            if(!e.getMovieShowingStatus().equals("END_SHOWING"))
                System.out.println(i++ +") "+e.getMovieTitle()+" <"+e.getMovieShowingStatus()+">");
        }
    }

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

    protected void reviewMenu(Movie movie){
        printSep();
        System.out.println("1) View reviews");
        System.out.println("2) Add review");
        System.out.println("0) Back");
        printSep();

        int choice = sc.nextInt();

        if(choice == 1){
            printSep();
            System.out.println("All reviews: ");

            for(Review e: movie.getMovieDetails().getReviews()){
                System.out.println(e.getUser() + ": Rating: " + String.format("%.2f", e.getRating()));
                System.out.println(e.getComment());
            }
            printSep();
        }
        else if(choice == 2){
            rateReviewMovie(movie);
            movieList = (ArrayList<Movie>) readData("movielist.txt");
        }
    }
    protected void rateReviewMovie(Movie movie){

        System.out.println("Enter your rating: ");
        Double rating = sc.nextDouble();
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

    protected void viewMovieSlots(ArrayList<MovieSlot> movieSlots){
        int i = 1;
        ArrayList<MovieSlot> JEMSlots = new ArrayList<>();
        ArrayList<MovieSlot> JPSlots = new ArrayList<>();
        ArrayList<MovieSlot> WMSlots = new ArrayList<>();
        for (MovieSlot e : movieSlots) {
            if (e.getCinema().getLocation().toString().equals("JEM")) {
                JEMSlots.add(e);
            } else if (e.getCinema().getLocation().toString().equals("JURONG_POINT")) {
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
        for(ArrayList<MovieSlot> e: allCineplexSlots){
            e.sort((o1, o2) -> o1.getaDate().compareTo(o2.getaDate()));

            if(e.size() > 0) {
                printSep();
                System.out.println("Time slots for <" + e.get(0).getCinema().getLocation() + ">:");

                for (MovieSlot e1 : e) {
                    if(e1.getaDate().get(Calendar.MINUTE) == 0){
                        System.out.print(j++ + ") " + e1.getaDate().get(Calendar.DAY_OF_MONTH) +"/" + (e1.getaDate().get(Calendar.MONTH)+1)
                                +"; "+ e1.getaDate().get(Calendar.HOUR_OF_DAY) +":"+ e1.getaDate().get(Calendar.MINUTE) +"0" + "\t");
                    }
                    else
                        System.out.print(j++ + ") " + e1.getaDate().get(Calendar.DAY_OF_MONTH) +"/" + (e1.getaDate().get(Calendar.MONTH)+1)
                                +"; "+ e1.getaDate().get(Calendar.HOUR_OF_DAY) +":"+ e1.getaDate().get(Calendar.MINUTE) + "\t");
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
            //send to booking part
            if(loggedIn) {
                Booking booking = new Booking(user, selectedMovieSlot);
                BookingMenu bookingMenu = new BookingMenu(booking, user, movieList, userList);
                open(this, bookingMenu);
            }
            else{
                Booking booking = new Booking(selectedMovieSlot);
                BookingMenu bookingMenu = new BookingMenu(booking, movieList, userList);
                open(this, bookingMenu);
            }

            //aft booking need to read updated files
            movieList = (ArrayList<Movie>) readData("movielist.txt");
            ticketList = (ArrayList<TicketDetails>) readData("ticketlist.txt");
            userList = (ArrayList<MovieGoer>) readData("useraccounts.txt");
        }
        else if(index == 0)
            return;
        else
            viewMovieSlots(movieSlots);

    }
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