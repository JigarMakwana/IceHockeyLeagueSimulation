package group11.Hockey.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {

	String hostURL = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_11_DEVINT?serverTimezone=UTC&useSSL=false";
	String dbUserName = "CSCI5308_11_DEVINT_USER";
	String dbPassword = "Mu3cQ7SWYb";

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(hostURL, dbUserName, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DbConnection Failed");
		}
		return conn;
	}

	public void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			System.out.println("Exception occured while closing the connection");
		}
	}
}
