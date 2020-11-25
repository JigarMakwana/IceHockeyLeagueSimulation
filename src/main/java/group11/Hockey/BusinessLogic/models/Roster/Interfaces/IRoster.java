package group11.Hockey.BusinessLogic.models.Roster.Interfaces;

import group11.Hockey.BusinessLogic.models.Player;

import java.util.List;

public interface IRoster {
    List<Player> getAllPlayerList();
    List<Player> getActiveRoster();
    List<Player> getInActiveRoster();
    List<Player> getForwardList();
    List<Player> getDefenseList();
    List<Player> getGoalieList();
    String getTeamName();
    boolean isValidRoster();
    boolean isValidActiveRoster();
    boolean isValidInActiveRoster();
    void swapPlayers(Player one, Player two);
}
