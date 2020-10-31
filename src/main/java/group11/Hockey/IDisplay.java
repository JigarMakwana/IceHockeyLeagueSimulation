package group11.Hockey;

import group11.Hockey.models.League;
import group11.Hockey.models.Player;
import group11.Hockey.models.Team;

import java.util.List;

public interface IDisplay {

	public void showMessageOnConsole(String message);

	public void displayListOfGeneralMangers(League league);

	public void displayListOfCoaches(League league);

	public void displayListOfPLayers(League league);

	public void displayTradeStatistics(Team team1, List<Player> offeredPlayerList,
										Team team2, List<Player> requestedPlayerList);

	public void displayPlayers(List<Player> playersList);

	public void displayTradeStatisticsToUser(Team team1, List<Player> offeredPlayerList,
											  Team team2, List<Player> requestedPlayerList);

	public void displayAcceptRejectOptionToUser();


}
