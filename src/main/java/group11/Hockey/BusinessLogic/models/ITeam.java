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
}
