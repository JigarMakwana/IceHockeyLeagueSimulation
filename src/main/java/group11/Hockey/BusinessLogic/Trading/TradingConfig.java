package group11.Hockey.BusinessLogic.Trading;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradingConfig;
import group11.Hockey.BusinessLogic.models.IgmTable;

public class TradingConfig implements ITradingConfig {
    private final int lossPoint;
    private final float randomTradeOfferChance;
    private final int maxPlayersPerTrade;
    private final float randomAcceptanceChance;
    private IgmTable gmTable;
    private static Logger logger = LogManager.getLogger(TradingConfig.class);

    public TradingConfig(int lossPoint, float randomTradeOfferChance, int maxPlayersPerTrade, float randomAcceptanceChance,
                         IgmTable gmTable) {
        this.lossPoint = lossPoint;
        this.randomTradeOfferChance = randomTradeOfferChance;
        this.maxPlayersPerTrade = maxPlayersPerTrade;
        this.randomAcceptanceChance = randomAcceptanceChance;
        this.gmTable = gmTable;
    }

    public int getLossPoint() {
    	logger.info("Entered getLossPoint()");
        return lossPoint;
    }

    public float getRandomTradeOfferChance() {
    	logger.info("Entered getRandomTradeOfferChance()");
        return randomTradeOfferChance;
    }

    public int getMaxPlayersPerTrade() {
    	logger.info("Entered getMaxPlayersPerTrade()");
        return maxPlayersPerTrade;
    }

    public float getRandomAcceptanceChance() {
    	logger.info("Entered getRandomAcceptanceChance()");
        return randomAcceptanceChance;
    }

    public IgmTable getGmTable() {
    	logger.info("Entered getGmTable()");
        return this.gmTable;
    }
}
