package group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IDivision;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.LeagueModelMock;
import group11.Hockey.BusinessLogic.models.Player;

public class DefencePlayerActiveTest {

	@Test
	public void calculateAveragePlayersStrengthTest() {
		ILeague league = new LeagueModelMock().getLeagueInfo();
		IConference conf = league.getConferences().get(0);
		IDivision div = conf.getDivisions().get(0);
		ITeam team = div.getTeams().get(0);
		List<Player> playersList = team.getPlayers();

		IGameStrategy gs = DefaultHockeyFactory.makeDefencePlayerActive();
		int value = gs.calculateAveragePlayersStrength(playersList, team);
		Assert.assertEquals(10, value);
	}

	@Test
	public void playGameTest() {
		IGameStrategy gs = DefaultHockeyFactory.makeDefencePlayerActive();
		ILeague league = new LeagueModelMock().getLeagueInfo();
		IConference conf = league.getConferences().get(0);
		IDivision div = conf.getDivisions().get(0);
		ITeam team = div.getTeams().get(0);
		List<Player> playersList = team.getPlayers();
		playersList.addAll(playersList);
		gs.playGame(playersList, playersList, team, team, 2);
		int saves = team.getSavesInSeason();
		Assert.assertEquals(1, saves);
	}

}
