package group11.Hockey.BusinessLogic.Trading.Interfaces;

import java.util.List;

import group11.Hockey.BusinessLogic.Positions;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;

public interface ISettleTeamRoster {
	void settleTeam(ITeam team) throws Exception;

	void hirePlayer(ILeague league, List<IPlayer> playerList, Positions playerPosition) throws Exception;

	void dropPlayer(ILeague league, List<IPlayer> playerList, Positions playerPosition);

	void hirePlayerUser(ILeague league, List<IPlayer> playerList, Positions playerPosition) throws Exception;

	void dropPlayerUser(ILeague league, List<IPlayer> playerList, Positions playerPosition);
}
