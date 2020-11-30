// Author: Harry B00856244
package group11.Hockey.BusinessLogic.LeagueSimulation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.InjurySystem.IInjurySystem;
import group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation.IGameSimulation;
import group11.Hockey.BusinessLogic.LeagueSimulation.Interfaces.ICheckAndSimulateTodaySchedule;
import group11.Hockey.BusinessLogic.LeagueSimulation.Interfaces.IParse;
import group11.Hockey.BusinessLogic.models.IAdvance;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IDivision;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITeam;

public class CheckAndSimulateTodaySchedule implements ICheckAndSimulateTodaySchedule {

	private HashMap<String, HashMap<ITeam, ITeam>> schedule;
	private ILeague league;
	private static Logger logger = LogManager.getLogger(CheckAndSimulateTodaySchedule.class);

	public CheckAndSimulateTodaySchedule(HashMap<String, HashMap<ITeam, ITeam>> schedule, ILeague league) {
		super();
		this.schedule = schedule;
		this.league = league;
	}

	@Override
	public void CheckAndSimulateToday(String date) {
		logger.debug("Entered CheckAndSimulateToday()");
		int points;
		int updatePoints;
		int year;
		int updateWin;
		int loss;
		Date possibleSeasonEnd;
		Date possibleSeasonStart;
		Date dateTime;
		ITeam team1;
		ITeam team2;
		ITeam won;
		ITeam lost;
		String time = "00:00:00";
		String id = date + "T" + time;
		String message;

		IParse parse = DefaultHockeyFactory.makeParse();	
		IAdvance advance = DefaultHockeyFactory.makeAdvance();
		List<ITeam> qualifiedTeams = league.getQualifiedTeams();
		HashMap<ITeam, ITeam> todayTeams = new HashMap<>();
		todayTeams = schedule.get(id);
		dateTime = parse.stringToDate(date);
		year = parse.stringToYear(date);
		possibleSeasonStart = parse.stringToDate("29/09/" + Integer.toString(year));
		possibleSeasonEnd = parse.getFirstSaturdayOfAprilInYear(year);	
		
		while (schedule.containsKey(id)) {
			List<IConference> cconferenceList = league.getConferences();
			for (IConference conference : cconferenceList) {
				List<IDivision> divisionList = conference.getDivisions();
				for (IDivision division : divisionList) {
					List<ITeam> teamList = division.getTeams();
					for (ITeam team : teamList) {
						if (todayTeams.containsKey(team)) {
							team1 = team;
							team2 = todayTeams.get(team);
							message = id + " teams are " + team1.getTeamName() + " and " + team2.getTeamName();
							logger.info(message);
							IGameSimulation gamePlay = DefaultHockeyFactory.makeGameSimulation(league, team1, team2);
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
									logger.info(message);
									points = won.getPoints();
									message = "Points are " + points;
									logger.info(message);
									updatePoints = points + 2;
									message = "Updated Points is " + updatePoints;
									logger.info(message);
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
								logger.info(message);
								points = won.getPoints();
								message = "Points are " + points;
								logger.info(message);
								updatePoints = points + 2;
								message = "Updated Points is " + updatePoints;
								logger.info(message);
								won.setPoints(updatePoints);
								won.setLosses(0);
								loss = lost.getLosses();
								loss++;
								lost.setLosses(loss);
							}
							IInjurySystem injury = DefaultHockeyFactory.makeInjurySystem(league);
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
