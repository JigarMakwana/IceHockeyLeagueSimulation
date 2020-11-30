/**
 * Author: Jigar Makwana B00842568
 */
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
    private float randomTradeOfferChance;
    private float randomAcceptanceChance;
    private ILeague league;
    private Trading trading;
    private IDisplay display;
    private List<ITeam> teamsList;
    private IValidations validations;
    private ITradeConfig tradingConfig;
    private List<IPlayer> freeAgentsList;
    private ICommandLineInput commandLineInput;
    private ITradeCharter tradeCharter, inValidCharter;
    private ITeam team1, team2, team3, team4, team5,team6, team7;
    private List<Triplet<ITeam, List<IPlayer>, Float>> tradingTeamsBuffer;


    public TradingModelMock(float randomTradeOfferChance, float randomAcceptanceChance) {
        super();
        init(randomTradeOfferChance, randomAcceptanceChance);
        addLeague();
    }

    private void init(float randomTradeOfferChance, float randomAcceptanceChance){
        this.teamsList = new ArrayList<>();
        this.freeAgentsList = new ArrayList<>();
        this.randomTradeOfferChance = randomTradeOfferChance;
        this.randomAcceptanceChance = randomAcceptanceChance;
        IgmTable gmTbale = DefaultHockeyFactory.makeGMTable(-0.1f, 0.1f, 0.0f);
        this.trading = DefaultHockeyFactory.makeTradingConfig(2, this.randomTradeOfferChance, 2, this.randomAcceptanceChance, gmTbale);
        this.tradingConfig = TradingFactory.makeTradeConfig(2, this.randomTradeOfferChance, 2, this.randomAcceptanceChance, gmTbale);
        this.commandLineInput = DefaultHockeyFactory.makeCommandLineInput();
        this.display = DefaultHockeyFactory.makeDisplay();
        this.validations = DefaultHockeyFactory.makeValidations(display);
    }

    private void addLeague() {

        List<IPlayer> playerList1 = new ArrayList<>();
        List<IPlayer> playerList2 = new ArrayList<>();
        List<IPlayer> playerList3 = new ArrayList<>();
        List<IPlayer> playerList4 = new ArrayList<>();
        List<IPlayer> playerList5 = new ArrayList<>();
        List<IPlayer> playerList7 = new ArrayList<>();

        IPlayer player1 = DefaultHockeyFactory.makePlayer(15, 18, 12, 1, "Tom", "forward", true, false, 25, true);
        IPlayer player2 = DefaultHockeyFactory.makePlayer(10, 10, 10, 1, "Dick", "defense", false, false, 28,true);
        IPlayer player3 = DefaultHockeyFactory.makePlayer(10, 4, 9, 18, "Harry", "goalie", false, false, 30, true);
        IPlayer player4 = DefaultHockeyFactory.makePlayer(10, 10, 10, 1, "Jerry", "defense", false, false, 21, false);

        playerList1.add(player1);
        playerList1.add(player2);
        playerList1.add(player3);
        playerList1.add(player4);

        IGeneralManager gm1 = DefaultHockeyFactory.makeGeneralManager("Mister Fred","normal");
        team1 = DefaultHockeyFactory.makeTeam("Boston", gm1, null, playerList1);

        IPlayer player01 = DefaultHockeyFactory.makePlayer(2, 2, 2, 1, "Ramesh", "forward", true, false, 30, true);
        IPlayer player02 = DefaultHockeyFactory.makePlayer(2, 2, 2, 1, "Suresh", "goalie", false, false, 31, true);
        IPlayer player03 = DefaultHockeyFactory.makePlayer(2, 2, 2, 2, "Mahesh", "defense", false, false, 32, true);
        IPlayer player04 = DefaultHockeyFactory.makePlayer(2, 2, 2, 1, "Lokesh", "forward", false, false, 33, true);
        IPlayer player5 = DefaultHockeyFactory.makePlayer(15, 14, 15, 1, "North", "forward", true, false, 23,true);
        IPlayer player6 = DefaultHockeyFactory.makePlayer(12, 10, 14, 1, "East", "forward", false, false, 24, true);
        IPlayer player7= DefaultHockeyFactory.makePlayer(10, 15, 9, 19, "West", "forward", false, false, 25, true);
        IPlayer player8 = DefaultHockeyFactory.makePlayer(10, 12, 10, 1, "South", "forward", false, false, 26, true);
        IPlayer player9 = DefaultHockeyFactory.makePlayer(2, 2, 2, 1, "Ishan", "forward", false, false, 26, true);
        IPlayer player10 = DefaultHockeyFactory.makePlayer(10, 12, 10, 1, "Neirutya", "forward", false, false, 26, true);
        IPlayer player11 = DefaultHockeyFactory.makePlayer(2, 2, 2, 1, "Agni", "forward", false, false, 26, true);
        IPlayer player12= DefaultHockeyFactory.makePlayer(10, 15, 9, 11, "Vayavya", "forward", false, false, 25, true);
        IPlayer player13 = DefaultHockeyFactory.makePlayer(15, 14, 15, 1, "ria", "forward", true, false, 23,true);
        IPlayer player14 = DefaultHockeyFactory.makePlayer(12, 10, 14, 1, "dsf", "forward", false, false, 24, true);
        IPlayer player15= DefaultHockeyFactory.makePlayer(10, 15, 9, 19, "Wesfsfst", "forward", false, false, 25, true);
        IPlayer player16 = DefaultHockeyFactory.makePlayer(10, 12, 10, 1, "Sosfuth", "forward", false, false, 26, true);
        IPlayer player17 = DefaultHockeyFactory.makePlayer(2, 2, 2, 1, "Ishsfan", "forward", false, false, 26, true);
        IPlayer player18 = DefaultHockeyFactory.makePlayer(10, 12, 10, 1, "Neirsfutya", "defense", false, false, 26, true);
        IPlayer player19 = DefaultHockeyFactory.makePlayer(2, 2, 2, 1, "Agsfni", "defense", false, false, 26, false);
        IPlayer player20= DefaultHockeyFactory.makePlayer(10, 15, 9, 11, "Vayasfvya", "defense", false, false, 25, false);
        IPlayer player21 = DefaultHockeyFactory.makePlayer(15, 14, 15, 1, "Nosfrth", "defense", true, false, 23,false);
        IPlayer player22 = DefaultHockeyFactory.makePlayer(12, 10, 14, 1, "Esfsast", "defense", false, false, 24, false);
        IPlayer player23= DefaultHockeyFactory.makePlayer(10, 15, 9, 19, "Wessft", "defense", false, false, 25, false);
        IPlayer player24 = DefaultHockeyFactory.makePlayer(10, 12, 10, 1, "Sousfth", "defense", false, false, 26, false);
        IPlayer player25 = DefaultHockeyFactory.makePlayer(2, 2, 2, 1, "Issfahan", "defense", false, false, 26, false);
        IPlayer player26 = DefaultHockeyFactory.makePlayer(10, 12, 10, 1, "Neiffrutya", "defense", false, false, 26, false);
        IPlayer player27 = DefaultHockeyFactory.makePlayer(2, 2, 2, 20, "Agfdfni", "forward", false, false, 26, false);
        IPlayer player28= DefaultHockeyFactory.makePlayer(10, 15, 9, 15, "Vinayak", "goalie", false, false, 25, false);
        IPlayer player29 = DefaultHockeyFactory.makePlayer(2, 2, 2, 16, "Aggfrni", "goalie", false, false, 26, true);
        IPlayer player30= DefaultHockeyFactory.makePlayer(10, 15, 9, 14, "Vaysfdsavya", "goalie", false, false, 25, true);

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

        IGeneralManager gm2 = DefaultHockeyFactory.makeGeneralManager("John Smith","shrewd");
        team2 = DefaultHockeyFactory.makeTeam("Miami", gm2, null, playerList2);
        team2.setLosses(4);

        IPlayer player31 = DefaultHockeyFactory.makePlayer(12, 11, 11, 1, "Jigar", "forward", true, false, 23, true);
        IPlayer player32 = DefaultHockeyFactory.makePlayer(7, 8, 9, 1, "Raj", "defense", false, false, 24, true);
        IPlayer player33= DefaultHockeyFactory.makePlayer(10, 5, 5, 18, "Jatin", "goalie", false, false, 25,true);
        IPlayer player34 = DefaultHockeyFactory.makePlayer(10, 12, 10, 1, "Alex", "defense", false, false, 26, false);
        playerList3.add(player31);
        playerList3.add(player32);
        playerList3.add(player33);
        playerList3.add(player34);

        IGeneralManager gm3 = DefaultHockeyFactory.makeGeneralManager("Marry Pascal","gambler");
        team3 = DefaultHockeyFactory.makeTeam("NewYork", gm3, null, playerList3);

        IPlayer player35 = DefaultHockeyFactory.makePlayer(15, 15, 15, 1, "Alfa", "forward", true, false, 23, true);
        IPlayer player36 = DefaultHockeyFactory.makePlayer(12, 13, 14, 1, "Beta", "defense", false, false, 24, true);
        IPlayer player37= DefaultHockeyFactory.makePlayer(10, 15, 9, 18, "Gama", "goalie", false, false, 25, true);
        IPlayer player38 = DefaultHockeyFactory.makePlayer(10, 12, 10, 1, "Theta", "defense", false, false, 26, true);

        playerList4.add(player35);
        playerList4.add(player36);
        playerList4.add(player37);
        playerList4.add(player38);

        IGeneralManager gm4 = DefaultHockeyFactory.makeGeneralManager("Ram Saxsena","gambler");
        team4 = DefaultHockeyFactory.makeTeam("Viena", gm4, null, playerList4);

        IPlayer player39 = DefaultHockeyFactory.makePlayer(15, 14, 15, 1, "North", "forward", true, false, 23,true);
        IPlayer player40 = DefaultHockeyFactory.makePlayer(12, 10, 14, 1, "East", "defense", false, false, 24, true);
        IPlayer player41= DefaultHockeyFactory.makePlayer(10, 15, 9, 19, "West", "goalie", false, false, 25, true);
        IPlayer player42 = DefaultHockeyFactory.makePlayer(10, 12, 10, 1, "South", "defense", false, false, 26, false);
        IPlayer player43 = DefaultHockeyFactory.makePlayer(2, 2, 2, 1, "Ishan", "forward", false, false, 26, true);
        IPlayer player44 = DefaultHockeyFactory.makePlayer(10, 12, 10, 1, "Neirutya", "forward", false, false, 26, true);
        IPlayer player45 = DefaultHockeyFactory.makePlayer(2, 2, 2, 1, "Agni", "defense", false, false, 26, true);
        IPlayer player46= DefaultHockeyFactory.makePlayer(10, 15, 9, 11, "Vayavya", "goalie", false, false, 25, true);

        playerList5.add(player39);
        playerList5.add(player40);
        playerList5.add(player41);
        playerList5.add(player42);
        playerList5.add(player43);
        playerList5.add(player44);
        playerList5.add(player45);
        playerList5.add(player46);

        IGeneralManager gm5 = DefaultHockeyFactory.makeGeneralManager("John Snow","gambler");
        team5 = DefaultHockeyFactory.makeTeam("Mexico", gm5, null, playerList5);
        team5.setLosses(3);

        IGeneralManager gm6 = DefaultHockeyFactory.makeGeneralManager("John Smith","normal");
        team6 = DefaultHockeyFactory.makeTeam("North Carolina", gm6, null, playerList4);

        IPlayer newplayer01 = DefaultHockeyFactory.makePlayer(12, 2, 20, 1, "Ramekhksh", "forward", true, false, 30, true);
        IPlayer newplayer02 = DefaultHockeyFactory.makePlayer(12, 12, 2, 1, "Surdtuyesh", "goalie", false, false, 31, true);
        IPlayer newplayer03 = DefaultHockeyFactory.makePlayer(12, 12, 2, 2, "Mahyrysryesh", "defense", false, false, 32, true);
        IPlayer newplayer04 = DefaultHockeyFactory.makePlayer(2, 2, 12, 1, "Lokryresh", "forward", false, false, 33, true);
        IPlayer newplayer5 = DefaultHockeyFactory.makePlayer(15, 14, 15, 1, "Ndrdydorth", "forward", true, false, 23,true);
        IPlayer newplayer6 = DefaultHockeyFactory.makePlayer(12, 10, 14, 1, "Eddast", "forward", false, false, 24, true);
        IPlayer newplayer7= DefaultHockeyFactory.makePlayer(10, 15, 9, 19, "Weddgddst", "forward", false, false, 25, true);
        IPlayer newplayer8 = DefaultHockeyFactory.makePlayer(10, 12, 10, 1, "Sdgdouth", "forward", false, false, 26, true);
        IPlayer newplayer9 = DefaultHockeyFactory.makePlayer(2, 12, 12, 1, "Ishdtdstan", "forward", false, false, 26, true);
        IPlayer newplayer10 = DefaultHockeyFactory.makePlayer(10, 12, 10, 1, "Neirdgddutya", "forward", false, false, 26, true);
        IPlayer newplayer11 = DefaultHockeyFactory.makePlayer(2, 2, 2, 1, "Aggdni", "forward", false, false, 26, true);
        IPlayer newplayer12= DefaultHockeyFactory.makePlayer(10, 15, 19, 11, "Vayavsdsya", "forward", false, false, 25, true);
        IPlayer newplayer13 = DefaultHockeyFactory.makePlayer(15, 14, 15, 1, "rdfsdfia", "forward", true, false, 23,true);
        IPlayer newplayer14 = DefaultHockeyFactory.makePlayer(12, 10, 14, 1, "dssdfdsf", "forward", false, false, 24, true);
        IPlayer newplayer15= DefaultHockeyFactory.makePlayer(10, 15, 9, 19, "Wessfsffsfst", "forward", false, false, 25, true);
        IPlayer newplayer16 = DefaultHockeyFactory.makePlayer(10, 12, 10, 1, "Sosfssfuth", "forward", false, false, 26, true);
        IPlayer newplayer17 = DefaultHockeyFactory.makePlayer(2, 12, 2, 1, "Ishssfsfan", "forward", false, false, 26, true);
        IPlayer newplayer18 = DefaultHockeyFactory.makePlayer(10, 12, 10, 1, "Nefsdirsfutya", "defense", false, false, 26, true);
        IPlayer newplayer19 = DefaultHockeyFactory.makePlayer(2, 2, 2, 1, "Agsdgfni", "defense", false, false, 26, false);
        IPlayer newplayer20= DefaultHockeyFactory.makePlayer(10, 15, 9, 11, "Vaydgdasfvya", "defense", false, false, 25, false);
        IPlayer newplayer21 = DefaultHockeyFactory.makePlayer(15, 14, 15, 1, "Nosdgsfrth", "defense", true, false, 23,false);
        IPlayer newplayer22 = DefaultHockeyFactory.makePlayer(12, 10, 14, 1, "Esfdgdsast", "defense", false, false, 24, false);
        IPlayer newplayer23= DefaultHockeyFactory.makePlayer(10, 15, 19, 19, "Wesggdsft", "defense", false, false, 25, false);
        IPlayer newplayer24 = DefaultHockeyFactory.makePlayer(10, 12, 10, 1, "Sogdusfth", "defense", false, false, 26, false);
        IPlayer newplayer25 = DefaultHockeyFactory.makePlayer(2, 12, 12, 1, "Issfdgadahan", "defense", false, false, 26, false);
        IPlayer newplayer26 = DefaultHockeyFactory.makePlayer(10, 12, 10, 1, "Neifsfdsgfrutya", "defense", false, false, 26, false);
        IPlayer newplayer27 = DefaultHockeyFactory.makePlayer(2, 12, 2, 20, "Agsgsfdfni", "forward", false, false, 26, false);
        IPlayer newplayer28= DefaultHockeyFactory.makePlayer(10, 15, 9, 15, "Vsgayavfdgya", "goalie", false, false, 25, false);
        IPlayer newplayer29 = DefaultHockeyFactory.makePlayer(2, 2, 12, 16, "Aggfrsgni", "goalie", false, false, 26, true);
        IPlayer newplayer30= DefaultHockeyFactory.makePlayer(10, 15, 9, 14, "Vayssgfdsavya", "goalie", false, false, 25, true);

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

        IGeneralManager gm7 = DefaultHockeyFactory.makeGeneralManager("John Smith","normal");
        team7 = DefaultHockeyFactory.makeTeam("Florida", gm7, null, playerList7);
        team7.setLosses(4);

        teamsList.add(team1);
        teamsList.add(team2);
        teamsList.add(team3);
        teamsList.add(team4);
        teamsList.add(team5);
        teamsList.add(team6);
        teamsList.add(team7);

        IPlayer firstFreeAgent = DefaultHockeyFactory.makePlayer(2, 4, 6, 1, "firstFreeAgent", "forward", true, false, 25, true);
        IPlayer secondFreeAgent = DefaultHockeyFactory.makePlayer(7, 8, 9, 10, "secondFreeAgent", "goalie", true, false, 30, true);
        IPlayer thirdFreeAgent = DefaultHockeyFactory.makePlayer(11, 12, 13, 0, "thirdFreeAgent", "defense", true, false, 24, true);
        IPlayer forthFreeAgent = DefaultHockeyFactory.makePlayer(7, 8, 9, 0, "forthFreeAgent", "forward", true, false, 26, true);
        IPlayer fifthFreeAgent = DefaultHockeyFactory.makePlayer(8, 9, 10, 15, "fifthFreeAgent", "goalie", true, false, 27, true);
        IPlayer sixthFreeAgent = DefaultHockeyFactory.makePlayer(12, 13, 14, 0, "sixthFreeAgent", "defense", true, false, 28, true);
        IPlayer FreeAgent7 = DefaultHockeyFactory.makePlayer(2, 4, 6, 1, "firstFredfeAgent", "forward", true, false, 25, true);
        IPlayer secondFreeAgent8 = DefaultHockeyFactory.makePlayer(7, 8, 9, 10, "segfdgcondFreeAgent", "goalie", true, false, 30, true);
        IPlayer thirdFreeAgent9 = DefaultHockeyFactory.makePlayer(11, 12, 13, 0, "thirdgfdgdFreeAgent", "defense", true, false, 24, true);
        IPlayer forthFreeAgent10 = DefaultHockeyFactory.makePlayer(7, 8, 9, 0, "forthFreddgdfeAgent", "forward", true, false, 26, true);
        IPlayer fifthFreeAgent11 = DefaultHockeyFactory.makePlayer(8, 9, 10, 15, "fiftsdfhFreeAgent", "goalie", true, false, 27, true);
        IPlayer sixthFreeAgent12 = DefaultHockeyFactory.makePlayer(12, 13, 14, 0, "sixthfdsfFreeAgent", "defense", true, false, 28, true);
        IPlayer firstFreeAgent13 = DefaultHockeyFactory.makePlayer(2, 4, 6, 1, "firffgrstFreeAgent", "forward", true, false, 25, true);
        IPlayer secondFreeAgent14 = DefaultHockeyFactory.makePlayer(7, 8, 9, 10, "secondFreegtgAgent", "goalie", true, false, 30, true);
        IPlayer thirdFreeAgent15 = DefaultHockeyFactory.makePlayer(11, 12, 13, 0, "thifrrdFreeAgent", "defense", true, false, 24, true);
        IPlayer forthFreeAgent16 = DefaultHockeyFactory.makePlayer(7, 8, 9, 0, "forthFewfreeAgent", "forward", true, false, 26, true);
        IPlayer fifthFreeAgent17 = DefaultHockeyFactory.makePlayer(8, 9, 10, 15, "fiftfEWFEWhFreeAgent", "goalie", true, false, 27, true);
        IPlayer sixthFreeAgent18 = DefaultHockeyFactory.makePlayer(12, 13, 14, 0, "siEWFdfdxthFreeAgent", "defense", true, false, 28, true);
        IPlayer firstFreeAgent19 = DefaultHockeyFactory.makePlayer(2, 4, 6, 1, "firstFresfsfeAgent", "forward", true, false, 25, true);
        IPlayer secondFreeAgent20 = DefaultHockeyFactory.makePlayer(7, 8, 9, 10, "secondFresfeAgent", "goalie", true, false, 30, true);
        IPlayer thirdFreeAgent21 = DefaultHockeyFactory.makePlayer(11, 12, 13, 0, "thirdFsfsreeAgent", "defense", true, false, 24, false);
        IPlayer forthFreeAgent22 = DefaultHockeyFactory.makePlayer(7, 8, 9, 0, "forthFreseAgent", "forward", true, false, 26, false);
        IPlayer fifthFreeAgent23 = DefaultHockeyFactory.makePlayer(8, 9, 10, 15, "fifthFGTRreeAgent", "goalie", true, false, 27, false);
        IPlayer sixthFreeAgent24 = DefaultHockeyFactory.makePlayer(12, 13, 14, 0, "sixthFrefgfeAgent", "defense", true, false, 28, false);
        IPlayer firstFreeAgent25 = DefaultHockeyFactory.makePlayer(2, 4, 6, 1, "firssfstFreeAgent", "forward", true, false, 25, false);
        IPlayer secondFreeAgent26 = DefaultHockeyFactory.makePlayer(7, 8, 9, 10, "secondFhgfhreeAgent", "goalie", true, false, 30, false);
        IPlayer thirdFreeAgent27 = DefaultHockeyFactory.makePlayer(11, 12, 13, 0, "thirdFsdffreeAgent", "defense", true, false, 24, false);
        IPlayer forthFreeAgent28 = DefaultHockeyFactory.makePlayer(7, 8, 9, 0, "forthFreaaeAgent", "forward", true, false, 26, false);
        IPlayer fifthFreeAgent29 = DefaultHockeyFactory.makePlayer(8, 9, 10, 15, "fifthgfhFreeAgent", "goalie", true, false, 27, false);
        IPlayer sixthFreeAgent30 = DefaultHockeyFactory.makePlayer(12, 13, 14, 0, "sixthFreejAgent", "defense", true, false, 28, false);
        IPlayer firstFreeAgent31 = DefaultHockeyFactory.makePlayer(2, 4, 6, 1, "firstFreeghAgent", "forward", true, false, 25, false);
        IPlayer secondFreeAgent32 = DefaultHockeyFactory.makePlayer(7, 8, 9, 10, "secondgddFreeAgent", "goalie", true, false, 30, false);
        IPlayer thirdFreeAgent33 = DefaultHockeyFactory.makePlayer(11, 12, 13, 0, "thidgdrdFreeAgent", "defense", true, false, 24, false);
        IPlayer forthFreeAgent34 = DefaultHockeyFactory.makePlayer(7, 8, 9, 0, "forthFrdgdaeeAgent", "forward", true, false, 26, false);
        IPlayer fifthFreeAgent35 = DefaultHockeyFactory.makePlayer(8, 9, 10, 15, "fiagdfgfthFreeAgent", "goalie", true, false, 27, false);
        IPlayer sixthFreeAgent36 = DefaultHockeyFactory.makePlayer(12, 13, 14, 0, "sixthFjjesreeAgent", "defense", true, false, 28, false);

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

        List<IPlayer> offeredPlayerList = new ArrayList<>();
        offeredPlayerList.add(player2);
        offeredPlayerList.add(player4);
        List<IPlayer> requestedPlayerList = new ArrayList<>();
        requestedPlayerList.add(player14);
        requestedPlayerList.add(player16);
        tradeCharter = TradingFactory.makeTradeCharter(team1, offeredPlayerList, team4, requestedPlayerList, -1);
        int draftRound = tradeCharter.getDraftRoundIdx();
        inValidCharter = TradingFactory.makeTradeCharter(null, null, team4, requestedPlayerList, draftRound);

        tradingTeamsBuffer= new ArrayList<>();
        Triplet<ITeam, List<IPlayer>, Float> teamRequestEntry1 =
                Triplet.of(team1, team1.getPlayers(),team1.getTeamStrength());
        Triplet<ITeam, List<IPlayer>, Float> teamRequestEntry2 =
                Triplet.of(team3, team3.getPlayers(),team3.getTeamStrength());
        Triplet<ITeam, List<IPlayer>, Float> teamRequestEntry3 =
                Triplet.of(team4, team4.getPlayers(),team4.getTeamStrength());

        tradingTeamsBuffer.add(teamRequestEntry1);
        tradingTeamsBuffer.add(teamRequestEntry2);
        tradingTeamsBuffer.add(teamRequestEntry3);

        IAging aging = DefaultHockeyFactory.makeAging(30, 55);
        IInjuries injuries = DefaultHockeyFactory.makeInjuries(1, 1, 100);
        ITraining training = DefaultHockeyFactory.makeTraining(0);
        IGameplayConfig gameplayConfig = DefaultHockeyFactory.makeGameplayConfig(aging, injuries, training, this.trading);

        List<IDivision> divisionsList = new ArrayList<>();
        IDivision atlanticDivision = DefaultHockeyFactory.makeDivision("Atlantic", teamsList);
        divisionsList.add(atlanticDivision);
        List<IConference> conferenceList = new ArrayList<>();
        IConference conference = DefaultHockeyFactory.makeConference("Eastern Conference", divisionsList);
        conferenceList.add(conference);

        league = DefaultHockeyFactory.makeLeague("Dalhousie Hockey League", conferenceList, freeAgentsList, gameplayConfig, null, null);
    }

    public void addPlayertoTeam2(){
        IPlayer x = DefaultHockeyFactory.makePlayer(15, 15, 15, 1, "Alfa", "forward", true, false, 23, true);
        IPlayer y = DefaultHockeyFactory.makePlayer(12, 13, 14, 1, "Beta", "defense", false, false, 24, true);
        IPlayer z = DefaultHockeyFactory.makePlayer(10, 15, 9, 18, "Gama", "goalie", false, false, 25, true);
        team2.getPlayers().add(x);
        team2.getPlayers().add(y);
        team2.getPlayers().add(z);
        team2.getRoster().updateSubRoster(team2.getPlayers());
    }

    public void dropPlayerFromTeam2(){
        for(int i=0; i<3; i++){
            team2.getPlayers().remove(0);
        }
        team2.getRoster().updateSubRoster(team2.getPlayers());
    }

    public ITeam getTeam1() {
        return team1;
    }

    public ITeam getTeam5() {
        return team5;
    }

    public ITeam getTeam6() {
        return team6;
    }

    public ITeam getTeam2() {
        return team2;
    }

    public ITeam getTeam3() {
        return team3;
    }

    public List<ITeam> getTeamList() {
        return teamsList;
    }

    public List<IPlayer> getFreeAgentsList() {
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

    public ILeague getLeagueInfo() {
        return league;
    }

    public ILeague getLeague() {
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

    public List<Triplet<ITeam, List<IPlayer>, Float>> getTradingTeamsBuffer() {
        return tradingTeamsBuffer;
    }
}
