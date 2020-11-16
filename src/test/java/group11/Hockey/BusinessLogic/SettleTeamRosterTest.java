package group11.Hockey.BusinessLogic;

import group11.Hockey.BusinessLogic.Trading.SettleTeamRoster;
import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class SettleTeamRosterTest {
    public IConstantSupplier constants;
    public TradingModelMock leagueModel;
    public League leagueObj;
    public SettleTeamRoster settleObj ;

    @Test
    public void hirePlayerTest() {
        this.constants = new ConstantSupplier(5,3,2);
        this.leagueModel = new TradingModelMock(1.0f, 1.0f);
        this.leagueObj = leagueModel.getLeagueInfo();
        this.settleObj = new SettleTeamRoster(leagueObj, constants);

        List<Team> teamList = new ArrayList<>();
        teamList = leagueModel.getTeamList();
        Team team1 = teamList.get(0);
        List<Player> playerList = team1.getPlayers();
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
    void dropPlayerTest() {
        this.constants = new ConstantSupplier(3,2,1);
        this.leagueModel = new TradingModelMock(1.0f, 1.0f);
        this.leagueObj = leagueModel.getLeagueInfo();
        this.settleObj = new SettleTeamRoster(leagueObj, constants);

        List<Team> teamList = new ArrayList<>();
        teamList = leagueModel.getTeamList();
        Team team1 = teamList.get(0);
        List<Player> playerList = team1.getPlayers();
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
