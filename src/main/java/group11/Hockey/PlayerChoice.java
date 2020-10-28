package group11.Hockey;


public class PlayerChoice {
	private ICommandLineInput userInputMode;

	public PlayerChoice(ICommandLineInput userInputMode) {
		super();
		this.userInputMode = userInputMode;
	};
	
	public int getNumberOfSeasonsToSimulate() {
		boolean isNotValidNumber = true;
		int numberOfSeasons = 0;
		while (isNotValidNumber) {
			
//			try {
//				userInputMode.getValueFromUser("Enter number of seasons to simulate:");
//				numberOfSeasons = userInputMode.getInt();
//				isNotValidNumber = false;
//			} catch (Exception e) {
//				userInputMode.displayMessage("not a valid number");
//			}
		}
		
		return numberOfSeasons;
	}
	
	
	 

}
