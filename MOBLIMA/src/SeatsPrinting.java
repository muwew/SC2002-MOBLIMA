package MOBLIMA.src;
import java.util.ArrayList;
import java.util.Scanner;

public class SeatsPrinting {
    public static void main(String[] args) {
        String[][] seatLayout = seatCreation(10, 12, true);
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
    }

    public static String[][] seatCreation (int rows, int cols, boolean isElite) {
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
                if (!isElite) {
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

    public static void seatsPrinting (String[][] seatLayout, int rows, int cols) {
        char row = 65;
        for (int i = 0; i < rows + 1; i++) {
            if (i == rows - 2) {
                for (int j = 0; j < cols + 2; j++) {
                    System.out.print(seatLayout[i][j] /*+ " "*/);
                }
                System.out.println();
            }
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

    public static String[][] seatSelection (String[][] seatLayout, int rows, int cols, String seatSelection) {
        int rowNo = seatSelection.charAt(0) - 65;
        int colNo = seatSelection.charAt(1) - 48 - 1;

        if (colNo >= cols / 2) {
            colNo += 2;
        }

        String selectedSeat = "|S|";
        seatLayout[rowNo][colNo] = selectedSeat;
        seatsPrinting(seatLayout, rows, cols);

        return seatLayout;
    }

    public static String[][] seatPurchased (String[][] seatLayout, int rows, int cols, String seatSelection) {
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
}