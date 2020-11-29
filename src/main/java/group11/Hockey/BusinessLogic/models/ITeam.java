package group11.Hockey.BusinessLogic.models;

import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;
import group11.Hockey.db.Team.ITeamDb;

import java.util.List;

public interface ITeam {

	public void setTeamName(String teamName);

	public String getTeamName();

	public IGeneralManager getGeneralManager();

	public void setGeneralManager(IGeneralManager generalManager);

	public ICoach getHeadCoach();

	public void setHeadCoach(Coach headCoach);

	public int getPoints();

	public void setPlayers(List<IPlayer> players);

	public int getAverageShoots();

	public void setAverageShoots(int averageShoots);

	public List<IPlayer> getPlayers();

	public boolean isOnPenalty();

	public void setOnPenalty(boolean isOnPenalty);

	public int getPenaltyPeriod();

	public void setPenaltyPeriod(int penaltyPeriod);

	public boolean isTeamNameValid(String teamName, ILeague league);

	public int getLosses();

	public void setLosses(int losses);

	public int getWins();

	public void setWins(int wins);

	public void setPoints(int points);

	public int getGoalsInSeason();

	public void setGoalsInSeason(int goalsInSeason);

	public int getPenaltiesInSeason();

	public void setPenaltiesInSeason(int penaltiesInSeason);

	public int getSavesInSeason();

	public void setSavesInSeason(int savesInSeason);

	public boolean isUserTeam();

	public IRoster getRoster();

	public void setRoster(IRoster roster);

	public List<Boolean> getTradedPicks();

	public void updateTradedPicks(int index);

	public float getTeamStrength();

	public void setUserTeam(boolean isUserTeam);

	public boolean teamExistsInDivision(String teamName, Division divisionName);

	public ITeam getTeamFromDivision(String teamName, Division division);

	public League loadLeagueWithTeamName(String teamName, ITeamDb teamDb);

	public void addGeneralMangerToTeam(ITeam team, IGeneralManager gmObj, ILeague league);

	public void addCoachToTeam(ITeam team, String coachName, ILeague league);

	public List<Team> orderTeamsInLeagueStandings(ILeague league);

	public List<Team> sortTeam(List<ITeam> teamsOrderedInReverse);

	public String toString();
}
