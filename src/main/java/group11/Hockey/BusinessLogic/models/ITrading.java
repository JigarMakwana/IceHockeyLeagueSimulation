package group11.Hockey.BusinessLogic.models;

public interface ITrading {
	public int getLossPoint();

	public float getRandomTradeOfferChance();

	public int getMaxPlayersPerTrade();

	public float getRandomAcceptanceChance();
}
