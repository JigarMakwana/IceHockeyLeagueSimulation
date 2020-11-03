package group11.Hockey.BusinessLogic.models;

public class Training implements ITraining{
	private int daysUntilStatIncreaseCheck;

	public Training(int daysUntilStatIncreaseCheck) {
		super();
		this.daysUntilStatIncreaseCheck = daysUntilStatIncreaseCheck;
	}

	public int getDaysUntilStatIncreaseCheck() {
		return daysUntilStatIncreaseCheck;
	}

}
