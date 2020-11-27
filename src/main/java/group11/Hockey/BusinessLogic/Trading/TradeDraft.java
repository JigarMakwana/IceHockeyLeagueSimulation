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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TradeDraft implements ITradeDraft {
    private List<Map<Team, Map<Team, List<Integer>>>> draftTradeTracker;
    private List<Boolean> tradedPicks;
    private Team offeringTeam;
    private List<Player> weakestPlayerList;
    private ITradingConfig tradingConfig;
    private IDisplay display;
    private IRosterSearch rosterSearch;
    private List<Triplet<Team, List<Player>, Float>> tradingTeamsBuffer = new ArrayList<>();

    public TradeDraft(Team offeringTeam, ITradingConfig tradingConfig, IDisplay display) {
        this.draftTradeTracker = new ArrayList<>();
        this.tradedPicks = new ArrayList<>(Collections.nCopies(PlayerDraft.PLAYER_DRAFT_ROUNDS.getNumVal(), false));
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
    public List<Map<Team, Map<Team, List<Integer>>>> getDraftTradeTracker() {
        return draftTradeTracker;
    }

    public void updateDraftTradeTracker() {
    }

    @Override
    public ITradeCharter generateTradeOffer(List<Team> eligibleTeamList) {
        display.showMessageOnConsole("Trading draft picks...");
        for(int i=PlayerDraft.ROUND_7.getNumVal(); i>=0; i--){
            if(tradedPicks.get(i) == false){
                return tradeRound(eligibleTeamList);
            }
        }
        return null;
    }

    public ITradeCharter tradeRound(List<Team> eligibleTeamList){
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
                    tradeTeam.getFirst(), tradeTeam.getSecond(), true);
        }
        return null;
    }

    private void tradingAlgorithm(Team requestedTeam) {
        List<Integer> playerPositionFlag;
        playerPositionFlag = rosterSearch.findPlayerPositions(weakestPlayerList);
        List<Player> requestedPlayerList = requestedTeam.getRoster().getAllPlayerList();
        List<Player> strongestPlayerList = rosterSearch
                .findStrongestPlayers(requestedPlayerList, playerPositionFlag, tradingConfig.getMaxPlayersPerTrade());
        Float playersStrengthSum = rosterSearch.playersStrengthSum(strongestPlayerList);
        Triplet<Team, List<Player>, Float> teamRequestEntry =
                Triplet.of(requestedTeam, strongestPlayerList, playersStrengthSum);
        tradingTeamsBuffer.add(teamRequestEntry);
    }
}
