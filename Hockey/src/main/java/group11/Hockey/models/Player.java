package group11.Hockey.models;

/**
 * This is model class for Player and it contains all the business logic related
 * to player
 * 
 * @author jatinpartaprana
 *
 */
public class Player {
	private String playerName;
	private String position;
	private Boolean captain;

	public Player() {
	}

	public Player(String playerName, String position, Boolean captain) {
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

	@Override
	public String toString() {
		return "playerName=" + playerName + ", position=" + position + ", captain=" + captain;
	}

}
