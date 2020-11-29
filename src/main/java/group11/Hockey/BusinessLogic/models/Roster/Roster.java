/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.models.Roster;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.Enums.RosterSize;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;

public class Roster implements IRoster {
    private String teamName;
    private List<IPlayer> allPlayerList;
    private List<IPlayer> forwardList;
    private List<IPlayer> defenseList;
    private List<IPlayer> goalieList;
    private List<IPlayer> activeRosterList = new ArrayList<>();
    private List<IPlayer> inActiveRosterList = new ArrayList<>();

    public Roster(String teamName, List<IPlayer> playerList){
        this.teamName = teamName;
        this.allPlayerList = playerList;
        this.updateSubRoster(this.allPlayerList);
    }

    public void updateSubRoster(List<IPlayer> allPlayerList){
        IRosterSearch rosterSearch = DefaultHockeyFactory.makeRosterSearch();
        if(allPlayerList == null){
            return;
        } else {
            this.forwardList =  rosterSearch.getForwardList(allPlayerList);
            this.defenseList =  rosterSearch.getDefenseList(allPlayerList);
            this.goalieList =  rosterSearch.getGoalieList(allPlayerList);
            this. activeRosterList = allPlayerList.stream().filter(player -> player.isActive() == true).collect(Collectors.toList());
            this. inActiveRosterList = allPlayerList.stream().filter(player -> player.isActive() == false).collect(Collectors.toList());
        }
    }

    public boolean isValidRoster(){
        int tamSize =  RosterSize.ACTIVE_ROSTER_SIZE.getNumVal() + RosterSize.INACTIVE_ROSTER_SIZE.getNumVal();
        if(allPlayerList.size() == tamSize &&
                isValidActiveRoster() &&
                isValidInActiveRoster() &&
                forwardList.size() == RosterSize.FORWARD_SIZE.getNumVal() &&
                defenseList.size() == RosterSize.DEFENSE_SIZE.getNumVal() &&
                goalieList.size() == RosterSize.GOALIE_SIZE.getNumVal()){
            return true;
        } else{
            return false;
        }
    }

    public boolean isValidActiveRoster(){
        if(RosterSize.ACTIVE_ROSTER_SIZE.getNumVal() == activeRosterList.size()){
            return true;
        }else{
            return false;
        }
    }

    public boolean isValidInActiveRoster(){
        if(RosterSize.INACTIVE_ROSTER_SIZE.getNumVal() == inActiveRosterList.size()){
            return true;
        }else{
            return false;
        }
    }

    public void swapPlayers(IPlayer one, IPlayer two){
        activeRosterList.add(two);
        inActiveRosterList.add(one);
        activeRosterList.remove(one);
        inActiveRosterList.remove(two);
    }

    public List<IPlayer> getAllPlayerList() {
        return allPlayerList;
    }

    public List<IPlayer> getActiveRoster() {
        return activeRosterList;
    }

    public List<IPlayer> getForwardList() {
        return forwardList;
    }

    public List<IPlayer> getDefenseList() {
        return defenseList;
    }

    public List<IPlayer> getGoalieList() {
        return goalieList;
    }

    public List<IPlayer> getInActiveRoster() {
        return inActiveRosterList;
    }

}
