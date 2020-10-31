package group11.Hockey;

import group11.Hockey.models.League;
import group11.Hockey.models.Player;
import group11.Hockey.models.Team;

import java.util.List;

public interface IGenerateTradeOffers extends ITrading {
    public float generateRandomNumber();
    public void generateTradeOffers();
    public Triplet<Team, List<Player>, Float> findStrongestTradeTeam
            (Team team, List<Triplet<Team, List<Player>, Float>> tradingTeamsBuffer);
    public List<Player> findWeakestPlayers(Team team);
    public List<Player> findStrongestPlayers(Team team, List<Integer> playerPositionFlag);
    public void displayTradeStatistics(Team team1, List<Player> playerList1, Team team2, List<Player> playerList2);
}
