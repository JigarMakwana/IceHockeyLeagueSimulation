package group11.Hockey.db.League;

import group11.Hockey.BusinessLogic.models.ILeague;
import group11.Hockey.db.Deserialize;
import group11.Hockey.db.IDeserialize;
import group11.Hockey.db.ISerialize;
import group11.Hockey.db.Serialize;

public class LeagueSerialisation implements ILeagueDb{

	
	private static ILeagueDb  leagueSerialisationInstance = null;
	
	private LeagueSerialisation() {
	}
	
	public static ILeagueDb getInstance() {
		if(leagueSerialisationInstance == null) {
			leagueSerialisationInstance = new LeagueSerialisation();
		}
		return leagueSerialisationInstance;
	}


	@Override
	public boolean insertLeagueInDb(ILeague league) {
		ISerialize serialize = Serialize.getInstance();
		serialize.serializeLeagueObject(league);
		return false;
	}

	@Override
	public ILeague loadLeague() {
		IDeserialize deserialize = Deserialize.getInstance();
		ILeague league = deserialize.deSerializeLeagueObjectFromFile();
		return league;
	}
	
	

}
