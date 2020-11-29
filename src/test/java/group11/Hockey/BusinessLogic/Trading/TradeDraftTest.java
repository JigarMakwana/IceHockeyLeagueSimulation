package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeCharter;
import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TradeDraftTest {
    private TradingModelMock leagueModel;
    private ITradeGenerator aiTradingObj;

    @Before
    public void setUp() throws Exception {
        leagueModel = TradingMockFactory.makeTradingMock(1.0f, 1.0f);
        aiTradingObj = TradingFactory.makeTradeDraft(leagueModel.getTeam1(), leagueModel.getTradingConfig(), leagueModel.getDisplay());
    }

    @Test
    public void generateTradeOfferTest() {
        ITradeCharter tradeCharter = aiTradingObj.generateTradeOffer(leagueModel.getTeamList());
        Assert.assertEquals(tradeCharter.getOfferingTeam().getTeamName(), "Boston");
        Assert.assertEquals(tradeCharter.getRequestedTeam().getTeamName(), "Florida");
    }
}
