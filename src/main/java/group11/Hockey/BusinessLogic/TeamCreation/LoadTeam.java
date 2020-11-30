package group11.Hockey.BusinessLogic.TeamCreation;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.IValidations;
import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.League.ILeagueDb;

public class LoadTeam extends StateMachineState implements IRenderTeam {
	ICommandLineInput userInputMode;
	League league;
	IDisplay display;
	IValidations validations;
	ILeagueDb leagueDb;
	private static Logger logger = LogManager.getLogger(LoadTeam.class);

	public LoadTeam() {

	}

	public LoadTeam(ICommandLineInput userInputMode, IDisplay display, IValidations validations, ILeagueDb leagueDb) {
		super();
		this.userInputMode = userInputMode;
		this.display = display;
		this.validations = validations;
		this.leagueDb = leagueDb;

	}

	@Override
	public StateMachineState startState() {
		logger.debug("Entered startState()");
		league = renderTeam();
		return DefaultHockeyFactory.makePlayerChoice(league, userInputMode, leagueDb, display);
	}

	@Override
	public League renderTeam() {
		logger.debug("Entered renderTeam()");
		display.showMessageOnConsole("***Load League***\n");
		String teamName;
		display.showMessageOnConsole("Enter Team Name: ");
		teamName = userInputMode.getValueFromUser();
		if (validations.isStrBlank(teamName)) {
			logger.error("Invalid team name :");
			display.showMessageOnConsole("Not a valid Team name ");
		}
		League league = DefaultHockeyFactory.makeLeague();
		league = (League) league.loadLeague(leagueDb);
		if (league.getLeagueName() == null || league.getLeagueName() == "") {
			logger.error("Team name "+teamName+" doesn't exist in system");
			display.showMessageOnConsole("Team name "+teamName+" does not exist in the system");
		}
		ITeam teamInLeague = DefaultHockeyFactory.makeTeam();
		teamInLeague.isTeamNameValid(teamName, league);
			logger.info("Team name "+teamName+" is valid");
			List<IConference> conferenceList = league.getConferences();
			for (IConference conference : conferenceList) {
				List<Division> divisionList = conference.getDivisions();
				for (Division division : divisionList) {
					List<ITeam> teamList = division.getTeams();
					for (ITeam team : teamList) {
						if (team.getTeamName().equalsIgnoreCase(teamName)) {
							display.printTeamDetails(league.getLeagueName(), conference.getConferenceName(),
									division.getDivisionName(), team.getTeamName(), team.getGeneralManager().getName(),
									team.getHeadCoach());
						}
					}
				}
			}

		return league;
	}

}
