/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation;

import java.util.List;

import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;

public interface IGameContext {
	public void executeStrategy(List<IPlayer> shootingTeamPlayers, List<IPlayer> defendingTeamPlayers,
			ITeam defendingTeam, ITeam ShootingTeam, int penaltyPeriod);

	public int getAveragePlayersStrength(List<IPlayer> playersList, ITeam defendingTeam);
}
