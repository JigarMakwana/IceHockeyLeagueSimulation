/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.LeagueSimulation;

import java.util.HashMap;

import group11.Hockey.BusinessLogic.models.Team;

public interface IPlayoffSchedule {
	public HashMap<String, HashMap<Team, Team>> generatePlayoffScheduleRound1(String date);
	public HashMap<String, HashMap<Team, Team>> generatePlayoffScheduleRemainingRounds(String date);
}
