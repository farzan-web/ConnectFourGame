package ir.co.isc.academy.g1.connectfourgame;

/**
 *
 * @author FarzanMoradi
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Player {
    private final static Logger LOGGER = LoggerFactory.getLogger(Player.class);

    protected String name;
    protected BoardDisc disk;

    public Player(String name, BoardDisc disk) {
        LOGGER.info("Initial Player class");
        this.name = name;
        this.disk = disk;
    }

    public abstract String playerIntroduce();

    public BoardDisc getDisk() {
        return disk;
    }

}
