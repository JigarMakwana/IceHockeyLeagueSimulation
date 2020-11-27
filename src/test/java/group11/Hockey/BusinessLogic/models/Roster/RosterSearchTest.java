package group11.Hockey.BusinessLogic.models.Roster;

import group11.Hockey.BusinessLogic.models.*;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RosterSearchTest {
    RosterMock mock = new RosterMock();
    IRoster roster = mock.team1Roster;
    IRosterSearch rosterSearch = new RosterSearch();

    @Test
    public void findWeakestPlayersTest() {
        List<Player> weakestPlayerList = rosterSearch.findWeakestPlayers(roster.getAllPlayerList(), 2);
        Assert.assertEquals(weakestPlayerList.size(), 2);
        Assert.assertEquals(weakestPlayerList.get(0).getPlayerName(), "George");
        Assert.assertEquals(weakestPlayerList.get(1).getPlayerName(), "Dick");
    }

    @Test
    public void findPlayerPositionsTest() {
        List<Integer> playerPositionFlag = new ArrayList<Integer>(Arrays.asList(0,0,0));

        playerPositionFlag = rosterSearch.findPlayerPositions(roster.getAllPlayerList());
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

        List<Player> strongestPlayerList = rosterSearch.findStrongestPlayers(roster.getAllPlayerList(), playerPositionFlag, 2);
        Assert.assertEquals(strongestPlayerList.get(0).getPlayerName(), "Tom");
        Assert.assertEquals(strongestPlayerList.get(1).getPlayerName(), "Vikash");
    }

    @Test
    public void findStrongestTradeTeamTest() {
//        List<Triplet<ITeam, List<Player>, Float>> tradingTeamsBuffer= new ArrayList<>();
//
//        Triplet<ITeam, List<Player>, Float> teamRequestEntry1 =
//                Triplet.of(teams.get(0), teams.get(0).getPlayers(),playerMiscellaneous.playersStrengthSum(teams.get(0).getPlayers()));
//        Triplet<ITeam, List<Player>, Float> teamRequestEntry2 =
//                Triplet.of(teams.get(0), teams.get(1).getPlayers(),playerMiscellaneous.playersStrengthSum(teams.get(1).getPlayers()));
//        Triplet<ITeam, List<Player>, Float> teamRequestEntry3 =
//                Triplet.of(teams.get(0), teams.get(2).getPlayers(),playerMiscellaneous.playersStrengthSum(teams.get(2).getPlayers()));
//
//        tradingTeamsBuffer.add(teamRequestEntry1);
//        tradingTeamsBuffer.add(teamRequestEntry2);
//
//        Triplet<ITeam, List<Player>, Float> strongestTeam = rosterSearch.findStrongestTradeTeam(tradingTeamsBuffer);
//        Assert.assertEquals(strongestTeam.getFirst().getTeamName(), "Boston");
    }

    @Test
    public void playersStrengthSumTest() {
        Float playersplayersStrengthSum = rosterSearch.getRosterStrength(roster.getAllPlayerList());
        Assert.assertEquals(playersplayersStrengthSum, 77.5, 0.0001);
    }

    @Test
    public void sortPlayersByStrengthTest() {
        List<Player> sortedPlayerList = rosterSearch.sortPlayersByStrength(roster.getAllPlayerList());
        Assert.assertEquals(sortedPlayerList.get(0).getPlayerName(), "George");
        Assert.assertEquals(sortedPlayerList.get(1).getPlayerName(), "Dick");
        Assert.assertEquals(sortedPlayerList.get(2).getPlayerName(), "Vikash");
        Assert.assertEquals(sortedPlayerList.get(3).getPlayerName(), "Tom");
    }

    @Test
    public void getDefenseListTest() {
        List<Player> defenseList = rosterSearch.getDefenseList(roster.getAllPlayerList());
        Assert.assertEquals(defenseList.size(), 2);
    }

    @Test
    public void getForwardListTest() {
        List<Player> forwardList = rosterSearch.getForwardList(roster.getAllPlayerList());
        Assert.assertEquals(forwardList.size(), 1);
    }

    @Test
    public void getGoalieListTest() {
        List<Player> goalieList = rosterSearch.getGoalieList(roster.getAllPlayerList());
        Assert.assertEquals(goalieList.size(), 1);
    }
}
