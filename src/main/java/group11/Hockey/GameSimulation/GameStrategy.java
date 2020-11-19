package group11.Hockey.GameSimulation;

import java.util.List;

import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Player;

public abstract class GameStrategy {
	public abstract int calculateAveragePlayersStrength(List<Player> playersList, ITeam defendingTeam);

	public abstract void playGame(List<Player> shootingTeamPlayers, List<Player> defendingTeamPlayers,
			ITeam defendingTeam, ITeam ShootingTeam, int penaltyPeriod);
}
