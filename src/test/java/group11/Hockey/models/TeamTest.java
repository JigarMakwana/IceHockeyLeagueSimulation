package group11.Hockey.models;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import group11.Hockey.db.Team.ITeamDb;
import group11.Hockey.db.Team.TeamDbMock;
import org.junit.Assert;


public class TeamTest {

	LeagueTest leagueTest = new LeagueTest();
	public League league = leagueTest.populateLeagueObject();
	Team team = new Team();
	Team teamWithParams = new Team("Vancouver Canucks", "John", null, null);

	@Test
	public void TeamDeafultConstructorTest() {
		Assert.assertNull(team.getTeamName());
		Assert.assertNull(team.getGeneralManager());
		Assert.assertNull(team.getHeadCoach());
		Assert.assertNull(team.getPlayers());
	}
	
	@Test
	public void TeamParameterisedConstructorTest() {
		Assert.assertEquals("Vancouver Canucks", teamWithParams.getTeamName());
		Assert.assertTrue(teamWithParams.getPlayers() == null);
		Assert.assertEquals("John", teamWithParams.getGeneralManager());
		Assert.assertEquals(null, teamWithParams.getHeadCoach());
	}
	
	@Test
	public void getTeamNameTest() {
		Assert.assertEquals("Vancouver Canucks", teamWithParams.getTeamName());
	}

	@Test
	public void getGeneralManagerTest() {
		Assert.assertEquals("John", teamWithParams.getGeneralManager());
	}

	@Test
	public void getHeadCoachTest() {
		Assert.assertEquals(null, teamWithParams.getHeadCoach());
	}

	// getPlayers
	@Test
	public void getPlayersTest() {
		List<Player> listOfPlayers = new ArrayList<Player>();
		Player player1 = new Player(0, 0, 0, 0, "Player1", "forward", true, false, 0);
		Player player2 = new Player(0, 0, 0, 0, "Player2", "goalie", false,false, 0);
		listOfPlayers.add(player1);
		listOfPlayers.add(player2);
		Team team = new Team("Vancouver Canucks", "John", null, listOfPlayers);
		Assert.assertEquals("playerName=" + player1.getPlayerName() + ", position=" + player1.getPosition()
				+ ", captain=" + player1.getCaptain(), team.getPlayers().get(0).toString());

		Assert.assertEquals("playerName=" + player2.getPlayerName() + ", position=" + player2.getPosition()
				+ ", captain=" + player2.getCaptain(), team.getPlayers().get(1).toString());
		Assert.assertTrue(team.getPlayers().size() == 2);
	}

	public ITeamDb createTeamDbMockObject() {
		ITeamDb teamDb = new TeamDbMock();
		return teamDb;
	}

	@Test
	public void isTeamNameValidTest() {
		String teamName = "Vancouver Canucks";
		boolean isTeamNameValid = true;
		isTeamNameValid = team.isTeamNameValid(teamName, league);
		Assert.assertFalse(isTeamNameValid);
	}


	@Test
	public void loadTeamWithTeamNameTest() {
		String teamName = "Toronto Maples";
		ITeamDb teamDb = createTeamDbMockObject();
		List<League> fetcheData = team.loadTeamWithTeamName(teamName, teamDb);
		Assert.assertTrue(fetcheData.size() == 1);
		Assert.assertTrue(
				fetcheData.get(0).getConferences().get(0).getConferenceName().equalsIgnoreCase("Westeren Conference"));
		Assert.assertTrue(fetcheData.get(0).getConferences().get(0).getDivisions().get(0).getDivisionName()
				.equalsIgnoreCase("Atlantic Division"));
		Assert.assertTrue(fetcheData.get(0).getConferences().get(0).getDivisions().get(0).getTeams().get(0)
				.getTeamName().equalsIgnoreCase("Toronto Maples"));
	}

}
