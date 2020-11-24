package group11.Hockey.BusinessLogic.models;

import java.util.List;

public interface ITeam {
	public String getTeamName();

	public String getGeneralManager();

	public Coach getHeadCoach();

	public int getPoints();

	public int getAverageShoots();

	public void setAverageShoots(int averageShoots);

	public List<Player> getPlayers();

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
}
