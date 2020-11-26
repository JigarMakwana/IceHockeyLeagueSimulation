/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.GameSimulation;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Player;

public class GameContext implements IGameContext {
	private GameStrategy gameStrategy;
	private static Logger logger = LogManager.getLogger(GameContext.class);

	public GameContext(GameStrategy gameStrategy) {
		super();
		this.gameStrategy = gameStrategy;
	}

	public void executeStrategy(List<Player> shootingTeamPlayers, List<Player> defendingTeamPlayers,
			ITeam defendingTeam, ITeam ShootingTeam, int penaltyPeriod) {
		logger.info("Entered executeStrategy()");
		gameStrategy.playGame(shootingTeamPlayers, defendingTeamPlayers, defendingTeam, ShootingTeam, penaltyPeriod);
	}

	public int getAveragePlayersStrength(List<Player> playersList, ITeam defendingTeam) {
		logger.info("Entered getAveragePlayersStrength()");
		int averageStrength = gameStrategy.calculateAveragePlayersStrength(playersList, defendingTeam);
		return averageStrength;
	}
}
