/*
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.models.Roster;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.Enums.RosterSize;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Roster implements IRoster {
    private String teamName;
    private List<Player> allPlayerList;
    private List<Player> forwardList;
    private List<Player> defenseList;
    private List<Player> goalieList;
    private List<Player> activeRosterList = new ArrayList<>();
    private List<Player> inActiveRosterList = new ArrayList<>();

    public Roster(String teamName, List<Player> playerList){
        this.teamName = teamName;
        this.allPlayerList = playerList;
        this.updateSubRoster(this.allPlayerList);
    }

    public void updateSubRoster(List<Player> allPlayerList){
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

    public void swapPlayers(Player one, Player two){
        activeRosterList.add(two);
        inActiveRosterList.add(one);
        activeRosterList.remove(one);
        inActiveRosterList.remove(two);
    }

    public List<Player> getAllPlayerList() {
        return allPlayerList;
    }

    public List<Player> getActiveRoster() {
        return activeRosterList;
    }

    public List<Player> getForwardList() {
        return forwardList;
    }

    public List<Player> getDefenseList() {
        return defenseList;
    }

    public List<Player> getGoalieList() {
        return goalieList;
    }

    public List<Player> getInActiveRoster() {
        return inActiveRosterList;
    }

}
