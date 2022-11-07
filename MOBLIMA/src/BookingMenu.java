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
        System.out.println("How many seats would you like to book?");
        int num = sc.nextInt();
        if(user == null)
            guestBook(num);
        else
            memberBook(num);
    }

    protected void guestBook(int num){

    }

    protected void memberBook(int num){
        MovieSlot movieSlot = booking.getMovieSlotBooked();
        ArrayList<Ticket> ticketsPurchased = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            // Creating ticket details object
            TicketDetails newTicketDetails = new TicketDetails();

            // Getting movie type
            newTicketDetails.setMovieType(movieSlot.getMovieType());

            // Getting cinema class
            newTicketDetails.setCinemaClass(movieSlot.getCinema().getCinemaClass());

            int j = 0;
            // Seat choosing process
            do {
                String[][] cinemaSeatLayout = movieSlot.getCinema().seatLayoutCreation(movieSlot.getCinema().getNoOfRows(), movieSlot.getCinema().getNoOfCols());
                j = seatSelection(cinemaSeatLayout, movieSlot.getCinema().getNoOfRows(), movieSlot.getCinema().getNoOfCols(), num);
            }while(j == 0);

            if(j==2) close();

            // Getting seat type
            Seat currentSeat = booking.getSeatsBooked().get(i);
            newTicketDetails.setSeatType(currentSeat.getSeatType());

            // Getting age bracket from moviegoer
            int choice = 0;
            do {
                System.out.println("For seat " + currentSeat.getSeatID() + ", what type of ticket would you like to purchase?");
                System.out.println("1) Child\n2) Student\n3) Adult\n4) Senior Citizen\n");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        newTicketDetails.setAgeBracket(AgeBracket.CHILD);
                        break;

                    case 2:
                        newTicketDetails.setAgeBracket(AgeBracket.STUDENT);
                        break;

                    case 3:
                        newTicketDetails.setAgeBracket(AgeBracket.ADULT);
                        break;

                    case 4:
                        newTicketDetails.setAgeBracket(AgeBracket.SENIOR_CTZ);
                        break;

                    default:
                        System.out.println("Invalid choice! Please try again!");
                }
            } while (choice < 1 || choice > 4);

            // Getting ticket type
            Calendar timeSlot = movieSlot.getaDate();

            // checking if its a public holiday based on PHList
            for (PublicHoliday day : phList) {
                if (day.getDate().get(Calendar.DATE) == timeSlot.get(Calendar.DATE) && day.getDate().get(Calendar.MONTH) == timeSlot.get(Calendar.MONTH)) {
                    newTicketDetails.setTicketType(TicketType.WEEKENDS_AND_PH);
                    break;
                }
            }

            // if not public holiday, check which day the movie happens on
            if (newTicketDetails.getTicketType() == null) {
                int dayOfWeek = timeSlot.get(Calendar.DAY_OF_WEEK);

                switch (dayOfWeek) {
                    case 2:
                    case 3:
                    case 4:
                        newTicketDetails.setTicketType(TicketType.MON_TO_WED);
                        break;

                    case 5:
                        newTicketDetails.setTicketType(TicketType.THURS);
                        break;

                    case 6:
                        int hours = timeSlot.get(Calendar.HOUR_OF_DAY);
                        if (hours <= 3 || hours >= 18) {
                            newTicketDetails.setTicketType(TicketType.FRI_AFTER_6PM);
                        } else {
                            newTicketDetails.setTicketType(TicketType.FRI_BEFORE_6PM);
                        }

                        break;

                    case 1:
                    case 7:
                        newTicketDetails.setTicketType(TicketType.WEEKENDS_AND_PH);
                        break;
                }
            }

            // Creating a new ticket with the relevant details
            Ticket newTicket = new Ticket(newTicketDetails, currentSeat, movieSlot);

            // Adding the ticket into the ticketsBooked array
            ticketsPurchased.add(newTicket);
        }
        //updating ticketsBooked array
        booking.setTicketsPurchased(ticketsPurchased);

        // Setting the number of tickets purchased as number of seats booked
        //this.numberOfTicketsPurchased = this.numberOfSeatsBooked;
        booking.setNumberOfTicketsPurchased(booking.getNumberOfSeatsBooked());

        // Calculating price of ticket
        for (Ticket ticket: ticketsPurchased) {
            ticket.getTicketDetails().autoAddTicketDetails(ticketList, ticket.getTicketDetails().getMovieType(), ticket.getTicketDetails().getTicketType(), ticket.getTicketDetails().getSeatType(), ticket.getTicketDetails().getAgeBracket(), ticket.getTicketDetails().getCinemaClass());
            float currentTicketPrice = ticket.getTicketDetails().autoGetPrice(ticketList, ticket.getTicketDetails().getMovieType(), ticket.getTicketDetails().getTicketType(), ticket.getTicketDetails().getSeatType(), ticket.getTicketDetails().getAgeBracket(), ticket.getTicketDetails().getCinemaClass());
            booking.setTotalPrice(booking.getTotalPrice()+ currentTicketPrice);
        }

        // Displaying the total price of the booking
        System.out.println("Total price of this transaction is: " + booking.getTotalPrice());
        System.out.println("Enjoy your movie!");

        // Creating the transaction ID
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmm");
        Calendar currentTime = Calendar.getInstance();
        String currentTimeFormatted = dateFormat.format(currentTime.getTime());
        booking.setTransactionID(booking.getCinema().getCinemaCode() + currentTimeFormatted);
        System.out.println("This is your transaction ID for your booking: "+ booking.getTransactionID() + "\nThank you!");

        ArrayList<Booking> bookingHistory = user.getBookingHistory();
        bookingHistory.add(booking);
        user.setBookingHistory(bookingHistory);

        //updating lists
        for(Movie e: movieList){
            if(e.getMovieTitle().equals(booking.getMovieSlotBooked().getMovie().getMovieTitle())){
                for(MovieSlot e1: e.getMovieSlots()){
                    if(e1.getaDate() == booking.getTimeslot() && e1.getCinema().getCinemaCode().equals(booking.getCinema().getCinemaCode())){
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

    protected int seatSelection (String[][] seatLayout, int rows, int cols, int numberOfSeatsToBook) {

        Cinema bookingCinema = booking.getMovieSlotBooked().getCinema();

        // Print out the seat layout for movie goer to see
        bookingCinema.seatsPrinting(seatLayout, rows, cols);
        Scanner sc = new Scanner(System.in);

        // Prompting user on number of seats wanted
        //System.out.println("How many seats would you like to book today?");
        //int numberOfSeats = sc.nextInt();
        //int numberOfSeats = numberOfSeatsToBook;

        // Array to store seats currently selected
        ArrayList<Seat> currentSeatsSelected = new ArrayList<Seat>();

        for (int i = 0; i < numberOfSeatsToBook; i++) {
            // Prompting user for seat selection
            System.out.println("For seat " + (i + 1) + ", which seat would you like to select? (type 0 to quit)");
            String seatSelection = sc.next();

            if(seatSelection.equals("0")){
                return 2;
            }

            // Processing the seat ID and decomposing it into row char and col number
            int rowNo = seatSelection.charAt(0) - 65;
            int colNo = seatSelection.charAt(1) - 48 - 1;

            if (colNo >= cols / 2) {
                colNo += 2;
            }

            if (rowNo >= rows - 2) {
                rowNo++;
            }

            // Checking whether the current chosen seat is occupied
            if (bookingCinema.getSeatLayout()[rowNo][colNo].isSeatSelected() || bookingCinema.getSeatLayout()[rowNo][colNo].isSeatOccupied()) {
                System.out.println("This seat is currently selected or occupied!");
                i--;
            }

            // Seat chosen is NOT selected or occupied --> Move to selection process
            else {

                // If booking for normal cinema
                if (bookingCinema.getCinemaClass() == CinemaClass.NORMAL) {

                    // If seat chosen is a couple seat
                    if (rowNo > rows - 2) {
                        System.out.println("You have chosen a couple seat. Hence, the seat beside the selected seat will automatically be chosen.");

                        // Updating the seat layout for the seats to be selected
                        // Selecting the current chosen seat
                        bookingCinema.getSeatLayout()[rowNo][colNo].selectSeat();
                        currentSeatsSelected.add(bookingCinema.getSeatLayout()[rowNo][colNo]);

                        // Selecting the seat next to the currently selected seat
                        if (colNo % 2 == 0) {
                            bookingCinema.getSeatLayout()[rowNo][colNo + 1].selectSeat();
                            currentSeatsSelected.add(bookingCinema.getSeatLayout()[rowNo][colNo + 1]);
                        } else {
                            bookingCinema.getSeatLayout()[rowNo][colNo - 1].selectSeat();
                            currentSeatsSelected.add(bookingCinema.getSeatLayout()[rowNo][colNo - 1]);
                        }

                        // Printing out the seat layout to display selected seats
                        seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('_', 'S');
                        if (colNo % 2 == 0) {
                            seatLayout[rowNo][colNo + 1] = seatLayout[rowNo][colNo + 1].replace('_', 'S');
                        } else {
                            seatLayout[rowNo][colNo - 1] = seatLayout[rowNo][colNo - 1].replace('_', 'S');
                        }
                        bookingCinema.seatsPrinting(seatLayout, rows, cols);
                        System.out.println();
                    }

                    // Else if the seat chosen is a normal seat
                    else {
                        System.out.println("You have chosen a single seat.");

                        // Updating the seat layout for the seats to be selected
                        // Selecting the current chosen seat
                        bookingCinema.getSeatLayout()[rowNo][colNo].selectSeat();
                        currentSeatsSelected.add(bookingCinema.getSeatLayout()[rowNo][colNo]);

                        // Printing out the seat layout to display selected seats
                        seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('_', 'S');
                        bookingCinema.seatsPrinting(seatLayout, rows, cols);
                        System.out.println();
                    }
                }

                // Else if the cinema is a platinum suite
                else if (bookingCinema.getCinemaClass() == CinemaClass.PLATINUM) {

                    // If seat chosen is an ultima (couple) seat
                    if (rowNo > rows - 2) {
                        System.out.println("You have chosen an ultima seat. Hence, the seat beside the selected seat will automatically be chosen.");

                        // Updating the seat layout for the seats to be selected
                        // Selecting the current chosen seat
                        bookingCinema.getSeatLayout()[rowNo][colNo].selectSeat();
                        currentSeatsSelected.add(bookingCinema.getSeatLayout()[rowNo][colNo]);

                        // Selecting the seat next to the currently selected seat
                        if (colNo % 2 == 0) {
                            bookingCinema.getSeatLayout()[rowNo][colNo + 1].selectSeat();
                            currentSeatsSelected.add(bookingCinema.getSeatLayout()[rowNo][colNo + 1]);
                        } else {
                            bookingCinema.getSeatLayout()[rowNo][colNo - 1].selectSeat();
                            currentSeatsSelected.add(bookingCinema.getSeatLayout()[rowNo][colNo - 1]);
                        }

                        // Printing out the seat layout to display selected seats
                        seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('_', 'S');
                        if (colNo % 2 == 0) {
                            seatLayout[rowNo][colNo + 1] = seatLayout[rowNo][colNo + 1].replace('_', 'S');
                        } else {
                            seatLayout[rowNo][colNo - 1] = seatLayout[rowNo][colNo - 1].replace('_', 'S');
                        }
                        bookingCinema.seatsPrinting(seatLayout, rows, cols);
                        System.out.println();
                    }

                    // Else if the seat chosen is an elite (single) seat
                    else {
                        System.out.println("You have chosen an elite (single) seat.");

                        // Updating the seat layout for the seats to be selected
                        // Selecting the current chosen seat
                        booking.getCinema().getSeatLayout()[rowNo][colNo].selectSeat();
                        currentSeatsSelected.add(booking.getCinema().getSeatLayout()[rowNo][colNo]);

                        // Printing out the seat layout to display selected seats
                        seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('E', 'S');
                        booking.getCinema().seatsPrinting(seatLayout, rows, cols);
                        System.out.println();
                    }
                }
            }
        }

        // Purchase process
        System.out.println("The seats that you have currently selected are: ");
        for (Seat currSeat: currentSeatsSelected) {
            currSeat.printSeatDetails();
        }

        System.out.println("Would you like to confirm your purchase of these seats ?");
        System.out.println("1) Yes, I would like to confirm these selected seats.");
        System.out.println("2) No, I would NOT like to confirm these selected seats.");
        int confirmation = sc.nextInt();
        String[][] updatedSeatLayout = seatLayout;

        do {
            if (confirmation == 1 || confirmation == 2) {
                for (Seat selectedSeat : currentSeatsSelected) {
                    updatedSeatLayout = seatPurchased(updatedSeatLayout, rows, cols, selectedSeat.getSeatID(), confirmation);
                }
            } else {
                System.out.println("Invalid option! Please try again!");
            }
        } while (confirmation != 1 && confirmation != 2);

        //String[][] updatedSeatLayout = seatPurchased(seatLayout, rows, cols, seatSelection);
        //this.cinema.setStringSeatLayout(updatedSeatLayout);
        if(confirmation == 1)
            booking.getCinema().seatsPrinting(updatedSeatLayout, rows, cols);

        booking.getCinema().setStringSeatLayout(updatedSeatLayout);

        booking.getMovieSlotBooked().setCinema(bookingCinema);

        if(confirmation == 1) return 1;

        return 0;
    }

    protected String[][] seatPurchased (String[][] seatLayout, int rows, int cols, String seatSelection, int confirmation) {

        Cinema bookingCinema = booking.getMovieSlotBooked().getCinema();
        ArrayList<Seat> seatsBooked = new ArrayList<>();

        // Decomposing the seat ID to its row character and column number
        int rowNo = seatSelection.charAt(0) - 65;
        int colNo = seatSelection.charAt(1) - 48 - 1;

        if (colNo >= cols / 2) {
            colNo += 2;
        }

        if (rowNo >= rows - 2) {
            rowNo++;
        }

        switch (confirmation) {

            // Purchase confirmed
            case 1:
                // Cinema is normal class
                if (bookingCinema.getCinemaClass() == CinemaClass.NORMAL) {

                    // Purchased a couples seat
                    if (rowNo > rows - 2) {
                        System.out.println("You have purchased a couple seat. Hence, the seat beside the selected seat will automatically be purchased as well. Thank you for your purchase!");

                        // Updating the number of seats booked in this booking
                        booking.setNumberOfSeatsBooked(booking.getNumberOfSeatsBooked() + 2);;

                        // Updating the seat layout for the seats to be selected
                        // Purchasing the current chosen seat
                        bookingCinema.getSeatLayout()[rowNo][colNo].deSelectSeat();
                        bookingCinema.getSeatLayout()[rowNo][colNo].occupySeat();
                        System.out.println();
                        seatsBooked.add(bookingCinema.getSeatLayout()[rowNo][colNo]);

                        // Purchasing the seat next to the currently selected seat
                        if (colNo % 2 == 0) {
                            bookingCinema.getSeatLayout()[rowNo][colNo + 1].deSelectSeat();
                            bookingCinema.getSeatLayout()[rowNo][colNo + 1].occupySeat();
                            System.out.println();
                            seatsBooked.add(bookingCinema.getSeatLayout()[rowNo][colNo + 1]);
                        } else {
                            bookingCinema.getSeatLayout()[rowNo][colNo - 1].deSelectSeat();
                            bookingCinema.getSeatLayout()[rowNo][colNo - 1].occupySeat();
                            System.out.println();
                            seatsBooked.add(bookingCinema.getSeatLayout()[rowNo][colNo - 1]);
                        }

                        // Printing out the seat layout to display purchased seats
                        seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('S', 'X');
                        if (colNo % 2 == 0) {
                            seatLayout[rowNo][colNo + 1] = seatLayout[rowNo][colNo + 1].replace('S', 'X');
                        } else {
                            seatLayout[rowNo][colNo - 1] = seatLayout[rowNo][colNo - 1].replace('S', 'X');
                        }
//                        this.cinema.seatsPrinting(seatLayout, rows, cols);
//                        System.out.println();
                    }

                    // Purchased a normal seat
                    else {
                        System.out.println("You have chosen a single seat.");

                        // Updating the number of seats booked in this booking
                        booking.setNumberOfSeatsBooked(booking.getNumberOfSeatsBooked()+1);
                        //numberOfSeatsBooked++;

                        // Updating the seat layout for the seat to be selected
                        // Purchasing the current chosen seat
                        bookingCinema.getSeatLayout()[rowNo][colNo].deSelectSeat();
                        bookingCinema.getSeatLayout()[rowNo][colNo].occupySeat();
                        System.out.println();
                        seatsBooked.add(bookingCinema.getSeatLayout()[rowNo][colNo]);

                        // Printing out the seat layout to display purchased seats
                        seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('S', 'X');
//                        this.cinema.seatsPrinting(seatLayout, rows, cols);
//                        System.out.println();
                    }
                }

                // Cinema is platinum suites
                else if (bookingCinema.getCinemaClass() == CinemaClass.PLATINUM){
                    // Purchased an ultima (couples) seat
                    if (rowNo > rows - 2) {
                        System.out.println("You have purchased an ultima seat. Hence, the seat beside the selected seat will automatically be purchased as well. Thank you for your purchase!");

                        // Updating the number of seats booked in this booking
                        //this.numberOfSeatsBooked += 2;
                        booking.setNumberOfSeatsBooked(booking.getNumberOfSeatsBooked() + 2);

                        // Updating the seat layout for the seats to be selected
                        // Purchasing the current chosen seat
                        bookingCinema.getSeatLayout()[rowNo][colNo].deSelectSeat();
                        bookingCinema.getSeatLayout()[rowNo][colNo].occupySeat();
                        System.out.println();
                        seatsBooked.add(bookingCinema.getSeatLayout()[rowNo][colNo]);

                        // Purchasing the seat next to the currently selected seat
                        if (colNo % 2 == 0) {
                            bookingCinema.getSeatLayout()[rowNo][colNo + 1].deSelectSeat();
                            bookingCinema.getSeatLayout()[rowNo][colNo + 1].occupySeat();
                            System.out.println();
                            seatsBooked.add(bookingCinema.getSeatLayout()[rowNo][colNo + 1]);
                        } else {
                            bookingCinema.getSeatLayout()[rowNo][colNo - 1].deSelectSeat();
                            bookingCinema.getSeatLayout()[rowNo][colNo - 1].occupySeat();
                            System.out.println();
                            seatsBooked.add(bookingCinema.getSeatLayout()[rowNo][colNo - 1]);
                        }

                        // Printing out the seat layout to display purchased seats
                        seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('S', 'X');
                        if (colNo % 2 == 0) {
                            seatLayout[rowNo][colNo + 1] = seatLayout[rowNo][colNo + 1].replace('S', 'X');
                        } else {
                            seatLayout[rowNo][colNo - 1] = seatLayout[rowNo][colNo - 1].replace('S', 'X');
                        }
//                        this.cinema.seatsPrinting(seatLayout, rows, cols);
//                        System.out.println();
                    }

                    // Purchased an elite seat
                    else {
                        System.out.println("You have purchased an elite seat.");

                        // Updating the number of seats booked in this booking
                        //this.numberOfSeatsBooked += 2;
                        booking.setNumberOfSeatsBooked(booking.getNumberOfSeatsBooked()+2);

                        // Updating the seat layout for the seat to be selected
                        // Selecting the current chosen seat
                        bookingCinema.getSeatLayout()[rowNo][colNo].deSelectSeat();
                        bookingCinema.getSeatLayout()[rowNo][colNo].occupySeat();
                        System.out.println();
                        seatsBooked.add(bookingCinema.getSeatLayout()[rowNo][colNo]);

                        // Printing out the seat layout to display purchased seats
                        seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('S', 'X');
//                        this.cinema.seatsPrinting(seatLayout, rows, cols);
//                        System.out.println();
                    }
                }

                break;

            case 2:
                System.out.println("Purchase has been cancelled.");

                // Cinema is normal class
                if (bookingCinema.getCinemaClass() == CinemaClass.NORMAL) {

                    // Cancel a couples seat
                    if (rowNo > rows - 2) {
                        System.out.println("You have cancelled the purchase of a couple seat. Hence, the purchase of the seat beside the selected seat will automatically be cancelled as well.");

                        // Updating the seat layout for the seats to be cancelled
                        // Cancelling the current chosen seat
                        bookingCinema.getSeatLayout()[rowNo][colNo].deSelectSeat();

                        // Cancelling the seat next to the current seat
                        if (colNo % 2 == 0) {
                            bookingCinema.getSeatLayout()[rowNo][colNo + 1].deSelectSeat();
                        } else {
                            bookingCinema.getSeatLayout()[rowNo][colNo - 1].deSelectSeat();
                        }

                        // Printing out the updated seat layout
                        seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('S', '_');
                        if (colNo % 2 == 0) {
                            seatLayout[rowNo][colNo + 1] = seatLayout[rowNo][colNo + 1].replace('S', '_');
                        } else {
                            seatLayout[rowNo][colNo - 1] = seatLayout[rowNo][colNo - 1].replace('S', '_');
                        }
                        //this.cinema.seatsPrinting(seatLayout, rows, cols);
                        //System.out.println();
                    }

                    // Cancelling a normal seat
                    else {
                        System.out.println("You have cancelled the purchase of a single seat.");

                        // Updating the seat layout for the seat to be cancelled
                        // Cancelling the current chosen seat
                        bookingCinema.getSeatLayout()[rowNo][colNo].deSelectSeat();

                        // Printing out the updated seat layout
                        seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('S', '_');
                        //this.cinema.seatsPrinting(seatLayout, rows, cols);
                        //System.out.println();
                    }
                }

                // Cinema is platinum suites
                else if (bookingCinema.getCinemaClass() == CinemaClass.PLATINUM){

                    // Cancelling an ultima (couples) seat
                    if (rowNo > rows - 2) {
                        System.out.println("You have cancelled the purchase of an ultima seat. Hence, the purchase of the seat beside the selected seat will automatically be cancelled as well. Thank you for your purchase!");

                        // Updating the seat layout for the seats to be cancelled
                        // Cancelling the current chosen seat
                        bookingCinema.getSeatLayout()[rowNo][colNo].deSelectSeat();

                        // Cancelling the seat next to the current seat
                        if (colNo % 2 == 0) {
                            bookingCinema.getSeatLayout()[rowNo][colNo + 1].deSelectSeat();
                        } else {
                            bookingCinema.getSeatLayout()[rowNo][colNo - 1].deSelectSeat();
                        }

                        // Printing out the updated seat layout
                        seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('S', '_');
                        if (colNo % 2 == 0) {
                            seatLayout[rowNo][colNo + 1] = seatLayout[rowNo][colNo + 1].replace('S', '_');
                        } else {
                            seatLayout[rowNo][colNo - 1] = seatLayout[rowNo][colNo - 1].replace('S', '_');
                        }
                        //this.cinema.seatsPrinting(seatLayout, rows, cols);
                        //System.out.println();
                    }

                    // Cancelled an elite seat
                    else {
                        System.out.println("You have cancelled the purchase of an elite seat.");

                        // Updating the seat layout for the seat to be cancelled
                        // Cancelling the currently chosen seat
                        bookingCinema.getSeatLayout()[rowNo][colNo].deSelectSeat();

                        // Printing out the updated seat layout
                        seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('S', 'E');
                        //this.cinema.seatsPrinting(seatLayout, rows, cols);
                        //System.out.println();
                    }
                }

                break;

            default:
                System.out.println("Invalid option! Please try again!");
        }

        booking.setSeatsBooked(seatsBooked);
        booking.getMovieSlotBooked().setCinema(bookingCinema);

        return seatLayout;
    }
}
