package group11.Hockey.BusinessLogic;

import java.util.Random;

import group11.Hockey.BusinessLogic.models.GameplayConfig;
import group11.Hockey.BusinessLogic.models.Injuries;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

public class InjurySystem {

	private float randomInjuryChance;
	private int injuryDaysLow;
	private int injuryDaysHigh;

	public InjurySystem(League league) {
		super();
		GameplayConfig gameplayConfig = league.getGamePlayConfig();
		Injuries injuries = gameplayConfig.getInjuries();
		this.randomInjuryChance = injuries.getRandomInjuryChance();
		this.injuryDaysLow = injuries.getInjuryDaysLow();
		this.injuryDaysHigh = injuries.getInjuryDaysHigh();
	}

	public void setInjuryToPlayers(Team team) {
		for (Player player : team.getPlayers()) {
			if (determainIsPlayerInjured()) {
				player.setInjured(true);
				player.setNumberOfInjuredDays(determainNumberOfDaysOfInjury());
			}
		}
	}

	public boolean determainIsPlayerInjured() {
		float probabilityOfInjury = new Random().nextFloat();
		boolean isPlayerInjured = randomInjuryChance >= probabilityOfInjury;
		return isPlayerInjured;
	}

	public int determainNumberOfDaysOfInjury() {
		int numberOfInjuredDays = new Random().nextInt((injuryDaysHigh - injuryDaysLow) + 1) + injuryDaysLow;
		return numberOfInjuredDays;
	}

}
