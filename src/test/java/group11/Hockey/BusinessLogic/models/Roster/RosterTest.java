package group11.Hockey.BusinessLogic.models.Roster;

import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class RosterTest {
    RosterMock mock = new RosterMock();
    IRoster roster = mock.team1Roster;
    @Test
    void isValidRosterTest() {
        boolean isValidRoster;
        isValidRoster = roster.isValidRoster();
        Assert.assertTrue(isValidRoster);
        roster.setSuppliedActiveRosterSize(3);
        isValidRoster = roster.isValidRoster();
        Assert.assertFalse(isValidRoster);
    }

    @Test
    void isValidActiveRosterTest() {
        boolean isValidActiveRoster;
        isValidActiveRoster = roster.isValidActiveRoster();
        Assert.assertTrue(isValidActiveRoster);
        roster.setSuppliedActiveRosterSize(3);;
        isValidActiveRoster = roster.isValidActiveRoster();
        Assert.assertFalse(isValidActiveRoster);
    }

    @Test
    void isValidInActiveRosterTest() {
        boolean isValidInActiveRoster;
        isValidInActiveRoster = roster.isValidInActiveRoster();
        Assert.assertTrue(isValidInActiveRoster);
        roster.setSuppliedInActiveRosterSize(3);
        isValidInActiveRoster = roster.isValidInActiveRoster();
        Assert.assertFalse(isValidInActiveRoster);
    }

    @Test
    void swapPlayersTest() {
        roster.swapPlayers(mock.activePlayer1, mock.inActivePlayer1);

        Assert.assertEquals(roster.getActiveRoster().size(), 2);
        Assert.assertEquals(roster.getInActiveRoster().size(), 2);

        Assert.assertEquals(roster.getActiveRoster().get(1).getPlayerName(), "Vikash");
        Assert.assertEquals(roster.getInActiveRoster().get(1).getPlayerName(), "Tom");
    }
}
