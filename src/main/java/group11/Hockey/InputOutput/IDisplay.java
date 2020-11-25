package group11.Hockey.InputOutput;

import group11.Hockey.BusinessLogic.models.*;

import java.util.List;

public interface IDisplay {

	public void showMessageOnConsole(String message);

	public void displayListOfGeneralMangers(League league);

	public void displayListOfCoaches(League league);

	public void displayListOfPLayers(League league);

	public void displayTradeStatistics(String offeringTeamName, List<Player> offeredPlayerList,
									   String requestedTeamName, List<Player> requestedPlayerList);

	public void displayPlayers(List<Player> playersList);

	public void displayTradeStatisticsToUser(String offeringTeamName, List<Player> offeredPlayerList,
											 String requestedTeamName, List<Player> requestedPlayerList);

	public void displayAcceptRejectOptionToUser();

	public void printTeamDetails(String leagueName, String conferenceName, String divisionName, String teamName,
			String managerName, Coach coach);

	public void displayListOfFreeAgents(List<Player> freeAgentList);
	public void pickPlayer(List<Player> freeAgentList);
}
