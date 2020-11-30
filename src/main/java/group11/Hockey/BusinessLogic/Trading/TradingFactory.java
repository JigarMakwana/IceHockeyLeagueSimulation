/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.*;
import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.*;
import group11.Hockey.BusinessLogic.models.*;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.League.ILeagueDb;

import java.util.List;

public class TradingFactory {

    public static StateMachineState makeTradeRunner(ILeague leagueObj, ILeagueDb leagueDb, ICommandLineInput commandLineInput, IValidations validation, IDisplay display ){
        return new TradeRunner(leagueObj, leagueDb, commandLineInput, validation, display);
    }

    public static ITradeInitializer makeTradeInitializer(ILeague leagueObj){
        return new TradeInitializer(leagueObj);
    }

    public static ITradeGenerator makeTradeGenerator(ITeam team, ITradeConfig tradingConfig, IDisplay display){
        return new TradeGenerator(team, tradingConfig, display);
    }

    public static ITradeResolver makeTradeResolver(ILeague leagueObj, ITradeCharter tradeCharter, ITradeConfig tradingConfig, ICommandLineInput commandLineInput,
                                                   IValidations validation, IDisplay display){
        return new TradeResolver(leagueObj, tradeCharter, tradingConfig, commandLineInput, validation, display);
    }

    public static ITradeSettler makeTradeSettler(ITeam team, List<IPlayer> list, ICommandLineInput commandLineInput, IValidations validation,
                                                 IDisplay display){
        return new TradeSettler(team, list, commandLineInput, validation, display);
    }

    public static ITradeCharter makeTradeCharter(ITeam offeringTeam, List<IPlayer> offeredPlayerList, ITeam requestedteam,
                                                 List<IPlayer> requestedPlayerList, int roundIdx){
        return new TradeCharter(offeringTeam, offeredPlayerList, requestedteam, requestedPlayerList, roundIdx);
    }

    public static ITradeConfig makeTradeConfig(int lossPoint, float randomTradeOfferChance, int maxPlayersPerTrade,
                                               float randomAcceptanceChance, IgmTable gmTable){
        return new TradeConfig(lossPoint, randomTradeOfferChance, maxPlayersPerTrade, randomAcceptanceChance,gmTable);
    }

    public static ITradeGenerator makeTradeDraft(ITeam offeringTeam, ITradeConfig tradingConfig, IDisplay display){
        return new TradeDraft(offeringTeam, tradingConfig, display);
    }
}
