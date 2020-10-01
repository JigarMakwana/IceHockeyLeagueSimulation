package group11.Hockey.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import group11.Hockey.models.Team;

public class TeamDbImpl implements ITeamDb {

	@Override
	public Team findTeamDetails(String teamName, String generalMangerName, String headCoachName, String leagueName) {
		ConnectionUtil connectionUtil = new ConnectionUtil();
		Connection connection = connectionUtil.getConnection();
		CallableStatement statement = null;
		Team teamFetchedFromDb = null;
		try {
			String procedureCall = "call findTeamWith_TeamName_ManagerName_CoachName";
			statement = connection.prepareCall(procedureCall);
			statement.setString(1, teamName);
			statement.setString(2, generalMangerName);
			statement.setString(3, headCoachName);
			statement.setString(4, leagueName);

			statement.registerOutParameter(5, Types.VARCHAR);
			statement.registerOutParameter(6, Types.VARCHAR);
			statement.registerOutParameter(7, Types.VARCHAR);
			statement.execute();

			teamFetchedFromDb = new Team();
			teamFetchedFromDb.setTeamName(statement.getNString(5));
			teamFetchedFromDb.setGeneralManager(statement.getNString(6));
			teamFetchedFromDb.setHeadCoach(statement.getNString(7));
			statement.close();
			connectionUtil.closeConnection(connection);
		} catch (Exception e) {
			connectionUtil.closeConnection(connection);
		} finally {
			connectionUtil.closeConnection(connection);
		}
		return teamFetchedFromDb;
	}

}
