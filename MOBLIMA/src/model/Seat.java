package MOBLIMA.src.model;

import MOBLIMA.src.enums.SeatType;

import java.io.Serializable;

/**
 * Class that holds details on the seats.
 */
public class Seat implements Serializable {

    // Attributes
    /**
     * ID of seat. eg. A1
     */
    private String seatID;

    /**
     * Row number of seat.
     */
    private int seatRow;

    /**
     * Column number of seat.
     */
    private int seatCol;

    /**
     * Type of seat.
     */
    private SeatType seatType;

    /**
     * Whether seat is occupied.
     */
    private boolean seatOccupied;

    /**
     * Whether seat is selected.
     */
    private boolean seatSelected;

    // Constructor

    /**
     * Constructor.
     * @param seatID Seat ID.
     * @param seatType Type of seat.
     * @param seatOccupied Whether seat is occupied.
     * @param seatSelected Whether seat is selected.
     */
    public Seat (String seatID, SeatType seatType, boolean seatOccupied, boolean seatSelected) {
        this.seatID = seatID;
        this.seatRow = seatID.charAt(0) - 65;
        int colNo;
        if(seatID.length() == 2)
            colNo = seatID.charAt(1) - 48 - 1;
        else
            colNo = (seatID.charAt(1)-48) * 10 + (seatID.charAt(2) -48) - 1;

        this.seatCol = colNo;
        this.seatType = seatType;
        this.seatOccupied = false;
        this.seatSelected = false;
    }

    // Getters and setters

    /**
     * Gets seat ID.
     * @return Seat ID.
     */
    public String getSeatID() {
        return seatID;
    }

    /**
     * Sets seat ID.
     * @param seatID Seat ID.
     */
    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }

    /**
     * Gets seat row number.
     * @return Seat row number.
     */
    public int getSeatRow() {
        return this.seatRow;
    }

    /**
     * Sets seat row number.
     * @param seatRow Seat row number.
     */
    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    /**
     * Gets seat column number.
     * @return Seat column number.
     */
    public int getSeatCol() {
        return this.seatCol;
    }

    /**
     * Sets seat column number.
     * @param seatCol Seat column number.
     */
    public void setSeatCol(int seatCol) {
        this.seatCol = seatCol;
    }

    /**
     * Gets seat type.
     * @return Seat type.
     */
    public SeatType getSeatType() {
        return this.seatType;
    }

    /**
     * Sets seat type.
     * @param seatType Seat type.
     */
    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    /**
     * Checks if seat is occupied.
     * @return Whether seat is occupied.
     */
    public boolean isSeatOccupied() {
        return this.seatOccupied;
    }

    /**
     * Checks if seat is selected.
     * @return Whether seat is selected.
     */
    public boolean isSeatSelected() {
        return this.seatSelected;
    }

    // Methods
    /**
     * Method to select seat for ticket purchase.
     */
    public void selectSeat () {
        this.seatSelected = true;
        System.out.println("You have selected " + this.seatID + ". Thank you!");
    }

    /**
     * Method to deselect seat when ticket for seat cancelled.
     */
    public void deSelectSeat () {
        this.seatSelected = false;
        //System.out.println("You have de-selected " + this.seatID + ". Thank you!");
    }

    /**
     * Method to occupy seat when ticket for seat purchased.
     */
    public void occupySeat () {
        this.seatOccupied = true;
        System.out.println("You have successfully purchased " + this.seatID + ". Thank you for your purchase!");
    }

    /**
     * Method to print seat details.
     */
    public void printSeatDetails () {
        System.out.println("Seat ID: " + this.seatID);
        System.out.println("\tSeat Type: " + this.seatType.toString());
    }
}
