/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.models.IGameplayConfig;
import group11.Hockey.BusinessLogic.models.IInjuries;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;

public class InjurySystem {

	private float randomInjuryChance;
	private int injuryDaysLow;
	private int injuryDaysHigh;
	private static Logger logger = LogManager.getLogger(InjurySystem.class);

	public InjurySystem(ILeague league) {
		super();
		IGameplayConfig gameplayConfig = league.getGamePlayConfig();
		IInjuries injuries = gameplayConfig.getInjuries();
		this.randomInjuryChance = injuries.getRandomInjuryChance();
		this.injuryDaysLow = injuries.getInjuryDaysLow();
		this.injuryDaysHigh = injuries.getInjuryDaysHigh();
	}

	public void setInjuryToPlayers(Team team) {
		logger.info("Entered setInjuryToPlayers()");
		for (Player player : team.getPlayers()) {
			if (determainIsPlayerInjured()) {
				logger.info("Player "+player.getPlayerName()+" is injured");
				player.setInjured(true);
				player.setNumberOfInjuredDays(determainNumberOfDaysOfInjury());
			}
		}
	}

	public boolean determainIsPlayerInjured() {
		logger.info("Entered determainIsPlayerInjured()");
		float probabilityOfInjury = new Random().nextFloat();
		boolean isPlayerInjured = randomInjuryChance >= probabilityOfInjury;
		return isPlayerInjured;
	}

	public int determainNumberOfDaysOfInjury() {
		logger.info("Entered determainNumberOfDaysOfInjury()");
		int numberOfInjuredDays = new Random().nextInt((injuryDaysHigh - injuryDaysLow) + 1) + injuryDaysLow;
		return numberOfInjuredDays;
	}

	public void settleInjuredPlayer(IRoster roster, IPlayer injuredPlayer) {
		logger.info("Entered settleInjuredPlayer()");
		// TODO When players are injured,
		// swap them for an uninjured player on the inactive roster (if there is one).
		if (isInjuredSwappingPossible(roster, injuredPlayer)) {
			// decide which player will replace the injured player
			// find the player with same position as injured player with highest strength in
			// InActive Roster
			IPlayer replacementPlayer = new Player();
//			roster.swapPlayers(injuredPlayer, replacementPlayer);
		}
	}

	public void settleRecoveredPlayer(IRoster roster, IPlayer recoveredPlayer) {
		logger.info("Entered settleRecoveredPlayer()");
		// TODO When players recover from injuries, if they are better than players on
		// the active roster
		// swap them back to the active roster.
		// decide which player will replace the recoveredPlayer player
		// find the player with same position as recovered player with lowest strength
		// in InActive Roster
		IPlayer replacementPlayer = new Player();
//		roster.swapPlayers(recoveredPlayer, replacementPlayer);
	}

	public boolean isInjuredSwappingPossible(IRoster roster, IPlayer injuredPlayer) {
		logger.info("Entered isInjuredSwappingPossible()");
		// TODO injuredPlayer.getPosition() should return Position and not String
		// check if UnInjured Player Available on InActive Rsoter
		return isUnInjuredPlayerAvailable(roster, Positions.DEFENSE);
	}

	public boolean isUnInjuredPlayerAvailable(IRoster roster, Positions position) {
		logger.info("Entered isUnInjuredPlayerAvailable()");
		int minUnInjuredPlayerRequired = 1;
		List<IPlayer> filteredPosition = roster.getInActiveRoster().stream()
				.filter(player -> player.getPosition().equals(position)).collect(Collectors.toList());

		List<IPlayer> filteredUnInjured = filteredPosition.stream().filter(player -> player.isInjured() == false)
				.collect(Collectors.toList());

		if (filteredUnInjured.size() >= minUnInjuredPlayerRequired) {
			logger.info("Uninjured player available");
			return true;
		} else {
			logger.info("Uninjured player is not available");
			return false;
		}
	}

}
