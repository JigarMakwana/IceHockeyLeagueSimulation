package group11.Hockey.BusinessLogic.models.Roster;

import group11.Hockey.BusinessLogic.ConstantSupplier;
import group11.Hockey.BusinessLogic.IConstantSupplier;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;

import java.util.ArrayList;
import java.util.List;

public class RosterMock {
    IPlayer activePlayer1;
    IPlayer activePlayer2;
    IPlayer inActivePlayer1;
    IPlayer inActivePlayer2;
    List<IPlayer> rosterPlayers = new ArrayList<>();
    IRoster team1Roster;

    public RosterMock(){
        initRoster();
    }
    public void initRoster(){
        this.activePlayer1 = new Player(15, 18, 12, 1, "Tom", "forward", true, false, 25,true);
        this.activePlayer2 = new Player(10, 10, 10, 1, "Dick", "defense", false, false, 28, true);
        this.inActivePlayer1 = new Player(10, 4, 9, 18, "Vikash", "goalie", false, false, 30, false);
        this.inActivePlayer2 = new Player(10, 10, 10, 1, "George", "defense", false, false, 21, false);

        this.rosterPlayers.add(activePlayer1);
        this.rosterPlayers.add(activePlayer2);
        this.rosterPlayers.add(inActivePlayer1);
        this.rosterPlayers.add(inActivePlayer2);

        IConstantSupplier constants = new ConstantSupplier(2,2,1,2,1);
        team1Roster = new Roster(this.rosterPlayers, constants);
    }
}
