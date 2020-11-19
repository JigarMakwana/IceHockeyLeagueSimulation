package group11.Hockey.BusinessLogic;

import java.util.List;

import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.League.ILeagueDb;

public class LoadTeam extends StateMachineState implements IRenderTeam {
	ICommandLineInput userInputMode;
	League league;
	IDisplay display;
	IValidations validations;
	ILeagueDb leagueDb;

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
		league = renderTeam();
		return DefaultHockeyFactory.makePlayerChoice(league, userInputMode, leagueDb);
	}
	
	@Override
	public League renderTeam() {
		display.showMessageOnConsole("***Load League***\n");
		String teamName;
		boolean isTeamNameValid = false;
		display.showMessageOnConsole("Enter Team Name: ");
		teamName = userInputMode.getValueFromUser();
		if (validations.isStrBlank(teamName)) {
			display.showMessageOnConsole("Not a valid Team name ");
		}
		League league = DefaultHockeyFactory.makeLeague();
		league = (League) league.loadLeague(leagueDb);
		if (league.getLeagueName() == null || league.getLeagueName() == "") {
			display.showMessageOnConsole("Team name does not exist in the system");
		}
		ITeam teamInLeague = DefaultHockeyFactory.makeTeam();
		isTeamNameValid = teamInLeague.isTeamNameValid(teamName, league);
		if(isTeamNameValid) {
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
		}
		else {
			display.showMessageOnConsole("Not a valid Team name ");
		}
		

		return league;
	}


//	@Override
//	public League loadLeagueWithTeamName() throws Exception {
//		display.showMessageOnConsole("***Load League***\n");
//		String teamName;
//
//		display.showMessageOnConsole("Enter Team Name: ");
//		teamName = userInputMode.getValueFromUser();
//		if (validations.isStrBlank(teamName)) {
//			throw new Exception("Not a valid Team name");
//		}
//
//		Team teamObj = new Team();
//
//		League league = teamObj.loadLeagueWithTeamName(teamName, teamDb);
//		if (league.getLeagueName() == null || league.getLeagueName() == "") {
//			throw new Exception("Team name does not exist in the system");
//		}
//
//		List<Conference> conferenceList = league.getConferences();
//		for (Conference conference : conferenceList) {
//			List<Division> divisionList = conference.getDivisions();
//			for (Division division : divisionList) {
//				List<Team> teamList = division.getTeams();
//				for (Team team : teamList) {
//					if (team.getTeamName().equalsIgnoreCase(teamName)) {
//						display.printTeamDetails(league.getLeagueName(), conference.getConferenceName(),
//								division.getDivisionName(), team.getTeamName(), team.getGeneralManager(),
//								team.getHeadCoach());
//					}
//				}
//			}
//		}
//
//		return league;
//	}

}