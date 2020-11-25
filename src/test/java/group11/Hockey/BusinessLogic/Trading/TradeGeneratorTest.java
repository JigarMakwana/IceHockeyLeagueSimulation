package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeCharter;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeGenerator;
import org.junit.Assert;
import org.junit.Test;

public class TradeGeneratorTest {
    TradingModelMock leagueModel = new TradingModelMock(1.0f, 1.0f);
    private ITradeGenerator aiTradingObj = new TradeGenerator(leagueModel.getTeam1(), leagueModel.getTradingConfig(),
            leagueModel.getDisplay());

    @Test
    public void generateTradeOfferTest() {
        ITradeCharter tradeCharter = aiTradingObj.generateTradeOffer(leagueModel.getTeamList());
        Assert.assertEquals(tradeCharter.getOfferingTeam().getTeamName(), "Boston");
        Assert.assertEquals(tradeCharter.getRequestedTeam().getTeamName(), "Viena");
    }

    @Test
    public void tradeDraftPicksTest() {
    }
}
