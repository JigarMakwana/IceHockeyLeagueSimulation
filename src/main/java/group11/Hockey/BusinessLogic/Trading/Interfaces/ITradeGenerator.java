/*
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading.Interfaces;

import group11.Hockey.BusinessLogic.models.Team;

import java.util.List;

public interface ITradeGenerator {
    ITradeCharter generateTradeOffer(List<Team> teamList);
    ITradeCharter tradeDraftPicks(List<Team> teamList);
}
