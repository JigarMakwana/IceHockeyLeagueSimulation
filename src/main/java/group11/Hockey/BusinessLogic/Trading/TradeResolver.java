package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.IRandomNoGenerator;
import group11.Hockey.BusinessLogic.IUserInputCheck;
import group11.Hockey.BusinessLogic.IValidations;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeCharter;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeGenerator;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeResolver;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradingConfig;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;

import java.util.ArrayList;
import java.util.List;

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
        if(tradeCharter.isCharterValid()){
            if (requestedTeam.isUserTeam()) {
                resolveAIToUserTrade();
            } else {
                resolveAIToAITrade();
            }
        }
        return;
    }

    @Override
    public void resetLossPoints(Team team) {
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

    private void resolveAIToAITrade(){
        display.displayTradeStatistics(offeringTeam.getTeamName(), offeredPlayerList,
                requestedTeam.getTeamName(), requestedPlayerList);

        Float playerStrength1 = rosterSearch.playersStrengthSum(offeredPlayerList);
        Float playerStrength2 = rosterSearch.playersStrengthSum(requestedPlayerList);

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
        List<Player> localOfferedPlayerList = new ArrayList<>();
        for (Player p : offeredPlayerList) {
            localOfferedPlayerList.add(p);
        }
        List<Player> localRequestedPlayerList = new ArrayList<>();
        for (Player p : requestedPlayerList) {
            localRequestedPlayerList.add(p);
        }

        for (Player toBeRemoved : localOfferedPlayerList) {
            offeringTeam.getPlayers().removeIf(player -> player.getPlayerName().equals(toBeRemoved.getPlayerName()));
        }
        for (Player toBeRemoved : localRequestedPlayerList) {
            requestedTeam.getPlayers().removeIf(player -> player.getPlayerName().equals(toBeRemoved.getPlayerName()));
        }
        for (Player toBeAdded : localRequestedPlayerList) {
            offeringTeam.getPlayers().add(toBeAdded);
        }
        for (Player toBeAdded : localOfferedPlayerList) {
            requestedTeam.getPlayers().add(toBeAdded);
        }
        resetLossPoints(offeringTeam);
        display.showMessageOnConsole("Trade successfully accepted!");
    }

    private void rejectTrade() {
        display.showMessageOnConsole("Trade is declined.");
        resetLossPoints(offeringTeam);
    }
}
