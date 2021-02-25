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

public class GameBoardTest {
    private GameBoard testBoard;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void before () {
        testBoard = new GameBoard();
    }

    @Test
    public void whenTheGameIsStartedTheBoardIsEmpty() {
        MatcherAssert.assertThat(testBoard.getNumberOfDiscsInBoard(), is(0));
    }

    @Test
    public void whenFirstDiscInsertedInColumnThenPositionIsEndOfColumn() {
        BoardDisc testDisk = BoardDisc.RED;
        int column = 1;
        int diskRowAfterPutInColumn = testBoard.getRowCount()-1;
        testBoard.putDiscInColumn(testDisk,column);

        MatcherAssert.assertThat(testBoard.getBoard(diskRowAfterPutInColumn, column), is(BoardDisc.RED));
        MatcherAssert.assertThat(testBoard.getNumberOfDiscsInColumn(column), is(1));
        MatcherAssert.assertThat(testBoard.getNumberOfDiscsInBoard(), is(1));
    }

    @Test
    public void whenFirstDiscInsertedInColumnThenPositionIsOneBeforeEndOfColumn() {
        BoardDisc testDisk = BoardDisc.RED;
        int column = 1;
        int secondDiskRowAfterPutInColumn = testBoard.getRowCount()-2;
        testBoard.putDiscInColumn(testDisk,column);
        testBoard.putDiscInColumn(testDisk,column);

        MatcherAssert.assertThat(testBoard.getBoard(secondDiskRowAfterPutInColumn, column), is(BoardDisc.RED));
        MatcherAssert.assertThat(testBoard.getNumberOfDiscsInColumn(column), is(2));
        MatcherAssert.assertThat(testBoard.getNumberOfDiscsInBoard(), is(2));
    }

    @Test
    public void whenDiscOutsideBoardThenRuntimeException() {
        BoardDisc testDisk = BoardDisc.RED;
        int column = -1;

        exception.expect(RuntimeException.class);
        exception.expectMessage("Invalid column " + column);

        testBoard.putDiscInColumn(testDisk,column);
    }

    @Test
    public void
    whenNoMoreRoomInColumnThenRuntimeException() {
        BoardDisc testDisk = BoardDisc.RED;
        int column = 1;
        int maxDiscsInColumn = testBoard.getRowCount();
        for (int times = 0; times < maxDiscsInColumn; ++times) {
            testBoard.putDiscInColumn(testDisk,column);
        }
        exception.expect(RuntimeException.class);
        exception .expectMessage("No more room in column " + column);
        testBoard.putDiscInColumn(testDisk,column);
    }

    @Test
    public void whenColumnIsFull() {
        BoardDisc testDisk = BoardDisc.RED;
        int column = 1;
        int maxDiscsInColumn = testBoard.getRowCount();
        for (int times = 0; times < maxDiscsInColumn; ++times) {
            testBoard.putDiscInColumn(testDisk,column);
        }
        MatcherAssert.assertThat(testBoard.isColumnFull(column), is(true));
    }

    @Test
    public void whenColumnIsNotFull() {

        BoardDisc testDisk = BoardDisc.RED;
        int column = 1;
        testBoard.putDiscInColumn(testDisk,column);

        MatcherAssert.assertThat(testBoard.isColumnFull(column), is(false));
    }

    @Test
    public void getNumberOfDiscsInColumn() {
        BoardDisc testDisk = BoardDisc.RED;
        int column = 1;
        testBoard.putDiscInColumn(testDisk,column);

        MatcherAssert.assertThat(testBoard.getNumberOfDiscsInColumn(column), is(1));
    }

    @Test
    public void getRowCount() {
        MatcherAssert.assertThat(testBoard.getRowCount(), is(6));
    }

    @Test
    public void getColumnCount() {
        MatcherAssert.assertThat(testBoard.getColumnCount(), is(7));
    }
}