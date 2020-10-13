package group11.Hockey;

import java.util.List;

import group11.Hockey.db.League.ILeagueDb;
import group11.Hockey.models.League;
import group11.Hockey.models.Player;

public class JsonAttributes {
	
	private ILeagueDb leagueDb;
	protected League leagueModelObj;
	
	public JsonAttributes() {
		super();
	}
	
	public JsonAttributes(ILeagueDb leagueDb) {
		super();
		this.leagueDb = leagueDb;
	}	

	public boolean isValidLeagueName(String name) {
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
