package MOBLIMA.src;
import java.util.*;

public class MainMenu extends Menu{
    public MainMenu(){};

    protected void printMenu(){
        Scanner sc = new Scanner(System.in);

        System.out.println("================================================");
        System.out.println("Welcome to MOBLIMA! What would you like to access?");
        System.out.println("1) Customer interface");
        System.out.println("2) Admin interface");
        System.out.println("0) Quit");
        System.out.println("================================================");
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                //user part
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
