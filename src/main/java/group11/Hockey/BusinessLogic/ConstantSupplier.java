package group11.Hockey.BusinessLogic;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ConstantSupplier implements IConstantSupplier {
    private int activeRosterSize;
    private int inActiveRosterSize;
    private int forwardSize;
    private int defenseSize;
    private int goalieSize;
    private static Logger logger = LogManager.getLogger(ConstantSupplier.class);

    public ConstantSupplier(int activeRosterSize, int inActiveRosterSize,
                            int forwardSize, int defenseSize, int goalieSize) {
        this.activeRosterSize = activeRosterSize;
        this.inActiveRosterSize = inActiveRosterSize;
        this.forwardSize = forwardSize;
        this.defenseSize = defenseSize;
        this.goalieSize = goalieSize;
    }

    public int getActiveRosterSize() {
    	logger.info("Entered getActiveRosterSize()");
        return activeRosterSize;
    }

    public int getInActiveRosterSize() {
    	logger.info("Entered getInActiveRosterSize()");
        return inActiveRosterSize;
    }

    public int getForwardSize() {
    	logger.info("Entered getForwardSize()");
        return forwardSize;
    }

    public int getDefenseSize() {
    	logger.info("Entered getDefenseSize()");
        return defenseSize;
    }

    public int getGoalieSize() {
    	logger.info("Entered getGoalieSize()");
        return goalieSize;
    }
}
