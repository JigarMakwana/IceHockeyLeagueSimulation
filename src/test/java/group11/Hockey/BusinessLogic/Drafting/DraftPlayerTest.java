package group11.Hockey.BusinessLogic.Drafting;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.Drafting.IDraftPlayer;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.LeagueModelMock;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.IDisplay;

public class DraftPlayerTest {

	ILeague league;
	IDisplay display = DefaultHockeyFactory.makeDisplay();
	IDraftPlayer draftPlayer;

	@Before
	public void loadLeague() {
		LeagueModelMock leagueMock = new LeagueModelMock();
		league = leagueMock.getLeagueInfo();
		leagueMock.insertDataForDrafing();
		draftPlayer = (IDraftPlayer) DefaultHockeyFactory.makeDraftPlayer(league, null, display);

	}

	@Test
	public void draftPlayerTest() {
		draftPlayer.draftPlayer();
		Assert.assertTrue(league.getConferences().get(0).getDivisions().get(0).getTeams().size() == 18);
		Assert.assertTrue(
				league.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().size() == 10);
	}

	@Test
	public void selectTeamFromRegularSeasonStandinfoTest() {
		List<ITeam> teams = league.getConferences().get(0).getDivisions().get(0).getTeams();
		List<Team> fetchedTeam = new ArrayList<>();
		for (ITeam team : teams) {
			fetchedTeam.add((Team) team);
		}
		draftPlayer.selectTeamFromRegularSeasonStandinfo(fetchedTeam);
		Assert.assertTrue(league.getConferences().get(0).getDivisions().get(0).getTeams().size() == 18);
	}

	@Test
	public void teamSettlementTest() {
		draftPlayer.teamSettlement(league.getConferences().get(0).getDivisions().get(0).getTeams().get(0));
		Assert.assertTrue(league.getLeagueName().equalsIgnoreCase("Dalhousie Hockey League"));
	}

	public void populateExtraPlayerListTest() {
		int extraCount = 1;
		draftPlayer.populateExtraPlayerList(
				league.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers(),
				league.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers(), extraCount);
		Assert.assertTrue(league.getLeagueName().equalsIgnoreCase("Dalhousie Hockey League"));
	}

}