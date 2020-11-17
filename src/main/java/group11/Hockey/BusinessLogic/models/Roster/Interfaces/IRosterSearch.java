package group11.Hockey.BusinessLogic.models.Roster.Interfaces;

import group11.Hockey.BusinessLogic.models.IPlayer;

import java.util.List;

public interface IRosterSearch {
    List<IPlayer> getDefenseList(List<IPlayer> playerList);
    List<IPlayer> getForwardList(List<IPlayer> playerList);
    List<IPlayer> getGoalieList(List<IPlayer> playerList);
}
