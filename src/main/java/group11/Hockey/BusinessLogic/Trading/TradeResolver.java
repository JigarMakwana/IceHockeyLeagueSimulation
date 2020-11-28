package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.*;
import group11.Hockey.BusinessLogic.Trading.Interfaces.*;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TradeResolver implements ITradeResolver {
    private ITradeCharter tradeCharter;
    private Team offeringTeam;
    private List<Player> offeredPlayerList;
    private Team requestedTeam;
    private List<Player> requestedPlayerList;
    private ITradingConfig tradingConfig;
    private ICommandLineInput commandLineInput;
    private IValidations validation;
    private IDisplay display;
    private IUserInputCheck userInputCheck;
    private IRosterSearch rosterSearch;
    private IRandomNoGenerator randomFloatGenerator;
    private static Logger logger = LogManager.getLogger(TradeResolver.class);

    public TradeResolver(ITradeCharter tradeCharter, ITradingConfig tradingConfig,
                         ICommandLineInput commandLineInput, IValidations validation, IDisplay display){
        this.tradeCharter = tradeCharter;
        this.offeringTeam = tradeCharter.getOfferingTeam();
        this.offeredPlayerList = tradeCharter.getOfferedPlayerList();
        this.requestedTeam = tradeCharter.getRequestedTeam();
        this.requestedPlayerList = tradeCharter.getRequestedPlayerList();
        this.tradingConfig = tradingConfig;
        this.commandLineInput = commandLineInput;
        this.validation = validation;
        this.display = display;
        this.userInputCheck = DefaultHockeyFactory.makeUserInputCheck(commandLineInput, validation, display);
        this.rosterSearch = DefaultHockeyFactory.makeRosterSearch();
        this.randomFloatGenerator = DefaultHockeyFactory.makeRandomFloatGenerator();
    }

    @Override
    public void resolveTrade() {
    	logger.info("Entered resolveTrade()");
        if(tradeCharter.isCharterValid()){
            if (requestedTeam.isUserTeam()) {
            	logger.info("Resolving AI to User trade");
                resolveAIToUserTrade();
            } else {
            	logger.info("Resolving AI to AI trade");
                resolveAIToAITrade();
            }
        } else if(tradeCharter.getDraftRoundIdx() <= PlayerDraft.ROUND_7.getNumVal() ||
                tradeCharter.getDraftRoundIdx() >= PlayerDraft.ROUNDS_1.getNumVal()){
        	logger.info("Resolving Draft trade");
            resolveDraftTrade();
        }
        return;
    }

    @Override
    public void resetLossPoints(Team team) {
    	logger.info("Entered resetLossPoints()");
        team.setLosses(0);
    }

    @Override
    public float modifyAcceptanceChance(){
    	logger.info("Entered modifyAcceptanceChance()");
        float modfiedChance = 0.0f;
        String gmPersonality = offeringTeam.getGeneralManager().getPersonality();
        if(gmPersonality.equalsIgnoreCase(GMPersonalities.SHREWD.toString())){
            modfiedChance = tradingConfig.getGmTable().getShrewd();
        } else if(gmPersonality.equalsIgnoreCase(GMPersonalities.NORMAL.toString())){
            modfiedChance = tradingConfig.getGmTable().getNormal();
        } else if(gmPersonality.equalsIgnoreCase(GMPersonalities.GAMBLER.toString())){
            modfiedChance = tradingConfig.getGmTable().getGambler();
        }
        return tradingConfig.getRandomAcceptanceChance() + modfiedChance;
    }

    public void resolveDraftTrade(){
    	logger.info("Entered resolveDraftTrade()");
        display.showMessageOnConsole("Resolving draft picks trading...");
        float randomAcceptanceChance = randomFloatGenerator.generateRandomFloat();
        if (randomAcceptanceChance < modifyAcceptanceChance()) {
        	logger.info("Draft trade is accepted");
            acceptTrade();
            offeringTeam.setTradedPicks(tradeCharter.getDraftRoundIdx());
            TradeDraft.updateDraftTradeTracker(offeringTeam,requestedTeam, tradeCharter.getDraftRoundIdx());

        } else {
        	logger.info("Draft trade is rejected");
            rejectTrade();
        }
    }

    private void resolveAIToAITrade(){
    	logger.info("Entered resolveAIToAITrade()");
        display.displayTradeStatistics(offeringTeam.getTeamName(), offeredPlayerList,
                requestedTeam.getTeamName(), requestedPlayerList);

        Float playerStrength1 = rosterSearch.getRosterStrength(offeredPlayerList);
        Float playerStrength2 = rosterSearch.getRosterStrength(requestedPlayerList);

        float randomAcceptanceChance = randomFloatGenerator.generateRandomFloat();
        if (randomAcceptanceChance < modifyAcceptanceChance()) {
        	logger.info("AI to AI trade is accepted");
            acceptTrade();
        } else if (playerStrength1 > playerStrength2) {
        	logger.info("AI to AI trade is accepted");
            acceptTrade();
        } else {
        	logger.info("AI to AI trade is rejected");
            rejectTrade();
        }
    }

    private void resolveAIToUserTrade(){
    	logger.info("Entered claculateStrength()");
        display.displayTradeStatisticsToUser(offeringTeam.getTeamName(), offeredPlayerList, requestedTeam.getTeamName(), requestedPlayerList);
        display.displayAcceptRejectOptionToUser();

        int userInput = userInputCheck.validateUserTradeInput();
        if (userInput == 1) {
        	logger.info("AI to User trade is accepted");
            acceptTrade();
        } else if (userInput == 0) {
        	logger.info("AI to User trade is rejected");
            rejectTrade();
        }
    }

    private void acceptTrade() {
    	logger.info("Entered acceptTrade()");
        List<Player> localOfferedPlayerList = new ArrayList<>();
        if(null == offeredPlayerList){
        } else {
            for (Player p : offeredPlayerList) {
                localOfferedPlayerList.add(p);
            }
        }

        List<Player> localRequestedPlayerList = new ArrayList<>();
        for (Player p : requestedPlayerList) {
            localRequestedPlayerList.add(p);
        }
        if(null == offeredPlayerList){
        } else {
            for (Player toBeRemoved : localOfferedPlayerList) {
                offeringTeam.getPlayers().removeIf(player -> player.getPlayerName().equals(toBeRemoved.getPlayerName()));
            }
        }
        for (Player toBeRemoved : localRequestedPlayerList) {
            requestedTeam.getPlayers().removeIf(player -> player.getPlayerName().equals(toBeRemoved.getPlayerName()));
        }
        for (Player toBeAdded : localRequestedPlayerList) {
            offeringTeam.getPlayers().add(toBeAdded);
        }
        if(null == offeredPlayerList){
        } else {
            for (Player toBeAdded : localOfferedPlayerList) {
                requestedTeam.getPlayers().add(toBeAdded);
            }
        }
        resetLossPoints(offeringTeam);
        display.showMessageOnConsole("Trade successfully accepted!");
    }

    private void rejectTrade() {
    	logger.info("Entered rejectTrade()");
        display.showMessageOnConsole("Trade is declined.");
        resetLossPoints(offeringTeam);
    }
}
