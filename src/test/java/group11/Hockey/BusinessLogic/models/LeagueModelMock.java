package group11.Hockey.BusinessLogic.models;

import java.util.ArrayList;
import java.util.List;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;

public class LeagueModelMock {
	private ILeague league;

	public LeagueModelMock() {
		super();

		addLeague();
	}

	private void addLeague() {

		List<IPlayer> playerList = new ArrayList<>();

		IAging aging = DefaultHockeyFactory.makeAging(30, 55);
		IInjuries injuries = DefaultHockeyFactory.makeInjuries(1, 1, 100);
		ITraining training = DefaultHockeyFactory.makeTraining(0);
		IgmTable gmTbale = DefaultHockeyFactory.makeGMTable(-0.1f, 0.1f, 0.0f);
		ITrading trading = DefaultHockeyFactory.makeTradingConfig(0, 0, 0, 0, gmTbale);
		IGameplayConfig gameplayConfig = DefaultHockeyFactory.makeGameplayConfig(aging, injuries, training, trading);

		IPlayer player1 = DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player One", "forward", true, false, 50);
		player1.setBirthDay(20);
		player1.setBirthMonth(8);
		player1.setBirthYear(1990);
		IPlayer player2 = DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player Two", "defense", false, false, 20);
		IPlayer player3 = DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player Three", "goalie", false, false, 20);
		playerList.add((Player) player1);
		playerList.add((Player) player2);
		playerList.add((Player) player3);

		List<Team> teamsList = new ArrayList<Team>();
		float skill = (float) 2.0;
		Coach coach = new Coach();
		coach.setChecking(skill);
		coach.setName("Dave");
		coach.setSaving(skill);
		coach.setShooting(skill);
		coach.setSkating(skill);

		GeneralManager gm1 = new GeneralManager("Mister Fred", "normal");
		Team team1 = new Team("Boston", gm1, coach, playerList);

		playerList = new ArrayList<>();
		Player player4 = new Player(10, 10, 10, 10, "Player One", "forward", true, false, 50);
		Player player5 = new Player(10, 10, 10, 10, "Player Two", "defense", false, false, 20);
		playerList.add(player4);
		playerList.add(player5);

		GeneralManager gm2 = new GeneralManager("John Smith", "shrewd");
		Team team2 = new Team("Vancouver Canucks", gm2, coach, playerList);
		teamsList.add(team1);
		teamsList.add(team2);

		List<IDivision> divisionsList = new ArrayList<>();
		IDivision atlanticDivision = new Division("Atlantic", teamsList);
		divisionsList.add(atlanticDivision);
		List<IConference> conferenceList = new ArrayList<>();
		List<Player> freeAgentsList = new ArrayList<>();
		IConference conference = new Conference("Eastern Conference", divisionsList);
		conferenceList.add(conference);
		league = new League("Dalhousie Hockey League", conferenceList, freeAgentsList, gameplayConfig, null, null);
		playerList = new ArrayList<>();
		List<ICoach> coachList = new ArrayList<>();
		coachList.add(new Coach((float) 2.0, (float) 2.0, (float) 2.0, (float) 2.0, "Coach 1"));
		league.setCoaches(coachList);
		List<IGeneralManager> generalManagerList = new ArrayList<>();
		IGeneralManager generalManager = new GeneralManager("General Manager 1", "normal");
		generalManagerList.add(generalManager);
		league.setGeneralManagers(generalManagerList);
		populateFreeAgents(league);
		playerList.add((Player) player1);
		List<ITeam> qualifiedTeams = league.getQualifiedTeams();
		qualifiedTeams.add(new Team("Rangers", gm2, coach, playerList));
		qualifiedTeams.add(new Team("Lions", gm2, coach, playerList));

		league.setRetiredPlayers(playerList);
		TimeLine timeLine = new TimeLine();
		timeLine.setCurrentDate("27/11/2020");
		league.setTimeLine(timeLine);
	}

	public void populateFreeAgents(ILeague league) {
		List<Player> freeAgents = new ArrayList<Player>();
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 1", "forward", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 2", "forward", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 3", "forward", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 4", "forward", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 5", "forward", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 6", "forward", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 7", "forward", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 8", "forward", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 9", "forward", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 10", "forward", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 11", "forward", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 12", "forward", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 13", "forward", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 14", "forward", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 15", "forward", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 16", "forward", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 1", "defense", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 2", "defense", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 3", "defense", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 4", "defense", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 5", "defense", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 6", "defense", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 7", "defense", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 8", "defense", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 9", "defense", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 10", "defense", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 20", "goalie", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 21", "goalie", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 22", "goalie", true, false, 50));
		freeAgents
				.add((Player) DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player 23", "goalie", true, false, 50));
		league.setFreeAgents(freeAgents);
	}

	public void insertDataForDrafing() {
		List<ITeam> teamList = league.getConferences().get(0).getDivisions().get(0).getTeams();
		List<IPlayer> playerList = teamList.get(0).getPlayers();
		GeneralManager gm = new GeneralManager("John Smith", "shrewd");
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

	public ILeague getLeagueInfo() {
		return league;
	}

}
