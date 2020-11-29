package group11.Hockey.BusinessLogic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import group11.Hockey.BusinessLogic.Trading.SettleTeamRoster;
import group11.Hockey.BusinessLogic.Trading.TradingModelMock;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Team;

public class SettleTeamRosterTest {
    public IConstantSupplier constants;
    public TradingModelMock leagueModel;
    public League leagueObj;
    public SettleTeamRoster settleObj ;

    @Test
    public void hirePlayerTest() {
        this.constants = new ConstantSupplier(5,5,3,2,1);
        this.leagueModel = new TradingModelMock(1.0f, 1.0f);
        this.leagueObj = leagueModel.getLeagueInfo();
        this.settleObj = new SettleTeamRoster(leagueObj, constants);

        List<ITeam> teamList = new ArrayList<>();
        teamList = leagueModel.getTeamList();
        ITeam team1 = teamList.get(0);
        List<IPlayer> playerList = team1.getPlayers();
        try{
            settleObj.hirePlayer(leagueObj, playerList, Positions.GOALIE);
        }
        catch (Exception e){
            System.out.println(e);
        }
        Assert.assertEquals(playerList.size(), 5);
        Assert.assertEquals(playerList.get(4).getPlayerName(), "fifthFreeAgent");
    }

    @Test
    public void dropPlayerTest() {
        this.constants = new ConstantSupplier(3,3, 2,1, 1);
        this.leagueModel = new TradingModelMock(1.0f, 1.0f);
        this.leagueObj = leagueModel.getLeagueInfo();
        this.settleObj = new SettleTeamRoster(leagueObj, constants);

        List<ITeam> teamList = new ArrayList<>();
        teamList = leagueModel.getTeamList();
        ITeam team1 = teamList.get(0);
        List<IPlayer> playerList = team1.getPlayers();
        try{
            settleObj.dropPlayer(leagueObj, playerList, Positions.GOALIE);
        }
        catch (Exception e){
            System.out.println(e);
        }
        Assert.assertEquals(playerList.size(), 3);
        Assert.assertEquals(playerList.get(0).getPlayerName(), "Dick");
        Assert.assertEquals(playerList.get(1).getPlayerName(), "Jerry");
        Assert.assertEquals(playerList.get(2).getPlayerName(), "Tom");
    }
}
