package group11.Hockey.db.Team;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import group11.Hockey.BusinessLogic.models.Coach;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.GeneralManager;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.db.GameplayConfigDb;
import group11.Hockey.db.IGameplayConfigDb;
import group11.Hockey.db.ProcedureCallDb;
import group11.Hockey.db.Player.IPlayerDb;
import group11.Hockey.db.Player.PlayerDbImpl;

public class TeamDbImpl implements ITeamDb {

	@Override
	public League loadLeagueWithTeamName(String teamName) {
		ProcedureCallDb procedureCallDb = new ProcedureCallDb("{call findTeamByTeamName(?)}");
		CallableStatement statement = procedureCallDb.getDBCallableStatement();
		Conference conference = new Conference();
		Division divison = new Division();
		Team team = new Team();
		League league = new League();
		try {
			statement.setString(1, teamName);
			procedureCallDb.executeProcedure();
			ResultSet resultSet = statement.getResultSet();
			while (resultSet.next()) {
				league.setLeagueName(resultSet.getString("leagueName"));
				league.setStartDate(resultSet.getString("leagueStartDate"));
				Conference conferenceInLeague = null;
				Division divisionInConference = null;
				Team teamInDivision = null;
				boolean conferencExits = conference.isConferenceNameValid(resultSet.getString("conferenceName"),
						league.getConferences());
				if (conferencExits) {
					conferenceInLeague = conference.getConferencefromConferenceName(
							resultSet.getString("conferenceName"), league.getConferences());
				} else {
					List<Conference> conferenceList = league.getConferences();
					conferenceInLeague = new Conference(resultSet.getString("conferenceName"), null);
					conferenceList.add(conferenceInLeague);
				}
				divisionInConference = populateDivisionInConference(divison, resultSet, conferenceInLeague);
				teamInDivision = pupulateTeamInDivision(team, resultSet, divisionInConference);
				Player player = populatePlayerDetails(resultSet);

				List<Player> playerList = teamInDivision.getPlayers();
				if (playerList == null || playerList.size() == 0) {
					playerList = new ArrayList<Player>();
					playerList.add(player);
					teamInDivision.setPlayers(playerList);
				} else {
					playerList.add(player);
				}
			}
			IPlayerDb playerDb = new PlayerDbImpl();
			List<Player> freeAgentList = playerDb.loadFreeAgents(league.getLeagueName());
			league.setFreeAgents(freeAgentList);
			IGameplayConfigDb gameConfigDb = new GameplayConfigDb();
			league.setGamePlayConfig(gameConfigDb.loadGameConfig(league.getLeagueName()));
			statement.close();
			procedureCallDb.closeConnection();
		} catch (Exception e) {
			procedureCallDb.closeConnection();
			System.out.println("Exception occured while getting the callable statment ");
		} finally {

			procedureCallDb.closeConnection();
		}

		return league;
	}

	private Division populateDivisionInConference(Division divison, ResultSet resultSet, Conference conferenceInLeague)
			throws SQLException {
		Division divisionInConference;
		boolean divisionExits = divison.isDivisionNameValid(resultSet.getString("divisionName"),
				conferenceInLeague.getDivisions());
		if (divisionExits) {
			divisionInConference = divison.getDivisionFromDivisionName(resultSet.getString("divisionName"),
					conferenceInLeague.getDivisions());
		} else {
			divisionInConference = new Division(resultSet.getString("divisionName"), null);
			List<Division> divisionList = conferenceInLeague.getDivisions();
			if (divisionList == null || divisionList.size() == 0) {
				divisionList = new ArrayList<Division>();
				divisionList.add(divisionInConference);
				conferenceInLeague.setDivisions(divisionList);
			} else {
				divisionList.add(divisionInConference);
			}
		}
		return divisionInConference;
	}

	private Team pupulateTeamInDivision(Team team, ResultSet resultSet, Division divisionInConference)
			throws SQLException {
		Team teamInDivision;
		boolean teamExists = team.teamExistsInDivision(resultSet.getString("teamName"), divisionInConference);
		if (teamExists) {
			teamInDivision = team.getTeamFromDivision(resultSet.getString("teamName"), divisionInConference);
		} else {
			teamInDivision = new Team();
			teamInDivision.setTeamName(resultSet.getString("teamName"));
			Coach headCoach = new Coach();
			headCoach.setName(resultSet.getString("coachName"));
			headCoach.setChecking(Float.parseFloat(resultSet.getString("coachChecking")));
			headCoach.setSaving((Float.parseFloat(resultSet.getString("coachSaving"))));
			headCoach.setShooting((Float.parseFloat(resultSet.getString("coachShooting"))));
			headCoach.setSkating((Float.parseFloat(resultSet.getString("coachSkating"))));
			teamInDivision.setHeadCoach(headCoach);
			teamInDivision.setGeneralManager(resultSet.getString("generalManger"));
			List<Team> teamList = divisionInConference.getTeams();
			if (teamList == null || teamList.size() == 0) {
				teamList = new ArrayList<Team>();
				teamList.add(teamInDivision);
				divisionInConference.setTeams(teamList);
			} else {
				teamList.add(teamInDivision);
			}
		}
		return teamInDivision;
	}

	private Player populatePlayerDetails(ResultSet resultSet) throws SQLException {
		Player player = new Player();
		player.setPlayerName(resultSet.getString("playerName"));
		player.setPosition(resultSet.getString("playerPosition"));
		player.setAge(Float.parseFloat(resultSet.getString("age")));
		player.setSkating(Float.parseFloat(resultSet.getString("skating")));
		player.setShooting(Float.parseFloat(resultSet.getString("shooting")));
		player.setChecking(Float.parseFloat(resultSet.getString("checking")));
		player.setSaving(Float.parseFloat(resultSet.getString("saving")));
		player.setCaptain(Boolean.parseBoolean(resultSet.getString("captain")));
		player.setIsFreeAgent(Boolean.parseBoolean(resultSet.getString("isFreeAgent")));
		player.setIsRetired(Boolean.parseBoolean(resultSet.getString("retired")));
		return player;
	}

}
