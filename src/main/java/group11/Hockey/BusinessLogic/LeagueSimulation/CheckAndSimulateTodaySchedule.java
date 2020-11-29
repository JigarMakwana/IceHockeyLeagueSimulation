package group11.Hockey.BusinessLogic.LeagueSimulation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.InjurySystem;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.GameSimulation;
import group11.Hockey.BusinessLogic.models.Advance;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.IAdvance;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.IPrintToConsole;
import group11.Hockey.InputOutput.PrintToConsole;

public class CheckAndSimulateTodaySchedule implements ICheckAndSimulateTodaySchedule {

	private HashMap<String, HashMap<Team, Team>> schedule;
	private ILeague league;
	private static Logger logger = LogManager.getLogger(CheckAndSimulateTodaySchedule.class);

	public CheckAndSimulateTodaySchedule(HashMap<String, HashMap<Team, Team>> schedule, ILeague league) {
		super();
		this.schedule = schedule;
		this.league = league;
	}

	@Override
	public void CheckAndSimulateToday(String date) {
		logger.info("Entered CheckAndSimulateToday()");
		String time = "00:00:00", id = date + "T" + time;
		HashMap<Team, Team> todayTeams = new HashMap<>();
		todayTeams = schedule.get(id);
		int points, updatePoints, year, updateWin, loss;
		Date possibleSeasonEnd, possibleSeasonStart, dateTime;
		List<Team> qualifiedTeams = league.getQualifiedTeams();

		IParse parse = new Parse();
		dateTime = parse.stringToDate(date);
		year = parse.stringToYear(date);
		possibleSeasonStart = parse.stringToDate("29/09/" + Integer.toString(year));
		possibleSeasonEnd = parse.getFirstSaturdayOfAprilInYear(year);

		Team team1, team2;
		ITeam won, lost;
		IAdvance advance = new Advance();
		IPrintToConsole console = new PrintToConsole();
		String message;
		while (schedule.containsKey(id)) {
			List<IConference> cconferenceList = league.getConferences();
			for (IConference conference : cconferenceList) {
				List<Division> divisionList = conference.getDivisions();
				for (Division division : divisionList) {
					List<Team> teamList = division.getTeams();
					for (Team team : teamList) {
						if (todayTeams.containsKey(team)) {
							team1 = team;
							team2 = todayTeams.get(team);
							message = id + " teams are " + team1.getTeamName() + " and " + team2.getTeamName();
							console.print(message);
							// game play start
							GameSimulation gamePlay = new GameSimulation(league, team1, team2);
							won = gamePlay.startGamePlay();
							if (won.getTeamName().equalsIgnoreCase(team1.getTeamName())) {
								lost = team2;
							} else {
								lost = team1;
							}

							if ((dateTime.compareTo(possibleSeasonStart) <= 0)
									&& (dateTime.compareTo(possibleSeasonEnd) > 0)) {
								if ((team1.getWins() < 4) && (team2.getWins() < 4)) {

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

			// in between stanley schedule
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
