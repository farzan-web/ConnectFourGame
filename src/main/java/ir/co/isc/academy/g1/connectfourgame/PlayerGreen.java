package ir.co.isc.academy.g1.connectfourgame;

/**
 *
 * @author FarzanMoradi
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerGreen extends Player{
    private final static Logger LOGGER = LoggerFactory.getLogger(PlayerGreen.class);

    public PlayerGreen(String name) {
        super(name, BoardDisc.GREEN);
    }

    public String playerIntroduce() {
        LOGGER.info("Call playerIntroduce from PlayerGreen class");
        final String ANSI_RESET  = "\u001B[0m";
        final String ANSI_GREEN  = "\u001B[32m";
        return (name + ANSI_GREEN +" Player G" + ANSI_RESET);
    }
}
