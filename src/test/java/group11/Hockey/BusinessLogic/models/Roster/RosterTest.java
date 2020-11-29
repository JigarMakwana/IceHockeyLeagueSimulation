package group11.Hockey.BusinessLogic.models.Roster;

import group11.Hockey.BusinessLogic.Trading.TradingMockFactory;
import group11.Hockey.BusinessLogic.Trading.TradingModelMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RosterTest {
    private TradingModelMock leagueModel;

    @Before
    public void setUp() throws Exception {
        this.leagueModel = TradingMockFactory.makeTradingMock(1.0f, 1.0f);
    }

    @Test
    public void isValidRosterTest() {
        boolean isValidRoster;
        isValidRoster = leagueModel.getTeam2().getRoster().isValidRoster();
        Assert.assertTrue(isValidRoster);
        isValidRoster = leagueModel.getTeam1().getRoster().isValidRoster();
        Assert.assertFalse(isValidRoster);
    }

    @Test
    public void isValidActiveRosterTest() {
        boolean isValidActiveRoster;
        isValidActiveRoster = leagueModel.getTeam2().getRoster().isValidActiveRoster();
        Assert.assertTrue(isValidActiveRoster);
        isValidActiveRoster = leagueModel.getTeam1().getRoster().isValidActiveRoster();
        Assert.assertFalse(isValidActiveRoster);
    }

    @Test
    public void isValidInActiveRosterTest() {
        boolean isValidInActiveRoster;
        isValidInActiveRoster = leagueModel.getTeam2().getRoster().isValidInActiveRoster();
        Assert.assertTrue(isValidInActiveRoster);
        isValidInActiveRoster = leagueModel.getTeam1().getRoster().isValidInActiveRoster();
        Assert.assertFalse(isValidInActiveRoster);
    }

    @Test
    public void swapPlayersTest() {
        leagueModel.getTeam1().getRoster().swapPlayers(leagueModel.getTeam1().getRoster().getActiveRoster().get(0),
                leagueModel.getTeam1().getRoster().getInActiveRoster().get(0));

        Assert.assertEquals(leagueModel.getTeam1().getRoster().getActiveRoster().size(), 3);
        Assert.assertEquals(leagueModel.getTeam1().getRoster().getInActiveRoster().size(), 1);

        Assert.assertEquals(leagueModel.getTeam1().getRoster().getActiveRoster().get(0).getPlayerName(), "Dick");
        Assert.assertEquals(leagueModel.getTeam1().getRoster().getInActiveRoster().get(0).getPlayerName(), "Tom");
    }
}
