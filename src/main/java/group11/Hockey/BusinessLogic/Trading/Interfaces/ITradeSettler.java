package group11.Hockey.BusinessLogic.Trading.Interfaces;

import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.Player;

import java.util.List;

public interface ITradeSettler {
   void settleTeam();
   void dropPlayers();
   void hirePlayers();
   public void dropPlayerFromUserTeam(List<IPlayer> playerList);
   void hirePlayerInUserTeam(List<IPlayer> sortedFreeAgents) throws Exception;
}
