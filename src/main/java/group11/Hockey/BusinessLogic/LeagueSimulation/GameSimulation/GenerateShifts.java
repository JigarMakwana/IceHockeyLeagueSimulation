/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.models.Player;

public class GenerateShifts extends GenerateShiftsTemplate {
	private List<Player> team;
	private static Logger logger = LogManager.getLogger(GenerateShifts.class);

	public GenerateShifts(List<Player> team) {
		super(team);
		this.team = team;
	}

	@Override
	public void generateGoalieShift(String position) throws Exception {
		int shift = 0;
		List<Player> goalies = new ArrayList<>();
		for (Player player : team) {
			if (player.getPosition().equalsIgnoreCase(position)) {
				goalies.add(player);
			}
		}

		if (goalies.size() < 2) {
			logger.warn("no sufficient goalies");
			throw new Exception("no sufficient goalies");
		}
		do {
			if (shift <= appConfiguration.twoPeriodsTime) {
				shifts[shift].add(goalies.get(0));
			} else {
				shifts[shift].add(goalies.get(1));
			}
			shift++;
		} while (shift < appConfiguration.shifts);

	}
}
