package ir.co.isc.academy.g1.connectfourgame;

/**
 *
 * @author FarzanMoradi
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerGreenTest {

    @Test
    void playerIntroduce() {
        final String ANSI_RESET  = "\u001B[0m";
        final String ANSI_GREEN  = "\u001B[32m";
        BoardDisc boardDisc = BoardDisc.GREEN;
        String name = "player2";
        Player result = PlayerFactory.getInstance(boardDisc,name);
        String testIntroduce = result.playerIntroduce();

        assertEquals("player2" + ANSI_GREEN +" Player G" + ANSI_RESET,testIntroduce);
    }
}