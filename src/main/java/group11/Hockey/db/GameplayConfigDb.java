package group11.Hockey.db;

import java.sql.CallableStatement;

public class GameplayConfigDb implements IGameplayConfigDb {

	@Override
	public boolean insertGameplayConfig(int averageRetirementAge, int maximumAge, float randomWinChance,
			float randomInjuryChance, int injuryDaysLow, int injuryDaysHigh, int daysUntilStatIncreaseCheck,
			int lossPoint, float randomTradeOfferChance, int maxPlayersPerTrade, float randomAcceptanceChance,
			String leagueName) {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb(
				"{call insertGameplayConfig(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		CallableStatement statement = procedureCallDb.getDBCallableStatement();
		try {
			statement.setInt(1, averageRetirementAge);
			statement.setInt(2, maximumAge);
			statement.setFloat(3, randomWinChance);
			statement.setFloat(4, randomInjuryChance);
			statement.setInt(5, injuryDaysLow);
			statement.setInt(6, injuryDaysHigh);
			statement.setInt(7, daysUntilStatIncreaseCheck);
			statement.setInt(8, lossPoint);
			statement.setFloat(9, randomTradeOfferChance);
			statement.setInt(10, maxPlayersPerTrade);
			statement.setFloat(11, randomAcceptanceChance);
			statement.setString(12, leagueName);

			procedureCallDb.executeProcedure();

			statement.close();
			procedureCallDb.closeConnection();
		} catch (Exception e) {
			procedureCallDb.closeConnection();
			System.out.println("Exception occured while getting the callable statment ");
		} finally {

			procedureCallDb.closeConnection();
		}
		
		return true;
	}

}
