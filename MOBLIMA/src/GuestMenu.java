//package MOBLIMA.src;
//import java.util.*;
//import static MOBLIMA.src.DataManager.*;
//
//public class GuestMenu extends Menu{
//    private ArrayList<Movie> movieList = (ArrayList<Movie>) readData("movielist.txt");;
//    private ArrayList<MovieGoer> movieGoerList = (ArrayList<MovieGoer>) readData("accounts.txt");
//    private boolean loggedIn = false;
//    Scanner sc = new Scanner(System.in);
//    public GuestMenu() {}
//
//    protected void printMenu() {
//        boolean exit = false;
//        System.out.println("Welcome Guest! What would you like to do today?");
//        System.out.println("================================================");
//        System.out.println("1) View Movies");
//        System.out.println("2) Member Login");
//        System.out.println("3) New Account Signup");
//        System.out.println("0) Quit");
//        System.out.println("================================================");
//
//        int choice = sc.nextInt();
//        switch (choice) {
//            case 1:
//                printMovieList();
//                System.out.println("\n");
//                open(this, this);
//                break;
//            case 2:
//                sc.nextLine();
//                guestLogin();
//                break;
//            case 3:
//                guestSignUp();
//                break;
//            case 0:
//                exit = true;
//                break;
//        }
//    }
//
//    protected void guestLogin(){
//        boolean exit = false;
//
//        System.out.println("Welcome Guest! Please log into your account!");
//        while (exit == false && loggedIn == false) {
//            System.out.println("Enter username: (type 0 to quit)");
//            String username = sc.next();
//            if (username.equals("0")) exit = true;
//
//            boolean match = false;
//            for (Account e : movieGoerList) {
//                if (e.getUsername().equals(username)) {
//                    match = true;
//                    System.out.println("Enter password: ");
//                    String password = sc.next();
//                    if (e.getPassword().equals(password)) {
//                        loggedIn = true;
//                        System.out.println("Succesfully logged in!");
//                    } else {
//                        System.out.println("Wrong password!");
//                        match = true;
//                    }
//                }
//            }
//            if (exit) ;
//            else if (!match) System.out.println("Account does not exist!");
//
//        }
//
//        if (exit) open(this, this.prevMenu);
//        else if (loggedIn) {
//            printGuestOptions();
//        }
//    }
//
//    protected void guestSignUp(){
//        // Gather customer information
//        System.out.println("Enter new username: ");
//        String username = sc.next();
//        System.out.println("Enter new password: ");
//        String password = sc.next();
//        System.out.println("Enter age: ");
//        int age = sc.nextInt();
//        System.out.println("Enter email address: ");
//        String emailAddress = sc.next();
//        System.out.println("Enter mobile number: ");
//        String mobileNumber = sc.next();
//
//
//        // Create new MovieGoer instance
//        MovieGoer guest = new MovieGoer(username, password);
//
//        guest.setAge(age);
//        guest.setAgeBracket(age);
//        guest.setEmailAddress(emailAddress);
//        guest.setMobileNumber(mobileNumber);
//
//        // Update accounts.txt
//        movieGoerList = (ArrayList<MovieGoer>) readData("accounts.txt");
//        movieGoerList.add(guest);
//        updateData(movieGoerList, "accounts.txt");
//
//        open(this, this);
//    }
//
//    protected void printGuestOptions(){
//        System.out.println("================================================");
//        System.out.println("1) View movies");
//        System.out.println("2) View Top 5 movies");
//        System.out.println("3) Search for a movie");
//        System.out.println("4) Rate and review a movie");
//        System.out.println("0) Log out");
//        System.out.println("================================================");
//
//        int choice = sc.nextInt();
//        boolean exit = false;
//        switch (choice) {
//            case 1:
//                sc.nextLine();
//                printMovieList();
//                System.out.println("================================================");
//                System.out.println("Do you wish to view a movie's details?");
//                System.out.println("1) YES");
//                System.out.println("2) NO");
//
//                boolean wantToView = (sc.nextInt() == 1);
//
//                while(wantToView){
//                    String selectedMovie = selectMovie();
//
//                    // if 0 is entered, exit
//                    if(selectedMovie.equals("0")){
//                        break;
//                    }
//
//                    Movie foundMovie = searchMovie(selectedMovie);
//                    // if movie not found, prompt if want continue or not
//                    if(foundMovie == null){
//                        System.out.println("Would you like to continue searching?");
//                        System.out.println("1) YES");
//                        System.out.println("2) NO");
//                        wantToView = (sc.nextInt() == 1);
//                    }
//                    else{
//                        foundMovie.printMovieDetails();
//                        System.out.println("================================================");
//                    }
//                }
//                break;
//            case 2:
//                //printTop5MovieList();
//                break;
//            case 3:
//                String searchedMovie = selectMovie();
//                Movie foundMovie = searchMovie(searchedMovie);
//                if(foundMovie == null){
//                    System.out.println("Would you like to continue searching?");
//                    System.out.println("1) YES");
//                    System.out.println("2) NO");
//                    wantToView = (sc.nextInt() == 1);
//                }
//                else{
//                    System.out.println("Movie found! Here are its details: ");
//                    foundMovie.printMovieDetails();
//                }
//                break;
//            case 4:
//                String selectedMovie = selectMovie();
//
//                // while "0" is not entered
//                while(!selectedMovie.equals("0")){
//                    Movie foundMovie2 = searchMovie(selectedMovie);
//                    if(foundMovie2 == null){
//                        System.out.println("Please reenter movie title or enter 0 to exit: ");
//                        selectedMovie = sc.next();
//                        continue;
//                    }
//                    rateReviewMovie(foundMovie2);
//                    break;
//                }
//                break;
//            case 0:
//                exit = true;
//                break;
//        }
//
//        if(exit == true){
//            updateData(movieList, "movielist.txt");
//            System.out.println("Logging out...");
//            open(this, this.prevMenu);
//        }
//        else printGuestOptions();
//    }
//
//    protected String selectMovie(){
//        System.out.println("Please enter the movie's title or enter 0 to exit: ");
//        return sc.next();
//    }
//
//    protected Movie searchMovie(String movieTitle){
//        for(Movie e: movieList){
//            // if found movie
//            if(movieTitle.equalsIgnoreCase(e.getMovieTitle())){
//                return e;
//            }
//        }
//        System.out.println("Movie not found!");
//        return null;
//    }
//
//    protected void rateReviewMovie(Movie movie){
//        System.out.println("Enter your rating: ");
//        Double rating = sc.nextDouble();
//        System.out.println("Enter your review: ");
//        sc.nextLine();
//        String review = sc.nextLine();
//
//        movie.addRating(rating);
//        movie.addReview(review);
//
//        System.out.println("Thank you for your input!");
//    }
//}
