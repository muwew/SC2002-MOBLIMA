package MOBLIMA.src;
import java.util.*;

public class Booking {
    // Attributes
    private Date date;
    private Date time;
    //private MovieGoer movieGoer;
    private int numberOfSeatsBooked;
    private ArrayList<Seat> seatsBooked;
    private int numberOfTicketsPurchased;
    private ArrayList<Ticket> ticketsPurchased;
    private float totalPrice;
    //private MovieSlot movieSlotBooked;
    private String transactionID;
    private Cinema cinema;

    // Getters and setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getNumberOfSeatsBooked() {
        return numberOfSeatsBooked;
    }

    public void setNumberOfSeatsBooked(int numberOfSeatsBooked) {
        this.numberOfSeatsBooked = numberOfSeatsBooked;
    }

    public ArrayList<Seat> getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(ArrayList<Seat> seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public int getNumberOfTicketsPurchased() {
        return numberOfTicketsPurchased;
    }

    public void setNumberOfTicketsPurchased() {
        this.numberOfTicketsPurchased = this.ticketsPurchased.size();
    }

    public ArrayList<Ticket> getTicketsPurchased() {
        return ticketsPurchased;
    }

    public void setTicketsPurchased(ArrayList<Ticket> ticketsPurchased) {
        this.ticketsPurchased = ticketsPurchased;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTicketID(String ticketID) {
        this.transactionID = transactionID;
    }

    // Other methods
    public void addTicket (Ticket ticket) {
        this.ticketsPurchased.add(ticket);
        System.out.println("Ticket has been added!");
    }

    public void removeTicket (Ticket ticket) {
        this.ticketsPurchased.remove(ticket);
        System.out.println("Ticket has been removed!");
    }

    //public void book (MovieSlot movieSlot, MovieGoer moviegoer) { }

    //public void cancelBooking (MovieSlot movieSlot, MovieGoer movieGoer) {}

    public void printBookingDetails () {
//        System.out.println("Name: " + this.movieGoer.getName());
        System.out.println("\tDate: " + this.date);
        System.out.println("\tTime: " + this.time);
        System.out.println("\tNumber of seats booked: " + this.numberOfSeatsBooked);
        for (Seat i: seatsBooked) {
            i.printSeatDetails();
        }
    }
}
