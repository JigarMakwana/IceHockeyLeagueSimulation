/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.models.Roster;

import group11.Hockey.BusinessLogic.Enums.Positions;
import group11.Hockey.BusinessLogic.Trading.TradingTriplet.Triplet;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import group11.Hockey.BusinessLogic.models.Team;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RosterSearch implements IRosterSearch{
    private static Logger logger = LogManager.getLogger(RosterSearch.class);

    public List<IPlayer> findWeakestPlayers(List<IPlayer> unSortedPlayerList, int maxPlayersPerTrade) {
        logger.debug("Entered findWeakestPlayers()");
        List<IPlayer> playerList = sortPlayersByStrength(unSortedPlayerList);
        List<IPlayer> weakestPlayerList = new ArrayList<>();
        for(int i=0; i < maxPlayersPerTrade; i++) {
            weakestPlayerList.add(playerList.get(i));
        }
        return weakestPlayerList;
    }

    public List<Integer> findPlayerPositions(List<IPlayer> playerList){
        logger.debug("Entered findPlayerPositions()");
        List<Integer> playerPositionFlag = new ArrayList<>(Arrays.asList(0,0,0));
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

    public List<IPlayer> findStrongestPlayers(List<IPlayer> unSortedPlayerList, List<Integer> playerPositionFlag,
                                              int maxPlayersPerTrade) {
        logger.debug("Entered findStrongestPlayers()");
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

    public IPlayer findStrongestPlayerByPosition(List<IPlayer> unSortedPlayerList, Positions positions) {
        logger.debug("Entered findStrongestPlayerByPosition()");
        List<IPlayer> playerList = sortPlayersByStrength(unSortedPlayerList);
        for(IPlayer p: playerList){
            if(p.getPosition().equalsIgnoreCase(positions.toString())){
                logger.debug("Found strongest player " + p.getPlayerName());
                return p;
            }
        }
        return null;
    }

    public IPlayer findWeakestPlayerByPosition(List<IPlayer> unSortedPlayerList, Positions positions) {
        logger.debug("Entered findWeakestPlayerByPosition()");
        List<IPlayer> playerList = sortPlayersByStrength(unSortedPlayerList);
        Collections.reverse(playerList);
        for(IPlayer p: playerList){
            if(p.getPosition().equalsIgnoreCase(positions.toString())){
                logger.debug("Found weakest player " + p.getPlayerName());
                return p;
            }
        }
        return null;
    }

    public Triplet<ITeam, List<IPlayer>, Float> findStrongestTradeTeam(
            List<Triplet<ITeam, List<IPlayer>, Float>> tradingTeamsBuffer) {
        logger.debug("Entered findStrongestTradeTeam()");
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
        logger.debug("Successfully found strongest trade team " + tradeTeam.getFirst().getTeamName());
        return tradeTeam;
    }

    public List<IPlayer> sortPlayersByStrength(List<IPlayer> unSortedPlayerList) {
        logger.debug("Entered sortPlayersByStrength()");
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

    public float getRosterStrength(List<IPlayer> playerList){
        logger.debug("Entered getRosterStrength()");
        float teamStrength = 0;
        if (playerList == null || playerList.size() == 0) {
            return 0;
        }
        for (IPlayer player : playerList) {
            teamStrength += player.getPlayerStrength();
        }
        return teamStrength;
    }

    public List<IPlayer> getDefenseList(List<IPlayer> playerList) {
        logger.debug("Entered getDefenseList()");
        List<IPlayer> defenceIPlayerList= playerList.stream().filter(player ->
                player.getPosition().equalsIgnoreCase(Positions.DEFENSE.toString())).collect(Collectors.toList());
        return sortPlayersByStrength(defenceIPlayerList);
    }

    public List<IPlayer> getForwardList(List<IPlayer> playerList) {
        logger.debug("Entered getForwardList()");
        List<IPlayer> forwardIPlayerList= playerList.stream().filter(player ->
                player.getPosition().equalsIgnoreCase(Positions.FORWARD.toString())).collect(Collectors.toList());
        return sortPlayersByStrength(forwardIPlayerList);
    }

    public List<IPlayer> getGoalieList(List<IPlayer> playerList) {
        logger.debug("Entered getGoalieList()");
        List<IPlayer> goalieIPlayerList= playerList.stream().filter(player ->
                player.getPosition().equalsIgnoreCase(Positions.GOALIE.toString())).collect(Collectors.toList());
        return sortPlayersByStrength(goalieIPlayerList);
    }

    public float averageTeamStrength(List<ITeam> eligibleTeamList){
        logger.debug("Entered averageTeamStrength()");
        float sum = 0.0f;
        for(int i=0 ; i<eligibleTeamList.size(); i++){
            sum += eligibleTeamList.get(i).getTeamStrength();
        }
        return sum/eligibleTeamList.size();
    }

    public ITeam findStrongestTeam(List<ITeam> eligibleTeamList){
        logger.debug("Entered findStrongestTeam()");
        List<Team> teams = new ArrayList<>();
        for(ITeam team: eligibleTeamList) {
            teams.add((Team)team);
        }
        Collections.sort(teams);
        return teams.get(0);
    }
}
