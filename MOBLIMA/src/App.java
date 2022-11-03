package MOBLIMA.src;
import java.util.*;

public class App {
    public static void main(String[] args) {
        //initialisations
        Scanner sc = new Scanner(System.in);
        FileIO fio = new FileIO();
        boolean adminPermissions = false;
        ArrayList<Movie> movieList = fio.readMovieList("movielist.txt");
        ArrayList<Account> accountList = fio.readAccountList("accounts.txt");

        Account userAccount;
        String userName = "Guest";

        System.out.println("================================================");
        System.out.println("Welcome, Guest! What would you like to do today?");
        System.out.println("1) Movies");
        System.out.println("2) Cinemas");
        System.out.println("3) Log in");
        System.out.println("4) Admin controls");
        System.out.println("5) Quit");
        System.out.println("================================================");

        int choice = sc.nextInt();
        while(choice != 0) {
            switch (choice) {
                case 1: //movies
                    int i = 1;
                    System.out.println("================================================");
                    for (Movie e : movieList) {
                        System.out.println(i++ + ") " + e.getMovieTitle());
                    }
                    System.out.println("0) Back");
                    System.out.println("================================================");

                    int index = sc.nextInt();
                    if(index == 0) break;

                    Movie movieToView = movieList.get(index-1);
                    System.out.println("================================================");
                    System.out.println("1) View movie timeslots");
                    System.out.println("2) View movie details");
                    System.out.println("0) Back");
                    System.out.println("================================================");
                    int choice1 = sc.nextInt();

                    while(choice1 != 0){
                        switch(choice1){
                            case 1:
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
                                    for (MovieSlot e: JEMSlots) {
                                        System.out.print(e.getaDate().getTime() + "\t");
                                    }
                                    System.out.println();
                                }
                                if (WMSlots.size() > 0) {
                                    System.out.println("Time slots <WEST_MALL>:");
                                    for (MovieSlot e: JEMSlots) {
                                        System.out.print(e.getaDate().getTime() + "\t");
                                    }
                                    System.out.println();
                                }
                                if (JPSlots.size() > 0) {
                                    System.out.println("Time slots <JURONG_POINT>:");
                                    for (MovieSlot e: JPSlots) {
                                        System.out.print(e.getaDate().getTime() + "\t");
                                    }
                                    System.out.println();
                                }

                                break;
                            case 2:
                                //code to display moviedetails
                                break;
                        }
                        System.out.println("================================================");
                        System.out.println("1) View movie timeslots");
                        System.out.println("2) View movie details");
                        System.out.println("0) Back");
                        System.out.println("================================================");
                        choice1 = sc.nextInt();
                    }
                    break;

                case 2: //cinemas
                    i = 1;
                    /*for (String e : cinemaList) {
                        System.out.println(i++ + ") " + e);
                    }*/
                    System.out.println("need to implement");
                    break;

                case 3: //login
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
                                userAccount = e;
                                userName = e.getUsername();
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

                case 4:
                    if(adminPermissions) {
                        MovieAdmin adminAcc = new MovieAdmin("admin", "password");
                        movieList = adminAcc.showMainMenu(movieList);
                    }
                    else{
                        System.out.println("You are not an admin!");
                    }
            }

            System.out.println("================================================");
            System.out.println("Welcome, "+ userName + "! What would you like to do today?");
            System.out.println("1) Movies");
            System.out.println("2) Cinemas");
            System.out.println("3) Log in");
            System.out.println("4) Admin controls");
            System.out.println("0) Quit");
            System.out.println("================================================");

            choice = sc.nextInt();
        }

        System.out.println("Ending session...");
        fio.updateMovieList(movieList,"movielist.txt");

    }
}

