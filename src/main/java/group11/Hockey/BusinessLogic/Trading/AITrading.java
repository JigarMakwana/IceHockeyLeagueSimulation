package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.*;
import group11.Hockey.BusinessLogic.models.*;
import group11.Hockey.InputOutput.*;
import group11.Hockey.db.League.ILeagueDb;

import java.util.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * This class contain all the logic to trade 1. Between AI Teams and 2. Between
 * AI to User Teams
 * 
 * @author Jigar Makwana B00842568
 */

public class AITrading extends StateMachineState {
	private ILeague leagueObj;
	private ITrading tradingConfig;
	private PlayerTradeOperations playerMiscellaneous;
	private ICommandLineInput commandLineInput;
	private IDisplay display;
	private IValidations validation;
	private ILeagueDb leagueDb;
	private static Logger logger = LogManager.getLogger(AITrading.class);

	public AITrading(ILeague leagueObj) {
		this.leagueObj = leagueObj;
		IGameplayConfig gameConfig = this.leagueObj.getGamePlayConfig();
		this.tradingConfig = gameConfig.getTrading();
		this.playerMiscellaneous = new PlayerTradeOperations(this.tradingConfig);
	}

	public AITrading(ILeague leagueObj, ILeagueDb leagueDb, IDisplay display) {
		this.leagueObj = leagueObj;
		IGameplayConfig gameConfig = this.leagueObj.getGamePlayConfig();
		this.display = display;
		this.tradingConfig = gameConfig.getTrading();
		this.playerMiscellaneous = new PlayerTradeOperations(this.tradingConfig);
		this.leagueDb = leagueDb;
	}

	public AITrading(ILeague leagueObj, ICommandLineInput commandLineInput, IDisplay display, IValidations validation,
			ILeagueDb leagueDb) {
		this.leagueObj = leagueObj;
		this.commandLineInput = commandLineInput;
		this.display = display;
		this.validation = validation;
		this.leagueDb = leagueDb;
		IGameplayConfig gameConfig = this.leagueObj.getGamePlayConfig();
		this.tradingConfig = gameConfig.getTrading();
		this.playerMiscellaneous = new PlayerTradeOperations(this.tradingConfig);
	}

	@Override
	public StateMachineState startState() {
		logger.info("Entered startState()");
		this.generateTradeOffers();
		return new AgePlayer(leagueObj, 1, leagueDb, display);
	}

	public PlayerTradeOperations getPlayerMiscellaneous() {
		logger.info("Entered getPlayerMiscellaneous()");
		return playerMiscellaneous;
	}

	public void generateTradeOffers() {
		logger.info("Entered generateTradeOffers()");
		List<Team> eligibleTeamList = determineTradeEligibleTeams();
		int teamLength = eligibleTeamList.size();
		boolean isAITeam = false;
		for (int i = 0; teamLength > 1; i = 0) {
			if (eligibleTeamList.get(i).isUserTeam()) {
				isAITeam = false;
			} else {
				isAITeam = true;
			}
			if (isAITeam) {
				float randomTradeOfferChance = this.generateRandomNumber();
				if (randomTradeOfferChance < tradingConfig.getRandomTradeOfferChance()) {
					display.showMessageOnConsole(
							"\nGenerating Trade for AI Team " + eligibleTeamList.get(i).getTeamName());
					List<Triplet<Team, List<Player>, Float>> tradingTeamsBuffer = new ArrayList<>();
					List<Player> weakestPlayerList = playerMiscellaneous.findWeakestPlayers(eligibleTeamList.get(i));
					List<Integer> playerPositionFlag = playerMiscellaneous.findPlayerPositions(weakestPlayerList);

					for (int k = 1; k < eligibleTeamList.size(); k++) {
						List<Player> strongestPlayerList = playerMiscellaneous
								.findStrongestPlayers(eligibleTeamList.get(k), playerPositionFlag);
						Float playersStrengthSum = playerMiscellaneous.playersStrengthSum(strongestPlayerList);
						Triplet<Team, List<Player>, Float> teamRequestEntry = Triplet.of(eligibleTeamList.get(k),
								strongestPlayerList, playersStrengthSum);
						tradingTeamsBuffer.add(teamRequestEntry);
					}
					if (tradingTeamsBuffer.size() > 0) {
						Triplet<Team, List<Player>, Float> tradeTeam = playerMiscellaneous
								.findStrongestTradeTeam(tradingTeamsBuffer);

						if (tradeTeam.getFirst().isUserTeam()) {
							resolveAIToUserTrade(eligibleTeamList.get(i), weakestPlayerList, tradeTeam.getFirst(),
									tradeTeam.getSecond());
						} else {
//							display.displayTradeStatistics(eligibleTeamList.get(i), weakestPlayerList,
//									tradeTeam.getFirst(), tradeTeam.getSecond());
							resolveAIToAITrade(eligibleTeamList.get(i), weakestPlayerList, tradeTeam.getFirst(),
									tradeTeam.getSecond());
						}
						settleTeam(eligibleTeamList.get(i));
						settleTeam(tradeTeam.getFirst());
					}
				}
			}
			eligibleTeamList.remove(0);
			teamLength = eligibleTeamList.size();
		}
	}

	public List<Team> determineTradeEligibleTeams() {
		logger.info("Entered determineTradeEligibleTeams()");
		int lossPointCutOff = tradingConfig.getLossPoint();
		boolean isAITeam;
		List<Team> eligibleTeamList = new ArrayList<Team>();
		List<IConference> conferenceList = leagueObj.getConferences();
		for (IConference conference : conferenceList) {
			List<Division> divisionList = conference.getDivisions();
			for (Division division : divisionList) {
				List<Team> teamList = division.getTeams();
				for (Team team : teamList) {
					if ((team.getLosses() >= lossPointCutOff)) {
						logger.info(team.getTeamName()+" team is eligible for trading");
						eligibleTeamList.add(team);
					}
				}
			}
		}
		System.out.println("------- ** Teams Eligible for Trade ** -------");
		for (Team team : eligibleTeamList) {
			System.out.println(team.getTeamName());
		}
		return eligibleTeamList;
	}

	private float generateRandomNumber() {
		logger.info("Entered generateRandomNumber()");
		Random rand = new Random();
		float randomTradeOfferChance = rand.nextFloat();
		return randomTradeOfferChance;
	}

	public void resolveAIToAITrade(Team team1, List<Player> offeredPlayerList, Team team2,
			List<Player> requestedPlayerList) {
		logger.info("Entered resolveAIToAITrade()");
		Float playerStrength1 = playerMiscellaneous.playersStrengthSum(offeredPlayerList);
		Float playerStrength2 = playerMiscellaneous.playersStrengthSum(requestedPlayerList);
		float randomAcceptanceChance = this.generateRandomNumber();
		if (randomAcceptanceChance < tradingConfig.getRandomAcceptanceChance()) {
			logger.info("AI to AI Trade accepted");
			acceptTrade(team1, offeredPlayerList, team2, requestedPlayerList);
		} else if (playerStrength1 > playerStrength2) {
			logger.info("AI to AI Trade accepted");
			acceptTrade(team1, offeredPlayerList, team2, requestedPlayerList);
		} else {
			logger.info("AI to AI Trade rejected");
			rejectTrade(team1);
		}
	}

	public void resolveAIToUserTrade(Team team1, List<Player> offeredPlayerList, Team team2,
			List<Player> requestedPlayerList) {
		logger.info("Entered resolveAIToUserTrade()");
//		display.displayTradeStatisticsToUser(team1, offeredPlayerList, team2, requestedPlayerList);
		display.displayAcceptRejectOptionToUser();
		IUserInputCheck userInputCheck = DefaultHockeyFactory.makeUserInputCheck(commandLineInput, validation, display);

		int userInput = userInputCheck.validateUserTradeInput();
		if (userInput == 1) {
			logger.info("AI to User Trade accepted");
			acceptTrade(team1, offeredPlayerList, team2, requestedPlayerList);
		} else if (userInput == 0) {
			logger.info("AI to User Trade rejected");
			rejectTrade(team1);
		}
	}

	public void rejectTrade(Team team) {
		logger.info("Entered rejectTrade()");
		display.showMessageOnConsole("Trade is declined.");
		resetLossPoints(team);
	}

	public void acceptTrade(Team team1, List<Player> offeredPlayerList, Team team2, List<Player> requestedPlayerList) {
		logger.info("Entered acceptTrade()");
		List<Player> localOfferedPlayerList = new ArrayList<>();
		for (Player p : offeredPlayerList) {
			localOfferedPlayerList.add(p);
		}
		List<Player> localRequestedPlayerList = new ArrayList<>();
		for (Player p : requestedPlayerList) {
			localRequestedPlayerList.add(p);
		}

		for (Player toBeRemoved : localOfferedPlayerList) {
			team1.getPlayers().removeIf(player -> player.getPlayerName().equals(toBeRemoved.getPlayerName()));
		}
		for (Player toBeRemoved : localRequestedPlayerList) {
			team2.getPlayers().removeIf(player -> player.getPlayerName().equals(toBeRemoved.getPlayerName()));
		}
		for (Player toBeAdded : localRequestedPlayerList) {
			team1.getPlayers().add(toBeAdded);
		}
		for (Player toBeAdded : localOfferedPlayerList) {
			team2.getPlayers().add(toBeAdded);
		}
		resetLossPoints(team1);
		resetLossPoints(team2);
		display.showMessageOnConsole("Trade successfully accepted!");
	}

	public void resetLossPoints(Team team) {
		logger.info("Entered resetLossPoints()");
		team.setLosses(0);
	}

	public void settleTeam(Team team) {
		logger.info("Entered settleTeam()");
		display.showMessageOnConsole("\nSettling Team " + team.getTeamName() + "'s size after trade negotiation...");
		IConstantSupplier constants = new ConstantSupplier(20, 10, 16, 10, 4);
		SettleTeamRoster settleObj = new SettleTeamRoster(leagueObj, constants);
		try {
			settleObj.settleTeam(team);
			display.showMessageOnConsole("Team " + team.getTeamName() + "'s size successfully settled!");
		} catch (Exception e) {
			logger.error("Exception occured : "+e);
			display.showMessageOnConsole("Teams cannot be settled after trade negotiation.");
		}
	}

}
