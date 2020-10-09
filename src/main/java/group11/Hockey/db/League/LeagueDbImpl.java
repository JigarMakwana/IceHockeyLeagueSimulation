package group11.Hockey.db.League;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import group11.Hockey.db.ProcedureCallDb;

public class LeagueDbImpl implements ILeagueDb {

	@Override
	public boolean insertLeagueInDb(String leagueName, String conferenceName, String divisionName, String teamName,
			String generalManger, String headCoach, String playerName, String playerPosition, Boolean captain) {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb("{call insertNew_original(?, ?, ?, ?, ?, ?, ?, ?,?,?)}");
		CallableStatement statement = procedureCallDb.getDBCallableStatement();
		boolean outPutValue = false;
		try {
			statement.setString(1, leagueName);
			statement.setString(2, conferenceName);
			statement.setString(3, divisionName);
			statement.setString(4, teamName);
			statement.setString(5, generalManger);
			statement.setString(6, headCoach);
			statement.setString(7, playerName);
			statement.setString(8, playerPosition);
			statement.setString(9, captain != null ? captain.toString() : null);
			procedureCallDb.executeProcedure();
			ResultSet resultSet = statement.getResultSet();
			while (resultSet.next()) {
				outPutValue = resultSet.getBoolean("status");
			}
			statement.close();
			procedureCallDb.closeConnection();
		} catch (Exception e) {
			procedureCallDb.closeConnection();
			System.out.println("Exception occured while getting the callable statment ");
		} finally {

			procedureCallDb.closeConnection();
		}
		return outPutValue;

	}

	@Override
	public boolean checkLeagueNameExitsInDb(String leagueName) {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb("{call isLeagueExists(?)}");
		CallableStatement statement = procedureCallDb.getDBCallableStatement();
		boolean isLeagueNameValid = true;
		try {
			statement.setString(1, leagueName);
			procedureCallDb.executeProcedure();
			ResultSet resultSet = statement.getResultSet();
			while (resultSet.next()) {
				if (resultSet.getString("league_name").equalsIgnoreCase(leagueName)) {
					isLeagueNameValid = false;
					break;
				}
			}
			statement.close();
			procedureCallDb.closeConnection();
		} catch (Exception e) {
			procedureCallDb.closeConnection();
			System.out.println("Exception occured while getting the callable statment ");
		} finally {

			procedureCallDb.closeConnection();
		}
		return isLeagueNameValid;
	}

	@Override
	public boolean insertLeagueFreeAgents(String leagueName, String freeAgentName, String position, Boolean captain) {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb("{call insertFreeAgent(?, ?, ?, ?, ?)}");
		CallableStatement statement = procedureCallDb.getDBCallableStatement();
		boolean outPutValue = false;
		try {
			statement.setString(1, leagueName);
			statement.setString(2, freeAgentName);
			statement.setString(3, position);
			statement.setString(4, captain != null ? captain.toString() : null);

			procedureCallDb.executeProcedure();
			ResultSet resultSet = statement.getResultSet();
			while (resultSet.next()) {
				outPutValue = resultSet.getBoolean("status");
			}
			statement.close();
			procedureCallDb.closeConnection();
		} catch (Exception e) {
			procedureCallDb.closeConnection();
			System.out.println("Exception occured while getting the callable statment ");
		} finally {

			procedureCallDb.closeConnection();
		}
		return outPutValue;
	}

}
