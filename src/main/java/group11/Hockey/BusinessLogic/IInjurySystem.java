/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic;

import group11.Hockey.BusinessLogic.models.ITeam;

public interface IInjurySystem {
	public void setInjuryToPlayers(ITeam team);

	public boolean determainIsPlayerInjured();

	public int determainNumberOfDaysOfInjury();
}
