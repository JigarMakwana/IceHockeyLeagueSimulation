package group11.Hockey;

import java.util.List;

import group11.Hockey.models.Coach;
import group11.Hockey.models.Conference;
import group11.Hockey.models.Division;
import group11.Hockey.models.GeneralManager;
import group11.Hockey.models.League;
import group11.Hockey.models.Player;
import group11.Hockey.models.Team;

public class Validations implements IValidations {

	Conference conferenceObj = new Conference();
	Division divisionObj = new Division();
	IDisplay display = new Display();

	protected boolean isNotValidConference(String conferenceName, List<Conference> conferencesList) {

		if (isStrBlank(conferenceName)) {
			return true;
		} else if (conferenceObj.isConferenceNameValid(conferenceName, conferencesList)) {
			return false;
		} else {
			display.showMessageOnConsole("Conference name does not exist, Please enter valid conference name");
			return true;
		}
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

	protected boolean isNotValidDivision(String divisionName, Conference conferenceItem) {

		if (isStrBlank(divisionName)) {
			return true;
		} else if (divisionObj.isDivisionNameValid(divisionName, conferenceItem.getDivisions())) {
			return false;
		} else {
			display.showMessageOnConsole("Division name does not exist, Please enter valid division name");
			return true;
		}
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

	protected boolean isNotValidTeamName(String teamName, Team teamObj, League league) {
		if (isStrBlank(teamName)) {
			return true;
		} else if (teamObj.isTeamNameValid(teamName, league)) {
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

	public boolean isNotValidGeneralManager(String name, Team teamObj) {
		if (isStrBlank(name)) {
			return true;
		} else {
			return false;
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

	public boolean playerCheck(int playerNumber, League league, List<Integer> selectedValues, List<Player> skaters,
			List<Player> goalies) {
		boolean isPlayerValueNotValid = true;
		String position = league.getFreeAgents().get(playerNumber).getPosition();
		if (playerNumber < 0 || playerNumber > 20) {
			isPlayerValueNotValid = true;
		}
		if (selectedValues.contains(playerNumber)) {
			display.showMessageOnConsole("This player is already selected");
			isPlayerValueNotValid = true;
			return isPlayerValueNotValid;
		} 
		if (position.equalsIgnoreCase("forward") || position.equalsIgnoreCase("defense")) {
			if (skaters.size() <= 18) {
				isPlayerValueNotValid = false;
				selectedValues.add(playerNumber);
				return isPlayerValueNotValid;
			} else {
				display.showMessageOnConsole("Can not select more skaters");
			}
		} else if (position.equalsIgnoreCase("goalie")) {
			if (goalies.size() <= 2) {
				isPlayerValueNotValid = false;
				selectedValues.add(playerNumber);
				return isPlayerValueNotValid;
			} else {
				display.showMessageOnConsole("Can not select more goalies");
			}
		}
		return isPlayerValueNotValid;

	}

	protected boolean isStrBlank(String str) {
		if (str == null || str.isEmpty() || str.split(" +").length == 0) {
			return true;
		}

		return false;
	}
}
