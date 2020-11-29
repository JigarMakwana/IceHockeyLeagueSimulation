/*
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeCharter;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

import java.util.List;

public class TradeCharter implements ITradeCharter {
    private Team offeringTeam;
    private List<Player> offeredPlayerList;
    private Team requestedTeam;
    private List<Player> requestedPlayerList;
    private int draftRoundIdx;


    public TradeCharter( Team offeringTeam, List<Player> offeredPlayerList,
                         Team requestedTeam, List<Player> requestedPlayerList, int draftRoundIdx){
        this.offeringTeam = offeringTeam;
        this.offeredPlayerList = offeredPlayerList;
        this.requestedTeam = requestedTeam;
        this.requestedPlayerList = requestedPlayerList;
        this.draftRoundIdx = draftRoundIdx;
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
    public int getDraftRoundIdx() {
        return draftRoundIdx;
    }

    @Override
    public boolean isCharterValid() {
        if(offeringTeam == null || requestedTeam == null ||
                offeredPlayerList == null || requestedPlayerList == null ){
            return false;
        }
        return true;
    }

    @Override
    public boolean isDraftTradeCharter() {
        if(offeringTeam == null || requestedTeam == null ||
                offeredPlayerList == null || requestedPlayerList == null ){
            return false;
        } else if(offeringTeam == null) {
            return true;
        }
        return false;
    }
}
