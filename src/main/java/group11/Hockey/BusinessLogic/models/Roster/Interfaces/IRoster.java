package group11.Hockey.BusinessLogic.models.Roster.Interfaces;

import group11.Hockey.BusinessLogic.models.IPlayer;

import java.util.List;

public interface IRoster {
    List<IPlayer> getActiveRoster();
    List<IPlayer> getInActiveRoster();
    void setSuppliedActiveRosterSize(int suppliedActiveRosterSize);
    void setSuppliedInActiveRosterSize(int suppliedInActiveRosterSize);
    void setSuppliedForwardSize(int suppliedForwardSize);
    void setSuppliedDefenseSize(int suppliedDefenseSize);
    void setSuppliedGoalieSize(int suppliedGoalieSize);
    boolean isValidRoster();
    boolean isValidActiveRoster();
    boolean isValidInActiveRoster();
    void swapPlayers(IPlayer one, IPlayer two);
}
