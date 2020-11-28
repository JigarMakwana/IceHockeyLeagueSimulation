package group11.Hockey.BusinessLogic.Trading.Interfaces;

import java.util.List;
import java.util.Map;

import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Team;

public interface ITradeDraft {
    void clearDraftTradeTracker();
    List<Map<ITeam, Map<ITeam, List<Boolean>>>> getDraftTradeTracker();
    ITradeCharter generateTradeOffer(List<ITeam> eligibleTeamList);
}
