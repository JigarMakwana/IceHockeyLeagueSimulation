package group11.Hockey.models;

import group11.Hockey.db.IGameplayConfigDb;

public class GameplayConfig {
	private Aging aging;
	private GameResolver gameResolver;
	private Injuries injuries;
	private Training training;
	private Trading trading;

	private IGameplayConfigDb gameplayConfigDb;
	private String leagueName;

	public GameplayConfig(Aging aging, GameResolver gameResolver, Injuries injuries, Training training,
			Trading trading) {
		super();
		this.aging = aging;
		this.gameResolver = gameResolver;
		this.injuries = injuries;
		this.training = training;
		this.trading = trading;
	}

	public GameplayConfig(Aging aging, GameResolver gameResolver, Injuries injuries, Training training, Trading trading,
			IGameplayConfigDb gameplayConfigDb, String leagueName) {
		this.aging = aging;
		this.gameResolver = gameResolver;
		this.injuries = injuries;
		this.training = training;
		this.trading = trading;
		this.gameplayConfigDb = gameplayConfigDb;
		this.leagueName = leagueName;
		saveGameplayConfig();
	}

	public Aging getAging() {
		return aging;
	}

	public GameResolver getGameResolver() {
		return gameResolver;
	}

	public Injuries getInjuries() {
		return injuries;
	}

	public Training getTraining() {
		return training;
	}

	public Trading getTrading() {
		return trading;
	}

	private void saveGameplayConfig() {

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

		gameplayConfigDb.insertGameplayConfig(averageRetirementAge, maximumAge, randomWinChance, randomInjuryChance,
				injuryDaysLow, injuryDaysHigh, daysUntilStatIncreaseCheck, lossPoint, randomTradeOfferChance,
				maxPlayersPerTrade, randomAcceptanceChance, leagueName);
	}

}
