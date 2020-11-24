package group11.Hockey.BusinessLogic.models.Roster.Interfaces;

import group11.Hockey.BusinessLogic.models.IPlayer;

import java.util.List;

public interface IRoster {
    List<IPlayer> getAllPlayerList();
    List<IPlayer> getActiveRoster();
    List<IPlayer> getInActiveRoster();
    List<IPlayer> getForwardList();
    List<IPlayer> getDefenseList();
    List<IPlayer> getGoalieList();
    String getTeamName();
    boolean isValidRoster();
    boolean isValidActiveRoster();
    boolean isValidInActiveRoster();
    void swapPlayers(IPlayer one, IPlayer two);
}
