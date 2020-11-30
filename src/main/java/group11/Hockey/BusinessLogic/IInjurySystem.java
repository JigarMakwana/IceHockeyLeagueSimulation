/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import group11.Hockey.BusinessLogic.Enums.Positions;
import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Roster.Interfaces.IRoster;

public interface IInjurySystem {
	public void setInjuryToPlayers(ITeam team);

	public boolean determainIsPlayerInjured();

	public int determainNumberOfDaysOfInjury();

	void settleRecoveredPlayer(IRoster roster, IPlayer recoveredPlayer);

	void settleInjuredPlayer(IRoster roster, IPlayer injuredPlayer);

	boolean isInjuredSwappingPossible(IRoster roster, IPlayer injuredPlayer);

	Positions findInjuredPlayerPosition(IPlayer injuredPlayer);

	boolean isUnInjuredPlayerAvailable(IRoster roster, Positions position);
}
