package group11.Hockey.BusinessLogic.GameSimulation;

import java.util.ArrayList;
import java.util.List;

import group11.Hockey.BusinessLogic.Positions;
import group11.Hockey.BusinessLogic.models.Player;

public abstract class GenerateShiftsTemplate {
	private List<Player> team;
	List<Player> playersOnIce = new ArrayList<>();
	List<Player>[] shifts = new List[appConfiguration.shifts];

	public GenerateShiftsTemplate(List<Player> team) {
		super();
		this.team = team;
	}

	public List<Player>[] getShifts() {

		for (int i = 0; i < appConfiguration.shifts; i++) {
			shifts[i] = new ArrayList<>();
		}
		generateShifts(appConfiguration.forwardMen, Positions.FORWARD.toString());
		generateShifts(appConfiguration.defenceMen, Positions.DEFENSE.toString());
		generateGoalieShift(Positions.GOALIE.toString());
		return shifts;
	}

	public void generateShifts(int limtOfPlayers, String position) {

		int shift = 0;
		do {
			for (Player player : team) {
				if (player.getPosition().equalsIgnoreCase(position)) {
					playersOnIce.add(player);
				}
				if (playersOnIce.size() == limtOfPlayers) {
					shifts[shift].addAll(playersOnIce);
					playersOnIce = new ArrayList<>();
					shift++;
				}
				if (shift == appConfiguration.shifts) {
					break;
				}
			}
		} while (shift < appConfiguration.shifts);
	}

	public abstract void generateGoalieShift(String position);
}
