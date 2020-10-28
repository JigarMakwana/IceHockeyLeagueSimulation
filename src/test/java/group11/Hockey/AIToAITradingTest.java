package group11.Hockey;

import group11.Hockey.models.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.List;

class AIToAITradingTest {
    private League league;
    private List<Team> teams;
    private List<Player> players;
    @BeforeClass
    public void init() {
        TradingModelMock leagueModel = new TradingModelMock();
        this.league = leagueModel.getLeagueInfo();
        List<Conference> conferences = league.getConferences();
        List<Division> divisions = conferences.get(0).getDivisions();
        this.teams = divisions.get(0).getTeams();
        this.players = teams.get(0).getPlayers();
    }

    @Test
    void determineEligibleTeams() {
    }

    @Test
    void generateRandomNumber() {
    }

    @Test
    void generateTradeOffers() {
    }

    @Test
    void offerTrade() {
    }

    @Test
    void findWeakestPlayers() {
    }

    @Test
    void findStrongestPlayers() {
    }

    @Test
    void rejectTrade() {
    }

    @Test
    void acceptTrade() {
    }

    @Test
    void displayTradeStatistics() {
    }

    @Test
    void testGenerateTradeOffers() {
    }

    @Test
    void testDetermineEligibleTeams() {
    }

    @Test
    void testGenerateRandomNumber() {
    }

    @Test
    void testFindWeakestPlayers() {
    }

    @Test
    void testFindStrongestPlayers() {
    }

    @Test
    void findPlayerPositions() {
    }

    @Test
    void testOfferTrade() {
    }

    @Test
    void testDisplayTradeStatistics() {
    }

    @Test
    void displayPlayers() {
    }

    @Test
    void resolveTrade() {
    }

    @Test
    void strengthSum() {
    }

    @Test
    void testRejectTrade() {
    }

    @Test
    void testAcceptTrade() {
    }

    @Test
    void resetLossPoints() { ;
        this.teams.get(0).setLossPoint(0);
        Assert.assertEquals(0, this.teams.get(0).getLossPoint());
    }

    @Test
    void checkTeamConstrains() {
    }

    @Test
    void dropPlayer() {
    }

    @Test
    void hirePlayer() {
    }

    @Test
    void settleTrade() {
    }
}
