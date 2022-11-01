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
    public String[][] seatCreation (int rows, int cols) {
        String blankSeat = "|_|";
        String staircase = "|=|";

        String [][] seatLayout = new String[rows][cols + 2];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols + 2; j++) {
                if (j == cols / 2 || j == (cols / 2) + 1) {
                    seatLayout[i][j] = staircase;
                } else {
                    seatLayout[i][j] = blankSeat;
                }
            }
        }

        return seatLayout;
    }

    public void seatsPrinting (String[][] seatLayout, int rows, int cols) {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols + 2; j++) {
                System.out.print(seatLayout[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void selectSeat (String[][] seatLayout, int rows, int cols, String seatSelection) {
        int rowNo = seatSelection.charAt(0) - 65;
        int colNo = seatSelection.charAt(1) - 48 - 1;

        //Seat selectedSeat = new Seat ()

        if (colNo >= cols / 2) {
            colNo += 2;
        }

        String selectedSeat = "|S|";
        seatLayout[rowNo][colNo] = selectedSeat;
        seatsPrinting(seatLayout, rows, cols);

        //return seatLayout;
    }

    public String[][] seatPurchased (String[][] seatLayout, int rows, int cols, String seatSelection) {
        int rowNo = seatSelection.charAt(0) - 65;
        int colNo = seatSelection.charAt(1) - 48 - 1;

        if (colNo >= cols / 2) {
            colNo += 2;
        }

        String bookedSeat = "|X|";
        seatLayout[rowNo][colNo] = bookedSeat;
        seatsPrinting(seatLayout, rows, cols);

        return seatLayout;
    }

    public void selectSeat () {
        this.seatSelected = true;
        System.out.println("You have selected " + this.seatID + ". Thank you!");
    }

    public void occupySeat () {
        this.seatOccupied = true;
        System.out.println("You have successfully purchased " + this.seatID + ". Thank you for your purchase!");
    }
}
