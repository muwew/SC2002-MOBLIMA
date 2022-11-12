package MOBLIMA.src.model;

import MOBLIMA.src.enums.CinemaClass;
import MOBLIMA.src.enums.Cineplex;
import MOBLIMA.src.enums.SeatType;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Class to store details about the cinema.
 */
public class Cinema implements Serializable {
    /**
     * Seat layout in cinema.
     */
    private Seat[][] seatLayout;

    /**
     * Seat layout in cinema represented in String.
     */
    private String[][] stringSeatLayout;

    /**
     * Number of rows of seats.
     */
    private int noOfRows;

    /**
     * Number of columns of seats.
     */
    private int noOfCols;

    /**
     * Class of cinema.
     */
    private CinemaClass cinemaClass;

    /**
     * Cinema's code.
     */
    private String cinemaCode;

    /**
     * Cineplex that this cinema is in.
     */
    private Cineplex cineplex;

    /**
     * Cinema number.
     */
    private int cinemaNo;

    // Constructor

    /**
     * Constructor.
     */
    public Cinema () {
        this(CinemaClass.NORMAL, Cineplex.WEST_MALL, -1);
        this.seatLayout = null;
        this.stringSeatLayout = null;
        if(cinemaClass.equals(CinemaClass.NORMAL)){
            this.noOfRows = 10;
            this.noOfCols = 12;
        }
        else{
            this.noOfRows = 5;
            this.noOfCols = 8;
        }
        this.cinemaCode = (cineplex.toCode() + cinemaNo);
    }

    public Cinema (CinemaClass cinemaClass, Cineplex cineplex, int cinemaNo) {
        this.seatLayout = null;
        this.stringSeatLayout = null;
        this.cinemaClass = cinemaClass;
        if(cinemaClass.equals(CinemaClass.NORMAL)){
            this.noOfRows = 10;
            this.noOfCols = 12;
        }
        else{
            this.noOfRows = 5;
            this.noOfCols = 8;
        }
        this.cinemaCode = (cineplex.toCode() + cinemaNo);
        this.cineplex = cineplex;
        this.cinemaNo = cinemaNo;
    }

    // Getters and setters

    /**
     * Gets the seat layout.
     * @return 2D list of seats representing the layout.
     */
    public Seat[][] getSeatLayout() {
        return seatLayout;
    }

    /**
     * Sets the seat layout.
     * @param seatLayout Seat layout.
     */
    public void setSeatLayout(Seat[][] seatLayout) {
        this.seatLayout = seatLayout;
    }

    /**
     * Gets the seat layout represented in String.
     * @return Seat layout in String.
     */
    public String[][] getStringSeatLayout() {
        return stringSeatLayout;
    }

    /**
     * Sets the seat layout.
     * @param stringSeatLayout Seat layout in string.
     */
    public void setStringSeatLayout(String[][] stringSeatLayout) {
        this.stringSeatLayout = stringSeatLayout;
    }

    /**
     * Gets number of rows.
     * @return Number of rows.
     */
    public int getNoOfRows() {
        return noOfRows;
    }

    /**
     * Sets number of rows.
     * @param noOfRows Number of rows.
     */
    public void setNoOfRows(int noOfRows) {
        this.noOfRows = noOfRows;
    }

    /**
     * Gets number of columns.
     * @return Number of columns.
     */
    public int getNoOfCols() {
        return noOfCols;
    }

    /**
     * Sets number of columns.
     * @param noOfCols Number of columns.
     */
    public void setNoOfCols(int noOfCols) {
        this.noOfCols = noOfCols;
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
     */
    public void setCinemaClass() {
        System.out.println("Enter cinema class:");
        System.out.println("1)NORMAL 2)PLATINUM");
        Scanner sc = new Scanner(System.in);

        int choice = sc.nextInt();

        if(choice == 1) {
            this.cinemaClass = CinemaClass.NORMAL;
            this.noOfRows = 10;
            this.noOfCols = 12;
        }
        else {
            this.cinemaClass = CinemaClass.PLATINUM;
            this.noOfRows = 5;
            this.noOfCols = 8;
        }
    }

    /**
     * Gets cineplex cinema is in.
     * @return Cineplex.
     */
    public Cineplex getCineplex() {
        return cineplex;
    }

    /**
     * Sets cineplex cinema is in.
     */
    public void setCineplex() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter location:\n1)JEM 2)WEST_MALL 3)JURONG_POINT");
        int choice = sc.nextInt();

        if(choice == 1)
            this.cineplex = Cineplex.JEM;
        else if(choice == 2)
            this.cineplex = Cineplex.WEST_MALL;
        else
            this.cineplex = Cineplex.JURONG_POINT;
    }

    /**
     * Sets cinema number.
     * @param cinemaNo Cinema number.
     */
    public void setCinemaNo(int cinemaNo){
        this.cinemaNo = cinemaNo;
    }

    /**
     * Gets cinema number.
     * @return Cinema number.
     */
    public int getCinemaNo(){return cinemaNo;}

    /**
     * Gets cinema code.
     * @return Cinema code.
     */
    public String getCinemaCode() {
        return cinemaCode;
    }

    /**
     * Sets cinema code.
     * @param cinemaCode Cinema code.
     */
    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }

    // Methods

    /**
     * Creates a string representation of seat layout.
     * @param rows Number of rows.
     * @param cols Number of columns.
     * @return String representation of seat layout.
     */
    public String[][] seatLayoutCreation (int rows, int cols) {
        String blankSeat = "|_|";
        String staircase = "|=|";
        String coupleSeatFirstHalf = "|_ ";
        String coupleSeatSecondHalf = " _|";
        String eliteSeat = "|E|";
        char rowChar = 65;

        this.seatLayout = new Seat[rows + 1][cols + 2];
        String[][] newSeatLayout = new String[rows + 1][cols + 2];

        // If the cinema is normal class, then the seats are normal, and the seats at the back are couple seats
        if (this.cinemaClass == CinemaClass.NORMAL) {

            // Printing out the normal seats
            for (int i = 0; i < rows - 2; i++) {
                for (int j = 0; j < cols + 2; j++) {
                    if (j == cols / 2 || j == (cols / 2) + 1) {
                        newSeatLayout[i][j] = staircase;
                        this.seatLayout[i][j] = null;
                    } else {
                        newSeatLayout[i][j] = blankSeat;
                        String seatNoString = null;
                        if (j < cols / 2) {
                            seatNoString = Integer.toString(j + 1);
                        } else {
                            seatNoString = Integer.toString(j - 1);
                        }
                        this.seatLayout[i][j] = new Seat((rowChar + seatNoString), SeatType.NORMAL_SEAT, false, false);
                    }
                }
                rowChar++;
            }

            // Printing out the couple seats
            for (int i = rows - 2; i < rows + 1; i++) {
                for (int j = 0; j < cols + 2; j++) {
                    // Empty row after normal seats
                    if (i == rows - 2) {
                        newSeatLayout[i][j] = " ";
                        this.seatLayout[i][j] = null;
                    }

                    // Staircase columns
                    else if (j == cols / 2 || j == (cols / 2) + 1) {
                        newSeatLayout[i][j] = staircase;
                        this.seatLayout[i][j] = null;
                    }

                    // Printing out the couple seats
                    else if (j % 2 == 0) {
                        newSeatLayout[i][j] = coupleSeatFirstHalf;

                        String seatNoString;
                        if (j < cols / 2) {
                            seatNoString = Integer.toString(j + 1);
                        } else {
                            seatNoString = Integer.toString(j - 1);
                        }

                        this.seatLayout[i][j] = new Seat((rowChar + seatNoString), SeatType.COUPLE_SEAT, false, false);
                    } else {
                        newSeatLayout[i][j] = coupleSeatSecondHalf;

                        String seatNoString;
                        if (j < cols / 2) {
                            seatNoString = Integer.toString(j + 1);
                        } else {
                            seatNoString = Integer.toString(j - 1);
                        }

                        this.seatLayout[i][j] = new Seat((rowChar + seatNoString), SeatType.COUPLE_SEAT, false, false);
                    }
                }
            }
        }

        // If the cinema is platinum suites, then the normal seats are elite seats and the couple seats are ultima seats
        else {

            // Printing out the elite seats
            for (int i = 0; i < rows - 2; i++) {
                for (int j = 0; j < cols + 2; j++) {
                    if (j == cols / 2 || j == (cols / 2) + 1) {
                        newSeatLayout[i][j] = staircase;

                        this.seatLayout[i][j] = null;
                    } else {
                        newSeatLayout[i][j] = eliteSeat;

                        String seatNoString = null;
                        if (j < cols / 2) {
                            seatNoString = Integer.toString(j + 1);
                        } else {
                            seatNoString = Integer.toString(j - 1);
                        }
                        this.seatLayout[i][j] = new Seat((rowChar + seatNoString), SeatType.ELITE_SEAT, false, false);
                    }
                }
                rowChar++;
            }

            // Printing out the ultima seats
            for (int i = rows - 2; i < rows + 1; i++) {
                for (int j = 0; j < cols + 2; j++) {

                    // Empty row after normal seats
                    if (i == rows - 2) {
                        newSeatLayout[i][j] = " ";

                        this.seatLayout[i][j] = null;
                    }

                    // Staircase columns
                    else if (j == cols / 2 || j == (cols / 2) + 1) {
                        newSeatLayout[i][j] = staircase;

                        this.seatLayout[i][j] = null;
                    }

                    // Printing out the ultima seats
                    else if (j % 2 == 0) {
                        newSeatLayout[i][j] = coupleSeatFirstHalf;

                        String seatNoString;
                        if (j < cols / 2) {
                            seatNoString = Integer.toString(j + 1);
                        } else {
                            seatNoString = Integer.toString(j - 1);
                        }

                        this.seatLayout[i][j] = new Seat((rowChar + seatNoString), SeatType.ULTIMA_SEAT, false, false);
                    } else {
                        newSeatLayout[i][j] = coupleSeatSecondHalf;

                        String seatNoString;
                        if (j < cols / 2) {
                            seatNoString = Integer.toString(j + 1);
                        } else {
                            seatNoString = Integer.toString(j - 1);
                        }

                        this.seatLayout[i][j] = new Seat((rowChar + seatNoString), SeatType.ULTIMA_SEAT, false, false);
                    }
                }
            }
        }

        return newSeatLayout;
    }

    /**
     * Method to display the seat layout.
     * @param seatLayout Seat layout.
     * @param rows Number of rows.
     * @param cols Number of columns.
     */
    public void seatsPrinting (String[][] seatLayout, int rows, int cols) {
        // Distinguish between normal and platinum suites
        if (this.cinemaClass == CinemaClass.PLATINUM) {
            System.out.println("PLATINUM SUITES SEAT SELECTION:");
            System.out.println();
        }

        char rowChar = 65;

        // Printing out the column numbers
        System.out.print("\t");
        for (int i = 0; i < cols + 2; i++) {
            if (i == (cols / 2) || i == (cols / 2) + 1) {
                System.out.print("   ");
            } else if (i > (cols / 2) + 1) {
                if (i >= 11) {
                    System.out.print((i - 1) + " ");
                } else {
                    System.out.print(" " + (i - 1) + " ");
                }
            } else {
                if (i >= 9) {
                    System.out.print((i + 1) + " ");
                } else {
                    System.out.print(" " + (i + 1) + " ");
                }
            }
        }

        System.out.println();

        for (int i = 0; i < rows + 1; i++) {
            if (i == rows - 2) {
                for (int j = 0; j < cols + 2; j++) {
                    System.out.print(seatLayout[i][j] /*+ " "*/);
                }
                System.out.println();
            }
            else {
                System.out.print(rowChar + "   ");
                for (int j = 0; j < cols + 2; j++) {
                    System.out.print(seatLayout[i][j] /*+ " "*/);
                }
                System.out.println();
                rowChar++;
            }
        }
    }
}
