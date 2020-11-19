package group11.Hockey.models;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.db.Team.ITeamDb;
import group11.Hockey.db.Team.TeamDbMock;
import org.junit.Assert;
import org.junit.BeforeClass;

public class TeamTest {

	private static LeagueTest leagueTest;
	private static League league;
	private static Team team;
	private static Player player1;
	private static Player player2;

	@BeforeClass
	public static void init() {
		List<Player> listOfPlayers = new ArrayList<Player>();
		leagueTest = new LeagueTest();
		league = leagueTest.populateLeagueObject();

		player1 = new Player(10, 10, 10, 10, "Player1", "forward", true, false, 20);
		player2 = new Player(15, 15, 15, 15, "Player2", "goalie", false, false, 20);
		listOfPlayers.add(player1);
		listOfPlayers.add(player2);
		team = new Team("Vancouver Canucks", "John", null, listOfPlayers);

	}

	@Test
	public void getTeamNameTest() {
		Assert.assertEquals("Vancouver Canucks", team.getTeamName());
	}

	@Test
	public void getGeneralManagerTest() {
//		Assert.assertEquals("Kevin", team.getGeneralManager());
	}

	@Test
	public void getHeadCoachTest() {
		Assert.assertEquals(null, team.getHeadCoach());
	}

	// getPlayers
	@Test
	public void getPlayersTest() {

		Assert.assertEquals("playerName=" + player1.getPlayerName() + ", position=" + player1.getPosition()
				+ ", captain=" + player1.getCaptain(), team.getPlayers().get(0).toString());

		Assert.assertEquals("playerName=" + player2.getPlayerName() + ", position=" + player2.getPosition()
				+ ", captain=" + player2.getCaptain(), team.getPlayers().get(1).toString());
		Assert.assertTrue(team.getPlayers().size() == 2);
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
		ITeamDb teamDb = new TeamDbMock();
		League fetcheData = team.loadLeagueWithTeamName(teamName, teamDb);

		Assert.assertTrue(
				fetcheData.getConferences().get(0).getConferenceName().equalsIgnoreCase("Westeren Conference"));
		Assert.assertTrue(fetcheData.getConferences().get(0).getDivisions().get(0).getDivisionName()
				.equalsIgnoreCase("Atlantic Division"));
		Assert.assertTrue(fetcheData.getConferences().get(0).getDivisions().get(0).getTeams().get(0)
				.getTeamName().equalsIgnoreCase("Toronto Maples"));
	}

	@Test
	public void getTeamStrengthTest() {
		float teamStrength = team.getTeamStrength();
		Assert.assertEquals(teamStrength, 55.0, 55.0);
	}

	@Test
	public void addGeneralMangerToTeamTest() {
		team.addGeneralMangerToTeam(team, "Kevin", league);
		Assert.assertEquals("Kevin", team.getGeneralManager());
		Assert.assertTrue(league.getGeneralManagers().size() == 0);
	}

	@Test
	public void addCoachToTeamTest() {
		team.addCoachToTeam(team, "C1", league);
		Assert.assertEquals("C1", team.getHeadCoach().getName());
		Assert.assertTrue(league.getCoaches().size() == 0);
	}

}
