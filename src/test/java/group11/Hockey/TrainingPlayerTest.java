package group11.Hockey;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import group11.Hockey.BusinessLogic.TrainingPlayer;
import group11.Hockey.BusinessLogic.models.Coach;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.models.LeagueModelMock;

public class TrainingPlayerTest {
	League league;
	TrainingPlayer traingPlayer = new TrainingPlayer();

	@Before
	public void loadLeague() {
		LeagueModelMock leagueMock = new LeagueModelMock();
		league = leagueMock.getLeagueInfo();
	}

	@Test
	public void trainPlayerTest() {
		Team team = league.getConferences().get(0).getDivisions().get(0).getTeams().get(0);
		traingPlayer.trainPlayer(league);
		Assert.assertTrue(team.getPlayers().get(0).getSkating() >= 0);
		Assert.assertTrue(team.getPlayers().get(0).getShooting() >= 0);
		Assert.assertTrue(team.getPlayers().get(0).getChecking() >= 0);
		Assert.assertTrue(team.getPlayers().get(0).getSaving() >= 0);
	}

	@Test
	public void comapreCoachStatTest() {
		Team team = league.getConferences().get(0).getDivisions().get(0).getTeams().get(0);
		boolean skillCheck = traingPlayer.comapreCoachStat(team.getHeadCoach().getSkating());
		Assert.assertTrue(skillCheck);

	}

	@Test
	public void changePlayerSkatingSkillTest() {
		Team team = league.getConferences().get(0).getDivisions().get(0).getTeams().get(0);
		Coach coach = team.getHeadCoach();
		List<Player> players = team.getPlayers();
		traingPlayer.changePlayerSkatingSkill(players.get(0), coach.getSkating(), league);
	}

	@Test
	public void changePlayerShootingSkillTest() {
		Team team = league.getConferences().get(0).getDivisions().get(0).getTeams().get(0);
		Coach coach = team.getHeadCoach();
		List<Player> players = team.getPlayers();
		traingPlayer.changePlayerShootingSkill(players.get(0), coach.getShooting(), league);
	}

	@Test
	public void changePlayerCheckingSkillTest() {
		Team team = league.getConferences().get(0).getDivisions().get(0).getTeams().get(0);
		Coach coach = team.getHeadCoach();
		List<Player> players = team.getPlayers();
		traingPlayer.changePlayerCheckingSkill(players.get(0), coach.getChecking(), league);
	}

	@Test
	public void changePlayerSavingSkillTest() {
		Team team = league.getConferences().get(0).getDivisions().get(0).getTeams().get(0);
		Coach coach = team.getHeadCoach();
		List<Player> players = team.getPlayers();
		traingPlayer.changePlayerSavingSkill(players.get(0), coach.getSaving(), league);
	}
}
