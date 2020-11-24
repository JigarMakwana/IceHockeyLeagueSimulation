package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeCharter;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;

import java.util.List;

public class TradeCharter implements ITradeCharter {
    private ITeam offeringTeam;
    private List<IPlayer> offeredPlayerList;
    private ITeam requestedTeam;
    private List<IPlayer> requestedPlayerList;


    public TradeCharter( ITeam offeringTeam, List<IPlayer> offeredPlayerList,
                         ITeam requestedTeam, List<IPlayer> requestedPlayerList){
        this.offeringTeam = offeringTeam;
        this.offeredPlayerList = offeredPlayerList;
        this.requestedTeam = requestedTeam;
        this.requestedPlayerList = requestedPlayerList;
    }

    @Override
    public List<IPlayer> getOfferedPlayerList() {
        return offeredPlayerList;
    }

    @Override
    public List<IPlayer> getRequestedPlayerList() {
        return requestedPlayerList;
    }

    @Override
    public ITeam getOfferingTeam() {
        return offeringTeam;
    }

    @Override
    public ITeam getRequestedTeam() {
        return requestedTeam;
    }
}
