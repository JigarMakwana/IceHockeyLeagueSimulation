/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import java.util.List;
import java.util.stream.Collectors;

import group11.Hockey.BusinessLogic.Enums.Positions;
import group11.Hockey.BusinessLogic.Trading.RandomNumGenerator.RandomNoFactory;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.models.IGameplayConfig;
import group11.Hockey.BusinessLogic.models.IInjuries;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;

public class InjurySystem implements IInjurySystem {

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

	public void setInjuryToPlayers(ITeam team) {
		for (IPlayer player : team.getPlayers()) {
			if (determainIsPlayerInjured()) {
				player.setInjured(true);
				player.setNumberOfInjuredDays(determainNumberOfDaysOfInjury());
			}
		}
	}

	public boolean determainIsPlayerInjured() {
		float probabilityOfInjury = RandomNoFactory.makeRandomFloatGenerator().generateRandomNo();
		boolean isPlayerInjured = randomInjuryChance >= probabilityOfInjury;
		return isPlayerInjured;
	}

	public int determainNumberOfDaysOfInjury() {
		int numberOfInjuredDays = (int) RandomNoFactory.makeRandomIntGenerator()
				.generateRandomNo((injuryDaysHigh - injuryDaysLow) + 1);
		numberOfInjuredDays += injuryDaysLow;
		return numberOfInjuredDays;
	}

	/*
	 * Author: Jigar Makwana B00842568
	 */
	public void settleRecoveredPlayer(IRoster roster, IPlayer recoveredPlayer) {
		logger.info("Entered settleRecoveredPlayer()");
		Positions position = findInjuredPlayerPosition(recoveredPlayer);
		IRosterSearch rosterSearch = DefaultHockeyFactory.makeRosterSearch();
		IPlayer replacement = rosterSearch.findWeakestPlayerByPosition(roster.getActiveRoster(), position);
		if (null == replacement) {
			return;
		} else {
			replacement.setActive(false);
			recoveredPlayer.setActive(true);
			roster.swapPlayers(recoveredPlayer, replacement);
		}
	}

	/*
	 * Author: Jigar Makwana B00842568
	 */
	public void settleInjuredPlayer(IRoster roster, IPlayer injuredPlayer) {
		logger.info("Entered settleInjuredPlayer()");
		if (isInjuredSwappingPossible(roster, injuredPlayer)) {
			Positions position = findInjuredPlayerPosition(injuredPlayer);
			IRosterSearch rosterSearch = DefaultHockeyFactory.makeRosterSearch();
			IPlayer replacement = rosterSearch.findStrongestPlayerByPosition(roster.getInActiveRoster(), position);
			if (null == replacement) {
				return;
			} else {
				replacement.setActive(true);
				injuredPlayer.setActive(false);
				roster.swapPlayers(injuredPlayer, replacement);
			}
		}
	}

	/*
	 * Author: Jigar Makwana B00842568
	 */
	public boolean isInjuredSwappingPossible(IRoster roster, IPlayer injuredPlayer) {
		logger.info("Entered isInjuredSwappingPossible()");
		Positions position = findInjuredPlayerPosition(injuredPlayer);
		return isUnInjuredPlayerAvailable(roster, position);
	}

	/*
	 * Author: Jigar Makwana B00842568
	 */
	public Positions findInjuredPlayerPosition(IPlayer injuredPlayer) {
		if (injuredPlayer.getPosition().equalsIgnoreCase(Positions.FORWARD.toString())) {
			return Positions.FORWARD;
		} else if (injuredPlayer.getPosition().equalsIgnoreCase(Positions.DEFENSE.toString())) {
			return Positions.DEFENSE;
		} else {
			return Positions.GOALIE;
		}
	}

	/*
	 * Author: Jigar Makwana B00842568
	 */
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
