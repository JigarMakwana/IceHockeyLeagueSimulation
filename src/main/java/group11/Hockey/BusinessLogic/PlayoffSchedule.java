package group11.Hockey.BusinessLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.IPrintToConsole;
import group11.Hockey.InputOutput.PrintToConsole;

public class PlayoffSchedule implements IPlayoffSchedule {

	private League league;

	public PlayoffSchedule(League league) {
		this.league = league;
	}

	@Override
	public HashMap<String, HashMap<Team, Team>> generatePlayoffScheduleRound1(String date) {
		IAdvance advance = new Advance();
		IPrintToConsole console = new PrintToConsole();
		Team team1;
		Team team2;
		Team firstHighestTeam = null;
		Team secondHighestTeam = null;
		Team thirdHighestTeam = null;
		Team tempTeam = null;
		int firstHighestPoints = 0;
		int secondHighestPoints = 0;
		int thirdHighestPoints = 0;
		int tempPoints;
		int teamPoints;
		String time = "00:00:00";
		String message;
		List<Team> qualifiedTeams = league.getQualifiedTeams();
		HashMap<String, HashMap<Team, Team>> firstRoundSchedule = new HashMap<>();
		message = "\n********** Playoff Schedule - First round **********";
		console.print(message);
		List<Conference> cconferenceList = league.getConferences();
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
				roundOne.add(firstHighestTeam);
				roundOne.add(secondHighestTeam);
				roundOne.add(thirdHighestTeam);
				roundOne.add(null);
			}
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
			int teamNumber1 = 0;
			int teamNumber2 = 3;
			int teams = 0, series = 0;
			while (teams < 4) {
				teams++;
				series = 0;
				while (series < 7) {
					series++;
					team1 = roundOne.get(teamNumber1);
					team2 = roundOne.get(teamNumber2);
					HashMap<Team, Team> schedule = new HashMap<>();
					schedule.put(team1, team2);
					firstRoundSchedule.put(date + "T" + time, schedule);
					message = "Scheduled b/w " + team1.getTeamName() + " & " + team2.getTeamName() + " on " + date
							+ " at " + time;
					console.print(message);
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

	@Override
	public HashMap<String, HashMap<Team, Team>> generatePlayoffScheduleRemainingRounds(String date) {
		IAdvance advance = new Advance();
		IPrintToConsole console = new PrintToConsole();
		Team team1;
		Team team2;
		String message;
		String time = "00:00:00";
		int teamNumber1 = 0;
		int teamNumber2 = 1;
		int teams = 0;
		int series = 0;
		int totalSetTeams;
		int qualifiedTeamsSize;
		HashMap<String, HashMap<Team, Team>> playoffSchedule = new HashMap<>();
		List<Team> qualifiedTeams = league.getQualifiedTeams();
		qualifiedTeamsSize = qualifiedTeams.size();
		totalSetTeams = (qualifiedTeamsSize / 2);
		if (totalSetTeams == 4) {
			message = "\n********** Playoff Schedule - Second round **********";
			console.print(message);
		} else if (totalSetTeams == 2) {
			message = "\n********** Playoff Schedule - Semi-Final round **********";
			console.print(message);
		} else if (totalSetTeams == 1) {
			message = "\n********** Playoff Schedule - Final round **********";
			console.print(message);
		}
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
				message = "Scheduled b/w " + team1.getTeamName() + " & " + team2.getTeamName() + " on " + date + " at "
						+ time;
				console.print(message);
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