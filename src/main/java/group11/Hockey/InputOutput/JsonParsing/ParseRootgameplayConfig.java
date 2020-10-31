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
		JSONObject gameplayConfigJson = (JSONObject) listJsonObject.get(Attributes.GAMEPLAYCONFIG.getAttribute());

		JSONObject agingJsonObj = (JSONObject) gameplayConfigJson.get(Attributes.AGING.getAttribute());
		Aging aging = parseAgeing(agingJsonObj);

		JSONObject gameResolverJsonObj = (JSONObject) gameplayConfigJson.get(Attributes.GAMERESOLVER.getAttribute());
		GameResolver gameResolver = parseGameResolver(gameResolverJsonObj);

		JSONObject injuriesJsonObj = (JSONObject) gameplayConfigJson.get(Attributes.INJURIES.getAttribute());
		Injuries injuries = parseInjuries(injuriesJsonObj);

		JSONObject trainingJsonObj = (JSONObject) gameplayConfigJson.get(Attributes.TRAINING.getAttribute());
		Training training = parseTraining(trainingJsonObj);

		JSONObject tradingJsonObj = (JSONObject) gameplayConfigJson.get(Attributes.TRADING.getAttribute());
		Trading trading = parseTrading(tradingJsonObj);

		GameplayConfig gameplayConfig = new GameplayConfig(aging, gameResolver, injuries, training, trading);
		return gameplayConfig;
	}

	private Aging parseAgeing(JSONObject agingJsonObj) {
		int averageRetirementAge = ((Long) agingJsonObj.get(Attributes.AVERAGERETIREMENTAGE.getAttribute())).intValue();
		int maximumAge = ((Long) agingJsonObj.get(Attributes.MAXIMUMAGE.getAttribute())).intValue();
		Aging aging = new Aging(averageRetirementAge, maximumAge);
		return aging;
	}

	private GameResolver parseGameResolver(JSONObject gameResolverJsonObj) {
		float randomWinChance = ((Double) gameResolverJsonObj.get(Attributes.RANDOMWINCHANCE.getAttribute())).floatValue();
		GameResolver gameResolver = new GameResolver(randomWinChance);
		return gameResolver;
	}

	private Injuries parseInjuries(JSONObject injuriesJsonObj) {
		float randomInjuryChance = ((Double) injuriesJsonObj.get(Attributes.RANDOMINJURYCHANCE.getAttribute())).floatValue();
		int injuryDaysLow = ((Long) injuriesJsonObj.get(Attributes.INJURYDAYSLOW.getAttribute())).intValue();
		int injuryDaysHigh = ((Long) injuriesJsonObj.get(Attributes.INJURYDAYSHIGH.getAttribute())).intValue();
		Injuries injuries = new Injuries(randomInjuryChance, injuryDaysLow, injuryDaysHigh);
		return injuries;
	}

	private Training parseTraining(JSONObject trainingJsonObj) {
		int daysUntilStatIncreaseCheck = ((Long) trainingJsonObj.get(Attributes.DAYSUNTILSTATINCREASECHECK.getAttribute())).intValue();
		Training training = new Training(daysUntilStatIncreaseCheck);
		return training;
	}

	private Trading parseTrading(JSONObject tradingJsonObj) {
		int lossPoint = ((Long) tradingJsonObj.get(Attributes.LOSSPOINT.getAttribute())).intValue();
		float randomTradeOfferChance = ((Double) tradingJsonObj.get(Attributes.RANDOMTRADEOFFERCHANCE.getAttribute())).floatValue();
		int maxPlayersPerTrade = ((Long) tradingJsonObj.get(Attributes.MAXPLAYERSPERTRADE.getAttribute())).intValue();
		float randomAcceptanceChance = ((Double) tradingJsonObj.get(Attributes.RANDOMACCEPTANCECHANCE.getAttribute())).floatValue();
		Trading trading = new Trading(lossPoint, randomTradeOfferChance, maxPlayersPerTrade, randomAcceptanceChance);
		return trading;
	}

}
