package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.IRandomNoGenerator;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeCharter;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeGenerator;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradingConfig;
import group11.Hockey.BusinessLogic.Triplet;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;
import group11.Hockey.InputOutput.IDisplay;
import javafx.util.Pair;

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
//        setWeakestPlayerList();
    }

    private void setWeakestPlayerList(){
        this.offeredPlayerList = this.rosterSearch.findWeakestPlayers(this.offeringTeam.getRoster().getAllPlayerList(),
                this.tradingConfig.getMaxPlayersPerTrade());
    }

    @Override
    public ITradeCharter generateTradeOffer(List<Team> eligibleTeamList) {
        display.showMessageOnConsole("\nGenerating Trade for AI Team " + offeringTeam.getTeamName());
        Team requestedTeam = findStrongestTeam(eligibleTeamList);
        if(requestedTeam == offeringTeam) {
        } else {
            List<Player> combinations = new ArrayList<>();
            findCombinations(requestedTeam.getPlayers(), combinations,0, requestedTeam.getPlayers().size() - 1, 0, tradingConfig.getMaxPlayersPerTrade());
            Pair<Float, List<Player>> requestedPlayers = findBestCombination();
            if(requestedPlayers.getKey() > offeringTeam.getTeamStrength()){
                return DefaultHockeyFactory.makeTradeCharter(offeringTeam,offeredPlayerList,requestedTeam, requestedPlayers.getValue());
            }
        }
//        findBestCombination();
//        offerTrade();
//        for (int k = 0; k < eligibleTeamList.size(); k++) {
//            if(eligibleTeamList.get(k) == this.offeringTeam) {
//                continue;
//            } else {
//                tradingAlgorithm(eligibleTeamList.get(k));
//            }
//        }
//        if (tradingTeamsBuffer.size() > 0) {
//            Triplet<Team, List<Player>, Float> tradeTeam = rosterSearch.findStrongestTradeTeam(tradingTeamsBuffer);
//            return DefaultHockeyFactory.makeTradeCharter(this.offeringTeam, this.offeredPlayerList,
//                    tradeTeam.getFirst(), tradeTeam.getSecond());
//        }
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

    public Pair<Float, List<Player>> findBestCombination(){
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
        Pair<Float, List<Player>> requestedPlayers = new Pair<>(newTeamStrength,tradePlayerList);
        return requestedPlayers;
    }



    private void tradingAlgorithm(Team requestedTeam) {
        List<Integer> playerPositionFlag;
        playerPositionFlag = rosterSearch.findPlayerPositions(offeredPlayerList);
        List<Player> requestedPlayerList = requestedTeam.getRoster().getAllPlayerList();
        List<Player> strongestPlayerList = rosterSearch
                .findStrongestPlayers(requestedPlayerList, playerPositionFlag, tradingConfig.getMaxPlayersPerTrade());
        Float playersStrengthSum = rosterSearch.playersStrengthSum(strongestPlayerList);
        Triplet<Team, List<Player>, Float> teamRequestEntry =
                Triplet.of(requestedTeam, strongestPlayerList, playersStrengthSum);
        tradingTeamsBuffer.add(teamRequestEntry);
    }

    @Override
    public void tradeDraftPicks() {

    }

}
