package group11.Hockey.BusinessLogic.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.PlayerDraft;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;
import group11.Hockey.db.Team.ITeamDb;

/**
 * This class contain all the business logic related to team model
 *
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
	private static Logger logger = LogManager.getLogger(Team.class);

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

	public int getSavesInSeason() {
		logger.info("Entered getSavesInSeason()");
		return savesInSeason;
	}

	public void setSavesInSeason(int savesInSeason) {
		logger.info("Entered setSavesInSeason()");
		this.savesInSeason = savesInSeason;
	}

	public boolean isOnPenalty() {
		logger.info("Entered isOnPenalty()");
		return isOnPenalty;
	}

	public void setOnPenalty(boolean isOnPenality) {
		logger.info("Entered setOnPenalty()");
		this.isOnPenalty = isOnPenality;
	}

	public int getPenaltyPeriod() {
		logger.info("Entered getPenaltyPeriod()");
		return penaltyPeriod;
	}

	public void setPenaltyPeriod(int penaltyPeriod) {
		logger.info("Entered setPenaltyPeriod()");
		this.penaltyPeriod = penaltyPeriod;
	}

	public int getAverageShoots() {
		logger.info("Entered getAverageShoots()");
		return averageShoots;
	}

	public void setAverageShoots(int averageShoots) {
		logger.info("Entered setAverageShoots()");
		this.averageShoots = averageShoots;
	}

	public String getTeamName() {
		logger.info("Entered getTeamName()");
		return teamName;
	}

	public void setTeamName(String teamName) {
		logger.info("Entered setTeamName()");
		this.teamName = teamName;
	}

	public IGeneralManager getGeneralManager() {
		logger.info("Entered getGeneralManager()");
		return generalManager;
	}

	public void setGeneralManager(IGeneralManager generalManager) {
		logger.info("Entered setGeneralManager()");
		this.generalManager = generalManager;
	}

	public ICoach getHeadCoach() {
		logger.info("Entered getHeadCoach()");
		return headCoach;
	}

	public void setHeadCoach(Coach headCoach) {
		logger.info("Entered setHeadCoach()");
		this.headCoach = headCoach;
	}

	public IRoster getRoster() {
		logger.info("Entered getRoster()");
		return roster;
	}

	public void setRoster(IRoster roster) {
		logger.info("Entered setRoster()");
		this.roster = roster;
	}

	public List<Player> getPlayers() {
		logger.info("Entered getPlayers()");
		return players;
	}

	public void setPlayers(List<Player> players) {
		logger.info("Entered setPlayers()");
		this.players = players;
	}

	public int getLosses() {
		logger.info("Entered getLosses()");
		return losses;
	}

	public void setLosses(int losses) {
		logger.info("Entered setLosses()");
		this.losses = losses;
	}

	public int getWins() {
		logger.info("Entered getWins()");
		return wins;
	}

	public void setWins(int wins) {
		logger.info("Entered setWins()");
		this.wins = wins;
	}

	public int getPoints() {
		logger.info("Entered getPoints()");
		return points;
	}

	public void setPoints(int points) {
		logger.info("Entered setPoints()");
		this.points = points;
	}

	public float getTeamStrength() {
		logger.info("Entered getTeamStrength()");
		List<Player> players = this.getPlayers();
		float teamStrength = 0;
		if (players == null || players.size() == 0) {
			logger.warn("No players found");
			return 0;
		}
		for (Player player : players) {
			teamStrength += player.getPlayerStrength();
		}
		return teamStrength;
	}

	public boolean isUserTeam() {
		logger.info("Entered isUserTeam()");
		return isUserTeam;
	}

	public void setUserTeam(boolean isUserTeam) {
		logger.info("Entered setUserTeam()");
		this.isUserTeam = isUserTeam;
	}

	public boolean isTeamNameValid(String teamName, ILeague league) {
		logger.info("Entered isTeamNameValid()");
		boolean isTeamNameValid = true;
		List<IConference> cconferenceList = league.getConferences();
		for (IConference conference : cconferenceList) {
			List<Division> divisionList = conference.getDivisions();
			for (Division division : divisionList) {
				List<Team> teamList = division.getTeams();
				for (Team team : teamList) {
					if (team.getTeamName() != null && team.getTeamName().equalsIgnoreCase(teamName)) {
						logger.info(teamName+" team is valid");
						isTeamNameValid = false;
						return isTeamNameValid;
					}
				}
			}
		}
		return isTeamNameValid;
	}

	public boolean teamExistsInDivision(String teamName, Division divisionName) {
		logger.info("Entered teamExistsInDivision()");
		boolean teamExists = false;
		List<Team> teamList = divisionName.getTeams();
		if (teamList == null) {
			logger.warn("No team found with name "+teamName);
			return teamExists;
		}
		for (Team team : teamList) {
			if (team.getTeamName().equalsIgnoreCase(teamName)) {
				logger.info("Team "+teamName+" exists in "+divisionName.getDivisionName());
				teamExists = true;
				return teamExists;
			}
		}
		return teamExists;
	}

	public Team getTeamFromDivision(String teamName, Division division) {
		logger.info("Entered getTeamFromDivision()");
		List<Team> teamList = division.getTeams();
		Team teamInDivision = null;
		for (Team team : teamList) {
			if (team.getTeamName().equalsIgnoreCase(teamName)) {
				logger.info("Team "+team.getTeamName()+" extracted from "+division.getDivisionName());
				teamInDivision = team;
				return team;
			}
		}
		return teamInDivision;
	}

	public League loadLeagueWithTeamName(String teamName, ITeamDb teamDb) {
		logger.info("Entered loadLeagueWithTeamName()");
		return teamDb.loadLeagueWithTeamName(teamName);
	}

	public void addGeneralMangerToTeam(Team team, IGeneralManager gmObj, ILeague league) {
		logger.info("Entered addGeneralMangerToTeam()");
		team.setGeneralManager(gmObj);
		List<IGeneralManager> generalManagers = league.getGeneralManagers();
		for (IGeneralManager gm : generalManagers) {
			if (gm.getName() != null && gm.getPersonality() != null && gm.getName().equalsIgnoreCase(gmObj.getName())
					&& gm.getPersonality().equalsIgnoreCase(gmObj.getPersonality())) {
				logger.info("General manager "+gm.getName()+" assigned to "+team.getTeamName()+" team");
				generalManagers.remove(gm);
				break;
			}
		}
	}

	public void addCoachToTeam(Team team, String coachName, ILeague league) {
		logger.info("Entered addCoachToTeam()");
		Coach coach = new Coach();
		coach.setName(coachName);
		team.setHeadCoach(coach);
		List<ICoach> coaches = league.getCoaches();
		for (ICoach ch : coaches) {
			if (ch.getName() != null && ch.getName().equalsIgnoreCase(coach.getName())) {
				logger.info("Coach "+ch.getName()+" assigned to "+team.getTeamName()+" team");
				coaches.remove(ch);
				break;
			}
		}
	}

	@Override
	public int compareTo(Team team) {
		logger.info("Entered compareTo()");
		if (this.getPoints() > team.getPoints()) {
			return 1;
		} else {
			return -1;
		}
	}

	public List<Team> orderTeamsInLeagueStandings(ILeague league) {
		logger.info("Entered orderTeamsInLeagueStandings()");
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
		logger.info("Entered sortTeam()");
		Collections.sort(teamsOrderedInReverse);
	}

	public List<Boolean> getTradedPicks() {
		logger.info("Entered getTradedPicks()");
		return tradedPicks;
	}

	public void setTradedPicks(int index) {
		logger.info("Entered setTradedPicks()");
		tradedPicks.set(index, true);
	}

	@Override
	public String toString() {
		logger.info("Entered toString()");
		return "Team [teamName=" + teamName + ", generalManager=" + generalManager + ", headCoach=" + headCoach
				+ ", players=" + players + "]";
	}
}
