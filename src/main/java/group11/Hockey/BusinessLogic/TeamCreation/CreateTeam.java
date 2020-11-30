package group11.Hockey.BusinessLogic.TeamCreation;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.IUserInputCheck;
import group11.Hockey.BusinessLogic.IValidations;
import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IDivision;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.League.ILeagueDb;

/**
 *
 * @author Jatin Partap Rana
 *
 */
public class CreateTeam extends StateMachineState implements IRenderTeam {

	ICommandLineInput commandLineInput;
	IDisplay display;
	IValidations validation;
	ILeague league;
	ILeagueDb leagueDb;
	private static Logger logger = LogManager.getLogger(CreateTeam.class);

	public CreateTeam() {

	}

	public CreateTeam(ILeague league, ICommandLineInput commandLineInput, IDisplay display, IValidations validation,
			ILeagueDb leagueDb) {
		this.league = league;
		this.commandLineInput = commandLineInput;
		this.display = display;
		this.validation = validation;
		this.leagueDb = leagueDb;
	}

	@Override
	public StateMachineState startState() {
		logger.debug("Entered startState()");
		renderTeam();
		return DefaultHockeyFactory.makePlayerChoice(league, commandLineInput, leagueDb, display);
	}

	@Override
	public ILeague renderTeam() {
		logger.debug("Entered renderTeam()");
		display.showMessageOnConsole("***Create Team***\\n");
		try {
			IUserInputCheck userInputCheck = DefaultHockeyFactory.makeUserInputCheck(commandLineInput, validation,
					display);
			List<IConference> conferencesList = league.getConferences();
			IConference conference = DefaultHockeyFactory.makeConference();
			IDivision division = DefaultHockeyFactory.makeDivision();
			ITeam newTeam = DefaultHockeyFactory.makeTeam();
			String conferenceName = userInputCheck.conferenceNameFromUserCheck(conferencesList);
			IConference conferenceItem = conference.getConferencefromConferenceName(conferenceName, conferencesList);
			String divisionName = userInputCheck.divisonNameFromUserCheck(conferenceItem);
			IDivision divisionItem = division.getDivisionFromDivisionName(divisionName, conferenceItem.getDivisions());
			userInputCheck.teamNameFromUserCheck(newTeam, league);
			display.displayListOfGeneralMangers(league);
			userInputCheck.generalManagerNameFromUserCheck(newTeam, league);
			display.displayListOfCoaches(league);
			userInputCheck.headCoachNameFromUserCheck(newTeam, league);
			display.displayListOfPLayers(league);
			userInputCheck.playerChoiceFromUser(newTeam, league);
			divisionItem.addNewTeamInDivision(newTeam);
		} catch (Exception e) {
			logger.error("Exception occcured in create team :"+ e.getMessage());
		}
		return league;
	}

}
