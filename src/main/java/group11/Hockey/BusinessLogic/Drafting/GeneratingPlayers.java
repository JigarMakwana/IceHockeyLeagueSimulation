package group11.Hockey.BusinessLogic.Drafting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.Enums.Positions;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.Player;
/**
 *
 * @author Jatin Partap Rana
 *
 */
public class GeneratingPlayers implements IGeneratingPlayers{
	enum PlayerEnum {
		player
	}

	private static Logger logger = LogManager.getLogger(GeneratingPlayers.class);

	public List<IPlayer> generatePlayers(int numbersOfPlayersToGenerate) {
		logger.debug("Entered generatePlayers()");
		List<IPlayer> listOfPlayers = new ArrayList<>();
		String player = PlayerEnum.player.toString();
		int forwardsToGenerate = numbersOfPlayersToGenerate / 2;
		int defenseToGenerate = ((numbersOfPlayersToGenerate / 2) * 40) / 100;
		int goaliesToGenerate = numbersOfPlayersToGenerate - (forwardsToGenerate + defenseToGenerate);
		int startVal = 0;
		for (startVal = 1; startVal <= numbersOfPlayersToGenerate / 2; startVal++) {
			IPlayer forwardPlayer = populatePlayer(player + Integer.toString(startVal));
			forwardPlayer.setPosition(Positions.FORWARD.toString());
			setStatForForwardPlayer(forwardPlayer);
			listOfPlayers.add(forwardPlayer);
		}
		for (int i = 0; i < ((numbersOfPlayersToGenerate / 2) * 40) / 100; i++, startVal++) {
			IPlayer defensePlayer = populatePlayer(player + Integer.toString(startVal));
			defensePlayer.setPosition(Positions.DEFENSE.toString());
			setStatDefensePlayer(defensePlayer);
			listOfPlayers.add(defensePlayer);
		}
		for (int j = 0; j < goaliesToGenerate; j++, startVal++) {
			IPlayer goaliePlayer = populatePlayer(player + Integer.toString(startVal));
			goaliePlayer.setPosition(Positions.GOALIE.toString());
			setStatGoaliePlayer(goaliePlayer);
			listOfPlayers.add(goaliePlayer);
		}

		return listOfPlayers;
	}

	private IPlayer populatePlayer(String playerName) {
		logger.debug("Entered populatePlayer()");
		IPlayer player = new Player();
		player.setPlayerName(playerName);
		player.setBirthDay(11);
		player.setBirthMonth(10);
		player.setBirthYear(2001);
		player.setCaptain(false);
		player.setInjured(false);
		player.setIsFreeAgent(false);
		player.setIsRetired(false);
		return player;
	}

	public void setStatForForwardPlayer(IPlayer player) {
		logger.debug("Entered setStatForForwardPlayer()");
		int skatingStat = getNumberInRange(12, 20);
		int savingStat = getNumberInRange(1, 7);
		int checkingStat = getNumberInRange(9, 18);
		int shootingStat = getNumberInRange(12, 20);
		player.setChecking(checkingStat);
		player.setSaving(savingStat);
		player.setSkating(skatingStat);
		player.setShooting(shootingStat);
	}

	public void setStatDefensePlayer(IPlayer player) {
		logger.debug("Entered setStatDefensePlayer()");
		int skatingStat = getNumberInRange(10, 19);
		int savingStat = getNumberInRange(1, 12);
		int checkingStat = getNumberInRange(12, 20);
		int shootingStat = getNumberInRange(9, 18);
		player.setChecking(checkingStat);
		player.setSaving(savingStat);
		player.setSkating(skatingStat);
		player.setShooting(shootingStat);
	}

	public void setStatGoaliePlayer(IPlayer player) {
		logger.debug("Entered setStatGoaliePlayer()");
		int skatingStat = getNumberInRange(8, 15);
		int savingStat = getNumberInRange(12, 20);
		int checkingStat = getNumberInRange(1, 12);
		int shootingStat = getNumberInRange(1, 10);
		player.setChecking(checkingStat);
		player.setSaving(savingStat);
		player.setSkating(skatingStat);
		player.setShooting(shootingStat);
	}

	public int getNumberInRange(int minRange, int maxRange) {
		logger.debug("Entered getNumberInRange()");
		Random random = new Random();
		int randomValue = random.nextInt(maxRange - minRange + 1) + minRange;
		return randomValue;
	}
}
