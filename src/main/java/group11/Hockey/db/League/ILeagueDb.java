package group11.Hockey.db.League;

import group11.Hockey.BusinessLogic.models.ILeague;

public interface ILeagueDb {

	public boolean insertLeagueInDb(ILeague league);
	
	public ILeague loadLeague();
}
