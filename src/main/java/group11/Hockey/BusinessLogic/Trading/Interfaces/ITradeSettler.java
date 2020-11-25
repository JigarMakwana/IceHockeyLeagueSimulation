package group11.Hockey.BusinessLogic.Trading.Interfaces;

import group11.Hockey.BusinessLogic.models.Player;

import java.util.List;

public interface ITradeSettler {
   void settleTeamAfterTrade();
   void dropPlayers();
   void hirePlayers();
   void dropPlayerFromUserTeam(List<Player> playerList);
   void hirePlayerInUserTeam(List<Player> sortedFreeAgents) throws Exception;
}
