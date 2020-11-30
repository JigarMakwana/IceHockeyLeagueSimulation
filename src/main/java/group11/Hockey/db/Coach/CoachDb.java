/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.db.Coach;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.db.DefaultDatabaseFactory;
import group11.Hockey.db.IProcedureCallDb;

public class CoachDb implements ICoachDb {
	private static Logger logger = LogManager.getLogger(CoachDb.class);

	@Override
	public boolean insertCoaches(String leagueName, String coachName, float skating, float shooting, float checking,
			float saving) {
		IProcedureCallDb procedureCallDb = DefaultDatabaseFactory
				.makeProcedureCallDb("{call insertCoaches(?, ?, ?, ?, ?, ?, ?)}");
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
			logger.error("Exception occured while getting the callable statment " + e);
		} finally {

			procedureCallDb.closeConnection();
		}
		return outPutValue;
	}

}
