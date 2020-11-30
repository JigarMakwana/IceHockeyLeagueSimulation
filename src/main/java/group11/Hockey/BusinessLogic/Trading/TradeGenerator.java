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
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import group11.Hockey.InputOutput.IDisplay;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TradeGenerator implements ITradeGenerator {
    private ITeam offeringTeam;
    private List<IPlayer> offeredPlayerList;
    private ITradeConfig tradingConfig;
    private IDisplay display;
    private IRosterSearch rosterSearch;
    private List<List<IPlayer>> playerCombinations;
    private IRandomFloatGenerator rand;
    private static Logger logger = LogManager.getLogger(TradeGenerator.class);

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
        logger.debug("Entered generateTradeOffer()");
        logger.debug("\nGenerating Trade for AI Team " + offeringTeam.getTeamName());
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
                logger.debug("Found the "+ requestedTeam +" team to trade. Creating Trade Charter...");
                return TradingFactory.makeTradeCharter(offeringTeam,offeredPlayerList,requestedTeam, entry.getValue(), -1);
            }
            else if(offeringTeam.getTeamStrength() < rosterSearch.averageTeamStrength(eligibleTeamList)){
                logger.debug("Could not generate Normal Trade. Generating Draft Pick Trades...");
                return tradeDraftPicks(eligibleTeamList);
            }
        }
        logger.debug("Could not generate any trade. Returning null Trade Charter.");
        return TradingFactory.makeTradeCharter(null,null,null, null, -1);
    }

    public ITradeCharter tradeDraftPicks(List<ITeam> eligibleTeamList) {
        logger.debug("Entered tradeDraftPicks(). Calling TradeDraft.");
        ITradeGenerator tradeDraft = TradingFactory.makeTradeDraft(offeringTeam,tradingConfig,display);
        return tradeDraft.generateTradeOffer(eligibleTeamList);
    }

    private void findCombinations(List<IPlayer> playerList,List<IPlayer> combinations, int start, int end, int index, int r) {
        logger.debug("Entered recursive findCombinations(). Generating player combinations.");
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
        logger.debug("Entered findBestCombination(). Finding best multiple players to trade.");
        List<IPlayer> tradePlayerList = new ArrayList<>();
        float newTeamStrength = 0.0f;
        offeringTeam.getRoster().updateSubRoster(offeringTeam.getPlayers());
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
        logger.info("Found best player combination with strength " + newTeamStrength);
        return requestedPlayers;
    }
}
