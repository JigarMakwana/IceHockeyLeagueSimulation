package group11.Hockey;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import group11.Hockey.BusinessLogic.models.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AITradingTest {
    TradingModelMock leagueModel = new TradingModelMock();
    private League leagueObj = leagueModel.getLeagueInfo();
    private Trading tradingConfig = new Trading(2, 1, 2, 1);;
    private AITrading aiTradingObj = new AITrading(leagueObj);

    @Test
    void generateTradeOffersTest() {
//        ICommandLineInput userInputMode = mock(ICommandLineInput.class);
//        when(userInputMode.getInt()).thenReturn(1);
//
//        aiTradingObj.generateTradeOffers();
////        Assert.assertEquals(playerList.size(), 2);
    }

    @Test
    void determineEligibleTeams() {
        List<Team> teamList = new ArrayList<>();
        teamList = aiTradingObj.determineEligibleTeams();
        Assert.assertEquals(teamList.size(), 5);
    }

    @Test
    void findWeakestPlayers() {
        Player player1 = new Player(11, 12, 13, 1, "Player 1", "defense", true, false, 50);
        Player player2 = new Player(7, 8, 9, 1, "Player 2", "forward", true, true, 20);
        Player player3 = new Player(5, 6, 7, 1, "Player 3", "forward", true, true, 20);

        List<Player> playerList = new ArrayList<Player>();
        List<Player> weakestPlayerList = new ArrayList<Player>();

        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        Team team = new Team("Halifax Superkings", "John", null, playerList);

        weakestPlayerList = aiTradingObj.findWeakestPlayers(team);
        Assert.assertEquals(weakestPlayerList.size(), 2);
        Assert.assertEquals(weakestPlayerList.get(0).getPlayerName(), "Player 3");
        Assert.assertEquals(weakestPlayerList.get(1).getPlayerName(), "Player 2");
    }

    @Test
    void findPlayerPositions() {
        Player player1 = new Player(11, 12, 13, 1, "Player 1", "defense", true, false, 50);
        Player player2 = new Player(7, 8, 9, 1, "Player 2", "forward", true, true, 20);
        Player player3 = new Player(5, 6, 7, 1, "Player 3", "forward", true, true, 20);

        List<Player> playerList = new ArrayList<Player>();

        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        List<Integer> playerPositionFlag = new ArrayList<Integer>(Arrays.asList(0,0,0));

        playerPositionFlag = aiTradingObj.findPlayerPositions(playerList);
        Assert.assertEquals(playerPositionFlag.size(), 3);
        int noOfForward = playerPositionFlag.get(0);
        Assert.assertEquals(noOfForward, 2);
        int noOfDefense = playerPositionFlag.get(1);
        Assert.assertEquals(noOfDefense, 1);
        int noOfGoalie = playerPositionFlag.get(2);
        Assert.assertEquals(noOfGoalie, 0);
    }

    @Test
    void findStrongestPlayers() {
        Player player1 = new Player(11, 12, 13, 1, "Player 1", "defense", true, false, 50);
        Player player2 = new Player(7, 8, 9, 1, "Player 2", "forward", true, true, 20);
        Player player3 = new Player(5, 6, 7, 1, "Player 3", "forward", true, true, 20);

        List<Player> playerList = new ArrayList<Player>();
        List<Player> strongestPlayerList = new ArrayList<Player>();

        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        Team team = new Team("Halifax Superkings", "John", null, playerList);
        List<Integer> playerPositionFlag = new ArrayList<Integer>(Arrays.asList(1,1,0));

        strongestPlayerList = aiTradingObj.findStrongestPlayers(team, playerPositionFlag);
        Assert.assertEquals(strongestPlayerList.size(), 2);
        Assert.assertEquals(strongestPlayerList.get(0).getPlayerName(), "Player 2");
        Assert.assertEquals(strongestPlayerList.get(1).getPlayerName(), "Player 1");
    }

    @Test
    void findStrongestTradeTeam() {
        List<Conference> conferences = leagueObj.getConferences();
        List<Division> divisions = conferences.get(0).getDivisions();
        List<Team> teams = divisions.get(0).getTeams();
        List<Triplet<Team, List<Player>, Float>> tradingTeamsBuffer= new ArrayList<>();

        Triplet<Team, List<Player>, Float> teamRequestEntry1 =
                Triplet.of(teams.get(0), teams.get(0).getPlayers(),aiTradingObj.strengthSum(teams.get(0).getPlayers()));
        Triplet<Team, List<Player>, Float> teamRequestEntry2 =
                Triplet.of(teams.get(0), teams.get(1).getPlayers(),aiTradingObj.strengthSum(teams.get(1).getPlayers()));
        Triplet<Team, List<Player>, Float> teamRequestEntry3 =
                Triplet.of(teams.get(0), teams.get(2).getPlayers(),aiTradingObj.strengthSum(teams.get(2).getPlayers()));

        tradingTeamsBuffer.add(teamRequestEntry1);
        tradingTeamsBuffer.add(teamRequestEntry2);
        tradingTeamsBuffer.add(teamRequestEntry3);

        Triplet<Team, List<Player>, Float> strongestTeam = aiTradingObj.findStrongestTradeTeam(tradingTeamsBuffer);
        Assert.assertEquals(strongestTeam.getFirst().getTeamName(), "Boston");
    }

    @Test
    void resolveAIToAITrade() {
        Player player1 = new Player(10, 10, 10, 1, "Jatin", "defense", false, false, 25);
        Player player2 = new Player(11, 12, 13, 1, "Alex", "forward", false, false, 30);
        List<Player> offeredPlayerList = new ArrayList<Player>();
        offeredPlayerList.add(player1);
        offeredPlayerList.add(player2);
        Team team1 = new Team("Daredevils", "Mister Fred", null, offeredPlayerList);

        Player player3 = new Player(14, 15, 15, 1, "Jigar", "defense", false, false, 28);
        Player player4 = new Player(10, 16, 20, 1, "Raj", "forward",false, false, 30);
        List<Player> requestedPlayerList = new ArrayList<Player>();
        requestedPlayerList.add(player3);
        requestedPlayerList.add(player4);
        Team team2 = new Team("Sunrisers", "Mister Smith", null, requestedPlayerList);

        aiTradingObj.resolveAIToAITrade(team1, offeredPlayerList, team2, requestedPlayerList);
        Assert.assertEquals(offeredPlayerList.size(), 2);
        Assert.assertEquals(offeredPlayerList.get(0).getPlayerName(), "Jigar");
        Assert.assertEquals(offeredPlayerList.get(1).getPlayerName(), "Raj");

        Assert.assertEquals(requestedPlayerList.size(), 2);
        Assert.assertEquals(requestedPlayerList.get(0).getPlayerName(), "Jatin");
        Assert.assertEquals(requestedPlayerList.get(1).getPlayerName(), "Alex");
    }

    @Test
    void resolveAIToUserTrade() {
//        Player player1 = new Player(10, 10, 10, 1, "Jatin", "defense", false, false, 25);
//        Player player2 = new Player(11, 12, 13, 1, "Alex", "forward", false, false, 30);
//        List<Player> offeredPlayerList = new ArrayList<Player>();
//        offeredPlayerList.add(player1);
//        offeredPlayerList.add(player2);
//        Team team1 = new Team("Daredevils", "Mister Fred", null, offeredPlayerList);
//
//        Player player3 = new Player(14, 15, 15, 1, "Jigar", "defense", false, false, 28);
//        Player player4 = new Player(10, 16, 20, 1, "Raj", "forward",false, false, 30);
//        List<Player> requestedPlayerList = new ArrayList<Player>();
//        requestedPlayerList.add(player3);
//        requestedPlayerList.add(player4);
//        Team team2 = new Team("Sunrisers", "Mister Smith", null, requestedPlayerList);
//
//        ICommandLineInput userInputMode = mock(ICommandLineInput.class);
//        when(userInputMode.getInt()).thenReturn(1);
//
//        aiTradingObj.resolveAIToUserTrade(team1, offeredPlayerList, team2, requestedPlayerList);
//        Assert.assertEquals(offeredPlayerList.size(), 2);
//        Assert.assertEquals(offeredPlayerList.get(0).getPlayerName(), "Jigar");
//        Assert.assertEquals(offeredPlayerList.get(1).getPlayerName(), "Raj");
//
//        Assert.assertEquals(requestedPlayerList.size(), 2);
//        Assert.assertEquals(requestedPlayerList.get(0).getPlayerName(), "Jatin");
//        Assert.assertEquals(requestedPlayerList.get(1).getPlayerName(), "Alex");
    }

    @Test
    void strengthSum() {
        Player player1 = new Player(10, 10, 10, 10, "Player One", "defense", true, false, 50);
        Player player2 = new Player(10, 10, 10, 10, "Agent one", "forward", true, true, 20);
        List<Player> playerList = new ArrayList<Player>();
        playerList.add(player1);
        playerList.add(player2);

        Float playersStrengthSum = aiTradingObj.strengthSum(playerList);
        Assert.assertEquals(playersStrengthSum, 50.0, 0.0001);
    }

    @Test
    void rejectTrade() {
        Team team = new Team("Halifax Superkings", "John", null, null);
        aiTradingObj.rejectTrade(team);
        Assert.assertEquals(team.getLossPoint(), 0);
    }

    @Test
    void acceptTrade() {
        Player player1 = new Player(10, 10, 10, 1, "Jatin", "defense", false, false, 25);
        Player player2 = new Player(11, 12, 13, 1, "Alex", "forward", false, false, 30);
        List<Player> offeredPlayerList = new ArrayList<Player>();
        offeredPlayerList.add(player1);
        offeredPlayerList.add(player2);
        Team team1 = new Team("Daredevils", "Mister Fred", null, offeredPlayerList);

        Player player3 = new Player(14, 15, 15, 1, "Jigar", "defense", false, false, 28);
        Player player4 = new Player(10, 16, 20, 1, "Raj", "forward",false, false, 30);
        List<Player> requestedPlayerList = new ArrayList<Player>();
        requestedPlayerList.add(player3);
        requestedPlayerList.add(player4);
        Team team2 = new Team("Sunrisers", "Mister Smith", null, requestedPlayerList);

        aiTradingObj.acceptTrade(team1, offeredPlayerList, team2, requestedPlayerList);
        Assert.assertEquals(offeredPlayerList.size(), 2);
        Assert.assertEquals(offeredPlayerList.get(0).getPlayerName(), "Jigar");
        Assert.assertEquals(offeredPlayerList.get(1).getPlayerName(), "Raj");

        Assert.assertEquals(requestedPlayerList.size(), 2);
        Assert.assertEquals(requestedPlayerList.get(0).getPlayerName(), "Jatin");
        Assert.assertEquals(requestedPlayerList.get(1).getPlayerName(), "Alex");
    }

    @Test
    void resetLossPoints() {
        Team team = new Team("Halifax Superkings", "John", null, null);
        aiTradingObj.resetLossPoints(team);
        Assert.assertEquals(team.getLossPoint(), 0);
    }

    @Test
    void getForwardList() {
        Player player1 = new Player(10, 10, 10, 10, "Player 1", "defense", true, false, 50);
        Player player2 = new Player(10, 10, 10, 10, "Player 2", "forward", true, true, 20);
        List<Player> playerList = new ArrayList<Player>();
        List<Player> forwardList = new ArrayList<Player>();

        playerList.add(player1);
        playerList.add(player2);

        forwardList = aiTradingObj.getForwardList(playerList);
        Assert.assertEquals(forwardList.size(), 1);
        Assert.assertEquals(forwardList.get(0).getPlayerName(), "Player 2");
    }

    @Test
    void getDefenseList() {
        Player player1 = new Player(10, 10, 10, 10, "Player 1", "defense", true, false, 50);
        Player player2 = new Player(10, 10, 10, 10, "Player 2", "forward", true, true, 20);
        List<Player> playerList = new ArrayList<Player>();
        List<Player> defenseList = new ArrayList<Player>();

        playerList.add(player1);
        playerList.add(player2);

        defenseList = aiTradingObj.getDefenseList(playerList);
        Assert.assertEquals(defenseList.size(), 1);
        Assert.assertEquals(defenseList.get(0).getPlayerName(), "Player 1");
    }

    @Test
    void getGoalieList() {
        Player player1 = new Player(10, 10, 10, 10, "Player 1", "goalie", true, false, 50);
        Player player2 = new Player(10, 10, 10, 10, "Player 2", "forward", true, true, 20);
        List<Player> playerList = new ArrayList<Player>();
        List<Player> goalieList = new ArrayList<Player>();

        playerList.add(player1);
        playerList.add(player2);

        goalieList = aiTradingObj.getGoalieList(playerList);
        Assert.assertEquals(goalieList.size(), 1);
        Assert.assertEquals(goalieList.get(0).getPlayerName(), "Player 1");
    }
}
