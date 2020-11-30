/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.models;

import group11.Hockey.db.GameplayConfig.IGameplayConfigDb;

public class GameplayConfig implements IGameplayConfig {
	private IAging aging;
	private IGameResolver gameResolver;
	private IInjuries injuries;
	private ITraining training;
	private ITrading trading;

	private IGameplayConfigDb gameplayConfigDb;
	private String leagueName;

	public GameplayConfig(IAging aging, IInjuries injuries, ITraining training, ITrading trading) {
		super();
		this.aging = aging;
		this.injuries = injuries;
		this.training = training;
		this.trading = trading;
	}

	public GameplayConfig() {

	}

	public GameplayConfig(IAging aging, IGameResolver gameResolver, IInjuries injuries, ITraining training,
			ITrading trading, IGameplayConfigDb gameplayConfigDb, String leagueName) {
		this.aging = aging;
		this.gameResolver = gameResolver;
		this.injuries = injuries;
		this.training = training;
		this.trading = trading;
		this.gameplayConfigDb = gameplayConfigDb;
		this.leagueName = leagueName;
		saveGameplayConfig();
	}

	public IAging getAging() {
		return aging;
	}

	public IGameResolver getGameResolver() {
		return gameResolver;
	}

	public IInjuries getInjuries() {
		return injuries;
	}

	public ITraining getTraining() {
		return training;
	}

	public ITrading getTrading() {
		return trading;
	}

	private void saveGameplayConfig() {
		gameplayConfigDb.insertGameplayConfig(aging, gameResolver, injuries, training, trading, leagueName);
	}

}
