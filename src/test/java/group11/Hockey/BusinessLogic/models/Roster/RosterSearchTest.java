package group11.Hockey.BusinessLogic.models.Roster;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.Enums.Positions;
import group11.Hockey.BusinessLogic.Trading.TradingMockFactory;
import group11.Hockey.BusinessLogic.Trading.TradingModelMock;
import group11.Hockey.BusinessLogic.Trading.TradingTriplet.Triplet;
import group11.Hockey.BusinessLogic.models.*;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RosterSearchTest {
    private TradingModelMock leagueModel;
    private IRosterSearch rosterSearch;

    @Before
    public void setUp() throws Exception {
        this.leagueModel = TradingMockFactory.makeTradingMock(1.0f, 1.0f);
        this.rosterSearch = DefaultHockeyFactory.makeRosterSearch();
    }

    @Test
    public void findWeakestPlayersTest() {
        List<IPlayer> weakestPlayerList = rosterSearch.findWeakestPlayers(leagueModel.getTeam3().getPlayers(), 2);
        Assert.assertEquals(weakestPlayerList.size(), 2);
        Assert.assertEquals(weakestPlayerList.get(0).getPlayerName(), "Raj");
    }

    @Test
    public void findPlayerPositionsTest() {
        List<Integer> playerPositionFlag;
        playerPositionFlag = rosterSearch.findPlayerPositions(leagueModel.getTeam3().getPlayers());
        Assert.assertEquals(playerPositionFlag.size(), 3);
        int noOfForward = playerPositionFlag.get(0);
        Assert.assertEquals(noOfForward, 1);
        int noOfDefense = playerPositionFlag.get(1);
        Assert.assertEquals(noOfDefense, 2);
        int noOfGoalie = playerPositionFlag.get(2);
        Assert.assertEquals(noOfGoalie, 1);
    }

    @Test
    public void findStrongestPlayersTest() {
        List<Integer> playerPositionFlag = new ArrayList<Integer>(Arrays.asList(1,0,1));
        List<IPlayer> strongestPlayerList = rosterSearch.findStrongestPlayers(leagueModel.getTeam3().getPlayers(), playerPositionFlag, 2);
        Assert.assertEquals(strongestPlayerList.get(0).getPlayerName(), "Jigar");
    }

    @Test
    public void findStrongestTradeTeamTest() {
        Triplet<ITeam, List<IPlayer>, Float> strongestTeam = rosterSearch.findStrongestTradeTeam(leagueModel.getTradingTeamsBuffer());
        Assert.assertEquals(strongestTeam.getFirst().getTeamName(), "Viena");
    }

    @Test
    public void playersStrengthSumTest() {
        Float playersplayersStrengthSum = rosterSearch.getRosterStrength(leagueModel.getTeam3().getPlayers());
        Assert.assertEquals(playersplayersStrengthSum, 102.5, 0.0001);
    }

    @Test
    public void sortPlayersByStrengthTest() {
        List<IPlayer> sortedPlayerList = rosterSearch.sortPlayersByStrength(leagueModel.getTeam3().getPlayers());
        Assert.assertEquals(sortedPlayerList.get(0).getPlayerName(), "Raj");
        Assert.assertEquals(sortedPlayerList.get(1).getPlayerName(), "Alex");
        Assert.assertEquals(sortedPlayerList.get(2).getPlayerName(), "Jatin");
        Assert.assertEquals(sortedPlayerList.get(3).getPlayerName(), "Jigar");
    }

    @Test
    public void getDefenseListTest() {
        List<IPlayer> defenseList = rosterSearch.getDefenseList(leagueModel.getTeam3().getPlayers());
        Assert.assertEquals(defenseList.size(), 2);
    }

    @Test
    public void getForwardListTest() {
        List<IPlayer> forwardList = rosterSearch.getForwardList(leagueModel.getTeam3().getPlayers());
        Assert.assertEquals(forwardList.size(), 1);
    }

    @Test
    public void getGoalieListTest() {
        List<IPlayer> goalieList = rosterSearch.getGoalieList(leagueModel.getTeam3().getPlayers());
        Assert.assertEquals(goalieList.size(), 1);
    }

    @Test
    public void findStrongestPlayerByPositionTest() {
        IPlayer p = rosterSearch.findStrongestPlayerByPosition(leagueModel.getTeam5().getPlayers(), Positions.FORWARD);
        Assert.assertEquals(p.getPlayerName(), "Ishan");
    }

    @Test
    public void findWeakestPlayerByPositionTest() {
        IPlayer p = rosterSearch.findWeakestPlayerByPosition(leagueModel.getTeam5().getPlayers(), Positions.DEFENSE);
        Assert.assertEquals(p.getPlayerName(), "East");
    }

    @Test
    public void getRosterStrengthTest() {
        Float rosterStrength = rosterSearch.getRosterStrength(leagueModel.getTeam3().getPlayers());
        Assert.assertEquals(rosterStrength, 102.5, 0.0001);
    }

    @Test
    public void averageTeamStrengthTest() {
        Float avgTeamStrengthSum = rosterSearch.averageTeamStrength(leagueModel.getTeamList());
        Assert.assertEquals(avgTeamStrengthSum, 284.4285583496094, 0.000);
    }

    @Test
    public void findStrongestTeamTest() {
        ITeam team = rosterSearch.findStrongestTeam(leagueModel.getTeamList());
        Assert.assertEquals(team.getTeamName(), "Florida");
    }
}
