package group11.Hockey.db.Team;

import java.util.List;

import group11.Hockey.db.ProcedureCallDb;
import group11.Hockey.models.League;

public class TeamDbImpl implements ITeamDb {

	@Override
	public int insertTeamInDb(String teamName, String generalMangerName, String coachName, int divisionId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<League> loadTeamFromTeamName(String teamName) {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb("{call findTeamByTeamName(?)}");
		return procedureCallDb.procCallForLoadTeamDetails(teamName);
	}

}
