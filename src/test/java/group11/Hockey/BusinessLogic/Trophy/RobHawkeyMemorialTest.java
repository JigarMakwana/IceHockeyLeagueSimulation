package group11.Hockey.BusinessLogic.Trophy;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.Assert;

import group11.Hockey.SimulationLeagueModelMock;
import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.Trophy.Interfaces.ITrophyObserver;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IDivision;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;

public class RobHawkeyMemorialTest {

	@Test
	public void AwardTrophyTest() {
		SimulationLeagueModelMock leagueMock = new SimulationLeagueModelMock();
		ILeague league = leagueMock.getLeagueInfo();
		List<IConference> cconferenceList = league.getConferences();
		for (IConference conference : cconferenceList) {
			List<IDivision> divisionList = conference.getDivisions();
			for (IDivision division : divisionList) {
				List<ITeam> teamList = division.getTeams();
				for (ITeam team : teamList) {
					List<IPlayer> playerList = team.getPlayers();
					for (IPlayer player : playerList) {
						Random r = new Random();
						int low = 10;
						int high = 100;
						int points = r.nextInt(high-low) + low;
						player.setPenaltiesInSeason(points);	
					}	
				}
			}
		}	
		ITrophyObserver robPlayers=DefaultHockeyFactory.makeRobHawkeyMemorial(league);
		robPlayers.AwardTrophy();
		List<IPlayer> rob=league.getRobHawkeyPlayers();
		Assert.assertEquals(rob.size(), 1);
	}
}
