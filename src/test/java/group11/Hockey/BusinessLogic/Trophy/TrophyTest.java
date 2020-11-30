//Author: Harry B00856244
package group11.Hockey.BusinessLogic.Trophy;

import java.util.List;

import org.junit.Test;
import org.junit.Assert;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.BusinessLogic.LeagueSimulation.SimulationLeagueModelMock;
import group11.Hockey.BusinessLogic.models.ICoach;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.ITimeLine;

public class TrophyTest {

	@Test
	public void AwardTrophyTest() {
		SimulationLeagueModelMock leagueMock = new SimulationLeagueModelMock();
		ILeague league = leagueMock.getLeagueInfo();	
		ITimeLine timeline=DefaultHockeyFactory.makeTimeLine();
		
		timeline.setStartDate("29/10/2020");	
		timeline.setStanleyDate("01/07/2021");
		league.setStartDate("29/10/2020");
		league.setTimeLine(timeline);
		ITeam team = DefaultHockeyFactory.makeTeam("Florida Panthers", null, null, null);
		ICoach coach = DefaultHockeyFactory.makeCoach("Dave", null);
		IPlayer player = DefaultHockeyFactory.makePlayer(15, 18, 12, 1, "Tom1", "forward", true, false, 25);
		List<ITeam> qualifiedTeams = league.getQualifiedTeams();
		List<ITeam> presidentTeams = league.getPresidentTeams();
		List<IPlayer> calderPlayers = league.getCalderPlayers();
		List<IPlayer> venizaPlayers = league.getVenizaPlayers();
		List<ICoach> jackAdamsCoaches = league.getJackAdamsCoaches();
		List<IPlayer> mauricePlayers = league.getMauriceRichardPlayers();
		List<IPlayer> robHawkeyPlayers = league.getRobHawkeyPlayers();
		List<ITeam> participationTeams = league.getParticipationTeams();
		
		qualifiedTeams.add(team);
		presidentTeams.add(team);
		calderPlayers.add(player);
		venizaPlayers.add(player);
		jackAdamsCoaches.add(coach);
		mauricePlayers.add(player);
		robHawkeyPlayers.add(player);
		participationTeams.add(team);

		StateMachineState trophy=DefaultHockeyFactory.makeTrophy(league, null, null);
		trophy.startState();
		List<ITeam> winner=league.getQualifiedTeams();
		Assert.assertEquals(winner.size(), 0);
	}
}
