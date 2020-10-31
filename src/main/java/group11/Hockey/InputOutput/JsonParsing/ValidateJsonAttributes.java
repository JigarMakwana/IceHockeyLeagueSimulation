package group11.Hockey.InputOutput.JsonParsing;

import java.util.List;

import group11.Hockey.BusinessLogic.models.League;
import group11.Hockey.BusinessLogic.models.Player;
import group11.Hockey.db.League.ILeagueDb;

public class ValidateJsonAttributes {
	
	private ILeagueDb leagueDb;
	
	public ValidateJsonAttributes() {
		super();
	}
	
	public ValidateJsonAttributes(ILeagueDb leagueDb) {
		super();
		this.leagueDb = leagueDb;
	}	

	public boolean isValidLeagueName(String name) {
		League leagueModelObj = new League();
		return leagueModelObj.isLeagueNameValid(name, leagueDb);
	}

	public boolean isNameAlreadyExists(List<String> list, String name) {

		if (list.contains(name.toLowerCase())) {
			return true;
		} else {
			list.add(name.toLowerCase());
			return false;
		}
	}

	public boolean hasInvalidCaptain(List<Player> playersList) {
		int captains = 0;
		for (Player player : playersList) {
			if (player.getCaptain()) {
				captains = captains + 1;
			}
		}

		return captains == 0 || captains > 1;
	}

}
