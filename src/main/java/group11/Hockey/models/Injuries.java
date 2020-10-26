package group11.Hockey.models;

public class Injuries {
	private float randomInjuryChance;
	private int injuryDaysLow;
	private int injuryDaysHigh;

	public Injuries(float randomInjuryChance, int injuryDaysLow, int injuryDaysHigh) {
		super();
		this.randomInjuryChance = randomInjuryChance;
		this.injuryDaysLow = injuryDaysLow;
		this.injuryDaysHigh = injuryDaysHigh;
	}

	public float getRandomInjuryChance() {
		return randomInjuryChance;
	}

	public int getInjuryDaysLow() {
		return injuryDaysLow;
	}

	public int getInjuryDaysHigh() {
		return injuryDaysHigh;
	}

}
