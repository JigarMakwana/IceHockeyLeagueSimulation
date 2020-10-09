package group11.Hockey;

import java.util.ArrayList;
import java.util.List;

import group11.Hockey.db.Team.ITeamDb;
import group11.Hockey.db.Team.TeamDbImpl;
import group11.Hockey.models.Conference;
import group11.Hockey.models.Division;
import group11.Hockey.models.League;
import group11.Hockey.models.Team;

public class LoadTeam {
	private IUserInputMode userInputMode;
	private ITeamDb teamDb;

	public LoadTeam(IUserInputMode userInputMode, ITeamDb teamDb) {
		super();
		this.userInputMode = userInputMode;
		this.teamDb = teamDb;
	}

	public Team getTeam() throws Exception {
		userInputMode.displayMessage("***Load Team***\n");
		String teamName;

		userInputMode.displayMessage("Enter Team Name: ");
		teamName = userInputMode.getName();
		if (isNotValidTeamName(teamName)) {
			throw new Exception("Not a valid Team name");
		}

		Team teamObj = new Team();

		List<League> leaguesList = teamObj.loadTeamWithTeamName(teamName, teamDb);
		if (leaguesList.size() == 0) {
			throw new Exception("Team name does not exist in the system");
		} else if (leaguesList.size() > 1) {
			userInputMode.displayMessage("Team Name found in the following Leagues: ");

			int count = 0;
			List<String> LeagueNames = new ArrayList<String>();

			boolean isNotValidNumber = true;
			int leagueOption = 0;
			while (isNotValidNumber || leagueOption <= 0 || leagueOption >= count) {
				count = 1;
				userInputMode.displayMessage("Choose following options: ");
				for (League league : leaguesList) {
					userInputMode.displayMessage("\"" + count + "\"" + " for " + league.getLeagueName());
					LeagueNames.add(league.getLeagueName());
					count++;
				}
				try {
					leagueOption = userInputMode.getInt();
					isNotValidNumber = false;
				} catch (Exception e) {
					userInputMode.displayMessage("not a valid number");
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
			String managerName, String hcName) {
		userInputMode.displayMessage("**Team details:");
		userInputMode.displayMessage("League name-> " + leagueName);
		userInputMode.displayMessage("-Conference name-> " + conferenceName);
		userInputMode.displayMessage("--Division name-> " + divisionName);
		userInputMode.displayMessage("---Team name-> " + teamName);
		userInputMode.displayMessage("---GeneralManager-> " + managerName);
		userInputMode.displayMessage("---HeadCoach-> " + hcName);
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
