package group11.Hockey.db.League;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import group11.Hockey.db.ProcedureCallDb;
import group11.Hockey.models.Coach;

public class LeagueDbImpl implements ILeagueDb {

	@Override
	public boolean insertLeagueInDb(String leagueName, String conferenceName, String divisionName, String teamName,
			String generalManger, String headcoachName, float skating, float shooting, float checking, float saving,
			String playerName, String playerPosition, boolean captain, float playerSkating, float playerShooting,
			float playerChecking, float playerSaving, float age) {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb(
				"{call insertNew(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)}");
		CallableStatement statement = procedureCallDb.getDBCallableStatement();
		boolean outPutValue = false;
		try {
			statement.setString(1, leagueName);
			statement.setString(2, conferenceName);
			statement.setString(3, divisionName);
			statement.setString(4, teamName);
			statement.setString(5, generalManger);

			statement.setString(6, headcoachName);
			statement.setFloat(7, skating);
			statement.setFloat(8, shooting);
			statement.setFloat(9, checking);
			statement.setFloat(10, saving);

			statement.setString(11, playerName);
			statement.setString(12, playerPosition);
			statement.setBoolean(13, captain);
			statement.setFloat(14, playerSkating);
			statement.setFloat(15, playerShooting);
			statement.setFloat(16, playerChecking);
			statement.setFloat(17, playerSaving);
			statement.setFloat(18, age);

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

}
