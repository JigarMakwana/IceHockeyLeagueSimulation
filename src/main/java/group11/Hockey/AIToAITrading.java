package group11.Hockey;
import group11.Hockey.models.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class contain all the logic to trade between AI teams
 *
 * @author  Jigar Makwana B00842568
 *
 */

public class AIToAITrading implements ITradingEligibility, IGenerateTradeOffers, IResolveTradeOffers, ISettleTrading {
    protected League leagueObj;
    protected Trading tradingConfig;

    enum Position {
        FORWARD,
        DEFENSE,
        GOALIE
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
        for (int i=0; teamLength > 1; i=0)
        {
//            if(this.generateRandomNumber() < tradingConfig.getRandomTradeOfferChance())
//            {
            System.out.println("\nGenerating Trade for " + eligibleTeamList.get(i).getTeamName() );
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
                        offerTrade(eligibleTeamList.get(i), tradingTeamsBuffer);
                displayTradeStatistics(eligibleTeamList.get(i), weakestPlayerList,
                        tradeTeam.getFirst(), tradeTeam.getSecond());
                resolveTrade(eligibleTeamList.get(i), weakestPlayerList,
                        tradeTeam.getFirst(), tradeTeam.getSecond());
                eligibleTeamList.remove(0);
                teamLength = eligibleTeamList.size();
            }
//            }
        }
    }

    @Override
    public List<Team> determineEligibleTeams()
    {
        int lossPointCutOff = tradingConfig.getLossPoint();
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
                            team.getTeamName().equalsIgnoreCase("New York"))
                    {
                        team.setLossPoint(2);
                    }
//                    System.out.println(team.getTeamName() + "'s LossPoint is " + team.getLossPoint());
                    if(team.getLossPoint() == lossPointCutOff)
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
            List<Player> forwardPlayerList= playerList.stream().filter(player ->
                    player.getPosition().equalsIgnoreCase(Position.FORWARD.toString())).collect(Collectors.toList());
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
            List<Player> defencePlayerList= playerList.stream().filter(player ->
                    player.getPosition().equalsIgnoreCase(Position.DEFENSE.toString())).collect(Collectors.toList());
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
            List<Player> goaliePlayerList= playerList.stream().filter(player ->
                    player.getPosition().equalsIgnoreCase(Position.GOALIE.toString())).collect(Collectors.toList());
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

    public List<Integer> findPlayerPositions(List<Player> weakestPlayerList){
        List<Integer> playerPositionFlag = new ArrayList<Integer>(Arrays.asList(0,0,0));
        for(int j=0; j<weakestPlayerList.size(); j++)
        {
            String position = weakestPlayerList.get(j).getPosition();
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
    public Triplet<Team, List<Player>, Float> offerTrade(Team team, List<Triplet<Team, List<Player>, Float>> tradingTeamsBuffer)
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
        System.out.println("Successfully generated the Trade with team " + tradeTeam.getFirst().getTeamName());
        return tradeTeam;
    }

    @Override
    public void displayTradeStatistics(Team team1, List<Player> playerList1, Team team2, List<Player> playerList2)
    {
        System.out.println("\n****** Trade Statistics ******");
        System.out.println("\nTeam " + team1.getTeamName() + " is offering the trade to " + team2.getTeamName());
        System.out.println("---- Team " + team1.getTeamName() + "'s Players Offered ----");
        displayPlayers(playerList1);
        System.out.println("---- Team " + team2.getTeamName() + "'s Players Requested ----");
        displayPlayers(playerList2);
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

    @Override
    public void resolveTrade(Team team1, List<Player> playerList1, Team team2, List<Player> playerList2) {
        Float playerStrength1 = strengthSum(playerList1);
        Float playerStrength2 = strengthSum(playerList2);
        if(this.generateRandomNumber() < tradingConfig.getRandomAcceptanceChance())
        {
            acceptTrade(team1, playerList1, team2, playerList2);
        }
        else if(playerStrength1 > playerStrength2)
        {
            acceptTrade(team1, playerList1, team2, playerList2);
        }
        else
        {
            rejectTrade(team1);
        }
        settleTrade();
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
        resetLossPoints(team);
    }

    @Override
    public void acceptTrade(Team team1, List<Player> playerList1, Team team2, List<Player> playerList2)
    {
        List<Conference> conferenceList = leagueObj.getConferences();
        for (Conference conference : conferenceList)
        {
            List<Division> divisionList = conference.getDivisions();
            for (Division division : divisionList)
            {
                List<Team> teamList = division.getTeams();
                for (Team team : teamList)
                {
                    if(team.getTeamName().equals(team1.getTeamName()))
                    {
                        for(Player toBeRemoved :playerList1)
                        {
                            team.getPlayers().removeIf(player ->
                                    player.getPlayerName().equals(toBeRemoved.getPlayerName()));
                        }
                        for(Player toBeAdded :playerList2)
                        {
                            team.getPlayers().add(toBeAdded);
                        }
                        resetLossPoints(team);
                        //TODO update this team to league object
                    }
                    else if(team.getTeamName().equals(team2.getTeamName()))
                    {
                        for(Player toBeRemoved :playerList2)
                        {
                            team.getPlayers().removeIf(player ->
                                    player.getPlayerName().equals(toBeRemoved.getPlayerName()));
                        }
                        for(Player toBeAdded :playerList1)
                        {
                            team.getPlayers().add(toBeAdded);
                        }
                        resetLossPoints(team);
                        //TODO update this team to league object
                    }

                }
            }
        }

    }

    @Override
    public void resetLossPoints(Team team) {
        team.setLossPoint(0);
    }

    @Override
    public void checkTeamConstrains()
    {

    }

    @Override
    public void dropPlayer()
    {

    }

    @Override
    public void hirePlayer()
    {

    }

    @Override
    public void settleTrade()
    {

    }
}
