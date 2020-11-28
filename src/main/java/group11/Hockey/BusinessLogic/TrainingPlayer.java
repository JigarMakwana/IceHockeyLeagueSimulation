package group11.Hockey.BusinessLogic;

import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.LeagueSimulation.CheckAndSimulateTodaySchedule;
import group11.Hockey.BusinessLogic.LeagueSimulation.ICheckAndSimulateTodaySchedule;
import group11.Hockey.BusinessLogic.LeagueSimulation.IParse;
import group11.Hockey.BusinessLogic.LeagueSimulation.Parse;
import group11.Hockey.BusinessLogic.Trading.AITrading;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.ICoach;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IGameplayConfig;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.ITimeLine;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.League.ILeagueDb;

public class TrainingPlayer extends StateMachineState implements ITrainingPlayer {
	private ILeague league;
	private ILeagueDb leaugueDb;
	IDisplay display;
	private static Logger logger = LogManager.getLogger(TrainingPlayer.class);

	public TrainingPlayer(ILeague league, ILeagueDb leaugueDb, IDisplay display) {
		super();
		this.league = league;
		this.leaugueDb = leaugueDb;
		this.display = display;
	}

	@Override
	public StateMachineState startState() {
		logger.info("Entered startState()");
		IParse parse = new Parse();
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
				List<Division> divisionList = conference.getDivisions();
				for (Division division : divisionList) {
					List<ITeam> teamList = division.getTeams();
					for (ITeam team : teamList) {
						ICoach headCoach = team.getHeadCoach();
						List<IPlayer> playerList = team.getPlayers();
						for (IPlayer player : playerList) {
							changePlayerSkatingSkill(player, headCoach.getSkating(), league);
							changePlayerShootingSkill(player, headCoach.getShooting(), league);
							changePlayerCheckingSkill(player, headCoach.getChecking(), league);
							changePlayerSavingSkill(player, headCoach.getChecking(), league);
						}
					}
				}
			}
		}

		// TODO: call simulate game
		ICheckAndSimulateTodaySchedule simulateToday = new CheckAndSimulateTodaySchedule(league.getSchedule(), league);
		simulateToday.CheckAndSimulateToday(currentDate);
		// .............
		// .............

		if (dateTime.compareTo(tradeDeadLine) <= 0) {
			logger.info("Performing trading");
			return new AITrading(league, leaugueDb, display);

		} else {
			logger.info("Performing aging");
			return new AgePlayer(league, 1, leaugueDb, display);
		}

	}

	@Override
	public void trainPlayer(ILeague league) {
		logger.info("Entered trainPlayer()");
	}

	public boolean comapreCoachStat(float coachStatValue) {
		logger.info("Entered comapreCoachStat()");
		boolean coachStat = false;
		float randomValue = (float) Math.random();
		if (randomValue < coachStatValue) {
			coachStat = true;
		}
		return coachStat;
	}

	public void changePlayerSkatingSkill(IPlayer player, float coachSkatingStatValue, ILeague league) {
		logger.info("Entered changePlayerSkatingSkill()");
		if (player.isInjured() == false) {
			logger.info(player.getPlayerName() + " is not injured so changing skating skill");
			boolean coachStat = comapreCoachStat(coachSkatingStatValue);
			if (coachStat) {
				float skatingSkill = player.getSkating() + 1;
				player.setSkating(skatingSkill);
			} else {
				player.checkInjury(league);
			}
		}

	}

	public void changePlayerShootingSkill(IPlayer player, float coachShootingStatValue, ILeague league) {
		logger.info("Entered changePlayerShootingSkill()");
		if (player.isInjured() == false) {
			logger.info(player.getPlayerName() + " is not injured so changing shooting skill");
			boolean coachStat = comapreCoachStat(coachShootingStatValue);
			if (coachStat) {
				float shootingSkill = player.getShooting() + 1;
				player.setShooting(shootingSkill);
			} else {
				player.checkInjury(league);
			}
		}

	}

	public void changePlayerCheckingSkill(IPlayer player, float coachCheckingStatValue, ILeague league) {
		logger.info("Entered changePlayerCheckingSkill()");
		if (player.isInjured() == false) {
			logger.info(player.getPlayerName() + " is not injured so changing checking skill");
			boolean coachStat = comapreCoachStat(coachCheckingStatValue);
			if (coachStat) {
				float checkingSkill = player.getChecking() + 1;
				player.setChecking(checkingSkill);
			} else {
				player.checkInjury(league);
			}
		}
	}

	public void changePlayerSavingSkill(IPlayer player, float coachSavingStatValue, ILeague league) {
		logger.info("Entered changePlayerSavingSkill()");
		if (player.isInjured() == false) {
			logger.info(player.getPlayerName() + " is not injured so changing saving skill");
			boolean coachStat = comapreCoachStat(coachSavingStatValue);
			if (coachStat) {
				float savingSkill = player.getSaving() + 1;
				player.setSaving(savingSkill);
			} else {
				player.checkInjury(league);
			}
		}

	}

}
