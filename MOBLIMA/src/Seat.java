package MOBLIMA.src;

public class Seat {

    // Attributes
    private String seatID;
    private int seatRow;
    private int seatCol;
    private SeatType seatType;
    private boolean seatOccupied;
    private boolean seatSelected;

    // Constructor
    public Seat (String seatID, SeatType seatType, boolean seatOccupied, boolean seatSelected) {
        this.seatID = seatID;
        this.seatRow = seatID.charAt(0) - 65;
        this.seatCol = seatID.charAt(1) - 48 - 1;
        this.seatType = seatType;
        this.seatOccupied = false;
        this.seatSelected = false;
    }

    // Getters and setters
    public String getSeatID() {
        return seatID;
    }

    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }

    public int getSeatRow() {
        return this.seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatCol() {
        return this.seatCol;
    }

    public void setSeatCol(int seatCol) {
        this.seatCol = seatCol;
    }

    public SeatType getSeatType() {
        return this.seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public boolean isSeatOccupied() {
        return this.seatOccupied;
    }

    public boolean isSeatSelected() {
        return this.seatSelected;
    }

    // Methods
    public void selectSeat () {
        this.seatSelected = true;
        System.out.println("You have selected " + this.seatID + ". Thank you!");
    }

    public void deSelectSeat () {
        this.seatSelected = false;
        //System.out.println("You have de-selected " + this.seatID + ". Thank you!");
    }

    public void occupySeat () {
        this.seatOccupied = true;
        System.out.println("You have successfully purchased " + this.seatID + ". Thank you for your purchase!");
    }

    public void printSeatDetails () {
        System.out.println("Seat ID: " + this.seatID);
        System.out.println("\tSeat Type: " + this.seatType.toString());
    }
}
