package group11.Hockey.BusinessLogic.Trading.Interfaces;

import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;

import java.util.List;

public interface ITradeCharter {
    List<IPlayer> getOfferedPlayerList();
    List<IPlayer> getRequestedPlayerList();
    ITeam getOfferingTeam();
    ITeam getRequestedTeam();
}
