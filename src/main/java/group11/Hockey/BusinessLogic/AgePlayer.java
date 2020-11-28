/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.LeagueSimulation.IParse;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.ITimeLine;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.League.ILeagueDb;

public class AgePlayer extends RetirePlayer {

	ILeague league;
	int days;
	ILeagueDb leagueDb;
	IDisplay display;
	private static Logger logger = LogManager.getLogger(AgePlayer.class);

	public AgePlayer() {

	}

	public AgePlayer(ILeague league, int days, IDisplay display) {
		this.league = league;
		this.days = days;
		this.display = display;
	}

	public AgePlayer(ILeague league, int days, ILeagueDb leagueDb, IDisplay display) {
		this.league = league;
		this.days = days;
		this.leagueDb = leagueDb;
		this.display = display;
	}

	@Override
	public StateMachineState startState() {
		logger.info("Entered startState()");
		agePlayers();
		// call Advance next season or Advance Time
		IParse parse = DefaultHockeyFactory.makeParse();
		ITimeLine timeLine = league.getTimeLine();
		String currentDate = timeLine.getCurrentDate();
		Date stanleyEndDateTime = timeLine.getStanleyEndDateTime();
		List<Team> qualifiedTeams = league.getQualifiedTeams();
		Date dateTime = parse.stringToDate(currentDate);
		if ((dateTime.equals(stanleyEndDateTime)) || (qualifiedTeams.size() == 1)) {
			return DefaultHockeyFactory.makeDraftPlayer(league, leagueDb, display);
		} else {
			logger.info("Date is not end of stanley playoffs");
			return DefaultHockeyFactory.makeAdvanceTime(league, leagueDb, display);
		}
	}

	public void agePlayers() {
		logger.info("Entered agePlayers()");
		IParse parse = DefaultHockeyFactory.makeParse();
		Date currentDate =  parse.stringToDate(league.getTimeLine().getCurrentDate());

		List<Player> freeAgents = (List<Player>) league.getFreeAgents();
		List<IConference> conferences = league.getConferences();
		float statDecayChance = league.getGamePlayConfig().getAging().getStatDecayChance();
 		if (freeAgents.size() > 0) {
 			logger.info("Freeagents exists so trying to loop over them");
 			for (Player freeAgent : freeAgents) {
				Date playerBirthDate = getPlayerBirthDate(freeAgent, parse);
				if (currentDate.compareTo(playerBirthDate) == 0) {
					logger.info("Date is "+freeAgent.getPlayerName()+"'s birthday");
					freeAgent.increaseAge(league, days, statDecayChance);
				}
				else {
					logger.info("Date is "+freeAgent.getPlayerName()+"'s birthday");
					freeAgent.decreaseInjuredDaysForPlayer(days);
				}
 			}
 		}

		if (conferences.size() > 0) {
			logger.info("Conferences exists in"+league.getLeagueName()+", so looping over them");
			for (IConference conference : conferences) {
				List<Division> divisions = conference.getDivisions();
				if (divisions.size() > 0) {
					logger.info("Divisions exists in"+conference.getConferenceName()+", so looping over them");
					for (Division division : divisions) {
						List<Team> teams = division.getTeams();
						if (teams.size() > 0) {
							logger.info("Teams exists in "+division.getDivisionName()+", so looping over them");
							for (Team team : teams) {
								List<Player> players = team.getPlayers();
								if (players.size() > 0) {
									logger.info("Players exists in "+team.getTeamName()+", so looping over them");
									for (Player player : players) {
										Date playerBirthDate = getPlayerBirthDate(player, parse);
										if (currentDate.compareTo(playerBirthDate) == 0) {
											logger.info("Date is "+player.getPlayerName()+"'s birthday");
											player.increaseAge(league, days, statDecayChance);
										}
										else {
											logger.info("Date is not "+player.getPlayerName()+"'s birthday");
											player.decreaseInjuredDaysForPlayer(days);
										}

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
		logger.info("Entered checkForRetirement()");
		boolean isRetired;
		List<Player> retiredPlayers = new ArrayList<Player>();
		List<Player> freeAgents = (List<Player>) league.getFreeAgents();
		List<IConference> conferences = league.getConferences();

		Iterator<Player> freeAgentsItr = freeAgents.iterator();

		while (freeAgentsItr.hasNext()) {
			Player freeAgent = freeAgentsItr.next();
			isRetired = freeAgent.isIsRetired();
			if (isRetired) {
				logger.info("Freeagent "+freeAgent.getPlayerName()+" is retired");
				retiredPlayers.add(freeAgent);
				freeAgentsItr.remove();
			}
		}

		if (conferences.size() > 0) {
			logger.info("Conferences exists in"+league.getLeagueName()+", so looping over them");
			for (IConference conference : conferences) {
				List<Division> divisions = conference.getDivisions();
				if (divisions.size() > 0) {
					logger.info("Divisions exists in"+conference.getConferenceName()+", so looping over them");
					for (Division division : divisions) {
						List<Team> teams = division.getTeams();
						if (teams.size() > 0) {
							logger.info("Teams exists in "+division.getDivisionName()+", so looping over them");
							for (Team team : teams) {
								List<Player> players = team.getPlayers();
								if (players.size() > 0) {
									logger.info("Players exists in "+team.getTeamName()+", so looping over them");
									for (Player player : players) {
										isRetired = player.isIsRetired();
										if (isRetired) {
											logger.info("Players "+player.getPlayerName()+" is retired");
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
	
	private Date getPlayerBirthDate(Player player, IParse parse) {
		logger.info("Entered getPlayerBirthDate()");
		int playerBirthYear = player.getBirthYear();
		int playerBirthMonth = player.getBirthMonth();
		int playerBirthDay = player.getBirthDay();
		String birthDate = Integer.toString(playerBirthDay) + "/" + Integer.toString(playerBirthMonth) +  "/"
				+ Integer.toString(playerBirthYear);
		Date playerBirthDate = parse.stringToDate(birthDate);
		return playerBirthDate;

	}


}
