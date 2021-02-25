package ir.co.isc.academy.g1.connectfourgame;

/**
 *
 * @author FarzanMoradi
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {
    private final static Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    private GameBoard gameBoard;
    private List<Player> players ;
    private Player playerTurn;
    private GameState gameState;
    private WinType winType;
    private int diskInLineCountToWin = 4;

    public GameController() {
        LOGGER.info("GameController initialize");
        players = new ArrayList<>();
        gameState = GameState.PLAYING;
    }

    public void mainGame() {
        LOGGER.info("MainGame started");
        Scanner in = new Scanner(System.in);

        newGame();
        playerTurn = players.get(players.size()-1);
        printBoard();

        while (GameState.PLAYING == gameState){
            nextPlayerTurn();

            playerNotice(playerTurn);

            int playerColumnSelect;


            while (true) {
                LOGGER.info("Get column number from player " + playerTurn.getDisk());

                try {
                    playerColumnSelect = in.nextInt();
                }catch (Exception e){
                    LOGGER.warn("player " + playerTurn.getDisk() + " entered none integer input");
                    System.out.printf("Please enter a integer number");
                    in = new Scanner(System.in);
                    continue;
                }

                if (playerColumnSelect > 0 && playerColumnSelect <= gameBoard.getColumnCount()) {

                    int arrayColumn = playerColumnSelect - 1;
                    if(gameBoard.isColumnFull(arrayColumn)){
                        LOGGER.warn("player " + playerTurn.getDisk() + " selected a full column");
                        System.out.printf("This Column is Full, Please choose another Column: ");
                        continue;
                    }
                    break;
                } else {
                    LOGGER.warn("player " + playerTurn.getDisk() + " entered a number out of [1, " + gameBoard.getColumnCount() +"]");
                    System.out.printf("Please enter a valid number between 1 to " + gameBoard.getColumnCount() + ": ");
                }
            }

            int arrayColumn = playerColumnSelect - 1;
            gameBoard.putDiscInColumn(playerTurn.getDisk(), arrayColumn);

            printBoard();
            isGameEnded();
        }

        if(GameState.TIE == gameState){
            System.out.println("Game is Tie.");
        }else if(GameState.WINNER == gameState){
            System.out.println(playerTurn.playerIntroduce() + " is this game winner");
        }else{
            System.out.println("Game force closed");
        }

    }

    public void playerNotice(Player currentPlayer) {
        System.out.printf( currentPlayer.playerIntroduce() + " turn, Choose a column: ");
    }

    public void isGameEnded(){
        LOGGER.info("Check if game is ended");
        isGameTie();
        isSomeoneWon();
    }

    private void isGameTie() {
        LOGGER.info("Check if game is Tie");
        boolean result = true;
        for (int column=0; column<gameBoard.getColumnCount(); column++){
            if(!gameBoard.isColumnFull(column)){
                result = false;
                break;
            }
        }
        if(result == true){
            LOGGER.info("Game is Tie");
            gameState = GameState.TIE;
        }
    }

    private void isSomeoneWon() {
        LOGGER.info("Check if a player won the game");
        checkRowWinner();
        checkColumnWinner();
        checkMaindiagonalWinner();
        checkAntidiagonalWinner();
    }

    private void checkColumnWinner() {
        LOGGER.info("Check if " + diskInLineCountToWin + " disks are in a column line");
        int sameDiskInLineCount;

        for (int column=0; column<gameBoard.getColumnCount(); column++){
            sameDiskInLineCount = 1;

            for (int row=0; row<gameBoard.getRowCount() - 1; row++){
                if(gameBoard.getBoard(row, column) == BoardDisc.NODISK) {
                    sameDiskInLineCount = 1;
                }else{
                    if(gameBoard.getBoard(row, column) == gameBoard.getBoard(row + 1, column)){
                        sameDiskInLineCount++;
                    }else{
                        sameDiskInLineCount = 1;
                    }
                }
            }

            if(sameDiskInLineCount >= diskInLineCountToWin){
                LOGGER.info("Game ends with " + diskInLineCountToWin + " disks in a column line");
                gameState = GameState.WINNER;
                winType = WinType.COLUMN_WINNER;
                System.out.println(winType + ": " +diskInLineCountToWin +" disks matched in Column");
                break;
            }
        }
    }

    private void checkRowWinner() {
        LOGGER.info("Check if " + diskInLineCountToWin + " disks are in a row line");
        int sameDiskInLineCount;

        for (int row=0; row<gameBoard.getRowCount(); row++){
            sameDiskInLineCount = 1;

            for (int column=0; column<gameBoard.getColumnCount() - 1; column++){
                if(gameBoard.getBoard(row, column) == BoardDisc.NODISK) {
                    sameDiskInLineCount = 1;
                }else{
                    if(gameBoard.getBoard(row, column) == gameBoard.getBoard(row, column + 1)){
                        sameDiskInLineCount++;
                    }else{
                        sameDiskInLineCount = 1;
                    }
                }

                if(sameDiskInLineCount >= diskInLineCountToWin){
                    break;
                }
            }

            if(sameDiskInLineCount >= diskInLineCountToWin){
                LOGGER.info("Game ends with " + diskInLineCountToWin + " disks in a Row line");
                gameState = GameState.WINNER;
                winType = WinType.ROW_WINNER;
                System.out.println(winType + ": " +diskInLineCountToWin +" disks matched in Row");
                break;
            }
        }
    }

    private void checkMaindiagonalWinner() {
        LOGGER.info("Check if " + diskInLineCountToWin + " disks are in a main diagonal line");
        int sameDiskInLineCount;
        int numberOfDiagonalLines = gameBoard.getRowCount() + gameBoard.getRowCount() - 1;

        for( int diagonal = 1 ; diagonal < numberOfDiagonalLines - 1; diagonal++ ) {
            sameDiskInLineCount = 1;

            for( int column = 0 ; column <= diagonal ; column++ ) {
                int row = gameBoard.getRowCount() - 1 - (diagonal - column);

                if( row < gameBoard.getRowCount() - 1 && row >= 0 && column < gameBoard.getColumnCount() - 1) {
                    if(gameBoard.getBoard(row, column) == BoardDisc.NODISK) {
                        sameDiskInLineCount = 1;
                    }else{
                        if(gameBoard.getBoard(row, column) == gameBoard.getBoard(row + 1,column + 1)){
                            sameDiskInLineCount++;
                        }else{
                            sameDiskInLineCount = 1;
                        }
                    }
                }

                if(sameDiskInLineCount >= diskInLineCountToWin){
                    break;
                }
            }
            if(sameDiskInLineCount >= diskInLineCountToWin){
                LOGGER.info("Game ends with " + diskInLineCountToWin + " disks in a Main Diagonal line");
                gameState = GameState.WINNER;
                winType = WinType.MAIN_DIAGONAL_WINNER;
                System.out.println(winType + ": " +diskInLineCountToWin +" disks matched in Main Diagonal");
                break;
            }
        }

    }

    private void checkAntidiagonalWinner() {
        LOGGER.info("Check if " + diskInLineCountToWin + " disks are in a anti diagonal line");
        int sameDiskInLineCount;
        int numberOfDiagonalLines = gameBoard.getRowCount() + gameBoard.getRowCount() - 1;

        for( int diagonal = 1 ; diagonal < numberOfDiagonalLines - 1; diagonal++ ) {
            sameDiskInLineCount = 1;

            for( int column = 0 ; column <= diagonal ; column++ ) {
                int row = diagonal - column;

                if( row < gameBoard.getRowCount() && row > 0 && column < gameBoard.getColumnCount() - 1 ) {
                    if(gameBoard.getBoard(row, column) == BoardDisc.NODISK) {
                        sameDiskInLineCount = 1;
                    }else{
                        if(gameBoard.getBoard(row, column) == gameBoard.getBoard(row - 1, column + 1)){
                            sameDiskInLineCount++;
                        }else{
                            sameDiskInLineCount = 1;
                        }
                    }
                }

                if(sameDiskInLineCount >= diskInLineCountToWin){
                    break;
                }
            }
            if(sameDiskInLineCount >= diskInLineCountToWin){
                LOGGER.info("Game ends with " + diskInLineCountToWin + " disks in a Anti Diagonal line");
                gameState = GameState.WINNER;
                winType = WinType.ANTI_DIAGONAL_WINNER;
                System.out.println(winType + ": " +diskInLineCountToWin +" disks matched in Anti Diagonal");

                break;
            }
        }

    }

    public void nextPlayerTurn() {
        LOGGER.info("Set next player turn");
        int currentPlayerIndex = players.indexOf(playerTurn);
        int nextPlayerIndex = (currentPlayerIndex + 1) % players.size();
        playerTurn = players.get(nextPlayerIndex);

    }

    private void printBoard() {
        gameBoard.printBoard();
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void newGame() {
        gameState = GameState.PLAYING;
    }

    public Player getPlayerTurn() {
        return playerTurn;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public GameState getGameState() {
        return gameState;
    }

    public WinType getWinType() {
        return winType;
    }

}
