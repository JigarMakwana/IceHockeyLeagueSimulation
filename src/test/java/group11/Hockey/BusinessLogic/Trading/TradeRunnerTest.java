/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeRunner;
import group11.Hockey.BusinessLogic.models.League;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TradeRunnerTest {
    private TradingModelMock leagueModel;
    private League leagueObj;
    private ITradeRunner tradeRunner;

    @Before
    public void setUp() throws Exception {
        leagueModel = TradingMockFactory.makeTradingMock(1.0f, 1.0f);
        leagueObj = leagueModel.getLeagueInfo();
        tradeRunner = new TradeRunner(leagueObj, null, leagueModel.getCommandLineInput(),leagueModel.getValidations(),
                leagueModel.getDisplay());
    }

    @Test
    public void runTradingTest() {
        tradeRunner.runTrading();
        Assert.assertEquals(leagueModel.getTradeCharter().getOfferedPlayerList().size(), 2);
        Assert.assertEquals(leagueModel.getTradeCharter().getRequestedPlayerList().size(), 2);
        Assert.assertEquals(leagueModel.getTeam1().getPlayers().get(2).getPlayerName(), "Harry");
        Assert.assertEquals(leagueModel.getTeam6().getPlayers().get(2).getPlayerName(), "Gama");
    }
}
