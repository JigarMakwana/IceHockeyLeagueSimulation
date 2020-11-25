package group11.Hockey.BusinessLogic.models.Roster;

import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;
import org.junit.Assert;
import org.junit.Test;

public class RosterTest {
    RosterMock mock = new RosterMock();
    IRoster roster = mock.team1Roster;
    @Test
    public void isValidRosterTest() {
        boolean isValidRoster;
        isValidRoster = roster.isValidRoster();
        Assert.assertTrue(isValidRoster);
        Player a1 = new Player(15, 18, 12, 1, "Knata", "forward", true, false, 25, true);
        roster.getActiveRoster().add(a1);
        isValidRoster = roster.isValidRoster();
        Assert.assertFalse(isValidRoster);
    }

    @Test
    public void isValidActiveRosterTest() {
        boolean isValidActiveRoster;
        isValidActiveRoster = roster.isValidActiveRoster();
        Assert.assertTrue(isValidActiveRoster);
        Player a1 = new Player(15, 18, 12, 1, "sonata", "forward", true, false, 25, true);
        roster.getActiveRoster().add(a1);
        isValidActiveRoster = roster.isValidActiveRoster();
        Assert.assertFalse(isValidActiveRoster);
    }

    @Test
    public void isValidInActiveRosterTest() {
        boolean isValidInActiveRoster;
        isValidInActiveRoster = roster.isValidInActiveRoster();
        Assert.assertTrue(isValidInActiveRoster);
        Player a1 = new Player(15, 18, 12, 1, "Cooper", "forward", true, false, 25, false);
        roster.getInActiveRoster().add(a1);
        isValidInActiveRoster = roster.isValidInActiveRoster();
        Assert.assertFalse(isValidInActiveRoster);
    }

    @Test
    public void swapPlayersTest() {
        roster.swapPlayers(mock.activePlayer1, mock.inActivePlayer1);

        Assert.assertEquals(roster.getActiveRoster().size(), 2);
        Assert.assertEquals(roster.getInActiveRoster().size(), 2);

        Assert.assertEquals(roster.getActiveRoster().get(1).getPlayerName(), "Vikash");
        Assert.assertEquals(roster.getInActiveRoster().get(1).getPlayerName(), "Tom");
    }
}
