/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.models;

public interface IGameplayConfig {
	public IAging getAging();

	public IGameResolver getGameResolver();

	public IInjuries getInjuries();

	public ITraining getTraining();

	public ITrading getTrading();
}
