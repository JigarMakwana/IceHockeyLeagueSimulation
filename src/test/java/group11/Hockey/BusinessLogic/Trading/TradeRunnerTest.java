package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeRunner;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeSettler;
import group11.Hockey.BusinessLogic.models.League;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TradeRunnerTest {
    private TradingModelMock leagueModel;
    private League leagueObj;
    private ITradeRunner tradeRunner;

    @Before
    public void setUp() throws Exception {
        leagueModel = new TradingModelMock(1.0f, 1.0f);
        leagueObj = leagueModel.getLeagueInfo();
        tradeRunner = new TradeRunner(leagueObj, leagueModel.getCommandLineInput(),leagueModel.getValidations(),
                leagueModel.getDisplay(), leagueModel.getConstantSupplier());
    }

    @Test
    public void runTradingTest() {
//        tradeRunner.runTrading();
    }
}
