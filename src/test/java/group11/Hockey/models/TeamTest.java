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

	@Test
	public void getTeamNameTest() {
		Team team = new Team("Vancouver Canucks", null, null, null);
		Assert.assertEquals("Vancouver Canucks", team.getTeamName());
	}

	@Test
	public void getGeneralManagerTest() {
		Team team = new Team("Vancouver Canucks", "John", null, null);
		Assert.assertEquals("John", team.getGeneralManager());
	}

	@Test
	public void getHeadCoachTest() {
		Team team = new Team("Vancouver Canucks", "John", "Peter", null);
		Assert.assertEquals("Peter", team.getHeadCoach());
	}

	// getPlayers
	@Test
	public void getPlayersTest() {
		List<Player> listOfPlayers = new ArrayList<Player>();
		Player player1 = new Player("Player1", "forward", true);
		Player player2 = new Player("Player2", "goalie", false);
		listOfPlayers.add(player1);
		listOfPlayers.add(player2);
		Team team = new Team("Vancouver Canucks", "John", "Peter", listOfPlayers);
		Assert.assertEquals("playerName=" + player1.getPlayerName() + ", position=" + player1.getPosition()
				+ ", captain=" + player1.getCaptain(), team.getPlayers().get(0).toString());

		Assert.assertEquals("playerName=" + player2.getPlayerName() + ", position=" + player2.getPosition()
				+ ", captain=" + player2.getCaptain(), team.getPlayers().get(1).toString());
	}

	@Test
	public void getPlayersListSizeTest() {
		List<Player> listOfPlayers = new ArrayList<Player>();
		Player player1 = new Player("Player1", "forward", true);
		Player player2 = new Player("Player2", "goalie", false);
		listOfPlayers.add(player1);
		listOfPlayers.add(player2);
		Team team = new Team("Vancouver Canucks", "John", "Peter", listOfPlayers);

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
	public void isTeamManagerNameValidTest() {
		String managerName = "John";
		boolean isMangerNameValid = true;
		isMangerNameValid = team.isTeamManagerNameValid(managerName, league);
		Assert.assertFalse(isMangerNameValid);

	}

	@Test
	public void isHeadCoachNameValidTest() {
		String coachName = "Peter";
		boolean isCoachNameValid = true;
		isCoachNameValid = team.isHeadCoachNameValid(coachName, league);
		Assert.assertFalse(isCoachNameValid);

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
