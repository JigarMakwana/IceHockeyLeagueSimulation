/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading.TradingInterfaces;

import group11.Hockey.BusinessLogic.models.IgmTable;

public interface ITradeConfig {
    int getLossPoint();
    float getRandomTradeOfferChance();
    int getMaxPlayersPerTrade();
    float getRandomAcceptanceChance();
    IgmTable getGmTable();
}
