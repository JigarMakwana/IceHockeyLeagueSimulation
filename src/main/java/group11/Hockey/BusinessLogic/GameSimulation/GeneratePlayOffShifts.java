/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.GameSimulation;

import java.util.ArrayList;
import java.util.List;

import group11.Hockey.BusinessLogic.models.Player;

public class GeneratePlayOffShifts extends GenerateShiftsTemplate {

	private List<Player> team;

	public GeneratePlayOffShifts(List<Player> team) {
		super(team);
		this.team = team;
	}

	@Override
	public void generateGoalieShift(String position) {
		int shift = 0;
		List<Player> goalies = new ArrayList<>();
		for (Player player : team) {
			if (player.getPosition().equalsIgnoreCase(position)) {
				goalies.add(player);
			}
		}

		if (goalies.size() < 2) {
			// TODO: throw exception
			goalies.add(goalies.get(0));
		}
		int index = bestGoalieIndex(goalies);
		do {
			shifts[shift].add(goalies.get(index));
			shift++;
		} while (shift < appConfiguration.shifts);

	}

	private int bestGoalieIndex(List<Player> goalies) {
		float player1_savings = goalies.get(0).getSaving();
		float player2_savings = goalies.get(1).getSaving();

		if (player1_savings > player2_savings) {
			return 0;
		} else {
			return 1;
		}
	}

}
