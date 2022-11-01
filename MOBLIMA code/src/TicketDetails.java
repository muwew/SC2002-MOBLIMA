import java.util.ArrayList;

public class TicketDetails {

    // Attributes
    private MovieType movieType;
    private TicketType ticketType;
    private AgeBracket ageBracket;
    private CinemaClass cinemaClass;
    private float price;

    // Constructors
    public TicketDetails () {
        this.movieType = null;
        this.ticketType = null;
        this.ageBracket = null;
        this.cinemaClass = null;
        this.price = -1;
    }

    public TicketDetails (MovieType movieType, TicketType ticketType, AgeBracket ageBracket, CinemaClass cinemaClass, float price) {
        this.movieType = movieType;
        this.ticketType = ticketType;
        this.ageBracket = ageBracket;
        this.cinemaClass = cinemaClass;
        this.price = price;
    }

    // Getters and setters
    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public AgeBracket getAgeBracket() {
        return ageBracket;
    }

    public void setAgeBracket(AgeBracket ageBracket) {
        this.ageBracket = ageBracket;
    }

    public CinemaClass getCinemaClass() {
        return cinemaClass;
    }

    public void setCinemaClass(CinemaClass cinemaClass) {
        this.cinemaClass = cinemaClass;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    // Methods
    public float getPrice (MovieType movieType, TicketType ticketType, AgeBracket ageBracket, CinemaClass cinemaClass) {
        if (this.movieType.equals(movieType) && this.ticketType.equals(ticketType) && this.ageBracket.equals(ageBracket) && this.cinemaClass.equals(cinemaClass)) {
            return this.price;
        } else {
            System.out.println("Ticket you're looking for is not found!");
            return -1;
        }
    }

    public void updatePrice (MovieType movieType, TicketType ticketType, AgeBracket ageBracket, CinemaClass cinemaClass, float newPrice) {
        if (this.movieType.equals(movieType) && this.ticketType.equals(ticketType) && this.ageBracket.equals(ageBracket) && this.cinemaClass.equals(cinemaClass)) {
            this.price = newPrice;
            System.out.println("Price has been updated to $" + newPrice);
        } else {
            System.out.println("Ticket you're looking for is not found!");
        }
    }

    public ArrayList<TicketDetails> addTicketDetails (ArrayList<TicketDetails> originalTicketDetailsDB, MovieType movieType, TicketType ticketType, AgeBracket ageBracket, CinemaClass cinemaClass, float newPrice) {
        TicketDetails newTicketDetails = new TicketDetails(movieType, ticketType, ageBracket, cinemaClass, newPrice);
        originalTicketDetailsDB.add(newTicketDetails);
        System.out.println("New ticket details have been added successfully!");
        return originalTicketDetailsDB;
    }
}
