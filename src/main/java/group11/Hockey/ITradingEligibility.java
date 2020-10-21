package group11.Hockey;

import group11.Hockey.models.League;
import group11.Hockey.models.Team;

import java.util.List;

public interface ITradingEligibility extends ITrading {
    public List<Team> determineEligibleTeams();
    public void displayTradeStatistics();
}
