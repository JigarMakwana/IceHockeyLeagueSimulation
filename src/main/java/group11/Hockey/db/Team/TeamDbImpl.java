package group11.Hockey.db.Team;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import group11.Hockey.BusinessLogic.models.*;
import group11.Hockey.db.Constants;
import group11.Hockey.db.ProcedureCallDb;
import group11.Hockey.db.GameplayConfig.GameplayConfigDb;
import group11.Hockey.db.GameplayConfig.IGameplayConfigDb;
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
				league.setLeagueName(resultSet.getString(Constants.leagueName.toString()));
				league.setStartDate(resultSet.getString(Constants.leagueStartDate.toString()));
				IConference conferenceInLeague = null;
				Division divisionInConference = null;
				ITeam teamInDivision = null;
				boolean conferencExits = conference.isConferenceNameValid(
						resultSet.getString(Constants.conferenceName.toString()), league.getConferences());
				if (conferencExits) {
					conferenceInLeague = conference.getConferencefromConferenceName(
							resultSet.getString(Constants.conferenceName.toString()), league.getConferences());
				} else {
					List<IConference> conferenceList = league.getConferences();
					conferenceInLeague = new Conference(resultSet.getString(Constants.conferenceName.toString()), null);
					conferenceList.add(conferenceInLeague);
				}
				divisionInConference = populateDivisionInConference(divison, resultSet, conferenceInLeague);
				teamInDivision = pupulateTeamInDivision(team, resultSet, divisionInConference);
				Player player = populatePlayerDetails(resultSet);

				List<IPlayer> playerList = teamInDivision.getPlayers();
				if (playerList == null || playerList.size() == 0) {
					playerList = new ArrayList<>();
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

	private Division populateDivisionInConference(Division divison, ResultSet resultSet, IConference conferenceInLeague)
			throws SQLException {
		Division divisionInConference;
		boolean divisionExits = divison.isDivisionNameValid(resultSet.getString(Constants.divisionName.toString()),
				conferenceInLeague.getDivisions());
		if (divisionExits) {
			divisionInConference = divison.getDivisionFromDivisionName(
					resultSet.getString(Constants.divisionName.toString()), conferenceInLeague.getDivisions());
		} else {
			divisionInConference = new Division(resultSet.getString(Constants.divisionName.toString()), null);
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

	private ITeam pupulateTeamInDivision(ITeam team, ResultSet resultSet, Division divisionInConference)
			throws SQLException {
		ITeam teamInDivision;
		boolean teamExists = team.teamExistsInDivision(resultSet.getString(Constants.teamName.toString()),
				divisionInConference);
		if (teamExists) {
			teamInDivision = team.getTeamFromDivision(resultSet.getString(Constants.teamName.toString()),
					divisionInConference);
		} else {
			teamInDivision = new Team();
			teamInDivision.setTeamName(resultSet.getString(Constants.teamName.toString()));
			Coach headCoach = new Coach();
			headCoach.setName(resultSet.getString(Constants.coachName.toString()));
			headCoach.setChecking(Float.parseFloat(resultSet.getString(Constants.coachChecking.toString())));
			headCoach.setSaving((Float.parseFloat(resultSet.getString(Constants.coachSaving.toString()))));
			headCoach.setShooting((Float.parseFloat(resultSet.getString(Constants.coachShooting.toString()))));
			headCoach.setSkating((Float.parseFloat(resultSet.getString(Constants.coachSkating.toString()))));
			teamInDivision.setHeadCoach(headCoach);
			GeneralManager gm = new GeneralManager();
			gm.setName(Constants.generalMangerName.toString());
			gm.setPersonality(Constants.generalMangerPersonality.toString());
			teamInDivision.setGeneralManager(gm);
			List<ITeam> teamList = divisionInConference.getTeams();
			if (teamList == null || teamList.size() == 0) {
				teamList = new ArrayList<>();
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
		player.setPlayerName(resultSet.getString(Constants.playerName.toString()));
		player.setPosition(resultSet.getString(Constants.playerPosition.toString()));
		player.setAge(Float.parseFloat(resultSet.getString(Constants.age.toString())));
		player.setSkating(Float.parseFloat(resultSet.getString(Constants.skating.toString())));
		player.setShooting(Float.parseFloat(resultSet.getString(Constants.shooting.toString())));
		player.setChecking(Float.parseFloat(resultSet.getString(Constants.checking.toString())));
		player.setSaving(Float.parseFloat(resultSet.getString(Constants.saving.toString())));
		player.setCaptain(Boolean.parseBoolean(resultSet.getString(Constants.captain.toString())));
		player.setIsFreeAgent(Boolean.parseBoolean(resultSet.getString(Constants.isFreeAgent.toString())));
		player.setIsRetired(Boolean.parseBoolean(resultSet.getString(Constants.retired.toString())));
		return player;
	}

}
