package group11.Hockey;

import java.util.Random;

import group11.Hockey.models.GameplayConfig;
import group11.Hockey.models.Injuries;
import group11.Hockey.models.League;
import group11.Hockey.models.Player;
import group11.Hockey.models.Team;

public class InjurySystem {

	public void checkForInjury(League league, Team team) {
		GameplayConfig gameplayConfig = league.getGamePlayConfig();
		Injuries injuries = gameplayConfig.getInjuries();
		float randomInjuryChance = injuries.getRandomInjuryChance();
		int injuryDaysLow = injuries.getInjuryDaysLow();
		int injuryDaysHigh = injuries.getInjuryDaysHigh();
		float probabilityOfInjury;

		for (Player player : team.getPlayers()) {
			probabilityOfInjury = new Random().nextFloat();

			if (randomInjuryChance >= probabilityOfInjury) {
				player.setInjured(true);
				int numberOfInjuredDays = new Random().nextInt((injuryDaysHigh - injuryDaysLow) + 1) + injuryDaysLow;
				player.setNumberOfInjuredDays(numberOfInjuredDays);
			}
		}

	}

}
