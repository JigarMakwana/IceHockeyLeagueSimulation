/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation;

import java.util.List;
import java.util.Random;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.Positions;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Player;

public class ActiveDefencePlayer implements IGameStrategy {
	private static Logger logger = LogManager.getLogger(ActiveDefencePlayer.class);

	@Override
	public int calculateAveragePlayersStrength(List<Player> playersList, ITeam defendingTeam) {
		int checking = 0;
		int numberOfDefenseMen = 0;
		int playerStrength = 0;
		for (Player player : playersList) {
			if (player.getPosition().equalsIgnoreCase(Positions.DEFENSE.toString())) {
				checking += player.getChecking();
				numberOfDefenseMen++;
				if (defendingTeam.isOnPenalty()) {
					logger.warn("Defence player is on penality");
					checking = (int) (checking * appConfiguration.reduceDefenceStrength);
					break;
				}
			}
		}
		if (numberOfDefenseMen == 0) {
			numberOfDefenseMen = 1;
		}
		playerStrength = checking / numberOfDefenseMen;
		return playerStrength;
	}

	@Override
	public void playGame(List<Player> shootingTeamPlayers, List<Player> defendingTeamPlayers, ITeam defendingTeam,
			ITeam ShootingTeam, int penaltyPeriod) {
		int penaltyProbality = new Random().nextInt(appConfiguration.penaltyRandomChance);
		int index = bestDefenceMenIndex(defendingTeamPlayers);
		int defenceMenStartIndex = appConfiguration.defenceMenStartIndex;
		if (penaltyProbality <= appConfiguration.penaltyOccuranceValue) {
			int penalties = defendingTeamPlayers.get(index + defenceMenStartIndex).getPenaltiesInSeason() + 1;
			defendingTeamPlayers.get(index + defenceMenStartIndex).setPenaltiesInSeason(penalties);
			defendingTeam.setOnPenalty(true);
			defendingTeam.setPenaltyPeriod(penaltyPeriod);
			penalties = defendingTeam.getPenaltiesInSeason() + 1;
			defendingTeam.setPenaltiesInSeason(penalties);
		}
		int saves = defendingTeamPlayers.get(index + defenceMenStartIndex).getSavesByDefenceManinSeason() + 1;
		defendingTeamPlayers.get(index + defenceMenStartIndex).setSavesByDefenceManinSeason(saves);
		saves = defendingTeam.getSavesInSeason() + 1;
		defendingTeam.setSavesInSeason(saves);
	}

	private int bestDefenceMenIndex(List<Player> defendingTeamPlayers) {
		float player1_checking = defendingTeamPlayers.get(appConfiguration.defenceMenStartIndex).getChecking();
		float player2_checking = defendingTeamPlayers.get(appConfiguration.defenceMenStartIndex + 1).getChecking();

		if (player1_checking > player2_checking) {
			return 0;
		} else {
			return 1;
		}
	}

}
