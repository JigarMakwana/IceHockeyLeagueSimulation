package group11.Hockey;

import group11.Hockey.models.GameplayConfig;
import group11.Hockey.models.League;
import group11.Hockey.models.Player;
import group11.Hockey.models.Team;

import java.util.List;

public class SettleTeamRoster {
//    protected League leagueObj;
//    protected static final int TEAM_SIZE = 20;
//    protected static final int SKATERS_SIZE = 18;
//    protected static final int GOALIE_SIZE = 2;
//
//    public SettleTeamRoster(League leagueObj)
//    {
//        this.leagueObj = leagueObj;
//    }
//
//    public void settleTeam(Team team) {
//        int noOfPlayers = team.getPlayers().size();
//        List<Player> playerList = team.getPlayers();
//        List<Integer> playerPositionFlag = ATrading.findPlayerPositions(playerList);
//        int noOfForward = playerPositionFlag.get(ATrading.Position.FORWARD.ordinal());
//        int noOfDefense = playerPositionFlag.get(ATrading.Position.DEFENSE.ordinal());
//        int noOfGoalies = playerPositionFlag.get(ATrading.Position.GOALIE.ordinal());
//        int noOfSkaters = noOfForward + noOfDefense;
//        if(noOfPlayers > TEAM_SIZE)
//        {
//            if(noOfGoalies > GOALIE_SIZE)
//            {
//                int noOfGoaliesToBeDropped = noOfGoalies - GOALIE_SIZE;
//                for(int i=0; i<noOfGoaliesToBeDropped; i++)
//                {
//                    dropPlayer(playerList, ATrading.Position.GOALIE);
//                }
//            }
//            if(noOfSkaters > SKATERS_SIZE)
//            {
//                int noOfSkatersToBeDropped =  noOfSkaters - SKATERS_SIZE;
//                for(int i=0; i<noOfSkatersToBeDropped; i++)
//                {
//                    dropPlayer(playerList, ATrading.Position.DEFENSE);
//                    // Dropping defense players as it should be 6
//                }
//            }
//        }
//        else if (noOfPlayers < TEAM_SIZE)
//        {
//            if(noOfGoalies < GOALIE_SIZE)
//            {
//                int noOfGoaliesToBeHired = GOALIE_SIZE - noOfGoalies;
//                for(int i=0; i<noOfGoaliesToBeHired; i++)
//                {
//                    hirePlayer(playerList,  ATrading.Position.GOALIE);
//                }
//            }
//            if(noOfSkaters < SKATERS_SIZE)
//            {
//                int noOfSkatersToBeHired = SKATERS_SIZE - noOfSkaters;
//                for(int i=0; i<noOfSkatersToBeHired; i++)
//                {
//                    hirePlayer(playerList, ATrading.Position.FORWARD);
//                    // Hiring forward players as it should be 12
//                }
//            }
//        }
//    }
//
//    public void dropPlayer(List<Player> playerList, ATrading.Position playerPosition)
//    {
//        Player p = new Player();
//        p.setPosition(playerPosition.toString());
//        p.dropPlayerToFreeAgent(leagueObj,playerList);
//    }
//
//    public void hirePlayer(List<Player> playerList, ATrading.Position playerPosition)
//    {
//        Player p = new Player();
//        p.setPosition(playerPosition.toString());
//        p.replacePlayerWithFreeAgent(leagueObj,playerList);
//    }
}
