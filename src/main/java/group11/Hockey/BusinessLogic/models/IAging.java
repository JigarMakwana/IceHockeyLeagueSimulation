/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.models;

public interface IAging {
	public int getAverageRetirementAge();

	public int getMaximumAge();
	
	public float getStatDecayChance();
	
	public void setStatDecayChance(float statDecayChance);
	
	
}
