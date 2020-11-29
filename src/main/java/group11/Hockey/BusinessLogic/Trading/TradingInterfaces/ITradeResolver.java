/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading.TradingInterfaces;

import group11.Hockey.BusinessLogic.models.ITeam;

public interface ITradeResolver {
    void resolveTrade();
    void resetLossPoints(ITeam team);
    void acceptTrade();
    void rejectTrade();
    float modifyAcceptanceChance();
}
