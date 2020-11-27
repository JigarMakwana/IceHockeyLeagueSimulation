package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.IRandomNoGenerator;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeCharter;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeDraft;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeGenerator;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradingConfig;
import group11.Hockey.BusinessLogic.Triplet;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import group11.Hockey.InputOutput.IDisplay;

import java.util.*;

public class TradeGenerator implements ITradeGenerator {
    private Team offeringTeam;
    private List<Player> offeredPlayerList;
    private ITradingConfig tradingConfig;
    private IDisplay display;
    private IRosterSearch rosterSearch;
    private List<List<Player>> playerCombinations;
    private List<Triplet<Team, List<Player>, Float>> tradingTeamsBuffer = new ArrayList<>();

    public TradeGenerator(Team offeringTeam, ITradingConfig tradingConfig, IDisplay display){
        this.offeringTeam = offeringTeam;
        this.tradingConfig = tradingConfig;
        this.display = display;
        this.rosterSearch = DefaultHockeyFactory.makeRosterSearch();
        this.playerCombinations = new ArrayList<>();
        this.offeredPlayerList = new ArrayList<>();
    }

    @Override
    public ITradeCharter generateTradeOffer(List<Team> eligibleTeamList) {
        display.showMessageOnConsole("\nGenerating Trade for AI Team " + offeringTeam.getTeamName());
        Team requestedTeam = findStrongestTeam(eligibleTeamList);
        if(requestedTeam == offeringTeam) {
        } else {
            List<Player> combinations = new ArrayList<>();
            findCombinations(requestedTeam.getPlayers(), combinations,0, requestedTeam.getPlayers().size() - 1, 0, tradingConfig.getMaxPlayersPerTrade());
            Map<Float, List<Player>> requestedPlayers = findBestCombination();
            Map.Entry<Float, List<Player>> entry = requestedPlayers.entrySet().iterator().next();
            if(entry.getKey() > offeringTeam.getTeamStrength()){
                return DefaultHockeyFactory.makeTradeCharter(offeringTeam,offeredPlayerList,requestedTeam, entry.getValue());
            }
            if(offeringTeam.getTeamStrength() < averageTeamStrength(eligibleTeamList)){
                return tradeDraftPicks(eligibleTeamList);
            }
        }
        return DefaultHockeyFactory.makeTradeCharter(null,null,null, null);
    }

    public Team findStrongestTeam(List<Team> eligibleTeamList){
        Collections.sort(eligibleTeamList);
        return eligibleTeamList.get(0);
    }

    private void findCombinations(List<Player> playerList,List<Player> combinations, int start, int end, int index, int r) {
        if (index == r) {
            List<Player> temp = new ArrayList<>();
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

    public Map<Float, List<Player>> findBestCombination(){
        List<Player> tradePlayerList = new ArrayList<>();
        float newTeamStrength = 0.0f;
        List<Player> activeRoster = offeringTeam.getRoster().getActiveRoster();
        for(int i=0; i<activeRoster.size(); i++){
            for(int j=0; j<playerCombinations.size(); j++){
                float calStrength = rosterSearch.getRosterStrength(activeRoster) +
                        rosterSearch.getRosterStrength(playerCombinations.get(j)) -
                        activeRoster.get(i).getPlayerStrength();
                if(calStrength > newTeamStrength){
                    newTeamStrength = calStrength;
                    tradePlayerList = playerCombinations.get(j);
                    IRandomNoGenerator rand = DefaultHockeyFactory.makeRandomFloatGenerator();
                    rand.generateRandomFloat();
                    if(rand.generateRandomFloat() > PlayerNoModifier.MULTIPLE_PLAYER_MODIFIER.getNumVal()){
                        offeredPlayerList.clear();
                    }
                    offeredPlayerList.add(activeRoster.get(i));
                }
            }
        }
        Map<Float, List<Player>> requestedPlayers = new HashMap<>();
        requestedPlayers.put(newTeamStrength,tradePlayerList);
        return requestedPlayers;
    }

    public float averageTeamStrength(List<Team> eligibleTeamList){
        float sum = 0.0f;
        for(int i=0 ; i<eligibleTeamList.size(); i++){
            sum += eligibleTeamList.get(i).getTeamStrength();
        }
        return sum/eligibleTeamList.size();
    }

    @Override
    public ITradeCharter tradeDraftPicks(List<Team> eligibleTeamList) {
        ITradeDraft tradeDraft = DefaultHockeyFactory.makeTradeDraft(offeringTeam,tradingConfig,display);
        return tradeDraft.generateTradeOffer(eligibleTeamList);
    }

}
