package group11.Hockey.models;

public class Aging {
	private int averageRetirementAge;
	private int maximumAge;

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

}
