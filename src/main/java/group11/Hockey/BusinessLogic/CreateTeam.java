package group11.Hockey.BusinessLogic;


import java.util.List;

import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IDivision;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.ICoachDb;
import group11.Hockey.db.IGameplayConfigDb;
import group11.Hockey.db.IManagerDb;
import group11.Hockey.db.IPlayerDb;
import group11.Hockey.db.League.ILeagueDb;

public class CreateTeam implements ICreateTeam {

	private ICommandLineInput commandLineInput;
	IDisplay display;
	IValidations validation;
	League league;
	IConference conference;
	IDivision division;

	public CreateTeam(League leagueObj, ILeagueDb leagueDb, IGameplayConfigDb gameplayConfigDb, IPlayerDb playerDb,
			ICoachDb coachDb, IManagerDb managerDb) {
		this.league = leagueObj;
	}


	public CreateTeam(League league, ICommandLineInput commandLineInput, IDisplay display, IValidations validation,
			IConference conference, IDivision division) {
		this.league = league;
		this.commandLineInput = commandLineInput;
		this.display = display;
		this.validation = validation;
		this.conference = conference;
		this.division = division;
	}

	@Override
	public void createTeamMethod() {
		System.out.println("***Create Team***\\n");
		IUserInputCheck userInputCheck = new UserInputCheck(commandLineInput, validation, display);
		List<Conference> conferencesList = league.getConferences();
		Team newTeam = new Team();
		String conferenceName = userInputCheck.conferenceNameFromUserCheck(conferencesList);
		Conference conferenceItem = conference.getConferencefromConferenceName(conferenceName, conferencesList);
		String divisionName = userInputCheck.divisonNameFromUserCheck(conferenceItem);
		Division divisionItem = division.getDivisionFromDivisionName(divisionName, conferenceItem.getDivisions());
		userInputCheck.teamNameFromUserCheck(newTeam, league);
		display.displayListOfGeneralMangers(league);
		userInputCheck.generalManagerNameFromUserCheck(newTeam, league);
		display.displayListOfCoaches(league);
		userInputCheck.headCoachNameFromUserCheck(newTeam, league);
		display.displayListOfPLayers(league);
		userInputCheck.playerChoiceFromUser(newTeam, league);
		divisionItem.addNewTeamInDivision(newTeam);
	}


}
