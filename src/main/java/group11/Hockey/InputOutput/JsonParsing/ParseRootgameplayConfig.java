package group11.Hockey.InputOutput.JsonParsing;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import org.json.simple.JSONObject;

import group11.Hockey.BusinessLogic.models.Aging;
import group11.Hockey.BusinessLogic.models.GameResolver;
import group11.Hockey.BusinessLogic.models.GameplayConfig;
import group11.Hockey.BusinessLogic.models.Injuries;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Trading;
import group11.Hockey.BusinessLogic.models.Training;

import java.util.Dictionary;
import java.util.Hashtable;

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
		Trading trading = parseTrading(gameplayConfigJson, tradingJsonObj);

		GameplayConfig gameplayConfig = DefaultHockeyFactory.makeGameplayConfig(aging, gameResolver, injuries, training, trading);
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

	private Trading parseTrading(JSONObject gameplayConfigJson, JSONObject tradingJsonObj) {
		int lossPoint = ((Long) tradingJsonObj.get(Attributes.LOSSPOINT.getAttribute())).intValue();
		float randomTradeOfferChance = ((Double) tradingJsonObj.get(Attributes.RANDOMTRADEOFFERCHANCE.getAttribute())).floatValue();
		int maxPlayersPerTrade = ((Long) tradingJsonObj.get(Attributes.MAXPLAYERSPERTRADE.getAttribute())).intValue();
		float randomAcceptanceChance = ((Double) tradingJsonObj.get(Attributes.RANDOMACCEPTANCECHANCE.getAttribute())).floatValue();

		JSONObject gmTableJsonObj = (JSONObject) gameplayConfigJson.get(Attributes.GMTABLE.getAttribute());
		Dictionary gmTable = parseGMTable(gmTableJsonObj);

		Trading trading = DefaultHockeyFactory.makeTradingConfig(lossPoint, randomTradeOfferChance,
				maxPlayersPerTrade, randomAcceptanceChance, gmTable);
		return trading;
	}

	private Dictionary parseGMTable(JSONObject gmTableJsonObj){
		float shrewdValue = ((Double) gmTableJsonObj.get(Attributes.SHREWD.getAttribute())).floatValue();
		float gamblerValue = ((Double) gmTableJsonObj.get(Attributes.GAMBLER.getAttribute())).floatValue();
		float normalValue = ((Double) gmTableJsonObj.get(Attributes.NORMAL.getAttribute())).floatValue();

		Dictionary gmTable = new Hashtable();
		gmTable.put("shrewd", shrewdValue);
		gmTable.put("gambler", gamblerValue);
		gmTable.put("normal",normalValue);

		return gmTable;
	}
}
