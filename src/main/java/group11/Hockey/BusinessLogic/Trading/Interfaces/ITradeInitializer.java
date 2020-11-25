package group11.Hockey.BusinessLogic.Trading.Interfaces;

import group11.Hockey.BusinessLogic.models.Team;

import java.util.List;

public interface ITradeInitializer {
    boolean isTradePossible(Team team);
    List<Team> getEligibleTeams();
    ITradingConfig getTradingConfig();
}
