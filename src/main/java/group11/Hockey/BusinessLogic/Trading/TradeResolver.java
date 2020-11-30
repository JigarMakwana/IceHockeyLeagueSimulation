/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading;

import java.util.ArrayList;
import java.util.List;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.RandomNumGenerator.IRandomFloatGenerator;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.Enums.GMPersonalities;
import group11.Hockey.BusinessLogic.Enums.PlayerDraft;
import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.*;
import group11.Hockey.BusinessLogic.Validations.IUserInputCheck;
import group11.Hockey.BusinessLogic.Validations.IValidations;
import group11.Hockey.BusinessLogic.RandomNumGenerator.RandomNoFactory;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TradeResolver implements ITradeResolver {
    private ILeague leagueObj;
    private ITradeCharter tradeCharter;
    private ITeam offeringTeam;
    private List<IPlayer> offeredPlayerList;
    private ITeam requestedTeam;
    private List<IPlayer> requestedPlayerList;
    private ITradeConfig tradingConfig;
    private IDisplay display;
    private IUserInputCheck userInputCheck;
    private IRosterSearch rosterSearch;
    private IRandomFloatGenerator randomFloatGenerator;
    private static Logger logger = LogManager.getLogger(TradeResolver.class);

    public TradeResolver(ILeague leagueObj, ITradeCharter tradeCharter, ITradeConfig tradingConfig,
                         ICommandLineInput commandLineInput, IValidations validation, IDisplay display){
        this.leagueObj = leagueObj;
        this.tradeCharter = tradeCharter;
        this.offeringTeam = tradeCharter.getOfferingTeam();
        this.offeredPlayerList = tradeCharter.getOfferedPlayerList();
        this.requestedTeam = tradeCharter.getRequestedTeam();
        this.requestedPlayerList = tradeCharter.getRequestedPlayerList();
        this.tradingConfig = tradingConfig;
        this.display = display;
        this.userInputCheck = DefaultHockeyFactory.makeUserInputCheck(commandLineInput, validation, display);
        this.rosterSearch = DefaultHockeyFactory.makeRosterSearch();
        this.randomFloatGenerator = RandomNoFactory.makeRandomFloatGenerator();
    }

    @Override
    public void resolveTrade() {
        logger.debug("Entered resolveTrade()");
        if(tradeCharter.isCharterValid()){
            if (requestedTeam.isUserTeam()) {
                resolveAIToUserTrade();
            } else {
                resolveAIToAITrade();
            }
        } else if((tradeCharter.isDraftTradeCharter()) && (tradeCharter.getDraftRoundIdx() <= PlayerDraft.ROUND_7.getNumVal() ||
                tradeCharter.getDraftRoundIdx() >= PlayerDraft.ROUNDS_1.getNumVal())){
            resolveDraftTrade();
        }
        return;
    }

    @Override
    public void resetLossPoints(ITeam team) {
        logger.debug("Entered resetLossPoints()");
        team.setLosses(0);
    }

    @Override
    public float modifyAcceptanceChance(){
        logger.debug("Entered modifyAcceptanceChance()");
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
        logger.debug("Entered resolveDraftTrade()");
        logger.info("Resolving draft picks trading between " + offeringTeam.getTeamName() + " and " + requestedTeam.getTeamName());
        float randomAcceptanceChance = randomFloatGenerator.generateRandomNo();
        if (randomAcceptanceChance < modifyAcceptanceChance()) {
            acceptTrade();
            offeringTeam.updateTradedPicks(tradeCharter.getDraftRoundIdx());
            leagueObj.setDraftTradeTracker(offeringTeam,requestedTeam, tradeCharter.getDraftRoundIdx());
        } else {
            rejectTrade();
        }
    }

    private void resolveAIToAITrade(){
        logger.debug("Entered resolveAIToAITrade()");
        logger.info("\nResolving Trade between team " + offeringTeam.getTeamName() + " and " + requestedTeam.getTeamName());

        Float playerStrength1 = rosterSearch.getRosterStrength(offeredPlayerList);
        Float playerStrength2 = rosterSearch.getRosterStrength(requestedPlayerList);

        float randomAcceptanceChance = randomFloatGenerator.generateRandomNo();
        if (randomAcceptanceChance < modifyAcceptanceChance()) {
            acceptTrade();
        } else if (playerStrength1 > playerStrength2) {
            acceptTrade();
        } else {
            rejectTrade();
        }
    }

    private void resolveAIToUserTrade(){
        logger.debug("Entered resolveAIToUserTrade()");
        display.displayTradeStatisticsToUser(offeringTeam.getTeamName(), offeredPlayerList, requestedTeam.getTeamName(), requestedPlayerList);
        display.displayAcceptRejectOptionToUser();

        int userInput = userInputCheck.validateUserTradeInput();
        if (userInput == 1) {
            acceptTrade();
        } else if (userInput == 0) {
            rejectTrade();
        }
    }

    @Override
    public void acceptTrade() {
        logger.debug("Entered acceptTrade()");
        List<IPlayer> localOfferedPlayerList = new ArrayList<>();
        if(null == offeredPlayerList){
        } else {
            for (IPlayer p : offeredPlayerList) {
                localOfferedPlayerList.add(p);
            }
        }

        List<IPlayer> localRequestedPlayerList = new ArrayList<>();
        for (IPlayer p : requestedPlayerList) {
            localRequestedPlayerList.add(p);
        }
        if(null == offeredPlayerList){
        } else {
            for (IPlayer toBeRemoved : localOfferedPlayerList) {
                offeringTeam.getPlayers().removeIf(player -> player.getPlayerName().equals(toBeRemoved.getPlayerName()));
            }
        }
        for (IPlayer toBeRemoved : localRequestedPlayerList) {
            requestedTeam.getPlayers().removeIf(player -> player.getPlayerName().equals(toBeRemoved.getPlayerName()));
        }
        for (IPlayer toBeAdded : localRequestedPlayerList) {
            offeringTeam.getPlayers().add(toBeAdded);
        }
        if(null == offeredPlayerList){
        } else {
            for (IPlayer toBeAdded : localOfferedPlayerList) {
                requestedTeam.getPlayers().add(toBeAdded);
            }
        }
        resetLossPoints(offeringTeam);
        display.showMessageOnConsole("Trade successfully accepted!");
    }

    @Override
    public void rejectTrade() {
        logger.debug("Entered rejectTrade()");
        display.showMessageOnConsole("Trade is declined.");
        resetLossPoints(offeringTeam);
    }
}
