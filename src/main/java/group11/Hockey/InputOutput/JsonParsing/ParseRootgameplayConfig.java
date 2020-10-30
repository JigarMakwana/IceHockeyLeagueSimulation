package group11.Hockey.InputOutput.JsonParsing;

import org.json.simple.JSONObject;

import group11.Hockey.BusinessLogic.models.Aging;
import group11.Hockey.BusinessLogic.models.GameResolver;
import group11.Hockey.BusinessLogic.models.GameplayConfig;
import group11.Hockey.BusinessLogic.models.Injuries;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Trading;
import group11.Hockey.BusinessLogic.models.Training;

public class ParseRootgameplayConfig implements IParseRootElement {

	@Override
	public void parseRootElement(League leagueModelObj, JSONObject jsonObject) throws Exception {
		// parse gameplayConfig
		GameplayConfig gameplayConfig = parseGameplayConfig(jsonObject);
		leagueModelObj.setGamePlayConfig(gameplayConfig);

	}

	private GameplayConfig parseGameplayConfig(JSONObject listJsonObject) {
		JSONObject gameplayConfigJson = (JSONObject) listJsonObject.get("gameplayConfig");

		JSONObject agingJsonObj = (JSONObject) gameplayConfigJson.get("aging");
		Aging aging = parseAgeing(agingJsonObj);

		JSONObject gameResolverJsonObj = (JSONObject) gameplayConfigJson.get("gameResolver");
		GameResolver gameResolver = parseGameResolver(gameResolverJsonObj);

		JSONObject injuriesJsonObj = (JSONObject) gameplayConfigJson.get("injuries");
		Injuries injuries = parseInjuries(injuriesJsonObj);

		JSONObject trainingJsonObj = (JSONObject) gameplayConfigJson.get("training");
		Training training = parseTraining(trainingJsonObj);

		JSONObject tradingJsonObj = (JSONObject) gameplayConfigJson.get("trading");
		Trading trading = parseTrading(tradingJsonObj);

		GameplayConfig gameplayConfig = new GameplayConfig(aging, gameResolver, injuries, training, trading);
		return gameplayConfig;
	}

	private Aging parseAgeing(JSONObject agingJsonObj) {
		int averageRetirementAge = ((Long) agingJsonObj.get("averageRetirementAge")).intValue();
		int maximumAge = ((Long) agingJsonObj.get("maximumAge")).intValue();
		Aging aging = new Aging(averageRetirementAge, maximumAge);
		return aging;
	}

	private GameResolver parseGameResolver(JSONObject gameResolverJsonObj) {
		float randomWinChance = ((Double) gameResolverJsonObj.get("randomWinChance")).floatValue();
		GameResolver gameResolver = new GameResolver(randomWinChance);
		return gameResolver;
	}

	private Injuries parseInjuries(JSONObject injuriesJsonObj) {
		float randomInjuryChance = ((Double) injuriesJsonObj.get("randomInjuryChance")).floatValue();
		int injuryDaysLow = ((Long) injuriesJsonObj.get("injuryDaysLow")).intValue();
		int injuryDaysHigh = ((Long) injuriesJsonObj.get("injuryDaysHigh")).intValue();
		Injuries injuries = new Injuries(randomInjuryChance, injuryDaysLow, injuryDaysHigh);
		return injuries;
	}

	private Training parseTraining(JSONObject trainingJsonObj) {
		int daysUntilStatIncreaseCheck = ((Long) trainingJsonObj.get("daysUntilStatIncreaseCheck")).intValue();
		Training training = new Training(daysUntilStatIncreaseCheck);
		return training;
	}

	private Trading parseTrading(JSONObject tradingJsonObj) {
		int lossPoint = ((Long) tradingJsonObj.get("lossPoint")).intValue();
		float randomTradeOfferChance = ((Double) tradingJsonObj.get("randomTradeOfferChance")).floatValue();
		int maxPlayersPerTrade = ((Long) tradingJsonObj.get("maxPlayersPerTrade")).intValue();
		float randomAcceptanceChance = ((Double) tradingJsonObj.get("randomAcceptanceChance")).floatValue();
		Trading trading = new Trading(lossPoint, randomTradeOfferChance, maxPlayersPerTrade, randomAcceptanceChance);
		return trading;
	}

}
