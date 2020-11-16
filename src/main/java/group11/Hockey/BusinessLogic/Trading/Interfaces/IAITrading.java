package group11.Hockey.BusinessLogic.Trading.Interfaces;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.BusinessLogic.models.Team;

import java.util.List;

public interface IAITrading {
    void generateTradeOffers();
    List<Team> determineEligibleTeams();
    void resolveAIToAITrade(Team team1, List<Player> offeredPlayerList,
                                   Team team2, List<Player> requestedPlayerList);
    void resolveAIToUserTrade(Team team1, List<Player> offeredPlayerList,
                                     Team team2, List<Player> requestedPlayerList);
    void rejectTrade(Team team);
    void acceptTrade(Team team1, List<Player> offeredPlayerList,
                            Team team2, List<Player> requestedPlayerList);
    void resetLossPoints(Team team);
    void settleTeamAfterTrade(Team team);
}
