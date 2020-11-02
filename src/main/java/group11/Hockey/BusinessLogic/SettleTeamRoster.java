package group11.Hockey.BusinessLogic;

import group11.Hockey.InputOutput.*;
import group11.Hockey.BusinessLogic.models.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SettleTeamRoster extends CommonUtilForLeague {
    private League leagueObj;
    private AITrading aiTradingObj;
    IDisplay display = new Display();
    IUserInputValidation userSelection = new UserInputValidation();
    private static final int TEAM_SIZE = 20;
    private static final int SKATERS_SIZE = 18;
    private static final int GOALIE_SIZE = 2;

    public SettleTeamRoster(League leagueObj)
    {
        this.leagueObj = leagueObj;
        aiTradingObj = new AITrading(leagueObj);
    }

    public void settleTeam(Team team) throws Exception
    {
        int noOfPlayers = team.getPlayers().size();
        List<Player> playerList = team.getPlayers();
        List<Integer> playerPositionFlag = aiTradingObj.getPlayerMiscellaneous().findPlayerPositions(playerList);
        int noOfForward = playerPositionFlag.get(Positions.FORWARD.ordinal());
        int noOfDefense = playerPositionFlag.get(Positions.DEFENSE.ordinal());
        int noOfGoalies = playerPositionFlag.get(Positions.GOALIE.ordinal());
        int noOfSkaters = noOfForward + noOfDefense;
        if(noOfPlayers > TEAM_SIZE)
        {
            if(noOfGoalies > GOALIE_SIZE)
            {
                int noOfGoaliesToBeDropped = noOfGoalies - GOALIE_SIZE;
                for(int i=0; i<noOfGoaliesToBeDropped; i++)
                {
                    if(team.isUserTeam() == true)
                    {
                        dropPlayerUser(leagueObj, playerList, Positions.GOALIE);
                    }
                    else
                    {
                        dropPlayer(leagueObj, playerList, Positions.GOALIE);
                    }
                }
            }
            if(noOfSkaters > SKATERS_SIZE)
            {
                int noOfSkatersToBeDropped =  noOfSkaters - SKATERS_SIZE;
                for(int i=0; i<noOfSkatersToBeDropped; i++)
                {
                    if(team.isUserTeam() == true)
                    {
                        dropPlayerUser(leagueObj, playerList, Positions.SKATER);
                    }
                    else
                    {
                        dropPlayer(leagueObj, playerList, Positions.SKATER);
                    }
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
                    if(team.isUserTeam() == true)
                    {
                        hirePlayerUser(leagueObj, playerList, Positions.GOALIE);
                    }
                    else
                    {
                        hirePlayer(leagueObj, playerList, Positions.GOALIE);
                    }
                }
            }
            if(noOfSkaters < SKATERS_SIZE)
            {
                int noOfSkatersToBeHired = SKATERS_SIZE - noOfSkaters;
                for(int i=0; i<noOfSkatersToBeHired; i++)
                {
                    if(team.isUserTeam() == true)
                    {
                        hirePlayerUser(leagueObj, playerList, Positions.SKATER);
                    }
                    else
                    {
                        hirePlayer(leagueObj, playerList, Positions.SKATER);
                    }
                }
            }
        }
    }

    public void hirePlayer(League league, List<Player> playerList, Positions playerPosition) throws Exception
    {
        Team team = new Team();
        List<Player> freeAgents = league.getFreeAgents();
        List<Player> sortedFreeAgents = aiTradingObj.getPlayerMiscellaneous().sortPlayersByStrength(freeAgents);
        Collections.reverse(sortedFreeAgents);
        Iterator<Player> freeAgentsItr = sortedFreeAgents.iterator();
        boolean playerHired = false;

        while (freeAgentsItr.hasNext()) {
            Player freeAgent = freeAgentsItr.next();
            if(playerPosition.equals(Positions.GOALIE))
            {
                if (freeAgent.getPosition().equalsIgnoreCase(playerPosition.toString())) {
                    freeAgent.setIsFreeAgent(false);
                    playerList.add(freeAgent);
                    freeAgentsItr.remove();
                    playerHired = true;
                    break;
                }
            }
            else if(playerPosition.equals(Positions.SKATER))
            {
                if (freeAgent.getPosition().equalsIgnoreCase(Positions.FORWARD.toString()) ||
                        freeAgent.getPosition().equalsIgnoreCase(Positions.DEFENSE.toString())) {
                    freeAgent.setIsFreeAgent(false);
                    playerList.add(freeAgent);
                    freeAgentsItr.remove();
                    playerHired = true;
                    break;
                }
            }
        }
        if(playerPosition.equals(Positions.GOALIE) && playerHired == false)
        {
            throw new Exception("Goalie is not available in Free Agents to form a Team.");
//            display.showMessageOnConsole("Goalie is not available in Free Agents to form a Team.");
        }
        if(playerPosition.equals(Positions.SKATER) && playerHired == false)
        {
            throw new Exception("Skater is not available in Free Agents to form a Team.");
//            display.showMessageOnConsole("Skater is not available in Free Agents to form a Team.");
        }
    }

    public void dropPlayer(League league, List<Player> playerList, Positions playerPosition)
    {
        List<Player> freeAgents = league.getFreeAgents();
        List<Player> sortedFreeAgents = aiTradingObj.getPlayerMiscellaneous().sortPlayersByStrength(playerList);
        Iterator<Player> playersItr = sortedFreeAgents.iterator();

        while (playersItr.hasNext()) {
            Player player = playersItr.next();
            if(playerPosition.equals(Positions.GOALIE))
            {
                if (player.getPosition().equalsIgnoreCase(playerPosition.toString())) {
                    player.setIsFreeAgent(true);
                    player.setCaptain(false);
                    freeAgents.add(player);
                    playersItr.remove();
                    break;
                }
            }
            else if(playerPosition.equals(Positions.SKATER))
            {
                if (player.getPosition().equalsIgnoreCase(Positions.FORWARD.toString()) ||
                        player.getPosition().equalsIgnoreCase(Positions.DEFENSE.toString())) {
                    player.setIsFreeAgent(true);
                    player.setCaptain(false);
                    freeAgents.add(player);
                    playersItr.remove();
                    break;
                }
            }
        }
    }

    public void hirePlayerUser(League league, List<Player> playerList, Positions playerPosition) throws Exception
    {
        List<Player> freeAgents = league.getFreeAgents();
        Iterator<Player> freeAgentsItr = freeAgents.iterator();
        boolean playerHired = false;

        if(playerPosition.equals(Positions.GOALIE))
        {
            List<Player> goalieFreeAgents = aiTradingObj.getPlayerMiscellaneous().getGoalieList(freeAgents);
            display.displayListOfFreeAgents(goalieFreeAgents);

            int userInput = userSelection.userIterativeIntegerSelection(goalieFreeAgents.size());
            String goalieName =  goalieFreeAgents.get(userInput-1).getPlayerName();
            while (freeAgentsItr.hasNext()) {
                Player freeAgent = freeAgentsItr.next();
                if ((freeAgent.getPlayerName().equalsIgnoreCase(goalieName)) &&
                        (freeAgent.getPosition().equalsIgnoreCase(Positions.GOALIE.toString()))) {
                    freeAgent.setIsFreeAgent(false);
                    playerList.add(freeAgent);
                    freeAgentsItr.remove();
                    playerHired = true;
                    break;
                }
            }
        }
        else if(playerPosition.equals(Positions.SKATER))
        {
            List<Player> forwardFreeAgents = aiTradingObj.getPlayerMiscellaneous().getForwardList(freeAgents);
            List<Player> defenseFreeAgents = aiTradingObj.getPlayerMiscellaneous().getDefenseList(freeAgents);
            List<Player> skaterFreeAgents = new ArrayList<Player>(forwardFreeAgents);
            skaterFreeAgents.addAll(defenseFreeAgents);
            display.displayListOfFreeAgents(skaterFreeAgents);

            int userInput = userSelection.userIterativeIntegerSelection(skaterFreeAgents.size());

            String skaterName =  skaterFreeAgents.get(userInput-1).getPlayerName();
            while (freeAgentsItr.hasNext()) {
                Player freeAgent = freeAgentsItr.next();
                if ((freeAgent.getPlayerName().equalsIgnoreCase(skaterName)) &&
                        (freeAgent.getPosition().equalsIgnoreCase(Positions.FORWARD.toString()) ||
                                freeAgent.getPosition().equalsIgnoreCase(Positions.DEFENSE.toString()))) {
                    freeAgent.setIsFreeAgent(false);
                    playerList.add(freeAgent);
                    freeAgentsItr.remove();
                    playerHired = true;
                    break;
                }
            }
        }
        if(playerPosition.equals(Positions.GOALIE) && playerHired == false)
        {
            throw new Exception("Goalie is not available in Free Agents to form a Team.");
//            display.showMessageOnConsole("Goalie is not available in Free Agents to form a Team.");
        }
        if(playerPosition.equals(Positions.SKATER) && playerHired == false)
        {
            throw new Exception("Skater is not available in Free Agents to form a Team.");
//            display.showMessageOnConsole("Skater is not available in Free Agents to form a Team.");
        }
    }

    public void dropPlayerUser(League league, List<Player> playerList, Positions playerPosition)
    {
        List<Player> freeAgents = league.getFreeAgents();
        Iterator<Player> playersItr = playerList.iterator();

        display.showMessageOnConsole("\n**Please select the player to drop**");
        if(playerPosition.equals(Positions.GOALIE))
        {
            List<Player> goalieFreeAgents = aiTradingObj.getPlayerMiscellaneous().getGoalieList(freeAgents);
            display.displayListOfFreeAgents(goalieFreeAgents);

            int userInput = userSelection.userIterativeIntegerSelection(goalieFreeAgents.size());

            String goalieName =  goalieFreeAgents.get(userInput-1).getPlayerName();
            while (playersItr.hasNext()) {
                Player player = playersItr.next();
                if ((player.getPlayerName().equalsIgnoreCase(goalieName)) &&
                        (player.getPosition().equalsIgnoreCase(Positions.GOALIE.toString()))) {
                    player.setIsFreeAgent(true);
                    player.setCaptain(false);
                    freeAgents.add(player);
                    playersItr.remove();
                    break;
                }
            }
        }
        else if(playerPosition.equals(Positions.SKATER))
        {
            List<Player> forwardFreeAgents = aiTradingObj.getPlayerMiscellaneous().getForwardList(freeAgents);
            List<Player> defenseFreeAgents = aiTradingObj.getPlayerMiscellaneous().getDefenseList(freeAgents);
            List<Player> skaterFreeAgents = new ArrayList<Player>(forwardFreeAgents);
            skaterFreeAgents.addAll(defenseFreeAgents);
            display.displayListOfFreeAgents(skaterFreeAgents);

            int userInput = userSelection.userIterativeIntegerSelection(skaterFreeAgents.size());

            String skaterName =  skaterFreeAgents.get(userInput-1).getPlayerName();
            while (playersItr.hasNext()) {
                Player player = playersItr.next();
                if ((player.getPlayerName().equalsIgnoreCase(skaterName)) &&
                        (player.getPosition().equalsIgnoreCase(Positions.FORWARD.toString()) ||
                                player.getPosition().equalsIgnoreCase(Positions.DEFENSE.toString()))) {
                    player.setIsFreeAgent(true);
                    player.setCaptain(false);
                    freeAgents.add(player);
                    playersItr.remove();
                    break;
                }
            }
        }
    }
}