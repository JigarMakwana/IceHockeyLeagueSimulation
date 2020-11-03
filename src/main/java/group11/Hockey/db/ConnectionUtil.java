package group11.Hockey.db;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionUtil {

	String hostURL;
	String dbUserName;
	String dbPassword;

	public Connection getConnection() {
		Connection conn = null;
		readDataConnectionDetails();
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

	public void readDataConnectionDetails() {
		try {
			String filePath = "./config.properties";
			InputStream input = new FileInputStream(filePath);
			Properties prop = new Properties();
			prop.load(input);
			input.close();
			hostURL = prop.getProperty(DbConstants.DB_NAME.toString());
			dbUserName = prop.getProperty(DbConstants.DB_USER_NAME.toString());
			dbPassword = prop.getProperty(DbConstants.DB_PASSWORD.toString());

		} catch (Exception e) {
			System.out.println("Exception occoured while reading data from the properties file");
		}
	}
}
