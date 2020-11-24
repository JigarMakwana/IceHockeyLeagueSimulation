package group11.Hockey.BusinessLogic.Trading.Interfaces;

import group11.Hockey.BusinessLogic.models.ITeam;

import java.util.List;

public interface ITradeGenerator {
    ITradeCharter generateTradeOffer(List<ITeam> teamList);
    void tradingAlgorithm(ITeam requestedTeam);
    void tradeDraftPicks();
}
