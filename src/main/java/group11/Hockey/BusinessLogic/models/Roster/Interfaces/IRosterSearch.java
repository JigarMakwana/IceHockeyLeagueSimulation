package group11.Hockey.BusinessLogic.models.Roster.Interfaces;

import group11.Hockey.BusinessLogic.Triplet;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Team;

import java.util.List;

public interface IRosterSearch {
    List<Player> findWeakestPlayers(List<Player> unSortedPlayerList, int maxPlayersPerTrade);
    List<Integer> findPlayerPositions(List<Player> playerList);
    List<Player> findStrongestPlayers(List<Player> unSortedPlayerList, List<Integer> playerPositionFlag,
                                       int maxPlayersPerTrade);
    Triplet<Team, List<Player>, Float> findStrongestTradeTeam(
            List<Triplet<Team, List<Player>, Float>> tradingTeamsBuffer);
    float getRosterStrength(List<Player> playerList);
    Float playersStrengthSum(List<Player> playerList);
    List<Player> sortPlayersByStrength(List<Player> unSortedPlayerList);
    List<Player> getDefenseList(List<Player> playerList);
    List<Player> getForwardList(List<Player> playerList);
    List<Player> getGoalieList(List<Player> playerList);
}
