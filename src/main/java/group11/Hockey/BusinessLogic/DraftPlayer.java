package group11.Hockey.BusinessLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

public class DraftPlayer extends StateMachineState implements IDraftPlayer {
	ILeague league;
	DraftPlayer(ILeague league) {
		this.league = league;
	}

	@Override
	public StateMachineState startState() {
		draftPlayer();
		return null;
	}

	@Override
	public void draftPlayer() {
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

		for (int round = 1; round <= 7; round++) {
			for (Team team : draftingTeams) {
				if (generatedPlayers.get(indexForGeneratedPlayers) != null) {
					team.getPlayers().add(generatedPlayers.get(indexForGeneratedPlayers));
				}
				indexForGeneratedPlayers++;
			}
		}
		

	}

	public List<Team> selectTeamFromRegularSeasonStandinfo(List<Team> regularSeasonTeams) {
		List<Team> teamsForRegularSeason = new ArrayList<>();
		Iterator<Team> interator = regularSeasonTeams.iterator();
		for (int i = 0; i < regularSeasonTeams.size() - 16; i++) {
			teamsForRegularSeason.add(interator.next());
			interator.remove();
		}
		return teamsForRegularSeason;
	}
}
