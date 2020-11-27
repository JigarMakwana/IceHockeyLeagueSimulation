package group11.Hockey.BusinessLogic.models;

import java.util.ArrayList;
import java.util.List;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.models.Aging;
import group11.Hockey.BusinessLogic.models.Coach;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.GameResolver;
import group11.Hockey.BusinessLogic.models.GameplayConfig;
import group11.Hockey.BusinessLogic.models.GeneralManager;
import group11.Hockey.BusinessLogic.models.IgmTable;
import group11.Hockey.BusinessLogic.models.Injuries;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.BusinessLogic.models.Trading;
import group11.Hockey.BusinessLogic.models.Training;
import group11.Hockey.BusinessLogic.models.gmTable;

public class LeagueModelMock {
	private League league;

	public LeagueModelMock() {
		super();

		addLeague();
	}

	private void addLeague() {

		List<Player> playerList = new ArrayList<Player>();

		Aging aging = new Aging(30, 55);
		GameResolver gameResolver = new GameResolver(0);
		Injuries injuries = new Injuries(1, 1, 100);
		Training training = new Training(0);
		IgmTable gmTbale = new gmTable(-0.1f, 0.1f, 0.0f);
		Trading trading = new Trading(0, 0, 0, 0, gmTbale);

		GameplayConfig gameplayConfig = new GameplayConfig(aging, gameResolver, injuries, training, trading);

		Player player1 = new Player(10, 10, 10, 10, "Player One", "forward", true, false, 50);
		Player player2 = new Player(10, 10, 10, 10, "Player Two", "defense", false, false, 20);
		Player player3 = new Player(10, 10, 10, 10, "Player Three", "goalie", false, false, 20);
		playerList.add(player1);
		playerList.add(player2);
		playerList.add(player3);

		List<Team> teamsList = new ArrayList<Team>();
		float skill = (float) 2.0;
		Coach coach = new Coach();
		coach.setChecking(skill);
		coach.setName("Dave");
		coach.setSaving(skill);
		coach.setShooting(skill);
		coach.setSkating(skill);

		GeneralManager gm1 = new GeneralManager("Mister Fred","normal");
		Team team1 = new Team("Boston", gm1, coach, playerList);

		playerList = new ArrayList<Player>();
		Player player4 = new Player(10, 10, 10, 10, "Player One", "forward", true, false, 50);
		Player player5 = new Player(10, 10, 10, 10, "Player Two", "defense", false, false, 20);
		playerList.add(player4);
		playerList.add(player5);

		GeneralManager gm2 = new GeneralManager("John Smith","shrewd");
		Team team2 = new Team("Vancouver Canucks", gm2, coach, playerList);
		teamsList.add(team1);
		teamsList.add(team2);

		List<Division> divisionsList = new ArrayList<Division>();
		Division atlanticDivision = new Division("Atlantic", teamsList);
		divisionsList.add(atlanticDivision);
		List<Conference> conferenceList = new ArrayList<Conference>();
		List<Player> freeAgentsList = new ArrayList<Player>();
		Conference conference = new Conference("Eastern Conference", divisionsList);
		conferenceList.add(conference);
		league = new League("Dalhousie Hockey League", conferenceList, freeAgentsList, gameplayConfig, null, null);
		playerList = new ArrayList<Player>();
		List<Coach> coachList = new ArrayList<Coach>();
		coachList.add(new Coach((float) 2.0, (float) 2.0, (float) 2.0, (float) 2.0, "Coach 1"));
		league.setCoaches(coachList);
		List<GeneralManager> generalManagerList = new ArrayList<GeneralManager>();
		GeneralManager generalManager = new GeneralManager("General Manager 1");
		generalManagerList.add(generalManager);
		league.setGeneralManagers(generalManagerList);
		populateFreeAgents(league);
		playerList.add(player1);
		List<Team> qualifiedTeams = league.getQualifiedTeams();
		qualifiedTeams.add(new Team("Rangers",gm2, coach, playerList));
		qualifiedTeams.add(new Team("Lions", gm2, coach, playerList));

		league.setRetiredPlayers(playerList);
		TimeLine timeLine = new TimeLine();
		timeLine.setCurrentDate("27/11/2020");
		league.setTimeLine(timeLine);
	}

	public void populateFreeAgents(League league) {
		List<Player> freeAgents = new ArrayList<Player>();
		freeAgents.add(new Player(10, 10, 10, 10, "Player 1", "forward", true, false, 50));
		freeAgents.add(new Player(10, 10, 10, 10, "Player 2", "forward", true, false, 50));
		freeAgents.add(new Player(10, 10, 10, 10, "Player 3", "forward", true, false, 50));
		freeAgents.add(new Player(10, 10, 10, 10, "Player 4", "forward", true, false, 50));
		freeAgents.add(new Player(10, 10, 10, 10, "Player 5", "forward", true, false, 50));
		freeAgents.add(new Player(10, 10, 10, 10, "Player 6", "forward", true, false, 50));
		freeAgents.add(new Player(10, 10, 10, 10, "Player 7", "forward", true, false, 50));
		freeAgents.add(new Player(10, 10, 10, 10, "Player 8", "forward", true, false, 50));
		freeAgents.add(new Player(10, 10, 10, 10, "Player 9", "forward", true, false, 50));
		freeAgents.add(new Player(10, 10, 10, 10, "Player 10", "forward", true, false, 50));
		freeAgents.add(new Player(10, 10, 10, 10, "Player 11", "forward", true, false, 50));
		freeAgents.add(new Player(10, 10, 10, 10, "Player 12", "forward", true, false, 50));
		freeAgents.add(new Player(10, 10, 10, 10, "Player 13", "forward", true, false, 50));
		freeAgents.add(new Player(10, 10, 10, 10, "Player 14", "forward", true, false, 50));
		freeAgents.add(new Player(10, 10, 10, 10, "Player 15", "forward", true, false, 50));
		freeAgents.add(new Player(10, 10, 10, 10, "Player 16", "forward", true, false, 50));
		freeAgents.add(new Player(10, 10, 10, 10, "Player 17", "forward", true, false, 50));
		freeAgents.add(new Player(10, 10, 10, 10, "Player 18", "forward", true, false, 50));
		freeAgents.add(new Player(10, 10, 10, 10, "Player 19", "goalie", true, false, 50));
		freeAgents.add(new Player(10, 10, 10, 10, "Player 20", "goalie", true, false, 50));
		league.setFreeAgents(freeAgents);
	}
	
	public void insertDataForDrafing() {
		List<Team> teamList = league.getConferences().get(0).getDivisions().get(0).getTeams();
		List<Player> playerList = teamList.get(0).getPlayers();
		GeneralManager gm = new GeneralManager("John Smith","shrewd");
		teamList.add(new Team("Rangers1", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(new Team("Rangers2", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(new Team("Rangers3", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(new Team("Rangers4", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(new Team("Rangers5", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(new Team("Rangers6", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(new Team("Rangers7", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(new Team("Rangers8", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(new Team("Rangers9", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(new Team("Rangers10", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(new Team("Rangers11", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(new Team("Rangers12", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(new Team("Rangers13", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(new Team("Rangers14", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(new Team("Rangers15", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(new Team("Rangers16", gm, teamList.get(0).getHeadCoach(), playerList));
	}


	public League getLeagueInfo() {
		return league;
	}

}
