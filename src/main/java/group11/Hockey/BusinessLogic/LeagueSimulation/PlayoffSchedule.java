// Author: Harry B00856244
package group11.Hockey.BusinessLogic.LeagueSimulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import group11.Hockey.InputOutput.ICommandLineInput;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.BusinessLogic.LeagueSimulation.Interfaces.IScheduleStrategy;
import group11.Hockey.BusinessLogic.Validations.IValidations;
import group11.Hockey.BusinessLogic.models.IAdvance;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IDivision;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.ITimeLine;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.League.ILeagueDb;

public class PlayoffSchedule implements IScheduleStrategy {

	IDisplay display;
	private ICommandLineInput commandLineInput;
	private IValidations validation;
	private static Logger logger = LogManager.getLogger(PlayoffSchedule.class);


	public PlayoffSchedule(IDisplay display, ICommandLineInput commandLineInput, IValidations validation) {
		this.display = display;
		this.commandLineInput = commandLineInput;
		this.validation = validation;
	}
	
	public PlayoffSchedule() {
	}

	@Override
	public StateMachineState getSchedule(ILeague league, ILeagueDb leagueDb) {
		logger.debug("Entered getSchedule() and started scheduling");
		ITimeLine timeLine = league.getTimeLine();
		String date = timeLine.getStanleyDate();
		HashMap<String, HashMap<ITeam, ITeam>> firstRoundSchedule = new HashMap<>();
		ITeam team1;
		ITeam team2;
		ITeam firstHighestTeam = null;
		ITeam secondHighestTeam = null;
		ITeam thirdHighestTeam = null;
		ITeam tempTeam = null;
		int firstHighestPoints = 0;
		int secondHighestPoints = 0;
		int thirdHighestPoints = 0;
		int tempPoints;
		int teamPoints;
		String time = "00:00:00";
		String message;
		List<ITeam> qualifiedTeams = league.getQualifiedTeams();
		IAdvance advance = DefaultHockeyFactory.makeAdvance();
		message = "\n********** Playoff Schedule - First round **********";
		logger.info(message);
		List<IConference> cconferenceList = league.getConferences();
		for (IConference conference : cconferenceList) {
			List<ITeam> roundOne = new ArrayList<>();
			List<IDivision> divisionList = conference.getDivisions();
			for (IDivision division : divisionList) {
				firstHighestPoints = 0;
				secondHighestPoints = 0;
				thirdHighestPoints = 0;
				firstHighestTeam = null;
				secondHighestTeam = null;
				thirdHighestTeam = null;
				tempTeam = null;
				List<ITeam> teamList = division.getTeams();
				for (ITeam team : teamList) {
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
			for (IDivision division : divisionList) {
				List<ITeam> teamList = division.getTeams();
				for (ITeam team : teamList) {
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

					HashMap<ITeam, ITeam> schedule = new HashMap<>();
					schedule.put(team1, team2);
					firstRoundSchedule.put(date + "T" + time, schedule);
					message = "Scheduled b/w " + team1.getTeamName() + " & " + team2.getTeamName() + " on " + date
							+ " at " + time;
					logger.info(message);
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
				qualifiedTeams.add((Team)team1);
				qualifiedTeams.add((Team)team2);
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
		return DefaultHockeyFactory.makeTrainingPlayer(league, leagueDb, display, commandLineInput, validation);

	}
}
