package MOBLIMA.src.menus;
import MOBLIMA.src.model.MovieAdmin;
import MOBLIMA.src.model.MovieGoer;
import MOBLIMA.src.model.Movie;
import MOBLIMA.src.model.PublicHoliday;
import MOBLIMA.src.model.TicketDetails;

import java.util.*;
import static MOBLIMA.src.DataManager.*;
import static MOBLIMA.src.model.Util.*;

/**
 * Base class for all the different menu interfaces.
 * Stores the data needed to be shared among menus.
 */
public abstract class Menu {
    /**
     * Link to the previous menu.
     */
    public Menu prevMenu;

    /**
     * The movies that have been added into the system.
     */
    protected ArrayList<Movie> movieList = (ArrayList<Movie>) readData("movielist.txt");
    /**
     * The accounts of the users that have been added into the system.
     */
    protected ArrayList<MovieGoer> userList = (ArrayList<MovieGoer>) readData("useraccounts.txt");
    /**
     * The details of tickets for movies that have been purchased.
     */
    protected ArrayList<TicketDetails> ticketList = (ArrayList<TicketDetails>) readData("ticketlist.txt");
    /**
     * The public holidays that have been added by the Admin.
     */
    protected ArrayList<PublicHoliday> phList = (ArrayList<PublicHoliday>) readData("phlist.txt");
    /**
     * The accounts of the admins that have been added into the system.
     */
    protected ArrayList<MovieAdmin> adminList = (ArrayList<MovieAdmin>) readData("adminaccounts.txt");

    /**
     * Method called when menus are first opened.
     */
    protected abstract void printMenu();

    /**
     * Method to open the next menu.
     * @param currentMenu The current menu.
     * @param nextMenu The next menu to be opened.
     */
    protected void open(Menu currentMenu, Menu nextMenu){
        nextMenu.prevMenu = currentMenu;
        nextMenu.printMenu();
    }

    /**
     * Gets the previous menu.
     * @return previous menu.
     */
    public Menu getPrevMenu(){
        return prevMenu;
    }

    /**
     * Prints all movies that are currently in list.
     * If not admin, print all movies that are not END_OF_SHOWING.
     * If admin, print all movies.
     * @param admin To determine if Admin.
     */
    public void printMovieList(boolean admin) {
        movieList = (ArrayList<Movie>) readData("movielist.txt");
        printSep();
        System.out.println("Current movie listing: ");
        int i=1;
        for(Movie e: movieList){
            if(!admin){
                if (!e.getMovieShowingStatus().equals("END_SHOWING"))
                    System.out.println(i++ + ") " + e.getMovieTitle() + " <" + e.getMovieShowingStatus() + ">");
            }
            else
                System.out.println(i++ + ") " + e.getMovieTitle() + " <" + e.getMovieShowingStatus() + ">");
        }
    }

}
