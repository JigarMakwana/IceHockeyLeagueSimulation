package group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation;

import java.util.List;

import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IDivision;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.LeagueModelMock;
import group11.Hockey.BusinessLogic.models.Player;
import junit.framework.Assert;

public class GenerateShiftsTest {

	@Test
	public void generateGoalieShiftTest() {
		ILeague league = new LeagueModelMock().getLeagueInfo();
		IConference conf = league.getConferences().get(0);
		IDivision div = conf.getDivisions().get(0);
		ITeam team = div.getTeams().get(0);
		List<Player> playersList = team.getPlayers();
		playersList.addAll(playersList);
		GenerateShiftsTemplate shiftsObj = DefaultHockeyFactory.makeGeneratePlayOffShifts(playersList);
		List<Player>[] shifts = shiftsObj.getShifts();
		Assert.assertEquals(shifts.length, 40);
	}

}
