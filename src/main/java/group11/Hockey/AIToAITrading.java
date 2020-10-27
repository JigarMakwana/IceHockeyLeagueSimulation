package group11.Hockey;
import group11.Hockey.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIToAITrading implements ITradingEligibility, IGenerateTradeOffers, IResolveTradeOffers {
    protected League leagueObj;
    public AIToAITrading(League leagueObj) {
        this.leagueObj = leagueObj;
    }

    public int getLossPoint(){
        return leagueObj.getGamePlayConfig().getTrading().getLossPoint();
    }

    public float getRandomTradeOfferChance(){
        return leagueObj.getGamePlayConfig().getTrading().getRandomTradeOfferChance();
    }

    @Override
    public List<Team> determineEligibleTeams() {
//        System.out.println("------- ** In determineEligibleTeams ** -------");
        int lossPointCutOff = getLossPoint();
        List<Team> eligibleTeamList = new ArrayList<Team>();
        List<Conference> conferenceList = leagueObj.getConferences();
        for (Conference conference : conferenceList) {
            List<Division> divisionList = conference.getDivisions();
            for (Division division : divisionList) {
                List<Team> teamList = division.getTeams();
                for (Team team : teamList) {
                    //TODO Mocking LossPoint for teams as Simulation is not ready
                    if(team.getTeamName().equalsIgnoreCase("Boston") ||
                            team.getTeamName().equalsIgnoreCase("Miami")) {
                        team.setLossPoint(2);
                    }
//                    System.out.println(team.getTeamName() + "'s LossPoint is " + team.getLossPoint());
                    if(team.getLossPoint() == this.getLossPoint()) {
                        eligibleTeamList.add(team);
                    }
                }
            }
        }
        System.out.println("------- ** Eligible Teams ** -------");
        for (Team team : eligibleTeamList) {
            System.out.println(team.getTeamName());
        }
        return eligibleTeamList;
    }

    @Override
    public float generateRandomNumber() {
        Random rand = new Random();
        float randomTradeOfferChance = rand.nextFloat();
        return randomTradeOfferChance;
    }

    @Override
    public void generateTradeOffers() {
        List<Team> eligibleTeamList = determineEligibleTeams();
        //Implementation of generating random trade offer
        for (Team team : eligibleTeamList) {
//            if(this.generateRandomNumber() == this.getRandomTradeOfferChance()) {
                System.out.println("Generating Trade for " + team.getTeamName() );
                List<Player> weakestPlayerList = findWeakestPlayers(team);
                List<Team> eligibleTeamListTemp = eligibleTeamList;
                eligibleTeamListTemp.set(0, team);
                for (int i =1; i < eligibleTeamListTemp.size(); i++){
                    List<Player> strongestPlayerList = findStrongestPlayers(eligibleTeamListTemp.get(i));
                }
                generateTradeOfferForTeam(team, eligibleTeamList);
//            }
        }
    }

    @Override
    public void generateTradeOfferForTeam(Team team, List<Team> eligibleTeamList) {
        for (Team t : eligibleTeamList) {
            System.out.println("------- ** Other teams ** -------");
            System.out.println(t.getTeamName());
        }
    }

    @Override
    public List<Player> findWeakestPlayers(Team team) {
        List<Player> playerList = team.getPlayers();


        return playerList;
    }

    @Override
    public List<Player> findStrongestPlayers(Team team) {
        List<Player> playerList = team.getPlayers();

        return playerList;
    }

    @Override
    public void rejectTrade() {

    }

    @Override
    public void acceptTrade() {

    }


    @Override
    public void displayTradeStatistics() {

    }
}
