package group11.Hockey;

import java.util.List;

import group11.Hockey.db.League.ILeagueDb;
import group11.Hockey.models.Conference;
import group11.Hockey.models.Division;
import group11.Hockey.models.League;
import group11.Hockey.models.Team;

public class ValidateCreateTeam {
	
	protected League leagueObj;
	protected IUserInputMode userInputMode;
	protected ILeagueDb leagueDb;
	
	Conference conferenceObj = new Conference();
	Division divisionObj = new Division();
	
	public ValidateCreateTeam(League leagueObj, IUserInputMode userInputMode, ILeagueDb leagueDb) {
		super();
		this.leagueObj = leagueObj;
		this.userInputMode = userInputMode;
		this.leagueDb = leagueDb;
	}

	protected boolean isNotValidConference(String conferenceName, List<Conference> conferencesList) {

		if (isStrBlank(conferenceName)) {
			return true;
		} else if (conferenceObj.isConferenceNameValid(conferenceName, conferencesList)) {
			return false;
		} else {
			userInputMode.displayMessage("Conference name does not exist, Please enter valid conference name");
			return true;
		}
	}

	protected boolean isNotValidDivision(String divisionName, Conference conferenceItem) {

		if (isStrBlank(divisionName)) {
			return true;
		} else if (divisionObj.isDivisionNameValid(divisionName, conferenceItem.getDivisions())) {
			return false;
		} else {
			userInputMode.displayMessage("Division name does not exist, Please enter valid division name");
			return true;
		}
	}

	protected boolean isNotValidTeamName(String teamName, Team teamObj) {
		if (isStrBlank(teamName)) {
			return true;
		} else if (teamObj.isTeamNameValid(teamName, leagueObj)) {
			return false;
		}
		userInputMode.displayMessage("Team name already exists in this League");
		return true;
	}

	protected boolean isNotValidGeneralManager(String name, Team teamObj) {
		if (isStrBlank(name)) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean isNotValidHeadCoach(String name, Team teamObj) {
		if (isStrBlank(name)) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean isStrBlank(String str) {
		if (str == null || str.isEmpty() || str.split(" +").length == 0) {
			return true;
		}

		return false;
	}
}
