package group11.Hockey.BusinessLogic.Trading.Interfaces;

import group11.Hockey.BusinessLogic.models.ITeam;

public interface ITradeResolver {
    void resolveAIToUserTrade();
    void resolveAIToAITrade();
    void acceptTrade();
    void rejectTrade();
    void resolveTrade();
    void resetLossPoints(ITeam team);
}
