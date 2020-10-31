package group11.Hockey.db.Player;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import group11.Hockey.BusinessLogic.models.Player;
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
				freeagent.setPlayerName(resultSet.getString("playerName"));
				freeagent.setPosition(resultSet.getString("playerPosition"));
				freeagent.setAge(Float.parseFloat(resultSet.getString("age")));
				freeagent.setSkating(Float.parseFloat(resultSet.getString("skating")));
				freeagent.setShooting(Float.parseFloat(resultSet.getString("shooting")));
				freeagent.setChecking(Float.parseFloat(resultSet.getString("checking")));
				freeagent.setSaving(Float.parseFloat(resultSet.getString("saving")));
				freeagent.setCaptain(Boolean.parseBoolean(resultSet.getString("captain")));
				freeagent.setIsFreeAgent(Boolean.parseBoolean(resultSet.getString("isFreeAgent")));
				freeagent.setIsRetired(Boolean.parseBoolean(resultSet.getString("retired")));
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
