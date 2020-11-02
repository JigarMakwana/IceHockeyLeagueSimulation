package group11.Hockey.BusinessLogic;

import java.util.List;

import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.Display;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.Team.ITeamDb;

public class LoadLeague implements ILoadLeague{
	private ICommandLineInput userInputMode;
	private ITeamDb teamDb;
	private IDisplay display = new Display();
	IValidations validations = new Validations();

	public LoadLeague(ICommandLineInput userInputMode, ITeamDb teamDb) {
		super();
		this.userInputMode = userInputMode;
		this.teamDb = teamDb;
	}

	public LoadLeague(ITeamDb teamDb) {
		super();
		this.teamDb = teamDb;
	}
	@Override
	public League loadLeagueWithTeamName() throws Exception {
		display.showMessageOnConsole("***Load League***\n");
		String teamName;

		display.showMessageOnConsole("Enter Team Name: ");
		teamName = userInputMode.getValueFromUser();
		if (validations.isStrBlank(teamName)) {
			throw new Exception("Not a valid Team name");
		}

		Team teamObj = new Team();

		League league = teamObj.loadLeagueWithTeamName(teamName, teamDb);
		if (league.getLeagueName() == null || league.getLeagueName() == "") {
			throw new Exception("Team name does not exist in the system");
		}

		List<Conference> conferenceList = league.getConferences();
		for (Conference conference : conferenceList) {
			List<Division> divisionList = conference.getDivisions();
			for (Division division : divisionList) {
				List<Team> teamList = division.getTeams();
				for (Team team : teamList) {
					if (team.getTeamName().equalsIgnoreCase(teamName)) {
						display.printTeamDetails(league.getLeagueName(), conference.getConferenceName(),
								division.getDivisionName(), team.getTeamName(), team.getGeneralManager(),
								team.getHeadCoach());
					}
				}
			}
		}

		return league;
	}

}
