/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeRunner;
import group11.Hockey.BusinessLogic.models.ILeague;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TradeRunnerTest {
    private TradingModelMock leagueModel;
    private ILeague leagueObj;
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
        Assert.assertEquals(leagueModel.getTeam1().getPlayers().size(), 4);
        Assert.assertEquals(leagueModel.getTeam4().getPlayers().size(), 4);
    }
}
