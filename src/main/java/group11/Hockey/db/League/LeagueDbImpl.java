package group11.Hockey.db.League;

import group11.Hockey.db.ProcedureCallDb;

public class LeagueDbImpl implements ILeagueDb {

	@Override
	public boolean insertLeagueInDb(String leagueName, String conferenceName, String divisionName, String teamName,
			String generalManger, String headCoach, String playerName, String playerPosition, Boolean captain) {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb("{call insertNew(?, ?, ?, ?, ?, ?, ?)}");
		return procedureCallDb.procCallForInsertLeague(leagueName, conferenceName, divisionName, teamName,
				generalManger, headCoach, playerName, playerPosition, captain);

	}

	@Override
	public boolean checkLeagueNameExitsInDb(String leagueName) {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb("{call isLeagueExists(?)}");
		return procedureCallDb.procedureCallForLeagueExistsCheck(leagueName);
	}

}
