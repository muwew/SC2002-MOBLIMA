package MOBLIMA.src.menus;
import MOBLIMA.src.model.Booking;
import MOBLIMA.src.model.Movie;
import MOBLIMA.src.model.MovieGoer;
import MOBLIMA.src.model.MovieSlot;

import java.util.*;
import static MOBLIMA.src.DataManager.*;

/**
 * Main menu for users to book movies.
 */
public class BookingMenu extends Menu {

    /**
     * Booking class to do the bookings.
     */
    private Booking booking;

    /**
     * If user has logged in.
     */
    private boolean loggedIn = false;

    /**
     * Account of the user when they log in.
     */
    private MovieGoer user;


    Scanner sc = new Scanner(System.in);

    /**
     * Constructors.
     */
    public BookingMenu(){}

    /**
     * Constructor for users that have logged in.
     * @param booking Booking class for booking.
     * @param user User's account.
     * @param movieList Current movie listing.
     * @param userList All users' accounts.
     */
    public BookingMenu(Booking booking, MovieGoer user, ArrayList<Movie> movieList, ArrayList<MovieGoer> userList){
        this.booking = booking;
        this.user = user;
        this.movieList = movieList;
        this.userList = userList;
    }

    /**
     * Constructor for guests.
     * @param booking Booking class for booking.
     * @param movieList Current movie listing.
     * @param userList All users' accounts.
     */
    public BookingMenu(Booking booking, ArrayList<Movie> movieList, ArrayList<MovieGoer> userList){
        this.booking = booking;
        this.user = null;
        this.movieList = movieList;
        this.userList = userList;
    }

    /**
     * Method called when menu first opened.
     */
    protected void printMenu(){
        if(user == null)
            guestBook();
        else
            memberBook();

    }

    /**
     * Method for guests to book movie time slots.
     */
    protected void guestBook(){
        MovieSlot movieSlot = booking.getMovieSlotBooked();

        booking.book(movieSlot, phList, ticketList);

        //updating lists
        for(Movie e: movieList){
            if(e.getMovieTitle().equals(booking.getMovieSlotBooked().getMovie().getMovieTitle())){
                for(MovieSlot e1: e.getMovieSlots()){
                    if(e1.getaDate().getTime().equals(booking.getMovieSlotBooked().getaDate().getTime()) && e1.getCinema().getCinemaCode().equals(booking.getMovieSlotBooked().getCinema().getCinemaCode())){
                        e1 = booking.getMovieSlotBooked();
                    }
                }
            }
        }

        updateData(movieList, "movielist.txt");
        updateData(ticketList, "ticketlist.txt");
    }

    /**
     * Method for users that have logged in to book time slots.
     */
    protected void memberBook(){
        MovieSlot movieSlot = booking.getMovieSlotBooked();

        booking.book(movieSlot, phList, ticketList);

        ArrayList<Booking> bookingHistory = user.getBookingHistory();
        bookingHistory.add(booking);
        user.setBookingHistory(bookingHistory);

        //updating lists
        for(Movie e: movieList){
            if(e.getMovieTitle().equals(booking.getMovieSlotBooked().getMovie().getMovieTitle())){
                for(MovieSlot e1: e.getMovieSlots()){
                    if(e1.getaDate().getTime().equals(booking.getMovieSlotBooked().getaDate().getTime()) && e1.getCinema().getCinemaCode().equals(booking.getMovieSlotBooked().getCinema().getCinemaCode())){
                        e1 = booking.getMovieSlotBooked();
                    }
                }
            }
        }
        for(MovieGoer e: userList){
            if(e.getUsername().equals(user.getUsername()))
                e = user;
        }

        updateData(movieList, "movielist.txt");
        updateData(userList, "useraccounts.txt");
        updateData(ticketList, "ticketlist.txt");
    }
}
