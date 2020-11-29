package group11.Hockey.BusinessLogic.models;

import java.util.List;

public interface IGeneralManager {
	public String getName();

	public String getPersonality();

	public void setName(String name);

	public void setPersonality(String personality);

	public boolean insertManager(List<GeneralManager> managerList);
}
