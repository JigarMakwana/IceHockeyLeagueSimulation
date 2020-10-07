package group11.Hockey.db.League;

import group11.Hockey.db.ProcedureCallDb;

public class LeagueDbImpl implements ILeagueDb {

	@Override
	public boolean insertLeagueInDb(String leagueName, String conferenceName, String divisionName, String teamName,
			String generalManager, String headCoach) {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb("{call insertNew(?, ?, ?, ?, ?, ?, ?)}");
		return procedureCallDb.procCallForInsertLeague(leagueName, conferenceName, divisionName, teamName,
				generalManager, headCoach);

	}

	@Override
	public boolean checkLeagueNameExitsInDb(String leagueName) {

		return false;
	}

}
