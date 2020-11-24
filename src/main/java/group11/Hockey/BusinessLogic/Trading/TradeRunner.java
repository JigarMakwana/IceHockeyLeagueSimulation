package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.IConstantSupplier;
import group11.Hockey.BusinessLogic.IValidations;
import group11.Hockey.BusinessLogic.Trading.Interfaces.*;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
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
        ITradeInitializer tradeInitializer = DefaultHockeyFactory.makeTradeInitializer(leagueObj );
        List<ITeam> eligibleTeamList = tradeInitializer.getEligibleTeams();
        ITradingConfig tradingConfig = tradeInitializer.getTradingConfig();

        for (int i = 0; eligibleTeamList.size() > 1; i = 0){
            if(tradeInitializer.isTradePossible(eligibleTeamList.get(i))){
                ITradeGenerator tradeGenerator = DefaultHockeyFactory.makeTradeGenerator
                        (eligibleTeamList.get(i),tradingConfig, display);
                ITradeCharter tradeCharter = tradeGenerator.generateTradeOffer(eligibleTeamList);

                ITradeResolver tradeResolver = DefaultHockeyFactory.makeTradeResolver(tradeCharter, tradingConfig,
                        commandLineInput, validation, display);
                tradeResolver.resolveTrade();

                ITradeSettler offeringTeamSettler = DefaultHockeyFactory.makeTradeSettler(tradeCharter.getOfferingTeam(),
                        (List<IPlayer>) leagueObj.getFreeAgents(), commandLineInput, validation, display, constantSupplier);
                offeringTeamSettler.settleTeamAfterTrade();
                ITradeSettler requestedTeamSettler = DefaultHockeyFactory.makeTradeSettler(tradeCharter.getRequestedTeam(),
                        (List<IPlayer>) leagueObj.getFreeAgents(), commandLineInput, validation, display, constantSupplier);
                requestedTeamSettler.settleTeamAfterTrade();
            }
            eligibleTeamList.remove(0);
        }
    }
}
