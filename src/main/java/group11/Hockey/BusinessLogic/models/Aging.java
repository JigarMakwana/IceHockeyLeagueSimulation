/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.models;

public class Aging implements IAging{
	private int averageRetirementAge;
	private int maximumAge;
	private float statDecayChance;

	public Aging(int averageRetirementAge, int maximumAge) {
		super();
		this.averageRetirementAge = averageRetirementAge;
		this.maximumAge = maximumAge;
	}

	public int getAverageRetirementAge() {
		return averageRetirementAge;
	}

	public int getMaximumAge() {
		return maximumAge;
	}
	
	public float getStatDecayChance() {
		return statDecayChance;
	}

	public void setStatDecayChance(float statDecayChance) {
		this.statDecayChance = statDecayChance;
	}


}
