package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeResolver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TradeResolverTest {
    private TradingModelMock leagueModel;
    private ITradeResolver aiTradingObj;

    @Before
    public void setUp() throws Exception {
        leagueModel = new TradingModelMock(1.0f, 1.0f);
        aiTradingObj = new TradeResolver(leagueModel.getTradeCharter(),
                leagueModel.getTradingConfig(), leagueModel.getCommandLineInput(), leagueModel.getValidations(),
                leagueModel.getDisplay());
    }

    @Test
    public void resolveTradeTest() {
        aiTradingObj.resolveTrade();
        Assert.assertEquals(leagueModel.getTradeCharter().getOfferedPlayerList().size(), 2);
        Assert.assertEquals(leagueModel.getTradeCharter().getRequestedPlayerList().size(), 2);
    }

    @Test
    public void resetLossPointsTest() {
        Assert.assertEquals(leagueModel.getTeam1().getLosses(), 3);
        aiTradingObj.resetLossPoints(leagueModel.getTeam1());
        Assert.assertEquals(leagueModel.getTeam1().getLosses(), 0);
    }

    @Test
    public void modifyAcceptanceChanceTest() {
        float modifiedChance = aiTradingObj.modifyAcceptanceChance();
        Assert.assertEquals(modifiedChance, 1.0f, 0.00f);
    }
}
