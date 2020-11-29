package group11.Hockey.BusinessLogic.models;

import java.util.List;

public interface IPlayer {

	public String getPlayerName();

	public String getPosition();

	public boolean getCaptain();

	public boolean getIsFreeAgent();

	public float getAge();

	public int getNumberOfInjuredDays();

	public float getPlayerStrength();

	public float getSkating();

	public float getShooting();

	public float getChecking();

	public float getSaving();

	public boolean isInjured();

	void setIsFreeAgent(boolean isFreeAgent);

	void setCaptain(boolean captain);

	boolean isActive();

	public void setBirthDay(int age);

	public void setBirthMonth(int age);

	public void setBirthYear(int age);

	public int getSavesByDefenceManinSeason();

	public void setSavesByDefenceManinSeason(int savesByDefenceManinSeason);

	public int getGoalsInSeason();

	public void setGoalsInSeason(int goalsInSeason);

	public int getPenaltiesInSeason();

	public void setPenaltiesInSeason(int penaltiesInSeason);

	public int getSavesByGoalieInSeason();

	public void setSavesByGoalieInSeason(int savesInSeason);

	public void setActive(boolean active);

	public void setPlayerName(String playerName);

	public void setPosition(String position);

	public void setAge(float age);

	public void setInjured(boolean isInjured);

	public int getBirthDay();

	public int getBirthMonth();

	public int getBirthYear();

	public boolean checkInjury(ILeague league);

	public boolean isIsRetired();

	public void setIsRetired(boolean isRetired);

	public void setNumberOfInjuredDays(int numberOfInjuredDays);

	public boolean insertLeagueFreeAgents(List<Player> listOfFreeAgents);

	public boolean insertLeagueRetiredPlayers(List<Player> listOfRetiredPlayers);

	public boolean deleteLeaguePlayers();

	public String toString();

	public void decreaseInjuredDaysForPlayer(int days);

	public void increaseAge(ILeague league, int days, float statDecayChance);

	public void replacePlayerWithFreeAgent(ILeague league, List<IPlayer> playersList);

	public void removeFreeAgentsFromLeague(ILeague league, List<IPlayer> freeAgents);

	public void checkAndDecrementPlayerShootingStat(float statDecayChance);

	public void checkAndDecrementPlayerCheckingStat(float statDecayChance);

	public void checkAndDecrementPlayerSkatingStat(float statDecayChance);

	public void checkAndDecrementPlayerSavingStat(float statDecayChance);

	public void setShooting(float shooting);

	public void setChecking(float checking);

	public void setSkating(float skating);

	public void setSaving(float saving);

}
