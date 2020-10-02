package group11.Hockey.db.League;

public interface ILeagueDb {
	public boolean checkLeagueNameExitsInDb(String leagueName);
	public int insertLeagueInDb(String leagueName);
}
