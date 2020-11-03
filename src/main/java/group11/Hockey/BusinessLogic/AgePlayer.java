package group11.Hockey.BusinessLogic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

public class AgePlayer extends RetirePlayer {

	public void increaseAge(League league, int days) {
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

	private void checkForRetirement(League league) {
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
