package group11.Hockey.db.Team;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import group11.Hockey.db.ConnectionUtil;
import group11.Hockey.models.Conference;
import group11.Hockey.models.Division;
import group11.Hockey.models.League;
import group11.Hockey.models.Team;

public class TeamDbImpl implements ITeamDb {

	@Override
	public int insertTeamInDb(String teamName, String generalMangerName, String coachName, int divisionId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<League> loadTeamFromTeamName(String teamName) {
		List<League> leagueList = new ArrayList<League>();
		ConnectionUtil connectionUtil = new ConnectionUtil();
		Connection connection = connectionUtil.getConnection();
		CallableStatement statement = null;
		try {
			String procedureCall = "call loadDataFromTeamName";
			statement = connection.prepareCall(procedureCall);
			statement.setString(1, teamName);
			if (statement.execute()) {
				ResultSet resultSet = statement.getResultSet();
				while (resultSet.next()) {

					Team team = new Team();
					team.setTeamName(resultSet.getString("TeamName"));
					List<Team> teamList = new ArrayList<Team>();
					teamList.add(team);
					Division division = new Division(resultSet.getString("DivisionName"), teamList);
					List<Division> divisionList = new ArrayList<Division>();
					divisionList.add(division);
					Conference conference = new Conference(resultSet.getString("ConferenceName"), divisionList);
					List<Conference> conferenceList = new ArrayList<Conference>();
					conferenceList.add(conference);

					League league = new League(resultSet.getString("LeagueName"), conferenceList, null);
					leagueList.add(league);

				}
			}

			statement.close();
			connectionUtil.closeConnection(connection);
		} catch (Exception e) {
			connectionUtil.closeConnection(connection);
		} finally {
			connectionUtil.closeConnection(connection);
		}
		return leagueList;

	}

}
