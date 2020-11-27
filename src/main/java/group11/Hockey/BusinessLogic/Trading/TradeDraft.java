package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.PlayerDraft;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeCharter;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeDraft;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradingConfig;
import group11.Hockey.BusinessLogic.Triplet;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.InputOutput.IDisplay;

import java.util.*;

public class TradeDraft implements ITradeDraft {
    private static List<Map<Team, Map<Team, List<Boolean>>>> draftTradeTracker;
    private Team offeringTeam;
    private List<Player> weakestPlayerList;
    private ITradingConfig tradingConfig;
    private IDisplay display;
    private IRosterSearch rosterSearch;
    private List<Triplet<Team, List<Player>, Float>> tradingTeamsBuffer = new ArrayList<>();

    public TradeDraft(Team offeringTeam, ITradingConfig tradingConfig, IDisplay display) {
        this.draftTradeTracker = new ArrayList<>();
        this.offeringTeam = offeringTeam;
        this.tradingConfig = tradingConfig;
        this.display = display;
        this.rosterSearch = DefaultHockeyFactory.makeRosterSearch();
        this.weakestPlayerList = new ArrayList<>();
        setWeakestPlayerList();
    }
    private void setWeakestPlayerList(){
        this.weakestPlayerList = this.rosterSearch.findWeakestPlayers(this.offeringTeam.getRoster().getAllPlayerList(),
                this.tradingConfig.getMaxPlayersPerTrade());
    }

    @Override
    public void clearDraftTradeTracker() {
        draftTradeTracker.clear();
    }

    @Override
    public List<Map<Team, Map<Team, List<Boolean>>>> getDraftTradeTracker() {
        return draftTradeTracker;
    }

    public static void updateDraftTradeTracker(Team offeringTeam, Team requestedTeam, int draftRound) {
        if(null == draftTradeTracker){
            addToOuterMap(offeringTeam, addToInnerMap(requestedTeam, draftRound));
        } else {
            for (Map<Team, Map<Team, List<Boolean>>> map : draftTradeTracker) {
                for(Team key: map.keySet()){
                    if (offeringTeam.equals(key)) {
                        for (Map<Team, List<Boolean>> innerMap : map.values()) {
                            for(Team key2: map.keySet()){
                                if (requestedTeam.equals(key2)) {
                                    Map.Entry<Team, List<Boolean>> entry = innerMap.entrySet().iterator().next();
                                    List<Boolean> roundTracker = entry.getValue();
                                    roundTracker.set(draftRound,true);
                                    break;
                                }
                                addToInnerMap(requestedTeam, draftRound);
                            }
                        }
                    }
                }
                addToOuterMap(offeringTeam, addToInnerMap(requestedTeam, draftRound));
            }
        }
    }

    private static Map<Team, List<Boolean>> addToInnerMap(Team requestedTeam, int draftRound){
        List<Boolean> roundTracker = new ArrayList<>(Collections.nCopies(PlayerDraft.PLAYER_DRAFT_ROUNDS.getNumVal(), false));
        roundTracker.set(draftRound,true);
        Map<Team, List<Boolean>> tradeDetail = new HashMap<>();
        tradeDetail.put(requestedTeam,roundTracker);
        return tradeDetail;
    }
    private static void addToOuterMap(Team offeringTeam, Map<Team, List<Boolean>> tradeDetail){
        Map<Team, Map<Team, List<Boolean>>> entry = new HashMap<>();
        entry.put(offeringTeam,tradeDetail);
        draftTradeTracker.add(entry);
    }

    @Override
    public ITradeCharter generateTradeOffer(List<Team> eligibleTeamList) {
        display.showMessageOnConsole("Trading draft picks...");
        for(int i=PlayerDraft.ROUND_7.getNumVal(); i>=0; i--){
            if(offeringTeam.getTradedPicks().get(i) == false){
                return tradeRound(eligibleTeamList, i);
            }
        }
        return null;
    }

    public ITradeCharter tradeRound(List<Team> eligibleTeamList, int roundIdx){
        for (int k = 0; k < eligibleTeamList.size(); k++) {
            if(eligibleTeamList.get(k) == this.offeringTeam) {
                continue;
            } else {
                tradingAlgorithm(eligibleTeamList.get(k));
            }
        }
        if (tradingTeamsBuffer.size() > 0) {
            Triplet<Team, List<Player>, Float> tradeTeam = rosterSearch.findStrongestTradeTeam(tradingTeamsBuffer);
            return DefaultHockeyFactory.makeTradeCharter(this.offeringTeam, null,
                    tradeTeam.getFirst(), tradeTeam.getSecond(), roundIdx);
        }
        return null;
    }

    private void tradingAlgorithm(Team requestedTeam) {
        List<Integer> playerPositionFlag;
        playerPositionFlag = rosterSearch.findPlayerPositions(weakestPlayerList);
        List<Player> requestedPlayerList = requestedTeam.getRoster().getAllPlayerList();
        List<Player> strongestPlayerList = rosterSearch
                .findStrongestPlayers(requestedPlayerList, playerPositionFlag, tradingConfig.getMaxPlayersPerTrade());
        Float playersStrengthSum = rosterSearch.getRosterStrength(strongestPlayerList);
        Triplet<Team, List<Player>, Float> teamRequestEntry =
                Triplet.of(requestedTeam, strongestPlayerList, playersStrengthSum);
        tradingTeamsBuffer.add(teamRequestEntry);
    }
}
