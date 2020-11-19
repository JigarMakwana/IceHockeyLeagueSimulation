package group11.Hockey.GameSimulation;

import java.util.List;
import java.util.Random;

import group11.Hockey.BusinessLogic.Positions;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Player;

public class ForwardPlayerActive extends GameStrategy {
	public int calculateAveragePlayersStrength(List<Player> playersList, ITeam defendingTeam) {
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

	public void playGame(List<Player> shootingTeamPlayers, List<Player> defendingTeamPlayers, ITeam defendingTeam,
			ITeam ShootingTeam, int penaltyPeriod) {

		int index = new Random().nextInt(3);
		int goals = shootingTeamPlayers.get(index).getGoalsInSeason() + 1;
		shootingTeamPlayers.get(index).setGoalsInSeason(goals);
		goals = ShootingTeam.getGoalsInSeason() + 1;
		ShootingTeam.setGoalsInSeason(goals);
	}
}
