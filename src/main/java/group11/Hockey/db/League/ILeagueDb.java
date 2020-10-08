package group11.Hockey.db.League;

public interface ILeagueDb {
	public boolean checkLeagueNameExitsInDb(String leagueName);

	public boolean insertLeagueInDb(String leagueName, String conferenceName, String divisionName, String teamName,
			String generalManger, String headCoach, String playerName, String playerPosition, Boolean captain);
}
