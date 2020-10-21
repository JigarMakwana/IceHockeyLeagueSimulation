package group11.Hockey;

import java.util.ArrayList;
import java.util.List;

import group11.Hockey.models.Coach;
import group11.Hockey.models.GeneralManager;
import group11.Hockey.models.League;
import group11.Hockey.models.Team;

abstract public class CommonUtil {

	/**
	 * Common method to get the list of available general managers
	 * 
	 * @param league
	 * @return
	 */
	public List<String> getGeneralManager(League league) {
		List<String> generalMangerNames = new ArrayList<String>();
		List<GeneralManager> generalManagers = league.getGeneralManagers();
		for (GeneralManager gm : generalManagers) {
			generalMangerNames.add(gm.getName());
		}
		return generalMangerNames;

	}

	/**
	 * Method to add general manager to team and remove the the manager name from
	 * league object genre
	 * 
	 * @param team
	 * @param generalMangerName
	 * @param league
	 */
	public void addGeneralMangerToTeam(Team team, String generalMangerName, League league) {
		team.setGeneralManager(generalMangerName);
		List<GeneralManager> generalManagers = league.getGeneralManagers();
		for (GeneralManager gm : generalManagers) {
			if (gm.getName() != null && gm.getName().equalsIgnoreCase(generalMangerName)) {
				generalManagers.remove(gm);
				break;
			}
		}
	}

	/**
	 * Method to get coach from coach name
	 * 
	 * @param league
	 * @param coachName
	 * @return
	 */
	public Coach getCoachFromCoachName(League league, String coachName) {
		List<Coach> coachList = league.getCoaches();
		Coach ch = null;
		for (Coach coach : coachList) {
			if (coach != null && coach.getName().equalsIgnoreCase(coachName)) {
				ch = coach;
			}
		}
		return ch;
	}

	/**
	 * Method to add general manager to team and remove the the manager name from
	 * league object genre
	 * 
	 * @param team
	 * @param generalMangerName
	 * @param league
	 */
	public void addCoachToTeam(Team team, Coach coach, League league) {
		team.setHeadCoach(coach);
		List<Coach> coaches = league.getCoaches();
		for (Coach ch : coaches) {
			if (ch.getName() != null && ch.getName().equalsIgnoreCase(coach.getName())) {
				coaches.remove(coach);
				break;
			}
		}
	}
}
