package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.*;
import group11.Hockey.BusinessLogic.Trading.Interfaces.IAITrading;
import group11.Hockey.BusinessLogic.models.*;
import group11.Hockey.BusinessLogic.models.Roster.RosterSize;
import group11.Hockey.InputOutput.*;
import group11.Hockey.db.League.ILeagueDb;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class contain all the logic to trade
 * 1. Between AI Teams and
 * 2. Between AI to User Teams
 * @author  Jigar Makwana B00842568
 */

public class AITrading extends StateMachineState {
	private ILeague leagueObj;
	private ITrading tradingConfig;
	private PlayerTradeOperations playerMiscellaneous;
	private ICommandLineInput commandLineInput;
	private IDisplay display = DefaultHockeyFactory.makeDisplay();
	private IValidations validation;
	private ILeagueDb leagueDb;

	public AITrading(ILeague leagueObj) {
		this.leagueObj = leagueObj;
		IGameplayConfig gameConfig = this.leagueObj.getGamePlayConfig();
		this.tradingConfig = gameConfig.getTrading();
		this.playerMiscellaneous = new PlayerTradeOperations(this.tradingConfig);
	}

	public AITrading(ILeague leagueObj, ILeagueDb leagueDb) {
		this.leagueObj = leagueObj;
		IGameplayConfig gameConfig = this.leagueObj.getGamePlayConfig();
		this.tradingConfig = gameConfig.getTrading();
		this.playerMiscellaneous = new PlayerTradeOperations(this.tradingConfig);
		this.leagueDb = leagueDb;
	}

	public AITrading(ILeague leagueObj, ICommandLineInput commandLineInput, IDisplay display, IValidations validation, ILeagueDb leagueDb) {
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
		this.generateTradeOffers();
		return new AgePlayer(leagueObj, 1,leagueDb);
	}

	public PlayerTradeOperations getPlayerMiscellaneous() {
		return playerMiscellaneous;
	}

	public void generateTradeOffers() {
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
						settleTeamAfterTrade(eligibleTeamList.get(i));
						settleTeamAfterTrade(tradeTeam.getFirst());
					}
				}
			}
			eligibleTeamList.remove(0);
			teamLength = eligibleTeamList.size();
		}
	}

	public List<Team> determineTradeEligibleTeams() {
		int lossPointCutOff = tradingConfig.getLossPoint();
		boolean isAITeam;
		List<Team> eligibleTeamList = new ArrayList<Team>();
		List<Conference> conferenceList = leagueObj.getConferences();
		for (Conference conference : conferenceList) {
			List<Division> divisionList = conference.getDivisions();
			for (Division division : divisionList) {
				List<Team> teamList = division.getTeams();
				for (Team team : teamList) {
					if ((team.getLosses() >= lossPointCutOff)) {
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
		Random rand = new Random();
		float randomTradeOfferChance = rand.nextFloat();
		return randomTradeOfferChance;
	}

	public void resolveAIToAITrade(Team team1, List<Player> offeredPlayerList, Team team2,
			List<Player> requestedPlayerList) {
		Float playerStrength1 = playerMiscellaneous.playersStrengthSum(offeredPlayerList);
		Float playerStrength2 = playerMiscellaneous.playersStrengthSum(requestedPlayerList);
		float randomAcceptanceChance = this.generateRandomNumber();
		if (randomAcceptanceChance < tradingConfig.getRandomAcceptanceChance()) {
			acceptTrade(team1, offeredPlayerList, team2, requestedPlayerList);
		} else if (playerStrength1 > playerStrength2) {
			acceptTrade(team1, offeredPlayerList, team2, requestedPlayerList);
		} else {
			rejectTrade(team1);
		}
	}

	public void resolveAIToUserTrade(Team team1, List<Player> offeredPlayerList, Team team2,
			List<Player> requestedPlayerList) {
//		display.displayTradeStatisticsToUser(team1, offeredPlayerList, team2, requestedPlayerList);
		display.displayAcceptRejectOptionToUser();
		IUserInputCheck userInputCheck = DefaultHockeyFactory.makeUserInputCheck(commandLineInput, validation, display);

		int userInput = userInputCheck.validateUserTradeInput();
		if (userInput == 1) {
			acceptTrade(team1, offeredPlayerList, team2, requestedPlayerList);
		} else if (userInput == 0) {
			rejectTrade(team1);
		}
	}

	public void rejectTrade(Team team) {
		display.showMessageOnConsole("Trade is declined.");
		resetLossPoints(team);
	}

	public void acceptTrade(Team team1, List<Player> offeredPlayerList, Team team2, List<Player> requestedPlayerList) {
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
		team.setLosses(0);
	}

	public void settleTeamAfterTrade(Team team) {
		display.showMessageOnConsole("\nSettling Team " + team.getTeamName() + "'s size after trade negotiation...");
		IConstantSupplier constants = new ConstantSupplier(20, 10, 16, 10, 4);
		SettleTeamRoster settleObj = new SettleTeamRoster(leagueObj, constants);
		try {
			settleObj.settleTeam(team);
			display.showMessageOnConsole("Team " + team.getTeamName() + "'s size successfully settled!");
		} catch (Exception e) {
			display.showMessageOnConsole("Teams cannot be settled after trade negotiation.");
		}
	}

}
