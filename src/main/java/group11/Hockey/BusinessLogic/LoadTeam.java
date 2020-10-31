package group11.Hockey.BusinessLogic;

import java.util.ArrayList;
import java.util.List;

import group11.Hockey.BusinessLogic.models.Coach;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.Display;
import group11.Hockey.InputOutput.ICommandLineInput;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.Team.ITeamDb;

public class LoadTeam {
	private ICommandLineInput userInputMode;
	private ITeamDb teamDb;
	private IDisplay display = new Display();

	public LoadTeam(ICommandLineInput userInputMode, ITeamDb teamDb) {
		super();
		this.userInputMode = userInputMode;
		this.teamDb = teamDb;
	}
	
	public LoadTeam( ITeamDb teamDb) {
		super();
		this.teamDb = teamDb;
	}

	public Team getTeam() throws Exception {
		display.showMessageOnConsole("***Load Team***\n");
		String teamName = "Team 2";

		display.showMessageOnConsole("Enter Team Name: ");
		//teamName = userInputMode.getValueFromUser();
		if (isNotValidTeamName(teamName)) {
			throw new Exception("Not a valid Team name");
		}

		Team teamObj = new Team();

		List<League> leaguesList = teamObj.loadTeamWithTeamName(teamName, teamDb);
		if (leaguesList.size() == 0) {
			throw new Exception("Team name does not exist in the system");
		} else if (leaguesList.size() > 1) {
			display.showMessageOnConsole("Team Name found in the following Leagues: ");

			int count = 0;
			List<String> LeagueNames = new ArrayList<String>();

			boolean isNotValidNumber = true;
			int leagueOption = 0;
			while (isNotValidNumber || leagueOption <= 0 || leagueOption >= count) {
				count = 1;
				display.showMessageOnConsole("Choose following options: ");
				for (League league : leaguesList) {
					display.showMessageOnConsole("" + count + "\"" + " for " + league.getLeagueName());
					LeagueNames.add(league.getLeagueName());
					count++;
				}
				try {
					leagueOption = userInputMode.getInt();
					isNotValidNumber = false;
				} catch (Exception e) {
					display.showMessageOnConsole("not a valid number");
				}
			}

			String leagueName = LeagueNames.get(leagueOption - 1);

			for (League league : leaguesList) {
				if (league.getLeagueName() == leagueName) {
					List<Conference> conferenceList = league.getConferences();
					for (Conference conference : conferenceList) {
						List<Division> divisionList = conference.getDivisions();
						for (Division division : divisionList) {
							List<Team> teamList = division.getTeams();
							for (Team team : teamList) {
								if (team.getTeamName().equalsIgnoreCase(teamName)) {
									printLoadTeamDetails(leagueName, conference.getConferenceName(),
											division.getDivisionName(), team.getTeamName(), team.getGeneralManager(),
											team.getHeadCoach());
									return team;
								}
							}
						}
					}

				}
			}

		} else if (leaguesList.size() == 1) {
			for (League league : leaguesList) {
				List<Conference> conferenceList = league.getConferences();
				for (Conference conference : conferenceList) {
					List<Division> divisionList = conference.getDivisions();
					for (Division division : divisionList) {
						List<Team> teamList = division.getTeams();
						for (Team team : teamList) {
							if (team.getTeamName().equalsIgnoreCase(teamName)) {
								printLoadTeamDetails(league.getLeagueName(), conference.getConferenceName(),
										division.getDivisionName(), team.getTeamName(), team.getGeneralManager(),
										team.getHeadCoach());
								return team;
							}
						}
					}
				}

			}
		}

		return null;
	}

	private void printLoadTeamDetails(String leagueName, String conferenceName, String divisionName, String teamName,
			String managerName, Coach coach) {
		display.showMessageOnConsole("**Team details:");
		display.showMessageOnConsole("League name-> " + leagueName);
		display.showMessageOnConsole("-Conference name-> " + conferenceName);
		display.showMessageOnConsole("--Division name-> " + divisionName);
		display.showMessageOnConsole("---Team name-> " + teamName);
		display.showMessageOnConsole("---GeneralManager-> " + managerName);
		display.showMessageOnConsole("---HeadCoach-> " + coach);
	}

	private boolean isNotValidTeamName(String teamName) {

		if (isStrBlank(teamName)) {
			return true;
		}

		return false;
	}

	private boolean isStrBlank(String str) {
		if (str == null || str.isEmpty() || str.split(" +").length == 0) {
			return true;
		}

		return false;
	}
}
