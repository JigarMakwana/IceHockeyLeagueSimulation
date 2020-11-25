package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradingConfig;
import group11.Hockey.BusinessLogic.models.IgmTable;

import java.util.HashMap;
import java.util.Map;
import java.util.Hashtable;
import java.util.Map;

public class TradingConfig implements ITradingConfig {
    private final int lossPoint;
    private final float randomTradeOfferChance;
    private final int maxPlayersPerTrade;
    private final float randomAcceptanceChance;
    private IgmTable gmTable;

    public TradingConfig(int lossPoint, float randomTradeOfferChance, int maxPlayersPerTrade, float randomAcceptanceChance,
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
