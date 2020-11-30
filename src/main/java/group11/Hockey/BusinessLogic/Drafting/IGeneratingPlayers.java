package group11.Hockey.BusinessLogic.Drafting;

import java.util.List;

import group11.Hockey.BusinessLogic.models.IPlayer;

public interface IGeneratingPlayers {

	public List<IPlayer> generatePlayers(int numbersOfPlayersToGenerate);
}
