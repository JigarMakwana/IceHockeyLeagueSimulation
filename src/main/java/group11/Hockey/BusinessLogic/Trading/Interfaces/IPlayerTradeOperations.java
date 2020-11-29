package group11.Hockey.BusinessLogic.Trading.Interfaces;

import java.util.List;

import group11.Hockey.BusinessLogic.Triplet;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

public interface IPlayerTradeOperations {
    List<IPlayer> findWeakestPlayers(ITeam team);
    List<Integer> findPlayerPositions(List<IPlayer> playerList);
    List<IPlayer> findStrongestPlayers(ITeam team, List<Integer> playerPositionFlag);
    Triplet<ITeam, List<IPlayer>, Float> findStrongestTradeTeam(
            List<Triplet<ITeam, List<IPlayer>, Float>> tradingTeamsBuffer);
    Float playersStrengthSum(List<IPlayer> playerList);
    List<IPlayer> getForwardList(List<IPlayer> playerList);
    List<IPlayer> getDefenseList(List<IPlayer> playerList);
    List<IPlayer> getGoalieList(List<IPlayer> playerList);
    List<IPlayer>  sortPlayersByStrength(List<IPlayer> unSortedPlayerList);
}
