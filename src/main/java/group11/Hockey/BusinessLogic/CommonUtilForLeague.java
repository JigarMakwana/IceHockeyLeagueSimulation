package group11.Hockey.BusinessLogic;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.models.Coach;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

abstract public class CommonUtilForLeague {

	private static Logger logger = LogManager.getLogger(CommonUtilForLeague.class);
	
	public void addGeneralMangerToTeam(Team team, String generalMangerName, League league) {
		logger.info("Entered addGeneralMangerToTeam()");
//		team.setGeneralManager(generalMangerName);
//		List<GeneralManager> generalManagers = league.getGeneralManagers();
//		for (GeneralManager gm : generalManagers) {
//			if (gm.getName() != null && gm.getName().equalsIgnoreCase(generalMangerName)) {
//				generalManagers.remove(gm);
//				break;
//			}
//		}
	}

	public void addCoachToTeam(Team team, String coachName, League league) {
		logger.info("Entered addCoachToTeam()");
		Coach coach = new Coach();
		coach.setName(coachName);
		team.setHeadCoach(coach);
		List<Coach> coaches = league.getCoaches();
		for (Coach ch : coaches) {
			if (ch.getName() != null && ch.getName().equalsIgnoreCase(coach.getName())) {
				logger.info("Coach "+ch.getName()+" is added to team "+team.getTeamName());
				coaches.remove(ch);
				break;
			}
		}
	}

	public void removeFreeAgentsFromLeague(League league, List<Player> freeAgents) {
		logger.info("Entered removeFreeAgentsFromLeague()");
		List<Player> listOfFreeAgentsInLeague = (List<Player>) league.getFreeAgents();
		Iterator<Player> interator = listOfFreeAgentsInLeague.iterator();
		while (interator.hasNext()) {
		    Player pl = interator.next();
			for (Player freeAgent : freeAgents) {
				if (freeAgent.toString().equalsIgnoreCase(pl.toString())) {
					logger.info("Freeagent "+freeAgent.getPlayerName()+" is removed from "+league.getLeagueName());
					interator.remove();
				}
			}
		}
	}
}
