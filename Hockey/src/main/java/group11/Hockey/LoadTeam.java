package group11.Hockey;

import group11.Hockey.models.League;

public class LoadTeam {
	private IUserInputMode userInputMode;

	public LoadTeam(IUserInputMode userInputMode) {
		super();
		this.userInputMode = userInputMode;
	}

	public League getTeam() throws Exception {
		userInputMode.displayMessage("***Load Team***\n");
		String teamName;

		userInputMode.displayMessage("Enter Team Name: ");
		teamName = userInputMode.getName();
		if (isNotValidTeamName(teamName)) {
			throw new Exception("Team name does not exist in the system");
		}

		return null;
	}

	private boolean isNotValidTeamName(String teamName) {
		if (isStrBlank(teamName)) {
			return true;
		} 
		return false;
	}

	private boolean isStrBlank(String str) {
		if (str == null || str.isEmpty() || str.split(" +").length == 0) {
			return true;
		}

		return false;
	}
}
