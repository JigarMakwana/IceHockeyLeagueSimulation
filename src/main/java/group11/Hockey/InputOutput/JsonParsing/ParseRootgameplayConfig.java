/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.InputOutput.JsonParsing;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.models.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

public class ParseRootgameplayConfig implements IParseRootElement {
	private static Logger logger = LogManager.getLogger(ParseRootgameplayConfig.class);
	@Override
	public void parseRootElement(ILeague leagueModelObj, JSONObject jsonObject) throws Exception {
		// parse gameplayConfig
		IGameplayConfig gameplayConfig = parseGameplayConfig(jsonObject);
		leagueModelObj.setGamePlayConfig(gameplayConfig);

	}

	private GameplayConfig parseGameplayConfig(JSONObject listJsonObject) {
		logger.debug("Parsing Gameplay from Json");
		JSONObject gameplayConfigJson = (JSONObject) listJsonObject.get(Attributes.GAMEPLAYCONFIG.getAttribute());

		JSONObject agingJsonObj = (JSONObject) gameplayConfigJson.get(Attributes.AGING.getAttribute());
		IAging aging = parseAgeing(agingJsonObj);

		JSONObject injuriesJsonObj = (JSONObject) gameplayConfigJson.get(Attributes.INJURIES.getAttribute());
		IInjuries injuries = parseInjuries(injuriesJsonObj);

		JSONObject trainingJsonObj = (JSONObject) gameplayConfigJson.get(Attributes.TRAINING.getAttribute());
		ITraining training = parseTraining(trainingJsonObj);

		JSONObject tradingJsonObj = (JSONObject) gameplayConfigJson.get(Attributes.TRADING.getAttribute());
		Trading trading = parseTrading(tradingJsonObj);

		GameplayConfig gameplayConfig = DefaultHockeyFactory.makeGameplayConfig(aging, injuries, training, trading);
		return gameplayConfig;
	}

	private IAging parseAgeing(JSONObject agingJsonObj) {
		int averageRetirementAge = ((Long) agingJsonObj.get(Attributes.AVERAGERETIREMENTAGE.getAttribute())).intValue();
		int maximumAge = ((Long) agingJsonObj.get(Attributes.MAXIMUMAGE.getAttribute())).intValue();
		IAging aging = DefaultHockeyFactory.makeAging(averageRetirementAge, maximumAge);
		return aging;
	}

	private IInjuries parseInjuries(JSONObject injuriesJsonObj) {
		float randomInjuryChance = ((Double) injuriesJsonObj.get(Attributes.RANDOMINJURYCHANCE.getAttribute()))
				.floatValue();
		int injuryDaysLow = ((Long) injuriesJsonObj.get(Attributes.INJURYDAYSLOW.getAttribute())).intValue();
		int injuryDaysHigh = ((Long) injuriesJsonObj.get(Attributes.INJURYDAYSHIGH.getAttribute())).intValue();
		IInjuries injuries = DefaultHockeyFactory.makeInjuries(randomInjuryChance, injuryDaysLow, injuryDaysHigh);
		return injuries;
	}

	private ITraining parseTraining(JSONObject trainingJsonObj) {
		int daysUntilStatIncreaseCheck = ((Long) trainingJsonObj
				.get(Attributes.DAYSUNTILSTATINCREASECHECK.getAttribute())).intValue();
		ITraining training = DefaultHockeyFactory.makeTraining(daysUntilStatIncreaseCheck);
		return training;
	}

	private Trading parseTrading(JSONObject tradingJsonObj) {
		int lossPoint = ((Long) tradingJsonObj.get(Attributes.LOSSPOINT.getAttribute())).intValue();
		float randomTradeOfferChance = ((Double) tradingJsonObj.get(Attributes.RANDOMTRADEOFFERCHANCE.getAttribute()))
				.floatValue();
		int maxPlayersPerTrade = ((Long) tradingJsonObj.get(Attributes.MAXPLAYERSPERTRADE.getAttribute())).intValue();
		float randomAcceptanceChance = ((Double) tradingJsonObj.get(Attributes.RANDOMACCEPTANCECHANCE.getAttribute()))
				.floatValue();

		JSONObject gmTableJsonObj = (JSONObject) tradingJsonObj.get(Attributes.GMTABLE.getAttribute());
		IgmTable gmTable = parseGMTable(gmTableJsonObj);

		Trading trading = DefaultHockeyFactory.makeTradingConfig(lossPoint, randomTradeOfferChance, maxPlayersPerTrade,
				randomAcceptanceChance, gmTable);
		return trading;
	}

	/**
	 * Author: Jigar Makwana B00842568
	 */
	private IgmTable parseGMTable(JSONObject gmTableJsonObj) {
		float shrewdValue = ((Double) gmTableJsonObj.get(Attributes.SHREWD.getAttribute())).floatValue();
		float gamblerValue = ((Double) gmTableJsonObj.get(Attributes.GAMBLER.getAttribute())).floatValue();
		float normalValue = ((Double) gmTableJsonObj.get(Attributes.NORMAL.getAttribute())).floatValue();

		IgmTable gmTableObj = DefaultHockeyFactory.makeGMTable(shrewdValue, gamblerValue, normalValue);

		return gmTableObj;
	}
}
