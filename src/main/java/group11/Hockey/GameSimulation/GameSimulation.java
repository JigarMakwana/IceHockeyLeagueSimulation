package group11.Hockey.GameSimulation;

import java.util.ArrayList;
import java.util.List;

import group11.Hockey.BusinessLogic.Positions;
import group11.Hockey.BusinessLogic.StateMachineState;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.Player;

public class GameSimulation {
	private List<Player> team;
	List<Player> playersOnIce = new ArrayList<>();
	List<Player>[] shifts = new List[40];

	public GameSimulation(ILeague league, List<Player> team) {
		super();
		this.team = team;
	}

	public List<Player>[] getShifts() {

		for (int i = 0; i < 40; i++) {
			shifts[i] = new ArrayList<>();
		}
		generateShifts(3, Positions.FORWARD.toString());
		generateShifts(2, Positions.DEFENSE.toString());
		generateShifts(1, Positions.GOALIE.toString());
		return shifts;
	}

	private void generateShifts(int limtOfPlayers, String position) {

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
				if (shift == 40) {
					break;
				}
			}
		} while (shift < 40);
	}

	public int teamSkatingStats() {
		int skatingStat = 0;
		for (Player player : team) {
			skatingStat += player.getSkating();
		}
		return skatingStat;
	}

}
