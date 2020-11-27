package group11.Hockey.BusinessLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import group11.Hockey.BusinessLogic.Enums.Positions;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.models.Player;

public class GeneratingPlayers {
	enum PlayerEnum {
		player
	}

	private static Logger logger = LogManager.getLogger(GeneratingPlayers.class);

	public List<Player> generatePlayers(int numbersOfPlayersToGenerate) {
		logger.info("Entered generatePlayers()");
		List<Player> listOfPlayers = new ArrayList<>();
		String player = PlayerEnum.player.toString();
		int forwardsToGenerate = numbersOfPlayersToGenerate / 2;
		int defenseToGenerate = ((numbersOfPlayersToGenerate / 2) * 40) / 100;
		int goaliesToGenerate = numbersOfPlayersToGenerate - (forwardsToGenerate + defenseToGenerate);
		int startVal = 0;
		for (startVal = 1; startVal <= numbersOfPlayersToGenerate / 2; startVal++) {
			Player forwardPlayer = populatePlayer(player + Integer.toString(startVal));
			forwardPlayer.setPosition(Positions.FORWARD.toString());
			setStatForForwardPlayer(forwardPlayer);
			listOfPlayers.add(forwardPlayer);
		}
		for (int i = 0; i < ((numbersOfPlayersToGenerate / 2) * 40) / 100; i++, startVal++) {
			Player defensePlayer = populatePlayer(player + Integer.toString(startVal));
			defensePlayer.setPosition(Positions.DEFENSE.toString());
			setStatDefensePlayer(defensePlayer);
			listOfPlayers.add(defensePlayer);
		}
		for (int j = 0; j < goaliesToGenerate; j++, startVal++) {
			Player goaliePlayer = populatePlayer(player + Integer.toString(startVal));
			goaliePlayer.setPosition(Positions.GOALIE.toString());
			setStatGoaliePlayer(goaliePlayer);
			listOfPlayers.add(goaliePlayer);
		}

		return listOfPlayers;
	}

	private Player populatePlayer(String playerName) {
		logger.info("Entered populatePlayer()");
		Player player = new Player();
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

	public void setStatForForwardPlayer(Player player) {
		logger.info("Entered setStatForForwardPlayer()");
		int skatingStat = getNumberInRange(12, 20);
		int savingStat = getNumberInRange(1, 7);
		int checkingStat = getNumberInRange(9, 18);
		int shootingStat = getNumberInRange(12, 20);
		player.setChecking(checkingStat);
		player.setSaving(savingStat);
		player.setSkating(skatingStat);
		player.setShooting(shootingStat);
	}

	public void setStatDefensePlayer(Player player) {
		logger.info("Entered setStatDefensePlayer()");
		int skatingStat = getNumberInRange(10, 19);
		int savingStat = getNumberInRange(1, 12);
		int checkingStat = getNumberInRange(12, 20);
		int shootingStat = getNumberInRange(9, 18);
		player.setChecking(checkingStat);
		player.setSaving(savingStat);
		player.setSkating(skatingStat);
		player.setShooting(shootingStat);
	}

	public void setStatGoaliePlayer(Player player) {
		logger.info("Entered setStatGoaliePlayer()");
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
		logger.info("Entered getNumberInRange()");
		Random random = new Random();
		int randomValue = random.nextInt(maxRange - minRange + 1) + minRange;
		return randomValue;
	}
}
