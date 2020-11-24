package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.*;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeSettler;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;

import java.util.Collections;
import java.util.Iterator;
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
    private IConstantSupplier constantSupplier;
    private IRosterSearch rosterSearch;

    public TradeSettler(ITeam team, List<IPlayer> freeAgentList,
                        ICommandLineInput commandLineInput,
                        IValidations validation, IDisplay display,
                        IConstantSupplier constantSupplier){
        this.team = team;
        this.teamSize = this.team.getRoster().getAllPlayerList().size();;
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
        for(int i=0; i<noOfGoaliesToBeDropped; i++) {
            if(team.isUserTeam() == true) {
                dropPlayerFromUserTeam(Positions.GOALIE);
            }
            else {
                dropPlayerFromAITeam(Positions.GOALIE);
            }
        }
    }

    public void dropForward(int noOfForwardToBeDropped){
        for(int i=0; i<noOfForwardToBeDropped; i++) {
            if(team.isUserTeam() == true) {
                dropPlayerFromUserTeam(Positions.FORWARD);
            }
            else {
                dropPlayerFromAITeam(Positions.FORWARD);
            }
        }
    }

    public void dropDefense(int noOfDefenseToBeDropped){
        for(int i=0; i<noOfDefenseToBeDropped; i++) {
            if(team.isUserTeam() == true) {
                dropPlayerFromUserTeam(Positions.DEFENSE);
            }
            else {
                dropPlayerFromAITeam(Positions.DEFENSE);
            }
        }
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
            try{
                if(team.isUserTeam() == true) {
                    hirePlayerInUserTeam(Positions.GOALIE);
                }
                else {
                    hirePlayerInAITeam(Positions.GOALIE);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void hireForward(int noOfForwardToBeHired){
        for(int i=0; i<noOfForwardToBeHired; i++) {
            try {
                if (team.isUserTeam() == true) {
                    hirePlayerInUserTeam(Positions.FORWARD);
                } else {
                    hirePlayerInAITeam(Positions.FORWARD);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void hireDefense(int noOfDefenseToBeHired){
        for(int i=0; i<noOfDefenseToBeHired; i++) {
            try{
                if(team.isUserTeam() == true) {
                    hirePlayerInUserTeam(Positions.DEFENSE);
                }
                else {
                    hirePlayerInAITeam(Positions.DEFENSE);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void dropPlayerFromAITeam(Positions playerPosition) {
        List<IPlayer> allPlayerList = team.getRoster().getAllPlayerList();
        List<IPlayer> sortedPlayersList = rosterSearch.sortPlayersByStrength(allPlayerList);
        Iterator<IPlayer> playersItr = sortedPlayersList.iterator();

        while (playersItr.hasNext()) {
            IPlayer player = playersItr.next();
            if(playerPosition.equals(Positions.GOALIE)) {
                if (player.getPosition().equalsIgnoreCase(playerPosition.toString())) {
                    player.setIsFreeAgent(true);
                    player.setCaptain(false);
                    freeAgentList.add(player);
                    playersItr.remove();
                    break;
                }
            } else if(playerPosition.equals(Positions.FORWARD)) {
                if (player.getPosition().equalsIgnoreCase(Positions.FORWARD.toString())) {
                    player.setIsFreeAgent(true);
                    player.setCaptain(false);
                    freeAgentList.add(player);
                    playersItr.remove();
                    break;
                }
            } else if(playerPosition.equals(Positions.DEFENSE)) {
                if (player.getPosition().equalsIgnoreCase(Positions.DEFENSE.toString())) {
                    player.setIsFreeAgent(true);
                    player.setCaptain(false);
                    freeAgentList.add(player);
                    playersItr.remove();
                    break;
                }
            }
        }
    }

    public void dropPlayerFromUserTeam(Positions playerPosition) {
        if(playerPosition.equals(Positions.GOALIE)) {
            List<IPlayer> goaliePlayerList = team.getRoster().getGoalieList();
            display.pickPlayer(goaliePlayerList);
            IUserInputCheck userInputCheck = DefaultHockeyFactory.makeUserInputCheck(commandLineInput, validation, display);
            int userInput = userInputCheck.userResolveRosterInput(goaliePlayerList.size());

            IPlayer goalie =  goaliePlayerList.get(userInput-1);
            goalie.setIsFreeAgent(true);
            goalie.setCaptain(false);
            freeAgentList.add(goalie);
            goaliePlayerList.remove(goalie);
        }
        else if(playerPosition.equals(Positions.FORWARD)) {
            List<IPlayer> forwardPlayerList = team.getRoster().getForwardList();
            display.pickPlayer(forwardPlayerList);
            IUserInputCheck userInputCheck = DefaultHockeyFactory.makeUserInputCheck(commandLineInput, validation, display);
            int userInput = userInputCheck.userResolveRosterInput(forwardPlayerList.size());

            IPlayer forward =  forwardPlayerList.get(userInput-1);
            forward.setIsFreeAgent(true);
            forward.setCaptain(false);
            freeAgentList.add(forward);
            forwardPlayerList.remove(forward);
        }
        else if(playerPosition.equals(Positions.DEFENSE)) {
            List<IPlayer> defensePlayerList = team.getRoster().getDefenseList();
            display.pickPlayer(defensePlayerList);
            IUserInputCheck userInputCheck = DefaultHockeyFactory.makeUserInputCheck(commandLineInput, validation, display);
            int userInput = userInputCheck.userResolveRosterInput(defensePlayerList.size());

            IPlayer defense =  defensePlayerList.get(userInput-1);
            defense.setIsFreeAgent(true);
            defense.setCaptain(false);
            freeAgentList.add(defense);
            defensePlayerList.remove(defense);
        }
    }

    public void hirePlayerInAITeam(Positions playerPosition) throws Exception {
        List<IPlayer> sortedFreeAgents = rosterSearch.sortPlayersByStrength(freeAgentList);
        Collections.reverse(sortedFreeAgents);
        Iterator<IPlayer> freeAgentsItr = sortedFreeAgents.iterator();
        boolean playerHired = false;

        while (freeAgentsItr.hasNext()) {
            IPlayer freeAgent = freeAgentsItr.next();
            if(playerPosition.equals(Positions.GOALIE)) {
                if (freeAgent.getPosition().equalsIgnoreCase(playerPosition.toString())) {
                    freeAgent.setIsFreeAgent(false);
                    team.getRoster().getAllPlayerList().add(freeAgent);
                    freeAgentsItr.remove();
                    playerHired = true;
                    break;
                }
            }
            else if(playerPosition.equals(Positions.SKATER)) {
                if (freeAgent.getPosition().equalsIgnoreCase(Positions.FORWARD.toString()) ||
                        freeAgent.getPosition().equalsIgnoreCase(Positions.DEFENSE.toString())) {
                    freeAgent.setIsFreeAgent(false);
                    team.getRoster().getAllPlayerList().add(freeAgent);
                    freeAgentsItr.remove();
                    playerHired = true;
                    break;
                }
            }
        }
        if(playerPosition.equals(Positions.GOALIE) && playerHired == false) {
            throw new Exception("Goalie is not available in Free Agents to form a Team.");
        } else if(playerPosition.equals(Positions.FORWARD) && playerHired == false) {
            throw new Exception("Forward is not available in Free Agents to form a Team.");
        } else if(playerPosition.equals(Positions.DEFENSE) && playerHired == false) {
            throw new Exception("Defense is not available in Free Agents to form a Team.");
        }
    }

    public void hirePlayerInUserTeam(Positions playerPosition) throws Exception {
        Iterator<IPlayer> freeAgentsItr = freeAgentList.iterator();
        boolean playerHired = false;

        if(playerPosition.equals(Positions.GOALIE)) {
            List<IPlayer> goalieFreeAgents = rosterSearch.getGoalieList(freeAgentList);
            if(goalieFreeAgents == null){
                playerHired = false;
            } else {
                display.pickPlayer(goalieFreeAgents);
                IUserInputCheck userInputCheck = DefaultHockeyFactory.makeUserInputCheck(commandLineInput, validation, display);
                int userInput = userInputCheck.userResolveRosterInput(goalieFreeAgents.size());
                IPlayer goalie =  goalieFreeAgents.get(userInput-1);
                goalie.setIsFreeAgent(false);
                team.getRoster().getAllPlayerList().add(goalie);
                freeAgentList.remove(goalie);
                playerHired = true;
            }
        } else if(playerPosition.equals(Positions.FORWARD)) {
            List<IPlayer> forwardFreeAgents = rosterSearch.getForwardList(freeAgentList);
            if(forwardFreeAgents == null){
                playerHired = false;
            } else {
                display.pickPlayer(forwardFreeAgents);
                IUserInputCheck userInputCheck = DefaultHockeyFactory.makeUserInputCheck(commandLineInput, validation, display);
                int userInput = userInputCheck.userResolveRosterInput(forwardFreeAgents.size());
                IPlayer forward =  forwardFreeAgents.get(userInput-1);
                forward.setIsFreeAgent(false);
                team.getRoster().getAllPlayerList().add(forward);
                freeAgentList.remove(forward);
                playerHired = true;
            }
        } else if(playerPosition.equals(Positions.DEFENSE)) {
            List<IPlayer> defenseFreeAgents = rosterSearch.getDefenseList(freeAgentList);
            if(defenseFreeAgents == null){
                playerHired = false;
            } else {
                display.pickPlayer(defenseFreeAgents);
                IUserInputCheck userInputCheck = DefaultHockeyFactory.makeUserInputCheck(commandLineInput, validation, display);
                int userInput = userInputCheck.userResolveRosterInput(defenseFreeAgents.size());
                IPlayer defense =  defenseFreeAgents.get(userInput-1);
                defense.setIsFreeAgent(false);
                team.getRoster().getAllPlayerList().add(defense);
                freeAgentList.remove(defense);
                playerHired = true;
            }
        }
        if(playerPosition.equals(Positions.GOALIE) && playerHired == false) {
            throw new Exception("Goalie is not available in Free Agents to form a Team.");
        } else if(playerPosition.equals(Positions.FORWARD) && playerHired == false) {
            throw new Exception("Forward is not available in Free Agents to form a Team.");
        } else if(playerPosition.equals(Positions.DEFENSE) && playerHired == false) {
            throw new Exception("Defense is not available in Free Agents to form a Team.");
        }
    }
}
