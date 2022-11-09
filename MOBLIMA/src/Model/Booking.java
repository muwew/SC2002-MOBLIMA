package MOBLIMA.src.Model;

import MOBLIMA.src.Accounts.MovieGoer;
import MOBLIMA.src.Enums.AgeBracket;
import MOBLIMA.src.Enums.CinemaClass;
import MOBLIMA.src.Enums.TicketType;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Booking implements Serializable {
    // Attributes
    private Calendar timeslot;
    private MovieGoer movieGoer;
    private int numberOfSeatsBooked;
    private ArrayList<Seat> seatsBooked;
    private int numberOfTicketsPurchased;
    private ArrayList<Ticket> ticketsPurchased;
    private float totalPrice;
    private MovieSlot movieSlotBooked;
    private String transactionID;
    //private Cinema cinema;

    // Constructors
    public Booking(MovieGoer movieGoer, int numberOfSeatsBooked, ArrayList<Seat> seatsBooked, int numberOfTicketsPurchased, ArrayList<Ticket> ticketsPurchased, MovieSlot movieSlotBooked, float totalPrice, String transactionID, Cinema cinema) {
        this.timeslot = movieSlotBooked.getaDate();
        this.movieGoer = movieGoer;
        this.numberOfSeatsBooked = numberOfSeatsBooked;
        this.seatsBooked = seatsBooked;
        this.numberOfTicketsPurchased = numberOfTicketsPurchased;
        this.ticketsPurchased = ticketsPurchased;
        this.totalPrice = totalPrice;
        this.movieSlotBooked = movieSlotBooked;
        this.transactionID = transactionID;
        //this.cinema = cinema;
    }

    public Booking(MovieGoer movieGoer, MovieSlot movieSlotBooked) {
        this.timeslot = movieSlotBooked.getaDate();
        this.movieGoer = movieGoer;
        this.numberOfSeatsBooked = 0;
        this.seatsBooked = new ArrayList<Seat>();
        this.numberOfTicketsPurchased = 0;
        this.ticketsPurchased = new ArrayList<Ticket>();
        this.totalPrice = 0;
        this.movieSlotBooked = movieSlotBooked;
        this.transactionID = null;
        //this.cinema = movieSlotBooked.getCinema();
    }

    public Booking(MovieSlot movieSlotBooked){
        this.timeslot = movieSlotBooked.getaDate();
        this.movieGoer = null;
        this.numberOfSeatsBooked = 0;
        this.seatsBooked = new ArrayList<Seat>();
        this.numberOfTicketsPurchased = 0;
        this.ticketsPurchased = new ArrayList<Ticket>();
        this.totalPrice = 0;
        this.movieSlotBooked = movieSlotBooked;
        this.transactionID = null;
        //this.cinema = movieSlotBooked.getCinema();
    }

    // Getters and setters
    public Calendar getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Calendar timeslot) {
        this.timeslot = timeslot;
    }

    public MovieGoer getMovieGoer() {
        return movieGoer;
    }

    public void setMovieGoer(MovieGoer movieGoer) {
        this.movieGoer = movieGoer;
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

    public void setNumberOfTicketsPurchased(int numberOfTicketsPurchased) {
        this.numberOfTicketsPurchased = numberOfTicketsPurchased;
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

    public MovieSlot getMovieSlotBooked() {
        return movieSlotBooked;
    }

    public void setMovieSlotBooked(MovieSlot movieSlotBooked) {
        this.movieSlotBooked = movieSlotBooked;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    /*public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }*/

    // Other methods
    public void addTicket (Ticket ticket) {
        this.ticketsPurchased.add(ticket);
        System.out.println("Ticket has been added!");
    }

    public void removeTicket (Ticket ticket) {
        this.ticketsPurchased.remove(ticket);
        System.out.println("Ticket has been removed!");
    }

//    public void start () {
//        Scanner sc = new Scanner(System.in);
//
//        int rows = 10;
//        int cols = 12;
//        String[][] seatLayout = this.cinema.seatLayoutCreation(rows, cols);
//
//    }

    public void book (MovieSlot movieSlot, ArrayList<PublicHoliday> phList, ArrayList<TicketDetails> ticketDetailsDB) {

        Scanner sc = new Scanner(System.in);
        System.out.println("How many seats would you like to book?");
        int numberOfSeatsToBook = sc.nextInt();

        String[][] cinemaSeatLayout = new String[1][1];

        // Go through each seat booked
            // Creating ticket details object
            TicketDetails newTicketDetails = new TicketDetails();

            // Getting movie type
            newTicketDetails.setMovieType(movieSlot.getMovieType());

            // Getting cinema class
            newTicketDetails.setCinemaClass(this.movieSlotBooked.getCinema().getCinemaClass());

            // Seat choosing process
            int j=0;

            do {
                if (this.movieSlotBooked.getCinema().getSeatLayout() == null) {
                    cinemaSeatLayout = this.movieSlotBooked.getCinema().seatLayoutCreation(this.movieSlotBooked.getCinema().getNoOfRows(), this.movieSlotBooked.getCinema().getNoOfCols());
                    this.movieSlotBooked.getCinema().setStringSeatLayout(cinemaSeatLayout);
                }//seatSelection(cinemaSeatLayout, this.movieSlotBooked.getCinema().getNoOfRows(), this.movieSlotBooked.getCinema().getNoOfCols(), numberOfSeatsToBook);
                j = seatSelection(this.movieSlotBooked.getCinema().getStringSeatLayout(), this.movieSlotBooked.getCinema().getNoOfRows(), this.movieSlotBooked.getCinema().getNoOfCols(), numberOfSeatsToBook);
            }while(j==0);

            if(j==2) return;

            // Getting seat type
            for(int i=0; i<numberOfSeatsToBook; i++)
            {
                Seat currentSeat = this.seatsBooked.get(i);
            newTicketDetails.setSeatType(currentSeat.getSeatType());

            // Getting age bracket from moviegoer
            int choice = 0;

            do {
                System.out.println("For seat " + currentSeat.getSeatID() + ", what type of ticket would you like to purchase?");
                System.out.println("1) Child\n2) Student\n3) Adult\n4) Senior Citizen");
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

            for (PublicHoliday day : phList) {
                if (day.getDate().get(Calendar.DATE) == timeSlot.get(Calendar.DATE) && day.getDate().get(Calendar.MONTH) == timeSlot.get(Calendar.MONTH)) {
                    newTicketDetails.setTicketType(TicketType.WEEKENDS_AND_PH);
                    break;
                }
            }

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
            this.ticketsPurchased.add(newTicket);
        }

        // Setting the number of tickets purchased as number of seats booked
        this.numberOfTicketsPurchased = this.numberOfSeatsBooked;

        // Calculating price of ticket
        for (Ticket ticket: this.ticketsPurchased) {
            ticket.getTicketDetails().autoAddTicketDetails(ticketDetailsDB, ticket.getTicketDetails().getMovieType(), ticket.getTicketDetails().getTicketType(), ticket.getTicketDetails().getSeatType(), ticket.getTicketDetails().getAgeBracket(), ticket.getTicketDetails().getCinemaClass());
            float currentTicketPrice = ticket.getTicketDetails().autoGetPrice(ticketDetailsDB, ticket.getTicketDetails().getMovieType(), ticket.getTicketDetails().getTicketType(), ticket.getTicketDetails().getSeatType(), ticket.getTicketDetails().getAgeBracket(), ticket.getTicketDetails().getCinemaClass());
            this.totalPrice += currentTicketPrice;
        }

        if(movieGoer == null) {
            System.out.println("To complete your booking, please");
            System.out.println("Enter your name: ");
            sc.nextLine();
            String name = sc.nextLine();
            System.out.println("Enter your mobile number");
            int phoneNo = sc.nextInt();
            System.out.println("Enter your email address");
            sc.nextLine();
            String email = sc.nextLine();
        }

        // Creating the transaction ID
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmm");
        Calendar currentTime = Calendar.getInstance();
        String currentTimeFormatted = dateFormat.format(currentTime.getTime());
        this.transactionID = this.movieSlotBooked.getCinema().getCinemaCode() + currentTimeFormatted;
        System.out.println("This is your transaction ID for your booking: "+ transactionID+ "\nThank you!");

        // Displaying the total price of the booking
        DecimalFormat df = new DecimalFormat("###.##");
        System.out.printf("Total price of this transaction is: " + df.format(this.totalPrice));
        System.out.println("\nWould you like to view the ticket details?");
        System.out.println("1) Yes");
        System.out.println("2) No");
        int choice = sc.nextInt();
        int i =1;

        if(choice == 1){
            for(Ticket e: this.ticketsPurchased){
                System.out.println("Ticket" + "("+ i++ +"):");
                e.printTicketDetails();
            }
        }

        System.out.println("Enjoy your movie!");
    }

    public void cancelBooking () {
        Scanner sc = new Scanner(System.in);
        int count = 1;
        int choice = 0;

        // Printing out the bookings of the moviegoer to choose from
        for (Booking i : this.movieGoer.getBookingHistory()) {
            System.out.println("Booking " + count + ":");
            i.printBookingDetails();
            count++;
        }

        do {
            System.out.println();
            System.out.println("Which entry would you like to delete from your booking history?");
            choice = sc.nextInt();

            if (choice > this.movieGoer.getBookingHistory().size()) {
                System.out.println("Invalid entry! Please try again!");
            } else {
                this.movieGoer.getBookingHistory().remove(choice - 1);
            }
        } while (choice > this.movieGoer.getBookingHistory().size());

        System.out.println();
        System.out.println("Booking removed!");
        System.out.println();
        System.out.println("Updated booking history: ");
        count = 1;

        for (Booking i : this.movieGoer.getBookingHistory()) {
            System.out.println("Booking " + count + ":");
            i.printBookingDetails();
            count++;
        }
    }

    public void printBookingDetails () {
        System.out.println("--------------------------------------------------------------");
        System.out.println("Transaction ID: " + this.transactionID);
        System.out.println("Name: " + this.movieGoer.getName());
        System.out.println("Cineplex: " + this.movieSlotBooked.getCinema().getLocation().toString());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        System.out.println(dateFormat.format(this.timeslot));
        System.out.println("\tNumber of seats booked: " + this.numberOfSeatsBooked);
        for (Seat i: this.seatsBooked) {
            i.printSeatDetails();
        }
        System.out.println("\tNumber of tickets purchased: " + this.numberOfTicketsPurchased);
        for (Ticket j: this.ticketsPurchased) {
            j.printTicketDetails();
        }
        System.out.println("Amount paid: " + this.totalPrice);
        System.out.println("--------------------------------------------------------------");
    }

    public int seatSelection (String[][] seatLayout, int rows, int cols, int numberOfSeatsToBook) {

        // Print out the seat layout for movie goer to see
        this.movieSlotBooked.getCinema().seatsPrinting(seatLayout, rows, cols);
        Scanner sc = new Scanner(System.in);

        // Prompting user on number of seats wanted
        //System.out.println("How many seats would you like to book today?");
        //int numberOfSeats = sc.nextInt();
        int numberOfSeats = numberOfSeatsToBook;

        // Array to store seats currently selected
        ArrayList<Seat> currentSeatsSelected = new ArrayList<Seat>();

        for (int i = 0; i < numberOfSeats; i++) {
            // Prompting user for seat selection
            System.out.println("For seat " + (i + 1) + ", which seat would you like to select? (type 0 to quit)");
            String seatSelection = sc.next();

            if(seatSelection.equals("0"))
                return 2;

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
            if (this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo].isSeatSelected() || this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo].isSeatOccupied()) {
                System.out.println("This seat is currently selected or occupied!");
                i--;
            }

            // Seat chosen is NOT selected or occupied --> Move to selection process
            else {

                // If booking for normal cinema
                if (this.movieSlotBooked.getCinema().getCinemaClass() == CinemaClass.NORMAL) {

                    // If seat chosen is a couple seat
                    if (rowNo > rows - 2) {
                        System.out.println("You have chosen a couple seat. Hence, the seat beside the selected seat will automatically be chosen.");

                        // Updating the seat layout for the seats to be selected
                        // Selecting the current chosen seat
                        this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo].selectSeat();
                        currentSeatsSelected.add(this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo]);

                        // Selecting the seat next to the currently selected seat
                        if (colNo % 2 == 0) {
                            this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo + 1].selectSeat();
                            currentSeatsSelected.add(this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo + 1]);
                        } else {
                            this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo - 1].selectSeat();
                            currentSeatsSelected.add(this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo - 1]);
                        }

                        // Printing out the seat layout to display selected seats
                        seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('_', 'S');
                        if (colNo % 2 == 0) {
                            seatLayout[rowNo][colNo + 1] = seatLayout[rowNo][colNo + 1].replace('_', 'S');
                        } else {
                            seatLayout[rowNo][colNo - 1] = seatLayout[rowNo][colNo - 1].replace('_', 'S');
                        }
                        this.movieSlotBooked.getCinema().seatsPrinting(seatLayout, rows, cols);
                        System.out.println();
                    }

                    // Else if the seat chosen is a normal seat
                    else {
                        System.out.println("You have chosen a single seat.");

                        // Updating the seat layout for the seats to be selected
                        // Selecting the current chosen seat
                        this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo].selectSeat();
                        currentSeatsSelected.add(this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo]);

                        // Printing out the seat layout to display selected seats
                        seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('_', 'S');
                        this.movieSlotBooked.getCinema().seatsPrinting(seatLayout, rows, cols);
                        System.out.println();
                    }
                }

                // Else if the cinema is a platinum suite
                else if (this.movieSlotBooked.getCinema().getCinemaClass() == CinemaClass.PLATINUM) {

                    // If seat chosen is an ultima (couple) seat
                    if (rowNo > rows - 2) {
                        System.out.println("You have chosen an ultima seat. Hence, the seat beside the selected seat will automatically be chosen.");

                        // Updating the seat layout for the seats to be selected
                        // Selecting the current chosen seat
                        this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo].selectSeat();
                        currentSeatsSelected.add(this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo]);

                        // Selecting the seat next to the currently selected seat
                        if (colNo % 2 == 0) {
                            this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo + 1].selectSeat();
                            currentSeatsSelected.add(this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo + 1]);
                        } else {
                            this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo - 1].selectSeat();
                            currentSeatsSelected.add(this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo - 1]);
                        }

                        // Printing out the seat layout to display selected seats
                        seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('_', 'S');
                        if (colNo % 2 == 0) {
                            seatLayout[rowNo][colNo + 1] = seatLayout[rowNo][colNo + 1].replace('_', 'S');
                        } else {
                            seatLayout[rowNo][colNo - 1] = seatLayout[rowNo][colNo - 1].replace('_', 'S');
                        }
                        this.movieSlotBooked.getCinema().seatsPrinting(seatLayout, rows, cols);
                        System.out.println();
                    }

                    // Else if the seat chosen is an elite (single) seat
                    else {
                        System.out.println("You have chosen an elite (single) seat.");

                        // Updating the seat layout for the seats to be selected
                        // Selecting the current chosen seat
                        this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo].selectSeat();
                        currentSeatsSelected.add(this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo]);

                        // Printing out the seat layout to display selected seats
                        seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('E', 'S');
                        this.movieSlotBooked.getCinema().seatsPrinting(seatLayout, rows, cols);
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
        this.movieSlotBooked.getCinema().seatsPrinting(updatedSeatLayout, rows, cols);
        this.movieSlotBooked.getCinema().setStringSeatLayout(updatedSeatLayout);

        if(confirmation == 1) return 1;

        return 0;
    }

    public String[][] seatPurchased (String[][] seatLayout, int rows, int cols, String seatSelection, int confirmation) {
        Scanner sc = new Scanner(System.in);

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
                if (this.movieSlotBooked.getCinema().getCinemaClass() == CinemaClass.NORMAL) {

                    // Purchased a couples seat
                    if (rowNo > rows - 2) {
                        System.out.println("You have purchased a couple seat. Hence, the seat beside the selected seat will automatically be purchased as well. Thank you for your purchase!");

                        // Updating the number of seats booked in this booking
                        this.numberOfSeatsBooked += 2;

                        // Updating the seat layout for the seats to be selected
                        // Purchasing the current chosen seat
                        this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo].deSelectSeat();
                        this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo].occupySeat();
                        System.out.println();
                        this.seatsBooked.add(this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo]);

                        // Purchasing the seat next to the currently selected seat
                        if (colNo % 2 == 0) {
                            this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo + 1].deSelectSeat();
                            this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo + 1].occupySeat();
                            System.out.println();
                            this.seatsBooked.add(this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo + 1]);
                        } else {
                            this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo - 1].deSelectSeat();
                            this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo - 1].occupySeat();
                            System.out.println();
                            this.seatsBooked.add(this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo - 1]);
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
                        this.numberOfSeatsBooked++;

                        // Updating the seat layout for the seat to be selected
                        // Purchasing the current chosen seat
                        this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo].deSelectSeat();
                        this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo].occupySeat();
                        System.out.println();
                        this.seatsBooked.add(this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo]);

                        // Printing out the seat layout to display purchased seats
                        seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('S', 'X');
//                        this.cinema.seatsPrinting(seatLayout, rows, cols);
//                        System.out.println();
                    }
                }

                // Cinema is platinum suites
                else if (this.movieSlotBooked.getCinema().getCinemaClass() == CinemaClass.PLATINUM){
                    // Purchased an ultima (couples) seat
                    if (rowNo > rows - 2) {
                        System.out.println("You have purchased an ultima seat. Hence, the seat beside the selected seat will automatically be purchased as well. Thank you for your purchase!");

                        // Updating the number of seats booked in this booking
                        this.numberOfSeatsBooked += 2;

                        // Updating the seat layout for the seats to be selected
                        // Purchasing the current chosen seat
                        this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo].deSelectSeat();
                        this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo].occupySeat();
                        System.out.println();
                        this.seatsBooked.add(this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo]);

                        // Purchasing the seat next to the currently selected seat
                        if (colNo % 2 == 0) {
                            this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo + 1].deSelectSeat();
                            this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo + 1].occupySeat();
                            System.out.println();
                            this.seatsBooked.add(this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo + 1]);
                        } else {
                            this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo - 1].deSelectSeat();
                            this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo - 1].occupySeat();
                            System.out.println();
                            this.seatsBooked.add(this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo - 1]);
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
                        this.numberOfSeatsBooked++;

                        // Updating the seat layout for the seat to be selected
                        // Selecting the current chosen seat
                        this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo].deSelectSeat();
                        this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo].occupySeat();
                        System.out.println();
                        this.seatsBooked.add(this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo]);

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
                if (this.movieSlotBooked.getCinema().getCinemaClass() == CinemaClass.NORMAL) {

                    // Cancel a couples seat
                    if (rowNo > rows - 2) {
                        System.out.println("You have cancelled the purchase of a couple seat. Hence, the purchase of the seat beside the selected seat will automatically be cancelled as well.");

                        // Updating the seat layout for the seats to be cancelled
                        // Cancelling the current chosen seat
                        this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo].deSelectSeat();

                        // Cancelling the seat next to the current seat
                        if (colNo % 2 == 0) {
                            this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo + 1].deSelectSeat();
                        } else {
                            this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo - 1].deSelectSeat();
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
                        this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo].deSelectSeat();

                        // Printing out the updated seat layout
                        seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('S', '_');
                        //this.cinema.seatsPrinting(seatLayout, rows, cols);
                        //System.out.println();
                    }
                }

                // Cinema is platinum suites
                else if (this.movieSlotBooked.getCinema().getCinemaClass() == CinemaClass.PLATINUM){

                    // Cancelling an ultima (couples) seat
                    if (rowNo > rows - 2) {
                        System.out.println("You have cancelled the purchase of an ultima seat. Hence, the purchase of the seat beside the selected seat will automatically be cancelled as well. Thank you for your purchase!");

                        // Updating the seat layout for the seats to be cancelled
                        // Cancelling the current chosen seat
                        this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo].deSelectSeat();

                        // Cancelling the seat next to the current seat
                        if (colNo % 2 == 0) {
                            this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo + 1].deSelectSeat();
                        } else {
                            this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo - 1].deSelectSeat();
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
                        this.movieSlotBooked.getCinema().getSeatLayout()[rowNo][colNo].deSelectSeat();

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

        //this.movieSlotBooked.setCinema(this.movieSlotBooked.getCinema());
        
        return seatLayout;
    }
}
