package group11.Hockey.db.League;

public interface ILeagueDb {
	public boolean checkLeagueNameExitsInDb(String leagueName);

	public boolean insertLeagueInDb(String leagueName, String conferenceName, String divisionName, String teamName,
			String generalManger, String headcoachName, float skating, float shooting, float checking, float saving,
			String playerName, String playerPosition, boolean captain, float playerSkating, float playerShooting,
			float playerChecking, float playerSaving, int age);

	public boolean insertLeagueFreeAgents(String leagueName, String freeAgentName, String position, float playerSkating,
			float playerShooting, float playerChecking, float playerSaving, int age);
}
