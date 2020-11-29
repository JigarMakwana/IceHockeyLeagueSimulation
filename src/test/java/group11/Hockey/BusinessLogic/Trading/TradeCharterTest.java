package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeCharter;
import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeResolver;
import group11.Hockey.BusinessLogic.models.League;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TradeCharterTest {
    private TradingModelMock leagueModel;
    private ITradeCharter aiTradingObj;

    @Before
    public void setUp() throws Exception {
        leagueModel = TradingMockFactory.makeTradingMock(1.0f, 1.0f);
        aiTradingObj = leagueModel.getTradeCharter();
    }

    @Test
    public void isCharterValid() {
        Assert.assertTrue(aiTradingObj.isCharterValid());
        aiTradingObj = leagueModel.getInValidCharter();
        Assert.assertFalse(aiTradingObj.isCharterValid());
    }
}
