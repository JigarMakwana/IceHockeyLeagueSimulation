package group11.Hockey.db;

import group11.Hockey.BusinessLogic.models.Aging;
import group11.Hockey.BusinessLogic.models.GameResolver;
import group11.Hockey.BusinessLogic.models.GameplayConfig;
import group11.Hockey.BusinessLogic.models.Injuries;
import group11.Hockey.BusinessLogic.models.Trading;
import group11.Hockey.BusinessLogic.models.Training;

public interface IGameplayConfigDb {
	public GameplayConfig loadGameConfig(String leagueName);

	public boolean insertGameplayConfig(Aging aging, GameResolver gameResolver, Injuries injuries, Training training,
			Trading trading, String leagueName);
}
