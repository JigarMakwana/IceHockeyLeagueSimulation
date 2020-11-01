package group11.Hockey.db;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import group11.Hockey.BusinessLogic.models.IPlayer;

public class PlayerDb implements IPlayerDb {

	@Override
	public boolean insertLeagueFreeAgents(String leagueName, IPlayer freeAgent) {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb("{call insertFreeAgent(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		CallableStatement statement = procedureCallDb.getDBCallableStatement();
		boolean outPutValue = false;
		try {

			statement.setString(1, leagueName);
			statement.setString(2, freeAgent.getPlayerName());
			statement.setString(3, freeAgent.getPosition());
			statement.setFloat(4, freeAgent.getSkating());
			statement.setFloat(5, freeAgent.getShooting());
			statement.setFloat(6, freeAgent.getChecking());
			statement.setFloat(7, freeAgent.getSaving());
			statement.setFloat(8, freeAgent.getAge());

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
	public boolean insertLeagueRetiredPlayers(String leagueName, IPlayer retiredPlayer) {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb("{call insertRetiredPlayers(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		CallableStatement statement = procedureCallDb.getDBCallableStatement();
		boolean outPutValue = false;
		try {
			statement.setString(1, leagueName);
			statement.setString(2, retiredPlayer.getPlayerName());
			statement.setString(3, retiredPlayer.getPosition());
			statement.setFloat(4, retiredPlayer.getSkating());
			statement.setFloat(5, retiredPlayer.getShooting());
			statement.setFloat(6, retiredPlayer.getChecking());
			statement.setFloat(7, retiredPlayer.getSaving());
			statement.setFloat(8, retiredPlayer.getAge());

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
	public boolean deleteLeaguePlayers(String leagueName) {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb("{call deleteLeaguePlayers(?, ?)}");
		CallableStatement statement = procedureCallDb.getDBCallableStatement();
		boolean outPutValue = false;
		try {
			statement.setString(1, leagueName);

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
