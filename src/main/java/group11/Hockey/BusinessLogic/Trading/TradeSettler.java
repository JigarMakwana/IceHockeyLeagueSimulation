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

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
    private static Logger logger = LogManager.getLogger(TradeSettler.class);

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
    public void settleTeam() {
    	logger.info("Entered settleTeam()");
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
    	logger.info("Entered dropPlayers()");
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
    	logger.info("Entered dropGoalie()");
        List<Player> goalieList = team.getRoster().getGoalieList();
        for(int i=0; i<noOfGoaliesToBeDropped; i++) {
            if(team.isUserTeam() == true) {
            	logger.info("Dropping goalie players from User team");
                dropPlayerFromUserTeam(goalieList);
            }
            else {
            	logger.info("Dropping goalie players from AI team");
                dropPlayerFromAITeam(goalieList);
            }
            updateRosterSubLists();
        }
    }

    public void dropForward(int noOfForwardToBeDropped){
    	logger.info("Entered dropForward()");
        List<Player> forwardList = team.getRoster().getForwardList();
        for(int i=0; i<noOfForwardToBeDropped; i++) {
            if(team.isUserTeam() == true) {
            	logger.info("Dropping forward players from User team");
                dropPlayerFromUserTeam(forwardList);
            }
            else {
            	logger.info("Dropping forward players from AI team");
                dropPlayerFromAITeam(forwardList);
            }
            updateRosterSubLists();
        }
    }

    public void dropDefense(int noOfDefenseToBeDropped){
    	logger.info("Entered dropDefense()");
        List<Player> defenseList = team.getRoster().getDefenseList();
        for(int i=0; i<noOfDefenseToBeDropped; i++) {
            if(team.isUserTeam() == true) {
            	logger.info("Dropping defense players from User team");
                dropPlayerFromUserTeam(defenseList);
            }
            else {
            	logger.info("Dropping defense players from AI team");
                dropPlayerFromAITeam(defenseList);
            }
            updateRosterSubLists();
        }
    }

    public void dropPlayerFromAITeam(List<Player> playerList) {
    	logger.info("Entered dropPlayerFromAITeam()");
        if(null == playerList.get(0)){
            return;
        } else{        	
            Player playerToBeDropped = playerList.get(0);
            logger.info("Dropping player "+playerToBeDropped.getPlayerName()+" from AI team");
            playerToBeDropped.setIsFreeAgent(true);
            playerToBeDropped.setCaptain(false);
            freeAgentList.add(playerToBeDropped);
            team.getRoster().getAllPlayerList().removeIf(player -> player.getPlayerName().equals(playerToBeDropped.getPlayerName()));
        }
    }

    public void dropPlayerFromUserTeam(List<Player> playerList) {
    	logger.info("Entered dropPlayerFromUserTeam()");
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
    	logger.info("Entered hirePlayers()");
        int constantGoalieSize = constantSupplier.getGoalieSize();
        int constantForwardSize = constantSupplier.getForwardSize();
        int constantDefenseSize = constantSupplier.getDefenseSize();

        if(teamGoalieSize < constantGoalieSize) {
        	logger.info("Hiring Goalie player");
            int noOfGoaliesToBeHired = constantGoalieSize - teamGoalieSize;
            hireGoalie(noOfGoaliesToBeHired);
        }
        if(teamForwardSize < constantForwardSize){
        	logger.info("Hiring Goalie player");
            int noOfForwardToBeHired = constantForwardSize - teamForwardSize;
            hireForward(noOfForwardToBeHired);
        }
        if(teamDefenseSize < constantDefenseSize){
        	logger.info("Hiring Defense player");
            int noOfDefenseToBeHired = constantDefenseSize - teamDefenseSize;
            hireDefense(noOfDefenseToBeHired);
        }
    }

    public void hireGoalie(int noOfGoaliesToBeHired){
    	logger.info("Entered hireGoalie()");
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
            	logger.error("Exception occured : "+e);
                e.printStackTrace();
            }
            updateRosterSubLists();
        }
    }

    public void hireForward(int noOfForwardToBeHired){
    	logger.info("Entered hireForward()");
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
            	logger.error("Exception occured : "+e);
                e.printStackTrace();
            }
            updateRosterSubLists();
        }
    }

    public void hireDefense(int noOfDefenseToBeHired){
    	logger.info("Entered hireDefense()");
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
            	logger.error("Exception occured : "+e);
                e.printStackTrace();
            }
            updateRosterSubLists();
        }
    }

    public void hirePlayerInAITeam(List<Player> sortedFreeAgents) throws Exception {
    	logger.info("Entered hirePlayerInAITeam()");
        if(null == sortedFreeAgents.get(0)){
        	logger.info("No free agent available");
            throw new Exception("Player is not available in Free Agents to form a Team.");
        } else{
        	logger.info("Hiring free agent for "+team.getTeamName()+" team");
            Player playerToBeHired = sortedFreeAgents.get(0);
            playerToBeHired.setIsFreeAgent(false);
            team.getRoster().getAllPlayerList().add(playerToBeHired);
            freeAgentList.removeIf(player -> player.getPlayerName().equals(playerToBeHired.getPlayerName()));
        }
    }

    public void hirePlayerInUserTeam(List<Player> sortedFreeAgents) throws Exception {
    	logger.info("Entered hirePlayerInUserTeam()");
        if(null == sortedFreeAgents){
        	logger.info("No free agent available");
            throw new Exception("Player is not available in Free Agents to form a Team.");
        } else {
        	logger.info("Hiring free agent for "+team.getTeamName()+" team");
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
    	logger.info("Entered claculateStrength()");
        if(null == team.getRoster().getAllPlayerList()){
        	logger.info("Roaster sublist of team "+team.getTeamName()+" is empty");
            return;
        } else {
        	logger.info("Updating roaster sublist of team "+team.getTeamName());
            team.getRoster().updateSubRoster(team.getRoster().getAllPlayerList());
        }
    }
}
