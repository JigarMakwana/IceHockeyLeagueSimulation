/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation;

import java.util.List;

import group11.Hockey.BusinessLogic.Positions;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;

public class ActiveForwardPlayer implements IGameStrategy {

	public int calculateAveragePlayersStrength(List<IPlayer> playersList, ITeam defendingTeam) {
		int skating = 0;
		int numberOfForwardMen = 0;
		int playerStrength = 0;
		for (IPlayer player : playersList) {

			if (player.getPosition().equalsIgnoreCase(Positions.FORWARD.toString())) {
				skating += player.getShooting();
				numberOfForwardMen++;
			}
		}
		if (numberOfForwardMen == 0) {
			numberOfForwardMen = 1;
		}
		playerStrength = skating / numberOfForwardMen;
		return playerStrength;
	}

	public void playGame(List<IPlayer> shootingTeamPlayers, List<IPlayer> defendingTeamPlayers, ITeam defendingTeam,
			ITeam ShootingTeam, int penaltyPeriod) {
		int index = bestForwardMen(shootingTeamPlayers);
		int goals = shootingTeamPlayers.get(index).getGoalsInSeason() + 1;
		shootingTeamPlayers.get(index).setGoalsInSeason(goals);
		goals = ShootingTeam.getGoalsInSeason() + 1;
		ShootingTeam.setGoalsInSeason(goals);
	}

	private int bestForwardMen(List<IPlayer> shootingTeamPlayers) {
		int maxIndex = 0;
		for (int i = 1; i < appConfiguration.forwardMen; i++) {
			if (shootingTeamPlayers.get(maxIndex).getShooting() < shootingTeamPlayers.get(i).getShooting()) {
				maxIndex = i;
			}
		}
		return maxIndex;
	}
}
