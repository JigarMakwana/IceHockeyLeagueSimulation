/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.db.GameplayConfig;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.models.IAging;
import group11.Hockey.BusinessLogic.models.IGameResolver;
import group11.Hockey.BusinessLogic.models.IGameplayConfig;
import group11.Hockey.BusinessLogic.models.IInjuries;
import group11.Hockey.BusinessLogic.models.ITrading;
import group11.Hockey.BusinessLogic.models.ITraining;
import group11.Hockey.db.Constants;
import group11.Hockey.db.DefaultDatabaseFactory;
import group11.Hockey.db.IProcedureCallDb;

public class GameplayConfigDb implements IGameplayConfigDb {

	@Override
	public boolean insertGameplayConfig(IAging aging, IGameResolver gameResolver, IInjuries injuries,
			ITraining training, ITrading trading, String leagueName) {
		IProcedureCallDb procedureCallDb = DefaultDatabaseFactory
				.makeProcedureCallDb("{call insertGameplayConfig(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
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
	public IGameplayConfig loadGameConfig(String leagueName) {
		IProcedureCallDb procedureCallDb = DefaultDatabaseFactory.makeProcedureCallDb("{call getGameConfig(?)}");
		CallableStatement statement = procedureCallDb.getDBCallableStatement();
		IGameplayConfig gameplayConfig = null;
		try {
			statement.setString(1, leagueName);
			procedureCallDb.executeProcedure();
			ResultSet resultSet = statement.getResultSet();
			while (resultSet.next()) {
				IAging aging = DefaultHockeyFactory.makeAging(
						resultSet.getInt(Constants.averageRetirementAge.toString()),
						resultSet.getInt(Constants.maximumAge.toString()));
				IInjuries injuries = DefaultHockeyFactory.makeInjuries(
						resultSet.getFloat(Constants.randomInjuryChance.toString()),
						resultSet.getInt(Constants.injuryDaysLow.toString()),
						resultSet.getInt(Constants.injuryDaysHigh.toString()));
				ITraining training = DefaultHockeyFactory
						.makeTraining(resultSet.getInt(Constants.daysUntilStatIncreaseCheck.toString()));
				ITrading trading = (ITrading) DefaultHockeyFactory.makeConfigTrading(
						resultSet.getInt(Constants.lossPoint.toString()),
						resultSet.getFloat(Constants.randomTradeOfferChance.toString()),
						resultSet.getInt(Constants.maxPlayersPerTrade.toString()),
						resultSet.getFloat("randomAcceptanceChance"), null);
				gameplayConfig = DefaultHockeyFactory.makeGameplayConfig(aging, injuries, training, trading);
			}
			statement.close();
			procedureCallDb.closeConnection();
		} catch (Exception e) {
			procedureCallDb.closeConnection();
			System.out.println("Exception occured while getting the callable statment ");
		} finally {
			procedureCallDb.closeConnection();
		}
		return gameplayConfig;
	}

}
