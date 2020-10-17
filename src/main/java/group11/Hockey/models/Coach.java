package group11.Hockey.models;

import java.util.List;

import group11.Hockey.db.ICoachDb;

public class Coach extends Stats {
	private String name;
	private String leagueName;
	private ICoachDb coachDb;

	public Coach(float skating, float shooting, float checking, float saving, String name) {
		super(skating, shooting, checking, saving);
		this.name = name;
	}

	public Coach(String leagueName, ICoachDb coachDb) {
		this.leagueName = leagueName;
		this.coachDb = coachDb;
	}

	public Coach() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean insertCoaches(List<Coach> coachesList) {
		boolean insertedToCoach = false;
		if (coachesList == null || coachesList.size() == 0) {
			insertedToCoach = true;
		} else {
			for (Coach coach : coachesList) {
				insertedToCoach = coachDb.insertCoaches(leagueName, coach.getName(), coach.getSkating(),
						coach.getShooting(), coach.getChecking(), coach.getSaving());
			}
		}
		return insertedToCoach;
	}

}
