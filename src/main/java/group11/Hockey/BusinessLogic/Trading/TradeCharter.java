/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading;

import java.util.List;

import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeCharter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TradeCharter implements ITradeCharter {
    private ITeam offeringTeam;
    private List<IPlayer> offeredPlayerList;
    private ITeam requestedTeam;
    private List<IPlayer> requestedPlayerList;
    private int draftRoundIdx;
    private static Logger logger = LogManager.getLogger(TradeCharter.class);

    public TradeCharter( ITeam offeringTeam, List<IPlayer> offeredPlayerList,
                         ITeam requestedTeam, List<IPlayer> requestedPlayerList, int draftRoundIdx){
        this.offeringTeam = offeringTeam;
        this.offeredPlayerList = offeredPlayerList;
        this.requestedTeam = requestedTeam;
        this.requestedPlayerList = requestedPlayerList;
        this.draftRoundIdx = draftRoundIdx;
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

    @Override
    public int getDraftRoundIdx() {
        return draftRoundIdx;
    }

    @Override
    public boolean isCharterValid() {
        logger.debug("Entered isCharterValid()");
        if(offeringTeam == null || requestedTeam == null ||
                offeredPlayerList == null || requestedPlayerList == null ){
            logger.debug("Charter is not valid");
            return false;
        }
        logger.debug("Charter is valid");
        return true;
    }

    @Override
    public boolean isDraftTradeCharter() {
        logger.debug("Entered isDraftTradeCharter()");
        if(offeringTeam == null || requestedTeam == null ||
                offeredPlayerList == null || requestedPlayerList == null ){
            logger.debug("Charter is not valid");
            return false;
        } else if(offeringTeam == null) {
            logger.debug("DraftCharter is valid");
            return true;
        }
        logger.debug("DraftCharter is not valid");
        return false;
    }
}
