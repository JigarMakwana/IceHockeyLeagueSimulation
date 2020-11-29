package group11.Hockey.BusinessLogic.Trading;

public class TradingMockFactory {
    public static TradingModelMock makeTradingMock(float randomTradeOfferChance, float randomAcceptanceChance){
        return new TradingModelMock(randomTradeOfferChance, randomAcceptanceChance);
    }
}
