package group11.Hockey.BusinessLogic.models.Roster;

public enum RosterSize {
    TEAM_SIZE(30),
    FORWARD_SIZE(16),
    DEFENSE_SIE(10),
    GOALIE_SIZE(4),
    ACTIVE_ROSTER_SIZE(20),
    INACTIVE_ROSTER_SIZE(10),
    ACTIVE_FORWARD_SIZE(16),
    ACTIVE_DEFENSE_SIE(10),
    ACTIVE_GOALIE_SIZE(4);

    private int numVal;

    RosterSize(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
