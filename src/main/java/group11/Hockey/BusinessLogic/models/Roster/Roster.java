package group11.Hockey.BusinessLogic.models.Roster;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.IConstantSupplier;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;

import java.util.ArrayList;
import java.util.List;

public class Roster implements IRoster {
    private String teamName;
    private List<Player> allPlayerList;
    private List<Player> forwardList;
    private List<Player> defenseList;
    private List<Player> goalieList;
    private List<Player> activeRosterList = new ArrayList<>();
    private List<Player> inActiveRosterList = new ArrayList<>();
    private IConstantSupplier constantSupplier;

    public Roster(String teamName, List<Player> playerList, IConstantSupplier constantSupplier){
        this.teamName = teamName;
        this.allPlayerList = playerList;
        this.generateSubRoster(this.allPlayerList);
        IRosterSearch rosterSearch = DefaultHockeyFactory.makeRosterSearch();
        this.forwardList =  rosterSearch.getForwardList(allPlayerList);
        this.defenseList =  rosterSearch.getDefenseList(allPlayerList);
        this.goalieList =  rosterSearch.getGoalieList(allPlayerList);
        this.constantSupplier = constantSupplier;
    }

    private void generateSubRoster(List<Player> allPlayerList){
        for(Player p: allPlayerList){
            if(p.isActive()){
                activeRosterList.add(p);
            }else {
                inActiveRosterList.add(p);
            }
        }
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

    public String getTeamName() {
        return teamName;
    }
}
