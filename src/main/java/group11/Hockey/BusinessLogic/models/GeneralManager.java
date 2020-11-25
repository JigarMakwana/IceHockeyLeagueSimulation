package group11.Hockey.BusinessLogic.models;

import java.util.List;

import group11.Hockey.db.IManagerDb;

public class GeneralManager {
	private String name;
	private String personality;
	private IManagerDb managerDb;
	private String leagueName;

	public GeneralManager(String name) {
		super();
		this.name = name;
	}

	public GeneralManager(String name, String personality) {
		super();
		this.name = name;
		this.personality = personality;
	}

	public GeneralManager(String leagueName, IManagerDb managerDb) {
		super();
		this.leagueName = leagueName;
		this.managerDb = managerDb;
	}

	public GeneralManager() {
	}

	public String getName() {
		return name;
	}

	public String getPersonality() {
		return personality;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPersonality(String personality) {
		this.personality = personality;
	}

	public boolean insertManager(List<GeneralManager> managerList) {
		boolean insertedToManager = false;
		if (managerList == null || managerList.size() == 0) {
			insertedToManager = true;
		} else {
			for (GeneralManager manager : managerList) {
				insertedToManager = managerDb.insertManager(leagueName, manager.getName());
			}
		}
		return insertedToManager;
	}

}
