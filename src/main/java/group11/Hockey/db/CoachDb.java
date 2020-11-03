package group11.Hockey.db;

import java.sql.CallableStatement;
import java.sql.ResultSet;

public class CoachDb implements ICoachDb {

	@Override
	public boolean insertCoaches(String leagueName, String coachName, float skating, float shooting, float checking,
			float saving) {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb("{call insertCoaches(?, ?, ?, ?, ?, ?, ?)}");
		CallableStatement statement = procedureCallDb.getDBCallableStatement();
		boolean outPutValue = false;
		try {
			statement.setString(1, leagueName);
			statement.setString(2, coachName);
			statement.setFloat(3, skating);
			statement.setFloat(4, shooting);
			statement.setFloat(5, checking);
			statement.setFloat(6, saving);

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
