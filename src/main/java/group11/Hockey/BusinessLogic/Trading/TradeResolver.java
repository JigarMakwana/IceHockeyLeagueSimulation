package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.IRandomNoGenerator;
import group11.Hockey.BusinessLogic.IUserInputCheck;
import group11.Hockey.BusinessLogic.IValidations;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeCharter;
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
        if(this.tradeCharter.isCharterValid()){
            if (this.requestedTeam.isUserTeam()) {
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
        String gmPersonality = this.offeringTeam.getGeneralManager().getPersonality();
        if(gmPersonality.equalsIgnoreCase(GMPersonalities.SHREWD.toString())){
            modfiedChance = this.tradingConfig.getGmTable().getShrewd();
        } else if(gmPersonality.equalsIgnoreCase(GMPersonalities.NORMAL.toString())){
            modfiedChance = this.tradingConfig.getGmTable().getNormal();
        } else if(gmPersonality.equalsIgnoreCase(GMPersonalities.GAMBLER.toString())){
            modfiedChance = this.tradingConfig.getGmTable().getGambler();
        }
        return this.tradingConfig.getRandomAcceptanceChance() + modfiedChance;
    }

    private void resolveAIToAITrade(){
        this.display.displayTradeStatistics(this.offeringTeam.getTeamName(), this.offeredPlayerList,
                this.requestedTeam.getTeamName(), this.requestedPlayerList);

        Float playerStrength1 = this.rosterSearch.playersStrengthSum(this.offeredPlayerList);
        Float playerStrength2 = this.rosterSearch.playersStrengthSum(this.requestedPlayerList);

        float randomAcceptanceChance = this.randomFloatGenerator.generateRandomFloat();
        if (randomAcceptanceChance < modifyAcceptanceChance()) {
            acceptTrade();
        } else if (playerStrength1 > playerStrength2) {
            acceptTrade();
        } else {
            rejectTrade();
        }
    }

    private void resolveAIToUserTrade(){
        this.display.displayTradeStatisticsToUser(this.offeringTeam.getTeamName(), this.offeredPlayerList, this.requestedTeam.getTeamName(), this.requestedPlayerList);
        this.display.displayAcceptRejectOptionToUser();

        int userInput = this.userInputCheck.validateUserTradeInput();
        if (userInput == 1) {
            acceptTrade();
        } else if (userInput == 0) {
            rejectTrade();
        }
    }

    private void acceptTrade() {
        List<Player> localOfferedPlayerList = new ArrayList<>();
        for (Player p : this.offeredPlayerList) {
            localOfferedPlayerList.add(p);
        }
        List<Player> localRequestedPlayerList = new ArrayList<>();
        for (Player p : this.requestedPlayerList) {
            localRequestedPlayerList.add(p);
        }

        for (Player toBeRemoved : localOfferedPlayerList) {
            this.offeringTeam.getPlayers().removeIf(player -> player.getPlayerName().equals(toBeRemoved.getPlayerName()));
        }
        for (Player toBeRemoved : localRequestedPlayerList) {
            this.requestedTeam.getPlayers().removeIf(player -> player.getPlayerName().equals(toBeRemoved.getPlayerName()));
        }
        for (Player toBeAdded : localRequestedPlayerList) {
            this.offeringTeam.getPlayers().add(toBeAdded);
        }
        for (Player toBeAdded : localOfferedPlayerList) {
            this.requestedTeam.getPlayers().add(toBeAdded);
        }
        resetLossPoints(this.offeringTeam);
        this.display.showMessageOnConsole("Trade successfully accepted!");
    }

    private void rejectTrade() {
        this.display.showMessageOnConsole("Trade is declined.");
        resetLossPoints(this.offeringTeam);
    }
}
