package MOBLIMA.src;
import java.io.Serializable;
import java.util.ArrayList;

public class Ticket implements Serializable {

    // Attributes
    private TicketDetails ticketDetails;
    private Seat seat;
    private MovieSlot movieSlot;

    // Constructor
    public Ticket () {
        this.ticketDetails = new TicketDetails();
        this.seat = null;
        this.movieSlot = null;
    }

    public Ticket (TicketDetails ticketDetails, Seat seat, MovieSlot movieSlot) {
        this.ticketDetails = ticketDetails;
        this.seat = seat;
        this.movieSlot = movieSlot;
    }

    // Getters and setters
    public TicketDetails getTicketDetails() {
        return ticketDetails;
    }

    public void setTicketDetails(TicketDetails ticketDetails) {
        this.ticketDetails = ticketDetails;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public MovieSlot getMovieSlot() {
        return movieSlot;
    }

    public void setMovieSlot(MovieSlot movieSlot) {
        this.movieSlot = movieSlot;
    }

    // Methods
    public void printTicketDetails () {
        System.out.println("Details of ticket are as follows:\n" +
                        "Movie Title: " + movieSlot.getMovie().getMovieTitle() +"\n" +
                "Movie Type: " + ticketDetails.getMovieType().toString() + "\n" +
                "Ticket Type: " + ticketDetails.getTicketType().toString() + "\n" +
                "Age Bracket: " + ticketDetails.getAgeBracket().toString() + "\n" +
                "Cinema Class: " + ticketDetails.getCinemaClass().toString() + "\n" +
                "Seat: " + seat.getSeatID() + "\n" +
                "Seat Type: " + seat.getSeatType() + "\n");
    }
}
