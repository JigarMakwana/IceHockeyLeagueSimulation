package group11.Hockey;
import group11.Hockey.BusinessLogic.models.*;
import group11.Hockey.InputOutput.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class contain all the logic to trade
 * 1. Between AI Teams and
 * 2. Between AI to User Teams
 * @author  Jigar Makwana B00842568
 */

public class AITrading {
    private League leagueObj;
    private Trading tradingConfig;
    IDisplay display = new Display();
    ICommandLineInput userInputMode = new CommandLineInput();
    IUserInputValidation userSelection = new UserInputValidation();
    enum Position
    {
        FORWARD,
        DEFENSE,
        GOALIE
    }

    public AITrading(League leagueObj)
    {
        this.leagueObj = leagueObj;
        GameplayConfig gameConfig = this.leagueObj.getGamePlayConfig();
        this.tradingConfig = gameConfig.getTrading();
    }

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
//                if( randomTradeOfferChance < tradingConfig.getRandomTradeOfferChance())
//                {
                display.showMessageOnConsole("\nGenerating Trade for AI Team " + eligibleTeamList.get(i).getTeamName() );
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
                    Triplet<Team, List<Player>, Float> tradeTeam = findStrongestTradeTeam(tradingTeamsBuffer);

                    if(tradeTeam.getFirst().isUserTeam())
                    {
                        resolveAIToUserTrade(eligibleTeamList.get(i), weakestPlayerList,
                                tradeTeam.getFirst(), tradeTeam.getSecond());
                    }
                    else
                    {
                        display.displayTradeStatistics(eligibleTeamList.get(i), weakestPlayerList,
                                tradeTeam.getFirst(), tradeTeam.getSecond());
                        resolveAIToAITrade(eligibleTeamList.get(i), weakestPlayerList,
                                tradeTeam.getFirst(), tradeTeam.getSecond());
                    }
                    settleTeamAfterTrade(eligibleTeamList.get(i));
                    settleTeamAfterTrade(tradeTeam.getFirst());
                    eligibleTeamList.remove(0);
                    teamLength = eligibleTeamList.size();
                }
//                }
            }
            else
            {
                eligibleTeamList.remove(i);
                teamLength = eligibleTeamList.size();
            }
        }
    }

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
                            team.getTeamName().equalsIgnoreCase("Mexico" +
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
//        System.out.println("------- ** Teams Eligible for Trade ** -------");
//        for (Team team : eligibleTeamList)
//        {
//            System.out.println(team.getTeamName());
//        }
        return eligibleTeamList;
    }

    private float generateRandomNumber()
    {
        Random rand = new Random();
        float randomTradeOfferChance = rand.nextFloat();
        return randomTradeOfferChance;
    }

    public List<Player> findWeakestPlayers(Team team)
    {
        int maxPlayers = tradingConfig.getMaxPlayersPerTrade();
        List<Player> unSortedPlayerList = team.getPlayers();
        List<Player> playerList = sortPlayersByStrength(unSortedPlayerList);
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

    public List<Player> findStrongestPlayers(Team team, List<Integer> playerPositionFlag)
    {
        int maxPlayers = tradingConfig.getMaxPlayersPerTrade();
        List<Player> unSortedPlayerList = team.getPlayers();
        List<Player> playerList = sortPlayersByStrength(unSortedPlayerList);
//        List<Player> playerList = team.sortPlayersByStrength();
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

    public Triplet<Team, List<Player>, Float> findStrongestTradeTeam(
            List<Triplet<Team, List<Player>, Float>> tradingTeamsBuffer)
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
        display.showMessageOnConsole("Successfully found strongest trade team " + tradeTeam.getFirst().getTeamName());
        return tradeTeam;
    }

    public void resolveAIToAITrade(Team team1, List<Player> offeredPlayerList,
                                   Team team2, List<Player> requestedPlayerList)
    {
        Float playerStrength1 = strengthSum(offeredPlayerList);
        Float playerStrength2 = strengthSum(requestedPlayerList);
        float randomAcceptanceChance = this.generateRandomNumber();
//        if(randomAcceptanceChance < tradingConfig.getRandomAcceptanceChance())
        if(true)

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
        display.displayTradeStatisticsToUser(team1, offeredPlayerList, team2, requestedPlayerList);
        display.displayAcceptRejectOptionToUser();

        int userInput = userSelection.validateUserTradeInput();
        if(userInput == 1)
        {
            acceptTrade(team1, offeredPlayerList, team2, requestedPlayerList);
        }
        else if(userInput == 0)
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

    public void rejectTrade(Team team)
    {
        display.showMessageOnConsole("Trade is declined.");
        resetLossPoints(team);
    }

    public void acceptTrade(Team team1, List<Player> offeredPlayerList, Team team2, List<Player> requestedPlayerList)
    {
        List<Player> localOfferedPlayerList = new ArrayList<>();
        for(Player p : offeredPlayerList) {
            localOfferedPlayerList.add(p);
        }
        List<Player> localRequestedPlayerList = new ArrayList<>();
        for(Player p : requestedPlayerList) {
            localRequestedPlayerList.add(p);
        }

        for(Player toBeRemoved :localOfferedPlayerList)
        {
            team1.getPlayers().removeIf(player ->
                    player.getPlayerName().equals(toBeRemoved.getPlayerName()));
        }
        for(Player toBeRemoved :localRequestedPlayerList)
        {
            team2.getPlayers().removeIf(player ->
                    player.getPlayerName().equals(toBeRemoved.getPlayerName()));
        }
        for(Player toBeAdded :localRequestedPlayerList)
        {
            team1.getPlayers().add(toBeAdded);
        }
        for(Player toBeAdded :localOfferedPlayerList)
        {
            team2.getPlayers().add(toBeAdded);
        }
        resetLossPoints(team1);
        resetLossPoints(team2);
        display.showMessageOnConsole("Trade successfully accepted!");
    }

    public void resetLossPoints(Team team)
    {
        team.setLossPoint(0);
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

    public List<Player>  sortPlayersByStrength(List<Player> unSortedPlayerList) {
        List<Player> sortedPlayerList = unSortedPlayerList;
        /* bubble sort */
        int i, j;
        Player temp;
        boolean swapped;
        int length = sortedPlayerList.size();
        for (i = 0; i < length - 1; i++) {
            swapped = false;
            for (j = 0; j < length - i - 1; j++) {
                if (sortedPlayerList.get(j).getPlayerStrength() >
                        sortedPlayerList.get(j + 1).getPlayerStrength()) {
                    temp = sortedPlayerList.get(j);
                    sortedPlayerList.set(j, sortedPlayerList.get(j + 1));
                    sortedPlayerList.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (swapped == false)
                break;
        }
        return sortedPlayerList;
    }

    public void settleTeamAfterTrade(Team team)
    {
        display.showMessageOnConsole("\nSettling Team " + team.getTeamName() + "'s size after trade negotiation...");
        SettleTeamRoster settleObj = new SettleTeamRoster(leagueObj);
        try{
            settleObj.settleTeam(team);
            display.showMessageOnConsole("Team " + team.getTeamName() + "'s size successfully settled!");
        }
        catch (Exception e)
        {
            display.showMessageOnConsole("Teams cannot be settled after trade negotiation.");
        }
    }
}
