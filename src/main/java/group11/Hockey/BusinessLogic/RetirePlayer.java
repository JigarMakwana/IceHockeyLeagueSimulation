/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.IAging;
import group11.Hockey.BusinessLogic.models.IConference;
import group11.Hockey.BusinessLogic.models.IGameplayConfig;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;

public abstract class RetirePlayer extends StateMachineState {
	private static Logger logger = LogManager.getLogger(RetirePlayer.class);

	public boolean checkForRetirement(ILeague league, float age) {
		int likelihoodOfRetirement = getLikelihoodOfRetirement(league, age);
		boolean isRetired = DefaultHockeyFactory.makeRandomNumberGenerator()
				.generateRandomInt(likelihoodOfRetirement) == likelihoodOfRetirement - 1;
		return isRetired;
	}

	private int getLikelihoodOfRetirement(ILeague league, float age) {
		IGameplayConfig gameplayConfig = league.getGamePlayConfig();
		IAging ageDetails = gameplayConfig.getAging();
		int averageRetirementAge = ageDetails.getAverageRetirementAge();
		int maximumAge = ageDetails.getMaximumAge();
		int likelihoodOfRetirement = 1;
		float playerAge = age;
		if (age >= maximumAge) {
			return likelihoodOfRetirement;
		} else if (averageRetirementAge >= playerAge) {
			likelihoodOfRetirement = (int) (maximumAge - playerAge)
					* BusinessConstants.Likelihood_Of_Retirement_Low.getIntValue();
		} else if (averageRetirementAge < playerAge) {
			likelihoodOfRetirement = (int) (maximumAge - playerAge)
					* BusinessConstants.Likelihood_Of_Retirement_Low.getIntValue();
		}
		return likelihoodOfRetirement;
	}

	public void retireAndReplacePlayer(ILeague league) {
		List<IPlayer> retiredPlayerList = league.getRetiredPlayers();
		List<IConference> conferences = league.getConferences();

		if (conferences.size() > 0) {
			for (IConference conference : conferences) {
				List<Division> divisions = conference.getDivisions();
				if (divisions.size() > 0) {
					for (Division division : divisions) {
						List<ITeam> teams = division.getTeams();
						if (teams.size() > 0) {
							for (ITeam team : teams) {
								List<IPlayer> playersList = team.getPlayers();
								if (retiredPlayerList.size() > 0) {
									for (IPlayer retiredPlayer : retiredPlayerList) {
										boolean removed = playersList.remove(retiredPlayer);
										if (removed) {
											logger.warn("Player is retired and replaced with FreeAgents");
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
