package group11.Hockey.BusinessLogic.Trading;
import group11.Hockey.BusinessLogic.ConstantSupplier;
import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.IConstantSupplier;
import group11.Hockey.BusinessLogic.IValidations;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeCharter;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeConfig;
import group11.Hockey.BusinessLogic.models.*;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;
import group11.Hockey.BusinessLogic.models.Roster.Roster;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;

import java.util.ArrayList;
import java.util.List;

public class TradingModelMock {
    private League league;
    private List<Team> teamsList;
    private Trading trading;
    private float randomTradeOfferChance;
    private float randomAcceptanceChance;
    private Team team1, team2, team3, team4, team5,team6;
    private List<Player> freeAgentsList;
    private ITradeConfig tradingConfig;
    private IConstantSupplier constantSupplier;
    private IConstantSupplier csTeam6;
    private ITradeCharter tradeCharter;
    private IDisplay display;
    private IValidations validations;
    private ICommandLineInput commandLineInput;


    public TradingModelMock(float randomTradeOfferChance, float randomAcceptanceChance) {
        super();
        init(randomTradeOfferChance, randomAcceptanceChance);
        addLeague();
    }

    private void init(float randomTradeOfferChance, float randomAcceptanceChance){
        teamsList = new ArrayList<Team>();
        this.randomTradeOfferChance = randomTradeOfferChance;
        this.randomAcceptanceChance = randomAcceptanceChance;
        IgmTable gmTbale = new gmTable(-0.1f, 0.1f, 0.0f);
        this.trading = new Trading(2, this.randomTradeOfferChance,
                2, this.randomAcceptanceChance, gmTbale);
        this.tradingConfig = new TradeConfig(2, this.randomTradeOfferChance,
                2, this.randomAcceptanceChance, gmTbale);
        this.constantSupplier = new ConstantSupplier(3,0,1,1,1);
        this.csTeam6 = new ConstantSupplier(7,0,2,3,2);
    }

    private void addLeague() {

        List<Player> playerList1 = new ArrayList<>();
        List<Player> playerList2 = new ArrayList<>();
        List<Player> playerList3 = new ArrayList<>();
        List<Player> playerList4 = new ArrayList<>();
        List<Player> playerList5 = new ArrayList<>();

        Aging aging = new Aging(30, 55);
        GameResolver gameResolver = new GameResolver(0);
        Injuries injuries = new Injuries(1, 1, 100);
        Training training = new Training(0);

        GameplayConfig gameplayConfig = new GameplayConfig(aging, gameResolver, injuries, training, this.trading);

        Player player1 = new Player(15, 18, 12, 1, "Tom", "forward", true, false, 25, true);
        Player player2 = new Player(10, 10, 10, 1, "Dick", "defense", false, false, 28,true);
        Player player3 = new Player(10, 4, 9, 18, "Harry", "goalie", false, false, 30, true);
        Player player4 = new Player(10, 10, 10, 1, "Jerry", "defense", false, false, 21, false);

        playerList1.add(player1);
        playerList1.add(player2);
        playerList1.add(player3);
        playerList1.add(player4);

        GeneralManager gm1 = new GeneralManager("Mister Fred","normal");
        team1 = new Team("Boston", gm1, null, playerList1);
        team1.setLosses(3);
        IRoster roster1 = new Roster(team1.getTeamName(), playerList1, constantSupplier);
        team1.setRoster(roster1);

        Player player5 = new Player(10, 20, 13, 1, "Ramesh", "forward", true, false, 30, true);
        Player player6 = new Player(12, 10, 14, 1, "Suresh", "defense", false, false, 31, true);
        Player player7 = new Player(10, 8, 9, 12, "Mahesh", "goalie", false, false, 32, true);
        Player player8 = new Player(10, 15, 10, 1, "Lokesh", "defense", false, false, 33, false);
        playerList2.add(player5);
        playerList2.add(player6);
        playerList2.add(player7);
        playerList2.add(player8);

        GeneralManager gm2 = new GeneralManager("John Smith","shrewd");
        team2 = new Team("Miami", gm2, null, playerList2);
        team2.setLosses(4);
        IRoster roster2 = new Roster(team2.getTeamName(), playerList2, constantSupplier);
        team2.setRoster(roster2);

        Player player9 = new Player(12, 11, 11, 1, "Jigar", "forward", true, false, 23, true);
        Player player10 = new Player(7, 8, 9, 1, "Raj", "defense", false, false, 24, true);
        Player player11= new Player(10, 5, 5, 18, "Jatin", "goalie", false, false, 25,true);
        Player player12 = new Player(10, 12, 10, 1, "Alex", "defense", false, false, 26, false);
        playerList3.add(player9);
        playerList3.add(player10);
        playerList3.add(player11);
        playerList3.add(player12);

        GeneralManager gm3 = new GeneralManager("Marry Pascal","gambler");
        team3 = new Team("NewYork", gm3, null, playerList3);
//        team3.setUserTeam(true);
        team3.setLosses(4);
        IRoster roster3 = new Roster(team3.getTeamName(), playerList3, constantSupplier);
        team3.setRoster(roster3);

        Player player13 = new Player(15, 15, 15, 1, "Alfa", "forward", true, false, 23, true);
        Player player14 = new Player(12, 13, 14, 1, "Beta", "defense", false, false, 24, true);
        Player player15= new Player(10, 15, 9, 18, "Gama", "goalie", false, false, 25, true);
        Player player16 = new Player(10, 12, 10, 1, "Theta", "defense", false, false, 26, true);

        playerList4.add(player13);
        playerList4.add(player14);
        playerList4.add(player15);
        playerList4.add(player16);

        GeneralManager gm4 = new GeneralManager("Ram Saxsena","gambler");
        team4 = new Team("Viena", gm4, null, playerList4);
        team4.setLosses(3);
        IRoster roster4 = new Roster(team4.getTeamName(), playerList4, constantSupplier);
        team4.setRoster(roster4);

        Player player17 = new Player(15, 14, 15, 1, "North", "forward", true, false, 23,true);
        Player player18 = new Player(12, 10, 14, 1, "East", "defense", false, false, 24, true);
        Player player19= new Player(10, 15, 9, 19, "West", "goalie", false, false, 25, true);
        Player player20 = new Player(10, 12, 10, 1, "South", "defense", false, false, 26, false);
        Player player21 = new Player(2, 2, 2, 1, "Ishan", "forward", false, false, 26, true);
        Player player22 = new Player(10, 12, 10, 1, "Neirutya", "forward", false, false, 26, true);
        Player player23 = new Player(2, 2, 2, 1, "Agni", "defense", false, false, 26, true);
        Player player24= new Player(10, 15, 9, 11, "Vayavya", "goalie", false, false, 25, true);


        playerList5.add(player17);
        playerList5.add(player18);
        playerList5.add(player19);
        playerList5.add(player20);
        playerList5.add(player21);
        playerList5.add(player22);
        playerList5.add(player23);
        playerList5.add(player24);

        GeneralManager gm5 = new GeneralManager("John Snow","gambler");
        team5 = new Team("Mexico", gm5, null, playerList5);
        team5.setLosses(1);
        IRoster roster5 = new Roster(team5.getTeamName(), playerList5, constantSupplier);
        team5.setRoster(roster5);

        team6 = new Team("Mexico", null, null, playerList4);
        IRoster roster6 = new Roster(team6.getTeamName(), playerList4, csTeam6);
        team6.setRoster(roster6);

        teamsList.add(team1);
        teamsList.add(team2);
        teamsList.add(team3);
        teamsList.add(team4);
        teamsList.add(team5);
        teamsList.add(team6);

        List<Division> divisionsList = new ArrayList<>();
        Division atlanticDivision = new Division("Atlantic", teamsList);
        divisionsList.add(atlanticDivision);
        List<Conference> conferenceList = new ArrayList<>();
        freeAgentsList = new ArrayList<>();
        Conference conference = new Conference("Eastern Conference", divisionsList);
        conferenceList.add(conference);

        Player firstFreeAgent = new Player(2, 4, 6, 1, "firstFreeAgent", "forward", true, false, 25);
        Player secondFreeAgent = new Player(7, 8, 9, 10, "secondFreeAgent", "goalie", true, false, 30);
        Player thirdFreeAgent = new Player(11, 12, 13, 0, "thirdFreeAgent", "defense", true, false, 24);
        Player forthFreeAgent = new Player(7, 8, 9, 0, "forthFreeAgent", "forward", true, false, 26);
        Player fifthFreeAgent = new Player(8, 9, 10, 15, "fifthFreeAgent", "goalie", true, false, 27);
        Player sixthFreeAgent = new Player(12, 13, 14, 0, "sixthFreeAgent", "defense", true, false, 28);

        freeAgentsList.add(firstFreeAgent);
        freeAgentsList.add(secondFreeAgent);
        freeAgentsList.add(thirdFreeAgent);
        freeAgentsList.add(forthFreeAgent);
        freeAgentsList.add(fifthFreeAgent);
        freeAgentsList.add(sixthFreeAgent);

        league = new League("Dalhousie Hockey League", conferenceList, freeAgentsList, gameplayConfig, null, null);

        List<Player> offeredPlayerList = new ArrayList<>();
        offeredPlayerList.add(player2);
        offeredPlayerList.add(player4);
        List<Player> requestedPlayerList = new ArrayList<>();
        requestedPlayerList.add(player14);
        requestedPlayerList.add(player16);
        tradeCharter = TradingFactory.makeTradeCharter(team1, offeredPlayerList, team4, requestedPlayerList, -1);

        commandLineInput = DefaultHockeyFactory.makeCommandLineInput();
        display = DefaultHockeyFactory.makeDisplay();
        validations = DefaultHockeyFactory.makeValidations(display);
    }

    public List<Team> getTeamList() {
        return teamsList;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam5() {
        return team5;
    }

    public Team getTeam6() {
        return team6;
    }

    public List<Player> getFreeAgentsList() {
        return freeAgentsList;
    }

    public ITradeConfig getTradingConfig() {
        return tradingConfig;
    }

    public ITradeCharter getTradeCharter() {
        return tradeCharter;
    }

    public League getLeagueInfo() {
        return league;
    }

    public League getLeague() {
        return league;
    }

    public IDisplay getDisplay() {
        return display;
    }

    public IValidations getValidations() {
        return validations;
    }

    public ICommandLineInput getCommandLineInput() {
        return commandLineInput;
    }

    public IConstantSupplier getConstantSupplier() {
        return constantSupplier;
    }

    public IConstantSupplier getCsTeam6() {
        return csTeam6;
    }
}
