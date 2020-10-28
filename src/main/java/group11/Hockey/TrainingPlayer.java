package group11.Hockey;

import java.util.List;

import group11.Hockey.models.Coach;
import group11.Hockey.models.Player;
import group11.Hockey.models.Team;

public class TrainingPlayer implements ITrainingPlayer {

	@Override
	public void trainPlayer(Team team) {
		List<Player> playerList = team.getPlayers();
		Coach headCoach = team.getHeadCoach();
		for (Player player : playerList) {
			changePlayerSkatingSkill(player, headCoach.getSkating());
			changePlayerShootingSkill(player, headCoach.getShooting());
			changePlayerCheckingSkill(player, headCoach.getChecking());
			changePlayerSavingSkill(player, headCoach.getChecking());
		}
	}

	public boolean comapreCoachStat(float coachStatValue) {
		boolean coachStat = false;
		float randomValue = (float) Math.random();
		if (randomValue < coachStatValue) {
			coachStat = true;
		}
		return coachStat;
	}

	public void changePlayerSkatingSkill(Player player, float coachSkatingStatValue) {
		if (player.isInjured() == false) {
			boolean coachStat = comapreCoachStat(coachSkatingStatValue);
			if (coachStat) {
				float skatingSkill = player.getSkating() + 1;
				player.setSkating(skatingSkill);
			} else {
				// call injury check
			}
		}

	}

	public void changePlayerShootingSkill(Player player, float coachShootingStatValue) {
		if (player.isInjured() == false) {
			boolean coachStat = comapreCoachStat(coachShootingStatValue);
			if (coachStat) {
				float shootingSkill = player.getShooting() + 1;
				player.setShooting(shootingSkill);
			} else {
				// call injury check
			}
		}

	}

	public void changePlayerCheckingSkill(Player player, float coachCheckingStatValue) {
		if (player.isInjured() == false) {
			boolean coachStat = comapreCoachStat(coachCheckingStatValue);
			if (coachStat) {
				float checkingSkill = player.getChecking() + 1;
				player.setChecking(checkingSkill);
			} else {
				// call injury check
			}
		}
	}

	public void changePlayerSavingSkill(Player player, float coachSavingStatValue) {
		if (player.isInjured() == false) {
			boolean coachStat = comapreCoachStat(coachSavingStatValue);
			if (coachStat) {
				float savingSkill = player.getSaving() + 1;
				player.setSaving(savingSkill);
			} else {
				// call injury check
			}
		}

	}

}
