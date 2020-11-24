package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.ConstantSupplier;
import group11.Hockey.BusinessLogic.IConstantSupplier;
import group11.Hockey.BusinessLogic.models.*;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;
import group11.Hockey.BusinessLogic.models.Roster.Roster;

import java.util.ArrayList;
import java.util.List;

public class TradingMock {
    private ILeague league;
    private List<ITeam> teamsList;
    private Trading tradingConfig;
    private float randomTradeOfferChance;
    private float randomAcceptanceChance;

    public TradingMock(float randomTradeOfferChance, float randomAcceptanceChance) {
        super();
        this.teamsList = new ArrayList<ITeam>();
        this.randomTradeOfferChance = randomTradeOfferChance;
        this.randomAcceptanceChance = randomAcceptanceChance;
        this.tradingConfig = new Trading(2, this.randomTradeOfferChance, 2, this.randomAcceptanceChance);
        addLeague();
    }

    private void addLeague() {

        List<IPlayer> playerList1 = new ArrayList<>();
        List<IPlayer> playerList2 = new ArrayList<>();
        List<IPlayer> playerList3 = new ArrayList<>();
        List<IPlayer> playerList4 = new ArrayList<>();
        List<IPlayer> playerList5 = new ArrayList<>();

        Aging aging = new Aging(30, 55);
        GameResolver gameResolver = new GameResolver(0);
        Injuries injuries = new Injuries(1, 1, 100);
        Training training = new Training(0);

        GameplayConfig gameplayConfig = new GameplayConfig(aging, gameResolver, injuries, training, this.tradingConfig);

        IPlayer activePlayer01;
        IPlayer activePlayer02;
        IPlayer inActivePlayer01;
        IPlayer inActivePlayer02;
        List<IPlayer> allPlayerList0 = new ArrayList<>();
//        IRoster team0Roster;

        activePlayer01 = new Player(20, 20, 20, 20, "Tom", "forward", true, false, 25,true);
        activePlayer02 = new Player(2, 2, 2, 2, "Dick", "defense", false, false, 28, true);
        inActivePlayer01 = new Player(10, 10, 10, 10, "Vikash", "goalie", false, false, 30, false);
        inActivePlayer02 = new Player(1, 1, 1, 1, "George", "defense", false, false, 21, false);

        allPlayerList0.add(activePlayer01);
        allPlayerList0.add(activePlayer02);
        allPlayerList0.add(inActivePlayer01);
        allPlayerList0.add(inActivePlayer02);

        IConstantSupplier constants = new ConstantSupplier(2,2,1,2,1);
        ITeam team0 = new Team("Boston", "Mister Fred", null, allPlayerList0);
        team0.setLosses(3);

        IPlayer player1 = new Player(15, 18, 12, 1, "Tom", "forward", true, false, 25,true);
        IPlayer player2 = new Player(10, 10, 10, 1, "Dick", "defense", false, false, 28,true);
        IPlayer player3 = new Player(10, 4, 9, 18, "Harry", "goalie", false, false, 30, false);
        IPlayer player4 = new Player(10, 10, 10, 1, "Jerry", "defense", false, false, 21, false);

        playerList1.add(player1);
        playerList1.add(player2);
        playerList1.add(player3);
        playerList1.add(player4);

        ITeam team1 = new Team("Boston", "Mister Fred", null, playerList1);
        team1.setLosses(3);

        IPlayer player5 = new Player(10, 20, 13, 1, "Ramesh", "forward", true, false, 30, true);
        IPlayer player6 = new Player(12, 10, 14, 1, "Suresh", "defense", false, false, 31, true);
        IPlayer player7 = new Player(10, 8, 9, 12, "Mahesh", "goalie", false, false, 32, false);
        IPlayer player8 = new Player(10, 15, 10, 1, "Lokesh", "defense", false, false, 33, false);
        playerList2.add(player5);
        playerList2.add(player6);
        playerList2.add(player7);
        playerList2.add(player8);

        ITeam team2 = new Team("Miami", "John Smith", null, playerList2);
        team2.setLosses(4);

        IPlayer player9 = new Player(12, 11, 11, 1, "Jigar", "forward", true, false, 23, true);
        IPlayer player10 = new Player(7, 8, 9, 1, "Raj", "defense", false, false, 24, true);
        IPlayer player11= new Player(10, 5, 5, 18, "Jatin", "goalie", false, false, 25, false);
        IPlayer player12 = new Player(10, 12, 10, 1, "Alex", "defense", false, false, 26, false);
        playerList3.add(player9);
        playerList3.add(player10);
        playerList3.add(player11);
        playerList3.add(player12);

        ITeam team3 = new Team("NewYork", "Marry Pascal", null, playerList3);

        IPlayer player13 = new Player(15, 15, 15, 1, "Alfa", "forward", true, false, 23, true);
        IPlayer player14 = new Player(12, 13, 14, 1, "Beta", "defense", false, false, 24, true);
        IPlayer player15= new Player(10, 15, 9, 18, "Gama", "goalie", false, false, 25, false);
        IPlayer player16 = new Player(10, 12, 10, 1, "Theta", "defense", false, false, 26, true);
        playerList4.add(player13);
        playerList4.add(player14);
        playerList4.add(player15);
        playerList4.add(player16);

        ITeam team4 = new Team("Viena", "Ram Saxsena", null, playerList3);

        IPlayer player17 = new Player(15, 14, 15, 1, "North", "forward", true, false, 23, true);
        IPlayer player18 = new Player(12, 10, 14, 1, "East", "defense", false, false, 24, true);
        IPlayer player19= new Player(10, 15, 9, 19, "West", "goalie", false, false, 25, true);
        IPlayer player20 = new Player(10, 12, 10, 1, "South", "defense", false, false, 26, true);
        IPlayer player21 = new Player(11, 12, 13, 1, "Ishan", "forward", false, false, 26, true);
        playerList5.add(player17);
        playerList5.add(player18);
        playerList5.add(player19);
        playerList5.add(player20);
        playerList5.add(player21);

        ITeam team5 = new Team("Mexico", "Ram Saxsena", null, playerList3);

        teamsList.add(team0);
        teamsList.add(team1);
        teamsList.add(team2);
        teamsList.add(team3);
        teamsList.add(team4);
        teamsList.add(team5);

        List<Division> divisionsList = new ArrayList<>();
        Division atlanticDivision = new Division("Atlantic", teamsList);
        divisionsList.add(atlanticDivision);
        List<Conference> conferenceList = new ArrayList<>();
        List<IPlayer> freeAgentsList = new ArrayList<>();
        Conference conference = new Conference("Eastern Conference", divisionsList);
        conferenceList.add(conference);

        IPlayer firstFreeAgent = new Player(2, 4, 6, 1, "firstFreeAgent", "forward", true, false, 25);
        IPlayer secondFreeAgent = new Player(7, 8, 9, 10, "secondFreeAgent", "goalie", true, false, 30);
        IPlayer thirdFreeAgent = new Player(11, 12, 13, 0, "thirdFreeAgent", "defense", true, false, 24);
        IPlayer forthFreeAgent = new Player(7, 8, 9, 0, "forthFreeAgent", "forward", true, false, 26);
        IPlayer fifthFreeAgent = new Player(8, 9, 10, 15, "fifthFreeAgent", "goalie", true, false, 27);
        IPlayer sixthFreeAgent = new Player(12, 13, 14, 0, "sixthFreeAgent", "defense", true, false, 28);

        freeAgentsList.add(firstFreeAgent);
        freeAgentsList.add(secondFreeAgent);
        freeAgentsList.add(thirdFreeAgent);
        freeAgentsList.add(forthFreeAgent);
        freeAgentsList.add(fifthFreeAgent);
        freeAgentsList.add(sixthFreeAgent);

//        league = new League("Dalhousie Hockey League", conferenceList, freeAgentsList, gameplayConfig, null, null);
    }

    public List<ITeam> getITeamList() {
        return teamsList;
    }

    public ILeague getLeagueInfo() {
        return league;
    }

    public void setITradingConfig(float randomTradeOfferChance, float randomAcceptanceChance){
        this.randomTradeOfferChance = randomTradeOfferChance;
        this.randomAcceptanceChance = randomAcceptanceChance;
    }
}
