package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradingConfig;

import java.util.Dictionary;
import java.util.Hashtable;

public class TradingConfig implements ITradingConfig {
    private final int lossPoint;
    private final float randomTradeOfferChance;
    private final int maxPlayersPerTrade;
    private final float randomAcceptanceChance;
    private Dictionary gmTable = new Hashtable();

    public TradingConfig(int lossPoint, float randomTradeOfferChance, int maxPlayersPerTrade, float randomAcceptanceChance,
                   Dictionary gmTable) {
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

    public Dictionary getGmTable() {
        return gmTable;
    }
}
