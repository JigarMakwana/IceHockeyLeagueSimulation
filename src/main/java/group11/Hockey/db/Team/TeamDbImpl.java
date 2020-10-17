package group11.Hockey.db.Team;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import group11.Hockey.db.ProcedureCallDb;
import group11.Hockey.models.Conference;
import group11.Hockey.models.Division;
import group11.Hockey.models.League;
import group11.Hockey.models.Team;

public class TeamDbImpl implements ITeamDb {

	@Override
	public List<League> loadTeamFromTeamName(String teamName) {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb("{call findTeamByTeamName(?)}");
		CallableStatement statement = procedureCallDb.getDBCallableStatement();
		List<League> leagueList = new ArrayList<League>();
		try {
			statement.setString(1, teamName);
			procedureCallDb.executeProcedure();
			ResultSet resultSet = statement.getResultSet();
			while (resultSet.next()) {
				//Team team = new Team(resultSet.getString("team_name"), resultSet.getString("general_manager"),
						//resultSet.getString("head_coach"), null);
				//Division divison = new Division(resultSet.getString("division_name"), Arrays.asList(team));
				//Conference conference = new Conference(resultSet.getString("conference_name"), Arrays.asList(divison));
				//League league = new League(resultSet.getString("league_name"), Arrays.asList(conference), null);
				//leagueList.add(league);
			}
			statement.close();
			procedureCallDb.closeConnection();
		} catch (Exception e) {
			procedureCallDb.closeConnection();
			System.out.println("Exception occured while getting the callable statment ");
		} finally {

			procedureCallDb.closeConnection();
		}

		return leagueList;
	}

}
