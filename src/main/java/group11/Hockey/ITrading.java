package group11.Hockey;

import group11.Hockey.models.Player;
import group11.Hockey.models.Team;

import java.util.List;

public interface ITrading {
    public void displayTradeStatistics(Team team1, List<Player> playerList1, Team team2, List<Player> playerList2);
}
