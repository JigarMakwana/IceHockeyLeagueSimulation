package group11.Hockey.db;

import java.sql.CallableStatement;
import java.sql.ResultSet;

public class ManagerDb implements IManagerDb {

	@Override
	public boolean insertManager(String leagueName, String managerName) {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb("{call insertManagers(?, ?, ?)}");
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
