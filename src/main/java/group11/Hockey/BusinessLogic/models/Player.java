package group11.Hockey.BusinessLogic.models;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.AgePlayer;
import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.IPlayerStrengthContext;
import group11.Hockey.BusinessLogic.InjurySystem;
import group11.Hockey.BusinessLogic.PlayerStrengthContext;
import group11.Hockey.BusinessLogic.Positions;
import group11.Hockey.db.IPlayerDb;

/**
 * This is model class for Player and it contains all the business logic related
 * to player
 *
 */
public class Player extends Stats implements Comparable<Player>, IPlayer {
	private String playerName;
	private String position;
	private boolean captain;
	private boolean isFreeAgent;
	private IPlayerDb playerDb;
	private String leagueName;
	private float age;
	private boolean isInjured;
	private boolean IsRetired;
	private boolean isActive;
	private int numberOfInjuredDays;
	private int goalsInSeason;
	private int penaltiesInSeason;
	private int savesByGoalieInSeason;
	private int savesByDefenceManinSeason;
	private int birthDay;
	private int birthMonth;
	private int birthYear;
	private static Logger logger = LogManager.getLogger(Player.class);

	public int getSavesByDefenceManinSeason() {
		logger.info("Entered getSavesByDefenceManinSeason()");
		return savesByDefenceManinSeason;
	}

	public void setSavesByDefenceManinSeason(int savesByDefenceManinSeason) {
		logger.info("Entered setSavesByDefenceManinSeason()");
		this.savesByDefenceManinSeason = savesByDefenceManinSeason;
	}

	public int getGoalsInSeason() {
		logger.info("Entered getGoalsInSeason()");
		return goalsInSeason;
	}

	public void setGoalsInSeason(int goalsInSeason) {
		logger.info("Entered setGoalsInSeason()");
		this.goalsInSeason = goalsInSeason;
	}

	public int getPenaltiesInSeason() {
		logger.info("Entered getPenaltiesInSeason()");
		return penaltiesInSeason;
	}

	public void setPenaltiesInSeason(int penaltiesInSeason) {
		logger.info("Entered setPenaltiesInSeason()");
		this.penaltiesInSeason = penaltiesInSeason;
	}

	public int getSavesByGoalieInSeason() {
		logger.info("Entered getSavesByGoalieInSeason()");
		return savesByGoalieInSeason;
	}

	public void setSavesByGoalieInSeason(int savesInSeason) {
		logger.info("Entered setSavesByGoalieInSeason()");
		this.savesByGoalieInSeason = savesInSeason;
	}

	public Player() {
		super();
	}

	public Player(String playerName) {
		this.playerName = playerName;
	}

	public Player(float skating, float shooting, float checking, float saving, String playerName, String position,
			boolean captain, boolean isFreeAgent, float age) {
		super(skating, shooting, checking, saving);
		this.playerName = playerName;
		this.position = position;
		this.captain = captain;
		this.isFreeAgent = isFreeAgent;
		this.age = age;
	}

	public Player(float skating, float shooting, float checking, float saving, String playerName, String position,
			boolean captain, boolean isFreeAgent, float age, boolean isActive) {
		super(skating, shooting, checking, saving);
		this.playerName = playerName;
		this.position = position;
		this.captain = captain;
		this.isFreeAgent = isFreeAgent;
		this.isActive = isActive;
		this.age = age;
	}

	public Player(String leagueName, IPlayerDb playerDb) {
		this.playerDb = playerDb;
		this.leagueName = leagueName;
	}

	public boolean isActive() {
		logger.info("Entered isActive()");
		return isActive;
	}

	public void setActive(boolean active) {
		logger.info("Entered setActive()");
		isActive = active;
	}

	public String getPlayerName() {
		logger.info("Entered getPlayerName()");
		return playerName;
	}

	public void setPlayerName(String playerName) {
		logger.info("Entered setPlayerName()");
		this.playerName = playerName;
	}

	public String getPosition() {
		logger.info("Entered getPosition()");
		return position;
	}

	public void setPosition(String position) {
		logger.info("Entered setPosition()");
		this.position = position;
	}

	public boolean getCaptain() {
		logger.info("Entered getCaptain()");
		return captain;
	}

	public void setCaptain(boolean captain) {
		logger.info("Entered setCaptain()");
		this.captain = captain;
	}

	public boolean getIsFreeAgent() {
		logger.info("Entered getIsFreeAgent()");
		return isFreeAgent;
	}

	public void setIsFreeAgent(boolean isFreeAgent) {
		logger.info("Entered setIsFreeAgent()");
		this.isFreeAgent = isFreeAgent;
	}

	public float getAge() {
		logger.info("Entered getAge()");
		return age;
	}

	public void setAge(float age) {
		logger.info("Entered setAge()");
		this.age = age;
	}

	public boolean isInjured() {
		logger.info("Entered isInjured()");
		return isInjured;
	}

	public void setInjured(boolean isInjured) {
		logger.info("Entered setInjured()");
		this.isInjured = isInjured;
	}

	public int getBirthDay() {
		logger.info("Entered getBirthDay()");
		return birthDay;
	}

	public void setBirthDay(int birthDay) {
		logger.info("Entered setBirthDay()");
		this.birthDay = birthDay;
	}

	public int getBirthMonth() {
		logger.info("Entered getBirthMonth()");
		return birthMonth;
	}

	public void setBirthMonth(int birthMonth) {
		logger.info("Entered setBirthMonth()");
		this.birthMonth = birthMonth;
	}

	public int getBirthYear() {
		logger.info("Entered getBirthYear()");
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		logger.info("Entered setBirthYear()");
		this.birthYear = birthYear;
	}

	public boolean checkInjury(ILeague league) {
		logger.info("Entered checkInjury()");
		if (this.isInjured()) {
			return this.isInjured();
		}
		InjurySystem injurySyetem = new InjurySystem(league);
		boolean isPlayerInjured = injurySyetem.determainIsPlayerInjured();
		this.setInjured(isPlayerInjured);
		this.setNumberOfInjuredDays(injurySyetem.determainNumberOfDaysOfInjury());
		return isPlayerInjured;
	}

	public boolean isIsRetired() {
		logger.info("Entered isIsRetired()");
		return IsRetired;
	}

	public void setIsRetired(boolean isRetired) {
		logger.info("Entered setIsRetired()");
		IsRetired = isRetired;
	}

	public int getNumberOfInjuredDays() {
		logger.info("Entered getNumberOfInjuredDays()");
		return numberOfInjuredDays;
	}

	public void setNumberOfInjuredDays(int numberOfInjuredDays) {
		logger.info("Entered setNumberOfInjuredDays()");
		this.numberOfInjuredDays = numberOfInjuredDays;
	}

	public float getPlayerStrength() {
		logger.info("Entered getPlayerStrength()");
		IPlayerStrengthContext playerStrength = null;
		if (this.position.equalsIgnoreCase(Positions.FORWARD.toString())) {
			logger.info("Forward Player");
			playerStrength = new PlayerStrengthContext(DefaultHockeyFactory.makeForwarsPosition(this));
		} else if (this.position.equalsIgnoreCase(Positions.DEFENSE.toString())) {
			logger.info("Defense Player");
			playerStrength = new PlayerStrengthContext(DefaultHockeyFactory.makeDefensePosition(this));
		} else {
			logger.info("Goalie Player");
			playerStrength = DefaultHockeyFactory.makePlayerStrengthContext(DefaultHockeyFactory.makeGoaliePosition(this));
		}
		return playerStrength.executeStrategy();
	}

	public boolean insertLeagueFreeAgents(List<Player> listOfFreeAgents) {
		logger.info("Entered insertLeagueFreeAgents()");
		boolean freeAgentInsertionCheck = false;

		if (listOfFreeAgents == null || listOfFreeAgents.size() == 0) {
			logger.info("No free agent players found");
			freeAgentInsertionCheck = true;
		} else {
			logger.info("Inserting into free agent players");
			for (Player freeAgent : listOfFreeAgents) {
				freeAgentInsertionCheck = playerDb.insertLeagueFreeAgents(leagueName, freeAgent);
			}
		}
		return freeAgentInsertionCheck;
	}

	public boolean insertLeagueRetiredPlayers(List<Player> listOfRetiredPlayers) {
		logger.info("Entered insertLeagueRetiredPlayers()");
		boolean retiredPlayersInsertionCheck = false;

		if (listOfRetiredPlayers == null || listOfRetiredPlayers.size() == 0) {
			logger.info("No retired players found");
			retiredPlayersInsertionCheck = true;
		} else {
			logger.info("Inserting into retired players");
			for (Player retiredPlayer : listOfRetiredPlayers) {
				retiredPlayersInsertionCheck = playerDb.insertLeagueRetiredPlayers(leagueName, retiredPlayer);
			}
		}
		return retiredPlayersInsertionCheck;
	}

	public boolean deleteLeaguePlayers() {
		logger.info("Entered deleteLeaguePlayers()");
		return playerDb.deleteLeaguePlayers(leagueName);
	}

	@Override
	public int compareTo(Player player) {
		logger.info("Entered compareTo()");
		return (int) this.getPlayerStrength() - (int) player.getPlayerStrength();
	}

	@Override
	public String toString() {
		logger.info("Entered toString()");
		return "Player [playerName=" + playerName + ", position=" + position + ", captain=" + captain + ", isFreeAgent="
				+ isFreeAgent + ", leagueName=" + leagueName + ", age=" + age + ", isInjured=" + isInjured
				+ ", IsRetired=" + IsRetired + ", birthDay=" + birthDay + ", birthMonth=" + birthMonth + ", birthYear="
				+ birthYear + "]";
	}

	public void decreaseInjuredDaysForPlayer(int days) {
		logger.info("Entered decreaseInjuredDaysForPlayer()");
		if (this.isInjured()) {
			int numberOfDaysLeftToHeal = this.getNumberOfInjuredDays() - days;
			this.setNumberOfInjuredDays(numberOfDaysLeftToHeal > 0 ? numberOfDaysLeftToHeal : 0);
			if (this.getNumberOfInjuredDays() == 0) {
				this.setInjured(false);
			}
		}
	}

	public void increaseAge(ILeague league, int days, float statDecayChance) {
		logger.info("Entered increaseAge()");
		float age;
		age = this.getAge() + 1;
		this.setAge(age);
		AgePlayer agePlayer = new AgePlayer();
		this.setIsRetired(agePlayer.checkForRetirement(league, age));
		checkAndDecrementPlayerShootingStat(statDecayChance);
		checkAndDecrementPlayerCheckingStat(statDecayChance);
		checkAndDecrementPlayerSkatingStat(statDecayChance);
		checkAndDecrementPlayerSavingStat(statDecayChance);
		decreaseInjuredDaysForPlayer(days);
	}

	public void replacePlayerWithFreeAgent(ILeague league, List<Player> playersList) {
		logger.info("Entered replacePlayerWithFreeAgent()");
		List<Player> freeAgents = (List<Player>) league.getFreeAgents();
		Iterator<Player> freeAgentsItr = freeAgents.iterator();

		while (freeAgentsItr.hasNext()) {
			Player freeAgent = freeAgentsItr.next();
			if (freeAgent.getPosition().equalsIgnoreCase(this.getPosition())) {
				freeAgent.setIsFreeAgent(false);
				freeAgent.setCaptain(this.getCaptain());
				playersList.add(freeAgent);
				freeAgentsItr.remove();
				break;
			}
		}
	}

	public void dropPlayerToFreeAgent(League league, List<Player> playersList) {
		logger.info("Entered dropPlayerToFreeAgent()");
		List<Player> freeAgents = (List<Player>) league.getFreeAgents();
		Iterator<Player> playersListItr = playersList.iterator();

		while (playersListItr.hasNext()) {
			Player player = playersListItr.next();
			if (player.getPosition().equalsIgnoreCase(this.getPosition())) {
				player.setIsFreeAgent(true);
				player.setCaptain(this.getCaptain());
				freeAgents.add(player);
				playersListItr.remove();
				break;
			}
		}
	}

	public void removeFreeAgentsFromLeague(ILeague league, List<Player> freeAgents) {
		logger.info("Entered removeFreeAgentsFromLeague()");
		List<Player> listOfFreeAgentsInLeague = (List<Player>) league.getFreeAgents();
		Iterator<Player> interator = listOfFreeAgentsInLeague.iterator();
		while (interator.hasNext()) {
			Player pl = interator.next();
			for (Player freeAgent : freeAgents) {
				if (freeAgent.toString().equalsIgnoreCase(pl.toString())) {
					interator.remove();
				}
			}
		}
	}

	private void checkAndDecrementPlayerShootingStat(float statDecayChance) {
		logger.info("Entered checkAndDecrementPlayerShootingStat()");
		float randomValue = (float) Math.random();
		if (randomValue > statDecayChance) {
			this.setShooting(this.getShooting() - 1);
		}
	}

	private void checkAndDecrementPlayerCheckingStat(float statDecayChance) {
		logger.info("Entered checkAndDecrementPlayerCheckingStat()");
		float randomValue = (float) Math.random();
		if (randomValue > statDecayChance) {
			this.setChecking(this.getChecking() - 1);
		}
	}

	private void checkAndDecrementPlayerSkatingStat(float statDecayChance) {
		logger.info("Entered checkAndDecrementPlayerSkatingStat()");
		float randomValue = (float) Math.random();
		if (randomValue > statDecayChance) {
			this.setSkating(this.getSkating() - 1);
		}
	}

	private void checkAndDecrementPlayerSavingStat(float statDecayChance) {
		logger.info("Entered checkAndDecrementPlayerSavingStat()");
		float randomValue = (float) Math.random();
		if (randomValue > statDecayChance) {
			this.setSaving(this.getSaving() - 1);
		}
	}

}
