/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Player;

public class GameSimulation implements IGameSimulation {
	private ILeague league;
	private ITeam team1;
	private ITeam team2;
	private static Logger logger = LogManager.getLogger(GameSimulation.class);

	public GameSimulation(ILeague league, ITeam team1, ITeam team2) {
		super();
		this.league = league;
		this.team1 = team1;
		this.team2 = team2;
	}

	public ITeam startGamePlay() {
		List<Player> team_p1 = team1.getPlayers();
		List<Player> team_p2 = team2.getPlayers();
		List<Player>[] shiftsTeam1 = null;
		List<Player>[] shiftsTeam2 = null;

		GenerateShiftsTemplate shifts1 = null;
		GenerateShiftsTemplate shifts2 = null;
		if (league.getQualifiedTeams().size() > 0) {
			logger.info("Generate shifts for playoff schedule");
			shifts1 = DefaultHockeyFactory.makeGeneratePlayOffShifts(team_p1);
			shifts2 = DefaultHockeyFactory.makeGeneratePlayOffShifts(team_p2);
		} else {
			logger.info("Generate shifts for normal schedule");
			shifts1 = DefaultHockeyFactory.makeGenerateShifts(team_p1);
			shifts2 = DefaultHockeyFactory.makeGenerateShifts(team_p2);
		}

		try {
			shiftsTeam1 = shifts1.getShifts();
			shiftsTeam2 = shifts2.getShifts();
		} catch (Exception e) {
			logger.error("error while generating shifts " + e);
		}
		int shootingStatsTeam1 = teamSkatingStats(team_p1);

		int shootingStatsTeam2 = teamSkatingStats(team_p2);
		setAverageShootsForTeams(shootingStatsTeam1, shootingStatsTeam2);

		startGame(shiftsTeam1, shiftsTeam2);
		ITeam winnerTeam = setWinnerTeam(team1, team2);
		gameSummary(team1, team2);
		resetTeamStats(team1, team2);
		return winnerTeam;
	}

	private void setAverageShootsForTeams(int teamOneShoots, int teamTwoShoots) {
		int averageShootsTeam1 = 0;
		int averageShootsTeam2 = 0;
		int shootingDifference = teamOneShoots - teamTwoShoots;
		if (shootingDifference > appConfiguration.maxDifferenceLimit) {
			shootingDifference = appConfiguration.maxDifferenceLimit;
		} else if (shootingDifference < appConfiguration.maxDifferenceLimit) {
			shootingDifference = -appConfiguration.maxDifferenceLimit;
		}
		averageShootsTeam1 = appConfiguration.averageShootsPerTeam + (shootingDifference);
		team1.setAverageShoots(averageShootsTeam1);
		averageShootsTeam2 = appConfiguration.averageShootsPerTeam - (shootingDifference);
		team2.setAverageShoots(averageShootsTeam2);
	}

	private void startGame(List<Player>[] shiftsTeam1, List<Player>[] shiftsTeam2) {
		int averageShootsTeam1 = team1.getAverageShoots();
		int averageShootsTeam2 = team2.getAverageShoots();
		for (int shift = 0; shift < appConfiguration.shifts / 2; shift++) {

			for (int i = 0; i < 2; i++) {
				if (averageShootsTeam1 > 0) {
					makeShoot(shiftsTeam1[shift], shiftsTeam2[shift], team1, team2,
							appConfiguration.numberOfShoots_high);
					averageShootsTeam1--;
				} else {
					if (averageShootsTeam2 > 0) {
						makeShoot(shiftsTeam2[shift], shiftsTeam1[shift], team2, team1,
								appConfiguration.numberOfShoots_high);
						averageShootsTeam2--;
					}
				}
			}
		}
		for (int shift = appConfiguration.shifts / 2; shift < appConfiguration.shifts; shift++) {
			if (averageShootsTeam2 > 0) {
				makeShoot(shiftsTeam2[shift], shiftsTeam1[shift], team2, team1, appConfiguration.numberOfShoots_low);
				averageShootsTeam2--;
			}
		}
	}

	private void makeShoot(List<Player> shootingTeamPlayers, List<Player> defendingTeamPlayers, ITeam defendingTeam,
			ITeam ShootingTeam, int penaltyPeriod) {
		logger.info("Team initiating the shoot");
		IGameContext gameContext = null;

		managePanelty(defendingTeam);

		IGameContext gameContext_forward = DefaultHockeyFactory
				.makeGameContext(DefaultHockeyFactory.makeForwardPlayerActive());
		int shootingStat = gameContext_forward.getAveragePlayersStrength(shootingTeamPlayers, defendingTeam);

		IGameContext gameContext_defence = DefaultHockeyFactory
				.makeGameContext(DefaultHockeyFactory.makeDefencePlayerActive());
		int checkingStat = gameContext_defence.getAveragePlayersStrength(shootingTeamPlayers, defendingTeam);

		IGameContext gameContext_goalie = DefaultHockeyFactory
				.makeGameContext(DefaultHockeyFactory.makeGoaliePlayerActive());
		int savingStat = gameContext_goalie.getAveragePlayersStrength(shootingTeamPlayers, defendingTeam);

		if (shootingStat - checkingStat < appConfiguration.saveChance) {
			gameContext = gameContext_defence;
		} else if (shootingStat - savingStat < appConfiguration.saveChance) {
			gameContext = gameContext_goalie;
		} else {
			gameContext = gameContext_forward;
		}
		gameContext.executeStrategy(shootingTeamPlayers, defendingTeamPlayers, defendingTeam, ShootingTeam,
				penaltyPeriod);
	}

	private void managePanelty(ITeam defendingTeam) {
		int penaltyPeriod = defendingTeam.getPenaltyPeriod();
		if (penaltyPeriod > 0) {
			penaltyPeriod--;
			defendingTeam.setPenaltyPeriod(penaltyPeriod);
			if (penaltyPeriod == 0) {
				defendingTeam.setOnPenalty(false);
			}
		}
	}

	private void gameSummary(ITeam team1, ITeam team2) {
		int goalsIngame = team1.getGoalsInSeason() + team2.getGoalsInSeason();
		int penaltiesInGame = team1.getPenaltiesInSeason() + team2.getPenaltiesInSeason();
		int savesInGame = team1.getSavesInSeason() + team2.getSavesInSeason();

		league.setGoalsInSeason(league.getGoalsInSeason() + goalsIngame);
		league.setPenaltiesInSeason(league.getPenaltiesInSeason() + penaltiesInGame);
		league.setSavesInSeason(league.getSavesInSeason() + savesInGame);
		league.setGamesInSeason(league.getGamesInSeason() + 2);

		System.out.println("***Game Summary***");
		System.out.println("Goals per game: " + (float) league.getGoalsInSeason() / league.getGamesInSeason());
		System.out.println("Penalties per game: " + (float) league.getPenaltiesInSeason() / league.getGamesInSeason());
		System.out.println("Shots: " + 60 / 2);
		System.out.println("Saves: " + (float) league.getSavesInSeason() / league.getGamesInSeason());
	}

	private void resetTeamStats(ITeam team1, ITeam team2) {
		team1.setGoalsInSeason(0);
		team2.setGoalsInSeason(0);
		team1.setPenaltiesInSeason(0);
		team2.setPenaltiesInSeason(0);
		team1.setSavesInSeason(0);
		team2.setSavesInSeason(0);
	}

	private ITeam setWinnerTeam(ITeam team1, ITeam team2) {
		int goalsTeam1 = team1.getGoalsInSeason();
		int goalsTeam2 = team2.getGoalsInSeason();

		if (goalsTeam1 > goalsTeam2) {
			return team1;
		} else {
			return team2;
		}
	}

	private int teamSkatingStats(List<Player> team) {
		int skatingStat = 0;
		for (Player player : team) {
			skatingStat += player.getSkating();
		}
		return skatingStat;
	}

}
