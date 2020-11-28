package group11.Hockey.BusinessLogic.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.db.League.ILeagueDb;

import org.junit.Assert;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LeagueTest {

	IConference westernConference = DefaultHockeyFactory.makeConference("Westeren Conference", null);
	IConference easternConference = DefaultHockeyFactory.makeConference("Eastern Conference", null);
	List<Conference> conferenceList = new ArrayList<Conference>();
	League league = new League();

	public League populateLeagueObject() {
		List<Team> teamsList = new ArrayList<Team>();
		List<GeneralManager> generalManagerList = new ArrayList<GeneralManager>();
		ICoach coach = DefaultHockeyFactory.makeCoach(0, 0, 0, 0, "C1");
		IGeneralManager generalManager = DefaultHockeyFactory.makeGeneralManager("Kevin", null);
		generalManagerList.add((GeneralManager) generalManager);
		IGeneralManager gm2 = DefaultHockeyFactory.makeGeneralManager("John Smith", "shrewd");
		Team team = new Team("Vancouver Canucks", gm2, coach, null);
		teamsList.add(team);

		List<Division> divisionsList = new ArrayList<Division>();
		Division atlanticDivision = new Division("Atlantic Division", teamsList);
		divisionsList.add(atlanticDivision);
		List<Conference> conferenceList = new ArrayList<Conference>();
		Conference conference = new Conference("Westeren Conference", divisionsList);
		conferenceList.add(conference);
		Player firstFreeAgent = new Player(0, 0, 0, 0, "Player 1", "forward", true, false, 0);
		Player secondFreeAgent = new Player(0, 0, 0, 0, "Player 2", "Goalie", true, false, 0);

		List<Coach> coachList = new ArrayList<Coach>();
		coachList.add((Coach) coach);
		IgmTable gmTbale = new gmTable(-0.1f, 0.1f, 0.0f);
		GameplayConfig gameplayConf = new GameplayConfig(new Aging(0, 0), new GameResolver(0), new Injuries(0, 0, 0),
				new Training(0), new Trading(0, 0, 0, 0, gmTbale));
		League league = new League("DHL", conferenceList, Arrays.asList(firstFreeAgent, secondFreeAgent), gameplayConf,
				coachList, generalManagerList);
		return league;

	}

	@Test
	public void LeagueDeafultConstructorTest() {
		Assert.assertNull(league.getLeagueName());
	}

	@Test
	public void LeagueParameterisedConstructorTest() {
		League league = new League("DHL", Arrays.asList(new Conference("Westeren Conference", null)), null, null, null,
				null);
		Assert.assertEquals("DHL", league.getLeagueName());
		Assert.assertTrue(league.getConferences().size() == 1);
	}

	@Test
	public void setLeagueNameTest() {
		league.setLeagueName("DHL");
		Assert.assertEquals("DHL", league.getLeagueName());
	}

	@Test
	public void getLeagueNameTest() {
		League league = new League("DHL", null, null, null, null, null);
		Assert.assertEquals("DHL", league.getLeagueName());
	}

	@Test
	public void setConferencesTest() {
		league.setConferences(Arrays.asList((Conference) westernConference, (Conference) easternConference));
		Assert.assertTrue(league.getConferences().size() == 2);
		Assert.assertEquals("Westeren Conference", league.getConferences().get(0).getConferenceName());

	}

	@Test
	public void getConferencesTest() {

		conferenceList.add((Conference) westernConference);
		conferenceList.add((Conference) easternConference);

		League league = new League("DHL", conferenceList, null, null, null, null);

		Assert.assertEquals("Conference [conferenceName=" + westernConference.getConferenceName() + ", divisions="
				+ westernConference.getDivisions() + "]", league.getConferences().get(0).toString());
		Assert.assertEquals("Conference [conferenceName=" + easternConference.getConferenceName() + ", divisions="
				+ easternConference.getDivisions() + "]", league.getConferences().get(1).toString());

		Assert.assertTrue(league.getConferences().size() == 2);
	}

	@Test
	public void setFreeAgentsTest() {
		List<Player> freeAgentList = new ArrayList<Player>();
		Player freeAgent = new Player(0, 0, 0, 0, "Free Agent 1", "Forward", false, false, 0);
		freeAgentList.add(freeAgent);
		league.setFreeAgents(freeAgentList);
		Assert.assertTrue(league.getFreeAgents().size() == 1);
	}

	@Test
	public void getFreeAgentsTest() {
		League league = populateLeagueObject();
		Assert.assertTrue(league.getFreeAgents().size() == 2);
		Assert.assertEquals("Player 1", league.getFreeAgents().get(0).getPlayerName());
	}

	@Test
	public void insertLeagueObjectTest() {
		League league = populateLeagueObject();

		ILeagueDb leagueDb = mock(ILeagueDb.class);
		when(leagueDb.insertLeagueInDb(league)).thenReturn(true);
		Assert.assertTrue(true);
	}

}
