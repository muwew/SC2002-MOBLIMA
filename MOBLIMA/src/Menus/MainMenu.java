package MOBLIMA.src.Menus;
import java.util.*;
import static MOBLIMA.src.Model.Util.*;

public class MainMenu extends Menu {
    public MainMenu(){};

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
