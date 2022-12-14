package MOBLIMA.src.model;

import MOBLIMA.src.enums.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that stores the details of tickets.
 */
public class TicketDetails implements Serializable {

    // Attributes
    /**
     * Movie title.
     */
    private String movieTitle;

    /**
     * Movie type.
     */
    private MovieType movieType;

    /**
     * Ticket type.
     */
    private TicketType ticketType;

    /**
     * Seat type.
     */
    private SeatType seatType;

    /**
     * Age bracket for ticket.
     */
    private AgeBracket ageBracket;

    /**
     * Cinema class of ticket.
     */
    private CinemaClass cinemaClass;

    /**
     * Price of ticket.
     */
    private float price;

    // Constructors

    /**
     * Constructors.
     */
    public TicketDetails () {
        this(null, null, null, null, null, -1);
    }

    public TicketDetails (MovieType movieType, TicketType ticketType, SeatType seatType, AgeBracket ageBracket, CinemaClass cinemaClass, float price) {
        this.movieTitle = "";
        this.movieType = movieType;
        this.ticketType = ticketType;
        this.seatType = seatType;
        this.ageBracket = ageBracket;
        this.cinemaClass = cinemaClass;
        this.price = price;
    }

    // Getters and setters

    /**
     * Gets movie title.
     * @return Movie title.
     */
    public String getMovieTitle() {
        return movieTitle;
    }

    /**
     * Sets movie title.
     * @param movieTitle Movie title.
     */
    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    /**
     * Gets movie type.
     * @return Movie type.
     */
    public MovieType getMovieType() {
        return movieType;
    }

    /**
     * Sets movie type.
     * @param movieType Movie type.
     */
    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    /**
     * Gets ticket type.
     * @return Ticket type.
     */
    public TicketType getTicketType() {
        return ticketType;
    }

    /**
     * Sets ticket type.
     * @param ticketType Ticket type.
     */
    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    /**
     * Gets seat type.
     * @return Seat type.
     */
    public SeatType getSeatType() {
        return seatType;
    }

    /**
     * Sets seat type.
     * @param seatType Seat type.
     */
    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    /**
     * Gets age bracket.
     * @return Age bracket.
     */
    public AgeBracket getAgeBracket() {
        return ageBracket;
    }

    /**
     * Sets age bracket.
     * @param ageBracket Age bracket.
     */
    public void setAgeBracket(AgeBracket ageBracket) {
        this.ageBracket = ageBracket;
    }

    /**
     * Gets cinema class.
     * @return Cinema class.
     */
    public CinemaClass getCinemaClass() {
        return cinemaClass;
    }

    /**
     * Sets cinema class.
     * @param cinemaClass Cinema class.
     */
    public void setCinemaClass(CinemaClass cinemaClass) {
        this.cinemaClass = cinemaClass;
    }

    /**
     * Gets price of ticket.
     * @return Price of ticket.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets price of ticket.
     * @param price Price of ticket.
     */
    public void setPrice(float price) {
        this.price = price;
    }

    // Methods
    /**
     * Gets price of ticket based on past tickets / tickets set by admin.
     * @param originalTicketDetailsDB Tickets stored.
     * @param movieType Movie type.
     * @param ticketType Ticket type.
     * @param seatType Seat type.
     * @param ageBracket Age bracket.
     * @param cinemaClass Cinema Class.
     * @return Price of ticket.
     */
    public float autoGetPrice (ArrayList<TicketDetails> originalTicketDetailsDB, MovieType movieType, TicketType ticketType, SeatType seatType, AgeBracket ageBracket, CinemaClass cinemaClass) {
        for (TicketDetails i : originalTicketDetailsDB) {
            if (movieType == i.getMovieType() && ticketType == i.getTicketType() && seatType == i.getSeatType() && ageBracket == i.getAgeBracket() && cinemaClass == i.getCinemaClass()) {
                System.out.println("Ticket details found! Price of ticket is " + i.getPrice());
                return i.getPrice();
            }
        }

        System.out.println("Ticket type not found in database!");
        return -1f;
    }

    /**
     * Method to allow admins to change price of tickets for future purchases.
     * @param originalTicketDetailsDB Tickets stored.
     */
    public void updatePrice (ArrayList<TicketDetails> originalTicketDetailsDB) {
        // If ticket DB is empty, return
        if (originalTicketDetailsDB.size() == 0) {
            System.out.println("Ticket details database is empty!");
            return;
        }

        Scanner sc = new Scanner(System.in);
        int choice = 0;

        TicketDetails ticketDetails = new TicketDetails();

        // Getting movie type
        do {
            System.out.println("What is the type of movie? ");
            System.out.println("1) Digital\n2) Blockbuster\n3) 3D\n4) Exclusive\n5) IMAX 3D\n");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    ticketDetails.setMovieType(MovieType.DIGITAL);
                    break;

                case 2:
                    ticketDetails.setMovieType(MovieType.BLOCKBUSTER);
                    break;

                case 3:
                    ticketDetails.setMovieType(MovieType.THREE_D);
                    break;

                case 4:
                    ticketDetails.setMovieType(MovieType.EXCLUSIVE);
                    break;

                case 5:
                    ticketDetails.setMovieType(MovieType.IMAX_THREE_D);
                    break;

                default:
                    System.out.println("Invalid choice! Please try again!");
            }
        } while (choice < 1 || choice > 5);

        // Getting ticket type
        do {
            System.out.println("What is the type of ticket? ");
            System.out.println("1) Mon to Wed\n2) Thurs\n3) Fri before 6pm\n4) Fri after 6pm\n5) Weekends and PH\n");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    ticketDetails.setTicketType(TicketType.MON_TO_WED);
                    break;

                case 2:
                    ticketDetails.setTicketType(TicketType.THURS);
                    break;

                case 3:
                    ticketDetails.setTicketType(TicketType.FRI_BEFORE_6PM);
                    break;

                case 4:
                    ticketDetails.setTicketType(TicketType.FRI_AFTER_6PM);
                    break;

                case 5:
                    ticketDetails.setTicketType(TicketType.WEEKENDS_AND_PH);
                    break;

                default:
                    System.out.println("Invalid choice! Please try again!");
            }
        } while (choice < 1 || choice > 5);

        // Getting seat type
        do {
            System.out.println("What is the type of seat? ");
            System.out.println("1) Normal Seat\n2) Couple Seat\n3) Elite Seat\n4) Ultima Seat\n");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    ticketDetails.setSeatType(SeatType.NORMAL_SEAT);
                    break;

                case 2:
                    ticketDetails.setSeatType(SeatType.COUPLE_SEAT);
                    break;

                case 3:
                    ticketDetails.setSeatType(SeatType.ELITE_SEAT);
                    break;

                case 4:
                    ticketDetails.setSeatType(SeatType.ULTIMA_SEAT);
                    break;

                default:
                    System.out.println("Invalid choice! Please try again!");
            }
        } while (choice < 1 || choice > 4);

        // Getting age bracket
        do {
            System.out.println("What is the age bracket? ");
            System.out.println("1) Student\n2) Child\n3) Adult\n4) Senior Citizen\n");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    ticketDetails.setAgeBracket(AgeBracket.STUDENT);
                    break;

                case 2:
                    ticketDetails.setAgeBracket(AgeBracket.CHILD);
                    break;

                case 3:
                    ticketDetails.setAgeBracket(AgeBracket.ADULT);
                    break;

                case 4:
                    ticketDetails.setAgeBracket(AgeBracket.SENIOR_CTZ);
                    break;

                default:
                    System.out.println("Invalid choice! Please try again!");
            }
        } while (choice < 1 || choice > 4);

        // Getting cinema class
        do {
            System.out.println("What is the cinema class? ");
            System.out.println("1) Normal\n2) Platinum\n");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    ticketDetails.setCinemaClass(CinemaClass.NORMAL);
                    break;

                case 2:
                    ticketDetails.setCinemaClass(CinemaClass.PLATINUM);
                    break;

                default:
                    System.out.println("Invalid choice! Please try again!");
            }
        } while (choice < 1 || choice > 2);

        System.out.println("Enter new price: ");
        float newPrice = sc.nextFloat();
        // Finding ticket details in ticket details database for respective prices
        for (TicketDetails i: originalTicketDetailsDB) {
            if (ticketDetails.getMovieType().equals(i.getMovieType()) && ticketDetails.getTicketType().equals(i.getTicketType()) && ticketDetails.getSeatType().equals(i.getSeatType()) && ticketDetails.getAgeBracket().equals(i.getAgeBracket()) && ticketDetails.getCinemaClass().equals(i.getCinemaClass())) {
                System.out.println("Ticket details found! Current price of ticket is " + i.getPrice());
                i.setPrice(newPrice);
                System.out.println("Ticket price updated! New price of ticket is " + i.getPrice());
                return;
            }
        }

        // Cannot find ticket details in database
        System.out.println("Ticket details you're looking for is not found!");
    }

    /**
     * Method to store new ticket details.
     * @param originalTicketDetailsDB Tickets stored.
     * @return ArrayList of stored ticket details.
     */
    public ArrayList<TicketDetails> addTicketDetails (ArrayList<TicketDetails> originalTicketDetailsDB) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        float basePrice = 0;

        TicketDetails ticketDetails = new TicketDetails();

        // Getting cinema class
        do {
            System.out.println("What is the cinema class? ");
            System.out.println("1) Normal\n2) Platinum\n");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    ticketDetails.setCinemaClass(CinemaClass.NORMAL);
                    basePrice = 28f;
                    break;

                case 2:
                    ticketDetails.setCinemaClass(CinemaClass.PLATINUM);
                    basePrice = 8.5f;
                    break;

                default:
                    System.out.println("Invalid choice! Please try again!");
            }
        } while (choice < 1 || choice > 2);

        // Normal class tickets
        if (ticketDetails.cinemaClass == CinemaClass.NORMAL) {
            // Getting movie type
            do {
                System.out.println("What is the type of movie? ");
                System.out.println("1) Digital\n2) Blockbuster\n3) 3D\n4) Exclusive\n5) IMAX 3D\n");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        ticketDetails.setMovieType(MovieType.DIGITAL);
                        break;

                    case 2:
                        ticketDetails.setMovieType(MovieType.BLOCKBUSTER);
                        basePrice++;
                        break;

                    case 3:
                        ticketDetails.setMovieType(MovieType.THREE_D);
                        basePrice += 3.5f;
                        break;

                    case 4:
                        ticketDetails.setMovieType(MovieType.EXCLUSIVE);
                        basePrice += 3.5f;
                        break;

                    case 5:
                        ticketDetails.setMovieType(MovieType.IMAX_THREE_D);
                        basePrice += 5.0f;
                        break;

                    default:
                        System.out.println("Invalid choice! Please try again!");
                }
            } while (choice < 1 || choice > 5);

            // Getting ticket type
            do {
                System.out.println("What is the type of ticket? ");
                System.out.println("1) Mon to Wed\n2) Thurs\n3) Fri before 6pm\n4) Fri after 6pm\n5) Weekends and PH\n");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        ticketDetails.setTicketType(TicketType.MON_TO_WED);
                        break;

                    case 2:
                        ticketDetails.setTicketType(TicketType.THURS);
                        basePrice++;
                        break;

                    case 3:
                        ticketDetails.setTicketType(TicketType.FRI_BEFORE_6PM);
                        basePrice++;
                        break;

                    case 4:
                        ticketDetails.setTicketType(TicketType.FRI_AFTER_6PM);
                        basePrice += 2.5f;
                        break;

                    case 5:
                        ticketDetails.setTicketType(TicketType.WEEKENDS_AND_PH);
                        basePrice += 2.5f;
                        break;

                    default:
                        System.out.println("Invalid choice! Please try again!");
                }
            } while (choice < 1 || choice > 5);

            // Getting seat type
            do {
                System.out.println("What is the type of seat? ");
                System.out.println("1) Normal Seat\n2) Couple Seat\n");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        ticketDetails.setSeatType(SeatType.NORMAL_SEAT);
                        break;

                    case 2:
                        ticketDetails.setSeatType(SeatType.COUPLE_SEAT);
                        break;

                    default:
                        System.out.println("Invalid choice! Please try again!");
                }
            } while (choice < 1 || choice > 2);

            // Getting age bracket
            do {
                System.out.println("What is the age bracket? ");
                System.out.println("1) Student\n2) Child\n3) Adult\n4) Senior Citizen\n");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        ticketDetails.setAgeBracket(AgeBracket.STUDENT);
                        basePrice *= 0.3f;
                        break;

                    case 2:
                        ticketDetails.setAgeBracket(AgeBracket.CHILD);
                        basePrice *= 0.5f;
                        break;

                    case 3:
                        ticketDetails.setAgeBracket(AgeBracket.ADULT);
                        break;

                    case 4:
                        ticketDetails.setAgeBracket(AgeBracket.SENIOR_CTZ);
                        basePrice *= 0.6f;
                        break;

                    default:
                        System.out.println("Invalid choice! Please try again!");
                }
            } while (choice < 1 || choice > 4);
        }

        // Platinum class tickets
        else if (ticketDetails.cinemaClass == CinemaClass.PLATINUM) {
            do {
                System.out.println("What is the type of movie? ");
                System.out.println("1) Digital\n2) Blockbuster\n3) Exclusive\n");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        ticketDetails.setMovieType(MovieType.DIGITAL);
                        break;

                    case 2:
                        ticketDetails.setMovieType(MovieType.BLOCKBUSTER);
                        basePrice += 5.0f;
                        break;

                    case 3:
                        ticketDetails.setMovieType(MovieType.EXCLUSIVE);
                        basePrice += 3.5f;
                        break;

                    default:
                        System.out.println("Invalid choice! Please try again!");
                }
            } while (choice < 1 || choice > 3);

            // Getting ticket type
            do {
                System.out.println("What is the type of ticket? ");
                System.out.println("1) Mon to Wed\n2) Thurs\n3) Fri before 6pm\n4) Fri after 6pm\n5) Weekends and PH\n");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        ticketDetails.setTicketType(TicketType.MON_TO_WED);
                        break;

                    case 2:
                        ticketDetails.setTicketType(TicketType.THURS);
                        break;

                    case 3:
                        ticketDetails.setTicketType(TicketType.FRI_BEFORE_6PM);
                        basePrice += 5.0f;
                        break;

                    case 4:
                        ticketDetails.setTicketType(TicketType.FRI_AFTER_6PM);
                        basePrice += 5.0f;
                        break;

                    case 5:
                        ticketDetails.setTicketType(TicketType.WEEKENDS_AND_PH);
                        basePrice += 5.0f;
                        break;

                    default:
                        System.out.println("Invalid choice! Please try again!");
                }
            } while (choice < 1 || choice > 5);

            // Getting seat type
            do {
                System.out.println("What is the type of seat? ");
                System.out.println("1) Elite Seat\n2) Ultima Seat\n");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        ticketDetails.setSeatType(SeatType.ELITE_SEAT);
                        break;

                    case 2:
                        ticketDetails.setSeatType(SeatType.ULTIMA_SEAT);
                        basePrice += 10.0f;
                        break;

                    default:
                        System.out.println("Invalid choice! Please try again!");
                }
            } while (choice < 1 || choice > 2);

            // Getting age bracket
            do {
                System.out.println("What is the age bracket? ");
                System.out.println("1) Student\n2) Child\n3) Adult\n");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        ticketDetails.setAgeBracket(AgeBracket.STUDENT);
                        break;

                    case 2:
                        ticketDetails.setAgeBracket(AgeBracket.CHILD);
                        break;

                    case 3:
                        ticketDetails.setAgeBracket(AgeBracket.ADULT);
                        break;

                    default:
                        System.out.println("Invalid choice! Please try again!");
                }
            } while (choice < 1 || choice > 3);
        }


        // Getting the price of ticket
        System.out.println("The price of the ticket is " + basePrice);
        ticketDetails.setPrice(basePrice);

        // Adding the ticket into the database
        originalTicketDetailsDB.add(ticketDetails);
        System.out.println("Ticket details successfully added into the database!");
        return originalTicketDetailsDB;
    }

    /**
     * Method to automatically add ticket details to stored list.
     * @param originalTicketDetailsDB Tickets stored.
     * @param movieType Movie type.
     * @param ticketType Ticket type.
     * @param seatType Seat type.
     * @param ageBracket Age bracket.
     * @param cinemaClass Cinema class.
     * @return ArrayList of stored ticket details.
     */
    public ArrayList<TicketDetails> autoAddTicketDetails (ArrayList<TicketDetails> originalTicketDetailsDB, MovieType movieType, TicketType ticketType, SeatType seatType, AgeBracket ageBracket, CinemaClass cinemaClass) {
        float basePrice = 0;

        TicketDetails ticketDetails = new TicketDetails();

        // Getting cinema class
        if (cinemaClass == CinemaClass.NORMAL) {
            ticketDetails.setCinemaClass(CinemaClass.NORMAL);
            basePrice = 8.5f;
        } else if (cinemaClass == CinemaClass.PLATINUM) {
            ticketDetails.setCinemaClass(CinemaClass.PLATINUM);
            basePrice = 28f;
        } else {
            System.out.println("Invalid choice! Please try again!");
        }

        // Normal class tickets
        if (ticketDetails.cinemaClass == CinemaClass.NORMAL) {

            // Getting movie type
            if (movieType == MovieType.DIGITAL) {
                ticketDetails.setMovieType(MovieType.DIGITAL);
            }

            else if (movieType == MovieType.BLOCKBUSTER) {
                ticketDetails.setMovieType(MovieType.BLOCKBUSTER);
                basePrice++;
            }

            else if (movieType == MovieType.THREE_D) {
                ticketDetails.setMovieType(MovieType.THREE_D);
                basePrice += 3.5f;
            }

            else if (movieType == MovieType.EXCLUSIVE) {
                ticketDetails.setMovieType(MovieType.EXCLUSIVE);
                basePrice += 3.5f;
            }

            else if (movieType == MovieType.IMAX_THREE_D) {
                ticketDetails.setMovieType(MovieType.IMAX_THREE_D);
                basePrice += 5.0f;
            }

            else {
                System.out.println("Invalid choice! Please try again!");
            }

            // Getting ticket type
            if (ticketType == TicketType.MON_TO_WED) {
                ticketDetails.setTicketType(TicketType.MON_TO_WED);
            }

            else if (ticketType == TicketType.THURS) {
                ticketDetails.setTicketType(TicketType.THURS);
                basePrice++;
            }

            else if (ticketType == TicketType.FRI_BEFORE_6PM) {
                ticketDetails.setTicketType(TicketType.FRI_BEFORE_6PM);
                basePrice++;
            }

            else if (ticketType == TicketType.FRI_AFTER_6PM) {
                ticketDetails.setTicketType(TicketType.FRI_AFTER_6PM);
                basePrice += 2.5f;
            }

            else if (ticketType == TicketType.WEEKENDS_AND_PH) {
                ticketDetails.setTicketType(TicketType.WEEKENDS_AND_PH);
                basePrice += 2.5f;
            }

            else {
                System.out.println("Invalid choice! Please try again!");
            }

            // Getting seat type
            if (seatType == SeatType.NORMAL_SEAT) {
                ticketDetails.setSeatType(SeatType.NORMAL_SEAT);
            }

            else if (seatType == SeatType.COUPLE_SEAT) {
                ticketDetails.setSeatType(SeatType.COUPLE_SEAT);
                basePrice*=2;
            }

            else {
                System.out.println("Invalid choice! Please try again!");
            }

            // Getting age bracket
            if (ageBracket == AgeBracket.STUDENT) {
                ticketDetails.setAgeBracket(AgeBracket.STUDENT);
                basePrice *= 0.7f;
            }

            else if (ageBracket == AgeBracket.CHILD) {
                ticketDetails.setAgeBracket(AgeBracket.CHILD);
                basePrice *= 0.5f;
            }

            else if (ageBracket == AgeBracket.ADULT) {
                ticketDetails.setAgeBracket(AgeBracket.ADULT);
            }

            else if (ageBracket == AgeBracket.SENIOR_CTZ) {
                ticketDetails.setAgeBracket(AgeBracket.SENIOR_CTZ);
                basePrice *= 0.4f;
            }

            else {
                System.out.println("Invalid choice! Please try again!");
            }
        }

        // Platinum class tickets
        else if (ticketDetails.cinemaClass == CinemaClass.PLATINUM) {

            // Getting movie type
            if (movieType == MovieType.DIGITAL) {
                ticketDetails.setMovieType(MovieType.DIGITAL);
            } else if (movieType == MovieType.BLOCKBUSTER) {
                ticketDetails.setMovieType(MovieType.BLOCKBUSTER);
                basePrice += 5.0f;
            } else if (movieType == MovieType.EXCLUSIVE) {
                ticketDetails.setMovieType(MovieType.EXCLUSIVE);
                basePrice += 3.5f;
            } else {
                System.out.println("Invalid choice! Please try again!");
            }

            // Getting ticket type
            if (ticketType == TicketType.MON_TO_WED) {
                ticketDetails.setTicketType(TicketType.MON_TO_WED);
            } else if (ticketType == TicketType.THURS) {
                ticketDetails.setTicketType(TicketType.THURS);
            } else if (ticketType == TicketType.FRI_BEFORE_6PM) {
                ticketDetails.setTicketType(TicketType.FRI_BEFORE_6PM);
                basePrice += 5.0f;
            } else if (ticketType == TicketType.FRI_AFTER_6PM) {
                ticketDetails.setTicketType(TicketType.FRI_AFTER_6PM);
                basePrice += 5.0f;
            } else if (ticketType == TicketType.WEEKENDS_AND_PH) {
                ticketDetails.setTicketType(TicketType.WEEKENDS_AND_PH);
                basePrice += 5.0f;
            } else {
                System.out.println("Invalid choice! Please try again!");
            }

            // Getting seat type
            if (seatType == SeatType.ELITE_SEAT) {
                ticketDetails.setSeatType(SeatType.ELITE_SEAT);
            }

            else if (seatType == SeatType.ULTIMA_SEAT) {
                ticketDetails.setSeatType(SeatType.ULTIMA_SEAT);
                basePrice *=2;
            }

            else {
                System.out.println("Invalid choice! Please try again!");
            }

            // Getting age bracket
            if (ageBracket == AgeBracket.STUDENT) {
                ticketDetails.setAgeBracket(AgeBracket.STUDENT);
            }

            else if (ageBracket == AgeBracket.CHILD) {
                ticketDetails.setAgeBracket(AgeBracket.CHILD);
            }

            else if (ageBracket == AgeBracket.ADULT) {
                ticketDetails.setAgeBracket(AgeBracket.ADULT);
            }

            else {
                System.out.println("Invalid choice! Please try again!");
            }
        }

        // Printing out the price of ticket
        //System.out.println("The price of the ticket is " + basePrice);
        ticketDetails.setPrice(basePrice);

        // Adding the ticket into the database
        originalTicketDetailsDB.add(ticketDetails);
        //System.out.println("Ticket details successfully added into the database!");
        return originalTicketDetailsDB;
    }
}
