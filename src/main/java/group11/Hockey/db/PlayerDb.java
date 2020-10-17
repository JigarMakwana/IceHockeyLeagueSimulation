package group11.Hockey.db;

import java.sql.CallableStatement;
import java.sql.ResultSet;

public class PlayerDb implements IPlayerDb{

	@Override
	public boolean insertLeagueFreeAgents(String leagueName, String freeAgentName, String position, float playerSkating,
			float playerShooting, float playerChecking, float playerSaving, int age) {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb("{call insertFreeAgent(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		CallableStatement statement = procedureCallDb.getDBCallableStatement();
		boolean outPutValue = false;
		try {
			statement.setString(1, leagueName);
			statement.setString(2, freeAgentName);
			statement.setString(3, position);
			statement.setFloat(4, playerSkating);
			statement.setFloat(5, playerShooting);
			statement.setFloat(6, playerChecking);
			statement.setFloat(7, playerSaving);
			statement.setInt(8, age);

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
