package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeCharter;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TradeCharter implements ITradeCharter {
    private Team offeringTeam;
    private List<Player> offeredPlayerList;
    private Team requestedTeam;
    private List<Player> requestedPlayerList;
    private int draftRoundIdx;
    private static Logger logger = LogManager.getLogger(TradeCharter.class);


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
    	logger.info("Entered getOfferedPlayerList()");
        return offeredPlayerList;
    }

    @Override
    public List<Player> getRequestedPlayerList() {
    	logger.info("Entered getRequestedPlayerList()");
        return requestedPlayerList;
    }

    @Override
    public Team getOfferingTeam() {
    	logger.info("Entered getOfferingTeam()");
        return offeringTeam;
    }

    @Override
    public Team getRequestedTeam() {
    	logger.info("Entered getRequestedTeam()");
        return requestedTeam;
    }

    @Override
    public int getDraftRoundIdx() {
    	logger.info("Entered getDraftRoundIdx()");
        return draftRoundIdx;
    }

    @Override
    public boolean isCharterValid() {
    	logger.info("Entered isCharterValid()");
        if(offeringTeam == null || requestedTeam == null ||
                offeredPlayerList == null || requestedPlayerList == null ){
        	logger.warn("Charter is null");
            return false;
        }
        return true;
    }
}
