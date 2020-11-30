/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.db.Manager;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.db.DefaultDatabaseFactory;
import group11.Hockey.db.IProcedureCallDb;

public class ManagerDb implements IManagerDb {
	private static Logger logger = LogManager.getLogger(ManagerDb.class);

	@Override
	public boolean insertManager(String leagueName, String managerName) {
		IProcedureCallDb procedureCallDb = DefaultDatabaseFactory.makeProcedureCallDb("{call insertManagers(?, ?, ?)}");
		CallableStatement statement = procedureCallDb.getDBCallableStatement();
		boolean outPutValue = false;
		try {
			statement.setString(1, leagueName);
			statement.setString(2, managerName);

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
