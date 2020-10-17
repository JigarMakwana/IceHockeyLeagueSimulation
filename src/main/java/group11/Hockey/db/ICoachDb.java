package group11.Hockey.db;

public interface ICoachDb {
	public boolean insertCoaches(String leagueName,String coachName, float skating, float shooting, float checking, float saving);
}
