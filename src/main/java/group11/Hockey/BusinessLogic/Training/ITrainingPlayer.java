package group11.Hockey.BusinessLogic.Training;

import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;

public interface ITrainingPlayer {
	public boolean comapreCoachStat(float coachStatValue) ;
	public void changePlayerSkatingSkill(IPlayer player, float coachSkatingStatValue, ILeague league);
	public void changePlayerShootingSkill(IPlayer player, float coachShootingStatValue, ILeague league);
	public void changePlayerCheckingSkill(IPlayer player, float coachCheckingStatValue, ILeague league);
	public void changePlayerSavingSkill(IPlayer player, float coachSavingStatValue, ILeague league);
}
