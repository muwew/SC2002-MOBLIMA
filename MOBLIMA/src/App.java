package MOBLIMA.src;
import java.util.*;

public class App {
    public static void main(String[] args) {
        //initialisations
        Scanner sc = new Scanner(System.in);
        FileIO fio = new FileIO();
        boolean adminPermissions = false;
        boolean loggedIn = false;
        ArrayList<Movie> movieList = fio.readMovieList("movielist.txt");
        ArrayList<Account> accountList = fio.readAccountList("accounts.txt");
        String userName = "Guest";

        System.out.println("================================================");
        System.out.println("Welcome, Guest! What would you like to do today?");
        System.out.println("1) Movies");
        System.out.println("2) Log in");
        System.out.println("3) Admin controls");
        System.out.println("4) Log out");
        System.out.println("0) Quit");
        System.out.println("================================================");

        int choice = sc.nextInt();
        while(choice != 0) {
            switch (choice) {
                case 1: //movies -> view all movies/ movie search / list top 5
                    System.out.println("================================================");
                    System.out.println("1) View current movie listing");
                    System.out.println("2) Top movies");
                    System.out.println("3) Movie search");
                    System.out.println("0) Back");
                    System.out.println("================================================");

                    Movie movieToView = null;
                    int choice1 = sc.nextInt();

                    while(choice1 != 0) {
                        switch (choice1)
                        {
                            case 1: {
                                int i = 1;
                                System.out.println("================================================");
                                for (Movie e : movieList) {
                                    System.out.println(i++ + ") " + e.getMovieTitle());
                                }
                                System.out.println("0) Back");
                                System.out.println("================================================");

                                int index = sc.nextInt();
                                if (index == 0) break;
                                movieToView = movieList.get(index-1);
                                break;
                            }
                            case 2:{
                                System.out.println("================================================");
                                System.out.println("Top 5 movies:");
                                System.out.println("================================================");
                                break;
                            }
                            case 3:{
                                sc.nextLine();
                                System.out.println("Enter movie name: ");
                                String title = sc.nextLine();

                                for(Movie movieSearch : movieList){
                                    if(movieSearch.getMovieTitle().equals(title))
                                        movieToView = movieSearch;
                                }

                                if (movieToView == null){
                                    System.out.println("Movie does not exist!");
                                }

                                break;
                            }
                        }

                        int choice2 = 1;
                        if(movieToView == null) choice2 = 0;

                        while(choice2 != 0){
                            System.out.println("================================================");
                            System.out.println("1) View movie timeslots");
                            System.out.println("2) View movie details");
                            System.out.println("0) Back");
                            System.out.println("================================================");
                            choice2 = sc.nextInt();

                            switch (choice2) {
                                case 1: //view movie timeslots
                                {
                                    ArrayList<MovieSlot> movieSlots = movieToView.getMovieSlots();
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

                                    if (JEMSlots.size() > 0) {
                                        System.out.println("Time slots <JEM>:");
                                        for (MovieSlot e : JEMSlots) {
                                            System.out.print(e.getaDate().getTime() + "\t");
                                        }
                                        System.out.println();
                                    }
                                    if (WMSlots.size() > 0) {
                                        System.out.println("Time slots <WEST_MALL>:");
                                        for (MovieSlot e : JEMSlots) {
                                            System.out.print(e.getaDate().getTime() + "\t");
                                        }
                                        System.out.println();
                                    }
                                    if (JPSlots.size() > 0) {
                                        System.out.println("Time slots <JURONG_POINT>:");
                                        for (MovieSlot e : JPSlots) {
                                            System.out.print(e.getaDate().getTime() + "\t");
                                        }
                                        System.out.println();
                                    }

                                    break;
                                }
                                case 2: //code to display moviedetails
                                {
                                    movieToView.getMovieDetails().printMovieDetails();
                                }
                                    break;
                                default:
                                    break;
                            }

//                            System.out.println("================================================");
//                            System.out.println("1) View movie timeslots");
//                            System.out.println("2) View movie details");
//                            System.out.println("0) Back");
//                            System.out.println("================================================");
//
//                            choice2 = sc.nextInt();
                        }

                        System.out.println("================================================");
                        System.out.println("1) View current movie listing");
                        System.out.println("2) Top movies");
                        System.out.println("3) Movie search");
                        System.out.println("0) Back");
                        System.out.println("================================================");

                        choice1 = sc.nextInt();
                    }
                    break;

                case 2://login
                    System.out.println("Enter username: ");
                    String username = sc.next();
                    boolean match = false;
                    for(Account e: accountList){
                        if(e.getUsername().equals(username)){
                            match = true;
                            System.out.println("Enter password: ");
                            String password = sc.next();
                            if(e.getPassword().equals(password)){
                                adminPermissions = true;
                                userName = e.getUsername();
                                loggedIn = true;
                                System.out.println("Succesfully logged in!");
                            }
                            else {
                                System.out.println("Wrong password!");
                                match = true;
                            }
                        }
                    }
                    if(!match) System.out.println("Account does not exist!");
                    break;

                case 3:
                    if(adminPermissions) {
                        MovieAdmin adminAcc = new MovieAdmin("admin", "password");
                        movieList = adminAcc.showMainMenu(movieList);
                    }
                    else{
                        System.out.println("You are not an admin!");
                    }
                    break;

                case 4:
                    if(!loggedIn){
                        System.out.println("You are not logged in yet!");
                        break;
                    }
                    else{
                        loggedIn = false;
                        adminPermissions = false;
                        userName = "Guest";
                        System.out.println("Succesfully logged out!");
                    }
            }

            System.out.println("================================================");
            System.out.println("Welcome, "+ userName + "! What would you like to do today?");
            System.out.println("1) Movies");
            System.out.println("2) Log in");
            System.out.println("3) Admin controls");
            System.out.println("4) Log out");
            System.out.println("0) Quit");
            System.out.println("================================================");

            choice = sc.nextInt();
        }

        System.out.println("Ending session...");
        fio.updateMovieList(movieList,"movielist.txt");

    }
}

