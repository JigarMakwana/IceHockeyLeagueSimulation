package group11.Hockey.BusinessLogic.models;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.db.Team.ITeamDb;
import group11.Hockey.db.Team.TeamDbMock;

public class TeamTest {

	private static LeagueTest leagueTest;
	private static ILeague league;
	private static Team team;
	private static Player player1;
	private static Player player2;

	@BeforeClass
	public static void init() {
		List<IPlayer> listOfPlayers = new ArrayList<>();
		leagueTest = new LeagueTest();
		league = leagueTest.populateLeagueObject();

		player1 = new Player(10, 10, 10, 10, "Player1", "forward", true, false, 20);
		player2 = new Player(15, 15, 15, 15, "Player2", "goalie", false, false, 20);
		listOfPlayers.add(player1);
		listOfPlayers.add(player2);
		GeneralManager gm = new GeneralManager("John", "shrewd");
		team = new Team("Vancouver Canucks", gm, null, listOfPlayers);

	}

	@Test
	public void getTeamNameTest() {
		Assert.assertEquals("Vancouver Canucks", team.getTeamName());
	}

	@Test
	public void getPlayersTest() {

		Assert.assertEquals(player1.getPlayerName(), team.getPlayers().get(0).getPlayerName());

		Assert.assertEquals(player2.getPlayerName(), team.getPlayers().get(1).getPlayerName());
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
		Assert.assertTrue(fetcheData.getConferences().get(0).getDivisions().get(0).getTeams().get(0).getTeamName()
				.equalsIgnoreCase("Toronto Maples"));
	}

	@Test
	public void getTeamStrengthTest() {
		float teamStrength = team.getTeamStrength();
		Assert.assertEquals(teamStrength, 55.0, 55.0);
	}

	@Test
	public void addGeneralMangerToTeamTest() {
		IGeneralManager generalManager = DefaultHockeyFactory.makeGeneralManager("Kevin", "normal");
		team.addGeneralMangerToTeam(team, generalManager, league);
		Assert.assertEquals("Kevin", generalManager.getName());
		Assert.assertTrue(league.getGeneralManagers().size() == 1);
	}

	@Test
	public void orderTeamsInLeagueStandingsTest() {
		DefaultHockeyFactory.makeTeam();
		Assert.assertTrue(team.getPlayers().size() == 2);
	}

	@Test
	public void addCoachToTeamTest() {
		team.addCoachToTeam(team, "C1", league);
		Assert.assertEquals("C1", team.getHeadCoach().getName());
		Assert.assertTrue(league.getCoaches().size() == 0);
	}

	@Test
	public void sortTeamTest() {
		ITeam teamm = DefaultHockeyFactory.makeTeam();
		teamm.sortTeam(league.getConferences().get(0).getDivisions().get(0).getTeams());
		Assert.assertTrue(team.getPlayers().size() == 2);
	}

}
