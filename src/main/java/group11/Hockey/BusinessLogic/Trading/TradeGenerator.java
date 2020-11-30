/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.RandomNumGenerator.IRandomFloatGenerator;
import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeConfig;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.Enums.PlayerNoModifier;
import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeCharter;
import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeGenerator;
import group11.Hockey.BusinessLogic.RandomNumGenerator.RandomNoFactory;
import group11.Hockey.BusinessLogic.Trading.TradingTriplet.Triplet;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import group11.Hockey.InputOutput.IDisplay;

public class TradeGenerator implements ITradeGenerator {
    private ITeam offeringTeam;
    private List<IPlayer> offeredPlayerList;
    private ITradeConfig tradingConfig;
    private IDisplay display;
    private IRosterSearch rosterSearch;
    private List<List<IPlayer>> playerCombinations;
    private IRandomFloatGenerator rand;
    private List<Triplet<Team, List<Player>, Float>> tradingTeamsBuffer = new ArrayList<>();

    public TradeGenerator(ITeam offeringTeam, ITradeConfig tradingConfig, IDisplay display){
        this.display = display;
        this.offeringTeam = offeringTeam;
        this.tradingConfig = tradingConfig;
        this.playerCombinations = new ArrayList<>();
        this.offeredPlayerList = new ArrayList<>();
        this.rand = RandomNoFactory.makeRandomFloatGenerator();
        this.rosterSearch = DefaultHockeyFactory.makeRosterSearch();
    }

    @Override
    public ITradeCharter generateTradeOffer(List<ITeam> eligibleTeamList) {
        display.showMessageOnConsole("\nGenerating Trade for AI Team " + offeringTeam.getTeamName());
        ITeam requestedTeam = rosterSearch.findStrongestTeam(eligibleTeamList);
        if(requestedTeam == offeringTeam) {
        } else {
            List<IPlayer> combinations = new ArrayList<>();
            findCombinations(requestedTeam.getPlayers(), combinations,0, requestedTeam.getPlayers().size() - 1, 0, tradingConfig.getMaxPlayersPerTrade());
            Map<Float, List<IPlayer>> requestedPlayers = findBestCombination();
            Map.Entry<Float, List<IPlayer>> entry = requestedPlayers.entrySet().iterator().next();
            if(rand.generateRandomNo() > PlayerNoModifier.DRAFTTRADE_MODIFIER.getNumVal() &&
                    entry.getKey() > offeringTeam.getTeamStrength()){
                display.displayTradeStatistics(offeringTeam, offeredPlayerList, requestedTeam, entry.getValue());
                return TradingFactory.makeTradeCharter(offeringTeam,offeredPlayerList,requestedTeam, entry.getValue(), -1);
            }
            else if(offeringTeam.getTeamStrength() < rosterSearch.averageTeamStrength(eligibleTeamList)){
                return tradeDraftPicks(eligibleTeamList);
            }
        }
        return TradingFactory.makeTradeCharter(null,null,null, null, -1);
    }

    public ITradeCharter tradeDraftPicks(List<ITeam> eligibleTeamList) {
        ITradeGenerator tradeDraft = TradingFactory.makeTradeDraft(offeringTeam,tradingConfig,display);
        return tradeDraft.generateTradeOffer(eligibleTeamList);
    }

    private void findCombinations(List<IPlayer> playerList,List<IPlayer> combinations, int start, int end, int index, int r) {
        if (index == r) {
            List<IPlayer> temp = new ArrayList<>();
            for (int i=0; i<r; i++){
                temp.add(combinations.get(i));
            }
            playerCombinations.add(temp);
            return;
        }
        for (int i=start; i<=end && end-i+1 >= r-index; i++) {
            combinations.add(index,playerList.get(i));
            findCombinations(playerList, combinations, i+1, end, index+1, r);
        }
    }

    public Map<Float, List<IPlayer>> findBestCombination(){
        List<IPlayer> tradePlayerList = new ArrayList<>();
        float newTeamStrength = 0.0f;
        offeringTeam.getRoster().updateSubRoster(offeringTeam.getPlayers());
//        List<IPlayer> activeRoster = offeringTeam.getRoster().getActiveRoster();
        List<IPlayer> activeRoster = offeringTeam.getPlayers();

        for(int i=0; i<activeRoster.size(); i++){
            for(int j=0; j<playerCombinations.size(); j++){
                float calStrength = rosterSearch.getRosterStrength(activeRoster) +
                        rosterSearch.getRosterStrength(playerCombinations.get(j)) -
                        activeRoster.get(i).getPlayerStrength();
                if(calStrength > newTeamStrength){
                    newTeamStrength = calStrength;
                    tradePlayerList = playerCombinations.get(j);
                    if(rand.generateRandomNo() > PlayerNoModifier.MULTIPLE_PLAYER_MODIFIER.getNumVal()){
                        offeredPlayerList.clear();
                    }
                    offeredPlayerList.add(activeRoster.get(i));
                }
            }
        }
        Map<Float, List<IPlayer>> requestedPlayers = new HashMap<>();
        requestedPlayers.put(newTeamStrength,tradePlayerList);
        return requestedPlayers;
    }
}
