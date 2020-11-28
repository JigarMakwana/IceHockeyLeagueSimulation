/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.db.Manager;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import group11.Hockey.db.DefaultDatabaseFactory;
import group11.Hockey.db.IProcedureCallDb;

public class ManagerDb implements IManagerDb {

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
			System.out.println("Exception occured while getting the callable statment ");
		} finally {

			procedureCallDb.closeConnection();
		}
		return outPutValue;
	}

}
