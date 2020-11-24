/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import group11.Hockey.BusinessLogic.LeagueSimulation.IParse;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITimeLine;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.db.League.ILeagueDb;

public class AgePlayer extends RetirePlayer {

	ILeague league;
	int days;
	ILeagueDb leagueDb;

	public AgePlayer() {

	}

	public AgePlayer(ILeague league, int days) {
		this.league = league;
		this.days = days;
	}

	public AgePlayer(ILeague league, int days, ILeagueDb leagueDb) {
		this.league = league;
		this.days = days;
		this.leagueDb = leagueDb;
	}

	@Override
	public StateMachineState startState() {
		agePlayers();
		// call Advance next season or Advance Time
		IParse parse = DefaultHockeyFactory.makeParse();
		ITimeLine timeLine = league.getTimeLine();
		String currentDate = timeLine.getCurrentDate();
		Date stanleyEndDateTime = timeLine.getStanleyEndDateTime();
		List<Team> qualifiedTeams = league.getQualifiedTeams();
		Date dateTime = parse.stringToDate(currentDate);
		if ((dateTime.equals(stanleyEndDateTime)) || (qualifiedTeams.size() == 1)) {

			return DefaultHockeyFactory.makeAdvanceToNextSeason(league, leagueDb);
		} else {
			return DefaultHockeyFactory.makeAdvanceTime(league, leagueDb);
		}
	}

	public void agePlayers() {
		List<Player> freeAgents = league.getFreeAgents();
		List<Conference> conferences = league.getConferences();

		if (freeAgents.size() > 0) {
			for (Player freeAgent : freeAgents) {
				freeAgent.increaseAge(league, days);
			}
		}

		if (conferences.size() > 0) {
			for (Conference conference : conferences) {
				List<Division> divisions = conference.getDivisions();
				if (divisions.size() > 0) {
					for (Division division : divisions) {
						List<Team> teams = division.getTeams();
						if (teams.size() > 0) {
							for (Team team : teams) {
								List<Player> players = team.getPlayers();
								if (players.size() > 0) {
									for (Player player : players) {
										player.increaseAge(league, days);
									}
								}
							}
						}
					}
				}
			}
		}
		checkForRetirement(league);
	}

	private void checkForRetirement(ILeague league) {
		boolean isRetired;
		List<Player> retiredPlayers = new ArrayList<Player>();
		List<Player> freeAgents = league.getFreeAgents();
		List<Conference> conferences = league.getConferences();

		Iterator<Player> freeAgentsItr = freeAgents.iterator();

		while (freeAgentsItr.hasNext()) {
			Player freeAgent = freeAgentsItr.next();
			isRetired = freeAgent.isIsRetired();
			if (isRetired) {
				retiredPlayers.add(freeAgent);
				freeAgentsItr.remove();
			}
		}

		if (conferences.size() > 0) {
			for (Conference conference : conferences) {
				List<Division> divisions = conference.getDivisions();
				if (divisions.size() > 0) {
					for (Division division : divisions) {
						List<Team> teams = division.getTeams();
						if (teams.size() > 0) {
							for (Team team : teams) {
								List<Player> players = team.getPlayers();
								if (players.size() > 0) {
									for (Player player : players) {
										isRetired = player.isIsRetired();
										if (isRetired) {
											retiredPlayers.add(player);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		List<Player> existingRetiredPlayers = league.getRetiredPlayers();
		existingRetiredPlayers.addAll(retiredPlayers);
		league.setRetiredPlayers(existingRetiredPlayers);
		retireAndReplacePlayer(league);
	}

}
