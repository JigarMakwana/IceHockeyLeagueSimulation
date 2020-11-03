package group11.Hockey.db.Player;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.db.Constants;
import group11.Hockey.db.ProcedureCallDb;

public class PlayerDbImpl implements IPlayerDb {

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
		}catch (Exception e) {
			procedureCallDb.closeConnection();
			System.out.println("Exception occured while getting the callable statment ");
		} finally {

			procedureCallDb.closeConnection();
		}
		return freeAgentsList;
	}

}
