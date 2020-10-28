package group11.Hockey;

import group11.Hockey.models.Player;
import group11.Hockey.models.Team;

import java.util.List;

public interface IResolveTradeOffers extends ITrading {
    public void rejectTrade(Team team1);
    public void acceptTrade(Team team1, List<Player> playerList1, Team team2, List<Player> playerList2);
    public void resetLossPoints(Team team);
    public void resolveTrade(Team team1, List<Player> playerList1, Team team2, List<Player> playerList2);
    public void displayTradeStatistics(Team team1, List<Player> playerList1, Team team2, List<Player> playerList2);
}
