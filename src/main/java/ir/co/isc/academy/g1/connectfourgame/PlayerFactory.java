package ir.co.isc.academy.g1.connectfourgame;

/**
 *
 * @author FarzanMoradi
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerFactory {
    private final static Logger LOGGER = LoggerFactory.getLogger(PlayerFactory.class);

    private PlayerFactory(){}

    public  static Player getInstance(BoardDisc disk, String name){
        LOGGER.info("Create player " + disk + " from PlayerFactory class");
        if(BoardDisc.RED == disk){
            return new PlayerRed(name);
        }else if(BoardDisc.GREEN == disk){
            return new PlayerGreen(name);
        }
        else{
            return null;
        }
    }
}
