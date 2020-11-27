package group11.Hockey.BusinessLogic.LeagueSimulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.BusinessLogic.models.Advance;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.IAdvance;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITimeLine;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.InputOutput.IPrintToConsole;
import group11.Hockey.InputOutput.PrintToConsole;
import group11.Hockey.db.League.ILeagueDb;

public class PlayoffSchedule implements IScheduleStrategy {
	
	IDisplay display;
	
	public PlayoffSchedule() {
		
	}
	
	public PlayoffSchedule(IDisplay display) {
		this.display = display;
	}
	
	private static Logger logger = LogManager.getLogger(PlayoffSchedule.class);

	@Override
	public StateMachineState getSchedule(ILeague league, ILeagueDb leagueDb) {
		logger.info("Entered getSchedule() and started scheduling");
		ITimeLine timeLine = league.getTimeLine();
		String date = timeLine.getStanleyDate();
		HashMap<String, HashMap<Team, Team>> firstRoundSchedule = new HashMap<>();
		Team team1, team2, firstHighestTeam = null, secondHighestTeam = null, thirdHighestTeam = null, tempTeam = null;
		int firstHighestPoints = 0, secondHighestPoints = 0, thirdHighestPoints = 0, tempPoints, teamPoints;
		String time = "00:00:00", message;
		List<Team> qualifiedTeams = league.getQualifiedTeams();
		IAdvance advance = new Advance();
		IPrintToConsole console = new PrintToConsole();
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
			// four final sets of teams(team1,team2) from each conference - FIRST ROUND
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
						logger.error("Exception occured : "+e);
						e.printStackTrace();
					}

					if (time.equals("18:00:00")) {
						try {
							date = advance.getAdvanceDate(date, 1);
							time = "00:00:00";
						} catch (Exception e1) {
							logger.error("Exception occured : "+e1);
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
		league.setSchedule(firstRoundSchedule);
		return DefaultHockeyFactory.makeTrainingPlayer(league, leagueDb, display);

	}
}