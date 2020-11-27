/*
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading.Interfaces;

import group11.Hockey.BusinessLogic.models.Team;

import java.util.List;
import java.util.Map;

public interface ITradeDraft {
    void clearDraftTradeTracker();
    List<Map<Team, Map<Team, List<Boolean>>>> getDraftTradeTracker();
    ITradeCharter generateTradeOffer(List<Team> eligibleTeamList);
}
