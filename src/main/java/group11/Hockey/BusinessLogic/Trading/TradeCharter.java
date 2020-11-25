package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeCharter;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;

import java.util.List;

public class TradeCharter implements ITradeCharter {
    private Team offeringTeam;
    private List<Player> offeredPlayerList;
    private Team requestedTeam;
    private List<Player> requestedPlayerList;


    public TradeCharter( Team offeringTeam, List<Player> offeredPlayerList,
                         Team requestedTeam, List<Player> requestedPlayerList){
        this.offeringTeam = offeringTeam;
        this.offeredPlayerList = offeredPlayerList;
        this.requestedTeam = requestedTeam;
        this.requestedPlayerList = requestedPlayerList;
    }

    @Override
    public List<Player> getOfferedPlayerList() {
        return offeredPlayerList;
    }

    @Override
    public List<Player> getRequestedPlayerList() {
        return requestedPlayerList;
    }

    @Override
    public Team getOfferingTeam() {
        return offeringTeam;
    }

    @Override
    public Team getRequestedTeam() {
        return requestedTeam;
    }

    @Override
    public boolean isCharterValid() {
        if(offeringTeam == null || requestedTeam == null ||
                offeredPlayerList == null || requestedPlayerList == null ){
            return false;
        }
        return true;
    }
}
