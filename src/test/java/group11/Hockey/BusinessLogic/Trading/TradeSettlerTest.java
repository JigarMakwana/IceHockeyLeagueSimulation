package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeSettler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TradeSettlerTest {
    private TradingModelMock leagueModel;
    private ITradeSettler dropTest;
    private ITradeSettler hireTest;

    @Before
    public void setUp() throws Exception {
        leagueModel = TradingMockFactory.makeTradingMock(1.0f, 1.0f);
    }

    @Test
    public void settleTeam() {
        leagueModel.addPlayertoTeam2();
        dropTest = TradingFactory.makeTradeSettler(leagueModel.getTeam2(), leagueModel.getFreeAgentsList(),
                leagueModel.getCommandLineInput(),leagueModel.getValidations(),leagueModel.getDisplay());
        dropTest.settleTeam();
        Assert.assertEquals(leagueModel.getTeam2().getRoster().getAllPlayerList().size(), 30);

        leagueModel.dropPlayerFromTeam2();
        hireTest = new TradeSettler(leagueModel.getTeam2(), leagueModel.getFreeAgentsList(),
                leagueModel.getCommandLineInput(),leagueModel.getValidations(),leagueModel.getDisplay());
        hireTest.settleTeam();
        Assert.assertEquals(leagueModel.getTeam2().getRoster().getAllPlayerList().size(), 30);
    }

    @Test
    public void dropPlayersTest() {
        leagueModel.addPlayertoTeam2();
        dropTest = TradingFactory.makeTradeSettler(leagueModel.getTeam2(), leagueModel.getFreeAgentsList(),
                leagueModel.getCommandLineInput(),leagueModel.getValidations(),leagueModel.getDisplay());
        dropTest.dropPlayers();
        Assert.assertEquals(leagueModel.getTeam2().getRoster().getAllPlayerList().size(), 30);
    }

    @Test
    public void hirePlayersTest() {
        leagueModel.dropPlayerFromTeam2();
        hireTest = new TradeSettler(leagueModel.getTeam2(), leagueModel.getFreeAgentsList(),
                leagueModel.getCommandLineInput(),leagueModel.getValidations(),leagueModel.getDisplay());
        hireTest.hirePlayers();
        Assert.assertEquals(leagueModel.getTeam2().getRoster().getAllPlayerList().size(), 30);
    }
}
