package MOBLIMA.src.Accounts;
import MOBLIMA.src.Model.Booking;

import java.io.Serializable;
import java.util.*;

public class MovieGoer extends Account implements Serializable {

    private static final long serialVersionUID = 7689870075709929042L;

    private String name;
    private Integer mobileNumber;
    private String emailAddress;
    private Integer age;
    //private AgeBracket ageBracket;
    private ArrayList<Booking> bookingHistory;


    public MovieGoer(String username, String name, Integer mobileNumber, String emailAddress, String password, ArrayList<Booking> bookingHistory, Integer age) {
        super(username, password);
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.bookingHistory = bookingHistory;
        this.age = age;
        //this.ageBracket = ageBracket;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Integer mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ArrayList<Booking> getBookingHistory() {
        return bookingHistory;
    }

    public void setBookingHistory(ArrayList<Booking> bookingHistory) {
        this.bookingHistory = bookingHistory;
    }

//    public AgeBracket getAgeBracket() {
//        return ageBracket;
//    }
//
//    public void setAgeBracket(AgeBracket ageBracket) {
//        this.ageBracket = ageBracket;
//    }

}