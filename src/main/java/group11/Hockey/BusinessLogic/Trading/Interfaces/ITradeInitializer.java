package group11.Hockey.BusinessLogic.Trading.Interfaces;

import group11.Hockey.BusinessLogic.models.ITeam;

import java.util.List;

public interface ITradeInitializer {
    boolean isTradePossible(ITeam team);
    List<ITeam> getEligibleTeams();
    ITradingConfig getTradingConfig();
}
