/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.db.Coach;

public interface ICoachDb {
	public boolean insertCoaches(String leagueName,String coachName, float skating, float shooting, float checking, float saving);
}
