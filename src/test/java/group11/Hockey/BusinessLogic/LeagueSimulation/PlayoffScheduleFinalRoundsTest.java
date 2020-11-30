// Author: Harry B00856244
package group11.Hockey.BusinessLogic.LeagueSimulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.LeagueSimulation.Interfaces.IScheduleStrategy;
import group11.Hockey.BusinessLogic.models.ICoach;
import group11.Hockey.BusinessLogic.models.IGeneralManager;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.ITimeLine;

public class PlayoffScheduleFinalRoundsTest {
	
	@Test
	public void getSchedule() {
		 SimulationLeagueModelMock leagueMock = new SimulationLeagueModelMock();
		  ILeague league = leagueMock.getLeagueInfo(); 
		  ITeam team1,team2;
		  
		  ICoach coach = DefaultHockeyFactory.makeCoach("Dave", null);
		  
		  IGeneralManager gm1=DefaultHockeyFactory.makeGeneralManager("gm1", "normal");
		  IGeneralManager gm2=DefaultHockeyFactory.makeGeneralManager("gm2", "normal");
		  
		  List<IPlayer> playerList = new ArrayList<>(); 
		  IPlayer player1 = DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player One", "forward", true, false, 50); 
		  IPlayer player2 = DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player Two", "defense", false, false,
		  20); 
		  IPlayer player3 = DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player Three", "goalie",
		  false, false, 20);
		  
		  playerList.add(player1); playerList.add(player2); playerList.add(player3);
		  
		  team1 = DefaultHockeyFactory.makeTeam("Boston Bruins", gm1, coach, playerList); 
		  team2 = DefaultHockeyFactory.makeTeam("Arizona Coyotes", gm2, coach, playerList); 
		  List<ITeam> teamList = new ArrayList<>();
		  teamList.add(team1); teamList.add(team2);
		  league.setQualifiedTeams(teamList);
		  
		  league.setStartDate("01/10/2020");
		  ITimeLine timeline=DefaultHockeyFactory.makeTimeLine();
		  timeline.setCurrentDate("01/06/2021");
		  league.setTimeLine(timeline);
			
		  IScheduleStrategy playoff=DefaultHockeyFactory.makePlayoffScheduleFinalRounds(null, null, null);
		  playoff.getSchedule(league, null);
		  HashMap<String, HashMap<ITeam, ITeam>> playoffScheduleFinal = league.getSchedule();
		  int size=playoffScheduleFinal.size(); 
		  Assert.assertEquals(7, size); 
	}
}