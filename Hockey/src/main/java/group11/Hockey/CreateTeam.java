package group11.Hockey;

import java.util.List;

import group11.Hockey.models.Conference;
import group11.Hockey.models.Division;
import group11.Hockey.models.League;
import group11.Hockey.models.Team;

public class CreateTeam {
	private String conferenceName;
	private String divisionName;
	private String teamName;
	private League leagueObj;
	private IUserInputMode userInputMode;
	Conference conferenceObj = new Conference();
	Division divisionObj = new Division();

	public CreateTeam(IUserInputMode userInputMode, League leagueObj) {
		super();
		this.userInputMode = userInputMode;
		this.leagueObj = leagueObj;
	}

	public League getTeam() {

		userInputMode.displayMessage("***Create Team***\n");
		List<Conference> conferencesList = leagueObj.getConferences();
		// Conference name
		while (isNotValidConference(conferenceName, conferencesList)) {
			userInputMode.displayMessage("Enter Conference Name: ");
			conferenceName = userInputMode.getName();
		}
		Conference conferenceItem = conferenceObj.getConferencefromConferenceName(conferenceName, conferencesList);
		// Division Name
		while (isNotValidDivision(divisionName, conferenceItem)) {
			userInputMode.displayMessage("Enter Division Name: ");
			divisionName = userInputMode.getName();
		}
		Division divisionItem = divisionObj.getDivisionFromDivisionName(divisionName, conferenceItem.getDivisions());

		boolean isNotValidNumber = true;
		while (isNotValidNumber) {
			int numberOfTeams = 1;
			try {
				userInputMode.displayMessage("Enter Number of Teams to create: ");
				numberOfTeams = userInputMode.getInt();
				isNotValidNumber = false;
				for (int i = 0; i < numberOfTeams; i++) {
					// Create Team
					userInputMode.displayMessage("Team " + (i + 1) + "/" + numberOfTeams);
					createTeam(divisionItem);
				}
			} catch (Exception e) {
				userInputMode.displayMessage("not a valid number");
			}
		}

		return leagueObj;

	}

	private void createTeam(Division division) {
		String teamName = null;
		String generalManager = null;
		String headCoach = null;
		Team newTeam = new Team();
		while (isNotValidTeamName(teamName, newTeam)) {
			userInputMode.displayMessage("Enter Team Name: ");
			teamName = userInputMode.getName();
			newTeam.setTeamName(teamName);
		}

		while (isNotValidGeneralManager(generalManager, newTeam)) {
			userInputMode.displayMessage("Enter generalManager Name: ");
			generalManager = userInputMode.getName();
			newTeam.setGeneralManager(generalManager);
		}

		while (isNotValidHeadCoach(headCoach, newTeam)) {
			userInputMode.displayMessage("Enter headCoach Name: ");
			headCoach = userInputMode.getName();
			newTeam.setHeadCoach(headCoach);
		}

		division.addNewTeamInDivision(newTeam);

//		userInputMode.displayMessage("Enter Number of Players to create: ");
//		try {
//			int numberOfPlayers = userInputMode.getInt();
//			for (int i = 0; i < numberOfPlayers; i++) {
//				// Create Team
//				userInputMode.displayMessage("Player " + (i + 1) + "/" + numberOfPlayers);
//				//createPlayer();
//			}
//		} catch (Exception e) {
//			userInputMode.displayMessage("not a valid number, restart the process");
//		}

	}

	private void createPlayer() {
		String playerName = null;
		int positionChoice = 0;
		String position = null;
		Boolean isNotValidInput = true;
		String headCoach = null;
		String isCaptainString = null;
		Boolean isCaptian;
		while (!isValidString(playerName)) {
			userInputMode.displayMessage("Enter Player Name: ");
			playerName = userInputMode.getName();
		}

		while (isNotValidInput) {
			userInputMode.displayMessage(
					"Choose following options  \"Position\" :\n \"1\" for \"forward\" \n \"2\" for \"defense\" \n \"3\" for \"goalie\" \n Enter your choice: ");
			positionChoice = userInputMode.getInt();
			if (positionChoice == 1) {
				position = "forward";
				isNotValidInput = false;
			} else if (positionChoice == 2) {
				position = "defense";
				isNotValidInput = false;
			} else if (positionChoice == 3) {
				position = "goalie";
				isNotValidInput = false;
			} else {
				userInputMode.displayMessage("Wrong input");
			}
		}

		while (!isValidCaptain(isCaptainString)) {
			userInputMode.displayMessage("Is the player Capitan [Enter (yes/no)]: ");
			isCaptainString = userInputMode.getName();
			if (isCaptainString.equalsIgnoreCase("yes")) {
				isCaptian = true;
			} else if (isCaptainString.equalsIgnoreCase("no")) {
				isCaptian = false;
			}
		}
	}

	private boolean isNotValidConference(String conferenceName, List<Conference> conferencesList) {

		if (isStrBlank(conferenceName)) {
			return true;
		} else if (conferenceObj.isConferenceNameValid(conferenceName, conferencesList)) {
			return false;
		} else {
			userInputMode.displayMessage("Conference name does not exist, Please enter valid conference name");
			return true;
		}
	}

	private boolean isNotValidDivision(String divisionName, Conference conferenceItem) {

		if (isStrBlank(divisionName)) {
			return true;
		} else if (divisionObj.isDivisionNameValid(divisionName, conferenceItem.getDivisions())) {
			return false;
		} else {
			userInputMode.displayMessage("Division name does not exist, Please enter valid division name");
			return true;
		}
	}

	private boolean isNotValidTeamName(String teamName, Team teamObj) {
		if (isStrBlank(teamName)) {
			return true;
		} else if (teamObj.isTeamNameValid(teamName, leagueObj)) {
			return false;
		}
		userInputMode.displayMessage("Team name already exists in this League");
		return true;
	}

	private boolean isNotValidGeneralManager(String name, Team teamObj) {
		if (isStrBlank(name)) {
			return true;
		}
		if (teamObj.isTeamManagerNameValid(name, leagueObj)) {
			return false;
		}
		userInputMode.displayMessage("GeneralManager already exists in this League");
		return true;
	}

	private boolean isNotValidHeadCoach(String name, Team teamObj) {
		if (isStrBlank(name)) {
			return true;
		}
		if (teamObj.isHeadCoachNameValid(name, leagueObj)) {
			return false;
		}
		userInputMode.displayMessage("HeadCoach already exists in this League");
		return true;
	}

	private boolean isValidString(String name) {
		if (isStrBlank(name)) {
			return false;
		}
		return true;
	}

	private boolean isValidCaptain(String isCaptain) {
		if (isStrBlank(isCaptain)) {
			return false;
		} else if (isCaptain.equalsIgnoreCase("yes") || isCaptain.equalsIgnoreCase("no")) {

			return true;
		}
		userInputMode.displayMessage("Wrong input");
		return false;
	}
	
	private boolean isStrBlank(String str) {
		if (str == null || str.isEmpty() || str.split(" +").length == 0) {
			return true;
		}

		return false;
	}

}
