/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.Positions;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Player;

public class GoaliePlayerActive extends GameStrategy {
	
	private static Logger logger = LogManager.getLogger(GoaliePlayerActive.class);

	@Override
	public int calculateAveragePlayersStrength(List<Player> playersList, ITeam defendingTeam) {
		logger.info("Entered calculateAveragePlayersStrength()");
		int saving = 0;
		int numberOfGoalieMen = 0;
		for (Player player : playersList) {
			if (player.getPosition().equalsIgnoreCase(Positions.GOALIE.toString())) {
				saving += player.getSaving();
				numberOfGoalieMen++;
			}
		}
		if (numberOfGoalieMen == 0) {
			numberOfGoalieMen = 1;
		}
		return saving / numberOfGoalieMen;
	}

	@Override
	public void playGame(List<Player> shootingTeamPlayers, List<Player> defendingTeamPlayers, ITeam defendingTeam,
			ITeam ShootingTeam, int penaltyPeriod) {
		logger.info("Entered playGame()");
		int saves = defendingTeamPlayers.get(appConfiguration.goalieStartIndex).getSavesByGoalieInSeason() + 1;
		defendingTeamPlayers.get(appConfiguration.goalieStartIndex).setSavesByGoalieInSeason(saves);
		saves = defendingTeam.getSavesInSeason() + 1;
		defendingTeam.setSavesInSeason(saves);
	}

}
