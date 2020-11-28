/*
 * Author: RajKumar B00849566
 */
package group11.Hockey.BusinessLogic.models;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import group11.Hockey.db.IManagerDb;

public class GeneralManager implements IGeneralManager {
	private String name;
	private String personality;
	private IManagerDb managerDb;
	private String leagueName;
	private static Logger logger = LogManager.getLogger(GeneralManager.class);

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
		logger.info("Entered getName()");
		return name;
	}

	public String getPersonality() {
		logger.info("Entered getPersonality()");
		return personality;
	}

	public void setName(String name) {
		logger.info("Entered setName()");
		this.name = name;
	}

	public void setPersonality(String personality) {
		logger.info("Entered setPersonality()");
		this.personality = personality;
	}

	public boolean insertManager(List<GeneralManager> managerList) {
		logger.info("Entered insertManager()");
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
