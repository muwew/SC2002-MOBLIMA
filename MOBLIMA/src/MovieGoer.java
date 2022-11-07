package MOBLIMA.src;
import java.io.Serializable;
import java.util.*;

public class MovieGoer extends Account implements Serializable {
    public int age;
    // public Booking[] bookingHistory;
    public AgeBracket ageBracket;
    public String mobileNumber;
    public String emailAddress;


    public MovieGoer(String username, String password) {
        super(username, password);
    }


    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAgeBracket() {
        return ageBracket.toString();
    }

    public void setAgeBracket(AgeBracket ageBracket) {
        this.ageBracket = ageBracket;
    }

    public void setAgeBracket(int age){
        Scanner sc = new Scanner(System.in);
        if (age < 12){
            this.ageBracket = AgeBracket.CHILD;
        }
        else{
            System.out.println("Are you a student?");
            System.out.println("1) YES");
            System.out.println("2) NO");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    this.ageBracket = AgeBracket.STUDENT;
                    break;
                case 2:
                    this.ageBracket = AgeBracket.ADULT;
                    break;
            }
        }
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }




}
