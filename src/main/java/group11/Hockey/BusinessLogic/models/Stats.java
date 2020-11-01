package group11.Hockey.BusinessLogic.models;

public class Stats {

	private float skating;
	private float shooting;
	private float checking;
	private float saving;

	public Stats(float skating, float shooting, float checking, float saving) {
		super();
		this.skating = skating;
		this.shooting = shooting;
		this.checking = checking;
		this.saving = saving;
	}

	public Stats() {
	}

	public float getSkating() {
		return skating;
	}

	public float getShooting() {
		return shooting;
	}

	public float getChecking() {
		return checking;
	}

	public float getSaving() {
		return saving;
	}

	public void setSkating(float skating) {
		this.skating = skating;
	}

	public void setShooting(float shooting) {
		this.shooting = shooting;
	}

	public void setChecking(float checking) {
		this.checking = checking;
	}

	public void setSaving(float saving) {
		this.saving = saving;
	}

}
