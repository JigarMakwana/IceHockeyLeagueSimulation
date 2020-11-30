package group11.Hockey;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.LeagueSimulation.InitializeSeason;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.db.Coach.ICoachDb;
import group11.Hockey.db.GameplayConfig.IGameplayConfigDb;
import group11.Hockey.db.League.ILeagueDb;
import group11.Hockey.db.Manager.IManagerDb;

public class InitializeSeasonTest {

	@Test
	public void startSeasonsTest() {

//		SimulationLeagueModelMock leagueMock = new SimulationLeagueModelMock();
//		League league = leagueMock.getLeagueInfo();
//
//		ILeagueDb leagueDb = mock(ILeagueDb.class);
//		IGameplayConfigDb gameplayConfigDb = mock(IGameplayConfigDb.class);
//		IPlayerDb playerDb = mock(IPlayerDb.class);
//		ICoachDb coachDb = mock(ICoachDb.class);
//		IManagerDb managerDb = mock(IManagerDb.class);
//
//		when(leagueDb.insertLeagueInDb(null, "conf", "div", null, null, null)).thenReturn(true);
//		when(gameplayConfigDb.insertGameplayConfig(null,null,null,null,null, "league")).thenReturn(true);
//		when(playerDb.insertLeagueFreeAgents("league", null)).thenReturn(true);
//		when(coachDb.insertCoaches("league", "c1", 0, 0, 0, 0)).thenReturn(true);
//		when(managerDb.insertManager("league", "M1")).thenReturn(true);
//
//		InitializeSeason initialize=new InitializeSeason(league, leagueDb,  gameplayConfigDb, playerDb, coachDb, managerDb);
//		String endDate=initialize.startSeasons(1);
//		Assert.assertEquals("29/09/2021", endDate);
	}
}

