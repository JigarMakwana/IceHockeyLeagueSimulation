package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeCharter;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeGenerator;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradingConfig;
import group11.Hockey.BusinessLogic.Triplet;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import group11.Hockey.InputOutput.IDisplay;

import java.util.ArrayList;
import java.util.List;

public class TradeGenerator implements ITradeGenerator {
    private ITeam offeringTeam;
    private List<IPlayer> weakestPlayerList;
    private ITradingConfig tradingConfig;
    private IDisplay display;
    private IRosterSearch rosterSearch;
    private List<Triplet<ITeam, List<IPlayer>, Float>> tradingTeamsBuffer = new ArrayList<>();

    public TradeGenerator(ITeam offeringTeam, ITradingConfig tradingConfig, IDisplay display){
        this.offeringTeam = offeringTeam;
        this.tradingConfig = tradingConfig;
        this.display = display;
        this.rosterSearch = DefaultHockeyFactory.makeRosterSearch();
        this.weakestPlayerList = rosterSearch.findWeakestPlayers(this.offeringTeam.getRoster().getAllPlayerList(),
                this.tradingConfig.getMaxPlayersPerTrade());
    }

    @Override
    public ITradeCharter generateTradeOffer(List<ITeam> eligibleTeamList) {
        for (int k = 0; k < eligibleTeamList.size(); k++) {
            if(eligibleTeamList.get(k) == this.offeringTeam) {
                continue;
            } else {
                tradingAlgorithm(eligibleTeamList.get(k));
            }
        }
        if (tradingTeamsBuffer.size() > 0) {
            Triplet<ITeam, List<IPlayer>, Float> tradeTeam = rosterSearch.findStrongestTradeTeam(tradingTeamsBuffer);
            return DefaultHockeyFactory.makeTradeCharter(this.offeringTeam, this.weakestPlayerList,
                    tradeTeam.getFirst(), tradeTeam.getSecond());
        }
        return DefaultHockeyFactory.makeTradeCharter(null,null,null, null);
    }

    @Override
    public void tradingAlgorithm(ITeam requestedTeam) {
        List<Integer> playerPositionFlag;

        display.showMessageOnConsole("\nGenerating Trade for AI Team " + this.offeringTeam.getTeamName());
        playerPositionFlag = rosterSearch.findPlayerPositions(weakestPlayerList);

        List<IPlayer> requestedPlayerList = requestedTeam.getRoster().getAllPlayerList();
        List<IPlayer> strongestPlayerList = rosterSearch
                .findStrongestPlayers(requestedPlayerList, playerPositionFlag, this.tradingConfig.getMaxPlayersPerTrade());
        Float playersStrengthSum = rosterSearch.playersStrengthSum(strongestPlayerList);
        Triplet<ITeam, List<IPlayer>, Float> teamRequestEntry =
                Triplet.of(requestedTeam, strongestPlayerList, playersStrengthSum);
        tradingTeamsBuffer.add(teamRequestEntry);
    }

    @Override
    public void tradeDraftPicks() {

    }

}
