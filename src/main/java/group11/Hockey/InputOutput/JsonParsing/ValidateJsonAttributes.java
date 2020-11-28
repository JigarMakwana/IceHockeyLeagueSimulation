package group11.Hockey.InputOutput.JsonParsing;

import java.util.List;

import group11.Hockey.BusinessLogic.models.IPlayer;

public abstract class ValidateJsonAttributes {

	public ValidateJsonAttributes() {
		super();
	}

	public boolean isNameAlreadyExists(List<String> list, String name) {

		if (list.contains(name.toLowerCase())) {
			return true;
		} else {
			list.add(name.toLowerCase());
			return false;
		}
	}

	public boolean hasInvalidCaptain(List<IPlayer> playersList) {
		int captains = 0;
		for (IPlayer player : playersList) {
			if (player.getCaptain()) {
				captains = captains + 1;
			}
		}

		return captains == 0 || captains > 1;
	}

}
