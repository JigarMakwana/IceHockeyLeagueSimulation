package group11.Hockey.db;

import group11.Hockey.BusinessLogic.models.GameplayConfig;

public interface IGameplayConfigDb {
	public GameplayConfig loadGameConfig(String leagueName);
	
	public boolean insertGameplayConfig(int averageRetirementAge, int maximumAge, float randomWinChance,
			float randomInjuryChance, int injuryDaysLow, int injuryDaysHigh, int daysUntilStatIncreaseCheck,
			int lossPoint, float randomTradeOfferChance, int maxPlayersPerTrade, float randomAcceptanceChance,String leagueName);
}
