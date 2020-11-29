package group11.Hockey.BusinessLogic.Trading;
import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.IValidations;
import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeCharter;
import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeConfig;
import group11.Hockey.BusinessLogic.Trading.TradingTriplet.Triplet;
import group11.Hockey.BusinessLogic.models.*;
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
    private Team team1, team2, team3, team4, team5,team6, team7;
    private List<Player> freeAgentsList;
    private ITradeConfig tradingConfig;
    private ITradeCharter tradeCharter, inValidCharter;
    private IDisplay display;
    private IValidations validations;
    private ICommandLineInput commandLineInput;
    private List<Triplet<Team, List<Player>, Float>> tradingTeamsBuffer;


    public TradingModelMock(float randomTradeOfferChance, float randomAcceptanceChance) {
        super();
        init(randomTradeOfferChance, randomAcceptanceChance);
        addLeague();
    }

    private void init(float randomTradeOfferChance, float randomAcceptanceChance){
        teamsList = new ArrayList<>();
        this.randomTradeOfferChance = randomTradeOfferChance;
        this.randomAcceptanceChance = randomAcceptanceChance;
        IgmTable gmTbale = new gmTable(-0.1f, 0.1f, 0.0f);
        this.trading = new Trading(2, this.randomTradeOfferChance, 2, this.randomAcceptanceChance, gmTbale);
        this.tradingConfig = TradingFactory.makeTradeConfig(2, this.randomTradeOfferChance, 2, this.randomAcceptanceChance, gmTbale);
    }

    private void addLeague() {

        List<Player> playerList1 = new ArrayList<>();
        List<Player> playerList2 = new ArrayList<>();
        List<Player> playerList3 = new ArrayList<>();
        List<Player> playerList4 = new ArrayList<>();
        List<Player> playerList5 = new ArrayList<>();
        List<Player> playerList7 = new ArrayList<>();

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

        Player player01 = new Player(2, 2, 2, 1, "Ramesh", "forward", true, false, 30, true);
        Player player02 = new Player(2, 2, 2, 1, "Suresh", "goalie", false, false, 31, true);
        Player player03 = new Player(2, 2, 2, 2, "Mahesh", "defense", false, false, 32, true);
        Player player04 = new Player(2, 2, 2, 1, "Lokesh", "forward", false, false, 33, true);
        Player player5 = new Player(15, 14, 15, 1, "North", "forward", true, false, 23,true);
        Player player6 = new Player(12, 10, 14, 1, "East", "forward", false, false, 24, true);
        Player player7= new Player(10, 15, 9, 19, "West", "forward", false, false, 25, true);
        Player player8 = new Player(10, 12, 10, 1, "South", "forward", false, false, 26, true);
        Player player9 = new Player(2, 2, 2, 1, "Ishan", "forward", false, false, 26, true);
        Player player10 = new Player(10, 12, 10, 1, "Neirutya", "forward", false, false, 26, true);
        Player player11 = new Player(2, 2, 2, 1, "Agni", "forward", false, false, 26, true);
        Player player12= new Player(10, 15, 9, 11, "Vayavya", "forward", false, false, 25, true);
        Player player13 = new Player(15, 14, 15, 1, "ria", "forward", true, false, 23,true);
        Player player14 = new Player(12, 10, 14, 1, "dsf", "forward", false, false, 24, true);
        Player player15= new Player(10, 15, 9, 19, "Wesfsfst", "forward", false, false, 25, true);
        Player player16 = new Player(10, 12, 10, 1, "Sosfuth", "forward", false, false, 26, true);
        Player player17 = new Player(2, 2, 2, 1, "Ishsfan", "forward", false, false, 26, true);
        Player player18 = new Player(10, 12, 10, 1, "Neirsfutya", "defense", false, false, 26, true);
        Player player19 = new Player(2, 2, 2, 1, "Agsfni", "defense", false, false, 26, false);
        Player player20= new Player(10, 15, 9, 11, "Vayasfvya", "defense", false, false, 25, false);
        Player player21 = new Player(15, 14, 15, 1, "Nosfrth", "defense", true, false, 23,false);
        Player player22 = new Player(12, 10, 14, 1, "Esfsast", "defense", false, false, 24, false);
        Player player23= new Player(10, 15, 9, 19, "Wessft", "defense", false, false, 25, false);
        Player player24 = new Player(10, 12, 10, 1, "Sousfth", "defense", false, false, 26, false);
        Player player25 = new Player(2, 2, 2, 1, "Issfahan", "defense", false, false, 26, false);
        Player player26 = new Player(10, 12, 10, 1, "Neiffrutya", "defense", false, false, 26, false);
        Player player27 = new Player(2, 2, 2, 20, "Agfdfni", "forward", false, false, 26, false);
        Player player28= new Player(10, 15, 9, 15, "Vayavfdgya", "goalie", false, false, 25, false);
        Player player29 = new Player(2, 2, 2, 16, "Aggfrni", "goalie", false, false, 26, true);
        Player player30= new Player(10, 15, 9, 14, "Vaysfdsavya", "goalie", false, false, 25, true);

        playerList2.add(player01);
        playerList2.add(player02);
        playerList2.add(player03);
        playerList2.add(player04);
        playerList2.add(player5);
        playerList2.add(player6);
        playerList2.add(player7);
        playerList2.add(player8);
        playerList2.add(player9);
        playerList2.add(player10);
        playerList2.add(player11);
        playerList2.add(player12);
        playerList2.add(player13);
        playerList2.add(player14);
        playerList2.add(player15);
        playerList2.add(player16);
        playerList2.add(player17);
        playerList2.add(player18);
        playerList2.add(player19);
        playerList2.add(player20);
        playerList2.add(player21);
        playerList2.add(player22);
        playerList2.add(player23);
        playerList2.add(player24);
        playerList2.add(player25);
        playerList2.add(player26);
        playerList2.add(player27);
        playerList2.add(player28);
        playerList2.add(player29);
        playerList2.add(player30);

        GeneralManager gm2 = new GeneralManager("John Smith","shrewd");
        team2 = new Team("Miami", gm2, null, playerList2);
        team2.setLosses(4);

        Player player31 = new Player(12, 11, 11, 1, "Jigar", "forward", true, false, 23, true);
        Player player32 = new Player(7, 8, 9, 1, "Raj", "defense", false, false, 24, true);
        Player player33= new Player(10, 5, 5, 18, "Jatin", "goalie", false, false, 25,true);
        Player player34 = new Player(10, 12, 10, 1, "Alex", "defense", false, false, 26, false);
        playerList3.add(player31);
        playerList3.add(player32);
        playerList3.add(player33);
        playerList3.add(player34);

        GeneralManager gm3 = new GeneralManager("Marry Pascal","gambler");
        team3 = new Team("NewYork", gm3, null, playerList3);

        Player player35 = new Player(15, 15, 15, 1, "Alfa", "forward", true, false, 23, true);
        Player player36 = new Player(12, 13, 14, 1, "Beta", "defense", false, false, 24, true);
        Player player37= new Player(10, 15, 9, 18, "Gama", "goalie", false, false, 25, true);
        Player player38 = new Player(10, 12, 10, 1, "Theta", "defense", false, false, 26, true);

        playerList4.add(player35);
        playerList4.add(player36);
        playerList4.add(player37);
        playerList4.add(player38);

        GeneralManager gm4 = new GeneralManager("Ram Saxsena","gambler");
        team4 = new Team("Viena", gm4, null, playerList4);

        Player player39 = new Player(15, 14, 15, 1, "North", "forward", true, false, 23,true);
        Player player40 = new Player(12, 10, 14, 1, "East", "defense", false, false, 24, true);
        Player player41= new Player(10, 15, 9, 19, "West", "goalie", false, false, 25, true);
        Player player42 = new Player(10, 12, 10, 1, "South", "defense", false, false, 26, false);
        Player player43 = new Player(2, 2, 2, 1, "Ishan", "forward", false, false, 26, true);
        Player player44 = new Player(10, 12, 10, 1, "Neirutya", "forward", false, false, 26, true);
        Player player45 = new Player(2, 2, 2, 1, "Agni", "defense", false, false, 26, true);
        Player player46= new Player(10, 15, 9, 11, "Vayavya", "goalie", false, false, 25, true);

        playerList5.add(player39);
        playerList5.add(player40);
        playerList5.add(player41);
        playerList5.add(player42);
        playerList5.add(player43);
        playerList5.add(player44);
        playerList5.add(player45);
        playerList5.add(player46);

        GeneralManager gm5 = new GeneralManager("John Snow","gambler");
        team5 = new Team("Mexico", gm5, null, playerList5);
        team5.setLosses(3);

        GeneralManager gm6 = new GeneralManager("John Smith","normal");
        team6 = new Team("North Carolina", gm6, null, playerList4);

        Player newplayer01 = new Player(12, 2, 20, 1, "Ramekhksh", "forward", true, false, 30, true);
        Player newplayer02 = new Player(12, 12, 2, 1, "Surdtuyesh", "goalie", false, false, 31, true);
        Player newplayer03 = new Player(12, 12, 2, 2, "Mahyrysryesh", "defense", false, false, 32, true);
        Player newplayer04 = new Player(2, 2, 12, 1, "Lokryresh", "forward", false, false, 33, true);
        Player newplayer5 = new Player(15, 14, 15, 1, "Ndrdydorth", "forward", true, false, 23,true);
        Player newplayer6 = new Player(12, 10, 14, 1, "Eddast", "forward", false, false, 24, true);
        Player newplayer7= new Player(10, 15, 9, 19, "Weddgddst", "forward", false, false, 25, true);
        Player newplayer8 = new Player(10, 12, 10, 1, "Sdgdouth", "forward", false, false, 26, true);
        Player newplayer9 = new Player(2, 12, 12, 1, "Ishdtdstan", "forward", false, false, 26, true);
        Player newplayer10 = new Player(10, 12, 10, 1, "Neirdgddutya", "forward", false, false, 26, true);
        Player newplayer11 = new Player(2, 2, 2, 1, "Aggdni", "forward", false, false, 26, true);
        Player newplayer12= new Player(10, 15, 19, 11, "Vayavsdsya", "forward", false, false, 25, true);
        Player newplayer13 = new Player(15, 14, 15, 1, "rdfsdfia", "forward", true, false, 23,true);
        Player newplayer14 = new Player(12, 10, 14, 1, "dssdfdsf", "forward", false, false, 24, true);
        Player newplayer15= new Player(10, 15, 9, 19, "Wessfsffsfst", "forward", false, false, 25, true);
        Player newplayer16 = new Player(10, 12, 10, 1, "Sosfssfuth", "forward", false, false, 26, true);
        Player newplayer17 = new Player(2, 12, 2, 1, "Ishssfsfan", "forward", false, false, 26, true);
        Player newplayer18 = new Player(10, 12, 10, 1, "Nefsdirsfutya", "defense", false, false, 26, true);
        Player newplayer19 = new Player(2, 2, 2, 1, "Agsdgfni", "defense", false, false, 26, false);
        Player newplayer20= new Player(10, 15, 9, 11, "Vaydgdasfvya", "defense", false, false, 25, false);
        Player newplayer21 = new Player(15, 14, 15, 1, "Nosdgsfrth", "defense", true, false, 23,false);
        Player newplayer22 = new Player(12, 10, 14, 1, "Esfdgdsast", "defense", false, false, 24, false);
        Player newplayer23= new Player(10, 15, 19, 19, "Wesggdsft", "defense", false, false, 25, false);
        Player newplayer24 = new Player(10, 12, 10, 1, "Sogdusfth", "defense", false, false, 26, false);
        Player newplayer25 = new Player(2, 12, 12, 1, "Issfdgadahan", "defense", false, false, 26, false);
        Player newplayer26 = new Player(10, 12, 10, 1, "Neifsfdsgfrutya", "defense", false, false, 26, false);
        Player newplayer27 = new Player(2, 12, 2, 20, "Agsgsfdfni", "forward", false, false, 26, false);
        Player newplayer28= new Player(10, 15, 9, 15, "Vsgayavfdgya", "goalie", false, false, 25, false);
        Player newplayer29 = new Player(2, 2, 12, 16, "Aggfrsgni", "goalie", false, false, 26, true);
        Player newplayer30= new Player(10, 15, 9, 14, "Vayssgfdsavya", "goalie", false, false, 25, true);

        playerList7.add(newplayer01);
        playerList7.add(newplayer02);
        playerList7.add(newplayer03);
        playerList7.add(newplayer04);
        playerList7.add(newplayer5);
        playerList7.add(newplayer6);
        playerList7.add(newplayer7);
        playerList7.add(newplayer8);
        playerList7.add(newplayer9);
        playerList7.add(newplayer10);
        playerList7.add(newplayer11);
        playerList7.add(newplayer12);
        playerList7.add(newplayer13);
        playerList7.add(newplayer14);
        playerList7.add(newplayer15);
        playerList7.add(newplayer16);
        playerList7.add(newplayer17);
        playerList7.add(newplayer18);
        playerList7.add(newplayer19);
        playerList7.add(newplayer20);
        playerList7.add(newplayer21);
        playerList7.add(newplayer22);
        playerList7.add(newplayer23);
        playerList7.add(newplayer24);
        playerList7.add(newplayer25);
        playerList7.add(newplayer26);
        playerList7.add(newplayer27);
        playerList7.add(newplayer28);
        playerList7.add(newplayer29);
        playerList7.add(newplayer30);

        GeneralManager gm7 = new GeneralManager("John Smith","normal");
        team7 = new Team("Florida", gm7, null, playerList7);
        team7.setLosses(4);

        teamsList.add(team1);
        teamsList.add(team2);
        teamsList.add(team3);
        teamsList.add(team4);
        teamsList.add(team5);
        teamsList.add(team6);
        teamsList.add(team7);

        List<Division> divisionsList = new ArrayList<>();
        Division atlanticDivision = new Division("Atlantic", teamsList);
        divisionsList.add(atlanticDivision);
        List<Conference> conferenceList = new ArrayList<>();
        freeAgentsList = new ArrayList<>();
        Conference conference = new Conference("Eastern Conference", divisionsList);
        conferenceList.add(conference);

        Player firstFreeAgent = new Player(2, 4, 6, 1, "firstFreeAgent", "forward", true, false, 25, true);
        Player secondFreeAgent = new Player(7, 8, 9, 10, "secondFreeAgent", "goalie", true, false, 30, true);
        Player thirdFreeAgent = new Player(11, 12, 13, 0, "thirdFreeAgent", "defense", true, false, 24, true);
        Player forthFreeAgent = new Player(7, 8, 9, 0, "forthFreeAgent", "forward", true, false, 26, true);
        Player fifthFreeAgent = new Player(8, 9, 10, 15, "fifthFreeAgent", "goalie", true, false, 27, true);
        Player sixthFreeAgent = new Player(12, 13, 14, 0, "sixthFreeAgent", "defense", true, false, 28, true);
        Player FreeAgent7 = new Player(2, 4, 6, 1, "firstFredfeAgent", "forward", true, false, 25, true);
        Player secondFreeAgent8 = new Player(7, 8, 9, 10, "segfdgcondFreeAgent", "goalie", true, false, 30, true);
        Player thirdFreeAgent9 = new Player(11, 12, 13, 0, "thirdgfdgdFreeAgent", "defense", true, false, 24, true);
        Player forthFreeAgent10 = new Player(7, 8, 9, 0, "forthFreddgdfeAgent", "forward", true, false, 26, true);
        Player fifthFreeAgent11 = new Player(8, 9, 10, 15, "fiftsdfhFreeAgent", "goalie", true, false, 27, true);
        Player sixthFreeAgent12 = new Player(12, 13, 14, 0, "sixthfdsfFreeAgent", "defense", true, false, 28, true);
        Player firstFreeAgent13 = new Player(2, 4, 6, 1, "firffgrstFreeAgent", "forward", true, false, 25, true);
        Player secondFreeAgent14 = new Player(7, 8, 9, 10, "secondFreegtgAgent", "goalie", true, false, 30, true);
        Player thirdFreeAgent15 = new Player(11, 12, 13, 0, "thifrrdFreeAgent", "defense", true, false, 24, true);
        Player forthFreeAgent16 = new Player(7, 8, 9, 0, "forthFewfreeAgent", "forward", true, false, 26, true);
        Player fifthFreeAgent17 = new Player(8, 9, 10, 15, "fiftfEWFEWhFreeAgent", "goalie", true, false, 27, true);
        Player sixthFreeAgent18 = new Player(12, 13, 14, 0, "siEWFdfdxthFreeAgent", "defense", true, false, 28, true);
        Player firstFreeAgent19 = new Player(2, 4, 6, 1, "firstFresfsfeAgent", "forward", true, false, 25, true);
        Player secondFreeAgent20 = new Player(7, 8, 9, 10, "secondFresfeAgent", "goalie", true, false, 30, true);
        Player thirdFreeAgent21 = new Player(11, 12, 13, 0, "thirdFsfsreeAgent", "defense", true, false, 24, false);
        Player forthFreeAgent22 = new Player(7, 8, 9, 0, "forthFreseAgent", "forward", true, false, 26, false);
        Player fifthFreeAgent23 = new Player(8, 9, 10, 15, "fifthFGTRreeAgent", "goalie", true, false, 27, false);
        Player sixthFreeAgent24 = new Player(12, 13, 14, 0, "sixthFrefgfeAgent", "defense", true, false, 28, false);
        Player firstFreeAgent25 = new Player(2, 4, 6, 1, "firssfstFreeAgent", "forward", true, false, 25, false);
        Player secondFreeAgent26 = new Player(7, 8, 9, 10, "secondFhgfhreeAgent", "goalie", true, false, 30, false);
        Player thirdFreeAgent27 = new Player(11, 12, 13, 0, "thirdFsdffreeAgent", "defense", true, false, 24, false);
        Player forthFreeAgent28 = new Player(7, 8, 9, 0, "forthFreaaeAgent", "forward", true, false, 26, false);
        Player fifthFreeAgent29 = new Player(8, 9, 10, 15, "fifthgfhFreeAgent", "goalie", true, false, 27, false);
        Player sixthFreeAgent30 = new Player(12, 13, 14, 0, "sixthFreejAgent", "defense", true, false, 28, false);
        Player firstFreeAgent31 = new Player(2, 4, 6, 1, "firstFreeghAgent", "forward", true, false, 25, false);
        Player secondFreeAgent32 = new Player(7, 8, 9, 10, "secondgddFreeAgent", "goalie", true, false, 30, false);
        Player thirdFreeAgent33 = new Player(11, 12, 13, 0, "thidgdrdFreeAgent", "defense", true, false, 24, false);
        Player forthFreeAgent34 = new Player(7, 8, 9, 0, "forthFrdgdaeeAgent", "forward", true, false, 26, false);
        Player fifthFreeAgent35 = new Player(8, 9, 10, 15, "fiagdfgfthFreeAgent", "goalie", true, false, 27, false);
        Player sixthFreeAgent36 = new Player(12, 13, 14, 0, "sixthFjjesreeAgent", "defense", true, false, 28, false);

        freeAgentsList.add(firstFreeAgent);
        freeAgentsList.add(secondFreeAgent);
        freeAgentsList.add(thirdFreeAgent);
        freeAgentsList.add(forthFreeAgent);
        freeAgentsList.add(fifthFreeAgent);
        freeAgentsList.add(sixthFreeAgent);
        freeAgentsList.add(FreeAgent7);
        freeAgentsList.add(secondFreeAgent8);
        freeAgentsList.add(thirdFreeAgent9);
        freeAgentsList.add(forthFreeAgent10);
        freeAgentsList.add(fifthFreeAgent11);
        freeAgentsList.add(sixthFreeAgent12);
        freeAgentsList.add(FreeAgent7);
        freeAgentsList.add(secondFreeAgent8);
        freeAgentsList.add(thirdFreeAgent9);
        freeAgentsList.add(forthFreeAgent10);
        freeAgentsList.add(fifthFreeAgent11);
        freeAgentsList.add(sixthFreeAgent12 );
        freeAgentsList.add(firstFreeAgent13 );
        freeAgentsList.add(secondFreeAgent14);
        freeAgentsList.add(thirdFreeAgent15 );
        freeAgentsList.add(forthFreeAgent16 );
        freeAgentsList.add(fifthFreeAgent17 );
        freeAgentsList.add(sixthFreeAgent18 );
        freeAgentsList.add(firstFreeAgent19 );
        freeAgentsList.add(secondFreeAgent20);
        freeAgentsList.add(thirdFreeAgent21 );
        freeAgentsList.add(forthFreeAgent22 );
        freeAgentsList.add(fifthFreeAgent23 );
        freeAgentsList.add(sixthFreeAgent24 );
        freeAgentsList.add(firstFreeAgent25 );
        freeAgentsList.add(secondFreeAgent26);
        freeAgentsList.add(thirdFreeAgent27 );
        freeAgentsList.add(forthFreeAgent28 );
        freeAgentsList.add(fifthFreeAgent29 );
        freeAgentsList.add(sixthFreeAgent30 );
        freeAgentsList.add(firstFreeAgent31 );
        freeAgentsList.add(secondFreeAgent32);
        freeAgentsList.add(thirdFreeAgent33 );
        freeAgentsList.add(forthFreeAgent34 );
        freeAgentsList.add(fifthFreeAgent35 );
        freeAgentsList.add(sixthFreeAgent36 );

        league = new League("Dalhousie Hockey League", conferenceList, freeAgentsList, gameplayConfig, null, null);

        List<Player> offeredPlayerList = new ArrayList<>();
        offeredPlayerList.add(player2);
        offeredPlayerList.add(player4);
        List<Player> requestedPlayerList = new ArrayList<>();
        requestedPlayerList.add(player14);
        requestedPlayerList.add(player16);
        tradeCharter = TradingFactory.makeTradeCharter(team1, offeredPlayerList, team4, requestedPlayerList, -1);
        int draftRound = tradeCharter.getDraftRoundIdx();
        inValidCharter = TradingFactory.makeTradeCharter(null, null, team4, requestedPlayerList, draftRound);


        commandLineInput = DefaultHockeyFactory.makeCommandLineInput();
        display = DefaultHockeyFactory.makeDisplay();
        validations = DefaultHockeyFactory.makeValidations(display);

        tradingTeamsBuffer= new ArrayList<>();

        Triplet<Team, List<Player>, Float> teamRequestEntry1 =
                Triplet.of(team1, team1.getPlayers(),team1.getTeamStrength());
        Triplet<Team, List<Player>, Float> teamRequestEntry2 =
                Triplet.of(team3, team3.getPlayers(),team3.getTeamStrength());
        Triplet<Team, List<Player>, Float> teamRequestEntry3 =
                Triplet.of(team4, team4.getPlayers(),team4.getTeamStrength());

        tradingTeamsBuffer.add(teamRequestEntry1);
        tradingTeamsBuffer.add(teamRequestEntry2);
        tradingTeamsBuffer.add(teamRequestEntry3);

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

    public Team getTeam2() {
        return team2;
    }

    public Team getTeam3() {
        return team3;
    }

    public Team getTeam4() {
        return team4;
    }

    public List<Player> getFreeAgentsList() {
        return freeAgentsList;
    }

    public ITradeConfig getTradingConfig() {
        return this.tradingConfig;
    }

    public ITradeCharter getTradeCharter() {
        return tradeCharter;
    }

    public ITradeCharter getInValidCharter() {
        return inValidCharter;
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

    public List<Triplet<Team, List<Player>, Float>> getTradingTeamsBuffer() {
        return tradingTeamsBuffer;
    }

    public void addPlayertoTeam2(){
        Player x = new Player(15, 15, 15, 1, "Alfa", "forward", true, false, 23, true);
        Player y = new Player(12, 13, 14, 1, "Beta", "defense", false, false, 24, true);
        Player z = new Player(10, 15, 9, 18, "Gama", "goalie", false, false, 25, true);
        team2.getPlayers().add(x);
        team2.getPlayers().add(y);
        team2.getPlayers().add(z);
        team2.getRoster().updateSubRoster(team2.getPlayers());
    }

    public void dropPlayerFromTeam2(){
        for(int i=0; i<5; i++){
            team2.getPlayers().remove(i);
        }
        team2.getRoster().updateSubRoster(team2.getPlayers());
    }
}
