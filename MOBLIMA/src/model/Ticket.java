package MOBLIMA.src.model;

import java.io.Serializable;

/**
 * Class that represents the ticket for movie slots.
 */
public class Ticket implements Serializable {

    // Attributes
    /**
     * Details of ticket.
     */
    private TicketDetails ticketDetails;

    /**
     * Seat associated with ticket.
     */
    private Seat seat;

    /**
     * Movie slot ticket is for.
     */
    private MovieSlot movieSlot;

    // Constructor

    /**
     * Constructors.
     */
    public Ticket () {
        this.ticketDetails = new TicketDetails();
        this.seat = null;
        this.movieSlot = null;
    }

    public Ticket (TicketDetails ticketDetails, Seat seat, MovieSlot movieSlot) {
        this.ticketDetails = ticketDetails;
        this.seat = seat;
        this.movieSlot = movieSlot;
        this.ticketDetails.setMovieTitle(movieSlot.getMovie().getMovieTitle());
    }

    // Getters and setters

    /**
     * Gets ticket details
     * @return Ticket details.
     */
    public TicketDetails getTicketDetails() {
        return ticketDetails;
    }

    /**
     * Sets ticket details.
     * @param ticketDetails Ticket details.
     */
    public void setTicketDetails(TicketDetails ticketDetails) {
        this.ticketDetails = ticketDetails;
    }

    /**
     * Gets seat.
     * @return Seat.
     */
    public Seat getSeat() {
        return seat;
    }

    /**
     * Sets seat.
     * @param seat Seat.
     */
    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    /**
     * Gets movie slot of ticket.
     * @return Movie slot.
     */
    public MovieSlot getMovieSlot() {
        return movieSlot;
    }

    /**
     * Sets movie slot of ticket.
     * @param movieSlot Movie slot.
     */
    public void setMovieSlot(MovieSlot movieSlot) {
        this.movieSlot = movieSlot;
    }

    // Methods

    /**
     * Method to print out all the ticket details of ticket.
     */
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
