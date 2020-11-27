/*
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.IConstantSupplier;
import group11.Hockey.BusinessLogic.IValidations;
import group11.Hockey.BusinessLogic.Trading.Interfaces.*;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;

import java.util.List;

public class TradeRunner implements ITradeRunner {
    private ILeague leagueObj;
    private ICommandLineInput commandLineInput;
    private IValidations validation;
    private IDisplay display;
    private IConstantSupplier constantSupplier;

    public TradeRunner(ILeague leagueObj, ICommandLineInput commandLineInput, IValidations validation,
                       IDisplay display, IConstantSupplier constantSupplier){
        this.leagueObj = leagueObj;
        this.commandLineInput = commandLineInput;
        this.validation = validation;
        this.display = display;
        this.constantSupplier = constantSupplier;
    }

    @Override
    public void runTrading() {
        ITradeInitializer tradeInitializer = TradingFactory.makeTradeInitializer(leagueObj);
        List<Team> eligibleTeamList = tradeInitializer.getEligibleTeams();
        ITradeConfig tradingConfig = tradeInitializer.getTradingConfig();

        for (int i = 0; eligibleTeamList.size() > 1; i = 0){
            if(tradeInitializer.isTradePossible(eligibleTeamList.get(i))){
                ITradeGenerator tradeGenerator = TradingFactory.makeTradeGenerator(eligibleTeamList.get(i),tradingConfig, display);
                ITradeCharter tradeCharter = tradeGenerator.generateTradeOffer(eligibleTeamList);

                ITradeResolver tradeResolver = TradingFactory.makeTradeResolver(tradeCharter, tradingConfig, commandLineInput, validation, display);
                tradeResolver.resolveTrade();

                ITradeSettler offeringTeamSettler = TradingFactory.makeTradeSettler(tradeCharter.getOfferingTeam(), (List<Player>) leagueObj.getFreeAgents(), commandLineInput, validation, display, constantSupplier);
                offeringTeamSettler.settleTeam();

                ITradeSettler requestedTeamSettler = TradingFactory.makeTradeSettler(tradeCharter.getRequestedTeam(), (List<Player>) leagueObj.getFreeAgents(), commandLineInput, validation, display, constantSupplier);
                requestedTeamSettler.settleTeam();
            }
            eligibleTeamList.remove(0);
        }
    }
}
