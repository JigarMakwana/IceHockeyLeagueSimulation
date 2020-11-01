package group11.Hockey.BusinessLogic;

import java.util.Iterator;
import java.util.List;

import group11.Hockey.BusinessLogic.models.Coach;
import group11.Hockey.BusinessLogic.models.GeneralManager;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

abstract public class CommonUtilForLeague {

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

	public void addCoachToTeam(Team team, String coachName, League league) {
		Coach coach = new Coach();
		coach.setName(coachName);
		team.setHeadCoach(coach);
		List<Coach> coaches = league.getCoaches();
		for (Coach ch : coaches) {
			if (ch.getName() != null && ch.getName().equalsIgnoreCase(coach.getName())) {
				coaches.remove(ch);
				break;
			}
		}
	}

	public void removeFreeAgentsFromLeague(League league, List<Player> freeAgents) {
		List<Player> listOfFreeAgentsInLeague = league.getFreeAgents();
		Iterator<Player> interator = listOfFreeAgentsInLeague.iterator();
		while (interator.hasNext()) {
		    Player pl = interator.next();
			for (Player freeAgent : freeAgents) {
				if (freeAgent.toString().equalsIgnoreCase(pl.toString())) {
					interator.remove();
				}
			}
		}
	}
}
