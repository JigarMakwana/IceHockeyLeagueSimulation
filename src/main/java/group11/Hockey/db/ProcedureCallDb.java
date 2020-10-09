package group11.Hockey.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ProcedureCallDb {

	private String procedureName;
	ConnectionUtil connectionUtil = new ConnectionUtil();
	CallableStatement statement = null;
	Connection connection = null;

	public ProcedureCallDb(String procedureName) {
		this.procedureName = procedureName;
	}

	public CallableStatement getDBCallableStatement() {
		try {
			this.connection = connectionUtil.getConnection();
			statement = connection.prepareCall(this.procedureName);
		} catch (SQLException e) {
			connectionUtil.closeConnection(connection);
			System.out.println("Exception occured while making the callable statement");
		}
		return statement;
	}

	public void closeConnection() {
		connectionUtil.closeConnection(connection);
	}

	public void executeProcedure() {
		Connection connection = null;
		ConnectionUtil connectionUtil = new ConnectionUtil();

		try {

			statement.executeQuery();

		} catch (Exception e) {
			connectionUtil.closeConnection(connection);
		}

	}
}
