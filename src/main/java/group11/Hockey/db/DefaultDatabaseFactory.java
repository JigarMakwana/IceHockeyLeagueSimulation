/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.db;

public class DefaultDatabaseFactory {
	public static IProcedureCallDb makeProcedureCallDb(String procedureName) {
		return new ProcedureCallDb(procedureName);
	}
}
