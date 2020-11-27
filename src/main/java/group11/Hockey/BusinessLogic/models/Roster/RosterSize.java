package group11.Hockey.BusinessLogic.models.Roster;

public enum RosterSize {
    FORWARD_SIZE(2),
    DEFENSE_SIE(2),
    GOALIE_SIZE(2),
    ACTIVE_ROSTER_SIZE(5),
    INACTIVE_ROSTER_SIZE(1);

    private int numVal;

    RosterSize(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
