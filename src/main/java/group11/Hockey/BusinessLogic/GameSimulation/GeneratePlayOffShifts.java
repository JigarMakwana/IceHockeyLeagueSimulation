/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.GameSimulation;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.models.Player;

public class GeneratePlayOffShifts extends GenerateShiftsTemplate {

	private static Logger logger = LogManager.getLogger(GeneratePlayOffShifts.class);
	
	private List<Player> team;

	public GeneratePlayOffShifts(List<Player> team) {
		super(team);
		this.team = team;
	}

	@Override
	public void generateGoalieShift(String position) {
		logger.info("Entered generateGoalieShift()");
		int shift = 0;
		List<Player> goalies = new ArrayList<>();
		for (Player player : team) {
			if (player.getPosition().equalsIgnoreCase(position)) {
				goalies.add(player);
			}
		}

		if (goalies.size() < 2) {
			goalies.add(goalies.get(0));
		}
		int index = bestGoalieIndex(goalies);
		do {
			shifts[shift].add(goalies.get(index));
			shift++;
		} while (shift < appConfiguration.shifts);

	}

	private int bestGoalieIndex(List<Player> goalies) {
		logger.info("Entered bestGoalieIndex()");
		float player1_savings = goalies.get(0).getSaving();
		float player2_savings = goalies.get(1).getSaving();

		if (player1_savings > player2_savings) {
			return 0;
		} else {
			return 1;
		}
	}

}
