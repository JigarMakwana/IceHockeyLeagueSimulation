package group11.Hockey.models;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

public class TeamTest {
	
	public static League league;
	
	@BeforeClass
	public static void loadDataInLeague() {
		
		
		List<Team> teamsList = new ArrayList<Team>();
		Team team = new Team("Vancouver Canucks", "John", "Peter", null);
		teamsList.add(team);
		
		List<Division> divisionsList = new ArrayList<Division>();
		Division atlanticDivision = new Division("Atlantic Division", teamsList);
		divisionsList.add(atlanticDivision);
		List<Conference> conferenceList = new ArrayList<Conference>();
		Conference conference = new Conference("Westeren Conference", divisionsList);
		conferenceList.add(conference);
		// add free agents
		league = new League("DHL", conferenceList, null);
		
	}
	
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
	
	public boolean validateTeamMethodField(League league, String methodName, String value) {
		boolean isTeamDetailsValid = true;
		List<Conference> cconferenceList = league.getConferences();
		for (Conference conference : cconferenceList) {
			List<Division> divisionList = conference.getDivisions();
			for (Division division : divisionList) {
				List<Team> teamList = division.getTeams();
				for (Team team : teamList) {

					Method m;
					try {
						m = team.getClass().getMethod(methodName);
						String fetchedValue = (String) m.invoke(team);
						if (fetchedValue.equalsIgnoreCase(value)) {
							isTeamDetailsValid = false;
							return isTeamDetailsValid;
						}
					} catch (Exception e) {
						System.out.println("Exception occured while validating the new team values with imported JSON");
					}
				}
			}
		}

		return isTeamDetailsValid;

	}
	
	@Test
	public void isTeamNameValidTest() {
		league = this.league;
		String teamName = "Vancouver Canucks";
		boolean isTeamNameValid = true;
		List<Conference> cconferenceList  = league.getConferences();
		Team team = new Team();
		isTeamNameValid = team.validateTeamMethod(league, "getTeamName", "Vancouver Canucks");
//		for(Conference conference : cconferenceList) {
//			List<Division> divisionList = conference.getDivisions();
//			for(Division division: divisionList) {
//				List<Team> teamList = division.getTeams();
//				for(Team team: teamList) {
//					if(team.getTeamName().equalsIgnoreCase(teamName)) {
//						isTeamNameValid = false;
//					}
//				}
//			}
//		}
		Assert.assertFalse(isTeamNameValid);
		
	}

}
