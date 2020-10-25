package group11.Hockey;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.models.Conference;
import group11.Hockey.models.Division;
import group11.Hockey.models.League;
import group11.Hockey.models.LeagueModelMock;
import group11.Hockey.models.Player;
import group11.Hockey.models.Team;

public class AgePlayerTest {

	@Test
	public void increaseAgeTest() {

		LeagueModelMock leagueModel = new LeagueModelMock();
		League league = leagueModel.getLeagueInfo();

		AgePlayer agePlayer = new AgePlayer();
		agePlayer.increaseAge(league, 365);
		List<Conference> conferences = league.getConferences();
		List<Division> divisions = conferences.get(0).getDivisions();
		List<Team> teams = divisions.get(0).getTeams();
		List<Player> players = teams.get(0).getPlayers();
		Player player = players.get(0);
		Assert.assertEquals(21, player.getAge(), 21);
	}

}
