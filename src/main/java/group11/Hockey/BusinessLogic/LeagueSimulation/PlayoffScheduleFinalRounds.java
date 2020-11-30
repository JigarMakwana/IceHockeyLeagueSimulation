// Author: Harry B00856244
package group11.Hockey.BusinessLogic.LeagueSimulation;

import java.util.HashMap;
import java.util.List;

import group11.Hockey.InputOutput.ICommandLineInput;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.IValidations;
import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.BusinessLogic.LeagueSimulation.Interfaces.IScheduleStrategy;
import group11.Hockey.BusinessLogic.models.IAdvance;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.ITimeLine;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.League.ILeagueDb;

public class PlayoffScheduleFinalRounds implements IScheduleStrategy {

	IDisplay display;
	private ICommandLineInput commandLineInput;
	private IValidations validation;
	private static Logger logger = LogManager.getLogger(PlayoffScheduleFinalRounds.class);

	public PlayoffScheduleFinalRounds(IDisplay display, ICommandLineInput commandLineInput, IValidations validation) {
		this.display = display;
		this.commandLineInput = commandLineInput;
		this.validation = validation;
	}

	public StateMachineState getSchedule(ILeague league, ILeagueDb leagueDb) {
		logger.debug("Entered getSchedule()");
		ITimeLine timeLine = league.getTimeLine();
		String date = timeLine.getCurrentDate();
		IAdvance advance = DefaultHockeyFactory.makeAdvance();
		HashMap<String, HashMap<ITeam, ITeam>> playoffSchedule = new HashMap<>();
		List<ITeam> qualifiedTeams = league.getQualifiedTeams();
		ITeam team1;
		ITeam team2;
		String message;
		if(display == null) {
			display = DefaultHockeyFactory.makeDisplay();
		}
		String time = "00:00:00";
		int teamNumber1 = 0;
		int teamNumber2 = 1;
		int teams = 0;
		int series = 0;
		int totalSetTeams;
		int qualifiedTeamsSize;
		qualifiedTeamsSize = qualifiedTeams.size();
		totalSetTeams = (qualifiedTeamsSize / 2);
		if (totalSetTeams == 4) {
			logger.info("Playoff Schedule - Second round");
			message = "\n********** Playoff Schedule - Second round **********";
			logger.info(message);
		} else if (totalSetTeams == 2) {
			logger.info("Playoff Schedule - Semi-Final round");
			message = "\n********** Playoff Schedule - Semi-Final round **********";
			logger.info(message);
		} else if (totalSetTeams == 1) {
			logger.info("Playoff Schedule - Final round");
			message = "\n********** Playoff Schedule - Final round **********";
			logger.info(message);
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

				HashMap<ITeam, ITeam> schedule = new HashMap<>();
				schedule.put(team1, team2);
				playoffSchedule.put(date + "T" + time, schedule);
				message = "Scheduled b/w " + team1.getTeamName() + " & " + team2.getTeamName() + " on " + date + " at "
						+ time;
				logger.info(message);
				try {
					time = advance.getAdvanceTime(time, 6);
				} catch (Exception e) {
					logger.error("Error occured : "+e);
					e.printStackTrace();
				}

				if (time.equals("18:00:00")) {
					try {
						date = advance.getAdvanceDate(date, 1);
						time = "00:00:00";
					} catch (Exception e1) {
						logger.error("Error occured : "+e1);
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
		league.setSchedule(playoffSchedule);
		return DefaultHockeyFactory.makeTrainingPlayer(league, leagueDb, display, commandLineInput, validation);
	}
}
