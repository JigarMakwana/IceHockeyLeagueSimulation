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

		List<ITeam> teamsList = new ArrayList<>();
		float skill = (float) 2.0;
		ICoach coach = DefaultHockeyFactory.makeCoach();
		coach.setChecking(skill);
		coach.setName("Dave");
		coach.setSaving(skill);
		coach.setShooting(skill);
		coach.setSkating(skill);

		IGeneralManager gm1 = DefaultHockeyFactory.makeGeneralManager("Mister Fred", "normal");
		ITeam team1 = DefaultHockeyFactory.makeTeam("Boston", gm1, coach, playerList);

		playerList = new ArrayList<>();
		IPlayer player4 = DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player One", "forward", true, false, 50);
		IPlayer player5 = DefaultHockeyFactory.makePlayer(10, 10, 10, 10, "Player Two", "defense", false, false, 20);
		playerList.add(player4);
		playerList.add(player5);

		IGeneralManager gm2 = DefaultHockeyFactory.makeGeneralManager("John Smith", "shrewd");
		ITeam team2 = DefaultHockeyFactory.makeTeam("Vancouver Canucks", gm2, coach, playerList);
		teamsList.add(team1);
		teamsList.add(team2);

		List<IDivision> divisionsList = new ArrayList<>();
		IDivision atlanticDivision = DefaultHockeyFactory.makeDivision("Atlantic", teamsList);
		divisionsList.add(atlanticDivision);
		List<IConference> conferenceList = new ArrayList<>();
		List<Player> freeAgentsList = new ArrayList<>();
		IConference conference = DefaultHockeyFactory.makeConference("Eastern Conference", divisionsList);
		conferenceList.add(conference);
		league = DefaultHockeyFactory.makeLeague("Dalhousie Hockey League", conferenceList, freeAgentsList,
				gameplayConfig, null, null);
		playerList = new ArrayList<>();
		List<ICoach> coachList = new ArrayList<>();
		coachList.add(DefaultHockeyFactory.makeCoach((float) 2.0, (float) 2.0, (float) 2.0, (float) 2.0, "Coach 1"));
		league.setCoaches(coachList);
		List<IGeneralManager> generalManagerList = new ArrayList<>();
		IGeneralManager generalManager = DefaultHockeyFactory.makeGeneralManager("General Manager 1", "normal");
		generalManagerList.add(generalManager);
		league.setGeneralManagers(generalManagerList);
		populateFreeAgents(league);
		playerList.add((Player) player1);
		List<ITeam> qualifiedTeams = league.getQualifiedTeams();
		qualifiedTeams.add(DefaultHockeyFactory.makeTeam("Rangers", gm2, coach, playerList));
		qualifiedTeams.add(DefaultHockeyFactory.makeTeam("Lions", gm2, coach, playerList));

		league.setRetiredPlayers(playerList);
		ITimeLine timeLine = DefaultHockeyFactory.makeTimeLine();
		timeLine.setCurrentDate("27/11/2020");
		league.setTimeLine(timeLine);
	}

	public void populateFreeAgents(ILeague league) {
		List<Player> freeAgents = new ArrayList<>();
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
		IGeneralManager gm = DefaultHockeyFactory.makeGeneralManager("John Smith", "shrewd");
		teamList.add(DefaultHockeyFactory.makeTeam("Rangers1", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(DefaultHockeyFactory.makeTeam("Rangers2", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(DefaultHockeyFactory.makeTeam("Rangers3", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(DefaultHockeyFactory.makeTeam("Rangers4", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(DefaultHockeyFactory.makeTeam("Rangers5", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(DefaultHockeyFactory.makeTeam("Rangers6", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(DefaultHockeyFactory.makeTeam("Rangers7", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(DefaultHockeyFactory.makeTeam("Rangers8", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(DefaultHockeyFactory.makeTeam("Rangers9", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(DefaultHockeyFactory.makeTeam("Rangers10", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(DefaultHockeyFactory.makeTeam("Rangers11", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(DefaultHockeyFactory.makeTeam("Rangers12", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(DefaultHockeyFactory.makeTeam("Rangers13", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(DefaultHockeyFactory.makeTeam("Rangers14", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(DefaultHockeyFactory.makeTeam("Rangers15", gm, teamList.get(0).getHeadCoach(), playerList));
		teamList.add(DefaultHockeyFactory.makeTeam("Rangers16", gm, teamList.get(0).getHeadCoach(), playerList));
	}

	public ILeague getLeagueInfo() {
		return league;
	}

}
