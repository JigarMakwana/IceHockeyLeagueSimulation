package group11.Hockey.BusinessLogic.Trading.Interfaces;

import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

import java.util.List;

public interface ITradeCharter {
    List<Player> getOfferedPlayerList();
    List<Player> getRequestedPlayerList();
    Team getOfferingTeam();
    Team getRequestedTeam();
    boolean isDraftTrade();
    boolean isCharterValid();
}
