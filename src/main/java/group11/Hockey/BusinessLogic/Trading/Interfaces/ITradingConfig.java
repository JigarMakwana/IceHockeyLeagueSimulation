package group11.Hockey.BusinessLogic.Trading.Interfaces;

import group11.Hockey.BusinessLogic.models.IgmTable;

import java.util.Map;

public interface ITradingConfig {
    int getLossPoint();
    float getRandomTradeOfferChance();
    int getMaxPlayersPerTrade();
    float getRandomAcceptanceChance();
    IgmTable getGmTable();
}
