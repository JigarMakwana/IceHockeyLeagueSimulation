package group11.Hockey;

import group11.Hockey.models.League;
import group11.Hockey.models.Player;
import group11.Hockey.models.Team;

import java.util.List;

public interface IGenerateTradeOffers extends ITrading {
    public float generateRandomNumber();
    public void generateTradeOffers();
    public void generateTradeOfferForTeam(Team team, List<Team> eligibleTeamList);
    public List<Player> findWeakestPlayers(Team team);
    public List<Player> findStrongestPlayers(Team team);
    public void displayTradeStatistics();
}
