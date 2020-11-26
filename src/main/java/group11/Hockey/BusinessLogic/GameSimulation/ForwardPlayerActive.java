/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.GameSimulation;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.Positions;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Player;

public class ForwardPlayerActive extends GameStrategy {
	
	private static Logger logger = LogManager.getLogger(ForwardPlayerActive.class);
	
	public int calculateAveragePlayersStrength(List<Player> playersList, ITeam defendingTeam) {
		logger.info("Entered calculateAveragePlayersStrength()");
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
		logger.info("Entered playGame()");
		int index = bestForwardMen(shootingTeamPlayers);
		int goals = shootingTeamPlayers.get(index).getGoalsInSeason() + 1;
		shootingTeamPlayers.get(index).setGoalsInSeason(goals);
		goals = ShootingTeam.getGoalsInSeason() + 1;
		ShootingTeam.setGoalsInSeason(goals);
	}

	private int bestForwardMen(List<Player> shootingTeamPlayers) {
		logger.info("Entered bestForwardMen()");
		int maxIndex = 0;
		for (int i = 1; i < appConfiguration.forwardMen; i++) {
			if (shootingTeamPlayers.get(maxIndex).getShooting() < shootingTeamPlayers.get(i).getShooting()) {
				maxIndex = i;
			}
		}
		return maxIndex;
	}
}
