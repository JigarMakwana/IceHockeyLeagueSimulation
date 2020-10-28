package group11.Hockey.models;

import java.util.List;

import group11.Hockey.db.Team.ITeamDb;

/**
 * This class contain all the business logic related to team model
 *
 * @author jatinpartaprana
 *
 */
public class Team {

	private String teamName;
	private String generalManager;
	private Coach headCoach;
	private List<Player> players = null;
	private float teamStrength;
	private boolean isUserTeam = false;
	private int lossPoint;

	public Team(String teamName, String generalManager, Coach headCoach, List<Player> players) {
		super();
		this.teamName = teamName;
		this.generalManager = generalManager;
		this.headCoach = headCoach;
		this.players = players;
	}

	public Team() {

	}

	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @param teamName the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	/**
	 * @return the generalManager
	 */
	public String getGeneralManager() {
		return generalManager;
	}

	/**
	 * @param generalManager the generalManager to set
	 */
	public void setGeneralManager(String generalManager) {
		this.generalManager = generalManager;
	}

	/**
	 * @return the headCoach
	 */
	public Coach getHeadCoach() {
		return headCoach;
	}

	/**
	 * @param headCoach the headCoach to set
	 */
	public void setHeadCoach(Coach headCoach) {
		this.headCoach = headCoach;
	}

	/**
	 * @return the players
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * @param players the players to set
	 */
	public void setPlayers(List<Player> players) {
		this.players = players;
	}


	public float getTeamStrength() {
		List<Player> players = this.getPlayers();
		float teamStrength = 0;
		if (players == null || players.size() == 0) {
			return 0;
		}
		for (Player player : players) {
			teamStrength += player.getPlayerStrength();
		}
		return teamStrength;
	}

	/**
	 * @return the isUserTeam
	 */
	public boolean isUserTeam() {
		return isUserTeam;
	}

	/**
	 * @param isUserTeam the isUserTeam to set
	 */
	public void setUserTeam(boolean isUserTeam) {
		this.isUserTeam = isUserTeam;
	}

	public boolean isTeamNameValid(String teamName, League league) {
		boolean isTeamNameValid = true;
		List<Conference> cconferenceList = league.getConferences();
		for (Conference conference : cconferenceList) {
			List<Division> divisionList = conference.getDivisions();
			for (Division division : divisionList) {
				List<Team> teamList = division.getTeams();
				for (Team team : teamList) {
					if (team.getTeamName() != null && team.getTeamName().equalsIgnoreCase(teamName)) {
						isTeamNameValid = false;
						return isTeamNameValid;
					}
				}
			}
		}
		return isTeamNameValid;
	}

	public List<League> loadTeamWithTeamName(String teamName, ITeamDb teamDb) {
		return teamDb.loadTeamFromTeamName(teamName);
	}

	@Override
	public String toString() {
		return "Team [teamName=" + teamName + ", generalManager=" + generalManager + ", headCoach=" + headCoach
				+ ", players=" + players + "]";
	}

	/**
	 *
	 * @author  Jigar Makwana B00842568
	 *
	 */
	public List<Player>  sortPlayersByStrength() {
		List<Player> sortedPlayerList = this.players;
		/* bubble sort */
		int i, j;
		Player temp;
		boolean swapped;
		int length = sortedPlayerList.size();
		for (i = 0; i < length - 1; i++) {
			swapped = false;
			for (j = 0; j < length - i - 1; j++) {
				if (sortedPlayerList.get(j).getPlayerStrength() >
						sortedPlayerList.get(j + 1).getPlayerStrength()) {
					temp = sortedPlayerList.get(j);
					sortedPlayerList.set(j, sortedPlayerList.get(j + 1));
					sortedPlayerList.set(j + 1, temp);
					swapped = true;
				}
			}
			if (swapped == false)
				break;
		}
		return sortedPlayerList;
	}

	/**
	 *
	 * @author  Jigar Makwana B00842568
	 *
	 */
	public int getLossPoint()
	{
		return lossPoint;
	}

	/**
	 *
	 * @author  Jigar Makwana B00842568
	 *
	 */
	public void setLossPoint(int lossPoint)
	{
		this.lossPoint = lossPoint;
	}
}
