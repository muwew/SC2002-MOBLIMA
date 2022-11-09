package MOBLIMA.src.Menus;
import MOBLIMA.src.Accounts.MovieAdmin;
import MOBLIMA.src.Accounts.MovieGoer;
import MOBLIMA.src.Model.Movie;
import MOBLIMA.src.Model.PublicHoliday;
import MOBLIMA.src.Model.TicketDetails;

import java.util.*;
import static MOBLIMA.src.DataManager.*;

public abstract class Menu {
    public Menu prevMenu;

    protected ArrayList<Movie> movieList = (ArrayList<Movie>) readData("movielist.txt");
    protected ArrayList<MovieGoer> userList = (ArrayList<MovieGoer>) readData("useraccounts.txt");
    protected ArrayList<TicketDetails> ticketList = (ArrayList<TicketDetails>) readData("ticketlist.txt");
    protected ArrayList<PublicHoliday> phList = (ArrayList<PublicHoliday>) readData("phlist.txt");
    protected ArrayList<MovieAdmin> adminList = (ArrayList<MovieAdmin>) readData("adminaccounts.txt");

    protected abstract void printMenu();

    protected void close(){
        if(prevMenu == null)
            return; //quit
        else{
            prevMenu.printMenu();
        }
    }

    protected void open(Menu currentMenu, Menu nextMenu){
        nextMenu.prevMenu = currentMenu;
        nextMenu.printMenu();
    }

    public Menu getPrevMenu(){
        return prevMenu;
    }

    /*public void printMovieList() {
        final ArrayList<Movie> movieList = (ArrayList<Movie>) readData("movielist.txt");;

        System.out.println("================================================");
        System.out.println("Current movie listing: ");
        int i=1;
        for(Movie e: movieList){
            System.out.println(i++ +") "+e.getMovieTitle()+" <"+e.getMovieShowingStatus()+">");
        }
    }*/
}
