package group11.Hockey.BusinessLogic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.LeagueModelMock;

public class DraftPlayerTest {

	ILeague league;

	@Before
	public void loadLeague() {
		LeagueModelMock leagueMock = new LeagueModelMock();
		league = leagueMock.getLeagueInfo();
		leagueMock.insertDataForDrafing();

	}

	@Test
	public void draftPlayerTest() {
		DraftPlayer draftPlayer = new DraftPlayer(league);
		draftPlayer.draftPlayer();
		Assert.assertTrue(league.getConferences().get(0).getDivisions().get(0).getTeams().size() == 18);
		Assert.assertTrue(
				league.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getPlayers().size() == 10);
	}

	@Test
	public void selectTeamFromRegularSeasonStandinfoTest() {
		DraftPlayer draftPlayer = new DraftPlayer(league);
		draftPlayer
				.selectTeamFromRegularSeasonStandinfo(league.getConferences().get(0).getDivisions().get(0).getTeams());
		Assert.assertTrue(league.getConferences().get(0).getDivisions().get(0).getTeams().size() == 17);
	}
}