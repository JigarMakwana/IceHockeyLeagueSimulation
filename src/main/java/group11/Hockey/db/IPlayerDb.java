package group11.Hockey.db;

public interface IPlayerDb {
	public boolean insertLeagueFreeAgents(String leagueName, String freeAgentName, String position, float playerSkating,
			float playerShooting, float playerChecking, float playerSaving, float age);
	
	public boolean insertLeagueRetiredPlayers(String leagueName, String playerName, String position, float playerSkating,
			float playerShooting, float playerChecking, float playerSaving, float age);
	
	public boolean deleteLeaguePlayers(String leagueName);
}
