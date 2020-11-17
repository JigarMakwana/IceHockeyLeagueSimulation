package group11.Hockey.BusinessLogic.models.Roster;

import group11.Hockey.BusinessLogic.Positions;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRosterSearch;

import java.util.List;
import java.util.stream.Collectors;

public class RosterSearch implements IRosterSearch {

    public List<IPlayer> getDefenseList(List<IPlayer> playerList) {
        List<IPlayer> defenceIPlayerList= playerList.stream().filter(player ->
                player.getPosition().equalsIgnoreCase(Positions.DEFENSE.toString())).collect(Collectors.toList());
        return defenceIPlayerList;
    }

    public List<IPlayer> getForwardList(List<IPlayer> playerList) {
        List<IPlayer> forwardIPlayerList= playerList.stream().filter(player ->
                player.getPosition().equalsIgnoreCase(Positions.FORWARD.toString())).collect(Collectors.toList());
        return forwardIPlayerList;
    }

    public List<IPlayer> getGoalieList(List<IPlayer> playerList) {
        List<IPlayer> goalieIPlayerList= playerList.stream().filter(player ->
                player.getPosition().equalsIgnoreCase(Positions.GOALIE.toString())).collect(Collectors.toList());
        return goalieIPlayerList;
    }
}
