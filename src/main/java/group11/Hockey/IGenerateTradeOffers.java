package group11.Hockey;

import group11.Hockey.models.League;
import group11.Hockey.models.Team;

public interface IGenerateTradeOffers extends ITrading {
    public float generateRandomNumber();
    public void generateTradeOffers();
    public void generateTradeOfferForTeam(Team team);
    public void findWeakestPlayers();
    public void findStrongestPlayers();
    public void displayTradeStatistics();
}
