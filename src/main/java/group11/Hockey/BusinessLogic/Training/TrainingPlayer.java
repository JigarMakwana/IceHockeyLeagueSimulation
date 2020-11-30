package group11.Hockey.BusinessLogic.Training;

import java.util.Date;
import java.util.List;

import group11.Hockey.BusinessLogic.Trading.TradingFactory;
import group11.Hockey.InputOutput.ICommandLineInput;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.IValidations;
import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.BusinessLogic.LeagueSimulation.CheckAndSimulateTodaySchedule;
import group11.Hockey.BusinessLogic.LeagueSimulation.Interfaces.ICheckAndSimulateTodaySchedule;
import group11.Hockey.BusinessLogic.LeagueSimulation.Interfaces.IParse;
import group11.Hockey.BusinessLogic.models.ICoach;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IDivision;
import group11.Hockey.BusinessLogic.models.IGameplayConfig;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.ITimeLine;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.League.ILeagueDb;
/**
 *
 * @author Jatin Partap Rana
 *
 */
public class TrainingPlayer extends StateMachineState implements ITrainingPlayer {
	private ILeague league;
	private ILeagueDb leaugueDb;
	private IDisplay display;
	private ICommandLineInput commandLineInput;
	private IValidations validation;
	private static Logger logger = LogManager.getLogger(TrainingPlayer.class);

	public TrainingPlayer(ILeague league, ILeagueDb leaugueDb, IDisplay display, ICommandLineInput commandLineInput, IValidations validation
	) {
		super();
		this.league = league;
		this.leaugueDb = leaugueDb;
		this.display = display;
		this.commandLineInput = commandLineInput;
		this.validation = validation;
	}

	@Override
	public StateMachineState startState() {
		logger.debug("Entered startState()");
		IParse parse = DefaultHockeyFactory.makeParse();
		ITimeLine timeLine = league.getTimeLine();
		String currentDate = timeLine.getCurrentDate();
		Date dateTime = parse.stringToDate(currentDate);
		Date tradeDeadLine = timeLine.getTradeDeadLine();
		String startDate = timeLine.getStartDate();
		IGameplayConfig gameplayConfig = league.getGamePlayConfig();

		int trainingDays = gameplayConfig.getTraining().getDaysUntilStatIncreaseCheck();
		int daysDifference = (int) ((parse.stringToDate(currentDate).getTime()
				- parse.stringToDate(startDate).getTime()) / (24 * 60 * 60 * 1000));
		if (daysDifference > trainingDays) {
			List<IConference> conferenceList = league.getConferences();
			for (IConference conference : conferenceList) {
				List<IDivision> divisionList = conference.getDivisions();
				for (IDivision division : divisionList) {
					List<ITeam> teamList = division.getTeams();
					for (ITeam team : teamList) {
						ICoach headCoach = team.getHeadCoach();
						List<IPlayer> playerList = team.getPlayers();
						for (IPlayer player : playerList) {
							try {
							changePlayerSkatingSkill(player, headCoach.getSkating(), league);
							changePlayerShootingSkill(player, headCoach.getShooting(), league);
							changePlayerCheckingSkill(player, headCoach.getChecking(), league);
							changePlayerSavingSkill(player, headCoach.getChecking(), league);
							}
							catch (Exception e) {
								logger.info("Exception occurred in training player :"+e.getMessage());
							}
						}
					}
				}
			}
		}

		ICheckAndSimulateTodaySchedule simulateToday = new CheckAndSimulateTodaySchedule(league.getSchedule(), league);
		simulateToday.CheckAndSimulateToday(currentDate);


		if (dateTime.compareTo(tradeDeadLine) <= 0) {
			logger.info("Performing trading");
			return TradingFactory.makeTradeRunner(league, leaugueDb, commandLineInput, validation, display);
		} else {
			logger.info("Performing aging");
			return DefaultHockeyFactory.makeAgePlayer(league, 1, leaugueDb, display);
		}

	}

	@Override
	public void trainPlayer(ILeague league) {
		logger.debug("Entered trainPlayer()");
	}

	public boolean comapreCoachStat(float coachStatValue) {
		logger.debug("Entered comapreCoachStat()");
		boolean coachStat = false;
		float randomValue = (float) Math.random();
		if (randomValue < coachStatValue) {
			coachStat = true;
		}
		return coachStat;
	}

	public void changePlayerSkatingSkill(IPlayer player, float coachSkatingStatValue, ILeague league) {
		logger.debug("Entered changePlayerSkatingSkill()");
		if (player.isInjured() == false) {
			logger.info(player.getPlayerName() + " is not injured so changing skating skill");
			boolean coachStat = comapreCoachStat(coachSkatingStatValue);
			if (coachStat) {
				float skatingSkill = player.getSkating() + 1;
				display.showMessageOnConsole("Player with name "+ player.getPlayerName()+ "skating skill improved");
				player.setSkating(skatingSkill);
			} else {
				player.checkInjury(league);
			}
		}

	}

	public void changePlayerShootingSkill(IPlayer player, float coachShootingStatValue, ILeague league) {
		logger.debug("Entered changePlayerShootingSkill()");
		if (player.isInjured() == false) {
			logger.info(player.getPlayerName() + " is not injured so changing shooting skill");
			boolean coachStat = comapreCoachStat(coachShootingStatValue);
			if (coachStat) {
				float shootingSkill = player.getShooting() + 1;
				display.showMessageOnConsole("Player with name "+ player.getPlayerName()+ "shooting skill improved");
				player.setShooting(shootingSkill);
			} else {
				player.checkInjury(league);
			}
		}

	}

	public void changePlayerCheckingSkill(IPlayer player, float coachCheckingStatValue, ILeague league) {
		logger.debug("Entered changePlayerCheckingSkill()");
		if (player.isInjured() == false) {
			logger.info(player.getPlayerName() + " is not injured so changing checking skill");
			boolean coachStat = comapreCoachStat(coachCheckingStatValue);
			if (coachStat) {
				float checkingSkill = player.getChecking() + 1;
				display.showMessageOnConsole("Player with name "+ player.getPlayerName()+ "checking skill improved");
				player.setChecking(checkingSkill);
			} else {
				player.checkInjury(league);
			}
		}
	}

	public void changePlayerSavingSkill(IPlayer player, float coachSavingStatValue, ILeague league) {
		logger.debug("Entered changePlayerSavingSkill()");
		if (player.isInjured() == false) {
			logger.info(player.getPlayerName() + " is not injured so changing saving skill");
			boolean coachStat = comapreCoachStat(coachSavingStatValue);
			if (coachStat) {
				float savingSkill = player.getSaving() + 1;
				display.showMessageOnConsole("Player with name "+ player.getPlayerName()+ "saving skill improved");
				player.setSaving(savingSkill);
			} else {
				player.checkInjury(league);
			}
		}

	}

}
