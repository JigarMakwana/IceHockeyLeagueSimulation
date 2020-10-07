package group11.Hockey;


public class PlayerChoice {
	private IUserInputMode userInputMode;

	public PlayerChoice(IUserInputMode userInputMode) {
		super();
		this.userInputMode = userInputMode;
	};

	public int getCreateOrLoadTeam() {
		Boolean isNotValidInput = true;
		int input = 0;
		while (isNotValidInput) {
			userInputMode.displayMessage(
					"Choose the following options:\n \"1\" to Create Team \n \"2\" to Load Team \n Enter your choice: ");
			try {
				input = userInputMode.getInt();
			} catch (Exception e) {
				userInputMode.displayMessage("-------------");
				userInputMode.displayMessage("Not a valid input");
				userInputMode.displayMessage("-------------");
				return 0;
			}

			if (input == 1) {
				isNotValidInput = false;
				userInputMode.displayMessage("###########");
				userInputMode.displayMessage("You choose: " + input + " => Create Team");
				userInputMode.displayMessage("###########");
			} else if (input == 2) {
				isNotValidInput = false;
				userInputMode.displayMessage("###########");
				userInputMode.displayMessage("You choose: " + input + " => Load Team");
				userInputMode.displayMessage("###########");
			} else {
				userInputMode.displayMessage("-------------");
				userInputMode.displayMessage(input + " is Wrong Input");
				userInputMode.displayMessage("-------------");
			}
		}

		return input;
	}
	
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
