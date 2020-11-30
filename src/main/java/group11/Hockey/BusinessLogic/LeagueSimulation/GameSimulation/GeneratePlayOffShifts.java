/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.LeagueSimulation.GameSimulation;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.BusinessLogic.models.IPlayer;

public class GeneratePlayOffShifts extends GenerateShiftsTemplate {

	private static Logger logger = LogManager.getLogger(GeneratePlayOffShifts.class);

	private List<IPlayer> team;

	public GeneratePlayOffShifts(List<IPlayer> team) {
		super(team);
		this.team = team;
	}

	@Override
	public void generateGoalieShift(String position) throws Exception {
		int shift = 0;
		List<IPlayer> goalies = new ArrayList<>();
		for (IPlayer player : team) {
			if (player.getPosition().equalsIgnoreCase(position)) {
				goalies.add(player);
			}
		}
		if (goalies.size() < 2) {
			logger.warn("no sufficient goalies");
			throw new Exception("no sufficient goalies");
		}
		int index = bestGoalieIndex(goalies);
		do {
			shifts[shift].add(goalies.get(index));
			shift++;
		} while (shift < appConfiguration.shifts);

	}

	private int bestGoalieIndex(List<IPlayer> goalies) {
		float player1_savings = goalies.get(0).getSaving();
		float player2_savings = goalies.get(1).getSaving();

		if (player1_savings > player2_savings) {
			return 0;
		} else {
			return 1;
		}
	}

}
