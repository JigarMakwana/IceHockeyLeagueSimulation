/*
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading.TradingInterfaces;

import java.util.List;

import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Team;

public interface ITradeCharter {
    List<IPlayer> getOfferedPlayerList();
    List<IPlayer> getRequestedPlayerList();
    ITeam getOfferingTeam();
    ITeam getRequestedTeam();
    int getDraftRoundIdx();
    boolean isCharterValid();
    boolean isDraftTradeCharter();
}
