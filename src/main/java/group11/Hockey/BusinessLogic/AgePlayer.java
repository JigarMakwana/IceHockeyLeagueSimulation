/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import group11.Hockey.InputOutput.ICommandLineInput;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.LeagueSimulation.IParse;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.ITimeLine;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.InputOutput.IDisplay;
import group11.Hockey.db.League.ILeagueDb;

public class AgePlayer extends RetirePlayer {

	ILeague league;
	int days;
	ILeagueDb leagueDb;
	IDisplay display;
	private ICommandLineInput commandLineInput;
	private IValidations validation;
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

	public AgePlayer(ILeague league, ILeagueDb leagueDb, IDisplay display, ICommandLineInput commandLineInput,
			IValidations validation) {
		this.league = league;
		this.leagueDb = leagueDb;
		this.display = display;
		this.commandLineInput = commandLineInput;
		this.validation = validation;
	}

	@Override
	public StateMachineState startState() {
		agePlayers();
		IParse parse = DefaultHockeyFactory.makeParse();
		ITimeLine timeLine = league.getTimeLine();
		String currentDate = timeLine.getCurrentDate();
		Date stanleyEndDateTime = timeLine.getStanleyEndDateTime();
		List<ITeam> qualifiedTeams = league.getQualifiedTeams();
		Date dateTime = parse.stringToDate(currentDate);
		if ((dateTime.equals(stanleyEndDateTime)) || (qualifiedTeams.size() == 1)) {
			logger.info("Move to DraftPlayer State");
			return DefaultHockeyFactory.makeDraftPlayer(league, leagueDb, display);
		} else {
			logger.info("Date is not end of stanley playoffs");
			return DefaultHockeyFactory.makeAdvanceTime(league, leagueDb, display, commandLineInput, validation);
		}
	}

	public void agePlayers() {
		IParse parse = DefaultHockeyFactory.makeParse();
		Date currentDate = parse.stringToDate(league.getTimeLine().getCurrentDate());

		List<IPlayer> freeAgents = (List<IPlayer>) league.getFreeAgents();
		List<IConference> conferences = league.getConferences();
		float statDecayChance = league.getGamePlayConfig().getAging().getStatDecayChance();
		if (freeAgents.size() > 0) {
			for (IPlayer freeAgent : freeAgents) {
				Date playerBirthDate = getPlayerBirthDate(freeAgent, parse);
				if (currentDate.compareTo(playerBirthDate) == 0) {
					freeAgent.increaseAge(league, days, statDecayChance);
				} else {
					freeAgent.decreaseInjuredDaysForPlayer(days);
				}
			}
		}

		if (conferences.size() > 0) {
			for (IConference conference : conferences) {
				List<Division> divisions = conference.getDivisions();
				if (divisions.size() > 0) {
					for (Division division : divisions) {
						List<ITeam> teams = division.getTeams();
						if (teams.size() > 0) {
							for (ITeam team : teams) {
								List<IPlayer> players = team.getPlayers();
								if (players.size() > 0) {
									for (IPlayer player : players) {
										Date playerBirthDate = getPlayerBirthDate(player, parse);
										if (currentDate.compareTo(playerBirthDate) == 0) {
											player.increaseAge(league, days, statDecayChance);
										} else {
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
		boolean isRetired;
		List<IPlayer> retiredPlayers = new ArrayList<>();
		List<Player> freeAgents = (List<Player>) league.getFreeAgents();
		List<IConference> conferences = league.getConferences();

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
			for (IConference conference : conferences) {
				List<Division> divisions = conference.getDivisions();
				if (divisions.size() > 0) {
					for (Division division : divisions) {
						List<ITeam> teams = division.getTeams();
						if (teams.size() > 0) {
							for (ITeam team : teams) {
								List<IPlayer> players = team.getPlayers();
								if (players.size() > 0) {
									for (IPlayer player : players) {
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
		List<IPlayer> existingRetiredPlayers = league.getRetiredPlayers();
		existingRetiredPlayers.addAll(retiredPlayers);
		league.setRetiredPlayers(existingRetiredPlayers);
		retireAndReplacePlayer(league);
	}

	private Date getPlayerBirthDate(IPlayer player, IParse parse) {

		int playerBirthYear = player.getBirthYear();
		int playerBirthMonth = player.getBirthMonth();
		int playerBirthDay = player.getBirthDay();
		String birthDate = Integer.toString(playerBirthDay) + "/" + Integer.toString(playerBirthMonth) + "/"
				+ Integer.toString(playerBirthYear);
		Date playerBirthDate = parse.stringToDate(birthDate);
		return playerBirthDate;

	}

}
