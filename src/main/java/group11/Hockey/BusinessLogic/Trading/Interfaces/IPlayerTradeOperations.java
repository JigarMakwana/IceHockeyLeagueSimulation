package group11.Hockey.BusinessLogic.Trading.Interfaces;

import group11.Hockey.BusinessLogic.Triplet;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

import java.util.List;

public interface IPlayerTradeOperations {
    List<Player> findWeakestPlayers(Team team);
    List<Integer> findPlayerPositions(List<Player> playerList);
    List<Player> findStrongestPlayers(Team team, List<Integer> playerPositionFlag);
    Triplet<Team, List<Player>, Float> findStrongestTradeTeam(
            List<Triplet<Team, List<Player>, Float>> tradingTeamsBuffer);
    Float playersStrengthSum(List<Player> playerList);
    List<Player> getForwardList(List<Player> playerList);
    List<Player> getDefenseList(List<Player> playerList);
    List<Player> getGoalieList(List<Player> playerList);
    List<Player>  sortPlayersByStrength(List<Player> unSortedPlayerList);
}
