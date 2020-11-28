package group11.Hockey.BusinessLogic.Trading;

import java.util.ArrayList;
import java.util.List;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.IRandomNoGenerator;
import group11.Hockey.BusinessLogic.IUserInputCheck;
import group11.Hockey.BusinessLogic.IValidations;
import group11.Hockey.BusinessLogic.PlayerDraft;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeCharter;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeResolver;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradingConfig;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;

public class TradeResolver implements ITradeResolver {
    private ITradeCharter tradeCharter;
    private ITeam offeringTeam;
    private List<IPlayer> offeredPlayerList;
    private ITeam requestedTeam;
    private List<IPlayer> requestedPlayerList;
    private ITradingConfig tradingConfig;
    private ICommandLineInput commandLineInput;
    private IValidations validation;
    private IDisplay display;
    private IUserInputCheck userInputCheck;
    private IRosterSearch rosterSearch;
    private IRandomNoGenerator randomFloatGenerator;

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
        this.randomFloatGenerator = DefaultHockeyFactory.makeRandomNumberGenerator();
    }

    @Override
    public void resolveTrade() {
        if(tradeCharter.isCharterValid()){
            if (requestedTeam.isUserTeam()) {
                resolveAIToUserTrade();
            } else {
                resolveAIToAITrade();
            }
        } else if(tradeCharter.getDraftRoundIdx() <= PlayerDraft.ROUND_7.getNumVal() ||
                tradeCharter.getDraftRoundIdx() >= PlayerDraft.ROUNDS_1.getNumVal()){
            resolveDraftTrade();
        }
        return;
    }

    @Override
    public void resetLossPoints(ITeam team) {
        team.setLosses(0);
    }

    @Override
    public float modifyAcceptanceChance(){
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
        display.showMessageOnConsole("Resolving draft picks trading...");
        float randomAcceptanceChance = randomFloatGenerator.generateRandomFloat();
        if (randomAcceptanceChance < modifyAcceptanceChance()) {
            acceptTrade();
            offeringTeam.setTradedPicks(tradeCharter.getDraftRoundIdx());
            TradeDraft.updateDraftTradeTracker(offeringTeam,requestedTeam, tradeCharter.getDraftRoundIdx());

        } else {
            rejectTrade();
        }
    }

    private void resolveAIToAITrade(){
        display.displayTradeStatistics(offeringTeam.getTeamName(), offeredPlayerList,
                requestedTeam.getTeamName(), requestedPlayerList);

        Float playerStrength1 = rosterSearch.getRosterStrength(offeredPlayerList);
        Float playerStrength2 = rosterSearch.getRosterStrength(requestedPlayerList);

        float randomAcceptanceChance = randomFloatGenerator.generateRandomFloat();
        if (randomAcceptanceChance < modifyAcceptanceChance()) {
            acceptTrade();
        } else if (playerStrength1 > playerStrength2) {
            acceptTrade();
        } else {
            rejectTrade();
        }
    }

    private void resolveAIToUserTrade(){
        display.displayTradeStatisticsToUser(offeringTeam.getTeamName(), offeredPlayerList, requestedTeam.getTeamName(), requestedPlayerList);
        display.displayAcceptRejectOptionToUser();

        int userInput = userInputCheck.validateUserTradeInput();
        if (userInput == 1) {
            acceptTrade();
        } else if (userInput == 0) {
            rejectTrade();
        }
    }

    private void acceptTrade() {
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

    private void rejectTrade() {
        display.showMessageOnConsole("Trade is declined.");
        resetLossPoints(offeringTeam);
    }
}
