package MOBLIMA.src;
import java.io.Serializable;
import java.util.*;

public class Cinema_old implements Serializable {
    private Seat[][] seatLayout;
    private String[][] stringSeatLayout;
    private int noOfRows;
    private int noOfCols;
    private boolean isElite;
    private CinemaClass cinemaClass;
    //private Cineplex cineplex;
    Location location;
    private int cinemaNo;
    private String cinemaCode;

    // Constructor
    public Cinema_old () {
        this(false, null, null, -1);
    }
    public Cinema_old(boolean isElite, CinemaClass cinemaClass, Location location, int cinemaNo){
        this.seatLayout = null;
        this.isElite = isElite;
        this.cinemaClass = cinemaClass;
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

    public boolean isElite() {
        return isElite;
    }

    public void setElite() {
        System.out.println("Seats are:\n1)NORMAL 2)ELITE");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if(choice == 1)
            isElite = false;
        else
            isElite = true;
    }

    public CinemaClass getCinemaClass() {
        return cinemaClass;
    }

    public void setCinemaClass() {
        System.out.println("Enter cinema class:");
        System.out.println("1)NORMAL 2)PLATINUM");
        Scanner sc = new Scanner(System.in);

        int choice = sc.nextInt();

        if(choice == 1)
            this.cinemaClass = CinemaClass.NORMAL;
        else
            this.cinemaClass = CinemaClass.PLATINUM;
    }

//    public Cineplex getCineplex() {
//        return cineplex;
//    }
//
//    public void setCineplex(Cineplex cineplex) {
//        this.cineplex = cineplex;
//    }

    public String getLocation() {
        return location.toString();
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

    // Methods
    public String[][] seatLayoutCreation (int rows, int cols) {
        String blankSeat = "|_|";
        String staircase = "|=|";
        String coupleSeat = "|_  _|";
        String eliteSeat = "|E|";

        String [][] seatLayout = new String[rows + 1][cols + 2];

        for (int i = 0; i < rows - 2; i++) {
            for (int j = 0; j < cols + 2; j++) {
                if (j == cols / 2 || j == (cols / 2) + 1) {
                    seatLayout[i][j] = staircase;
                } else {
                    seatLayout[i][j] = blankSeat;
                }
            }
        }

        for (int i = rows - 2; i < rows + 1; i++) {
            for (int j = 0; j < cols + 2; j++) {
                if (!this.isElite) {
                    if (i == rows - 2) {
                        seatLayout[i][j] = " ";
                    } else if (j == cols / 2 || j == (cols / 2) + 1) {
                        seatLayout[i][j] = staircase;
                    } else if (j % 2 == 0) {
                        seatLayout[i][j] = coupleSeat;
                    } else {
                        seatLayout[i][j] = "";
                    }
                } else {
                    if (i == rows - 2) {
                        seatLayout[i][j] = " ";
                    } else if (j == cols / 2 || j == (cols / 2) + 1) {
                        seatLayout[i][j] = staircase;
                    } else if (((j % 2 == 0) && (j < cols / 2)) || ((j % 2 == 1) && (j > (cols / 2) + 1))) {
                        seatLayout[i][j] = eliteSeat;
                    } else {
                        seatLayout[i][j] = "   ";
                    }
                }
            }
        }

        return seatLayout;
    }

    public void setCinemaNo(int cinemaNo){
        this.cinemaNo = cinemaNo;
    }
}
