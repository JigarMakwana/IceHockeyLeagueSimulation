/*
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.*;
import group11.Hockey.BusinessLogic.Trading.Interfaces.*;
import group11.Hockey.BusinessLogic.models.*;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;

import java.util.List;

public class TradingFactory {

    public TradingFactory() {
        super();
    }

    public static ITradeInitializer makeTradeInitializer(ILeague leagueObj){
        return new TradeInitializer(leagueObj);
    }

    public static ITradeGenerator makeTradeGenerator(Team team, ITradeConfig tradingConfig, IDisplay display){
        return new TradeGenerator(team, tradingConfig, display);
    }

    public static ITradeResolver makeTradeResolver(ITradeCharter tradeCharter, ITradeConfig tradingConfig, ICommandLineInput commandLineInput,
                                                   IValidations validation, IDisplay display){
        return new TradeResolver(tradeCharter, tradingConfig, commandLineInput, validation, display);
    }

    public static ITradeSettler makeTradeSettler(Team team, List<Player> freeAgentList, ICommandLineInput commandLineInput, IValidations validation,
                                                 IDisplay display, IConstantSupplier constantSupplier){
        return new TradeSettler(team, freeAgentList, commandLineInput, validation, display, constantSupplier);
    }

    public static ITradeCharter makeTradeCharter(Team offeringTeam, List<Player> offeredPlayerList, Team requestedteam,
                                                 List<Player> requestedPlayerList, int roundIdx){
        return new TradeCharter(offeringTeam, offeredPlayerList, requestedteam, requestedPlayerList, roundIdx);
    }

    public static ITradeConfig makeTradeConfig(int lossPoint, float randomTradeOfferChance, int maxPlayersPerTrade,
                                                   float randomAcceptanceChance, IgmTable gmTable){
        return new TradeConfig(lossPoint, randomTradeOfferChance, maxPlayersPerTrade, randomAcceptanceChance,gmTable);
    }

    public static ITradeDraft makeTradeDraft(Team offeringTeam, ITradeConfig tradingConfig, IDisplay display){
        return new TradeDraft(offeringTeam, tradingConfig, display);
    }
}
