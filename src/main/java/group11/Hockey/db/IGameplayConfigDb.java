package group11.Hockey.db;

public interface IGameplayConfigDb {
	public void insertGameplayConfig(int averageRetirementAge, int maximumAge, float randomWinChance,
			float randomInjuryChance, int injuryDaysLow, int injuryDaysHigh, int daysUntilStatIncreaseCheck,
			int lossPoint, float randomTradeOfferChance, int maxPlayersPerTrade, float randomAcceptanceChance,String leagueName);
}
