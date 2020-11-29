package group11.Hockey.InputOutput;

import group11.Hockey.BusinessLogic.models.*;

import java.util.List;

public interface IDisplay {

	public void showMessageOnConsole(String message);

	public void displayListOfGeneralMangers(ILeague league);

	public void displayListOfCoaches(ILeague league);

	public void displayListOfPLayers(ILeague league);

	public void displayTradeStatistics(String offeringTeamName, List<IPlayer> offeredPlayerList,
									   String requestedTeamName, List<IPlayer> requestedPlayerList);

	public void displayPlayers(List<IPlayer> playersList);

	public void displayTradeStatisticsToUser(String offeringTeamName, List<IPlayer> offeredPlayerList,
											 String requestedTeamName, List<IPlayer> requestedPlayerList);

	public void displayAcceptRejectOptionToUser();

	public void printTeamDetails(String leagueName, String conferenceName, String divisionName, String teamName,
			String managerName, ICoach coach);

	public void displayListOfFreeAgents(List<IPlayer> freeAgentList);
	public void pickPlayer(List<IPlayer> freeAgentList);
}
