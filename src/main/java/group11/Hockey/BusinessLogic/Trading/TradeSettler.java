package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.*;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeSettler;
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
    private IConstantSupplier constantSupplier;
    private IRosterSearch rosterSearch;

    public TradeSettler(Team team, List<Player> freeAgentList,
                        ICommandLineInput commandLineInput,
                        IValidations validation, IDisplay display,
                        IConstantSupplier constantSupplier){
        this.team = team;
        this.teamSize = this.team.getRoster().getAllPlayerList().size();
        this.teamGoalieSize = this.team.getRoster().getGoalieList().size();
        this.teamForwardSize = this.team.getRoster().getForwardList().size();
        this.teamDefenseSize = this.team.getRoster().getDefenseList().size();
        this.freeAgentList = freeAgentList;
        this.commandLineInput = commandLineInput;
        this.validation = validation;
        this.display = display;
        this.constantSupplier = constantSupplier;
        this.rosterSearch = DefaultHockeyFactory.makeRosterSearch();
    }

    @Override
    public void settleTeamAfterTrade() {
        display.showMessageOnConsole("\nSettling Team " + team.getTeamName() + "'s size...");
        if(team.getRoster().isValidRoster()){
            return;
        } else {
            int constantTeamSize = constantSupplier.getInActiveRosterSize() + constantSupplier.getActiveRosterSize();
            if(teamSize > constantTeamSize) {
                dropPlayers();
            } else if (teamSize < constantTeamSize){
                hirePlayers();
            }
        }
        display.showMessageOnConsole("Team " + team.getTeamName() + "'s size successfully settled!\n");
    }

    public void dropPlayers(){
        int constantGoalieSize = constantSupplier.getGoalieSize();
        int constantForwardSize = constantSupplier.getForwardSize();
        int constantDefenseSize = constantSupplier.getDefenseSize();

        if(teamGoalieSize > constantGoalieSize) {
            int noOfGoaliesToBeDropped = teamGoalieSize - constantGoalieSize;
            dropGoalie(noOfGoaliesToBeDropped);
        }
        if(teamForwardSize > constantForwardSize){
            int noOfForwardToBeDropped = teamForwardSize - constantForwardSize;
            dropForward(noOfForwardToBeDropped);
        }
        if(teamDefenseSize > constantDefenseSize){
            int noOfDefenseToBeDropped = teamDefenseSize - constantDefenseSize;
            dropDefense(noOfDefenseToBeDropped);
        }
    }

    public void dropGoalie(int noOfGoaliesToBeDropped){
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

    public void dropForward(int noOfForwardToBeDropped){
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

    public void dropDefense(int noOfDefenseToBeDropped){
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

    public void dropPlayerFromAITeam(List<Player> playerList) {
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

    public void dropPlayerFromUserTeam(List<Player> playerList) {
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
        int constantGoalieSize = constantSupplier.getGoalieSize();
        int constantForwardSize = constantSupplier.getForwardSize();
        int constantDefenseSize = constantSupplier.getDefenseSize();

        if(teamGoalieSize < constantGoalieSize) {
            int noOfGoaliesToBeHired = constantGoalieSize - teamGoalieSize;
            hireGoalie(noOfGoaliesToBeHired);
        }
        if(teamForwardSize < constantForwardSize){
            int noOfForwardToBeHired = constantForwardSize - teamForwardSize;
            hireForward(noOfForwardToBeHired);
        }
        if(teamDefenseSize < constantDefenseSize){
            int noOfDefenseToBeHired = constantDefenseSize - teamDefenseSize;
            hireDefense(noOfDefenseToBeHired);
        }
    }

    public void hireGoalie(int noOfGoaliesToBeHired){
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

    public void hireForward(int noOfForwardToBeHired){
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

    public void hireDefense(int noOfDefenseToBeHired){
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

    public void hirePlayerInAITeam(List<Player> sortedFreeAgents) throws Exception {
        if(null == sortedFreeAgents.get(0)){
            throw new Exception("Player is not available in Free Agents to form a Team.");
        } else{
            Player playerToBeHired = sortedFreeAgents.get(0);
            playerToBeHired.setIsFreeAgent(false);
            team.getRoster().getAllPlayerList().add(playerToBeHired);
            freeAgentList.removeIf(player -> player.getPlayerName().equals(playerToBeHired.getPlayerName()));
        }
    }

    public void hirePlayerInUserTeam(List<Player> sortedFreeAgents) throws Exception {
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

    public void updateRosterSubLists(){
        if(null == team.getRoster().getAllPlayerList()){
            return;
        } else {
            team.getRoster().updateSubRoster(team.getRoster().getAllPlayerList());
        }
    }
}
