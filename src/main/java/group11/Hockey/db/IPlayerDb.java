package group11.Hockey.db;

public interface IPlayerDb {
	public boolean insertLeagueFreeAgents(String leagueName, String freeAgentName, String position, float playerSkating,
			float playerShooting, float playerChecking, float playerSaving, int age);
}
