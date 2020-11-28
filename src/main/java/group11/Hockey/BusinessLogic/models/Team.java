package group11.Hockey.BusinessLogic.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import group11.Hockey.BusinessLogic.PlayerDraft;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;
import group11.Hockey.db.Team.ITeamDb;

/**
 * This class contain all the business logic related to team model
 *
 * @author jatinpartaprana
 *
 */
public class Team implements ITeam, Comparable<Team> {

	private String teamName;
	private IGeneralManager generalManager;
	private ICoach headCoach;
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
	private int wins;
	private int points;
	private List<Boolean> tradedPicks;

	public Team(String teamName, IGeneralManager generalManager, ICoach headCoach, List<? extends IPlayer> players) {
		super();
		this.teamName = teamName;
		this.generalManager = generalManager;
		this.headCoach = headCoach;
		this.players = (List<Player>) players;
		this.tradedPicks = new ArrayList<>(Collections.nCopies(PlayerDraft.PLAYER_DRAFT_ROUNDS.getNumVal(), false));
//		this.roster = DefaultHockeyFactory.makeRoster(this.teamName, (List<IPlayer>) players);
	}

	public Team() {

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

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public IGeneralManager getGeneralManager() {
		return generalManager;
	}

	public void setGeneralManager(IGeneralManager generalManager) {
		this.generalManager = generalManager;
	}

	public ICoach getHeadCoach() {
		return headCoach;
	}

	public void setHeadCoach(Coach headCoach) {
		this.headCoach = headCoach;
	}

	public IRoster getRoster() {
		return roster;
	}

	public void setRoster(IRoster roster) {
		this.roster = roster;
	}

	public List<Player> getPlayers() {
		return players;
	}

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

	public boolean isUserTeam() {
		return isUserTeam;
	}

	public void setUserTeam(boolean isUserTeam) {
		this.isUserTeam = isUserTeam;
	}

	public boolean isTeamNameValid(String teamName, ILeague league) {
		boolean isTeamNameValid = true;
		List<IConference> cconferenceList = league.getConferences();
		for (IConference conference : cconferenceList) {
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

	public void addGeneralMangerToTeam(Team team, IGeneralManager gmObj, ILeague league) {
		team.setGeneralManager(gmObj);
		List<IGeneralManager> generalManagers = league.getGeneralManagers();
		for (IGeneralManager gm : generalManagers) {
			if (gm.getName() != null && gm.getPersonality() != null && gm.getName().equalsIgnoreCase(gmObj.getName())
					&& gm.getPersonality().equalsIgnoreCase(gmObj.getPersonality())) {
				generalManagers.remove(gm);
				break;
			}
		}
	}

	public void addCoachToTeam(Team team, String coachName, ILeague league) {
		Coach coach = new Coach();
		coach.setName(coachName);
		team.setHeadCoach(coach);
		List<ICoach> coaches = league.getCoaches();
		for (ICoach ch : coaches) {
			if (ch.getName() != null && ch.getName().equalsIgnoreCase(coach.getName())) {
				coaches.remove(ch);
				break;
			}
		}
	}

	@Override
	public int compareTo(Team team) {
		if (this.getPoints() > team.getPoints()) {
			return 1;
		} else {
			return -1;
		}
	}

	public List<Team> orderTeamsInLeagueStandings(ILeague league) {
		List<Team> teamsOrderedInReverse = new ArrayList<>();
		for (IConference conference : league.getConferences()) {
			for (IDivision divison : conference.getDivisions()) {
				List<Team> team = divison.getTeams();
				teamsOrderedInReverse.addAll(team);
			}
		}
		sortTeam(teamsOrderedInReverse);
		return teamsOrderedInReverse;
	}

	public void sortTeam(List<Team> teamsOrderedInReverse) {
		Collections.sort(teamsOrderedInReverse);
	}

	public List<Boolean> getTradedPicks() {
		return tradedPicks;
	}

	public void setTradedPicks(int index) {
		tradedPicks.set(index, true);
	}

	@Override
	public String toString() {
		return "Team [teamName=" + teamName + ", generalManager=" + generalManager + ", headCoach=" + headCoach
				+ ", players=" + players + "]";
	}
}
