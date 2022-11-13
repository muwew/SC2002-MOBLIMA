package MOBLIMA.src.model;
import MOBLIMA.src.model.Account;
import MOBLIMA.src.model.Booking;

import java.io.Serializable;
import java.util.*;

/**
 * Account for movie goers using system.
 */
public class MovieGoer extends Account implements Serializable {

    /**
     * Name of user.
     */
    private String name;
    /**
     * Mobile number of user.
     */
    private Integer mobileNumber;
    /**
     * Email address of user.
     */
    private String emailAddress;
    /**
     * Age of user.
     */
    private Integer age;
    /**
     * Booking history of user.
     */
    private ArrayList<Booking> bookingHistory;

    /**
     * Constructor.
     * @param username Username used to log in to account.
     * @param name Name of user.
     * @param mobileNumber Mobile number of user.
     * @param emailAddress Email address of user.
     * @param password Password used to log in to account.
     * @param bookingHistory Booking history of user.
     * @param age Age of user.
     */
    public MovieGoer(String username, String name, Integer mobileNumber, String emailAddress, String password, ArrayList<Booking> bookingHistory, Integer age) {
        super(username, password);
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.bookingHistory = bookingHistory;
        this.age = age;
    }


    /**
     * Gets user's name.
     * @return User's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets user's name.
     * @param name User's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets user's mobile number.
     * @return User's mobile number.
     */
    public Integer getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Sets user's mobile number.
     * @param mobileNumber User's mobile number.
     */
    public void setMobileNumber(Integer mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * Gets user's email.
     * @return User's email.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets user's email.
     * @param emailAddress User's email.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Gets user's age.
     * @return User's age.
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Sets user's age.
     * @param age User's age.
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Gets user's booking history.
     * @return User's booking history.
     */
    public ArrayList<Booking> getBookingHistory() {
        return bookingHistory;
    }

    /**
     * Sets user's booking history.
     * @param bookingHistory User's booking history.
     */
    public void setBookingHistory(ArrayList<Booking> bookingHistory) {
        this.bookingHistory = bookingHistory;
    }


}