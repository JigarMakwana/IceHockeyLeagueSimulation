package group11.Hockey.BusinessLogic.models;

import java.util.List;

import group11.Hockey.db.Team.ITeamDb;
import group11.Hockey.BusinessLogic.models.Roster.RosterSize;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;
import group11.Hockey.BusinessLogic.ConstantSupplier;
import group11.Hockey.BusinessLogic.IConstantSupplier;

/**
 * This class contain all the business logic related to team model
 *
 * @author jatinpartaprana
 *
 */
public class Team implements ITeam {

	private String teamName;
	private String generalManager;
	private Coach headCoach;
	private List<Player> players = null;
	private boolean isUserTeam = false;
	private int losses;
	private IRoster roster;
	private int averageShoots;
	private boolean isOnPenalty;
	private int penaltyPeriod;
	private int goalsInSeason;
	private int penaltiesInSeason;
	private int savesInSeason;

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

	public int getSavesInSeason() {
		return savesInSeason;
	}

	public void setSavesInSeason(int savesInSeason) {
		this.savesInSeason = savesInSeason;
	}

	public boolean isOnPenalty() {
		return isOnPenalty;
	}

	public void setOnPenalty(boolean isOnPenality) {
		this.isOnPenalty = isOnPenality;
	}

	public int getPenaltyPeriod() {
		return penaltyPeriod;
	}

	public void setPenaltyPeriod(int penaltyPeriod) {
		this.penaltyPeriod = penaltyPeriod;
	}

	public int getAverageShoots() {
		return averageShoots;
	}

	public void setAverageShoots(int averageShoots) {
		this.averageShoots = averageShoots;
	}

	private int wins;
	private int points;

	public Team(String teamName, String generalManager, Coach headCoach, List<Player> players) {
		super();
		this.teamName = teamName;
		this.generalManager = generalManager;
		this.headCoach = headCoach;
		this.players = players;
		// TODO Creational pattern
		IConstantSupplier rosterSize = new ConstantSupplier(RosterSize.ACTIVE_ROSTER_SIZE.getNumVal(),
				RosterSize.INACTIVE_ROSTER_SIZE.getNumVal(), RosterSize.FORWARD_SIZE.getNumVal(),
				RosterSize.DEFENSE_SIE.getNumVal(), RosterSize.GOALIE_SIZE.getNumVal());
		// roster = new Roster(this.players, rosterSize);
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

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
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

	public boolean isTeamNameValid(String teamName, ILeague league) {
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

	public boolean teamExistsInDivision(String teamName, Division divisionName) {
		boolean teamExists = false;
		List<Team> teamList = divisionName.getTeams();
		if (teamList == null) {
			return teamExists;
		}
		for (Team team : teamList) {
			if (team.getTeamName().equalsIgnoreCase(teamName)) {
				teamExists = true;
				return teamExists;
			}
		}
		return teamExists;
	}

	public Team getTeamFromDivision(String teamName, Division division) {
		List<Team> teamList = division.getTeams();
		Team teamInDivision = null;
		for (Team team : teamList) {
			if (team.getTeamName().equalsIgnoreCase(teamName)) {
				teamInDivision = team;
				return team;
			}
		}
		return teamInDivision;
	}

	public League loadLeagueWithTeamName(String teamName, ITeamDb teamDb) {
		return teamDb.loadLeagueWithTeamName(teamName);
	}

	public void addGeneralMangerToTeam(Team team, String generalMangerName, ILeague league) {
		team.setGeneralManager(generalMangerName);
		List<GeneralManager> generalManagers = league.getGeneralManagers();
		for (GeneralManager gm : generalManagers) {
			if (gm.getName() != null && gm.getName().equalsIgnoreCase(generalMangerName)) {
				generalManagers.remove(gm);
				break;
			}
		}
	}

	public void addCoachToTeam(Team team, String coachName, ILeague league) {
		Coach coach = new Coach();
		coach.setName(coachName);
		team.setHeadCoach(coach);
		List<Coach> coaches = league.getCoaches();
		for (Coach ch : coaches) {
			if (ch.getName() != null && ch.getName().equalsIgnoreCase(coach.getName())) {
				coaches.remove(ch);
				break;
			}
		}
	}

	@Override
	public String toString() {
		return "Team [teamName=" + teamName + ", generalManager=" + generalManager + ", headCoach=" + headCoach
				+ ", players=" + players + "]";
	}
}
