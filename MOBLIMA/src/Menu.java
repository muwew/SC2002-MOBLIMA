package MOBLIMA.src;
import java.util.*;
import static MOBLIMA.src.DataManager.*;

public abstract class Menu {
    public Menu prevMenu;

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
