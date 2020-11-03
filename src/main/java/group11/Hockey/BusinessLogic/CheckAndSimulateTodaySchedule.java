package group11.Hockey.BusinessLogic;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.IPrintToConsole;
import group11.Hockey.InputOutput.PrintToConsole;

public class CheckAndSimulateTodaySchedule implements ICheckAndSimulateTodaySchedule {

	private HashMap<String, HashMap<Team, Team>> schedule;
	private League league;

	public CheckAndSimulateTodaySchedule(HashMap<String, HashMap<Team, Team>> schedule, League league) {
		super();
		this.schedule = schedule;
		this.league = league;
	}

	@Override
	public void CheckAndSimulateToday(String date) {
		IParse parse = new Parse();
		IAdvance advance = new Advance();
		IPrintToConsole console = new PrintToConsole();
		String message;
		String time = "00:00:00";
		String id = date + "T" + time;
		int points;
		int updatePoints;
		int year;
		int updateWin;
		int loss;
		Date possibleSeasonEnd;
		Date possibleSeasonStart;
		Date dateTime;
		Team team1;
		Team team2;
		Team won;
		Team lost;
		HashMap<Team, Team> todayTeams = new HashMap<>();
		todayTeams = schedule.get(id);
		List<Team> qualifiedTeams = league.getQualifiedTeams();
		dateTime = parse.stringToDate(date);
		year = parse.stringToYear(date);
		possibleSeasonStart = parse.stringToDate("29/09/" + Integer.toString(year));
		possibleSeasonEnd = parse.getFirstSaturdayOfAprilInYear(year);
		while (schedule.containsKey(id)) {
			List<Conference> cconferenceList = league.getConferences();
			for (Conference conference : cconferenceList) {
				List<Division> divisionList = conference.getDivisions();
				for (Division division : divisionList) {
					List<Team> teamList = division.getTeams();
					for (Team team : teamList) {
						if (todayTeams.containsKey(team)) {
							team1 = team;
							team2 = todayTeams.get(team);
							message = id + " teams are " + team1.getTeamName() + " and " + team2.getTeamName();
							console.print(message);
							long wonTeam = Math.round(Math.random());
							if (wonTeam == 0) {
								won = team1;
								lost = team2;
							} else {
								won = team2;
								lost = team1;
							}
							if ((dateTime.compareTo(possibleSeasonStart) <= 0)
									&& (dateTime.compareTo(possibleSeasonEnd) > 0)) {
								if ((team1.getWins() >= 4) || (team2.getWins() >= 4)) {
									if (team1.getWins() == 4) {
										message = "Winner already declared :" + team1.getTeamName() + "(4-"
												+ team2.getWins() + ")";
										console.print(message);
									} else {
										message = "Winner already declared :" + team2.getTeamName() + "("
												+ team1.getWins() + "-4)";
										console.print(message);
									}
									continue;
								} else {
									message = "Team Won : " + won.getTeamName();
									console.print(message);
									points = won.getPoints();
									message = "Points are " + points;
									console.print(message);
									updatePoints = points + 2;
									message = "Updated Points is " + updatePoints;
									console.print(message);
									won.setPoints(updatePoints);
									won.setLosses(0);
									loss = lost.getLosses();
									loss++;
									lost.setLosses(loss);
									updateWin = won.getWins();
									updateWin++;
									won.setWins(updateWin);
									if (updateWin == 4) {
										if (qualifiedTeams.contains(lost)) {
											qualifiedTeams.remove(lost);
										}
									}
								}
							} else {
								message = "Team Won : " + won.getTeamName();
								console.print(message);
								points = won.getPoints();
								message = "Points are " + points;
								console.print(message);
								updatePoints = points + 2;
								message = "Updated Points is " + updatePoints;
								console.print(message);
								won.setPoints(updatePoints);
								won.setLosses(0);
								loss = lost.getLosses();
								loss++;
								lost.setLosses(loss);
							}
							InjurySystem injury = new InjurySystem(league);
							injury.setInjuryToPlayers(team1);
							injury.setInjuryToPlayers(team2);
						}
					}
				}
			}

			if ((dateTime.compareTo(possibleSeasonStart) <= 0) && (dateTime.compareTo(possibleSeasonEnd) > 0)) {
				time = advance.getAdvanceTime(time, 5);
				id = date + "T" + time;
				todayTeams = schedule.get(id);
			}
			time = advance.getAdvanceTime(time, 1);
			id = date + "T" + time;
			todayTeams = schedule.get(id);
		}
	}

}
