package group11.Hockey.GameSimulation;

import java.util.List;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Player;

public class GamePlay {
	private ILeague league;
	private ITeam team1;
	private ITeam team2;

	public GamePlay(ILeague league, ITeam team1, ITeam team2) {
		super();
		this.league = league;
		this.team1 = team1;
		this.team2 = team2;
	}

	public ITeam startGamePlay() {
		List<Player> team_p1 = team1.getPlayers();
		List<Player> team_p2 = team2.getPlayers();

		GameSimulation gm1 = new GameSimulation(league, team_p1);
		List<Player>[] shiftsTeam1 = gm1.getShifts();
		int shootingStatsTeam1 = gm1.teamSkatingStats();
		gm1 = new GameSimulation(league, team_p2);
		List<Player>[] shiftsTeam2 = gm1.getShifts();
		int shootingStatsTeam2 = gm1.teamSkatingStats();
		setAverageShootsForTeams(shootingStatsTeam1, shootingStatsTeam2);

		startGame(shiftsTeam1, shiftsTeam2);
		ITeam winnerTeam = setWinnerTeam(team1, team2);
		gameSummary(team1, team2);
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
					makeShoot(shiftsTeam1[shift], shiftsTeam2[shift], team1, team2, 3);
					averageShootsTeam1--;
				} else {
					if (averageShootsTeam2 > 0) {
						makeShoot(shiftsTeam2[shift], shiftsTeam1[shift], team2, team1, 3);
						averageShootsTeam2--;
					}
				}
			}
		}
		for (int shift = appConfiguration.shifts / 2; shift < appConfiguration.shifts; shift++) {
			if (averageShootsTeam2 > 0) {
				makeShoot(shiftsTeam2[shift], shiftsTeam1[shift], team2, team1, 2);
				averageShootsTeam2--;
			}
		}
	}

	private void makeShoot(List<Player> shootingTeamPlayers, List<Player> defendingTeamPlayers, ITeam defendingTeam,
			ITeam ShootingTeam, int penaltyPeriod) {

		GameContext gameContext = null;
		GameStrategy gameStrategy = null;

		managePanelty(defendingTeam);

		gameStrategy = new ForwardPlayerActive();
		gameContext = new GameContext(gameStrategy);
		int shootingStat = gameContext.getAveragePlayersStrength(shootingTeamPlayers, defendingTeam);

		gameStrategy = new DefencePlayerActive();
		gameContext = new GameContext(gameStrategy);
		int checkingStat = gameContext.getAveragePlayersStrength(shootingTeamPlayers, defendingTeam);

		gameStrategy = new GoaliePlayerActive();
		gameContext = new GameContext(gameStrategy);
		int savingStat = gameContext.getAveragePlayersStrength(shootingTeamPlayers, defendingTeam);

		if (shootingStat - checkingStat < appConfiguration.saveChance) {
			gameStrategy = new DefencePlayerActive();
			gameContext = new GameContext(gameStrategy);
		} else if (shootingStat - savingStat < appConfiguration.saveChance) {
			gameStrategy = new GoaliePlayerActive();
			gameContext = new GameContext(gameStrategy);
		} else {
			gameStrategy = new ForwardPlayerActive();
			gameContext = new GameContext(gameStrategy);
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

		System.out.println("***********end summary**************");

		System.out.println("Goals per game: " + (float) league.getGoalsInSeason() / league.getGamesInSeason());
		System.out.println("Penalties per game: " + (float) league.getPenaltiesInSeason() / league.getGamesInSeason());
		System.out.println("Shots: " + 60 / 2);
		System.out.println("Saves: " + (float) league.getSavesInSeason() / league.getGamesInSeason());

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

}
