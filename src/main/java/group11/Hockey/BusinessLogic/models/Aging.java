package group11.Hockey.BusinessLogic.models;

public class Aging implements IAging{
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
