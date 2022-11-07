package MOBLIMA.src;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import static MOBLIMA.src.DataManager.*;

public class BookingMenu extends Menu{

    private Booking booking;
    private ArrayList<MovieGoer> userList;
    private ArrayList<Movie> movieList;
    private ArrayList<PublicHoliday> phList = (ArrayList<PublicHoliday>) readData("phlist.txt");
    private ArrayList<TicketDetails> ticketList = (ArrayList<TicketDetails>) readData("ticketlist.txt");
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
