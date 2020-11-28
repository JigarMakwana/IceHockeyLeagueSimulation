package group11.Hockey.BusinessLogic.models.Roster;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.IConstantSupplier;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;

public class Roster implements IRoster {
    private String teamName;
    private List<IPlayer> allPlayerList;
    private List<IPlayer> forwardList;
    private List<IPlayer> defenseList;
    private List<IPlayer> goalieList;
    private List<IPlayer> activeRosterList = new ArrayList<>();
    private List<IPlayer> inActiveRosterList = new ArrayList<>();
    private IConstantSupplier constantSupplier;

    public Roster(String teamName, List<IPlayer> playerList, IConstantSupplier constantSupplier){
        this.teamName = teamName;
        this.allPlayerList = playerList;
        this.updateSubRoster(this.allPlayerList);
        this.constantSupplier = constantSupplier;
    }

    public void updateSubRoster(List<IPlayer> allPlayerList){
        IRosterSearch rosterSearch = DefaultHockeyFactory.makeRosterSearch();
        this.forwardList =  rosterSearch.getForwardList(allPlayerList);
        this.defenseList =  rosterSearch.getDefenseList(allPlayerList);
        this.goalieList =  rosterSearch.getGoalieList(allPlayerList);
        this. activeRosterList = allPlayerList.stream().filter(player ->
                player.isActive() == true).collect(Collectors.toList());
        this. inActiveRosterList = allPlayerList.stream().filter(player ->
                player.isActive() == false).collect(Collectors.toList());
    }

    public boolean isValidRoster(){
        int tamSize =  constantSupplier.getActiveRosterSize() + constantSupplier.getInActiveRosterSize();
        if(allPlayerList.size() == tamSize &&
                isValidActiveRoster() &&
                isValidInActiveRoster() &&
                forwardList.size() == constantSupplier.getForwardSize() &&
                defenseList.size() == constantSupplier.getDefenseSize() &&
                goalieList.size() == constantSupplier.getGoalieSize()){
            return true;
        } else{
            return false;
        }
    }

    public boolean isValidActiveRoster(){
        if(constantSupplier.getActiveRosterSize() == activeRosterList.size()){
            return true;
        }else{
            return false;
        }
    }

    public boolean isValidInActiveRoster(){
        if(constantSupplier.getInActiveRosterSize() == inActiveRosterList.size()){
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

    public String getTeamName() {
        return teamName;
    }
}
