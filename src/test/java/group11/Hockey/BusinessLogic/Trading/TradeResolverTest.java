/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeResolver;
import group11.Hockey.BusinessLogic.models.League;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TradeResolverTest {
    private TradingModelMock leagueModel;
    private League leagueObj;
    private ITradeResolver aiTradingObj;

    @Before
    public void setUp() throws Exception {
        leagueModel = TradingMockFactory.makeTradingMock(1.0f, 1.0f);
        leagueObj = leagueModel.getLeagueInfo();
        aiTradingObj = TradingFactory.makeTradeResolver(leagueObj, leagueModel.getTradeCharter(), leagueModel.getTradingConfig(),
                leagueModel.getCommandLineInput(), leagueModel.getValidations(), leagueModel.getDisplay());
    }

    @Test
    public void resolveTradeTest() {
        aiTradingObj.resolveTrade();
        Assert.assertEquals(leagueModel.getTradeCharter().getOfferedPlayerList().size(), 2);
        Assert.assertEquals(leagueModel.getTradeCharter().getRequestedPlayerList().size(), 2);
    }

    @Test
    public void resetLossPointsTest() {
        Assert.assertEquals(leagueModel.getTeam2().getLosses(), 4);
        aiTradingObj.resetLossPoints(leagueModel.getTeam1());
        Assert.assertEquals(leagueModel.getTeam1().getLosses(), 0);
    }

    @Test
    public void modifyAcceptanceChanceTest() {
        float modifiedChance = aiTradingObj.modifyAcceptanceChance();
        Assert.assertEquals(modifiedChance, 1.0f, 0.00f);
    }

    @Test
    public void rejectTradeTest() {
        aiTradingObj.rejectTrade();
        Assert.assertEquals(leagueModel.getTeam1().getLosses(), 0);
    }

    @Test
    public void acceptTradeTest() {
        aiTradingObj.acceptTrade();
        Assert.assertEquals(leagueModel.getTradeCharter().getOfferedPlayerList().size(), 2);
        Assert.assertEquals(leagueModel.getTradeCharter().getRequestedPlayerList().size(), 2);
        Assert.assertEquals(leagueModel.getTeam1().getPlayers().get(2).getPlayerName(), "dsf");
        Assert.assertEquals(leagueModel.getTeam6().getPlayers().get(2).getPlayerName(), "Gama");
    }
}
