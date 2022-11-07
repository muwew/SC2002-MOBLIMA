package MOBLIMA.src;

import java.util.ArrayList;
import java.util.Scanner;

public class SeatsPrinting {
    public static void main(String[] args) {
        boolean isElite = false;
        String[][] seatLayout = seatCreation(10, 12, isElite);
        seatsPrinting(seatLayout, 10, 12);

        System.out.println();
        System.out.println();

//        Scanner sc = new Scanner(System.in);
//        System.out.println("Please enter your seat selection: ");
//        String seatSelection = sc.next();
//        String[][] updatedSeatLayout = seatSelection(seatLayout, 10, 12, seatSelection);
//        System.out.println();
//        System.out.println();
//        seatsPrinting(updatedSeatLayout, 10, 12);
//
//        System.out.println("Would you like to confirm your booking? ");
//        char confirmChar = sc.next().charAt(0);
//
//        if (confirmChar == 'y' || confirmChar == 'Y') {
//            updatedSeatLayout = seatPurchased(updatedSeatLayout, 10, 10, seatSelection);
//            System.out.println();
//            System.out.println();
//            seatsPrinting(updatedSeatLayout, 10, 12);
//        }

        String[][] finalSeatLayout = seatSelection(seatLayout, 10, 12, isElite);
        //seatsPrinting(finalSeatLayout, 10, 12);
    }

    public static String[][] seatCreation (int rows, int cols, boolean isElite) {
        String blankSeat = "|_|";
        String staircase = "|=|";
        String coupleSeatFirstHalf = "|_ ";
        String coupleSeatSecondHalf = " _|";
        String eliteSeat = "|E|";
        //char rowChar = 65;

        //this.seatLayout = new Seat[rows + 1][cols + 2];
        String [][] newSeatLayout = new String[rows + 1][cols + 2];

        for (int i = 0; i < rows - 2; i++) {
            for (int j = 0; j < cols + 2; j++) {
                if (j == cols / 2 || j == (cols / 2) + 1) {
                    newSeatLayout[i][j] = staircase;
                    //this.seatLayout[i][j] = null;
                } else {
                    newSeatLayout[i][j] = blankSeat;
//                    String seatNoString = null;
//                    if (j < cols / 2) {
//                        seatNoString = Integer.toString(j + 1);
//                    } else {
//                        seatNoString = Integer.toString(j - 1);
//                    }
//                    this.seatLayout[i][j] = new Seat((rowChar + seatNoString), SeatType.NORMAL_SEAT, false, false);
                }
            }
            //rowChar++;
        }

        for (int i = rows - 2; i < rows + 1; i++) {
            for (int j = 0; j < cols + 2; j++) {
                if (!isElite) {

                    // Empty row after normal seats
                    if (i == rows - 2) {
                        newSeatLayout[i][j] = " ";
                        //this.seatLayout[i][j] = null;
                    }

                    // Staircase columns
                    else if (j == cols / 2 || j == (cols / 2) + 1) {
                        newSeatLayout[i][j] = staircase;
                        //this.seatLayout[i][j] = null;
                    }

                    //
                    else if (j % 2 == 0) {
                        newSeatLayout[i][j] = coupleSeatFirstHalf;

//                        String seatNoString;
//                        if (j < cols / 2) {
//                            seatNoString = Integer.toString(j + 1);
//                        } else {
//                            seatNoString = Integer.toString(j - 1);
//                        }
//
//                        this.seatLayout[i][j] = new Seat((rowChar + seatNoString), SeatType.ULTIMA_SEAT, false, false);
                    } else {
                        newSeatLayout[i][j] = coupleSeatSecondHalf;

//                        String seatNoString;
//                        if (j < cols / 2) {
//                            seatNoString = Integer.toString(j + 1);
//                        } else {
//                            seatNoString = Integer.toString(j - 1);
//                        }
//
//                        this.seatLayout[i][j] = new Seat((rowChar + seatNoString), SeatType.ULTIMA_SEAT, false, false);

                    }
                } else {
                    if (i == rows - 2) {
                        newSeatLayout[i][j] = " ";
                        //this.seatLayout[i][j] = null;
                    } else if (j == cols / 2 || j == (cols / 2) + 1) {
                        newSeatLayout[i][j] = staircase;
                        //this.seatLayout[i][j] = null;
                    } else if (((j % 2 == 0) && (j < cols / 2)) || ((j % 2 == 1) && (j > (cols / 2) + 1))) {
                        newSeatLayout[i][j] = eliteSeat;

//                        String seatNoString;
//                        if (j < cols / 2) {
//                            seatNoString = Integer.toString(j + 1);
//                        } else {
//                            seatNoString = Integer.toString(j - 1);
//                        }
//
//                        this.seatLayout[i][j] = new Seat((rowChar + seatNoString), SeatType.ELITE_SEAT, false, false);
                    } else {
                        newSeatLayout[i][j] = "   ";
                        //this.seatLayout[i][j] = null;
                    }
                }
            }
        }

        return newSeatLayout;
    }

    public static void seatsPrinting (String[][] seatLayout, int rows, int cols) {
        char row = 65;

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

        // Printing out the seat arrangement
        for (int i = 0; i < rows + 1; i++) {
            // Empty row to walk
            if (i == rows - 2) {
                for (int j = 0; j < cols + 2; j++) {
                    System.out.print(seatLayout[i][j] /*+ " "*/);
                }
                System.out.println();
            }
            // Print out the rows with seats and its corresponding row letter
            else {
                System.out.print(row + "   ");
                for (int j = 0; j < cols + 2; j++) {
                    System.out.print(seatLayout[i][j] /*+ " "*/);
                }
                System.out.println();
                row++;
            }
        }
    }

    public static String[][] seatSelection (String[][] seatLayout, int rows, int cols, boolean isElite) {
        seatsPrinting(seatLayout, rows, cols);
        Scanner sc = new Scanner(System.in);

        System.out.println("Which seat would you like to select? ");
        String seatSelection = sc.next();

        int rowNo = seatSelection.charAt(0) - 65;
        int colNo = seatSelection.charAt(1) - 48 - 1;

        if (colNo >= cols / 2) {
            colNo += 2;
        }

        if (rowNo >= rows - 2) {
            rowNo++;
        }

        if (rowNo > rows - 2 && !isElite) {
            System.out.println("You have chosen an ultima seat. Hence, the seat beside the selected seat will automatically be chosen.");

            seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('_', 'S');
            if (colNo % 2 == 0) {
                seatLayout[rowNo][colNo + 1] = seatLayout[rowNo][colNo + 1].replace('_', 'S');
            } else {
                seatLayout[rowNo][colNo - 1] = seatLayout[rowNo][colNo - 1].replace('_', 'S');
            }
            seatsPrinting(seatLayout, rows, cols);
            System.out.println();
        } else if (rowNo > rows - 2 && isElite){
            System.out.println("You have chosen an elite seat.");

            seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('E', 'S');
            seatsPrinting(seatLayout, rows, cols);
            System.out.println();
        } else {
            System.out.println("You have chosen a normal seat.");

            seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('_', 'S');
            seatsPrinting(seatLayout, rows, cols);
            System.out.println();
        }

        String[][] updatedSeatLayout = seatPurchased(seatLayout, rows, cols, seatSelection, isElite);

//        if (this.cinema.getSeatLayout()[rowNo][colNo].isSeatSelected() || this.cinema.getSeatLayout()[rowNo][colNo].isSeatOccupied()) {
//            System.out.println("This seat is currently selected or occupied!");
//        } else if (this.cinema.getSeatLayout()[rowNo][colNo].getSeatType() == SeatType.ULTIMA_SEAT) {
//
//        }
//        else {
//            this.cinema.getSeatLayout()[rowNo][colNo].selectSeat();
//        }

        return updatedSeatLayout;
    }

    public static String[][] seatPurchased (String[][] seatLayout, int rows, int cols, String seatSelection, boolean isElite) {
        Scanner sc = new Scanner(System.in);

        int rowNo = seatSelection.charAt(0) - 65;
        int colNo = seatSelection.charAt(1) - 48 - 1;

        if (colNo >= cols / 2) {
            colNo += 2;
        }

        if (rowNo >= rows - 2) {
            rowNo++;
        }

        System.out.println("Would you like to confirm your purchase of seat " + seatSelection + "? Please type y or n.");
        char confirmation = sc.next().charAt(0);

        if (confirmation == 'y') {
            if (rowNo > rows - 2 && !isElite) {
                System.out.println("You have purchased an ultima seat. Hence, the seat beside the purchased seat will automatically be purchased. Thank you for your purchase!");

                seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('S', 'X');
                if (colNo % 2 == 0) {
                    seatLayout[rowNo][colNo + 1] = seatLayout[rowNo][colNo + 1].replace('S', 'X');
                } else {
                    seatLayout[rowNo][colNo - 1] = seatLayout[rowNo][colNo - 1].replace('S', 'X');
                }

            } else if (rowNo > rows - 2 && isElite){
                System.out.println("You have purchased an elite seat. Thank you for your purchase!");

                seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('S', 'X');

            } else {
                System.out.println("You have purchased a normal seat. Thank you for your purchase!");

                seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('S', 'X');

            }
        }

        else if (confirmation == 'n'){
            System.out.println("Purchase cancelled.");

            if (rowNo > rows - 2 && !isElite) {
                //System.out.println("You have purchased an ultima seat. Hence, the seat beside the purchased seat will automatically be purchased. Thank you for your purchase!");

                seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('S', '_');
                if (colNo % 2 == 0) {
                    seatLayout[rowNo][colNo + 1] = seatLayout[rowNo][colNo + 1].replace('S', '_');
                } else {
                    seatLayout[rowNo][colNo - 1] = seatLayout[rowNo][colNo - 1].replace('S', '_');
                }

            } else if (rowNo > rows - 2 && isElite){
                //System.out.println("You have purchased an elite seat. Thank you for your purchase!");

                seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('S', '_');

            } else {
                //System.out.println("You have purchased a normal seat. Thank you for your purchase!");

                seatLayout[rowNo][colNo] = seatLayout[rowNo][colNo].replace('S', '_');

            }
        }

        seatsPrinting(seatLayout, rows, cols);

        return seatLayout;
    }
}