package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeSettler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TradeSettlerTest {
    private TradingModelMock leagueModel;
    private ITradeSettler dropTest;
    private ITradeSettler hireTest;

    @Before
    public void setUp() throws Exception {
        leagueModel = new TradingModelMock(1.0f, 1.0f);
        dropTest = new TradeSettler(leagueModel.getTeam5(), leagueModel.getFreeAgentsList(),
                leagueModel.getCommandLineInput(),leagueModel.getValidations(),leagueModel.getDisplay(),
                leagueModel.getConstantSupplier());
        hireTest = new TradeSettler(leagueModel.getTeam6(), leagueModel.getFreeAgentsList(),
                leagueModel.getCommandLineInput(),leagueModel.getValidations(),leagueModel.getDisplay(),
                leagueModel.getCsTeam6());
    }

    @Test
    public void settleTeamAfterTradeTest() {
        dropTest.settleTeamAfterTrade();
        Assert.assertEquals(leagueModel.getTeam5().getRoster().getAllPlayerList().size(), 5);
        Assert.assertEquals(leagueModel.getFreeAgentsList().size(), 11);

        hireTest.settleTeamAfterTrade();
        Assert.assertEquals(leagueModel.getTeam6().getRoster().getAllPlayerList().size(), 7);
        Assert.assertEquals(leagueModel.getFreeAgentsList().size(), 8);
    }

    @Test
    public void dropPlayersTest() {
        dropTest.dropPlayers();
        Assert.assertEquals(leagueModel.getTeam5().getRoster().getAllPlayerList().size(), 5);
        Assert.assertEquals(leagueModel.getFreeAgentsList().size(), 11);
    }

    @Test
    public void hirePlayersTest() {
        hireTest.hirePlayers();
        Assert.assertEquals(leagueModel.getTeam6().getRoster().getAllPlayerList().size(), 7);
        Assert.assertEquals(leagueModel.getFreeAgentsList().size(), 3);
    }

    @Test
    public void dropPlayerFromUserTeamTest() {
//        dropTest.dropPlayerFromUserTeam(leagueModel.getTeam5().getRoster().getAllPlayerList());
    }

    @Test
    public void hirePlayerInUserTeamTest() {
//        dropTest.hirePlayerInUserTeam(leagueModel.getTeam6().getRoster().getAllPlayerList());
    }
}
