package group11.Hockey.BusinessLogic;

import group11.Hockey.BusinessLogic.Trading.PlayerTradeOperations;
import group11.Hockey.BusinessLogic.Trading.TradingModelMock;
import group11.Hockey.BusinessLogic.models.*;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerTradeOperationsTest {
    TradingModelMock leagueModel = new TradingModelMock(1.0f, 1.0f);
    private League leagueObj = leagueModel.getLeagueInfo();
    IgmTable gmTbale = new gmTable(-0.1f, 0.1f, 0.0f);
    private Trading tradingConfig = new Trading(2, 1, 2, 1, gmTbale);
    private PlayerTradeOperations playerMiscellaneous = new PlayerTradeOperations(tradingConfig);

    @Test
    public void findWeakestPlayersTest() {
        Player player1 = new Player(11, 12, 13, 1, "Player 1", "defense", true, false, 50);
        Player player2 = new Player(7, 8, 9, 1, "Player 2", "forward", true, true, 20);
        Player player3 = new Player(5, 6, 7, 1, "Player 3", "forward", true, true, 20);

        List<Player> playerList = new ArrayList<Player>();
        List<Player> weakestPlayerList = new ArrayList<Player>();

        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        Team team = new Team("Halifax Superkings", null, null, playerList);

        weakestPlayerList = playerMiscellaneous.findWeakestPlayers(team);
        Assert.assertEquals(weakestPlayerList.size(), 2);
        Assert.assertEquals(weakestPlayerList.get(0).getPlayerName(), "Player 3");
        Assert.assertEquals(weakestPlayerList.get(1).getPlayerName(), "Player 2");
    }

    @Test
    public void findPlayerPositionsTest() {
        Player player1 = new Player(11, 12, 13, 1, "Player 1", "defense", true, false, 50);
        Player player2 = new Player(7, 8, 9, 1, "Player 2", "forward", true, true, 20);
        Player player3 = new Player(5, 6, 7, 1, "Player 3", "forward", true, true, 20);

        List<Player> playerList = new ArrayList<Player>();

        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        List<Integer> playerPositionFlag = new ArrayList<Integer>(Arrays.asList(0,0,0));

        playerPositionFlag = playerMiscellaneous.findPlayerPositions(playerList);
        Assert.assertEquals(playerPositionFlag.size(), 3);
        int noOfForward = playerPositionFlag.get(0);
        Assert.assertEquals(noOfForward, 2);
        int noOfDefense = playerPositionFlag.get(1);
        Assert.assertEquals(noOfDefense, 1);
        int noOfGoalie = playerPositionFlag.get(2);
        Assert.assertEquals(noOfGoalie, 0);
    }

    @Test
    public void findStrongestPlayersTest() {
        Player player1 = new Player(11, 12, 13, 1, "Player 1", "defense", true, false, 50);
        Player player2 = new Player(7, 8, 9, 1, "Player 2", "forward", true, true, 20);
        Player player3 = new Player(5, 6, 7, 1, "Player 3", "forward", true, true, 20);

        List<Player> playerList = new ArrayList<Player>();
        List<Player> strongestPlayerList = new ArrayList<Player>();

        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        Team team = new Team("Halifax Superkings", null, null, playerList);
        List<Integer> playerPositionFlag = new ArrayList<Integer>(Arrays.asList(1,1,0));

        strongestPlayerList = playerMiscellaneous.findStrongestPlayers(team, playerPositionFlag);
        Assert.assertEquals(strongestPlayerList.size(), 2);
        Assert.assertEquals(strongestPlayerList.get(0).getPlayerName(), "Player 2");
        Assert.assertEquals(strongestPlayerList.get(1).getPlayerName(), "Player 1");
    }

    @Test
    public void findStrongestTradeTeamTest() {
        List<IConference> conferences = leagueObj.getConferences();
        List<Division> divisions = conferences.get(0).getDivisions();
        List<Team> teams = divisions.get(0).getTeams();
        List<Triplet<Team, List<Player>, Float>> tradingTeamsBuffer= new ArrayList<>();

        Triplet<Team, List<Player>, Float> teamRequestEntry1 =
                Triplet.of(teams.get(0), teams.get(0).getPlayers(),playerMiscellaneous.playersStrengthSum(teams.get(0).getPlayers()));
        Triplet<Team, List<Player>, Float> teamRequestEntry2 =
                Triplet.of(teams.get(0), teams.get(1).getPlayers(),playerMiscellaneous.playersStrengthSum(teams.get(1).getPlayers()));
        Triplet<Team, List<Player>, Float> teamRequestEntry3 =
                Triplet.of(teams.get(0), teams.get(2).getPlayers(),playerMiscellaneous.playersStrengthSum(teams.get(2).getPlayers()));

        tradingTeamsBuffer.add(teamRequestEntry1);
        tradingTeamsBuffer.add(teamRequestEntry2);
        tradingTeamsBuffer.add(teamRequestEntry3);

        Triplet<Team, List<Player>, Float> strongestTeam = playerMiscellaneous.findStrongestTradeTeam(tradingTeamsBuffer);
        Assert.assertEquals(strongestTeam.getFirst().getTeamName(), "Boston");
    }

    @Test
    public void playersStrengthSumTest() {
        Player player1 = new Player(10, 10, 10, 10, "Player One", "defense", true, false, 50);
        Player player2 = new Player(10, 10, 10, 10, "Agent one", "forward", true, true, 20);
        List<Player> playerList = new ArrayList<Player>();
        playerList.add(player1);
        playerList.add(player2);

        Float playersplayersStrengthSum = playerMiscellaneous.playersStrengthSum(playerList);
        Assert.assertEquals(playersplayersStrengthSum, 50.0, 0.0001);
    }

    @Test
    public void getForwardListTest() {
        Player player1 = new Player(10, 10, 10, 10, "Player 1", "defense", true, false, 50);
        Player player2 = new Player(10, 10, 10, 10, "Player 2", "forward", true, true, 20);
        List<Player> playerList = new ArrayList<Player>();
        List<Player> forwardList = new ArrayList<Player>();

        playerList.add(player1);
        playerList.add(player2);

        forwardList = playerMiscellaneous.getForwardList(playerList);
        Assert.assertEquals(forwardList.size(), 1);
        Assert.assertEquals(forwardList.get(0).getPlayerName(), "Player 2");
    }

    @Test
    public void getDefenseListTest() {
        Player player1 = new Player(10, 10, 10, 10, "Player 1", "defense", true, false, 50);
        Player player2 = new Player(10, 10, 10, 10, "Player 2", "forward", true, true, 20);
        List<Player> playerList = new ArrayList<Player>();
        List<Player> defenseList = new ArrayList<Player>();

        playerList.add(player1);
        playerList.add(player2);

        defenseList = playerMiscellaneous.getDefenseList(playerList);
        Assert.assertEquals(defenseList.size(), 1);
        Assert.assertEquals(defenseList.get(0).getPlayerName(), "Player 1");
    }

    @Test
    public void getGoalieListTest() {
        Player player1 = new Player(10, 10, 10, 10, "Player 1", "goalie", true, false, 50);
        Player player2 = new Player(10, 10, 10, 10, "Player 2", "forward", true, true, 20);
        List<Player> playerList = new ArrayList<Player>();
        List<Player> goalieList = new ArrayList<Player>();

        playerList.add(player1);
        playerList.add(player2);

        goalieList = playerMiscellaneous.getGoalieList(playerList);
        Assert.assertEquals(goalieList.size(), 1);
        Assert.assertEquals(goalieList.get(0).getPlayerName(), "Player 1");
    }

    @Test
    public void sortPlayersByStrengthTest() {
        Player player1 = new Player(14, 12, 10, 1, "Jatin", "defense", false, false, 25);
        Player player2 = new Player(8, 9, 8, 1, "Alex", "forward", false, false, 30);
        Player player3 = new Player(5, 6, 7, 1, "Jigar", "defense", false, false, 28);
        List<Player> playerList = new ArrayList<Player>();
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);

        List<Player> sortedPlayerList = playerMiscellaneous.sortPlayersByStrength(playerList);
        Assert.assertEquals(sortedPlayerList.get(0).getPlayerName(), "Jigar");
        Assert.assertEquals(sortedPlayerList.get(1).getPlayerName(), "Alex");
        Assert.assertEquals(sortedPlayerList.get(2).getPlayerName(), "Jatin");
    }
}
