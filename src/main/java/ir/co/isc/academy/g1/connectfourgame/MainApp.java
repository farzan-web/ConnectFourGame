package ir.co.isc.academy.g1.connectfourgame;

/**
 *
 * @author FarzanMoradi
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class MainApp {
    private final static Logger LOGGER = LoggerFactory.getLogger(MainApp.class);
    private final static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        LOGGER.info("Start APP");

        GameController gameController = new GameController();
        LOGGER.info("Create GameController");

        System.out.println("Welcome to ConnectFour Game");

        System.out.printf("Player RED Please enter your Name: ");
        gameController.addPlayer(PlayerFactory.getInstance(BoardDisc.RED,getPlayerName()));
        LOGGER.info("Create and Add first Player");

        System.out.printf("Player GREEN Please enter your Name: ");
        gameController.addPlayer(PlayerFactory.getInstance(BoardDisc.GREEN,getPlayerName()));
        LOGGER.info("Create and Add second Player");

        boolean play;
        do {
            GameBoard gameBoard = new GameBoard();
            gameController.setGameBoard(gameBoard);
            LOGGER.info("Start New Game");
            gameController.mainGame();
            LOGGER.info("End Game");

            System.out.printf("Do you want play again (y/n)? ");
            play =  playAgain();

        }  while (play);

        LOGGER.info("End APP");
    }

    public static String getPlayerName(){
        String name = in.nextLine();
        return name;
    }

    public static boolean playAgain(){
        while (true){
            String answer = in.nextLine();
            if(answer.equalsIgnoreCase("y")){
                return true;
            }else if(answer.equalsIgnoreCase("n")){
                return false;
            }else{
                System.out.printf("Please enter y for 'yes' and n for 'no'  ");
            }
        }
    }
}
