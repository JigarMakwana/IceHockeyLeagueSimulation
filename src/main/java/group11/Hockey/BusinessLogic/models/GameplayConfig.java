package group11.Hockey.BusinessLogic.models;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.db.IGameplayConfigDb;

public class GameplayConfig implements IGameplayConfig {
	private IAging aging;
	private IGameResolver gameResolver;
	private IInjuries injuries;
	private ITraining training;
	private ITrading trading;
	

	private IGameplayConfigDb gameplayConfigDb;
	private String leagueName;
	
	private static Logger logger = LogManager.getLogger(GameplayConfig.class);

	public GameplayConfig(IAging aging, IInjuries injuries, ITraining training,
			ITrading trading) {
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
		logger.info("Entered getAging()");
		return aging;
	}

	public IGameResolver getGameResolver() {
		logger.info("Entered getGameResolver()");
		return gameResolver;
	}

	public IInjuries getInjuries() {
		logger.info("Entered getInjuries()");
		return injuries;
	}

	public ITraining getTraining() {
		logger.info("Entered getTraining()");
		return training;
	}

	public ITrading getTrading() {
		logger.info("Entered getTrading()");
		return trading;
	}

	private void saveGameplayConfig() {
		logger.info("Entered saveGameplayConfig()");
		gameplayConfigDb.insertGameplayConfig(aging, gameResolver, injuries, training, trading, leagueName);
	}

}
