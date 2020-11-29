package group11.Hockey;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import group11.Hockey.BusinessLogic.TrainingPlayer;
import group11.Hockey.BusinessLogic.models.ICoach;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.LeagueModelMock;

public class TrainingPlayerTest {
	ILeague league;
	TrainingPlayer traingPlayer = new TrainingPlayer(league, null, null);

	@Before
	public void loadLeague() {
		LeagueModelMock leagueMock = new LeagueModelMock();
		league = leagueMock.getLeagueInfo();
	}

	@Test
	public void trainPlayerTest() {
		ITeam team = league.getConferences().get(0).getDivisions().get(0).getTeams().get(0);
		traingPlayer.trainPlayer(league);
		Assert.assertTrue(team.getPlayers().get(0).getSkating() >= 0);
		Assert.assertTrue(team.getPlayers().get(0).getShooting() >= 0);
		Assert.assertTrue(team.getPlayers().get(0).getChecking() >= 0);
		Assert.assertTrue(team.getPlayers().get(0).getSaving() >= 0);
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
	}

	@Test
	public void changePlayerShootingSkillTest() {
		ITeam team = league.getConferences().get(0).getDivisions().get(0).getTeams().get(0);
		ICoach coach = team.getHeadCoach();
		List<IPlayer> players = team.getPlayers();
		traingPlayer.changePlayerShootingSkill(players.get(0), coach.getShooting(), league);
	}

	@Test
	public void changePlayerCheckingSkillTest() {
		ITeam team = league.getConferences().get(0).getDivisions().get(0).getTeams().get(0);
		ICoach coach = team.getHeadCoach();
		List<IPlayer> players = team.getPlayers();
		traingPlayer.changePlayerCheckingSkill(players.get(0), coach.getChecking(), league);
	}

	@Test
	public void changePlayerSavingSkillTest() {
		ITeam team = league.getConferences().get(0).getDivisions().get(0).getTeams().get(0);
		ICoach coach = team.getHeadCoach();
		List<IPlayer> players = team.getPlayers();
		traingPlayer.changePlayerSavingSkill(players.get(0), coach.getSaving(), league);
	}
}
