package group11.Hockey.BusinessLogic.Drafting;

import java.util.List;

import group11.Hockey.BusinessLogic.models.IPlayer;
import group11.Hockey.BusinessLogic.models.ITeam;
import group11.Hockey.BusinessLogic.models.Team;

public interface IDraftPlayer {

	public void draftPlayer();
	
	public List<Team> selectTeamFromRegularSeasonStandinfo(List<Team> regularSeasonTeams);
	
	public List<IPlayer> teamSettlement(ITeam team);
	
	public void populateExtraPlayerList(List<IPlayer> extraPlayers, List<IPlayer> playerList, int extraPlayersCount);
}