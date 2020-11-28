/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation;

import java.util.List;

import group11.Hockey.BusinessLogic.Positions;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Player;

public class ActiveGoaliePlayer implements IGameStrategy {

	@Override
	public int calculateAveragePlayersStrength(List<Player> playersList, ITeam defendingTeam) {
		int saving = 0;
		int numberOfGoalieMen = 0;
		int playerStrength = 0;
		for (Player player : playersList) {
			if (player.getPosition().equalsIgnoreCase(Positions.GOALIE.toString())) {
				saving += player.getSaving();
				numberOfGoalieMen++;
			}
		}
		if (numberOfGoalieMen == 0) {
			numberOfGoalieMen = 1;
		}
		playerStrength = saving / numberOfGoalieMen;
		return playerStrength;
	}

	@Override
	public void playGame(List<Player> shootingTeamPlayers, List<Player> defendingTeamPlayers, ITeam defendingTeam,
			ITeam ShootingTeam, int penaltyPeriod) {
		int saves = defendingTeamPlayers.get(appConfiguration.goalieStartIndex).getSavesByGoalieInSeason() + 1;
		defendingTeamPlayers.get(appConfiguration.goalieStartIndex).setSavesByGoalieInSeason(saves);
		saves = defendingTeam.getSavesInSeason() + 1;
		defendingTeam.setSavesInSeason(saves);
	}

}
