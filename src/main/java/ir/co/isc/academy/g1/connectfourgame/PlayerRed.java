package ir.co.isc.academy.g1.connectfourgame;

/**
 *
 * @author FarzanMoradi
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerRed extends Player{
    private final static Logger LOGGER = LoggerFactory.getLogger(PlayerRed.class);

    public PlayerRed(String name) {
        super(name, BoardDisc.RED);
    }

    public String playerIntroduce() {
        LOGGER.info("Call playerIntroduce from PlayerRed class");
        final String ANSI_RESET  = "\u001B[0m";
        final String ANSI_RED    = "\u001B[31m";
        return (name  + ANSI_RED + " Player R" + ANSI_RESET);
    }
}