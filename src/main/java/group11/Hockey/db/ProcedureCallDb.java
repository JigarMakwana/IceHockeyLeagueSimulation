package group11.Hockey.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import group11.Hockey.models.Conference;
import group11.Hockey.models.Division;
import group11.Hockey.models.League;
import group11.Hockey.models.Team;

public class ProcedureCallDb {

	private String procedureName;

	public ProcedureCallDb(String procedureName) {
		this.procedureName = procedureName;
	}

	public boolean procCallForInsertLeague(String leagueName, String conferenceName, String divisionName,
			String teamName, String generalManager, String headCoach, String playerName, String position, Boolean captain) {
		Connection connection = null;
		ConnectionUtil connectionUtil = new ConnectionUtil();
		boolean outPutValue = false;
		try {
			connection = connectionUtil.getConnection();
			CallableStatement statement = null;
			try {
				statement = connection.prepareCall(this.procedureName);
				statement.setString(1, leagueName);
				statement.setString(2, conferenceName);
				statement.setString(3, divisionName);
				statement.setString(4, teamName);
				statement.setString(5, generalManager);
				statement.setString(6, headCoach);
				statement.setString(7, playerName);
				statement.setString(8, position);
				statement.setString(9, captain != null ? captain.toString() : null);
				statement.registerOutParameter(10, Types.BOOLEAN);
				statement.execute();
				outPutValue = statement.getBoolean(10);

				statement.close();
				connectionUtil.closeConnection(connection);
			} catch (Exception e) {
				connectionUtil.closeConnection(connection);
			} finally {
				connectionUtil.closeConnection(connection);
			}

		} catch (Exception e) {
			connectionUtil.closeConnection(connection);
		}
		return outPutValue;
	}

	public List<League> procCallForLoadTeamDetails(String teamName) {
		Connection connection = null;
		ConnectionUtil connectionUtil = new ConnectionUtil();
		List<League> leagueList = new ArrayList<League>();
		try {
			connection = connectionUtil.getConnection();
			CallableStatement statement = null;
			try {
				statement = connection.prepareCall(this.procedureName);
				statement.setString(1, teamName);
				if (statement.execute()) {
					ResultSet resultSet = statement.getResultSet();
					while (resultSet.next()) {
						Team team = new Team(resultSet.getString("team_name"), resultSet.getString("general_manager"),
								resultSet.getString("head_coach"), null);
						Division divison = new Division(resultSet.getString("division_name"), Arrays.asList(team));
						Conference conference = new Conference(resultSet.getString("conference_name"),
								Arrays.asList(divison));
						League league = new League(resultSet.getString("league_name"), Arrays.asList(conference), null);
						leagueList.add(league);
					}
				}
			} catch (Exception e) {
				connectionUtil.closeConnection(connection);
			} finally {
				connectionUtil.closeConnection(connection);
			}

		} catch (Exception e) {
			connectionUtil.closeConnection(connection);
		}
		return leagueList;

	}
	
	public boolean procCallForInsertFreeAgents(String leagueName, String freeAgentName, String position, Boolean captain) {
		Connection connection = null;
		ConnectionUtil connectionUtil = new ConnectionUtil();
		boolean outPutValue = false;
		try {
			connection = connectionUtil.getConnection();
			CallableStatement statement = null;
			try {
				statement = connection.prepareCall(this.procedureName);
				statement.setString(1, leagueName);
				statement.setString(2, freeAgentName);
				statement.setString(3, position);
				statement.setString(4, captain != null ? captain.toString() : null);
				statement.registerOutParameter(5, Types.BOOLEAN);
				statement.execute();
				outPutValue = statement.getBoolean(5);

				statement.close();
				connectionUtil.closeConnection(connection);
			} catch (Exception e) {
				connectionUtil.closeConnection(connection);
			} finally {
				connectionUtil.closeConnection(connection);
			}

		} catch (Exception e) {
			connectionUtil.closeConnection(connection);
		}
		return outPutValue;
	}
	

	public boolean procedureCallForLeagueExistsCheck(String leagueName) {
		Connection connection = null;
		ConnectionUtil connectionUtil = new ConnectionUtil();
		boolean isLeagueNameValid = true;
		try {
			connection = connectionUtil.getConnection();
			CallableStatement statement = null;
			try {
				statement = connection.prepareCall(this.procedureName);
				statement.setString(1, leagueName);
				if (statement.execute()) {
					ResultSet resultSet = statement.getResultSet();
					while (resultSet.next()) {
						if (resultSet.getString("league_name").equalsIgnoreCase(leagueName)) {
							isLeagueNameValid = false;
							break;
						}
					}
				}
			}

			catch (Exception e) {
				connectionUtil.closeConnection(connection);
			} finally {
				connectionUtil.closeConnection(connection);
			}
		} catch (Exception e) {
			connectionUtil.closeConnection(connection);
		}
		return isLeagueNameValid;
	}
}
