package group11.Hockey.BusinessLogic.models.Roster;

import group11.Hockey.BusinessLogic.IConstantSupplier;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;

import java.util.ArrayList;
import java.util.List;

public class Roster implements IRoster {
    private List<IPlayer> roster;
    private List<IPlayer> activeRosterList = new ArrayList<>();
    private List<IPlayer> inActiveRosterList = new ArrayList<>();
    private int suppliedActiveRosterSize;
    private int suppliedInActiveRosterSize;
    private int suppliedForwardSize;
    private int suppliedDefenseSize;
    private int suppliedGoalieSize;

    public Roster(List<IPlayer> playerList, IConstantSupplier supplier){
        this.roster = playerList;
        this.generateSubRoster(this.roster);
        this.suppliedActiveRosterSize = supplier.getActiveRosterSize();
        this.suppliedInActiveRosterSize = supplier.getInActiveRosterSize();
        this.suppliedForwardSize = supplier.getForwardSize();
        this.suppliedDefenseSize = supplier.getDefenseSize();
        this.suppliedGoalieSize = supplier.getGoalieSize();
    }

    public List<IPlayer> getActiveRoster() {
        return activeRosterList;
    }

    public List<IPlayer> getInActiveRoster() {
        return inActiveRosterList;
    }

    public void setSuppliedActiveRosterSize(int suppliedActiveRosterSize) {
        this.suppliedActiveRosterSize = suppliedActiveRosterSize;
    }

    public void setSuppliedInActiveRosterSize(int suppliedInActiveRosterSize) {
        this.suppliedInActiveRosterSize = suppliedInActiveRosterSize;
    }

    public void setSuppliedForwardSize(int suppliedForwardSize) {
        this.suppliedForwardSize = suppliedForwardSize;
    }

    public void setSuppliedDefenseSize(int suppliedDefenseSize) {
        this.suppliedDefenseSize = suppliedDefenseSize;
    }

    public void setSuppliedGoalieSize(int suppliedGoalieSize) {
        this.suppliedGoalieSize = suppliedGoalieSize;
    }

    private void generateSubRoster(List<IPlayer> roster){
        for(IPlayer p: roster){
            if(p.isActive()){
                activeRosterList.add(p);
            }else {
                inActiveRosterList.add(p);
            }
        }
    }

    public boolean isValidRoster(){
        // TODO need to use creational pattern for RosterSearch()
        IRosterSearch rosterSearch = new RosterSearch();
        List<IPlayer> forwardList =  rosterSearch.getForwardList(roster);
        List<IPlayer> defenceList =  rosterSearch.getDefenseList(roster);
        List<IPlayer> goalieList =  rosterSearch.getGoalieList(roster);
        int tamSize =  suppliedActiveRosterSize + suppliedInActiveRosterSize;
        if(roster.size() == tamSize && forwardList.size() == suppliedForwardSize &&
                defenceList.size() == suppliedDefenseSize && goalieList.size() == suppliedGoalieSize){
            return true;
        } else{
            return false;
        }
    }

    public boolean isValidActiveRoster(){
        if(this.suppliedActiveRosterSize == activeRosterList.size()){
            return true;
        }else{
            return false;
        }
    }

    public boolean isValidInActiveRoster(){
        if(this.suppliedInActiveRosterSize == inActiveRosterList.size()){
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
}
