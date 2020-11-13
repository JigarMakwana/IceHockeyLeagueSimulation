package group11.Hockey.BusinessLogic;

import java.util.List;
import java.util.Random;

import group11.Hockey.BusinessLogic.models.Aging;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.GameplayConfig;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

public abstract class RetirePlayer extends StateMachineState {

	public boolean checkForRetirement(ILeague league, float age) {
		int likelihoodOfRetirement = getLikelihoodOfRetirement(league, age);
		boolean isRetired = new Random().nextInt(likelihoodOfRetirement) == likelihoodOfRetirement - 1;
		return isRetired;
	}

	private int getLikelihoodOfRetirement(ILeague league, float age) {
		GameplayConfig gameplayConfig = league.getGamePlayConfig();
		Aging ageDetails = gameplayConfig.getAging();
		int averageRetirementAge = ageDetails.getAverageRetirementAge();
		int maximumAge = ageDetails.getMaximumAge();
		int likelihoodOfRetirement = 1;
		float playerAge = age;
		if (age >= maximumAge) {
			return likelihoodOfRetirement;
		} else if (averageRetirementAge >= playerAge) {
			likelihoodOfRetirement = (int) (maximumAge - playerAge) * 1000;
		} else if (averageRetirementAge < playerAge) {
			likelihoodOfRetirement = (int) (maximumAge - playerAge) * 750;
		}
		return likelihoodOfRetirement;
	}

	public void retireAndReplacePlayer(ILeague league) {
		List<Player> retiredPlayerList = league.getRetiredPlayers();
		List<Conference> conferences = league.getConferences();

		if (conferences.size() > 0) {
			for (Conference conference : conferences) {
				List<Division> divisions = conference.getDivisions();
				if (divisions.size() > 0) {
					for (Division division : divisions) {
						List<Team> teams = division.getTeams();
						if (teams.size() > 0) {
							for (Team team : teams) {
								List<Player> playersList = team.getPlayers();
								if (retiredPlayerList.size() > 0) {
									for (Player retiredPlayer : retiredPlayerList) {
										boolean removed = playersList.remove(retiredPlayer);
										if (removed) {
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
