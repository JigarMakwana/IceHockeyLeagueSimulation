package group11.Hockey.BusinessLogic.Trading.Interfaces;

import java.util.List;

import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Team;

public interface ITradeInitializer {
    boolean isTradePossible(ITeam team);
    List<ITeam> getEligibleTeams();
    ITradingConfig getTradingConfig();
}
