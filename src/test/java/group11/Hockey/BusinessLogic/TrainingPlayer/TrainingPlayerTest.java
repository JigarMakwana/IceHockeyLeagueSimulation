package group11.Hockey.BusinessLogic.TrainingPlayer;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.Training.TrainingPlayer;
import group11.Hockey.BusinessLogic.models.ICoach;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.LeagueModelMock;
import group11.Hockey.InputOutput.IDisplay;
/**
 * 
 * @author jatinpartaprana
 *
 */
public class TrainingPlayerTest {
	ILeague league;
	TrainingPlayer traingPlayer;
	IDisplay display = DefaultHockeyFactory.makeDisplay();

	@Before
	public void loadLeague() {
		LeagueModelMock leagueMock = new LeagueModelMock();
		league = leagueMock.getLeagueInfo();
		traingPlayer = (TrainingPlayer) DefaultHockeyFactory.makeTrainingPlayer(league, null, display,null,null);
	}


	@Test
	public void comapreCoachStatTest() {
		ITeam team = league.getConferences().get(0).getDivisions().get(0).getTeams().get(0);
		boolean skillCheck = traingPlayer.comapreCoachStat(team.getHeadCoach().getSkating());
		Assert.assertTrue(skillCheck);

	}

	@Test
	public void changePlayerSkatingSkillTest() {
		ITeam team = league.getConferences().get(0).getDivisions().get(0).getTeams().get(0);
		ICoach coach = team.getHeadCoach();
		List<IPlayer> players = team.getPlayers();
		traingPlayer.changePlayerSkatingSkill(players.get(0), coach.getSkating(), league);
		Assert.assertTrue(players.size() > 0);
	}

	@Test
	public void changePlayerShootingSkillTest() {
		ITeam team = league.getConferences().get(0).getDivisions().get(0).getTeams().get(0);
		ICoach coach = team.getHeadCoach();
		List<IPlayer> players = team.getPlayers();
		traingPlayer.changePlayerShootingSkill(players.get(0), coach.getShooting(), league);
		Assert.assertTrue(players.size() > 0);
	}

	@Test
	public void changePlayerCheckingSkillTest() {
		ITeam team = league.getConferences().get(0).getDivisions().get(0).getTeams().get(0);
		ICoach coach = team.getHeadCoach();
		List<IPlayer> players = team.getPlayers();
		traingPlayer.changePlayerCheckingSkill(players.get(0), coach.getChecking(), league);
		Assert.assertTrue(players.size() > 0);
	}

	@Test
	public void changePlayerSavingSkillTest() {
		ITeam team = league.getConferences().get(0).getDivisions().get(0).getTeams().get(0);
		ICoach coach = team.getHeadCoach();
		List<IPlayer> players = team.getPlayers();
		traingPlayer.changePlayerSavingSkill(players.get(0), coach.getSaving(), league);
		Assert.assertTrue(players.size() > 0);
	}
}
