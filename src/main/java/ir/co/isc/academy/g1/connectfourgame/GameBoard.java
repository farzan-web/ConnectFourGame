package ir.co.isc.academy.g1.connectfourgame;

/**
 *
 * @author FarzanMoradi
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class GameBoard {
    private final static Logger LOGGER = LoggerFactory.getLogger(GameBoard.class);

    private BoardDisc[][] board;
    private int rowCount = 6;
    private int columnCount = 7;

    public GameBoard() {
        LOGGER.info("GameBoard created with NODISK in it");
        board = new BoardDisc[rowCount][columnCount];
        startGameNoDiskInBoard();
    }

    private void startGameNoDiskInBoard() {
        for (BoardDisc[] row: board)
            Arrays.fill(row, BoardDisc.NODISK);
    }

    public BoardDisc getBoard(int row, int column) {
        return board[row][column];
    }

    public void putDiscInColumn(BoardDisc playerDisc, int column) {
        try{
            if(isColumnFull(column)) {
                throw new RuntimeException();
            }else {
                int diskCount = getNumberOfDiscsInColumn(column);
                int lastOldDiscRow = (columnCount-1) - diskCount;
                int newDiskRow = lastOldDiscRow - 1;
                board[newDiskRow][column] = playerDisc;
            }
            LOGGER.info("Successfully putDiskInColumn");
        }catch(RuntimeException e){
            String message;
            if(column > 0 && column <= columnCount){
                message = "No more room in column " + column;
                LOGGER.warn("Player with "+ playerDisc + " disk tried to put disk in full column");
            }else{
                message = "Invalid column " + column;
                LOGGER.warn("Player with "+ playerDisc + " select Invalid column number");
            }
            throw new RuntimeException(message, e);
        }
    }

    public boolean isColumnFull(int column) {
        LOGGER.info("Check if column " + column + " is Full");
        int diskCount = getNumberOfDiscsInColumn(column);
        return diskCount == rowCount ? true : false;
    }

    public int getNumberOfDiscsInColumn(int column){
        LOGGER.info("Get number of disks in column " + column);
        try {
            int result = 0;
            for (int row = rowCount - 1; row >= 0; row--) {
                if (board[row][column] != BoardDisc.NODISK) {
                    result++;
                } else {
                    break;
                }
            }
            return result;
        }catch(RuntimeException e){
            LOGGER.warn("Invalid column selected: " + column);
            String message = "Invalid column " + column;
            throw e;
        }
    }

    public void printBoard() {
        LOGGER.info("Print game board");
        final String ANSI_RESET  = "\u001B[0m";
        final String ANSI_RED    = "\u001B[31m";
        final String ANSI_GREEN  = "\u001B[32m";
        String textColor  = ANSI_RESET;

        System.out.println("  1   2   3   4   5   6   7");
        for (int row=0; row<rowCount; row++){
            for (int column=0; column<columnCount; column++){
                textColor = board[row][column] == BoardDisc.RED ? ANSI_RED : textColor;
                textColor = board[row][column] == BoardDisc.GREEN ? ANSI_GREEN : textColor;
                textColor = board[row][column] == BoardDisc.NODISK ? ANSI_RESET : textColor;


                System.out.printf(ANSI_RESET + "| " + textColor + board[row][column].label + ANSI_RESET + " ");
            }
            System.out.printf("|\n");
        }
    }

    public int getNumberOfDiscsInBoard(){
        LOGGER.info("Get number of disks in board");
        int numberOfDisks = 0;
        for (int column=0; column<columnCount; column++){
            numberOfDisks += getNumberOfDiscsInColumn(column);
        }
        return numberOfDisks;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }
}
