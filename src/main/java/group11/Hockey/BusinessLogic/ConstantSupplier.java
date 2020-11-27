
/*
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic;

public class ConstantSupplier implements IConstantSupplier {
    private int activeRosterSize;
    private int inActiveRosterSize;
    private int forwardSize;
    private int defenseSize;
    private int goalieSize;

    public ConstantSupplier(int activeRosterSize, int inActiveRosterSize,
                            int forwardSize, int defenseSize, int goalieSize) {
        this.activeRosterSize = activeRosterSize;
        this.inActiveRosterSize = inActiveRosterSize;
        this.forwardSize = forwardSize;
        this.defenseSize = defenseSize;
        this.goalieSize = goalieSize;
    }

    public int getActiveRosterSize() {
        return activeRosterSize;
    }

    public int getInActiveRosterSize() {
        return inActiveRosterSize;
    }

    public int getForwardSize() {
        return forwardSize;
    }

    public int getDefenseSize() {
        return defenseSize;
    }

    public int getGoalieSize() {
        return goalieSize;
    }
}
