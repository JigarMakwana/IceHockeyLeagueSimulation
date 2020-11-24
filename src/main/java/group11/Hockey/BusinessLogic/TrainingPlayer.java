package group11.Hockey.BusinessLogic;

import java.util.Date;
import java.util.List;

import group11.Hockey.BusinessLogic.models.Coach;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.IGameplayConfig;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITimeLine;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.BusinessLogic.LeagueSimulation.CheckAndSimulateTodaySchedule;
import group11.Hockey.BusinessLogic.LeagueSimulation.ICheckAndSimulateTodaySchedule;
import group11.Hockey.BusinessLogic.LeagueSimulation.IParse;
import group11.Hockey.BusinessLogic.LeagueSimulation.Parse;
import group11.Hockey.BusinessLogic.Trading.AITrading;
import group11.Hockey.db.League.ILeagueDb;

public class TrainingPlayer extends StateMachineState implements ITrainingPlayer {
	private ILeague league;
	private ILeagueDb leaugueDb;

	public TrainingPlayer(ILeague league, ILeagueDb leaugueDb) {
		super();
		this.league = league;
		this.leaugueDb = leaugueDb;
	}

	@Override
	public StateMachineState startState() {
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
			List<Conference> conferenceList = league.getConferences();
			for (Conference conference : conferenceList) {
				List<Division> divisionList = conference.getDivisions();
				for (Division division : divisionList) {
					List<Team> teamList = division.getTeams();
					for (Team team : teamList) {
						Coach headCoach = team.getHeadCoach();
						List<Player> playerList = team.getPlayers();
						for (Player player : playerList) {
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
			return new AITrading(league, leaugueDb);

		} else {
			return new AgePlayer(league, 1, leaugueDb);
		}

	}

	@Override
	public void trainPlayer(ILeague league) {

	}

	public boolean comapreCoachStat(float coachStatValue) {
		boolean coachStat = false;
		float randomValue = (float) Math.random();
		if (randomValue < coachStatValue) {
			coachStat = true;
		}
		return coachStat;
	}

	public void changePlayerSkatingSkill(Player player, float coachSkatingStatValue, ILeague league) {
		if (player.isInjured() == false) {
			boolean coachStat = comapreCoachStat(coachSkatingStatValue);
			if (coachStat) {
				float skatingSkill = player.getSkating() + 1;
				player.setSkating(skatingSkill);
			} else {
				player.checkInjury(league);
			}
		}

	}

	public void changePlayerShootingSkill(Player player, float coachShootingStatValue, ILeague league) {
		if (player.isInjured() == false) {
			boolean coachStat = comapreCoachStat(coachShootingStatValue);
			if (coachStat) {
				float shootingSkill = player.getShooting() + 1;
				player.setShooting(shootingSkill);
			} else {
				player.checkInjury(league);
			}
		}

	}

	public void changePlayerCheckingSkill(Player player, float coachCheckingStatValue, ILeague league) {
		if (player.isInjured() == false) {
			boolean coachStat = comapreCoachStat(coachCheckingStatValue);
			if (coachStat) {
				float checkingSkill = player.getChecking() + 1;
				player.setChecking(checkingSkill);
			} else {
				player.checkInjury(league);
			}
		}
	}

	public void changePlayerSavingSkill(Player player, float coachSavingStatValue, ILeague league) {
		if (player.isInjured() == false) {
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
