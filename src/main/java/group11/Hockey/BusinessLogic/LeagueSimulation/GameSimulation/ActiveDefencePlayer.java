/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation;

import java.util.List;
import java.util.Random;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.Enums.Positions;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;

public class ActiveDefencePlayer implements IGameStrategy {
	private static Logger logger = LogManager.getLogger(ActiveDefencePlayer.class);

	@Override
	public int calculateAveragePlayersStrength(List<IPlayer> playersList, ITeam defendingTeam) {
		int checking = 0;
		int numberOfDefenseMen = 0;
		int playerStrength = 0;

		for (IPlayer player : playersList) {

			if (player.getPosition().equalsIgnoreCase(Positions.DEFENSE.toString())) {
				checking += player.getChecking();
				numberOfDefenseMen++;
				if (defendingTeam.isOnPenalty()) {
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


	public void playGame(List<IPlayer> shootingTeamPlayers, List<IPlayer> defendingTeamPlayers, ITeam defendingTeam,
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

	private int bestDefenceMenIndex(List<IPlayer> defendingTeamPlayers) {
		float player1_checking = defendingTeamPlayers.get(appConfiguration.defenceMenStartIndex).getChecking();
		float player2_checking = defendingTeamPlayers.get(appConfiguration.defenceMenStartIndex + 1).getChecking();

		if (player1_checking > player2_checking) {
			return 0;
		} else {
			return 1;
		}
	}

}
