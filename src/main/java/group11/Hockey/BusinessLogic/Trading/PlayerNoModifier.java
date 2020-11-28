package group11.Hockey.BusinessLogic.Trading;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public enum PlayerNoModifier {
    MULTIPLE_PLAYER_MODIFIER(0.05f);

    private float numVal;
    private static Logger logger = LogManager.getLogger(PlayerNoModifier.class);

    PlayerNoModifier(float numVal) {
        this.numVal = numVal;
    }

    public float getNumVal() {
    	logger.info("Entered getNumVal()");
        return numVal;
    }
}
