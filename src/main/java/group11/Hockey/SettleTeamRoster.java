package group11.Hockey;

import group11.Hockey.AITrading;
import group11.Hockey.models.League;
import group11.Hockey.models.Player;
import group11.Hockey.models.Team;

import java.util.ArrayList;
import java.util.List;

public class SettleTeamRoster extends CommonUtilForLeague{
    private League leagueObj;
    private AITrading aiTradingObj;
    IDisplay display = new Display();
    private ICommandLineInput userInputMode = new CommandLineInput();
    IValidations validation = new Validations();
    private static final int TEAM_SIZE = 5;
    private static final int SKATERS_SIZE = 3;
    private static final int GOALIE_SIZE = 2;

    public SettleTeamRoster(League leagueObj)
    {
        this.leagueObj = leagueObj;
        aiTradingObj = new AITrading(leagueObj);
    }

    public void settleTeam(Team team) {
        int noOfPlayers = team.getPlayers().size();
        List<Player> playerList = team.getPlayers();
        List<Integer> playerPositionFlag = aiTradingObj.findPlayerPositions(playerList);
        int noOfForward = playerPositionFlag.get(AITrading.Position.FORWARD.ordinal());
        int noOfDefense = playerPositionFlag.get(AITrading.Position.DEFENSE.ordinal());
        int noOfGoalies = playerPositionFlag.get(AITrading.Position.GOALIE.ordinal());
        int noOfSkaters = noOfForward + noOfDefense;
        if(noOfPlayers > TEAM_SIZE)
        {
            if(noOfGoalies > GOALIE_SIZE)
            {
                int noOfGoaliesToBeDropped = noOfGoalies - GOALIE_SIZE;
                for(int i=0; i<noOfGoaliesToBeDropped; i++)
                {
                    dropPlayer(playerList, AITrading.Position.GOALIE);
                }
            }
            if(noOfSkaters > SKATERS_SIZE)
            {
                int noOfSkatersToBeDropped =  noOfSkaters - SKATERS_SIZE;
                for(int i=0; i<noOfSkatersToBeDropped; i++)
                {
                    dropPlayer(playerList, AITrading.Position.DEFENSE);
                    // Dropping defense players as it should be 6
                }
            }
        }
        else if (noOfPlayers < TEAM_SIZE)
        {
            if(noOfGoalies < GOALIE_SIZE)
            {
                int noOfGoaliesToBeHired = GOALIE_SIZE - noOfGoalies;
                for(int i=0; i<noOfGoaliesToBeHired; i++)
                {
                    hirePlayer(playerList,  AITrading.Position.GOALIE);
                }
            }
            if(noOfSkaters < SKATERS_SIZE)
            {
                int noOfSkatersToBeHired = SKATERS_SIZE - noOfSkaters;
                for(int i=0; i<noOfSkatersToBeHired; i++)
                {
                    hirePlayer(playerList, AITrading.Position.FORWARD);
                    // Hiring forward players as it should be 12
                }
            }
        }
    }

    public void dropPlayer(List<Player> playerList, AITrading.Position playerPosition)
    {
        Player p = new Player();
        p.setPosition(playerPosition.toString());
        p.dropPlayerToFreeAgent(leagueObj,playerList);
    }

    public void hirePlayer(List<Player> playerList, AITrading.Position playerPosition)
    {
        Player p = new Player();
        p.setPosition(playerPosition.toString());
        p.replacePlayerWithFreeAgent(leagueObj,playerList);
    }

    public void settleTeamUser(Team team)
    {
        int noOfPlayers = team.getPlayers().size();
        List<Player> playerList = team.getPlayers();
        List<Integer> playerPositionFlag = aiTradingObj.findPlayerPositions(playerList);
        int noOfForward = playerPositionFlag.get(AITrading.Position.FORWARD.ordinal());
        int noOfDefense = playerPositionFlag.get(AITrading.Position.DEFENSE.ordinal());
        int noOfGoalies = playerPositionFlag.get(AITrading.Position.GOALIE.ordinal());
        int noOfSkaters = noOfForward + noOfDefense;
        if(noOfPlayers > TEAM_SIZE)
        {
            if(noOfGoalies > GOALIE_SIZE)
            {
                int noOfGoaliesToBeDropped = noOfGoalies - GOALIE_SIZE;
                for(int i=0; i<noOfGoaliesToBeDropped; i++)
                {
                    dropPlayerUser(playerList, AITrading.Position.GOALIE);
                }
            }
            if(noOfSkaters > SKATERS_SIZE)
            {
                int noOfSkatersToBeDropped =  noOfSkaters - SKATERS_SIZE;
                for(int i=0; i<noOfSkatersToBeDropped; i++)
                {
                    dropPlayerUser(playerList, AITrading.Position.DEFENSE);
                    // Dropping defense players as it should be 6
                }
            }
        }
        else if (noOfPlayers < TEAM_SIZE)
        {
            if(noOfGoalies < GOALIE_SIZE)
            {
                int noOfGoaliesToBeHired = GOALIE_SIZE - noOfGoalies;
                for(int i=0; i<noOfGoaliesToBeHired; i++)
                {
                    display.displayListOfPLayers(leagueObj);
                    hirePlayerUser(team, leagueObj);
                }
            }
            if(noOfSkaters < SKATERS_SIZE)
            {
                int noOfSkatersToBeHired = SKATERS_SIZE - noOfSkaters;
                for(int i=0; i<noOfSkatersToBeHired; i++)
                {
//                    hirePlayerUser(playerList, AITrading.Position.FORWARD);
                    // Hiring forward players as it should be 12
                }
            }
        }
    }

    public void dropPlayerUser(List<Player> playerList, AITrading.Position playerPosition)
    {

    }

    public void hirePlayerUser(Team newTeam, League league)
    {

    }
}
