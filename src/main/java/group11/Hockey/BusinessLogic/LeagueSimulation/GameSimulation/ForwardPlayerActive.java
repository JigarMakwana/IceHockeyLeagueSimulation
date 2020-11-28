/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation;

import java.util.List;

import group11.Hockey.BusinessLogic.Positions;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Player;

public class ForwardPlayerActive implements IGameStrategy {

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
		int index = bestForwardMen(shootingTeamPlayers);
		int goals = shootingTeamPlayers.get(index).getGoalsInSeason() + 1;
		shootingTeamPlayers.get(index).setGoalsInSeason(goals);
		goals = ShootingTeam.getGoalsInSeason() + 1;
		ShootingTeam.setGoalsInSeason(goals);
	}

	private int bestForwardMen(List<Player> shootingTeamPlayers) {
		int maxIndex = 0;
		for (int i = 1; i < appConfiguration.forwardMen; i++) {
			if (shootingTeamPlayers.get(maxIndex).getShooting() < shootingTeamPlayers.get(i).getShooting()) {
				maxIndex = i;
			}
		}
		return maxIndex;
	}
}
