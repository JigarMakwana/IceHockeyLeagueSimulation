package group11.Hockey.models;

import java.util.Iterator;
import java.util.List;

import group11.Hockey.AgePlayer;
import group11.Hockey.DefensePosition;
import group11.Hockey.ForwardPosition;
import group11.Hockey.GoaliePosition;
import group11.Hockey.IPosition;
import group11.Hockey.InjurySystem;
import group11.Hockey.PlayerStrength;
import group11.Hockey.RetirePlayer;
import group11.Hockey.db.IPlayerDb;

/**
 * This is model class for Player and it contains all the business logic related
 * to player
 * 
 * @author jatinpartaprana
 *
 */
public class Player extends Stats implements Comparable<Player> {
	private String playerName;
	private String position;
	private boolean captain;
	private boolean isFreeAgent;
	private IPlayerDb playerDb;
	private String leagueName;
	private float age;
	private boolean isInjured;
	private boolean IsRetired;
	private int numberOfInjuredDays;

	public Player() {
		super();
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

	public Player(String leagueName, IPlayerDb playerDb) {
		this.playerDb = playerDb;
		this.leagueName = leagueName;
	}

	/**
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @param playerName the playerName to set
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the captain
	 */
	public boolean getCaptain() {
		return captain;
	}

	/**
	 * @param captain the captain to set
	 */
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
	
	public boolean checkInjury(League league) {
		if(this.isInjured()) {
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
		float strength;
		PlayerStrength playerStrength = new PlayerStrength();
		if (this.position.equalsIgnoreCase("Forward")) {
			strength = playerStrength.calculatePlayerStrength(new ForwardPosition(this));
		} else if (this.position.equalsIgnoreCase("Defense")) {
			strength = playerStrength.calculatePlayerStrength(new DefensePosition(this));
		} else {
			strength = playerStrength.calculatePlayerStrength(new GoaliePosition(this));
		}
		return strength;
	}

	public boolean insertLeagueFreeAgents(List<Player> listOfFreeAgents) {
		boolean freeAgentInsertionCheck = false;

		if (listOfFreeAgents == null || listOfFreeAgents.size() == 0) {
			freeAgentInsertionCheck = true;
		} else {
			for (Player freeAgent : listOfFreeAgents) {
				freeAgentInsertionCheck = playerDb.insertLeagueFreeAgents(leagueName, freeAgent.getPlayerName(),
						freeAgent.getPosition(), freeAgent.getSkating(), freeAgent.getShooting(),
						freeAgent.getChecking(), freeAgent.getSaving(), freeAgent.getAge());
			}
		}
		return freeAgentInsertionCheck;
	}

	public boolean insertLeagueRetiredPlayers(List<Player> listOfRetiredPlayers) {
		boolean retiredPlayersInsertionCheck = false;

		if (listOfRetiredPlayers == null || listOfRetiredPlayers.size() == 0) {
			retiredPlayersInsertionCheck = true;
		} else {
			for (Player freeAgent : listOfRetiredPlayers) {
				retiredPlayersInsertionCheck = playerDb.insertLeagueRetiredPlayers(leagueName,
						freeAgent.getPlayerName(), freeAgent.getPosition(), freeAgent.getSkating(),
						freeAgent.getShooting(), freeAgent.getChecking(), freeAgent.getSaving(), freeAgent.getAge());
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
		return "playerName=" + playerName + ", position=" + position + ", captain=" + captain;
	}

	private void decreaseInjuredDaysForPlayer(int days) {
		if (this.isInjured()) {
			int numberOfDaysLeftToHeal = this.getNumberOfInjuredDays() - days;
			this.setNumberOfInjuredDays(numberOfDaysLeftToHeal > 0 ? numberOfDaysLeftToHeal : 0);
			if (this.getNumberOfInjuredDays() == 0) {
				this.setInjured(false);
			}
		}
	}

	public void increaseAge(League league, int days) {
		float yearsToIncrease = (float) days / 365;
		float age;
		age = this.getAge() + yearsToIncrease;
		this.setAge(age);
		AgePlayer agePlayer = new AgePlayer();
		this.setIsRetired(agePlayer.checkForRetirement(league, age));
		decreaseInjuredDaysForPlayer(days);
	}

	public void replacePlayerWithFreeAgent(League league, List<Player> playersList) {
		List<Player> freeAgents = league.getFreeAgents();
		Iterator<Player> freeAgentsItr = freeAgents.iterator();

		while (freeAgentsItr.hasNext()) {
			Player freeAgent = freeAgentsItr.next();
			if (freeAgent.getPosition().equalsIgnoreCase(this.getPosition())) {
				freeAgent.setIsFreeAgent(false);
				freeAgent.setCaptain(this.getCaptain());
				playersList.add(freeAgent);
				freeAgentsItr.remove();
			}
		}
	}

}
