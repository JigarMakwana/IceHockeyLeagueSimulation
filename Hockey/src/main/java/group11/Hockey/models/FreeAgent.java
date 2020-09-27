package group11.Hockey.models;

/**
 * This class contains the business logic for the FreeAgent model
 * 
 * @author jatinpartaprana
 *
 */
public class FreeAgent {
	private String playerName;
	private String position;
	private Boolean captain;

	public FreeAgent(String playerName, String position, Boolean captain) {
		super();
		this.playerName = playerName;
		this.position = position;
		this.captain = captain;
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
	public Boolean getCaptain() {
		return captain;
	}

	/**
	 * @param captain the captain to set
	 */
	public void setCaptain(Boolean captain) {
		this.captain = captain;
	}

}
