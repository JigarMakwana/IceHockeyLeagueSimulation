package group11.Hockey.BusinessLogic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import group11.Hockey.BusinessLogic.models.*;
import group11.Hockey.InputOutput.ICommandLineInput;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class AITradingTest {
    TradingModelMock leagueModel = new TradingModelMock(1.0f, 1.0f);
    private League leagueObj = leagueModel.getLeagueInfo();
    private AITrading aiTradingObj = new AITrading(leagueObj);

    @Test
    public void generateTradeOffersTest() {
        aiTradingObj.generateTradeOffers();
        List<Team> teamList = new ArrayList<>();
        teamList = leagueModel.getTeamList();
        Team team1 = teamList.get(0);
        List<Player> playerList1 = team1.getPlayers();
        Team team2 = teamList.get(1);
        List<Player> playerList2 = team2.getPlayers();

        Assert.assertEquals(playerList1.get(0).getPlayerName(), "Harry");
        Assert.assertEquals(playerList1.get(1).getPlayerName(), "Tom");
        Assert.assertEquals(playerList1.get(2).getPlayerName(), "Suresh");
        Assert.assertEquals(playerList1.get(3).getPlayerName(), "Lokesh");

        Assert.assertEquals(playerList2.get(0).getPlayerName(), "Mahesh");
        Assert.assertEquals(playerList2.get(1).getPlayerName(), "Ramesh");
        Assert.assertEquals(playerList2.get(2).getPlayerName(), "Dick");
        Assert.assertEquals(playerList2.get(3).getPlayerName(), "Jerry");
    }

    @Test
    public void determineEligibleTeams() {
        List<Team> teamList = new ArrayList<>();
        teamList = aiTradingObj.determineEligibleTeams();
        Assert.assertEquals(teamList.size(), 2);
    }

    @Test
    public void resolveAIToAITradeTest() {
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


        Player player5 = new Player(10, 10, 10, 1, "Jatin", "defense", false, false, 25);
        Player player6 = new Player(11, 12, 13, 1, "Alex", "forward", false, false, 30);
        List<Player> offeredPlayerList2 = new ArrayList<Player>();
        offeredPlayerList2.add(player5);
        offeredPlayerList2.add(player6);
        Team team3 = new Team("Daredevils", "Mister Fred", null, offeredPlayerList);

        Player player7 = new Player(14, 15, 15, 1, "Jigar", "defense", false, false, 28);
        Player player8 = new Player(10, 16, 20, 1, "Raj", "forward",false, false, 30);
        List<Player> requestedPlayerList2 = new ArrayList<Player>();
        requestedPlayerList2.add(player7);
        requestedPlayerList2.add(player8);
        Team team4 = new Team("Sunrisers", "Mister Smith", null, requestedPlayerList);

        TradingModelMock leagueModel2 = new TradingModelMock(0.0f, 0.0f);
        League leagueObj2 = leagueModel2.getLeagueInfo();
        AITrading aiTradingObj2 = new AITrading(leagueObj2);

        aiTradingObj2.resolveAIToAITrade(team3, offeredPlayerList2, team4, requestedPlayerList2);
        Assert.assertEquals(offeredPlayerList2.size(), 2);
        Assert.assertEquals(offeredPlayerList2.get(0).getPlayerName(), "Jatin");
        Assert.assertEquals(offeredPlayerList2.get(1).getPlayerName(), "Alex");

        Assert.assertEquals(requestedPlayerList2.size(), 2);
        Assert.assertEquals(requestedPlayerList2.get(0).getPlayerName(), "Jigar");
        Assert.assertEquals(requestedPlayerList2.get(1).getPlayerName(), "Raj");
    }

    @Test
    public void resolveAIToUserTradeTest() {
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
////        UserInputValidation userInput = mock(UserInputValidation.class);
////        when(userInput.validateUserTradeInput()).thenReturn(1);
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
    public void rejectTradeTest() {
        Team team = new Team("Halifax Superkings", "John", null, null);
        aiTradingObj.rejectTrade(team);
        Assert.assertEquals(team.getLosses(), 0);
    }

    @Test
    public void acceptTradeTest() {
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
    public void resetLossPointsTest() {
        List<Team> teamList = new ArrayList<>();
        teamList = leagueModel.getTeamList();
        Team team1 = teamList.get(0);
        aiTradingObj.resetLossPoints(team1);
        Assert.assertEquals(team1.getLosses(), 0);
    }
}
