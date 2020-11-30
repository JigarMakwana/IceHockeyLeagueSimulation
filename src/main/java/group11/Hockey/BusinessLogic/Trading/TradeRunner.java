/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading;

import java.util.List;

import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.BusinessLogic.Aging.AgePlayer;
import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.*;
import group11.Hockey.BusinessLogic.Validations.IValidations;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.League.ILeagueDb;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TradeRunner extends StateMachineState implements ITradeRunner {
    private ILeague leagueObj;
    private ILeagueDb leagueDb;
    private ICommandLineInput commandLineInput;
    private IValidations validation;
    private IDisplay display;
    private static Logger logger = LogManager.getLogger(TradeRunner.class);

    public TradeRunner(ILeague leagueObj, ILeagueDb leagueDb, ICommandLineInput commandLineInput, IValidations validation, IDisplay display){
        this.leagueObj = leagueObj;
        this.leagueDb = leagueDb;
        this.commandLineInput = commandLineInput;
        this.validation = validation;
        this.display = display;
    }

    @Override
    public StateMachineState startState() {
        this.runTrading();
        return new AgePlayer(leagueObj, leagueDb, display, commandLineInput, validation);
    }

    @SuppressWarnings("unchecked")
	@Override
    public void runTrading() {
        logger.debug("Entered runTrading()");
        logger.debug("Running Trade Life Cycle: Initialize -> Generate -> Resolve -> Settle Team");
        ITradeInitializer tradeInitializer = TradingFactory.makeTradeInitializer(leagueObj);
        List<ITeam> eligibleTeamList = tradeInitializer.getEligibleTeams();
        ITradeConfig tradingConfig = tradeInitializer.getTradingConfig();

        for (int i = 0; eligibleTeamList.size() > 1; i = 0){
            if(tradeInitializer.isTradePossible(eligibleTeamList.get(i))){
                ITradeGenerator tradeGenerator = TradingFactory.makeTradeGenerator(eligibleTeamList.get(i),tradingConfig, display);
                ITradeCharter tradeCharter = tradeGenerator.generateTradeOffer(eligibleTeamList);

                ITradeResolver tradeResolver = TradingFactory.makeTradeResolver(leagueObj, tradeCharter, tradingConfig, commandLineInput, validation, display);
                tradeResolver.resolveTrade();

                ITradeSettler offeringTeamSettler = TradingFactory.makeTradeSettler(tradeCharter.getOfferingTeam(), (List<IPlayer>) leagueObj.getFreeAgents(), commandLineInput, validation, display);
                offeringTeamSettler.settleTeam();

                ITradeSettler requestedTeamSettler = TradingFactory.makeTradeSettler(tradeCharter.getRequestedTeam(), (List<IPlayer>) leagueObj.getFreeAgents(), commandLineInput, validation, display);
                requestedTeamSettler.settleTeam();
            }
            eligibleTeamList.remove(0);
        }
    }
}
