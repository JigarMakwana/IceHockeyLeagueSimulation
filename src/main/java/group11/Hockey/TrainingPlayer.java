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
			for(Player player: playerList) {
				float skatingSkill = player.getSkating();
			}
	}
	
	public boolean comapreCoachStatAnd(float coachStatValue) {
			//boolean coach
			float randomValue = (float)Math.random();
			if(randomValue < coachStatValue) {
				
			}
			return true;
	}

}
