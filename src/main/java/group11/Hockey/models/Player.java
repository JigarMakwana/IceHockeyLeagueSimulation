package group11.Hockey.models;

import java.util.List;

import group11.Hockey.db.IPlayerDb;

/**
 * This is model class for Player and it contains all the business logic related
 * to player
 * 
 * @author jatinpartaprana
 *
 */
public class Player extends Stats {
	private String playerName;
	private String position;
	private boolean captain;
	private boolean isFreeAgent;
	private int age;
	private IPlayerDb playerDb;
	private String leagueName;

	public Player() {
		super();
	}

	public Player(float skating, float shooting, float checking, float saving, String playerName, String position,
			boolean captain, boolean isFreeAgent, int age) {
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	@Override
	public String toString() {
		return "playerName=" + playerName + ", position=" + position + ", captain=" + captain;
	}

}
