/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.models;

public interface ICoach {
	public String getName();

	public float getSkating();

	public float getShooting();

	public float getChecking();

	public float getSaving();

	public void setName(String name);

	public void setChecking(float value);

	public void setSaving(float value);

	public void setShooting(float value);

	public void setSkating(float value);
}
