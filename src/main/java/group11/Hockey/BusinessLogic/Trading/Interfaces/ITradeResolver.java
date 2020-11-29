package group11.Hockey.BusinessLogic.Trading.Interfaces;

import group11.Hockey.BusinessLogic.models.ITeam;

public interface ITradeResolver {
    void resolveTrade();
    void resetLossPoints(ITeam team);
    float modifyAcceptanceChance();
}
