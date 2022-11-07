package MOBLIMA.src;

import java.io.Serializable;
import java.util.Scanner;

public class Cinema implements Serializable {
    private Seat[][] seatLayout;
    private String[][] stringSeatLayout;
    private int noOfRows;
    private int noOfCols;
    private CinemaClass cinemaClass;
    private Cineplex cineplex;
    private String cinemaCode;
    private Location location;
    private int cinemaNo;

    // Constructor
    public Cinema () {
        this(CinemaClass.NORMAL, null, Location.WEST_MALL, -1);
        this.cineplex = new Cineplex();
    }

    public Cinema (CinemaClass cinemaClass, Cineplex cineplex, Location location, int cinemaNo) {
        this.seatLayout = null;
        this.stringSeatLayout = null;
        this.cinemaClass = cinemaClass;
        this.cineplex = cineplex;
        if(cinemaClass.equals(CinemaClass.NORMAL)){
            this.noOfRows = 10;
            this.noOfCols = 12;
        }
        else{
            this.noOfRows = 5;
            this.noOfCols = 8;
        }
        this.cinemaCode = (location.toCode() + cinemaNo);
        this.location = location;
        this.cinemaNo = cinemaNo;
    }

    // Getters and setters
    public Seat[][] getSeatLayout() {
        return seatLayout;
    }

    public void setSeatLayout(Seat[][] seatLayout) {
        this.seatLayout = seatLayout;
    }

    public String[][] getStringSeatLayout() {
        return stringSeatLayout;
    }

    public void setStringSeatLayout(String[][] stringSeatLayout) {
        this.stringSeatLayout = stringSeatLayout;
    }

    public int getNoOfRows() {
        return noOfRows;
    }

    public void setNoOfRows(int noOfRows) {
        this.noOfRows = noOfRows;
    }

    public int getNoOfCols() {
        return noOfCols;
    }

    public void setNoOfCols(int noOfCols) {
        this.noOfCols = noOfCols;
    }

    public CinemaClass getCinemaClass() {
        return cinemaClass;
    }

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

    public Location getLocation() {
        return location;
    }

    public void setLocation() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter location:\n1)JEM 2)WEST_MALL 3)JURONG_POINT");
        int choice = sc.nextInt();

        if(choice == 1)
            this.location = Location.JEM;
        else if(choice == 2)
            this.location = Location.WEST_MALL;
        else
            this.location = Location.JURONG_POINT;
    }
    public void setCinemaNo(int cinemaNo){
        this.cinemaNo = cinemaNo;
    }

    public int getCinemaNo(){return cinemaNo;}

    public Cineplex getCineplex() {
        return cineplex;
    }

    public void setCineplex(Cineplex cineplex) {
        this.cineplex = cineplex;
    }

    public String getCinemaCode() {
        return cinemaCode;
    }

    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }

    // Methods
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
