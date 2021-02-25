package ir.co.isc.academy.g1.connectfourgame;

/**
 *
 * @author FarzanMoradi
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void playerIntroduce() {
    }

    @Test
    void getDisk() {
        BoardDisc boardDisc = BoardDisc.RED;
        String name = "playerR";
        Player result = PlayerFactory.getInstance(boardDisc,name);
        assertEquals(boardDisc,result.getDisk());
    }
}