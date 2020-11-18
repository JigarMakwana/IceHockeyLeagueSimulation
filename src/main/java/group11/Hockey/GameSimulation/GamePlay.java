package group11.Hockey.GameSimulation;

import java.util.List;
import java.util.Random;

import group11.Hockey.BusinessLogic.Positions;
import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Player;

public class GamePlay extends StateMachineState {
	private ILeague league;
	private ITeam team1;
	private ITeam team2;

	public GamePlay(ILeague league, ITeam team1, ITeam team2) {
		super();
		this.league = league;
		this.team1 = team1;
		this.team2 = team2;
	}

	@Override
	public StateMachineState startState() {

		// remove
		team1 = league.getConferences().get(1).getDivisions().get(0).getTeams().get(2);
		team2 = league.getConferences().get(0).getDivisions().get(0).getTeams().get(0);
		List<Player> team_p1 = team1.getPlayers();
		List<Player> team_p2 = team2.getPlayers();
		// remove

		GameSimulation gm1 = new GameSimulation(league, team_p1);
		List<Player>[] shiftsTeam1 = gm1.getShifts();
		int shootingStatsTeam1 = gm1.teamSkatingStats();
		gm1 = new GameSimulation(league, team_p2);
		List<Player>[] shiftsTeam2 = gm1.getShifts();
		int shootingStatsTeam2 = gm1.teamSkatingStats();
		setAverageShootsForTeams(shootingStatsTeam1, shootingStatsTeam2);

		startGame(shiftsTeam1, shiftsTeam2);
		gameSummary(team1, team2);
		setWinnerTeam(team1, team2);
		return null;
	}

	private void setAverageShootsForTeams(int teamOneShoots, int teamTwoShoots) {
		int averageShootsTeam1 = 0;
		int averageShootsTeam2 = 0;
		int shootingDifference = teamOneShoots - teamTwoShoots;
		if (shootingDifference > 10) {
			shootingDifference = 10;
		} else if (shootingDifference < 10) {
			shootingDifference = -10;
		}
		averageShootsTeam1 = 30 + (shootingDifference);
		team1.setAverageShoots(averageShootsTeam1);
		averageShootsTeam2 = 30 - (shootingDifference);
		team2.setAverageShoots(averageShootsTeam2);
	}

	private void startGame(List<Player>[] shiftsTeam1, List<Player>[] shiftsTeam2) {
		int averageShootsTeam1 = team1.getAverageShoots();
		int averageShootsTeam2 = team2.getAverageShoots();
		for (int shift = 0; shift < 20; shift++) {

			for (int i = 0; i < 2; i++) {
				if (averageShootsTeam1 > 0) {
					makeShoot(shiftsTeam1[shift], shiftsTeam2[shift], team1, 3);
					averageShootsTeam1--;
				} else {
					if (averageShootsTeam2 > 0) {
						makeShoot(shiftsTeam2[shift], shiftsTeam1[shift], team2, 3);
						averageShootsTeam2--;
					}
				}
			}
		}
		for (int shift = 20; shift < 40; shift++) {
			if (averageShootsTeam2 > 0) {
				makeShoot(shiftsTeam2[shift], shiftsTeam1[shift], team2, 2);
				averageShootsTeam2--;
			}
		}
	}

	private void makeShoot(List<Player> shootingTeamPlayers, List<Player> defendingTeamPlayers, ITeam defendingTeam,
			int penaltyPeriod) {

		int skatingStat = calculatePlayerStrength(shootingTeamPlayers);
		int checkingStat = calculatePlayersChecking(defendingTeamPlayers, defendingTeam);
		int savingStat = calculatePlayersSaving(defendingTeamPlayers);

		managePanelty(defendingTeam);

		// if (skatingStat <= checkingStat) {
		if (checkingStat - skatingStat > -9) {
			int penaltyProbality = new Random().nextInt(30);
			int index = new Random().nextInt(2);
			if (penaltyProbality <= 5) {
				defendingTeamPlayers.get(index + 3).setPenalitiesInSeason(1);
				defendingTeam.setOnPenalty(true);
				defendingTeam.setPenaltyPeriod(penaltyPeriod);
			}
			defendingTeamPlayers.get(index + 3).setSavesByDefenceManinSeason(1);
		}
		// else if (skatingStat <= savingStat) {
		else if (savingStat - skatingStat > -9) {
			defendingTeamPlayers.get(5).setSavesByGoalieInSeason(1);
		} else {
			int index = new Random().nextInt(3);
			shootingTeamPlayers.get(index).setGoalsInSeason(1);
		}
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

	private int calculatePlayerStrength(List<Player> playersList) {
		int skating = 0;
		int numberOfForwardMen = 0;
		for (Player player : playersList) {
			if (player.getPosition().equalsIgnoreCase(Positions.FORWARD.toString())) {
				skating += player.getShooting();
				numberOfForwardMen++;
			}
		}
		if (numberOfForwardMen == 0) {
			numberOfForwardMen = 1;
		}
		return skating / numberOfForwardMen;
	}

	private int calculatePlayersChecking(List<Player> playersList, ITeam defendingTeam) {
		int checking = 0;
		int numberOfDefenseMen = 0;
		for (Player player : playersList) {
			if (player.getPosition().equalsIgnoreCase(Positions.DEFENSE.toString())) {
				checking += player.getChecking();
				numberOfDefenseMen++;
				if (defendingTeam.isOnPenalty()) {
					checking = (int) (checking * 0.75);
					break;
				}
			}
		}
		if (numberOfDefenseMen == 0) {
			numberOfDefenseMen = 1;
		}
		return checking / numberOfDefenseMen;
	}

	private int calculatePlayersSaving(List<Player> playersList) {
		int saving = 0;
		int numberOfGoalieMen = 0;
		for (Player player : playersList) {
			if (player.getPosition().equalsIgnoreCase(Positions.GOALIE.toString())) {
				saving += player.getSaving();
				numberOfGoalieMen++;
			}
		}
		if (numberOfGoalieMen == 0) {
			numberOfGoalieMen = 1;
		}
		return saving / numberOfGoalieMen;
	}

	private void gameSummary(ITeam team1, ITeam team2) {
		int goalsPerTeam = 0;
		int penaltiesPerTeam = 0;
		int shots = 0;
		int saves = 0;
		for (Player player : team1.getPlayers()) {
			goalsPerTeam += player.getGoalsInSeason();
			penaltiesPerTeam += player.getPenalitiesInSeason();
			saves += player.getSavesByGoalieInSeason() + player.getSavesByDefenceManinSeason();
		}

		for (Player player : team2.getPlayers()) {
			goalsPerTeam += player.getGoalsInSeason();
			penaltiesPerTeam += player.getPenalitiesInSeason();
			saves += player.getSavesByGoalieInSeason() + player.getSavesByDefenceManinSeason();
		}

		System.out.println("Goals per game: " + goalsPerTeam / 2);
		System.out.println("Penalties per game: " + penaltiesPerTeam / 2);
		System.out.println("Shots: " + 60 / 2);
		System.out.println("Saves: " + saves / 2);
	}

	private void setWinnerTeam(ITeam team1, ITeam team2) {
		int goalsTeam1 = 0;
		int goalsTeam2 = 0;
		for (Player player : team1.getPlayers()) {
			goalsTeam1 += player.getGoalsInSeason();
		}

		for (Player player : team2.getPlayers()) {
			goalsTeam2 += player.getGoalsInSeason();
		}
		if (goalsTeam1 > goalsTeam2) {
			System.out.println(team1.getTeamName() + " won the match");
		} else {
			System.out.println(team2.getTeamName() + " won the match");
		}
	}

}
