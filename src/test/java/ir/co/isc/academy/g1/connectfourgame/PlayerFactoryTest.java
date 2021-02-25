package ir.co.isc.academy.g1.connectfourgame;

/**
 *
 * @author FarzanMoradi
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerFactoryTest {

    @Test
    void getInstancePlayerR() {
        BoardDisc boardDisc = BoardDisc.RED;
        String name = "playerR";
        Player result = PlayerFactory.getInstance(boardDisc,name);
        assertEquals(boardDisc,result.disk);
        assertEquals(name,result.name);
    }

    @Test
    void getInstancePlayerG() {
        BoardDisc boardDisc = BoardDisc.GREEN;
        String name = "playerG";
        Player result = PlayerFactory.getInstance(boardDisc,name);
        assertEquals(boardDisc,result.disk);
        assertEquals(name,result.name);
    }
}