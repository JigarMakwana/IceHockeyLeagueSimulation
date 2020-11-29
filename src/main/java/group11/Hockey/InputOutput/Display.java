package group11.Hockey.InputOutput;

import java.util.List;

import group11.Hockey.BusinessLogic.models.*;

public class Display implements IDisplay {

	private static Display displayInstance = null;

	private Display() {

	}

	public static Display getInstance() {
		if(displayInstance == null) {
			displayInstance = new Display();
		}
		return 	displayInstance;
	}

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
		List<Coach> coaches = league.getCoaches();
		System.out.println("Available Coaches List: ");
		for (Coach coach : coaches) {
			System.out.println("******Coach Details******");
			System.out.println("Coach Name: " + coach.getName());
			System.out.println("Skating :" + coach.getSkating());
			System.out.println("Shooting :" + coach.getShooting());
			System.out.println("Checking :" + coach.getChecking());
			System.out.println("Saving :" + coach.getSaving());
		}
	}

	@Override
	public void displayListOfPLayers(League league) {
		int count = 1;
		List<Player> freeAgents = (List<Player>) league.getFreeAgents();
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
			count++;
		}

	}

	/**
	 * @author  Jigar Makwana B00842568
	 */
	@Override
	public  void displayPlayers(List<Player> playersList) {
		int length = playersList.size();
		System.out.println("Player Name ----- Position ----- Strength");
		for (int i = 0; i <= length - 1; i++)
		{
			System.out.println(playersList.get(i).getPlayerName() + "       " +
					playersList.get(i).getPosition() + "        " +
					playersList.get(i).getPlayerStrength());
		}
	}

	/**
	 * @author  Jigar Makwana B00842568
	 */
	@Override
	public  void displayTradeStatistics(Team offeringTeamName, List<Player> offeredPlayerList,
										Team requestedTeamName, List<Player> requestedPlayerList) {
		System.out.println("\n****** Trade Statistics ******");
		System.out.println("\nTeam " + offeringTeamName.getTeamName() + " is offering the trade to " + requestedTeamName.getTeamName());
		if(null == offeredPlayerList){
			System.out.println("---- Team " + offeringTeamName.getTeamName() + " wants to trade their draft pick away ----");
		} else {
			System.out.println("---- Team " + offeringTeamName.getTeamName() + "'s Players Offered ----");
			this.displayPlayers(offeredPlayerList);
		}
		System.out.println("---- Team " + requestedTeamName.getTeamName() + "'s Players Requested ----");
		this.displayPlayers(requestedPlayerList);
	}

	/**
	 * @author  Jigar Makwana B00842568
	 */
	@Override
	public  void displayTradeStatisticsToUser(String offeringTeamName, List<Player> offeredPlayerList,
											  String requestedTeamName, List<Player> requestedPlayerList) {
		System.out.println("\n****** Woaha Trade Offer from AI Team ******");
		System.out.println("Team " + offeringTeamName + " is offering the trade");
		System.out.println("---- Team " + offeringTeamName + "'s Players Offered ----");
		this.displayPlayers(offeredPlayerList);
		System.out.println("---- Your Team's Requested Players ----");
		this.displayPlayers(requestedPlayerList);
	}

	/**
	 * @author  Jigar Makwana B00842568
	 */
	@Override
	public void displayAcceptRejectOptionToUser() {
		System.out.println("Press 1 to Accept the trade\nPress 0 to Reject the trade.");
	}

	/**
	 * @author  Jigar Makwana B00842568
	 */
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

	/**
	 * @author  Jigar Makwana B00842568
	 */
	@Override
	public void pickPlayer(List<Player> playerList) {
		System.out.println("\n**Please select the player to drop**");
		System.out.println("******List of players******: ");
		for(int i=0; i<playerList.size(); i++)
		{
			int playerNo = i +1;
			System.out.println("Press " + playerNo + " to select this player: " + playerList.get(i).getPlayerName());
			System.out.println("Player Name ----- Position ----- Strength");
			System.out.println(playerList.get(i).getPlayerName() + "        " +
					playerList.get(i).getPosition() + "         " +
					playerList.get(i).getPlayerStrength());

		}
	}

	@Override
	public void printTeamDetails(String leagueName, String conferenceName, String divisionName, String teamName,
			String managerName, Coach coach) {
		showMessageOnConsole("**Team details:");
		showMessageOnConsole("League name-> " + leagueName);
		showMessageOnConsole("-Conference name-> " + conferenceName);
		showMessageOnConsole("--Division name-> " + divisionName);
		showMessageOnConsole("---Team name-> " + teamName);
		showMessageOnConsole("---GeneralManager-> " + managerName);
	}
}
