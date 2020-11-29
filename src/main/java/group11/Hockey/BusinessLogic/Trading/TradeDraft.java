/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading;

import java.util.ArrayList;
import java.util.List;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.Enums.PlayerDraft;
import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeCharter;
import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeConfig;
import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeGenerator;
import group11.Hockey.BusinessLogic.Trading.TradingTriplet.Triplet;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import group11.Hockey.InputOutput.IDisplay;


public class TradeDraft implements ITradeGenerator {
    private ITeam offeringTeam;
    private List<IPlayer> weakestPlayerList;
    private ITradeConfig tradingConfig;
    private IDisplay display;
    private IRosterSearch rosterSearch;
    private List<Triplet<ITeam, List<IPlayer>, Float>> tradingTeamsBuffer = new ArrayList<>();


    public TradeDraft(ITeam offeringTeam, ITradeConfig tradingConfig, IDisplay display) {
        this.offeringTeam = offeringTeam;
        this.tradingConfig = tradingConfig;
        this.display = display;
        this.rosterSearch = DefaultHockeyFactory.makeRosterSearch();
        this.weakestPlayerList = new ArrayList<>();
        setWeakestPlayerList();
    }
    private void setWeakestPlayerList(){
        if(null == offeringTeam.getPlayers()){
            return;
        }
        weakestPlayerList = rosterSearch.findWeakestPlayers(offeringTeam.getPlayers(), tradingConfig.getMaxPlayersPerTrade());
    }

    @Override
    public ITradeCharter generateTradeOffer(List<ITeam> eligibleTeamList) {
        display.showMessageOnConsole("\nGenerating draft trading offers for AI Team " + offeringTeam.getTeamName());
        for(int i=PlayerDraft.ROUND_7.getNumVal(); i>=0; i--){
            if(offeringTeam.getTradedPicks().get(i) == false){
                return tradeRound(eligibleTeamList, i);
            }
        }
        return null;
    }

    public ITradeCharter tradeRound(List<ITeam> eligibleTeamList, int roundIdx){
        for (int k = 0; k < eligibleTeamList.size(); k++) {
            if(eligibleTeamList.get(k) == this.offeringTeam) {
                continue;
            } else {
                tradingAlgorithm(eligibleTeamList.get(k));
            }
        }
        if (tradingTeamsBuffer.size() > 0) {
            Triplet<ITeam, List<IPlayer>, Float> tradeTeam = rosterSearch.findStrongestTradeTeam(tradingTeamsBuffer);
            display.displayTradeStatistics(offeringTeam, null, tradeTeam.getFirst(), tradeTeam.getSecond());
            return TradingFactory.makeTradeCharter(offeringTeam, null, tradeTeam.getFirst(), tradeTeam.getSecond(), roundIdx);
        }
        return null;
    }

    private void tradingAlgorithm(ITeam requestedTeam) {
        List<Integer> playerPositionFlag;
        playerPositionFlag = rosterSearch.findPlayerPositions(weakestPlayerList);
        List<IPlayer> requestedPlayerList = requestedTeam.getRoster().getAllPlayerList();
        List<IPlayer> strongestPlayerList = rosterSearch
                .findStrongestPlayers(requestedPlayerList, playerPositionFlag, tradingConfig.getMaxPlayersPerTrade());
        Float playersStrengthSum = rosterSearch.getRosterStrength(strongestPlayerList);
        Triplet<ITeam, List<IPlayer>, Float> teamRequestEntry =
                Triplet.of(requestedTeam, strongestPlayerList, playersStrengthSum);
        tradingTeamsBuffer.add(teamRequestEntry);
    }
}
