/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.db;

import java.sql.CallableStatement;

public interface IProcedureCallDb {
	public CallableStatement getDBCallableStatement();

	public void closeConnection();

	public void executeProcedure();
}
