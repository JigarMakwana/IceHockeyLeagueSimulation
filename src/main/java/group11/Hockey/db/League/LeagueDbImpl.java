package group11.Hockey.db.League;

import java.sql.CallableStatement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.db.ProcedureCallDb;

public class LeagueDbImpl implements ILeagueDb {
	private static Logger logger = LogManager.getLogger(LeagueDbImpl.class);

	@Override
	public boolean insertLeagueInDb(ILeague league) {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb("{call insertNew(?)}");
		CallableStatement statement = procedureCallDb.getDBCallableStatement();
		boolean outPutValue = false;
		try {
			statement.setString(1, league.toString());
			procedureCallDb.executeProcedure();
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

	@Override
	public ILeague loadLeague() {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb("{call loadLeague()}");
		CallableStatement statement = procedureCallDb.getDBCallableStatement();
		ILeague league = null;
		try {
			procedureCallDb.executeProcedure();
			statement.close();
			procedureCallDb.closeConnection();
		} catch (Exception e) {
			procedureCallDb.closeConnection();
			logger.error("Exception occured while getting the callable statment " + e);
		} finally {

			procedureCallDb.closeConnection();
		}
		return league;
	}

}
