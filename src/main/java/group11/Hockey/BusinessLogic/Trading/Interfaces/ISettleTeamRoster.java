package group11.Hockey.BusinessLogic.Trading.Interfaces;

import group11.Hockey.BusinessLogic.IConstantSupplier;
import group11.Hockey.BusinessLogic.Positions;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

import java.util.List;

public interface ISettleTeamRoster {
    void settleTeam(Team team) throws Exception;
    void hirePlayer(League league, List<Player> playerList, Positions playerPosition) throws Exception;
    void dropPlayer(League league, List<Player> playerList, Positions playerPosition);
    void hirePlayerUser(League league, List<Player> playerList, Positions playerPosition) throws Exception;
    void dropPlayerUser(League league, List<Player> playerList, Positions playerPosition);
}
