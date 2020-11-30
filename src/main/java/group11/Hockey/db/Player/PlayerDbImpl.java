package group11.Hockey.db.Player;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.db.Constants;
import group11.Hockey.db.DefaultDatabaseFactory;
import group11.Hockey.db.IProcedureCallDb;
import group11.Hockey.db.ProcedureCallDb;

public class PlayerDbImpl implements IPlayerDb {
	private static Logger logger = LogManager.getLogger(PlayerDbImpl.class);

	@Override
	public List<Player> loadFreeAgents(String leagueName) {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb("{call getFreeAgents(?)}");
		CallableStatement statement = procedureCallDb.getDBCallableStatement();
		List<Player> freeAgentsList = new ArrayList<Player>();
		try {
			statement.setString(1, leagueName);
			procedureCallDb.executeProcedure();
			ResultSet resultSet = statement.getResultSet();
			while (resultSet.next()) {
				Player freeagent = new Player();
				freeagent.setPlayerName(resultSet.getString(Constants.playerName.toString()));
				freeagent.setPosition(resultSet.getString(Constants.playerPosition.toString()));
				freeagent.setAge(Float.parseFloat(resultSet.getString(Constants.age.toString())));
				freeagent.setSkating(Float.parseFloat(resultSet.getString(Constants.skating.toString())));
				freeagent.setShooting(Float.parseFloat(resultSet.getString(Constants.shooting.toString())));
				freeagent.setChecking(Float.parseFloat(resultSet.getString(Constants.checking.toString())));
				freeagent.setSaving(Float.parseFloat(resultSet.getString(Constants.saving.toString())));
				freeagent.setCaptain(Boolean.parseBoolean(resultSet.getString(Constants.captain.toString())));
				freeagent.setIsFreeAgent(Boolean.parseBoolean(resultSet.getString(Constants.isFreeAgent.toString())));
				freeagent.setIsRetired(Boolean.parseBoolean(resultSet.getString(Constants.retired.toString())));
				freeAgentsList.add(freeagent);
			}
			statement.close();
			procedureCallDb.closeConnection();
		} catch (Exception e) {
			procedureCallDb.closeConnection();
			logger.error("Exception occured while getting the callable statment " + e);
		} finally {

			procedureCallDb.closeConnection();
		}
		return freeAgentsList;
	}

	@Override
	public boolean insertLeagueFreeAgents(String leagueName, IPlayer freeAgent) {
		IProcedureCallDb procedureCallDb = DefaultDatabaseFactory
				.makeProcedureCallDb("{call insertFreeAgent(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
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
			logger.error("Exception occured while getting the callable statment " + e);
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
			logger.error("Exception occured while getting the callable statment " + e);
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
			logger.error("Exception occured while getting the callable statment " + e);
		} finally {

			procedureCallDb.closeConnection();
		}
		return outPutValue;
	}

}
