/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.*;
import group11.Hockey.BusinessLogic.Enums.RosterSize;
import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeSettler;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class TradeSettler implements ITradeSettler {
    private ITeam team;
    private int teamSize;
    private int teamGoalieSize;
    private int teamForwardSize;
    private int teamDefenseSize;
    private List<IPlayer> freeAgentList;
    private ICommandLineInput commandLineInput;
    private IValidations validation;
    private IDisplay display;
    private IRosterSearch rosterSearch;
    private static Logger logger = LogManager.getLogger(TradeSettler.class);

    public TradeSettler(ITeam team, List<IPlayer> freeAgentList, ICommandLineInput commandLineInput, IValidations validation, IDisplay display){
        this.team = team;
        this.freeAgentList = freeAgentList;
        this.commandLineInput = commandLineInput;
        this.validation = validation;
        this.display = display;
        this.rosterSearch = DefaultHockeyFactory.makeRosterSearch();
        initPlayers(team);
    }

    private void initPlayers(ITeam team){
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
        logger.debug("Entered settleTeam()");
        if(null == team || team.getRoster().isValidRoster()){
            return;
        } else {
            logger.info("\nSettling Team " + team.getTeamName() + "'s size...");
            int constantTeamSize = RosterSize.ACTIVE_ROSTER_SIZE.getNumVal() + RosterSize.INACTIVE_ROSTER_SIZE.getNumVal();
            if(teamSize > constantTeamSize) {
                dropPlayers();
            } else if (teamSize < constantTeamSize){
                hirePlayers();
            }
        }
        logger.info("Team " + team.getTeamName() + "'s size successfully settled!\n");
    }

    public void dropPlayers(){
        logger.debug("Entered dropPlayers()");
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
        logger.debug("Entered dropGoalie()");
        List<IPlayer> goalieList = team.getRoster().getGoalieList();
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
        logger.debug("Entered dropForward()");
        List<IPlayer> forwardList = team.getRoster().getForwardList();
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
        logger.debug("Entered dropDefense()");
        List<IPlayer> defenseList = team.getRoster().getDefenseList();
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

    public void dropPlayerFromAITeam(List<IPlayer> playerList) {
        logger.debug("Entered dropPlayerFromAITeam()");
        if(null == playerList.get(0)){
            return;
        } else{
        	IPlayer playerToBeDropped = playerList.get(0);
            playerToBeDropped.setIsFreeAgent(true);
            playerToBeDropped.setCaptain(false);
            freeAgentList.add(playerToBeDropped);
            team.getRoster().getAllPlayerList().removeIf(player -> player.getPlayerName().equals(playerToBeDropped.getPlayerName()));
        }
    }

    public void dropPlayerFromUserTeam(List<IPlayer> playerList) {
        logger.debug("Entered dropPlayerFromUserTeam()");
        display.pickPlayer(playerList);
        IUserInputCheck userInputCheck = DefaultHockeyFactory.makeUserInputCheck(commandLineInput, validation, display);
        int userInput = userInputCheck.userResolveRosterInput(playerList.size());
        logger.debug("User input is " + userInput);
        IPlayer p =  playerList.get(userInput-1);
        p.setIsFreeAgent(true);
        p.setCaptain(false);
        freeAgentList.add(p);
        team.getRoster().getAllPlayerList().removeIf(player -> player.getPlayerName().equals(p.getPlayerName()));
    }

    public void hirePlayers(){
        logger.debug("Entered hirePlayers()");
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
        logger.debug("Entered hireGoalie()");
        for(int i=0; i<noOfGoaliesToBeHired; i++) {
            List<IPlayer> sortedFreeAgents = rosterSearch.getGoalieList(freeAgentList);
            try{
                if(team.isUserTeam() == true) {
                    hirePlayerInUserTeam(sortedFreeAgents);
                }
                else {
                    Collections.reverse(sortedFreeAgents);
                    hirePlayerInAITeam(sortedFreeAgents);
                }
            }catch (Exception e) {
                logger.error("Cannot hire Goalie. " + e);
                e.printStackTrace();
            }
            updateRosterSubLists();
        }
    }

    private void hireForward(int noOfForwardToBeHired){
        logger.debug("Entered hireForward()");
        for(int i=0; i<noOfForwardToBeHired; i++) {
            List<IPlayer> sortedFreeAgents = rosterSearch.getForwardList(freeAgentList);
            try{
                if (team.isUserTeam() == true) {
                    hirePlayerInUserTeam(sortedFreeAgents);
                } else {
                    Collections.reverse(sortedFreeAgents);
                    hirePlayerInAITeam(sortedFreeAgents);
                }
            }catch (Exception e) {
                logger.error("Cannot hire Forward. " + e);
                e.printStackTrace();
            }
            updateRosterSubLists();
        }
    }

    private void hireDefense(int noOfDefenseToBeHired){
        logger.debug("Entered hireDefense()");
        for(int i=0; i<noOfDefenseToBeHired; i++) {
            List<IPlayer> sortedFreeAgents = rosterSearch.getDefenseList(freeAgentList);
            try{
                if(team.isUserTeam() == true) {
                    hirePlayerInUserTeam(sortedFreeAgents);
                }
                else {
                    Collections.reverse(sortedFreeAgents);
                    hirePlayerInAITeam(sortedFreeAgents);
                }
            }catch (Exception e) {
                logger.error("Cannot hire Defense. " + e);
                e.printStackTrace();
            }
            updateRosterSubLists();
        }
    }

    private void hirePlayerInAITeam(List<IPlayer> sortedFreeAgents) throws Exception {
        logger.debug("Entered hirePlayerInAITeam()");
        if(sortedFreeAgents.size() == 0){
            throw new Exception("There are No enough Free Agents available to settle a Team.");
        } else{
        	IPlayer playerToBeHired = sortedFreeAgents.get(0);
            playerToBeHired.setIsFreeAgent(false);
            team.getRoster().getAllPlayerList().add(playerToBeHired);
            freeAgentList.removeIf(player -> player.getPlayerName().equals(playerToBeHired.getPlayerName()));
        }
    }

    public void hirePlayerInUserTeam(List<IPlayer> sortedFreeAgents) throws Exception {
        logger.debug("Entered hirePlayerInUserTeam()");
        if(null == sortedFreeAgents){
            throw new Exception("IPlayer is not available in Free Agents to form a Team.");
        } else {
            display.pickPlayer(sortedFreeAgents);
            IUserInputCheck userInputCheck = DefaultHockeyFactory.makeUserInputCheck(commandLineInput, validation, display);
            int userInput = userInputCheck.userResolveRosterInput(sortedFreeAgents.size());

            IPlayer playerToBeHired =  sortedFreeAgents.get(userInput-1);
            playerToBeHired.setIsFreeAgent(false);
            team.getRoster().getAllPlayerList().add(playerToBeHired);
            freeAgentList.removeIf(player -> player.getPlayerName().equals(playerToBeHired.getPlayerName()));
        }
    }

    private void updateRosterSubLists(){
        logger.debug("Entered updateRosterSubLists()");
        if(null == team.getRoster().getAllPlayerList()){
            return;
        } else {
            logger.info("Updating Team Roster with latest team changes");
            team.getRoster().updateSubRoster(team.getRoster().getAllPlayerList());
        }
    }
}
