/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import java.util.List;
import java.util.Random;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.IAging;
import group11.Hockey.BusinessLogic.models.IGameplayConfig;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

public abstract class RetirePlayer extends StateMachineState {
	private static Logger logger = LogManager.getLogger(RetirePlayer.class);
	public boolean checkForRetirement(ILeague league, float age) {
		logger.info("Entered checkForRetirement()");
		int likelihoodOfRetirement = getLikelihoodOfRetirement(league, age);
		boolean isRetired = new Random().nextInt(likelihoodOfRetirement) == likelihoodOfRetirement - 1;
		return isRetired;
	}

	private int getLikelihoodOfRetirement(ILeague league, float age) {
		logger.info("Entered getLikelihoodOfRetirement()");
		IGameplayConfig gameplayConfig = league.getGamePlayConfig();
		IAging ageDetails = gameplayConfig.getAging();
		int averageRetirementAge = ageDetails.getAverageRetirementAge();
		int maximumAge = ageDetails.getMaximumAge();
		int likelihoodOfRetirement = 1;
		float playerAge = age;
		if (age >= maximumAge) {
			logger.info("Age is greater than or equal to maximum age");
			return likelihoodOfRetirement;
		} else if (averageRetirementAge >= playerAge) {
			logger.info("Age is less than or equal to average age");
			likelihoodOfRetirement = (int) (maximumAge - playerAge) * 1000;
		} else if (averageRetirementAge < playerAge) {
			logger.info("Age is greater than average age");
			likelihoodOfRetirement = (int) (maximumAge - playerAge) * 750;
		}
		return likelihoodOfRetirement;
	}

	public void retireAndReplacePlayer(ILeague league) {
		logger.info("Entered retireAndReplacePlayer()");
		List<Player> retiredPlayerList = league.getRetiredPlayers();
		List<Conference> conferences = league.getConferences();

		if (conferences.size() > 0) {
			logger.info("Conferences exists in"+league.getLeagueName()+", so looping over them");
			for (Conference conference : conferences) {
				List<Division> divisions = conference.getDivisions();
				if (divisions.size() > 0) {
					logger.info("Divisions exists in"+conference.getConferenceName()+", so looping over them");
					for (Division division : divisions) {
						List<Team> teams = division.getTeams();
						if (teams.size() > 0) {
							logger.info("Teams exists in "+division.getDivisionName()+", so looping over them");
							for (Team team : teams) {
								List<Player> playersList = team.getPlayers();
								if (retiredPlayerList.size() > 0) {
									logger.info("Retirement Players exists in "+team.getTeamName()+", so looping over them");
									for (Player retiredPlayer : retiredPlayerList) {
										boolean removed = playersList.remove(retiredPlayer);
										if (removed) {
											logger.info("Retirement Players replaced with freeagents");
											retiredPlayer.replacePlayerWithFreeAgent(league, playersList);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

}
