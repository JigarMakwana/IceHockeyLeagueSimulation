package group11.Hockey.db.League;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import group11.Hockey.BusinessLogic.models.Coach;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.db.ProcedureCallDb;

public class LeagueDbImpl implements ILeagueDb {

//	@Override
//	public boolean insertLeagueInDb(String leagueName, String conferenceName, String divisionName, String teamName,
//			String generalManger, String headcoachName, float skating, float shooting, float checking, float saving,
//			String playerName, String playerPosition, boolean captain, float playerSkating, float playerShooting,
//			float playerChecking, float playerSaving, float age) {
//		ProcedureCallDb procedureCallDb = new ProcedureCallDb(
//				"{call insertNew(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)}");
//		CallableStatement statement = procedureCallDb.getDBCallableStatement();
//		boolean outPutValue = false;
//		try {
//			statement.setString(1, leagueName);
//			statement.setString(2, conferenceName);
//			statement.setString(3, divisionName);
//			statement.setString(4, teamName);
//			statement.setString(5, generalManger);
//
//			statement.setString(6, headcoachName);
//			statement.setFloat(7, skating);
//			statement.setFloat(8, shooting);
//			statement.setFloat(9, checking);
//			statement.setFloat(10, saving);
//
//			statement.setString(11, playerName);
//			statement.setString(12, playerPosition);
//			statement.setBoolean(13, captain);
//			statement.setFloat(14, playerSkating);
//			statement.setFloat(15, playerShooting);
//			statement.setFloat(16, playerChecking);
//			statement.setFloat(17, playerSaving);
//			statement.setFloat(18, age);
//
//			procedureCallDb.executeProcedure();
//			ResultSet resultSet = statement.getResultSet();
//			while (resultSet.next()) {
//				outPutValue = resultSet.getBoolean("status");
//			}
//			statement.close();
//			procedureCallDb.closeConnection();
//		} catch (Exception e) {
//			procedureCallDb.closeConnection();
//			System.out.println("Exception occured while getting the callable statment ");
//		} finally {
//
//			procedureCallDb.closeConnection();
//		}
//		return outPutValue;
//
//	}

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
	public boolean insertLeagueInDb(League league, String conferenceName, String divisionName, Team team, Coach coach,
			Player player) {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb(
				"{call insertNew(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)}");
		CallableStatement statement = procedureCallDb.getDBCallableStatement();
		boolean outPutValue = false;
		try {
			statement.setString(1, league.getLeagueName());
			statement.setString(2, conferenceName);
			statement.setString(3, divisionName);
			statement.setString(4, team.getTeamName());
			statement.setString(5, team.getTeamName());

			statement.setString(6, coach.getName());
			statement.setFloat(7, coach.getSkating());
			statement.setFloat(8, coach.getShooting());
			statement.setFloat(9, coach.getChecking());
			statement.setFloat(10, coach.getSaving());

			statement.setString(11, player.getPlayerName());
			statement.setString(12, player.getPosition());
			statement.setBoolean(13, player.getCaptain());
			statement.setFloat(14, player.getSkating());
			statement.setFloat(15, player.getShooting());
			statement.setFloat(16, player.getChecking());
			statement.setFloat(17, player.getSaving());
			statement.setFloat(18, player.getAge());
			// statement.setString(19, league.emd);
			statement.setInt(20, team.getPoints());

			procedureCallDb.executeProcedure();
			ResultSet resultSet = statement.getResultSet();
			while (resultSet.next()) {
				outPutValue = resultSet.getBoolean("status");
			}
			statement.close();
			procedureCallDb.closeConnection();
		} catch (Exception e) {
			procedureCallDb.closeConnection();
			e.printStackTrace();
			System.out.println("Exception occured while getting the callable statment ");
		} finally {

			procedureCallDb.closeConnection();
		}
		return outPutValue;
	}

}
