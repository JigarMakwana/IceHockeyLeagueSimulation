/*
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.*;
import group11.Hockey.BusinessLogic.Enums.RosterSize;
import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeSettler;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;

import java.util.Collections;
import java.util.List;

public class TradeSettler implements ITradeSettler {
    private Team team;
    private int teamSize;
    private int teamGoalieSize;
    private int teamForwardSize;
    private int teamDefenseSize;
    private List<Player> freeAgentList;
    private ICommandLineInput commandLineInput;
    private IValidations validation;
    private IDisplay display;
    private IRosterSearch rosterSearch;

    public TradeSettler(Team team, List<Player> freeAgentList, ICommandLineInput commandLineInput, IValidations validation, IDisplay display){
        this.team = team;
        this.freeAgentList = freeAgentList;
        this.commandLineInput = commandLineInput;
        this.validation = validation;
        this.display = display;
        this.rosterSearch = DefaultHockeyFactory.makeRosterSearch();
        initPlayers(team);
    }

    public void initPlayers(Team team){
        if (null == team){
            return;
        }
        this.teamSize = team.getRoster().getAllPlayerList().size();
        this.teamGoalieSize = team.getRoster().getGoalieList().size();
        this.teamForwardSize = team.getRoster().getForwardList().size();
        this.teamDefenseSize = team.getRoster().getDefenseList().size();
    }

    @Override
    public void settleTeam() {
        if(null == team || team.getRoster().isValidRoster()){
            return;
        } else {
            display.showMessageOnConsole("\nSettling Team " + team.getTeamName() + "'s size...");
            int constantTeamSize = RosterSize.ACTIVE_ROSTER_SIZE.getNumVal() + RosterSize.INACTIVE_ROSTER_SIZE.getNumVal();
            if(teamSize > constantTeamSize) {
                dropPlayers();
            } else if (teamSize < constantTeamSize){
                hirePlayers();
            }
        }
        display.showMessageOnConsole("Team " + team.getTeamName() + "'s size successfully settled!\n");
    }

    public void dropPlayers(){
        if(teamGoalieSize > RosterSize.GOALIE_SIZE.getNumVal()) {
            int noOfGoaliesToBeDropped = teamGoalieSize - RosterSize.GOALIE_SIZE.getNumVal();
            dropGoalie(noOfGoaliesToBeDropped);
        }
        if(teamForwardSize > RosterSize.FORWARD_SIZE.getNumVal()){
            int noOfForwardToBeDropped = teamForwardSize - RosterSize.FORWARD_SIZE.getNumVal();
            dropForward(noOfForwardToBeDropped);
        }
        if(teamDefenseSize > RosterSize.DEFENSE_SIZE.getNumVal()){
            int noOfDefenseToBeDropped = teamDefenseSize - RosterSize.DEFENSE_SIZE.getNumVal();
            dropDefense(noOfDefenseToBeDropped);
        }
    }

    private void dropGoalie(int noOfGoaliesToBeDropped){
        List<Player> goalieList = team.getRoster().getGoalieList();
        for(int i=0; i<noOfGoaliesToBeDropped; i++) {
            if(team.isUserTeam() == true) {
                dropPlayerFromUserTeam(goalieList);
            }
            else {
                dropPlayerFromAITeam(goalieList);
            }
            updateRosterSubLists();
        }
    }

    private void dropForward(int noOfForwardToBeDropped){
        List<Player> forwardList = team.getRoster().getForwardList();
        for(int i=0; i<noOfForwardToBeDropped; i++) {
            if(team.isUserTeam() == true) {
                dropPlayerFromUserTeam(forwardList);
            }
            else {
                dropPlayerFromAITeam(forwardList);
            }
            updateRosterSubLists();
        }
    }

    private void dropDefense(int noOfDefenseToBeDropped){
        List<Player> defenseList = team.getRoster().getDefenseList();
        for(int i=0; i<noOfDefenseToBeDropped; i++) {
            if(team.isUserTeam() == true) {
                dropPlayerFromUserTeam(defenseList);
            }
            else {
                dropPlayerFromAITeam(defenseList);
            }
            updateRosterSubLists();
        }
    }

    private void dropPlayerFromAITeam(List<Player> playerList) {
        if(null == playerList.get(0)){
            return;
        } else{
            Player playerToBeDropped = playerList.get(0);
            playerToBeDropped.setIsFreeAgent(true);
            playerToBeDropped.setCaptain(false);
            freeAgentList.add(playerToBeDropped);
            team.getRoster().getAllPlayerList().removeIf(player -> player.getPlayerName().equals(playerToBeDropped.getPlayerName()));
        }
    }

    private void dropPlayerFromUserTeam(List<Player> playerList) {
        display.pickPlayer(playerList);
        IUserInputCheck userInputCheck = DefaultHockeyFactory.makeUserInputCheck(commandLineInput, validation, display);
        int userInput = userInputCheck.userResolveRosterInput(playerList.size());

        Player p =  playerList.get(userInput-1);
        p.setIsFreeAgent(true);
        p.setCaptain(false);
        freeAgentList.add(p);
        team.getRoster().getAllPlayerList().removeIf(player -> player.getPlayerName().equals(p.getPlayerName()));
    }

    public void hirePlayers(){
        if(teamGoalieSize < RosterSize.GOALIE_SIZE.getNumVal()) {
            int noOfGoaliesToBeHired = RosterSize.GOALIE_SIZE.getNumVal() - teamGoalieSize;
            hireGoalie(noOfGoaliesToBeHired);
        }
        if(teamForwardSize < RosterSize.FORWARD_SIZE.getNumVal()){
            int noOfForwardToBeHired = RosterSize.FORWARD_SIZE.getNumVal() - teamForwardSize;
            hireForward(noOfForwardToBeHired);
        }
        if(teamDefenseSize < RosterSize.DEFENSE_SIZE.getNumVal()){
            int noOfDefenseToBeHired = RosterSize.DEFENSE_SIZE.getNumVal() - teamDefenseSize;
            hireDefense(noOfDefenseToBeHired);
        }
    }

    private void hireGoalie(int noOfGoaliesToBeHired){
        for(int i=0; i<noOfGoaliesToBeHired; i++) {
            List<Player> sortedFreeAgents = rosterSearch.getGoalieList(freeAgentList);
            try{
                if(team.isUserTeam() == true) {
                    hirePlayerInUserTeam(sortedFreeAgents);
                }
                else {
                    Collections.reverse(sortedFreeAgents);
                    hirePlayerInAITeam(sortedFreeAgents);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            updateRosterSubLists();
        }
    }

    private void hireForward(int noOfForwardToBeHired){
        for(int i=0; i<noOfForwardToBeHired; i++) {
            List<Player> sortedFreeAgents = rosterSearch.getForwardList(freeAgentList);
            try {
                if (team.isUserTeam() == true) {
                    hirePlayerInUserTeam(sortedFreeAgents);
                } else {
                    Collections.reverse(sortedFreeAgents);
                    hirePlayerInAITeam(sortedFreeAgents);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            updateRosterSubLists();
        }
    }

    private void hireDefense(int noOfDefenseToBeHired){
        for(int i=0; i<noOfDefenseToBeHired; i++) {
            List<Player> sortedFreeAgents = rosterSearch.getDefenseList(freeAgentList);
            try{
                if(team.isUserTeam() == true) {
                    hirePlayerInUserTeam(sortedFreeAgents);
                }
                else {
                    Collections.reverse(sortedFreeAgents);
                    hirePlayerInAITeam(sortedFreeAgents);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            updateRosterSubLists();
        }
    }

    private void hirePlayerInAITeam(List<Player> sortedFreeAgents) throws Exception {
        if(sortedFreeAgents.size() == 0){
            throw new Exception("There are No enough Free Agents available to settle a Team.");
        } else{
            Player playerToBeHired = sortedFreeAgents.get(0);
            playerToBeHired.setIsFreeAgent(false);
            team.getRoster().getAllPlayerList().add(playerToBeHired);
            freeAgentList.removeIf(player -> player.getPlayerName().equals(playerToBeHired.getPlayerName()));
        }
    }

    private void hirePlayerInUserTeam(List<Player> sortedFreeAgents) throws Exception {
        if(null == sortedFreeAgents){
            throw new Exception("Player is not available in Free Agents to form a Team.");
        } else {
            display.pickPlayer(sortedFreeAgents);
            IUserInputCheck userInputCheck = DefaultHockeyFactory.makeUserInputCheck(commandLineInput, validation, display);
            int userInput = userInputCheck.userResolveRosterInput(sortedFreeAgents.size());

            Player playerToBeHired =  sortedFreeAgents.get(userInput-1);
            playerToBeHired.setIsFreeAgent(false);
            team.getRoster().getAllPlayerList().add(playerToBeHired);
            freeAgentList.removeIf(player -> player.getPlayerName().equals(playerToBeHired.getPlayerName()));
        }
    }

    private void updateRosterSubLists(){
        if(null == team.getRoster().getAllPlayerList()){
            return;
        } else {
            team.getRoster().updateSubRoster(team.getRoster().getAllPlayerList());
        }
    }
}
