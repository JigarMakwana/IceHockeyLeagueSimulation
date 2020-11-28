package group11.Hockey.BusinessLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import group11.Hockey.BusinessLogic.models.Roster.RosterSize;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.League.ILeagueDb;

public class DraftPlayer extends StateMachineState implements IDraftPlayer {
	ILeague league;
	ILeagueDb leagueDb;
	IDisplay display;
	private static Logger logger = LogManager.getLogger(DraftPlayer.class);
	DraftPlayer(ILeague league, ILeagueDb leagueDb, IDisplay display) {
		this.league = league;
		this.leagueDb = leagueDb;
		this.display = display;
	}

	@Override
	public StateMachineState startState() {
		draftPlayer();
		return DefaultHockeyFactory.makeAdvanceToNextSeason(league, leagueDb, display);
	}

	@Override
	public void draftPlayer() {
		logger.info("Entered draftPlayer()");
		List<Team> draftingTeams = new ArrayList<>();
		int numbersOfPlayersToGenerate;
		int indexForGeneratedPlayers = 0;
		List<Team> teamsInReverseOrder = DefaultHockeyFactory.makeTeam().orderTeamsInLeagueStandings(league);
		List<Team> playOffTeamsInReverseOrder = league.getQualifiedTeams();
		DefaultHockeyFactory.makeTeam().sortTeam(playOffTeamsInReverseOrder);
		draftingTeams.addAll(selectTeamFromRegularSeasonStandinfo(teamsInReverseOrder));
		draftingTeams.addAll(playOffTeamsInReverseOrder);
		numbersOfPlayersToGenerate = draftingTeams.size() * 7;
		GeneratingPlayers generatingPlayers = new GeneratingPlayers();
		List<Player> generatedPlayers = generatingPlayers.generatePlayers(numbersOfPlayersToGenerate);
		Collections.sort(generatedPlayers);
		//List<Map<Team, Map<Team, List<Boolean>>>> draftTradeTracker = league.getDraftTradeTracker();
		for (int round = 1; round <= 7; round++) {
			for (Team team : draftingTeams) {
				if (generatedPlayers.get(indexForGeneratedPlayers) != null) {
					logger.info("Players drafted for team "+team.getTeamName());
					team.getPlayers().add(generatedPlayers.get(indexForGeneratedPlayers));
				}
				indexForGeneratedPlayers++;
			}
		}

		for (Team team : draftingTeams) {
			List<IPlayer> extraPlayers = teamSettler(team);
			@SuppressWarnings("unchecked")
			List<IPlayer> freeAgents = (List<IPlayer>) DefaultHockeyFactory.makeLeague().getFreeAgents();
			freeAgents.addAll(extraPlayers);
		}


	}
	
	

	public List<Team> selectTeamFromRegularSeasonStandinfo(List<Team> regularSeasonTeams) {
		logger.info("Entered selectTeamFromRegularSeasonStandinfo()");
		List<Team> teamsForRegularSeason = new ArrayList<>();
		Iterator<Team> interator = regularSeasonTeams.iterator();
		for (int i = 0; i < regularSeasonTeams.size() - 16; i++) {
			teamsForRegularSeason.add(interator.next());
			interator.remove();
		}
		return teamsForRegularSeason;
	}

	public List<IPlayer> teamSettler(Team team) {
		List<IPlayer> extraPlayers = new ArrayList<IPlayer>();
		List<Player> players = team.getPlayers();
		if (players.size() > 30) {
			IRosterSearch playerSearch = DefaultHockeyFactory.makeRosterSearch();
			List<Player> forwardPlayer = playerSearch.getForwardList(players);
			List<Player> defensePlayers = playerSearch.getDefenseList(players);
			List<Player> goaliePlayer = playerSearch.getGoalieList(players);
			if (forwardPlayer.size() > RosterSize.ACTIVE_FORWARD_SIZE.getNumVal()) {
				int extraForwardPlayers = forwardPlayer.size() - RosterSize.ACTIVE_FORWARD_SIZE.getNumVal();
				populateExtraPlayerList(extraPlayers, forwardPlayer, extraForwardPlayers);
			}
			if (defensePlayers.size() > RosterSize.ACTIVE_DEFENSE_SIE.getNumVal()) {
				int extraDefensePlayers = defensePlayers.size() - RosterSize.ACTIVE_DEFENSE_SIE.getNumVal();
				populateExtraPlayerList(extraPlayers, defensePlayers, extraDefensePlayers);
			}
			if (goaliePlayer.size() > RosterSize.ACTIVE_GOALIE_SIZE.getNumVal()) {
				int extraGoalies = goaliePlayer.size() - RosterSize.ACTIVE_GOALIE_SIZE.getNumVal();
				populateExtraPlayerList(extraPlayers, goaliePlayer, extraGoalies);
			}
			for(int j=0; j< players.size(); j++) {
				for(int i=0; i< extraPlayers.size(); i++) {
				if(players.get(j).toString().equalsIgnoreCase(extraPlayers.get(i).toString())) {
					players.remove(j);
				}
			}
		}
		}

			return extraPlayers;

	}

	private void populateExtraPlayerList(List<IPlayer> extraPlayers, List<Player> playerList,
			int extraPlayersCount) {
		for(int i=0; i< extraPlayersCount; i++) {
			extraPlayers.add(playerList.get(i));
		}
	}
}