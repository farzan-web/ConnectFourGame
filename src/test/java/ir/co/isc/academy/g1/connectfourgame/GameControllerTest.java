package ir.co.isc.academy.g1.connectfourgame;

/**
 *
 * @author FarzanMoradi
 */

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.*;

public class GameControllerTest {
    private GameController testGameController;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void before () {
        testGameController = new GameController();
        testGameController.addPlayer(PlayerFactory.getInstance(BoardDisc.RED,"Player R"));
        testGameController.addPlayer(PlayerFactory.getInstance(BoardDisc.GREEN,"Player G"));

    }

    @Test
    public void whenFirstPlayerPlaysThenDiscColorIsRed() {
        testGameController.nextPlayerTurn();
        MatcherAssert.assertThat(testGameController.getPlayerTurn().getDisk(), is(BoardDisc.RED));
    }

    @Test
    public void whenSecondPlayerPlaysThenDiscColorIsGreen() {
        testGameController.nextPlayerTurn();
        testGameController.nextPlayerTurn();
        MatcherAssert.assertThat(testGameController.getPlayerTurn().getDisk(), is(BoardDisc.GREEN));
    }

    @Test
    public void WhenThirdPlayerPlaysThenDiscColorIsRed() {
        testGameController.nextPlayerTurn();
        testGameController.nextPlayerTurn();
        testGameController.nextPlayerTurn();
        MatcherAssert.assertThat(testGameController.getPlayerTurn().getDisk(), is(BoardDisc.RED));
    }

    @Test
    public void whenTheGameStartsItIsNotFinished() {
        testGameController.newGame();
        MatcherAssert.assertThat(testGameController.getGameState(), is(GameState.PLAYING));

    }

    @Test
    public void whenNoDiscCanBeIntroducedTheGamesIsTie() {
        GameBoard gameBoard = new GameBoard();
        testGameController.setGameBoard(gameBoard);
        BoardDisc[] testDisk = new BoardDisc[] {BoardDisc.RED, BoardDisc.GREEN};
        int[] testColumn = new int[] {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 4, 4, 4, 4, 4, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 5,3, 5, 3, 5, 3, 3, 5, 3, 5, 1, 2, 3, 5, 4, 6, 7};

        for (int i=0; i<testColumn.length; i++){
            testGameController.getGameBoard().putDiscInColumn(testDisk[i%2],testColumn[i]-1);
        }

        testGameController.isGameEnded();
        MatcherAssert.assertThat(testGameController.getGameState(), is(GameState.TIE));
    }

    @Test public void when4VerticalDiscsAreConnectedThenPlayerWins() {
        GameBoard gameBoard = new GameBoard();
        testGameController.setGameBoard(gameBoard);
        BoardDisc[] testDisk = new BoardDisc[] {BoardDisc.RED, BoardDisc.GREEN};
        int[] testColumn = new int[] {1, 5, 2, 3, 6, 3, 2, 5, 6, 7, 2, 3, 4, 2, 3, 5, 1, 5};

        for (int i=0; i<testColumn.length; i++){
            testGameController.getGameBoard().putDiscInColumn(testDisk[i%2],testColumn[i]-1);
        }

        testGameController.isGameEnded();
        MatcherAssert.assertThat(testGameController.getWinType(), is(WinType.COLUMN_WINNER));
        MatcherAssert.assertThat(testGameController.getGameState(), is(GameState.WINNER));
    }

    @Test public void when4HorizontalDiscsAreConnectedThenPlayerWins() {
        GameBoard gameBoard = new GameBoard();
        testGameController.setGameBoard(gameBoard);
        BoardDisc[] testDisk = new BoardDisc[] {BoardDisc.RED, BoardDisc.GREEN};
        int[] testColumn = new int[] {7, 2, 3, 5, 1, 1, 1, 2, 5, 6, 7, 3, 2, 2, 4, 4};

        for (int i=0; i<testColumn.length; i++){
            testGameController.getGameBoard().putDiscInColumn(testDisk[i%2],testColumn[i]-1);
        }

        testGameController.isGameEnded();
        MatcherAssert.assertThat(testGameController.getWinType(), is(WinType.ROW_WINNER));
        MatcherAssert.assertThat(testGameController.getGameState(), is(GameState.WINNER));
    }

    @Test public void when4MainDiagonalDiscsAreConnectedThenPlayerWins() {
        GameBoard gameBoard = new GameBoard();
        testGameController.setGameBoard(gameBoard);
        BoardDisc[] testDisk = new BoardDisc[] {BoardDisc.RED, BoardDisc.GREEN};
        int[] testColumn = new int[] {2, 5, 6, 7, 2, 3, 4, 5, 1, 1, 6, 4, 4, 4, 3, 3, 2, 2};

        for (int i=0; i<testColumn.length; i++){
            testGameController.getGameBoard().putDiscInColumn(testDisk[i%2],testColumn[i]-1);
        }

        testGameController.isGameEnded();
        MatcherAssert.assertThat(testGameController.getWinType(), is(WinType.MAIN_DIAGONAL_WINNER));
        MatcherAssert.assertThat(testGameController.getGameState(), is(GameState.WINNER));
    }

    @Test public void when4AntiDiagonalDiscsAreConnectedThenPlayerWins() {
        GameBoard gameBoard = new GameBoard();
        testGameController.setGameBoard(gameBoard);
        BoardDisc[] testDisk = new BoardDisc[] {BoardDisc.RED, BoardDisc.GREEN};
        int[] testColumn = new int[] {7, 2, 3, 5, 1, 1, 1, 2, 5, 6, 7, 3, 2, 4, 4, 4, 5, 4, 3, 5};

        for (int i=0; i<testColumn.length; i++){
            testGameController.getGameBoard().putDiscInColumn(testDisk[i%2],testColumn[i]-1);
        }

        testGameController.isGameEnded();
        MatcherAssert.assertThat(testGameController.getWinType(), is(WinType.ANTI_DIAGONAL_WINNER));
        MatcherAssert.assertThat(testGameController.getGameState(), is(GameState.WINNER));
    }

    @Test
    public void addPlayer() {
        testGameController = new GameController();
        testGameController.addPlayer(PlayerFactory.getInstance(BoardDisc.RED,"Player R"));

        MatcherAssert.assertThat(testGameController.getPlayers().size(), is(1));

    }
}