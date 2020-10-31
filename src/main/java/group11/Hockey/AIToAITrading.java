package group11.Hockey;
import group11.Hockey.models.*;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class contain all the logic to trade between AI teams
 *
 * @author  Jigar Makwana B00842568
 *
 */

public class AIToAITrading implements ITradingEligibility, IGenerateTradeOffers,
        IresolveAIToAITradeOffers, ISettleTrading {
    protected League leagueObj;
    protected Trading tradingConfig;
    protected static final int TEAM_SIZE = 20;
    protected static final int SKATERS_SIZE = 18;
    protected static final int GOALIE_SIZE = 2;

    enum Position
    {
        FORWARD,
        DEFENSE,
        GOALIE
    }

    enum UseChoice
    {
        ACCEPT,
        REJECT
    }

    public AIToAITrading(League leagueObj)
    {
        this.leagueObj = leagueObj;
        GameplayConfig gameConfig = this.leagueObj.getGamePlayConfig();
        this.tradingConfig = gameConfig.getTrading();
    }

    @Override
    public void generateTradeOffers()
    {
        List<Team> eligibleTeamList = determineEligibleTeams();
        int teamLength = eligibleTeamList.size();
        boolean isAITeam = false;
        for (int i=0; teamLength > 1; i=0)
        {
            if(eligibleTeamList.get(i).isUserTeam())
            {
                isAITeam = false;
            }
            else
            {
                isAITeam = true;
            }
            if(isAITeam)
            {
                float randomTradeOfferChance = this.generateRandomNumber();
                if( randomTradeOfferChance < tradingConfig.getRandomTradeOfferChance())
                {
                    System.out.println("\nGenerating Trade for AI Team " + eligibleTeamList.get(i).getTeamName() );
                    List<Triplet<Team, List<Player>, Float>> tradingTeamsBuffer= new ArrayList<>();
                    List<Player> weakestPlayerList = findWeakestPlayers(eligibleTeamList.get(i));
                    List<Integer> playerPositionFlag = findPlayerPositions(weakestPlayerList);

                    for (int k=1; k<eligibleTeamList.size(); k++)
                    {
                        List<Player> strongestPlayerList =
                                findStrongestPlayers(eligibleTeamList.get(k),playerPositionFlag);
                        Float strengthSum = strengthSum(strongestPlayerList);
                        Triplet<Team, List<Player>, Float> teamRequestEntry =
                                Triplet.of(eligibleTeamList.get(k), strongestPlayerList, strengthSum);
                        tradingTeamsBuffer.add(teamRequestEntry);
                    }
                    if(tradingTeamsBuffer.size() > 0){
                        Triplet<Team, List<Player>, Float> tradeTeam =
                                findStrongestTradeTeam(eligibleTeamList.get(i), tradingTeamsBuffer);
                        if(tradeTeam.getFirst().isUserTeam())
                        {
                            resolveAIToUserTrade(eligibleTeamList.get(i), weakestPlayerList,
                                    tradeTeam.getFirst(), tradeTeam.getSecond());
                        }
                        else
                        {
                            displayTradeStatistics(eligibleTeamList.get(i), weakestPlayerList,
                                    tradeTeam.getFirst(), tradeTeam.getSecond());
                            resolveAIToAITrade(eligibleTeamList.get(i), weakestPlayerList,
                                    tradeTeam.getFirst(), tradeTeam.getSecond());
                        }
                        eligibleTeamList.remove(0);
                        teamLength = eligibleTeamList.size();
                    }
                }
            }
            else
            {
                eligibleTeamList.remove(i);
                teamLength = eligibleTeamList.size();
            }
        }
    }

    @Override
    public List<Team> determineEligibleTeams()
    {
        int lossPointCutOff = tradingConfig.getLossPoint();
        boolean isAITeam;
        List<Team> eligibleTeamList = new ArrayList<Team>();
        List<Conference> conferenceList = leagueObj.getConferences();
        for (Conference conference : conferenceList)
        {
            List<Division> divisionList = conference.getDivisions();
            for (Division division : divisionList)
            {
                List<Team> teamList = division.getTeams();
                for (Team team : teamList)
                {
                    //TODO Mocking LossPoint for teams as Simulation is not ready
                    if(team.getTeamName().equalsIgnoreCase("Boston") ||
                            team.getTeamName().equalsIgnoreCase("Miami") ||
                            team.getTeamName().equalsIgnoreCase("NewYork") ||
                            team.getTeamName().equalsIgnoreCase("Viena")||
                            team.getTeamName().equalsIgnoreCase("Mexico1" +
                                    ""))
                    {
                        team.setLossPoint(2);
                    }
                    if(team.getTeamName().equalsIgnoreCase("Viena"))
                    {
                        team.setUserTeam(true);
                    }
                    if((team.getLossPoint() == lossPointCutOff))
                    {
                        eligibleTeamList.add(team);
                    }
                }
            }
        }
        System.out.println("------- ** Teams Eligible for Trade ** -------");
        for (Team team : eligibleTeamList)
        {
            System.out.println(team.getTeamName());
        }
        return eligibleTeamList;
    }

    @Override
    public float generateRandomNumber()
    {
        Random rand = new Random();
        float randomTradeOfferChance = rand.nextFloat();
        return randomTradeOfferChance;
    }

    @Override
    public List<Player> findWeakestPlayers(Team team)
    {
        int maxPlayers = tradingConfig.getMaxPlayersPerTrade();
        List<Player> playerList = team.sortPlayersByStrength();
//        System.out.println(team.getTeamName() + "'s Players");
//        displayPlayers(playerList);
        List<Player> weakestPlayerList = new ArrayList<Player>();
        for(int i=0; i < maxPlayers; i++)
        {
            weakestPlayerList.add(playerList.get(i));
        }
//        System.out.println("\nWeakest Players of team " + team.getTeamName() );
//        displayPlayers(weakestPlayerList);
        return weakestPlayerList;
    }

    public List<Integer> findPlayerPositions(List<Player> playerList){
        List<Integer> playerPositionFlag = new ArrayList<Integer>(Arrays.asList(0,0,0));
        for(int j=0; j<playerList.size(); j++)
        {
            String position = playerList.get(j).getPosition();
            if(Position.FORWARD.toString().equalsIgnoreCase(position))
            {
                int index = Position.FORWARD.ordinal();
                int value = playerPositionFlag.get(index);
                value = value + 1;
                playerPositionFlag.set(index,value);
            }
            else if(Position.DEFENSE.toString().equalsIgnoreCase(position))
            {
                int index = Position.DEFENSE.ordinal();
                int value = playerPositionFlag.get(index);
                value = value + 1;
                playerPositionFlag.set(index,value);
            }
            else if(Position.GOALIE.toString().equalsIgnoreCase(position))
            {
                int index = Position.GOALIE.ordinal();
                int value = playerPositionFlag.get(index);
                value = value + 1;
                playerPositionFlag.set(index,value);
            }
        }
        return playerPositionFlag;
    }

    @Override
    public List<Player> findStrongestPlayers(Team team, List<Integer> playerPositionFlag)
    {
        int maxPlayers = tradingConfig.getMaxPlayersPerTrade();
        List<Player> playerList = team.sortPlayersByStrength();
//        displayPlayers(playerList);
        List<Player> strongestPlayerList = new ArrayList<Player>();

        int noOfForwardNeeded = playerPositionFlag.get(Position.FORWARD.ordinal());
        int noOfDefenseNeeded = playerPositionFlag.get(Position.DEFENSE.ordinal());
        int noOfGoalieNeeded = playerPositionFlag.get(Position.GOALIE.ordinal());

        if(noOfForwardNeeded > 0)
        {
            int maxForwardPerTrade = 0;
            List<Player> forwardPlayerList= getForwardList(playerList);
            for(int i=forwardPlayerList.size()-1;
                maxForwardPerTrade < noOfForwardNeeded;
                i--,maxForwardPerTrade++)
            {
                strongestPlayerList.add(forwardPlayerList.get(i));
            }
        }

        if(noOfDefenseNeeded > 0)
        {
            int maxDefensePerTrade = 0;
            List<Player> defencePlayerList= getDefenseList(playerList);
            for(int i=defencePlayerList.size()-1;
                maxDefensePerTrade < noOfDefenseNeeded;
                i--,maxDefensePerTrade++)
            {
                strongestPlayerList.add(defencePlayerList.get(i));
            }
        }

        if(noOfGoalieNeeded > 0)
        {
            int maxGoaliePerTrade = 0;
            List<Player> goaliePlayerList= getGoalieList(playerList);
            for(int i=goaliePlayerList.size()-1;
                maxGoaliePerTrade < noOfGoalieNeeded;
                i--,maxGoaliePerTrade++)
            {
                strongestPlayerList.add(goaliePlayerList.get(i));
            }
        }
//        for(int i=playerList.size()-1; maxPlayersPerTrade < maxPlayers; i--,maxPlayersPerTrade++)
//        {
//            strongestPlayerList.add(playerList.get(i));
//        }
//        System.out.println("\nStrongest Players of team " + team.getTeamName() );
//        displayPlayers(strongestPlayerList);
        return strongestPlayerList;
    }

    @Override
    public Triplet<Team, List<Player>, Float> findStrongestTradeTeam(
            Team team, List<Triplet<Team, List<Player>, Float>> tradingTeamsBuffer)
    {
        List<Triplet<Team, List<Player>, Float>> sortedBuffer = tradingTeamsBuffer;
        /* bubble sort */
        int i, j;
        Triplet temp;
        boolean swapped;
        int length = sortedBuffer.size();
        for (i = 0; i < length - 1; i++) {
            swapped = false;
            for (j = 0; j < length - i - 1; j++) {
                if (sortedBuffer.get(j).getThird() >
                        sortedBuffer.get(j + 1).getThird())
                {
                    temp = sortedBuffer.get(j);
                    sortedBuffer.set(j, sortedBuffer.get(j + 1));
                    sortedBuffer.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (swapped == false)
                break;
        }
        Triplet<Team, List<Player>, Float> tradeTeam = sortedBuffer.get(length-1);
        System.out.println("Successfully found strongest trade team " + tradeTeam.getFirst().getTeamName());
        return tradeTeam;
    }

    @Override
    public void displayTradeStatistics(Team team1, List<Player> offeredPlayerList,
                                       Team team2, List<Player> requestedPlayerList)
    {
        System.out.println("\n****** Trade Statistics ******");
        System.out.println("\nTeam " + team1.getTeamName() + " is offering the trade to " + team2.getTeamName());
        System.out.println("---- Team " + team1.getTeamName() + "'s Players Offered ----");
        displayPlayers(offeredPlayerList);
        System.out.println("---- Team " + team2.getTeamName() + "'s Players Requested ----");
        displayPlayers(requestedPlayerList);
    }

    public void displayPlayers(List<Player> playersList)
    {
        int length = playersList.size();
        System.out.println("Player Name ----- Position ----- Strength");
        for (int i = 0; i <= length - 1; i++)
        {
            System.out.println(playersList.get(i).getPlayerName() + "       " +
                    playersList.get(i).getPosition() + "        " +
                    playersList.get(i).getPlayerStrength());
        }
    }

    public void displayTradeStatisticsToUser(Team team1, List<Player> offeredPlayerList,
                                             Team team2, List<Player> requestedPlayerList)
    {
        System.out.println("\n****** Woaha Trade Offer from AI Team ******");
        System.out.println("Team " + team1.getTeamName() + " is offering the trade");
        System.out.println("---- Team " + team1.getTeamName() + "'s Players Offered ----");
        displayPlayers(offeredPlayerList);
        System.out.println("---- Your Team's Requested Players ----");
        displayPlayers(requestedPlayerList);
    }

    @Override
    public void resolveAIToAITrade(Team team1, List<Player> offeredPlayerList,
                                   Team team2, List<Player> requestedPlayerList)
    {
        Float playerStrength1 = strengthSum(offeredPlayerList);
        Float playerStrength2 = strengthSum(requestedPlayerList);
        float randomAcceptanceChance = this.generateRandomNumber();
        if(randomAcceptanceChance < tradingConfig.getRandomAcceptanceChance())
//            if(true)

        {
            acceptTrade(team1, offeredPlayerList, team2, requestedPlayerList);
        }
        else if(playerStrength1 > playerStrength2)
        {
            acceptTrade(team1, offeredPlayerList, team2, requestedPlayerList);
        }
        else
        {
            rejectTrade(team1);
        }
    }

    public void resolveAIToUserTrade(Team team1, List<Player> offeredPlayerList,
                                     Team team2, List<Player> requestedPlayerList)
    {
        displayTradeStatisticsToUser(team1, offeredPlayerList, team2, requestedPlayerList);
        System.out.println("Press 1 to Accept the trade\nPress any other key to Reject the trade.");
        ICommandLineInput userInputMode = new CommandLineInput();
        int userChoice = userInputMode.getInt();

        if((userChoice == (UseChoice.ACCEPT.ordinal() + 1)))
        {
            acceptTrade(team1, offeredPlayerList, team2, requestedPlayerList);
        }
        else
        {
            rejectTrade(team1);
        }
    }

    public Float strengthSum(List<Player> playerList)
    {
        Float strengthSum = 0.0f;
        for(int l=0; l<playerList.size(); l++)
        {
            strengthSum += playerList.get(l).getPlayerStrength();
        }
        return strengthSum;
    }

    @Override
    public void rejectTrade(Team team)
    {
        System.out.println("Trade is declined.");
        resetLossPoints(team);
    }

    @Override
    public void acceptTrade(Team team1, List<Player> offeredPlayerList, Team team2, List<Player> requestedPlayerList)
    {
        for(Player toBeRemoved :offeredPlayerList)
        {
            team1.getPlayers().removeIf(player ->
                    player.getPlayerName().equals(toBeRemoved.getPlayerName()));
        }
        for(Player toBeAdded :requestedPlayerList)
        {
            team1.getPlayers().add(toBeAdded);
        }

        for(Player toBeRemoved :requestedPlayerList)
        {
            team2.getPlayers().removeIf(player ->
                    player.getPlayerName().equals(toBeRemoved.getPlayerName()));
        }
        for(Player toBeAdded :offeredPlayerList)
        {
            team2.getPlayers().add(toBeAdded);
        }
        resetLossPoints(team1);
        resetLossPoints(team2);
        settleTeam(team1);
        settleTeam(team2);
        System.out.println("Trade successfully accepted and team changes has been made!");
    }

    @Override
    public void resetLossPoints(Team team) {
        team.setLossPoint(0);
    }

    @Override
    public void settleTeam(Team team) {
        int noOfPlayers = team.getPlayers().size();
        List<Player> playerList = team.getPlayers();
        List<Integer> playerPositionFlag = findPlayerPositions(playerList);
        int noOfForward = playerPositionFlag.get(Position.FORWARD.ordinal());
        int noOfDefense = playerPositionFlag.get(Position.DEFENSE.ordinal());
        int noOfGoalies = playerPositionFlag.get(Position.GOALIE.ordinal());
        int noOfSkaters = noOfForward + noOfDefense;
        if(noOfPlayers > TEAM_SIZE)
        {
            if(noOfGoalies > GOALIE_SIZE)
            {
                int noOfGoaliesToBeDropped = noOfGoalies - GOALIE_SIZE;
                for(int i=0; i<noOfGoaliesToBeDropped; i++)
                {
                    dropPlayer(playerList, Position.GOALIE);
                }
            }
            if(noOfSkaters > SKATERS_SIZE)
            {
                int noOfSkatersToBeDropped =  noOfSkaters - SKATERS_SIZE;
                for(int i=0; i<noOfSkatersToBeDropped; i++)
                {
                    dropPlayer(playerList, Position.DEFENSE);
                    // Dropping defense players as it should be 6
                }
            }
        }
        else if (noOfPlayers < TEAM_SIZE)
        {
            if(noOfGoalies < GOALIE_SIZE)
            {
                int noOfGoaliesToBeHired = GOALIE_SIZE - noOfGoalies;
                for(int i=0; i<noOfGoaliesToBeHired; i++)
                {
                    hirePlayer(playerList,  Position.GOALIE);
                }
            }
            if(noOfSkaters < SKATERS_SIZE)
            {
                int noOfSkatersToBeHired = SKATERS_SIZE - noOfSkaters;
                for(int i=0; i<noOfSkatersToBeHired; i++)
                {
                    hirePlayer(playerList, Position.FORWARD);
                    // Hiring forward players as it should be 12
                }
            }
        }
    }

    public void dropPlayer(List<Player> playerList, Position playerPosition)
    {
        Player p = new Player();
        p.setPosition(playerPosition.toString());
        p.dropPlayerToFreeAgent(leagueObj,playerList);
    }

    public void hirePlayer(List<Player> playerList, Position playerPosition)
    {
        Player p = new Player();
        p.setPosition(playerPosition.toString());
        p.replacePlayerWithFreeAgent(leagueObj,playerList);
    }

    public List<Player> getForwardList(List<Player> playerList)
    {
        List<Player> forwardPlayerList= playerList.stream().filter(player ->
                player.getPosition().equalsIgnoreCase(Position.FORWARD.toString())).collect(Collectors.toList());
        return forwardPlayerList;
    }

    public List<Player> getDefenseList(List<Player> playerList)
    {
        List<Player> defencePlayerList= playerList.stream().filter(player ->
                player.getPosition().equalsIgnoreCase(Position.DEFENSE.toString())).collect(Collectors.toList());
        return defencePlayerList;
    }

    public List<Player> getGoalieList(List<Player> playerList)
    {
        List<Player> goaliePlayerList= playerList.stream().filter(player ->
                player.getPosition().equalsIgnoreCase(Position.GOALIE.toString())).collect(Collectors.toList());
        return goaliePlayerList;
    }

}
