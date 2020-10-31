package group11.Hockey.db;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import group11.Hockey.BusinessLogic.models.Aging;
import group11.Hockey.BusinessLogic.models.GameResolver;
import group11.Hockey.BusinessLogic.models.GameplayConfig;
import group11.Hockey.BusinessLogic.models.Injuries;
import group11.Hockey.BusinessLogic.models.Trading;
import group11.Hockey.BusinessLogic.models.Training;

public class GameplayConfigDb implements IGameplayConfigDb {

	@Override
	public boolean insertGameplayConfig(Aging aging, GameResolver gameResolver, Injuries injuries, Training training,
			Trading trading, String leagueName) {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb(
				"{call insertGameplayConfig(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		CallableStatement statement = procedureCallDb.getDBCallableStatement();
		try {
			int averageRetirementAge = aging.getAverageRetirementAge();
			int maximumAge = aging.getMaximumAge();
			float randomWinChance = gameResolver.getRandomWinChance();
			float randomInjuryChance = injuries.getRandomInjuryChance();
			int injuryDaysLow = injuries.getInjuryDaysLow();
			int injuryDaysHigh = injuries.getInjuryDaysHigh();
			int daysUntilStatIncreaseCheck = training.getDaysUntilStatIncreaseCheck();
			int lossPoint = trading.getLossPoint();
			float randomTradeOfferChance = trading.getRandomTradeOfferChance();
			int maxPlayersPerTrade = trading.getMaxPlayersPerTrade();
			float randomAcceptanceChance = trading.getRandomAcceptanceChance();
			
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

	@Override
	public GameplayConfig loadGameConfig(String leagueName) {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb("{call getGameConfig(?)}");
		CallableStatement statement = procedureCallDb.getDBCallableStatement();
		GameplayConfig gameplayConfig = null ;
		try {
			statement.setString(1, leagueName);
			procedureCallDb.executeProcedure();
			ResultSet resultSet = statement.getResultSet();
			while (resultSet.next()) {
				Aging aging = new Aging(resultSet.getInt("averageRetirementAge"), resultSet.getInt("maximumAge"));
				GameResolver gameResolver = new GameResolver(resultSet.getFloat("randomWinChance"));
				Injuries injuries = new Injuries(resultSet.getFloat("randomInjuryChance"), resultSet.getInt("injuryDaysLow"), resultSet.getInt("injuryDaysHigh"));
				Training training = new Training(resultSet.getInt("daysUntilStatIncreaseCheck"));
				Trading trading = new Trading(resultSet.getInt("lossPoint"), resultSet.getFloat("randomTradeOfferChance"), resultSet.getInt("maxPlayersPerTrade"), resultSet.getFloat("randomAcceptanceChance"));
				gameplayConfig = new GameplayConfig(aging, gameResolver, injuries, training, trading);
			}
			statement.close();
			procedureCallDb.closeConnection();
		}catch (Exception e) {
			procedureCallDb.closeConnection();
			System.out.println("Exception occured while getting the callable statment ");
		} finally {

			procedureCallDb.closeConnection();
		}
		return gameplayConfig;
	}

}
