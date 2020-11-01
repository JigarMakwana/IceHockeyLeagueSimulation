package group11.Hockey.db;

import group11.Hockey.BusinessLogic.models.IPlayer;

public interface IPlayerDb {
	public boolean insertLeagueFreeAgents(String leagueName, IPlayer freeAgent);
	
	public boolean insertLeagueRetiredPlayers(String leagueName, IPlayer retiredPlayer);
	
	public boolean deleteLeaguePlayers(String leagueName);
}
