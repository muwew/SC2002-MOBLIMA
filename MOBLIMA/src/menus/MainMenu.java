package MOBLIMA.src.menus;
import java.util.*;
import static MOBLIMA.src.model.Util.*;

/**
 * Initial interface to choose between admin and user menu.
 */
public class MainMenu extends Menu {
    public MainMenu(){};
    /**
     * Method called when menu first opened.
     */
    public void printMenu(){
        Scanner sc = new Scanner(System.in);

        printSep();
        System.out.println("Welcome to MOBLIMA! What would you like to access?");
        System.out.println("1) Customer interface");
        System.out.println("2) Admin interface");
        System.out.println("0) Quit");
        printSep();
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                //user part
                open(this, new UserMenu());
                break;
            case 2:
                //admin part
                open(this, new AdminMenu());
                break;
            default:
                System.exit(0);
        }
    }
}
