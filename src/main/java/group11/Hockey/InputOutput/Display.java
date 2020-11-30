package group11.Hockey.InputOutput;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.models.ICoach;
import group11.Hockey.BusinessLogic.models.IGeneralManager;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Player;
/**
 * 
 * @author Jigar and Jatin Partap Rana
 *
 */
public class Display implements IDisplay {

	private static Display displayInstance = null;
	private static Logger logger = LogManager.getLogger(Display.class);

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
		logger.info(message);
	}

	@Override
	public void displayListOfGeneralMangers(ILeague league) {
		int count = 1;
		logger.info("Available General Manger List: ");
		List<IGeneralManager> generalManagers = league.getGeneralManagers();
		for (IGeneralManager generalManger : generalManagers) {
			logger.info(count + ") General Manager Name: " + generalManger.getName());
			count++;
		}

	}

	@Override
	public void displayListOfCoaches(ILeague league) {
		List<ICoach> coaches = league.getCoaches();
		logger.info("Available Coaches List: ");
		for (ICoach coach : coaches) {
			logger.info("******Coach Details******");
			logger.info("Coach Name: " + coach.getName());
			logger.info("Skating :" + coach.getSkating());
			logger.info("Shooting :" + coach.getShooting());
			logger.info("Checking :" + coach.getChecking());
			logger.info("Saving :" + coach.getSaving());
		}
	}

	@Override
	public void displayListOfPLayers(ILeague league) {
		int count = 1;
		@SuppressWarnings("unchecked")
		List<Player> freeAgents = (List<Player>) league.getFreeAgents();
		logger.info("******Select Players for Team******: ");
		logger.info("******List of free agents******: ");
		for (Player freeAgent : freeAgents) {
			logger.info("Press " + count + "for free agent: " + freeAgent.getPlayerName());
			logger.info("******Free agent Skill Details******");
			logger.info("Position :" + freeAgent.getPosition());
			logger.info("Age :" + freeAgent.getAge());
			logger.info("Skating :" + freeAgent.getSkating());
			logger.info("Shooting :" + freeAgent.getShooting());
			logger.info("Checking :" + freeAgent.getChecking());
			logger.info("Saving :" + freeAgent.getSaving());
			count++;
		}

	}

	/**
	 * @author  Jigar Makwana B00842568
	 */

	@Override
	public  void displayPlayers(List<IPlayer> playersList) {
		int length = playersList.size();
		logger.info("Player Name ----- Position ----- Strength");
		for (int i = 0; i <= length - 1; i++)
		{
			logger.info(playersList.get(i).getPlayerName() + "       " +
					playersList.get(i).getPosition() + "        " +
					playersList.get(i).getPlayerStrength());
		}
	}

	/**
	 * @author  Jigar Makwana B00842568
	 */
	@Override
		public  void displayTradeStatistics(ITeam offeringTeamName, List<IPlayer> offeredPlayerList,
										ITeam requestedTeamName, List<IPlayer> requestedPlayerList) {
		logger.info("\n****** Trade Statistics ******");
		logger.info("\nTeam " + offeringTeamName.getTeamName() + " is offering the trade to " + requestedTeamName.getTeamName());
		if(null == offeredPlayerList){
			logger.info("---- Team " + offeringTeamName.getTeamName() + " wants to trade their draft pick away ----");
		} else {
			logger.info("---- Team " + offeringTeamName.getTeamName() + "'s Players Offered ----");
			this.displayPlayers(offeredPlayerList);
		}
		logger.info("---- Team " + requestedTeamName.getTeamName() + "'s Players Requested ----");
		this.displayPlayers(requestedPlayerList);
	}

	/**
	 * @author  Jigar Makwana B00842568
	 */
	@Override
	public  void displayTradeStatisticsToUser(String offeringTeamName, List<IPlayer> offeredPlayerList,
											  String requestedTeamName, List<IPlayer> requestedPlayerList) {
		logger.info("\n****** Woaha Trade Offer from AI Team ******");
		logger.info("Team " + offeringTeamName + " is offering the trade");
		logger.info("---- Team " + offeringTeamName + "'s Players Offered ----");
		this.displayPlayers(offeredPlayerList);
		logger.info("---- Your Team's Requested Players ----");
		this.displayPlayers(requestedPlayerList);
	}

	/**
	 * @author  Jigar Makwana B00842568
	 */
	@Override
	public void displayAcceptRejectOptionToUser() {
		logger.info("Press 1 to Accept the trade\nPress 0 to Reject the trade.");
	}

	/**
	 * @author  Jigar Makwana B00842568
	 */
	@Override
	public void displayListOfFreeAgents(List<IPlayer> freeAgentList) {
		logger.info("******Select Players for Team******: ");
		logger.info("******List of free agents******: ");
		for(int i=0; i<freeAgentList.size(); i++)
		{
			int playerNo = i +1;
			logger.info("Press " + playerNo + " to select this player: " + freeAgentList.get(i).getPlayerName());
			logger.info("Player Name ----- Position ----- Strength");
			logger.info(freeAgentList.get(i).getPlayerName() + "        " +
					freeAgentList.get(i).getPosition() + "         " +
					freeAgentList.get(i).getPlayerStrength());

		}
	}

	/**
	 * @author  Jigar Makwana B00842568
	 */
	@Override
	public void pickPlayer(List<IPlayer> playerList) {
		logger.info("\n**Please select the player to drop**");
		logger.info("******List of players******: ");
		for(int i=0; i<playerList.size(); i++)
		{
			int playerNo = i +1;
			logger.info("Press " + playerNo + " to select this player: " + playerList.get(i).getPlayerName());
			logger.info("Player Name ----- Position ----- Strength");
			logger.info(playerList.get(i).getPlayerName() + "        " +
					playerList.get(i).getPosition() + "         " +
					playerList.get(i).getPlayerStrength());

		}
	}

	@Override
	public void printTeamDetails(String leagueName, String conferenceName, String divisionName, String teamName,
			String managerName, ICoach coach) {
		showMessageOnConsole("**Team details:");
		showMessageOnConsole("League name-> " + leagueName);
		showMessageOnConsole("-Conference name-> " + conferenceName);
		showMessageOnConsole("--Division name-> " + divisionName);
		showMessageOnConsole("---Team name-> " + teamName);
		showMessageOnConsole("---GeneralManager-> " + managerName);
	}
}
