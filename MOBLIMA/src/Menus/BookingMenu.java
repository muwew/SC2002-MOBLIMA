package MOBLIMA.src.Menus;
import MOBLIMA.src.Model.Booking;
import MOBLIMA.src.Model.Movie;
import MOBLIMA.src.Accounts.MovieGoer;
import MOBLIMA.src.Model.MovieSlot;

import java.util.*;
import static MOBLIMA.src.DataManager.*;

public class BookingMenu extends Menu {

    private Booking booking;

    private boolean loggedIn = false;
    private MovieGoer user;


    Scanner sc = new Scanner(System.in);

    public BookingMenu(){}

    public BookingMenu(Booking booking, MovieGoer user, ArrayList<Movie> movieList, ArrayList<MovieGoer> userList){
        this.booking = booking;
        this.user = user;
        this.movieList = movieList;
        this.userList = userList;
    }

    public BookingMenu(Booking booking, ArrayList<Movie> movieList, ArrayList<MovieGoer> userList){
        this.booking = booking;
        this.user = null;
        this.movieList = movieList;
        this.userList = userList;
    }

    protected void printMenu(){
        if(user == null)
            guestBook();
        else
            memberBook();
    }

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
