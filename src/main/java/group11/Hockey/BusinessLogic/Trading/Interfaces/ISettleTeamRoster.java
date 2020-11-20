package group11.Hockey.BusinessLogic.Trading.Interfaces;

import group11.Hockey.BusinessLogic.IConstantSupplier;
import group11.Hockey.BusinessLogic.Positions;
import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

import java.util.List;

public interface ISettleTeamRoster {
    void settleTeam(Team team) throws Exception;
    void hirePlayer(ILeague league, List<Player> playerList, Positions playerPosition) throws Exception;
    void dropPlayer(ILeague league, List<Player> playerList, Positions playerPosition);
    void hirePlayerUser(ILeague league, List<Player> playerList, Positions playerPosition) throws Exception;
    void dropPlayerUser(ILeague league, List<Player> playerList, Positions playerPosition);
}
