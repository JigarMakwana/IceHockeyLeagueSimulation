package group11.Hockey.BusinessLogic.models;

import java.util.Iterator;
import java.util.List;

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
 * @author jatinpartaprana
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

	public int getSavesByDefenceManinSeason() {
		return savesByDefenceManinSeason;
	}

	public void setSavesByDefenceManinSeason(int savesByDefenceManinSeason) {
		this.savesByDefenceManinSeason = savesByDefenceManinSeason;
	}

	public int getGoalsInSeason() {
		return goalsInSeason;
	}

	public void setGoalsInSeason(int goalsInSeason) {
		this.goalsInSeason = goalsInSeason;
	}

	public int getPenaltiesInSeason() {
		return penaltiesInSeason;
	}

	public void setPenaltiesInSeason(int penaltiesInSeason) {
		this.penaltiesInSeason = penaltiesInSeason;
	}

	public int getSavesByGoalieInSeason() {
		return savesByGoalieInSeason;
	}

	public void setSavesByGoalieInSeason(int savesInSeason) {
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
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public boolean getCaptain() {
		return captain;
	}

	public void setCaptain(boolean captain) {
		this.captain = captain;
	}

	public boolean getIsFreeAgent() {
		return isFreeAgent;
	}

	public void setIsFreeAgent(boolean isFreeAgent) {
		this.isFreeAgent = isFreeAgent;
	}

	public float getAge() {
		return age;
	}

	public void setAge(float age) {
		this.age = age;
	}

	public boolean isInjured() {
		return isInjured;
	}

	public void setInjured(boolean isInjured) {
		this.isInjured = isInjured;
	}

	public int getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}

	public int getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public boolean checkInjury(ILeague league) {
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
		return IsRetired;
	}

	public void setIsRetired(boolean isRetired) {
		IsRetired = isRetired;
	}

	public int getNumberOfInjuredDays() {
		return numberOfInjuredDays;
	}

	public void setNumberOfInjuredDays(int numberOfInjuredDays) {
		this.numberOfInjuredDays = numberOfInjuredDays;
	}

	public float getPlayerStrength() {
		IPlayerStrengthContext playerStrength = null;
		if (this.position.equalsIgnoreCase(Positions.FORWARD.toString())) {
			playerStrength = new PlayerStrengthContext(DefaultHockeyFactory.makeForwarsPosition(this));
		} else if (this.position.equalsIgnoreCase(Positions.DEFENSE.toString())) {
			playerStrength = new PlayerStrengthContext(DefaultHockeyFactory.makeDefensePosition(this));
		} else {
			playerStrength = DefaultHockeyFactory.makePlayerStrengthContext(DefaultHockeyFactory.makeGoaliePosition(this));
		}
		return playerStrength.executeStrategy();
	}

	public boolean insertLeagueFreeAgents(List<Player> listOfFreeAgents) {
		boolean freeAgentInsertionCheck = false;

		if (listOfFreeAgents == null || listOfFreeAgents.size() == 0) {
			freeAgentInsertionCheck = true;
		} else {
			for (Player freeAgent : listOfFreeAgents) {
				freeAgentInsertionCheck = playerDb.insertLeagueFreeAgents(leagueName, freeAgent);
			}
		}
		return freeAgentInsertionCheck;
	}

	public boolean insertLeagueRetiredPlayers(List<Player> listOfRetiredPlayers) {
		boolean retiredPlayersInsertionCheck = false;

		if (listOfRetiredPlayers == null || listOfRetiredPlayers.size() == 0) {
			retiredPlayersInsertionCheck = true;
		} else {
			for (Player retiredPlayer : listOfRetiredPlayers) {
				retiredPlayersInsertionCheck = playerDb.insertLeagueRetiredPlayers(leagueName, retiredPlayer);
			}
		}
		return retiredPlayersInsertionCheck;
	}

	public boolean deleteLeaguePlayers() {
		return playerDb.deleteLeaguePlayers(leagueName);
	}

	@Override
	public int compareTo(Player player) {
		return (int) this.getPlayerStrength() - (int) player.getPlayerStrength();
	}

	@Override
	public String toString() {
		return "Player [playerName=" + playerName + ", position=" + position + ", captain=" + captain + ", isFreeAgent="
				+ isFreeAgent + ", leagueName=" + leagueName + ", age=" + age + ", isInjured=" + isInjured
				+ ", IsRetired=" + IsRetired + ", birthDay=" + birthDay + ", birthMonth=" + birthMonth + ", birthYear="
				+ birthYear + "]";
	}

	public void decreaseInjuredDaysForPlayer(int days) {
		if (this.isInjured()) {
			int numberOfDaysLeftToHeal = this.getNumberOfInjuredDays() - days;
			this.setNumberOfInjuredDays(numberOfDaysLeftToHeal > 0 ? numberOfDaysLeftToHeal : 0);
			if (this.getNumberOfInjuredDays() == 0) {
				this.setInjured(false);
			}
		}
	}

	public void increaseAge(ILeague league, int days, float statDecayChance) {
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
		// TODO implement exception if no player is hired from free agents
	}

	public void dropPlayerToFreeAgent(League league, List<Player> playersList) {
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
		float randomValue = (float) Math.random();
		if (randomValue > statDecayChance) {
			this.setShooting(this.getShooting() - 1);
		}
	}

	private void checkAndDecrementPlayerCheckingStat(float statDecayChance) {
		float randomValue = (float) Math.random();
		if (randomValue > statDecayChance) {
			this.setChecking(this.getChecking() - 1);
		}
	}

	private void checkAndDecrementPlayerSkatingStat(float statDecayChance) {
		float randomValue = (float) Math.random();
		if (randomValue > statDecayChance) {
			this.setSkating(this.getSkating() - 1);
		}
	}

	private void checkAndDecrementPlayerSavingStat(float statDecayChance) {
		float randomValue = (float) Math.random();
		if (randomValue > statDecayChance) {
			this.setSaving(this.getSaving() - 1);
		}
	}

}
