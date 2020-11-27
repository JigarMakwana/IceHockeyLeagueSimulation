/*
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading.Interfaces;

import group11.Hockey.BusinessLogic.models.Team;

public interface ITradeResolver {
    void resolveTrade();
    void resetLossPoints(Team team);
    float modifyAcceptanceChance();
}
