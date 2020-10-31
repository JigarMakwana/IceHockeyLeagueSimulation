package group11.Hockey.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import group11.Hockey.BusinessLogic.models.Advance;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;

public class PlayoffSchedule {

	private League leagueObj;

	public PlayoffSchedule(League leagueObj) {
		this.leagueObj = leagueObj;
	}

	public HashMap<String, HashMap<Team, Team>> generatePlayoffScheduleRound1(String date) {
		Advance advance = new Advance();
		HashMap<String, HashMap<Team, Team>> firstRoundSchedule = new HashMap<>();
		Team team1, team2, firstHighestTeam = null, secondHighestTeam = null, thirdHighestTeam = null, tempTeam = null;
		int firstHighestPoints = 0, secondHighestPoints = 0, thirdHighestPoints = 0, tempPoints, teamPoints;
		String time = "00:00:00";
		List<Team> qualifiedTeams = leagueObj.getQualifiedTeams();
		System.out.println("\n********** Playoff Schedule - First round **********");

		List<Conference> cconferenceList = leagueObj.getConferences();
		for (Conference conference : cconferenceList) {
			List<Team> roundOne = new ArrayList<Team>();
			List<Division> divisionList = conference.getDivisions();
			for (Division division : divisionList) {
				firstHighestPoints = 0;
				secondHighestPoints = 0;
				thirdHighestPoints = 0;
				firstHighestTeam = null;
				secondHighestTeam = null;
				thirdHighestTeam = null;
				tempTeam = null;
				List<Team> teamList = division.getTeams();
				for (Team team : teamList) {
					teamPoints = team.getPoints();
					if (teamPoints > firstHighestPoints) {
						tempPoints = secondHighestPoints;
						secondHighestPoints = firstHighestPoints;
						thirdHighestPoints = tempPoints;
						firstHighestPoints = team.getPoints();

						tempTeam = secondHighestTeam;
						secondHighestTeam = firstHighestTeam;
						thirdHighestTeam = tempTeam;
						firstHighestTeam = team;
					} else if ((team.getPoints() > secondHighestPoints) && (team.getPoints() <= firstHighestPoints)) {
						thirdHighestPoints = secondHighestPoints;
						secondHighestPoints = team.getPoints();

						thirdHighestTeam = secondHighestTeam;
						secondHighestTeam = team;
					} else if ((team.getPoints() > thirdHighestPoints) && (team.getPoints() <= secondHighestPoints)) {
						thirdHighestPoints = team.getPoints();
						thirdHighestTeam = team;
					}
				}
				// teams of one division ends
				roundOne.add(firstHighestTeam);
				roundOne.add(secondHighestTeam);
				roundOne.add(thirdHighestTeam);
				roundOne.add(null); // to populate with appropriate wildcard team

			}

			// get wildcards from remaining teams
			firstHighestPoints = 0;
			secondHighestPoints = 0;
			firstHighestTeam = null;
			secondHighestTeam = null;
			tempTeam = null;
			for (Division division : divisionList) {
				List<Team> teamList = division.getTeams();
				for (Team team : teamList) {
					if ((team == roundOne.get(0)) || (team == roundOne.get(1)) || (team == roundOne.get(2))
							|| (team == roundOne.get(4)) || (team == roundOne.get(5)) || (team == roundOne.get(6))) {
						continue;
					} else {
						if (team.getPoints() > firstHighestPoints) {
							secondHighestPoints = firstHighestPoints;
							firstHighestPoints = team.getPoints();

							secondHighestTeam = firstHighestTeam;
							firstHighestTeam = team;
						} else if ((team.getPoints() > secondHighestPoints)
								&& (team.getPoints() <= firstHighestPoints)) {
							secondHighestPoints = team.getPoints();
							secondHighestTeam = team;
						}
					}
				}
			}
			team1 = roundOne.get(0);
			team2 = roundOne.get(4);
			if (team1.getPoints() > team2.getPoints()) {
				roundOne.set(3, secondHighestTeam);
				roundOne.set(7, firstHighestTeam);
			} else {
				roundOne.set(3, firstHighestTeam);
				roundOne.set(7, secondHighestTeam);
			}
			// teams of one conference ends

			int teamNumber1 = 0;
			int teamNumber2 = 3;
			int teams = 0, series = 0;
			while (teams < 4) { // four final sets of teams(team1,team2) from each conference - FIRST ROUND
				teams++;
				series = 0;
				while (series < 7) {
					series++;
					team1 = roundOne.get(teamNumber1);
					team2 = roundOne.get(teamNumber2);

					HashMap<Team, Team> schedule = new HashMap<>();
					schedule.put(team1, team2);
					firstRoundSchedule.put(date + "T" + time, schedule);
					System.out.println("Scheduled b/w " + team1.getTeamName() + " & " + team2.getTeamName() + " on "
							+ date + " at " + time);

					try {
						time = advance.getAdvanceTime(time, 6);
					} catch (Exception e) {
						e.printStackTrace();
					}

					if (time.equals("18:00:00")) {
						try {
							date = advance.getAdvanceDate(date, 1);
							time = "00:00:00";
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
				qualifiedTeams.add(team1);
				qualifiedTeams.add(team2);
				team1.setWins(0);
				team2.setWins(0);
				if ((teamNumber1 < teamNumber2) && (teamNumber2 - teamNumber1 > 1)) {
					teamNumber1++;
					teamNumber2--;
				} else {
					teamNumber1 = 4;
					teamNumber2 = 7;
				}
			}

		}
		return firstRoundSchedule;
	}

	public HashMap<String, HashMap<Team, Team>> generatePlayoffScheduleReaminingRounds(String date) {
		Advance advance = new Advance();
		HashMap<String, HashMap<Team, Team>> playoffSchedule = new HashMap<>();
		List<Team> qualifiedTeams = leagueObj.getQualifiedTeams();
		Team team1, team2;

		String time = "00:00:00";
		int teamNumber1 = 0;
		int teamNumber2 = 1;
		int teams = 0, series = 0, totalSetTeams, qualifiedTeamsSize;
		qualifiedTeamsSize = qualifiedTeams.size();
		totalSetTeams = (qualifiedTeamsSize / 2);
		if (totalSetTeams == 4) {
			System.out.println("\n********** Playoff Schedule - Second round **********");
		} else if (totalSetTeams == 2) {
			System.out.println("\n********** Playoff Schedule - Semi-Final round **********");
		} else if (totalSetTeams == 1) {
			System.out.println("\n********** Playoff Schedule - Final round **********");
		}
		// final sets of teams(team1,team2)
		while (teams < totalSetTeams) { 
			teams++;
			series = 0;
			while (series < 7) {
				series++;
				team1 = qualifiedTeams.get(teamNumber1);
				team2 = qualifiedTeams.get(teamNumber2);

				team1.setWins(0);
				team2.setWins(0);


				HashMap<Team, Team> schedule = new HashMap<>();
				schedule.put(team1, team2);
				playoffSchedule.put(date + "T" + time, schedule);
				System.out.println("Scheduled b/w " + team1.getTeamName() + " & " + team2.getTeamName() + " on " + date
						+ " at " + time);
				try {
					time = advance.getAdvanceTime(time, 6);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (time.equals("18:00:00")) {
					try {
						date = advance.getAdvanceDate(date, 1);
						time = "00:00:00";
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
			teamNumber1 = teamNumber1 + 2;
			teamNumber2 = teamNumber2 + 2;
			if (teamNumber2 >= qualifiedTeamsSize) {
				break;
			}
		}
		return playoffSchedule;
	}
}