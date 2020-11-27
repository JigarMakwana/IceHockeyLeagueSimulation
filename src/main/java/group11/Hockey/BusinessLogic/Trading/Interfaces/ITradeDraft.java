package group11.Hockey.BusinessLogic.Trading.Interfaces;

import group11.Hockey.BusinessLogic.models.Team;

import java.util.List;
import java.util.Map;

public interface ITradeDraft {
    void clearDraftTradeTracker();
    List<Map<Team, Map<Team, List<Integer>>>> getDraftTradeTracker();
    ITradeCharter generateTradeOffer(List<Team> eligibleTeamList);
}
