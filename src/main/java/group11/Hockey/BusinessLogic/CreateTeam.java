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
import group11.Hockey.db.League.ILeagueDb;

public class CreateTeam extends StateMachineState implements IRenderTeam {

	ICommandLineInput commandLineInput;
	IDisplay display;
	IValidations validation;
	League league;
	ILeagueDb leagueDb;

	public CreateTeam() {

	}
	

	public CreateTeam(League league, ICommandLineInput commandLineInput, IDisplay display, IValidations validation,
			ILeagueDb leagueDb) {
		this.league = league;
		this.commandLineInput = commandLineInput;
		this.display = display;
		this.validation = validation;
		this.leagueDb = leagueDb;
	}

	@Override
	public StateMachineState startState() {
		//renderTeam();
		return DefaultHockeyFactory.makePlayerChoice(league, commandLineInput, leagueDb);
	}

	@Override
	public League renderTeam() {
		System.out.println("***Create Team***\\n");
		IUserInputCheck userInputCheck = DefaultHockeyFactory.makeUserInputCheck(commandLineInput, validation, display);
		List<Conference> conferencesList = league.getConferences();
		IConference conference = DefaultHockeyFactory.makeConference();
		IDivision division = DefaultHockeyFactory.makeDivision();
		Team newTeam = DefaultHockeyFactory.makeTeam();
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
		return league;
	}

//	public void createTeamMethod() {
//		System.out.println("***Create Team***\\n");
//		IUserInputCheck userInputCheck = new UserInputCheck(commandLineInput, validation, display);
//		List<Conference> conferencesList = league.getConferences();
//		Team newTeam = new Team();
//		String conferenceName = userInputCheck.conferenceNameFromUserCheck(conferencesList);
//		Conference conferenceItem = conference.getConferencefromConferenceName(conferenceName, conferencesList);
//		String divisionName = userInputCheck.divisonNameFromUserCheck(conferenceItem);
//		Division divisionItem = division.getDivisionFromDivisionName(divisionName, conferenceItem.getDivisions());
//		userInputCheck.teamNameFromUserCheck(newTeam, league);
//		display.displayListOfGeneralMangers(league);
//		userInputCheck.generalManagerNameFromUserCheck(newTeam, league);
//		display.displayListOfCoaches(league);
//		userInputCheck.headCoachNameFromUserCheck(newTeam, league);
//		display.displayListOfPLayers(league);
//		userInputCheck.playerChoiceFromUser(newTeam, league);
//		divisionItem.addNewTeamInDivision(newTeam);
//	}

}
