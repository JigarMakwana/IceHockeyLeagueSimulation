package group11.Hockey;


public class PlayerChoice {
	private IUserInputMode userInputMode;

	public PlayerChoice(IUserInputMode userInputMode) {
		super();
		this.userInputMode = userInputMode;
	};
	
	public int getNumberOfSeasonsToSimulate() {
		boolean isNotValidNumber = true;
		int numberOfSeasons = 0;
		while (isNotValidNumber) {
			
			try {
				userInputMode.displayMessage("Enter number of seasons to simulate:");
				numberOfSeasons = userInputMode.getInt();
				isNotValidNumber = false;
			} catch (Exception e) {
				userInputMode.displayMessage("not a valid number");
			}
		}
		
		return numberOfSeasons;
	}
	
	
	 

}
