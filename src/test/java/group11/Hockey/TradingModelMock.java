package group11.Hockey;

import group11.Hockey.models.*;

import java.util.ArrayList;
import java.util.List;

public class TradingModelMock {
    private League league;

    public TradingModelMock() {
        super();

        addLeague();
    }

    private void addLeague() {

        List<Player> playerList1 = new ArrayList<Player>();
        List<Player> playerList2 = new ArrayList<Player>();
        List<Player> playerList3 = new ArrayList<Player>();
        List<Player> playerList4 = new ArrayList<Player>();
        List<Player> playerList5 = new ArrayList<Player>();

        List<Team> teamsList = new ArrayList<Team>();

        Aging aging = new Aging(30, 55);
        GameResolver gameResolver = new GameResolver(0);
        Injuries injuries = new Injuries(1, 1, 100);
        Training training = new Training(0);
        Trading trading = new Trading(2, 0.05f, 2, 0.05f);

        GameplayConfig gameplayConfig = new GameplayConfig(aging, gameResolver, injuries, training, trading);

        Player player1 = new Player(15, 18, 12, 1, "Tom", "forward", true, false, 25);
        Player player2 = new Player(10, 10, 10, 1, "Dick", "defense", false, false, 28);
        Player player3 = new Player(10, 4, 9, 18, "Harry", "goalie", false, false, 30);
        Player player4 = new Player(10, 10, 10, 1, "Jerry", "defense", false, false, 21);

        playerList1.add(player1);
        playerList1.add(player2);
        playerList1.add(player3);
        playerList1.add(player4);

        Team team1 = new Team("Boston", "Mister Fred", null, playerList1);

        playerList2 = new ArrayList<Player>();
        Player player5 = new Player(10, 20, 13, 1, "Ramesh", "forward", true, false, 30);
        Player player6 = new Player(12, 10, 14, 1, "Suresh", "defense", false, false, 31);
        Player player7 = new Player(10, 8, 9, 12, "Mahesh", "goalie", false, false, 32);
        Player player8 = new Player(10, 15, 10, 1, "Lokesh", "defense", false, false, 33);
        playerList2.add(player5);
        playerList2.add(player6);
        playerList2.add(player7);
        playerList2.add(player8);

        Team team2 = new Team("Miami", "John Smith", null, playerList2);

        playerList3 = new ArrayList<Player>();
        Player player9 = new Player(12, 11, 11, 1, "Jigar", "forward", true, false, 23);
        Player player10 = new Player(7, 8, 9, 1, "Raj", "defense", false, false, 24);
        Player player11= new Player(10, 5, 5, 18, "Jatin", "goalie", false, false, 25);
        Player player12 = new Player(10, 12, 10, 1, "Alex", "defense", false, false, 26);
        playerList3.add(player9);
        playerList3.add(player10);
        playerList3.add(player11);
        playerList3.add(player12);

        Team team3 = new Team("NewYork", "Marry Pascal", null, playerList3);

        playerList4 = new ArrayList<Player>();
        Player player13 = new Player(15, 15, 15, 1, "Alfa", "forward", true, false, 23);
        Player player14 = new Player(12, 13, 14, 1, "Beta", "defense", false, false, 24);
        Player player15= new Player(10, 15, 9, 18, "Gama", "goalie", false, false, 25);
        Player player16 = new Player(10, 12, 10, 1, "Theta", "defense", false, false, 26);
        playerList4.add(player13);
        playerList4.add(player14);
        playerList4.add(player15);
        playerList4.add(player16);

        Team team4 = new Team("Viena", "Ram Saxsena", null, playerList3);

        playerList5 = new ArrayList<Player>();
        Player player17 = new Player(15, 14, 15, 1, "North", "forward", true, false, 23);
        Player player18 = new Player(12, 10, 14, 1, "East", "defense", false, false, 24);
        Player player19= new Player(10, 15, 9, 19, "West", "goalie", false, false, 25);
        Player player20 = new Player(10, 12, 10, 1, "South", "defense", false, false, 26);
        Player player21 = new Player(11, 12, 13, 1, "Ishan", "forward", false, false, 26);
        playerList5.add(player17);
        playerList5.add(player18);
        playerList5.add(player19);
        playerList5.add(player20);
        playerList5.add(player21);

        Team team5 = new Team("Mexico", "Ram Saxsena", null, playerList3);

        teamsList.add(team1);
        teamsList.add(team2);
        teamsList.add(team3);
        teamsList.add(team4);
        teamsList.add(team5);

        List<Division> divisionsList = new ArrayList<Division>();
        Division atlanticDivision = new Division("Atlantic", teamsList);
        divisionsList.add(atlanticDivision);
        List<Conference> conferenceList = new ArrayList<Conference>();
        List<Player> freeAgentsList = new ArrayList<Player>();
        Conference conference = new Conference("Eastern Conference", divisionsList);
        conferenceList.add(conference);

        Player firstFreeAgent = new Player(2, 4, 6, 1, "Player 1", "forward", true, false, 25);
        Player secondFreeAgent = new Player(7, 8, 9, 10, "Player 2", "goalie", true, false, 30);
        Player thirdFreeAgent = new Player(11, 12, 13, 0, "Player 1", "defense", true, false, 24);
        Player forthFreeAgent = new Player(7, 8, 9, 0, "Player 2", "forward", true, false, 26);
        Player fifthFreeAgent = new Player(8, 9, 10, 15, "Player 1", "goalie", true, false, 27);
        Player sixthFreeAgent = new Player(12, 13, 14, 0, "Player 2", "defense", true, false, 28);

        league = new League("Dalhousie Hockey League", conferenceList, freeAgentsList, gameplayConfig, null, null);
    }

    public League getLeagueInfo() {
        return league;
    }
}
