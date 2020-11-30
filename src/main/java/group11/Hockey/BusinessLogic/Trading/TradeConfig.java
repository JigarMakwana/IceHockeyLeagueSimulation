/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeConfig;
import group11.Hockey.BusinessLogic.models.IgmTable;

public class TradeConfig implements ITradeConfig {
    private final int lossPoint;
    private final float randomTradeOfferChance;
    private final int maxPlayersPerTrade;
    private final float randomAcceptanceChance;
    private IgmTable gmTable;

    public TradeConfig(int lossPoint, float randomTradeOfferChance, int maxPlayersPerTrade, float randomAcceptanceChance,
                       IgmTable gmTable) {
        this.lossPoint = lossPoint;
        this.randomTradeOfferChance = randomTradeOfferChance;
        this.maxPlayersPerTrade = maxPlayersPerTrade;
        this.randomAcceptanceChance = randomAcceptanceChance;
        this.gmTable = gmTable;
    }

    public int getLossPoint() {
        return lossPoint;
    }

    public float getRandomTradeOfferChance() {
        return randomTradeOfferChance;
    }

    public int getMaxPlayersPerTrade() {
        return maxPlayersPerTrade;
    }

    public float getRandomAcceptanceChance() {
        return randomAcceptanceChance;
    }

    public IgmTable getGmTable() {
        return this.gmTable;
    }
}
