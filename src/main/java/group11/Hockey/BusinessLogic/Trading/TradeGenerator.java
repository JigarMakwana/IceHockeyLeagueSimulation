package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeCharter;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeGenerator;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradingConfig;
import group11.Hockey.BusinessLogic.Triplet;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import group11.Hockey.InputOutput.IDisplay;

import java.util.ArrayList;
import java.util.List;

public class TradeGenerator implements ITradeGenerator {
    private Team offeringTeam;
    private List<Player> weakestPlayerList;
    private ITradingConfig tradingConfig;
    private IDisplay display;
    private IRosterSearch rosterSearch;
    private List<Triplet<Team, List<Player>, Float>> tradingTeamsBuffer = new ArrayList<>();

    public TradeGenerator(Team offeringTeam, ITradingConfig tradingConfig, IDisplay display){
        this.offeringTeam = offeringTeam;
        this.tradingConfig = tradingConfig;
        this.display = display;
        this.rosterSearch = DefaultHockeyFactory.makeRosterSearch();
        setWeakestPlayerList();
    }

    private void setWeakestPlayerList(){
        this.weakestPlayerList = this.rosterSearch.findWeakestPlayers(this.offeringTeam.getRoster().getAllPlayerList(),
                this.tradingConfig.getMaxPlayersPerTrade());
    }

    @Override
    public ITradeCharter generateTradeOffer(List<Team> eligibleTeamList) {
        for (int k = 0; k < eligibleTeamList.size(); k++) {
            if(eligibleTeamList.get(k) == this.offeringTeam) {
                continue;
            } else {
                tradingAlgorithm(eligibleTeamList.get(k));
            }
        }
        if (tradingTeamsBuffer.size() > 0) {
            Triplet<Team, List<Player>, Float> tradeTeam = rosterSearch.findStrongestTradeTeam(tradingTeamsBuffer);
            return DefaultHockeyFactory.makeTradeCharter(this.offeringTeam, this.weakestPlayerList,
                    tradeTeam.getFirst(), tradeTeam.getSecond());
        }
        return DefaultHockeyFactory.makeTradeCharter(null,null,null, null);
    }

    private void tradingAlgorithm(Team requestedTeam) {
        List<Integer> playerPositionFlag;

        display.showMessageOnConsole("\nGenerating Trade for AI Team " + this.offeringTeam.getTeamName());
        playerPositionFlag = rosterSearch.findPlayerPositions(weakestPlayerList);

        List<Player> requestedPlayerList = requestedTeam.getRoster().getAllPlayerList();
        List<Player> strongestPlayerList = rosterSearch
                .findStrongestPlayers(requestedPlayerList, playerPositionFlag, this.tradingConfig.getMaxPlayersPerTrade());
        Float playersStrengthSum = rosterSearch.playersStrengthSum(strongestPlayerList);
        Triplet<Team, List<Player>, Float> teamRequestEntry =
                Triplet.of(requestedTeam, strongestPlayerList, playersStrengthSum);
        tradingTeamsBuffer.add(teamRequestEntry);
    }

    @Override
    public void tradeDraftPicks() {

    }

}
