package group11.Hockey.db;

import group11.Hockey.BusinessLogic.models.GameplayConfig;
import group11.Hockey.BusinessLogic.models.IAging;
import group11.Hockey.BusinessLogic.models.IGameResolver;
import group11.Hockey.BusinessLogic.models.IInjuries;
import group11.Hockey.BusinessLogic.models.ITrading;
import group11.Hockey.BusinessLogic.models.ITraining;

public interface IGameplayConfigDb {
	public GameplayConfig loadGameConfig(String leagueName);

	public boolean insertGameplayConfig(IAging aging, IGameResolver gameResolver, IInjuries injuries,
			ITraining training, ITrading trading, String leagueName);
}
