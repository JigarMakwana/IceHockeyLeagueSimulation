package group11.Hockey.BusinessLogic;

import java.util.List;

import group11.Hockey.BusinessLogic.models.Coach;
import group11.Hockey.BusinessLogic.models.Conference;
import group11.Hockey.BusinessLogic.models.Division;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

public class TrainingPlayer implements ITrainingPlayer {

	@Override
	public void trainPlayer(League league) {
		List<Conference> conferenceList = league.getConferences();
		for(Conference conference : conferenceList) {
			List<Division> divisionList = conference.getDivisions();
			for(Division division: divisionList) {
				List<Team> teamList = division.getTeams();
				for(Team team: teamList) {
					Coach headCoach = team.getHeadCoach();
					List<Player> playerList = team.getPlayers();
					for (Player player : playerList) {
						changePlayerSkatingSkill(player, headCoach.getSkating(),league );
						changePlayerShootingSkill(player, headCoach.getShooting(), league);
						changePlayerCheckingSkill(player, headCoach.getChecking(), league);
						changePlayerSavingSkill(player, headCoach.getChecking(), league);
					}
				}
			}
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

	public void changePlayerSkatingSkill(Player player, float coachSkatingStatValue, League league) {
		if (player.isInjured() == false) {
			boolean coachStat = comapreCoachStat(coachSkatingStatValue);
			if (coachStat) {
				float skatingSkill = player.getSkating() + 1;
				player.setSkating(skatingSkill);
			} else {
				player.checkInjury(league);
			}
		}

	}

	public void changePlayerShootingSkill(Player player, float coachShootingStatValue, League league) {
		if (player.isInjured() == false) {
			boolean coachStat = comapreCoachStat(coachShootingStatValue);
			if (coachStat) {
				float shootingSkill = player.getShooting() + 1;
				player.setShooting(shootingSkill);
			} else {
				player.checkInjury(league);
			}
		}

	}

	public void changePlayerCheckingSkill(Player player, float coachCheckingStatValue, League league) {
		if (player.isInjured() == false) {
			boolean coachStat = comapreCoachStat(coachCheckingStatValue);
			if (coachStat) {
				float checkingSkill = player.getChecking() + 1;
				player.setChecking(checkingSkill);
			} else {
				player.checkInjury(league); 
			}
		}
	}

	public void changePlayerSavingSkill(Player player, float coachSavingStatValue, League league) {
		if (player.isInjured() == false) {
			boolean coachStat = comapreCoachStat(coachSavingStatValue);
			if (coachStat) {
				float savingSkill = player.getSaving() + 1;
				player.setSaving(savingSkill);
			} else {
				player.checkInjury(league);
			}
		}

	}

}
