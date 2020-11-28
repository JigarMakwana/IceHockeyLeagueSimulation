package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.*;
import group11.Hockey.BusinessLogic.Trading.Interfaces.IPlayerTradeOperations;
import group11.Hockey.BusinessLogic.models.*;
import group11.Hockey.InputOutput.IDisplay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * This class contain trade-helper operations done on players
 * @author  Jigar Makwana B00842568
 */

public class PlayerTradeOperations implements IPlayerTradeOperations {
    private ITrading tradingConfig;
    private static Logger logger = LogManager.getLogger(PlayerTradeOperations.class);
    IDisplay display = DefaultHockeyFactory.makeDisplay();
    public PlayerTradeOperations(ITrading tradingConfig)
    {
        this.tradingConfig = tradingConfig;
    }

    public List<Player> findWeakestPlayers(Team team) {
    	logger.info("Entered findWeakestPlayers()");
        int maxPlayers = tradingConfig.getMaxPlayersPerTrade();
        List<Player> unSortedPlayerList = team.getPlayers();
        List<Player> playerList = sortPlayersByStrength(unSortedPlayerList);
        List<Player> weakestPlayerList = new ArrayList<Player>();
        for(int i=0; i < maxPlayers; i++) {
            weakestPlayerList.add(playerList.get(i));
        }
        return weakestPlayerList;
    }

    public List<Integer> findPlayerPositions(List<Player> playerList){
    	logger.info("Entered findPlayerPositions()");
        List<Integer> playerPositionFlag = new ArrayList<Integer>(Arrays.asList(0,0,0));
        for(int j=0; j<playerList.size(); j++) {
            String position = playerList.get(j).getPosition();
            if(Positions.FORWARD.toString().equalsIgnoreCase(position)) {
            	logger.info("Position of player "+playerList.get(j).getPlayerName()+" is forward");
                int index = Positions.FORWARD.ordinal();
                int value = playerPositionFlag.get(index);
                value = value + 1;
                playerPositionFlag.set(index,value);
            }
            else if(Positions.DEFENSE.toString().equalsIgnoreCase(position)) {
            	logger.info("Position of player "+playerList.get(j).getPlayerName()+" is Defense");
                int index = Positions.DEFENSE.ordinal();
                int value = playerPositionFlag.get(index);
                value = value + 1;
                playerPositionFlag.set(index,value);
            }
            else if(Positions.GOALIE.toString().equalsIgnoreCase(position)) {
            	logger.info("Position of player "+playerList.get(j).getPlayerName()+" is Goalie");
                int index = Positions.GOALIE.ordinal();
                int value = playerPositionFlag.get(index);
                value = value + 1;
                playerPositionFlag.set(index,value);
            }
        }
        return playerPositionFlag;
    }

    public List<Player> findStrongestPlayers(Team team, List<Integer> playerPositionFlag) {
    	logger.info("Entered findStrongestPlayers()");
        int maxPlayers = tradingConfig.getMaxPlayersPerTrade();
        List<Player> unSortedPlayerList = team.getPlayers();
        List<Player> playerList = sortPlayersByStrength(unSortedPlayerList);
        List<Player> strongestPlayerList = new ArrayList<Player>();

        int noOfForwardNeeded = playerPositionFlag.get(Positions.FORWARD.ordinal());
        int noOfDefenseNeeded = playerPositionFlag.get(Positions.DEFENSE.ordinal());
        int noOfGoalieNeeded = playerPositionFlag.get(Positions.GOALIE.ordinal());

        if(noOfForwardNeeded > 0) {
        	logger.info("Finding strongest forward players");
            int maxForwardPerTrade = 0;
            List<Player> forwardPlayerList= getForwardList(playerList);
            for(int i=forwardPlayerList.size()-1;
                maxForwardPerTrade < noOfForwardNeeded;
                i--,maxForwardPerTrade++) {
                strongestPlayerList.add(forwardPlayerList.get(i));
            }
        }

        if(noOfDefenseNeeded > 0) {
        	logger.info("Finding strongest defense players");
            int maxDefensePerTrade = 0;
            List<Player> defencePlayerList= getDefenseList(playerList);
            for(int i=defencePlayerList.size()-1;
                maxDefensePerTrade < noOfDefenseNeeded;
                i--,maxDefensePerTrade++) {
                strongestPlayerList.add(defencePlayerList.get(i));
            }
        }

        if(noOfGoalieNeeded > 0) {
        	logger.info("Finding strongest goalie players");
            int maxGoaliePerTrade = 0;
            List<Player> goaliePlayerList= getGoalieList(playerList);
            for(int i=goaliePlayerList.size()-1;
                maxGoaliePerTrade < noOfGoalieNeeded;
                i--,maxGoaliePerTrade++) {
                strongestPlayerList.add(goaliePlayerList.get(i));
            }
        }
        return strongestPlayerList;
    }

    public Triplet<Team, List<Player>, Float> findStrongestTradeTeam(
            List<Triplet<Team, List<Player>, Float>> tradingTeamsBuffer) {
    	logger.info("Entered findStrongestTradeTeam()");
        List<Triplet<Team, List<Player>, Float>> sortedBuffer = tradingTeamsBuffer;
        int i, j;
        Triplet temp;
        boolean swapped;
        int length = sortedBuffer.size();
        for (i = 0; i < length - 1; i++) {
            swapped = false;
            for (j = 0; j < length - i - 1; j++) {
                if (sortedBuffer.get(j).getThird() >
                        sortedBuffer.get(j + 1).getThird()) {
                    temp = sortedBuffer.get(j);
                    sortedBuffer.set(j, sortedBuffer.get(j + 1));
                    sortedBuffer.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (swapped == false) {
                break;
            }
        }
        Triplet<Team, List<Player>, Float> tradeTeam = sortedBuffer.get(length-1);
//        display.showMessageOnConsole("Successfully found strongest trade team " + tradeTeam.getFirst().getTeamName());
        return tradeTeam;
    }

    public Float playersStrengthSum(List<Player> playerList) {
    	logger.info("Entered playersStrengthSum()");
        Float playersStrengthSum = 0.0f;
        for(int l=0; l<playerList.size(); l++) {
            playersStrengthSum += playerList.get(l).getPlayerStrength();
        }
        return playersStrengthSum;
    }

    public List<Player> getForwardList(List<Player> playerList) {
    	logger.info("Entered getForwardList()");
        List<Player> forwardPlayerList= playerList.stream().filter(player ->
                player.getPosition().equalsIgnoreCase(Positions.FORWARD.toString())).collect(Collectors.toList());
        return forwardPlayerList;
    }

    public List<Player> getDefenseList(List<Player> playerList) {
    	logger.info("Entered getDefenseList()");
        List<Player> defencePlayerList= playerList.stream().filter(player ->
                player.getPosition().equalsIgnoreCase(Positions.DEFENSE.toString())).collect(Collectors.toList());
        return defencePlayerList;
    }

    public List<Player> getGoalieList(List<Player> playerList) {
    	logger.info("Entered getGoalieList()");
        List<Player> goaliePlayerList= playerList.stream().filter(player ->
                player.getPosition().equalsIgnoreCase(Positions.GOALIE.toString())).collect(Collectors.toList());
        return goaliePlayerList;
    }

    public List<Player>  sortPlayersByStrength(List<Player> unSortedPlayerList) {
    	logger.info("Entered sortPlayersByStrength()");
        List<Player> sortedPlayerList = unSortedPlayerList;
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
            if (swapped == false) {
                break;
            }
        }
        return sortedPlayerList;
    }
}
