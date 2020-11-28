package group11.Hockey.BusinessLogic.Trading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.Positions;
import group11.Hockey.BusinessLogic.Triplet;
import group11.Hockey.BusinessLogic.Trading.Interfaces.IPlayerTradeOperations;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.ITrading;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.IDisplay;

/**
 * This class contain trade-helper operations done on players
 * @author  Jigar Makwana B00842568
 */

public class PlayerTradeOperations implements IPlayerTradeOperations {
    private ITrading tradingConfig;
    IDisplay display = DefaultHockeyFactory.makeDisplay();
    public PlayerTradeOperations(ITrading tradingConfig)
    {
        this.tradingConfig = tradingConfig;
    }

    public List<IPlayer> findWeakestPlayers(ITeam team) {
        int maxPlayers = tradingConfig.getMaxPlayersPerTrade();
        List<IPlayer> unSortedPlayerList = team.getPlayers();
        List<IPlayer> playerList = sortPlayersByStrength(unSortedPlayerList);
        List<IPlayer> weakestPlayerList = new ArrayList<>();
        for(int i=0; i < maxPlayers; i++) {
            weakestPlayerList.add(playerList.get(i));
        }
        return weakestPlayerList;
    }

    public List<Integer> findPlayerPositions(List<IPlayer> playerList){
        List<Integer> playerPositionFlag = new ArrayList<Integer>(Arrays.asList(0,0,0));
        for(int j=0; j<playerList.size(); j++) {
            String position = playerList.get(j).getPosition();
            if(Positions.FORWARD.toString().equalsIgnoreCase(position)) {
                int index = Positions.FORWARD.ordinal();
                int value = playerPositionFlag.get(index);
                value = value + 1;
                playerPositionFlag.set(index,value);
            }
            else if(Positions.DEFENSE.toString().equalsIgnoreCase(position)) {
                int index = Positions.DEFENSE.ordinal();
                int value = playerPositionFlag.get(index);
                value = value + 1;
                playerPositionFlag.set(index,value);
            }
            else if(Positions.GOALIE.toString().equalsIgnoreCase(position)) {
                int index = Positions.GOALIE.ordinal();
                int value = playerPositionFlag.get(index);
                value = value + 1;
                playerPositionFlag.set(index,value);
            }
        }
        return playerPositionFlag;
    }

    public List<IPlayer> findStrongestPlayers(ITeam team, List<Integer> playerPositionFlag) {
        int maxPlayers = tradingConfig.getMaxPlayersPerTrade();
        List<IPlayer> unSortedPlayerList = team.getPlayers();
        List<IPlayer> playerList = sortPlayersByStrength(unSortedPlayerList);
        List<IPlayer> strongestPlayerList = new ArrayList<>();

        int noOfForwardNeeded = playerPositionFlag.get(Positions.FORWARD.ordinal());
        int noOfDefenseNeeded = playerPositionFlag.get(Positions.DEFENSE.ordinal());
        int noOfGoalieNeeded = playerPositionFlag.get(Positions.GOALIE.ordinal());

        if(noOfForwardNeeded > 0) {
            int maxForwardPerTrade = 0;
            List<IPlayer> forwardPlayerList= getForwardList(playerList);
            for(int i=forwardPlayerList.size()-1;
                maxForwardPerTrade < noOfForwardNeeded;
                i--,maxForwardPerTrade++) {
                strongestPlayerList.add(forwardPlayerList.get(i));
            }
        }

        if(noOfDefenseNeeded > 0) {
            int maxDefensePerTrade = 0;
            List<IPlayer> defencePlayerList= getDefenseList(playerList);
            for(int i=defencePlayerList.size()-1;
                maxDefensePerTrade < noOfDefenseNeeded;
                i--,maxDefensePerTrade++) {
                strongestPlayerList.add(defencePlayerList.get(i));
            }
        }

        if(noOfGoalieNeeded > 0) {
            int maxGoaliePerTrade = 0;
            List<IPlayer> goaliePlayerList= getGoalieList(playerList);
            for(int i=goaliePlayerList.size()-1;
                maxGoaliePerTrade < noOfGoalieNeeded;
                i--,maxGoaliePerTrade++) {
                strongestPlayerList.add(goaliePlayerList.get(i));
            }
        }
        return strongestPlayerList;
    }

    public Triplet<ITeam, List<IPlayer>, Float> findStrongestTradeTeam(
            List<Triplet<ITeam, List<IPlayer>, Float>> tradingTeamsBuffer) {
        List<Triplet<ITeam, List<IPlayer>, Float>> sortedBuffer = tradingTeamsBuffer;
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
        Triplet<ITeam, List<IPlayer>, Float> tradeTeam = sortedBuffer.get(length-1);
//        display.showMessageOnConsole("Successfully found strongest trade team " + tradeTeam.getFirst().getTeamName());
        return tradeTeam;
    }

    public Float playersStrengthSum(List<IPlayer> playerList) {
        Float playersStrengthSum = 0.0f;
        for(int l=0; l<playerList.size(); l++) {
            playersStrengthSum += playerList.get(l).getPlayerStrength();
        }
        return playersStrengthSum;
    }

    public List<IPlayer> getForwardList(List<IPlayer> playerList) {
        List<IPlayer> forwardPlayerList= playerList.stream().filter(player ->
                player.getPosition().equalsIgnoreCase(Positions.FORWARD.toString())).collect(Collectors.toList());
        return forwardPlayerList;
    }

    public List<IPlayer> getDefenseList(List<IPlayer> playerList) {
        List<IPlayer> defencePlayerList= playerList.stream().filter(player ->
                player.getPosition().equalsIgnoreCase(Positions.DEFENSE.toString())).collect(Collectors.toList());
        return defencePlayerList;
    }

    public List<IPlayer> getGoalieList(List<IPlayer> playerList) {
        List<IPlayer> goaliePlayerList= playerList.stream().filter(player ->
                player.getPosition().equalsIgnoreCase(Positions.GOALIE.toString())).collect(Collectors.toList());
        return goaliePlayerList;
    }

    public List<IPlayer>  sortPlayersByStrength(List<IPlayer> unSortedPlayerList) {
        List<IPlayer> sortedPlayerList = unSortedPlayerList;
        int i, j;
        IPlayer temp;
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
