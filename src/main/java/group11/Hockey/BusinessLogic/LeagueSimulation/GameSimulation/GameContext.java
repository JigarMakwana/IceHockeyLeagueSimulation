/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation;

import java.util.List;

import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;

public class GameContext implements IGameContext {
	private IGameStrategy gameStrategy;

	public GameContext(IGameStrategy gameStrategy) {
		super();
		this.gameStrategy = gameStrategy;
	}

	public void executeStrategy(List<IPlayer> shootingTeamPlayers, List<IPlayer> defendingTeamPlayers,
			ITeam defendingTeam, ITeam ShootingTeam, int penaltyPeriod) {
		gameStrategy.playGame(shootingTeamPlayers, defendingTeamPlayers, defendingTeam, ShootingTeam, penaltyPeriod);
	}

	public int getAveragePlayersStrength(List<IPlayer> playersList, ITeam defendingTeam) {

		int averageStrength = gameStrategy.calculateAveragePlayersStrength(playersList, defendingTeam);
		return averageStrength;
	}
}
