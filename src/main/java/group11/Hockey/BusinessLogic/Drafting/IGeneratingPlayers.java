package group11.Hockey.BusinessLogic.Drafting;

import java.util.List;

import group11.Hockey.BusinessLogic.models.IPlayer;
/**
 * 
 * @author jatinpartaprana
 *
 */
public interface IGeneratingPlayers {

	public List<IPlayer> generatePlayers(int numbersOfPlayersToGenerate);
	
	public IPlayer populatePlayer(String playerName);
	
	public void setStatForForwardPlayer(IPlayer player);
	
	public void setStatDefensePlayer(IPlayer player);
	
	public void setStatGoaliePlayer(IPlayer player);
	
	public int getNumberInRange(int minRange, int maxRange);
}
