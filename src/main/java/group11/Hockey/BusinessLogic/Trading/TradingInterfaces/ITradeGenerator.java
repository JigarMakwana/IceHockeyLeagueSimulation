/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading.TradingInterfaces;

import java.util.List;

import group11.Hockey.BusinessLogic.models.ITeam;

public interface ITradeGenerator {
    ITradeCharter generateTradeOffer(List<ITeam> teamList);
}
