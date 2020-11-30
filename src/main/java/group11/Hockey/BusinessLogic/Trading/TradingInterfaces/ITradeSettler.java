/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading.TradingInterfaces;

import group11.Hockey.BusinessLogic.models.Player;

import java.util.List;

public interface ITradeSettler {
   void settleTeam();
   void dropPlayers();
   void hirePlayers();
}
