package group11.Hockey.GameSimulation;

import java.util.List;
import java.util.Random;

import group11.Hockey.BusinessLogic.Positions;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Player;

public class DefencePlayerActive extends GameStrategy {

	@Override
	public int calculateAveragePlayersStrength(List<Player> playersList, ITeam defendingTeam) {
		int checking = 0;
		int numberOfDefenseMen = 0;
		for (Player player : playersList) {
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
		return checking / numberOfDefenseMen;
	}

	@Override
	public void playGame(List<Player> shootingTeamPlayers, List<Player> defendingTeamPlayers, ITeam defendingTeam,
			ITeam ShootingTeam, int penaltyPeriod) {

		int penaltyProbality = new Random().nextInt(appConfiguration.penaltyRandomChance);
		int index = new Random().nextInt(2);
		if (penaltyProbality <= appConfiguration.penaltyOccuranceValue) {
			int penalties = defendingTeamPlayers.get(index + 3).getPenaltiesInSeason() + 1;
			defendingTeamPlayers.get(index + 3).setPenaltiesInSeason(penalties);
			defendingTeam.setOnPenalty(true);
			defendingTeam.setPenaltyPeriod(penaltyPeriod);
			penalties = defendingTeam.getPenaltiesInSeason() + 1;
			defendingTeam.setPenaltiesInSeason(penalties);
		}
		int saves = defendingTeamPlayers.get(index + 3).getSavesByDefenceManinSeason() + 1;
		defendingTeamPlayers.get(index + 3).setSavesByDefenceManinSeason(saves);
		saves = defendingTeam.getSavesInSeason() + 1;
		defendingTeam.setSavesInSeason(saves);
	}

}
