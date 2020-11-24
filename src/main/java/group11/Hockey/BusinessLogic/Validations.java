package group11.Hockey.BusinessLogic;

import java.util.List;

import group11.Hockey.BusinessLogic.models.Coach;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.GeneralManager;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.IDisplay;

public class Validations implements IValidations {

	Conference conferenceObj = new Conference();
	Division divisionObj = new Division();
	IDisplay display;

	private static Validations validationsInstance = null;

	public Validations(IDisplay display){
		this.display = display;
	}

	private Validations(){

	}

	public static Validations getInstance() {
		if(validationsInstance == null) {
			validationsInstance = new Validations();
		}
		return validationsInstance;
	}


	public boolean isConferenceNameValid(String conferenceName, List<Conference> conferencesList) {
		boolean conferenceNameCheck = false;
		if (isStrBlank(conferenceName)) {
			conferenceNameCheck = true;
		}
		if (conferenceObj.isConferenceNameValid(conferenceName, conferencesList)) {
			conferenceNameCheck = false;
		} else {
			display.showMessageOnConsole("Conference name does not exist, Please enter valid conference name");
			conferenceNameCheck = true;
		}
		return conferenceNameCheck;
	}

	public boolean isDivisionValid(String divisionName, Conference conferenceItem) {
		boolean divisionNameCheck = false;
		if (isStrBlank(divisionName)) {
			divisionNameCheck = true;
		}
		if (divisionObj.isDivisionNameValid(divisionName, conferenceItem.getDivisions())) {
			divisionNameCheck = false;
		} else {
			display.showMessageOnConsole("Division name does not exist, Please enter valid division name");
			divisionNameCheck = true;
		}
		return divisionNameCheck;
	}


	public boolean isTeamNameValid(String teamName, League league) {
		Team team = new Team();
		if (isStrBlank(teamName)) {
			return true;
		} else if (team.isTeamNameValid(teamName, league)) {
			return false;
		}
		display.showMessageOnConsole("Team name already exists in this League");
		return true;
	}


	public boolean generalManagerNameCheck(String name, League league) {
		if (isStrBlank(name)) {
			return true;
		} else {
			List<GeneralManager> generalMangerList = league.getGeneralManagers();
			for (GeneralManager generalManger : generalMangerList) {
				if (generalManger.getName().equalsIgnoreCase(name)) {
					return false;
				}
			}
			display.showMessageOnConsole("Enter valid general manager name");
			return true;
		}
	}


	public boolean headCoachNameCheck(String coachName, League league) {
		boolean coachNameCheck = true;
		if (isStrBlank(coachName)) {
			coachNameCheck = true;
		} else {
			List<Coach> coachList = league.getCoaches();
			for (Coach coach : coachList) {
				if (coach != null && coach.getName().equalsIgnoreCase(coachName)) {
					coachNameCheck = false;
					return coachNameCheck;
				}
			}
		}
		return coachNameCheck;
	}

	public boolean playerCheck(String playerNumber, League league, List<Integer> selectedValues, List<Player> skaters,
			List<Player> goalies) {
		boolean isPlayerValueNotValid = true;
		int playerNumberInInt = 0;
		try {
			playerNumberInInt = Integer.parseInt(playerNumber);
		} catch (Exception e) {
			display.showMessageOnConsole("Select valid value");
			isPlayerValueNotValid = true;
			return isPlayerValueNotValid;
		}
		if (playerNumberInInt < 0 || playerNumberInInt > league.getFreeAgents().size()) {
			display.showMessageOnConsole("Select Valid value");
			isPlayerValueNotValid = true;
			return isPlayerValueNotValid;
		}
		String position = league.getFreeAgents().get(playerNumberInInt - 1).getPosition();
		if (selectedValues.contains(playerNumberInInt)) {
			display.showMessageOnConsole("This player is already selected");
			isPlayerValueNotValid = true;
			return isPlayerValueNotValid;
		}
		if (position.equalsIgnoreCase("forward") || position.equalsIgnoreCase("defense")) {
			if (skaters.size() <= Integer.parseInt(BusinessConstants.Number_Of_Skaters.getValue().toString())) {
				isPlayerValueNotValid = false;
				selectedValues.add(playerNumberInInt);
				return isPlayerValueNotValid;
			} else {
				display.showMessageOnConsole("Can not select more skaters");
			}
		} else if (position.equalsIgnoreCase("goalie")) {
			if (goalies.size() <= Integer.parseInt(BusinessConstants.Number_Of_Goalies.getValue().toString())) {
				isPlayerValueNotValid = false;
				selectedValues.add(playerNumberInInt);
				return isPlayerValueNotValid;
			} else {
				display.showMessageOnConsole("Can not select more goalies");
			}
		}
		return isPlayerValueNotValid;

	}

	public boolean isStrBlank(String str) {
		if (str == null || str.isEmpty() || str.split(" +").length == 0) {
			return true;
		}

		return false;
	}

	public boolean isNoOfSeasonsValueValid(String numberOfSeasons) {
		boolean isNoOfSeasonsValueValid = false;
		try {
			int value = Integer.parseInt(numberOfSeasons);
			if(value < 0) {
				display.showMessageOnConsole("Select valid value for simulation");
				isNoOfSeasonsValueValid = true;
				return isNoOfSeasonsValueValid;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			display.showMessageOnConsole("Select valid value for simulation");
			isNoOfSeasonsValueValid = true;
			return isNoOfSeasonsValueValid;
		}
	}

	/**
	 * @author  Jigar Makwana B00842568
	 */
	public boolean isUserTradeInputValid(int userInput){
		if((userInput == 1) || (userInput == 0)){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @author  Jigar Makwana B00842568
	 */
	public boolean isUserResolveRosterInputValid(int userInput, int listSize){
		if(((userInput >= 1) && (userInput <= listSize))){
			return true;
		} else {
			return false;
		}
	}
}
