package group11.Hockey.BusinessLogic.models.Roster.Interfaces;

import java.util.List;

import group11.Hockey.BusinessLogic.Positions;
import group11.Hockey.BusinessLogic.Triplet;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Team;

public interface IRosterSearch {
    List<IPlayer> findWeakestPlayers(List<IPlayer> unSortedPlayerList, int maxPlayersPerTrade);
    List<Integer> findPlayerPositions(List<IPlayer> playerList);
    List<IPlayer> findStrongestPlayers(List<IPlayer> unSortedPlayerList, List<Integer> playerPositionFlag,
                                       int maxPlayersPerTrade);
    IPlayer findStrongestPlayerByPosition(List<IPlayer> unSortedPlayerList, Positions positions);
    IPlayer findWeakestPlayerByPosition(List<IPlayer> unSortedPlayerList, Positions positions);
    Triplet<ITeam, List<IPlayer>, Float> findStrongestTradeTeam(
            List<Triplet<ITeam, List<IPlayer>, Float>> tradingTeamsBuffer);
    float getRosterStrength(List<IPlayer> playerList);
    List<IPlayer> sortPlayersByStrength(List<IPlayer> unSortedPlayerList);
    List<IPlayer> getDefenseList(List<IPlayer> playerList);
    List<IPlayer> getForwardList(List<IPlayer> playerList);
    List<IPlayer> getGoalieList(List<IPlayer> playerList);
}
