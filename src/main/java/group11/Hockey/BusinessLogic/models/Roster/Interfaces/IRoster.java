package group11.Hockey.BusinessLogic.models.Roster.Interfaces;

import java.util.List;

import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.Player;

public interface IRoster {
    List<IPlayer> getAllPlayerList();
    List<IPlayer> getActiveRoster();
    List<IPlayer> getInActiveRoster();
    List<IPlayer> getForwardList();
    List<IPlayer> getDefenseList();
    List<IPlayer> getGoalieList();
    String getTeamName();
    void updateSubRoster(List<IPlayer> allPlayerList);
    boolean isValidRoster();
    boolean isValidActiveRoster();
    boolean isValidInActiveRoster();
    void swapPlayers(IPlayer one, IPlayer two);
}
