package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeInitializer;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradingConfig;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TradeInitializerTest {
    private TradingModelMock leagueModel;
    private League leagueObj;
    private ITradeInitializer aiTradingObj;

    @Before
    public void setUp() throws Exception {
        leagueModel = new TradingModelMock(1.0f, 1.0f);
        leagueObj = leagueModel.getLeagueInfo();
        aiTradingObj = new TradeInitializer(leagueObj);
    }

    @Test
    public void getTradingConfigTest() {
        ITradingConfig config = aiTradingObj.getTradingConfig();
        Assert.assertEquals(config.getRandomTradeOfferChance(), 1.0f, 0.0001);
        Assert.assertEquals(config.getRandomAcceptanceChance(), 1.0f, 0.0001);
        Assert.assertEquals(config.getLossPoint(), 2);
        Assert.assertEquals(config.getMaxPlayersPerTrade(), 2);
        Assert.assertEquals(config.getGmTable().getGambler(), 0.1, 0.0001);
        Assert.assertEquals(config.getGmTable().getShrewd(), -0.1, 0.0001);
        Assert.assertEquals(config.getGmTable().getNormal(), 0.0, 0.0001);
    }

    @Test
    public void isTradePossibleTest() {
        Assert.assertTrue(aiTradingObj.isTradePossible(leagueModel.getTeamList().get(3)));
    }

    @Test
    public void getEligibleTeamsTest() {
        List<Team> eligibleTeamList = aiTradingObj.getEligibleTeams();
        Assert.assertEquals(eligibleTeamList.size(), 4);
        Assert.assertEquals(eligibleTeamList.get(0).getTeamName(), "Boston");
        Assert.assertEquals(eligibleTeamList.get(1).getTeamName(), "Miami");
    }
}
