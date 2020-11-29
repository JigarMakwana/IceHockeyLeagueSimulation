package group11.Hockey.BusinessLogic.Trading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.IRandomNoGenerator;
import group11.Hockey.BusinessLogic.Triplet;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeCharter;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeDraft;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeGenerator;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradingConfig;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import group11.Hockey.InputOutput.IDisplay;

public class TradeGenerator implements ITradeGenerator {
    private ITeam offeringTeam;
    private List<IPlayer> offeredPlayerList;
    private ITradingConfig tradingConfig;
    private IDisplay display;
    private IRosterSearch rosterSearch;
    private List<List<IPlayer>> playerCombinations;
    private List<Triplet<Team, List<Player>, Float>> tradingTeamsBuffer = new ArrayList<>();

    public TradeGenerator(ITeam offeringTeam, ITradingConfig tradingConfig, IDisplay display){
        this.offeringTeam = offeringTeam;
        this.tradingConfig = tradingConfig;
        this.display = display;
        this.rosterSearch = DefaultHockeyFactory.makeRosterSearch();
        this.playerCombinations = new ArrayList<>();
        this.offeredPlayerList = new ArrayList<>();
    }

    @Override
    public ITradeCharter generateTradeOffer(List<ITeam> eligibleTeamList) {
        display.showMessageOnConsole("\nGenerating Trade for AI Team " + offeringTeam.getTeamName());
        Team requestedTeam = findStrongestTeam(eligibleTeamList);
        if(requestedTeam == offeringTeam) {
        } else {
            List<IPlayer> combinations = new ArrayList<>();
            findCombinations(requestedTeam.getPlayers(), combinations,0, requestedTeam.getPlayers().size() - 1, 0, tradingConfig.getMaxPlayersPerTrade());
            Map<Float, List<IPlayer>> requestedPlayers = findBestCombination();
            Map.Entry<Float, List<IPlayer>> entry = requestedPlayers.entrySet().iterator().next();
            if(entry.getKey() > offeringTeam.getTeamStrength()){
                return DefaultHockeyFactory.makeTradeCharter(offeringTeam,offeredPlayerList,requestedTeam, entry.getValue(), -1);
            }
            if(offeringTeam.getTeamStrength() < averageTeamStrength(eligibleTeamList)){
                return tradeDraftPicks(eligibleTeamList);
            }
        }
        return DefaultHockeyFactory.makeTradeCharter(null,null,null, null, -1);
    }

    public Team findStrongestTeam(List<ITeam> eligibleTeamList){
    	List<Team> teams = new ArrayList<>();
    	for(ITeam team: eligibleTeamList) {
    		teams.add((Team)team);
    	}
        Collections.sort(teams);
        return teams.get(0);
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
        List<IPlayer> activeRoster = offeringTeam.getRoster().getActiveRoster();
        for(int i=0; i<activeRoster.size(); i++){
            for(int j=0; j<playerCombinations.size(); j++){
                float calStrength = rosterSearch.getRosterStrength(activeRoster) +
                        rosterSearch.getRosterStrength(playerCombinations.get(j)) -
                        activeRoster.get(i).getPlayerStrength();
                if(calStrength > newTeamStrength){
                    newTeamStrength = calStrength;
                    tradePlayerList = playerCombinations.get(j);
                    IRandomNoGenerator rand = DefaultHockeyFactory.makeRandomNumberGenerator();
                    rand.generateRandomFloat();
                    if(rand.generateRandomFloat() > PlayerNoModifier.MULTIPLE_PLAYER_MODIFIER.getNumVal()){
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

    public float averageTeamStrength(List<ITeam> eligibleTeamList){
        float sum = 0.0f;
        for(int i=0 ; i<eligibleTeamList.size(); i++){
            sum += eligibleTeamList.get(i).getTeamStrength();
        }
        return sum/eligibleTeamList.size();
    }

    @Override
    public ITradeCharter tradeDraftPicks(List<ITeam> eligibleTeamList) {
        ITradeDraft tradeDraft = DefaultHockeyFactory.makeTradeDraft(offeringTeam,tradingConfig,display);
        return tradeDraft.generateTradeOffer(eligibleTeamList);
    }

}
