package ir.co.isc.academy.g1.connectfourgame;

/**
 *
 * @author FarzanMoradi
 */

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class OutputTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private GameController testGameController;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        testGameController = new GameController();
        testGameController.addPlayer(PlayerFactory.getInstance(BoardDisc.RED,"Player1"));
        testGameController.addPlayer(PlayerFactory.getInstance(BoardDisc.GREEN,"Player2"));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void whenAskedForCurrentPlayerTheOutputNoticeRed() {
        final String ANSI_RESET  = "\u001B[0m";
        final String ANSI_RED    = "\u001B[31m";

        testGameController.nextPlayerTurn();
        Player currentPlayer = testGameController.getPlayerTurn();
        testGameController.playerNotice(currentPlayer);

        Assert.assertEquals("Player1" + ANSI_RED + " Player R" + ANSI_RESET + " turn, Choose a column: ", outContent.toString());
    }

    @Test
    public void whenAskedForCurrentPlayerTheOutputNoticeGreen() {
        final String ANSI_RESET  = "\u001B[0m";
        final String ANSI_GREEN  = "\u001B[32m";

        testGameController.nextPlayerTurn();
        testGameController.nextPlayerTurn();
        Player currentPlayer = testGameController.getPlayerTurn();
        testGameController.playerNotice(currentPlayer);

        Assert.assertEquals("Player2" + ANSI_GREEN + " Player G" + ANSI_RESET + " turn, Choose a column: ", outContent.toString());
    }

    @Test
    public void whenADiscIsIntroducedTheBoardIsPrinted() {
        final String ANSI_RESET  = "\u001B[0m";
        final String ANSI_RED    = "\u001B[31m";

        GameBoard testBoard = new GameBoard();
        BoardDisc testDisk = BoardDisc.RED;
        int column = 1;
        testBoard.putDiscInColumn(testDisk,column - 1 );
        testBoard.printBoard();

        String testOutput = "  1   2   3   4   5   6   7" + "\n" +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                "|\n" +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                "|\n" +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                "|\n" +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                "|\n" +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                "|\n" +
                ANSI_RESET + "| " + ANSI_RED + "R" + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                ANSI_RESET + "| " + ANSI_RESET + " " + ANSI_RESET + " " +
                "|\n"
                ;

        Assert.assertEquals(testOutput.replaceAll("\n", "").replaceAll("\r", ""), outContent.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }

}