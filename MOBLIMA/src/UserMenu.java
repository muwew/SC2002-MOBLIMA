package MOBLIMA.src;

import java.util.*;

import static MOBLIMA.src.DataManager.*;
import static MOBLIMA.src.Util.*;

public class UserMenu extends Menu{
    private ArrayList<Movie> movieList = (ArrayList<Movie>) readData("movielist.txt");
    private ArrayList<MovieGoer> userList = (ArrayList<MovieGoer>) readData("useraccounts.txt");
    private ArrayList<TicketDetails> ticketList = new ArrayList<>();
    private ArrayList<PublicHoliday> phList = new ArrayList<>();
    private boolean loggedIn = false;
    private MovieGoer user;
    String userName = "Guest";
    Scanner sc = new Scanner(System.in);

    public UserMenu(){}

    protected void printMenu(){
        userOptions();
    }

    protected void userOptions(){
        System.out.println("================================================");
        System.out.println("Welcome " + userName + "! What would you like to do today?");
        System.out.println("1) View movies");
        System.out.println("2) View booking history");
        System.out.println("3) Member login");
        System.out.println("0) Back");
        System.out.println("================================================");

        int choice = sc.nextInt();
        boolean exit = false;
        switch (choice) {
            case 1:
                viewMovieOptions();
                break;
            case 2:
                if(loggedIn) {
                    //send it to booking part
                }
                else{
                    System.out.println("You are not logged in!");
                }
                System.out.println("nid 2 implement ;-0");
                break;
            case 3:
                userLogin();
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

    protected void viewMovieOptions(){
        System.out.println("================================================");
        System.out.println("View movies:");
        System.out.println("1) View current movie listing");
        System.out.println("2) View Top 5 Movies");
        System.out.println("3) Movie search");
        System.out.println("0) Back");
        System.out.println("================================================");

        int choice = sc.nextInt();

        switch(choice){
            case 1:
                printMovieList();
                System.out.println("0) Back");
                System.out.println("================================================");
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
        System.out.println("nid 2 implement ;-0");
    }

    protected void movieSearch(){
        System.out.println("================================================");
        System.out.println("Enter movie title:");
        System.out.println("================================================");

        String movieTitle = sc.nextLine();

        for(Movie e: movieList) {
            if (movieTitle.equals(e.getMovieTitle())) {
                viewMovie(e);
                return;
            }
        }

        System.out.println("Movie does not exist!");
    }
    protected void printMovieList(){
        System.out.println("================================================");
        System.out.println("Current movie listing: ");
        int i=1;
        for(Movie e: movieList){
            if(!e.getMovieShowingStatus().equals("END_SHOWING"))
                System.out.println(i++ +") "+e.getMovieTitle()+" <"+e.getMovieShowingStatus()+">");
        }
    }

    protected void viewMovie(Movie movieToView){
        System.out.println("================================================");
        System.out.println("Movie selected: " + movieToView.getMovieTitle());
        System.out.println("1) View movie details");
        System.out.println("2) View movie time slots");
        System.out.println("3) Reviews");
        System.out.println("0) Back");
        System.out.println("================================================");
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
            default:
                viewMovieOptions();
        }
    }

    protected void viewMovieSlots(ArrayList<MovieSlot> movieSlots){
        int i = 1;
        ArrayList<MovieSlot> JEMSlots = new ArrayList<>();
        ArrayList<MovieSlot> JPSlots = new ArrayList<>();
        ArrayList<MovieSlot> WMSlots = new ArrayList<>();
        for (MovieSlot e : movieSlots) {
            if (e.getCinema().getLocation().equals("JEM")) {
                JEMSlots.add(e);
            } else if (e.getCinema().getLocation().equals("JURONG_POINT")) {
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
                System.out.println("================================================");
                System.out.println("Time slots for <" + e.get(0).getCinema().getLocation() + ">:");

                for (MovieSlot e1 : e) {
                    if(e1.getaDate().get(Calendar.MINUTE) == 0){
                        System.out.print(j++ + ") " + e1.getaDate().get(Calendar.DAY_OF_MONTH) +"/" + e1.getaDate().get(Calendar.MONTH)
                                +"; "+ e1.getaDate().get(Calendar.HOUR_OF_DAY) +":"+ e1.getaDate().get(Calendar.MINUTE) +"0" + "\t");
                    }
                    else
                        System.out.print(j++ + ") " + e1.getaDate().get(Calendar.DAY_OF_MONTH) +"/" + e1.getaDate().get(Calendar.MONTH)
                                +"; "+ e1.getaDate().get(Calendar.HOUR_OF_DAY) +":"+ e1.getaDate().get(Calendar.MINUTE) + "\t");
                }
                System.out.println();
            }
        }

        System.out.println("\n0) Back");
        System.out.println("================================================");
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
        }
        else if(index == 0)
            return;
        else
            viewMovieSlots(movieSlots);

    }
    protected void userLogin(){
        boolean exit = false;

        System.out.println("================================================");
        System.out.println("Welcome Guest! Please log into your account!");
        System.out.println("================================================");
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
