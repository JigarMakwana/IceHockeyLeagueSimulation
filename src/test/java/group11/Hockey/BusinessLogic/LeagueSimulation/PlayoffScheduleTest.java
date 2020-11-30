package group11.Hockey.BusinessLogic.LeagueSimulation;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IDivision;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.ITimeLine;

public class PlayoffScheduleTest {
	
	@Test
	public void getSchedule() {
		SimulationLeagueModelMock leagueMock = new SimulationLeagueModelMock();
		ILeague league = leagueMock.getLeagueInfo();
		
		List<IConference> cconferenceList = league.getConferences();
		for (IConference conference : cconferenceList) {
			List<IDivision> divisionList = conference.getDivisions();
			for (IDivision division : divisionList) {
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
		league.setStartDate("01/10/2020");
		ITimeLine timeline=DefaultHockeyFactory.makeTimeLine();
		timeline.setStanleyDate("01/07/2021");
		league.setTimeLine(timeline);
		IScheduleStrategy playoff=DefaultHockeyFactory.makePlayoffSchedule();
		playoff.getSchedule(league, null);
		HashMap<String, HashMap<ITeam, ITeam>> playoffSchedule = league.getSchedule();
		int size=playoffSchedule.size();
		System.out.println("round1: "+size);
		Assert.assertEquals(56, size);
		
		
	}
}