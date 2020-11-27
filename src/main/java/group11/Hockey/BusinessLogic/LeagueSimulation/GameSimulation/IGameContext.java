/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation;

import java.util.List;

import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Player;

public interface IGameContext {
	public void executeStrategy(List<Player> shootingTeamPlayers, List<Player> defendingTeamPlayers,
			ITeam defendingTeam, ITeam ShootingTeam, int penaltyPeriod);

	public int getAveragePlayersStrength(List<Player> playersList, ITeam defendingTeam);
}
