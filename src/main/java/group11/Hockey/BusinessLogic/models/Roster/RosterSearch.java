package group11.Hockey.BusinessLogic.models.Roster;

import group11.Hockey.BusinessLogic.Positions;
import group11.Hockey.BusinessLogic.Triplet;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import group11.Hockey.BusinessLogic.models.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RosterSearch implements IRosterSearch{

    public List<Player> findWeakestPlayers(List<Player> unSortedPlayerList, int maxPlayersPerTrade) {
        List<Player> playerList = sortPlayersByStrength(unSortedPlayerList);
        List<Player> weakestPlayerList = new ArrayList<>();
        for(int i=0; i < maxPlayersPerTrade; i++) {
            weakestPlayerList.add(playerList.get(i));
        }
        return weakestPlayerList;
    }

    public List<Integer> findPlayerPositions(List<Player> playerList){
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

    public List<Player> findStrongestPlayers(List<Player> unSortedPlayerList, List<Integer> playerPositionFlag,
                                              int maxPlayersPerTrade) {
        List<Player> playerList = sortPlayersByStrength(unSortedPlayerList);
        List<Player> strongestPlayerList = new ArrayList<>();

        int noOfForwardNeeded = playerPositionFlag.get(Positions.FORWARD.ordinal());
        int noOfDefenseNeeded = playerPositionFlag.get(Positions.DEFENSE.ordinal());
        int noOfGoalieNeeded = playerPositionFlag.get(Positions.GOALIE.ordinal());

        if(noOfForwardNeeded > 0) {
            int maxForwardPerTrade = 0;
            List<Player> forwardPlayerList= getForwardList(playerList);
            for(int i=forwardPlayerList.size()-1;
                maxForwardPerTrade < noOfForwardNeeded;
                i--,maxForwardPerTrade++) {
                strongestPlayerList.add(forwardPlayerList.get(i));
            }
        }

        if(noOfDefenseNeeded > 0) {
            int maxDefensePerTrade = 0;
            List<Player> defencePlayerList= getDefenseList(playerList);
            for(int i=defencePlayerList.size()-1;
                maxDefensePerTrade < noOfDefenseNeeded;
                i--,maxDefensePerTrade++) {
                strongestPlayerList.add(defencePlayerList.get(i));
            }
        }

        if(noOfGoalieNeeded > 0) {
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
        // TODO logInfo
//        display.showMessageOnConsole("Successfully found strongest trade team " + tradeTeam.getFirst().getTeamName());
        return tradeTeam;
    }

    public Float playersStrengthSum(List<Player> playerList) {
        Float playersStrengthSum = 0.0f;
        for(int l=0; l<playerList.size(); l++) {
            playersStrengthSum += playerList.get(l).getPlayerStrength();
        }
        return playersStrengthSum;
    }

    public List<Player> sortPlayersByStrength(List<Player> unSortedPlayerList) {
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

    public List<Player> getDefenseList(List<Player> playerList) {
        List<Player> defenceIPlayerList= playerList.stream().filter(player ->
                player.getPosition().equalsIgnoreCase(Positions.DEFENSE.toString())).collect(Collectors.toList());
        return sortPlayersByStrength(defenceIPlayerList);
    }

    public List<Player> getForwardList(List<Player> playerList) {
        List<Player> forwardIPlayerList= playerList.stream().filter(player ->
                player.getPosition().equalsIgnoreCase(Positions.FORWARD.toString())).collect(Collectors.toList());
        return sortPlayersByStrength(forwardIPlayerList);
    }

    public List<Player> getGoalieList(List<Player> playerList) {
        List<Player> goalieIPlayerList= playerList.stream().filter(player ->
                player.getPosition().equalsIgnoreCase(Positions.GOALIE.toString())).collect(Collectors.toList());
        return sortPlayersByStrength(goalieIPlayerList);
    }
}
