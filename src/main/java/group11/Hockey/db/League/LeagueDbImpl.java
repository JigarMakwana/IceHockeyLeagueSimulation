package group11.Hockey.db.League;

import java.sql.CallableStatement;

import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.db.ProcedureCallDb;

public class LeagueDbImpl implements ILeagueDb {


	@Override
	public boolean insertLeagueInDb(ILeague league) {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb(
				"{call insertNew(?)}");
		CallableStatement statement = procedureCallDb.getDBCallableStatement();
		boolean outPutValue = false;
		try {
			statement.setString(1, league.toString());
			procedureCallDb.executeProcedure();
			statement.close();
			procedureCallDb.closeConnection();
		} catch (Exception e) {
			procedureCallDb.closeConnection();
			e.printStackTrace();
			System.out.println("Exception occured while getting the callable statment ");
		} finally {

			procedureCallDb.closeConnection();
		}
		return outPutValue;
	}

	@Override
	public ILeague loadLeague() {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb(
				"{call loadLeague()}");
		CallableStatement statement = procedureCallDb.getDBCallableStatement();
		ILeague league = null;
		try {
			 procedureCallDb.executeProcedure();
			statement.close();
			procedureCallDb.closeConnection();
		} catch (Exception e) {
			procedureCallDb.closeConnection();
			e.printStackTrace();
			System.out.println("Exception occured while getting the callable statment ");
		} finally {

			procedureCallDb.closeConnection();
		}
		return league;
	}

}
