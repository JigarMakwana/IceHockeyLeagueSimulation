package group11.Hockey.BusinessLogic.Trading.Interfaces;

import java.util.Dictionary;

public interface ITradingConfig {
    int getLossPoint();
    float getRandomTradeOfferChance();
    int getMaxPlayersPerTrade();
    float getRandomAcceptanceChance();
    Dictionary getGmTable();
}
