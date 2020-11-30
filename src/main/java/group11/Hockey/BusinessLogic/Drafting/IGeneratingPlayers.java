package group11.Hockey.BusinessLogic.Drafting;

import java.util.List;

import group11.Hockey.BusinessLogic.models.Player;

public interface IGeneratingPlayers {

	public List<Player> generatePlayers(int numbersOfPlayersToGenerate);
}
