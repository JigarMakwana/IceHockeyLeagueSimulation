/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.GameSimulation;

import java.util.ArrayList;
import java.util.List;

import group11.Hockey.BusinessLogic.models.Player;

public class GenerateShifts extends GenerateShiftsTemplate {
	private List<Player> team;

	public GenerateShifts(List<Player> team) {
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
			System.out.println();
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
