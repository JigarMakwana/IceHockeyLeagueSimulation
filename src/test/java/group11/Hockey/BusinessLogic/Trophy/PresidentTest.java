package group11.Hockey.BusinessLogic.Trophy;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.Assert;

import group11.Hockey.SimulationLeagueModelMock;
import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.Trophy.Interfaces.ITrophyObserver;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITeam;

public class PresidentTest {

	@Test
	public void AwardTrophyTest() {
		SimulationLeagueModelMock leagueMock = new SimulationLeagueModelMock();
		ILeague league = leagueMock.getLeagueInfo();
		List<IConference> cconferenceList = league.getConferences();
		for (IConference conference : cconferenceList) {
			List<Division> divisionList = conference.getDivisions();
			for (Division division : divisionList) {
				List<ITeam> teamList = division.getTeams();
				for (ITeam team : teamList) {
					Random r = new Random();
					int low = 10;
					int high = 100;
					int points = r.nextInt(high-low) + low;
					team.setPoints(points);				
				}
			}
		}	
		ITrophyObserver presidentTeam=DefaultHockeyFactory.makePresident(league);
		presidentTeam.AwardTrophy();
		List<ITeam> president=league.getPresidentTeams();
		Assert.assertEquals(president.size(), 1);
	}
}
