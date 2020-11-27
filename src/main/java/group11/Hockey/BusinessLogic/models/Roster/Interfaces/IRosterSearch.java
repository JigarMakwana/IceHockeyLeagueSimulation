/*
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.models.Roster.Interfaces;

import group11.Hockey.BusinessLogic.Enums.Positions;
import group11.Hockey.BusinessLogic.Trading.TradingTriplet.Triplet;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

import java.util.List;

public interface IRosterSearch {
    List<Player> findWeakestPlayers(List<Player> unSortedPlayerList, int maxPlayersPerTrade);
    List<Integer> findPlayerPositions(List<Player> playerList);
    List<Player> findStrongestPlayers(List<Player> unSortedPlayerList, List<Integer> playerPositionFlag,
                                       int maxPlayersPerTrade);
    Player findStrongestPlayerByPosition(List<Player> unSortedPlayerList, Positions positions);
    Player findWeakestPlayerByPosition(List<Player> unSortedPlayerList, Positions positions);
    Triplet<Team, List<Player>, Float> findStrongestTradeTeam(
            List<Triplet<Team, List<Player>, Float>> tradingTeamsBuffer);
    float getRosterStrength(List<Player> playerList);
    List<Player> sortPlayersByStrength(List<Player> unSortedPlayerList);
    List<Player> getDefenseList(List<Player> playerList);
    List<Player> getForwardList(List<Player> playerList);
    List<Player> getGoalieList(List<Player> playerList);
}
