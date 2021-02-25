package ir.co.isc.academy.g1.connectfourgame;

/**
 *
 * @author FarzanMoradi
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerRedTest {

    @Test
    void playerIntroduce() {
        final String ANSI_RESET  = "\u001B[0m";
        final String ANSI_RED    = "\u001B[31m";
        BoardDisc boardDisc = BoardDisc.RED;
        String name = "player1";
        Player result = PlayerFactory.getInstance(boardDisc,name);
        String testIntroduce = result.playerIntroduce();

        assertEquals("player1" + ANSI_RED +" Player R" + ANSI_RESET,testIntroduce);
    }
}