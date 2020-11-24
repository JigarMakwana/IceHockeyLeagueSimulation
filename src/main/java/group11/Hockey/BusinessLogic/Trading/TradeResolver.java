package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.IRandomNoGenerator;
import group11.Hockey.BusinessLogic.IUserInputCheck;
import group11.Hockey.BusinessLogic.IValidations;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeCharter;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeResolver;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradingConfig;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;

import java.util.ArrayList;
import java.util.List;

public class TradeResolver implements ITradeResolver {
    private ITeam offeringTeam;
    private List<IPlayer> offeredPlayerList;
    private ITeam requestedTeam;
    private List<IPlayer> requestedPlayerList;
    private ITradingConfig tradingConfig;
    private ICommandLineInput commandLineInput;
    private IValidations validation;
    private IDisplay display;

    public TradeResolver(ITradeCharter tradeCharter, ITradingConfig tradingConfig, ICommandLineInput commandLineInput, IValidations validation, IDisplay display){
        this.offeringTeam = tradeCharter.getOfferingTeam();
        this.offeredPlayerList = tradeCharter.getOfferedPlayerList();
        this.requestedTeam = tradeCharter.getRequestedTeam();
        this.requestedPlayerList = tradeCharter.getRequestedPlayerList();
        this.tradingConfig = tradingConfig;
        this.commandLineInput = commandLineInput;
        this.validation = validation;
        this.display = display;
    }

    @Override
    public void resolveTrade() {
        if (requestedTeam.isUserTeam()) {
            resolveAIToUserTrade();
        } else {
            resolveAIToAITrade();
        }
    }

    public void resolveAIToUserTrade(){
        display.displayTradeStatisticsToUser(offeringTeam.getTeamName(), offeredPlayerList, requestedTeam.getTeamName(), requestedPlayerList);
        display.displayAcceptRejectOptionToUser();

        IUserInputCheck userInputCheck = DefaultHockeyFactory.makeUserInputCheck(commandLineInput, validation, display);

        int userInput = userInputCheck.validateUserTradeInput();
        if (userInput == 1) {
            acceptTrade();
        } else if (userInput == 0) {
            rejectTrade();
        }
    }

    public void resolveAIToAITrade(){
        display.displayTradeStatistics(offeringTeam.getTeamName(), offeredPlayerList, requestedTeam.getTeamName(), requestedPlayerList);

        IRosterSearch rosterSearch = DefaultHockeyFactory.makeRosterSearch();
        Float playerStrength1 = rosterSearch.playersStrengthSum(offeredPlayerList);
        Float playerStrength2 = rosterSearch.playersStrengthSum(requestedPlayerList);

        IRandomNoGenerator randomFloatGenerator = DefaultHockeyFactory.makeRandomFloatGenerator();
        float randomAcceptanceChance = randomFloatGenerator.generateRandomFloat();
        if (randomAcceptanceChance < this.tradingConfig.getRandomAcceptanceChance()) {
            acceptTrade();
        } else if (playerStrength1 > playerStrength2) {
            acceptTrade();
        } else {
            rejectTrade();
        }
    }


    @Override
    public void acceptTrade() {
        List<IPlayer> localOfferedPlayerList = new ArrayList<>();
        for (IPlayer p : offeredPlayerList) {
            localOfferedPlayerList.add(p);
        }
        List<IPlayer> localRequestedPlayerList = new ArrayList<>();
        for (IPlayer p : requestedPlayerList) {
            localRequestedPlayerList.add(p);
        }

        for (IPlayer toBeRemoved : localOfferedPlayerList) {
            offeredPlayerList.removeIf(player -> player.getPlayerName().equals(toBeRemoved.getPlayerName()));
        }
        for (IPlayer toBeRemoved : localRequestedPlayerList) {
            requestedPlayerList.removeIf(player -> player.getPlayerName().equals(toBeRemoved.getPlayerName()));
        }
        for (IPlayer toBeAdded : localRequestedPlayerList) {
            offeredPlayerList.add(toBeAdded);
        }
        for (IPlayer toBeAdded : localOfferedPlayerList) {
            requestedPlayerList.add(toBeAdded);
        }
        resetLossPoints(offeringTeam);
        resetLossPoints(requestedTeam);
        display.showMessageOnConsole("Trade successfully accepted!");
    }

    @Override
    public void rejectTrade() {
        display.showMessageOnConsole("Trade is declined.");
        resetLossPoints(offeringTeam);
    }

    @Override
    public void resetLossPoints(ITeam team) {
        team.setLosses(0);
    }
}
