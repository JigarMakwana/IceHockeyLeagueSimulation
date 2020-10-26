package group11.Hockey.models;

import java.util.List;

import group11.Hockey.db.IManagerDb;

public class GeneralManager {
	private String name;
	private IManagerDb managerDb;
	private String leagueName;

	public GeneralManager(String name) {
		super();
		this.name = name;
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
