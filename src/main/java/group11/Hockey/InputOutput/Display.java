package group11.Hockey.InputOutput;

import java.util.List;

import group11.Hockey.BusinessLogic.models.*;
import group11.Hockey.models.*;

public class Display implements IDisplay {

	@Override
	public void showMessageOnConsole(String message) {
		System.out.println(message);
	}

	@Override
	public void displayListOfGeneralMangers(League league) {
		int count = 1;
		System.out.println("Available General Manger List: ");
		List<GeneralManager> generalManagers = league.getGeneralManagers();
		for (GeneralManager generalManger : generalManagers) {
			System.out.println(count + ") General Manager Name: " + generalManger.getName());
			count++;
		}

	}

	@Override
	public void displayListOfCoaches(League league) {
		int count = 1;
		List<Coach> coaches = league.getCoaches();
		System.out.println("Available Coaches List: ");
		for (Coach coach : coaches) {
			System.out.println("Press " + count + "for coach" + coach.getName());
			System.out.println("******Coach Skill Details******");
			System.out.println("Skating :" + coach.getSkating());
			System.out.println("Shooting :" + coach.getShooting());
			System.out.println("Checking :" + coach.getChecking());
			System.out.println("Saving :" + coach.getSaving());
			count++;
		}
	}

	@Override
	public void displayListOfPLayers(League league) {
		int count = 1;
		List<Player> freeAgents = league.getFreeAgents();
		System.out.println("******Select Players for Team******: ");
		System.out.println("******List of free agents******: ");
		for (Player freeAgent : freeAgents) {
			System.out.println("Press " + count + "for free agent: " + freeAgent.getPlayerName());
			System.out.println("******Free agent Skill Details******");
			System.out.println("Position :" + freeAgent.getPosition());
			System.out.println("Age :" + freeAgent.getAge());
			System.out.println("Skating :" + freeAgent.getSkating());
			System.out.println("Shooting :" + freeAgent.getShooting());
			System.out.println("Checking :" + freeAgent.getChecking());
			System.out.println("Saving :" + freeAgent.getSaving());
		}

	}

	/**
	 * @author  Jigar Makwana B00842568
	 */

	@Override
	public  void displayPlayers(List<Player> playersList)
	{
		int length = playersList.size();
		System.out.println("Player Name ----- Position ----- Strength");
		for (int i = 0; i <= length - 1; i++)
		{
			System.out.println(playersList.get(i).getPlayerName() + "       " +
					playersList.get(i).getPosition() + "        " +
					playersList.get(i).getPlayerStrength());
		}
	}

	@Override
	public  void displayTradeStatistics(Team team1, List<Player> offeredPlayerList,
										Team team2, List<Player> requestedPlayerList)
	{
		System.out.println("\n****** Trade Statistics ******");
		System.out.println("\nTeam " + team1.getTeamName() + " is offering the trade to " + team2.getTeamName());
		System.out.println("---- Team " + team1.getTeamName() + "'s Players Offered ----");
		this.displayPlayers(offeredPlayerList);
		System.out.println("---- Team " + team2.getTeamName() + "'s Players Requested ----");
		this.displayPlayers(requestedPlayerList);
	}

	@Override
	public  void displayTradeStatisticsToUser(Team team1, List<Player> offeredPlayerList,
											  Team team2, List<Player> requestedPlayerList)
	{
		System.out.println("\n****** Woaha Trade Offer from AI Team ******");
		System.out.println("Team " + team1.getTeamName() + " is offering the trade");
		System.out.println("---- Team " + team1.getTeamName() + "'s Players Offered ----");
		this.displayPlayers(offeredPlayerList);
		System.out.println("---- Your Team's Requested Players ----");
		this.displayPlayers(requestedPlayerList);
	}

	@Override
	public void displayAcceptRejectOptionToUser() {
		System.out.println("Press 1 to Accept the trade\nPress 0 to Reject the trade.");
	}

	@Override
	public void displayListOfFreeAgents(List<Player> freeAgentList) {
		System.out.println("******Select Players for Team******: ");
		System.out.println("******List of free agents******: ");
		for(int i=0; i<freeAgentList.size(); i++)
		{
			int playerNo = i +1;
			System.out.println("Press " + playerNo + " to select this player: " + freeAgentList.get(i).getPlayerName());
			System.out.println("Player Name ----- Position ----- Strength");
			System.out.println(freeAgentList.get(i).getPlayerName() + "        " +
					freeAgentList.get(i).getPosition() + "         " +
					freeAgentList.get(i).getPlayerStrength());

		}
	}
}
