/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation;

import java.util.List;

import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;

public interface IGameStrategy {
	public abstract int calculateAveragePlayersStrength(List<IPlayer> playersList, ITeam defendingTeam);

	public abstract void playGame(List<IPlayer> shootingTeamPlayers, List<IPlayer> defendingTeamPlayers,
			ITeam defendingTeam, ITeam ShootingTeam, int penaltyPeriod);
}
