package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.IRandomNoGenerator;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradeInitializer;
import group11.Hockey.BusinessLogic.Trading.Interfaces.ITradingConfig;
import group11.Hockey.BusinessLogic.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TradeInitializer implements ITradeInitializer {
	private ILeague leagueObj;
	private ITrading tradingConfig;
	private List<Team> eligibleTeams = new ArrayList<>();

	public TradeInitializer(ILeague leagueObj) {
		this.leagueObj = leagueObj;
		IGameplayConfig gameConfig = this.leagueObj.getGamePlayConfig();
		this.tradingConfig = gameConfig.getTrading();
		setEligibleTeams();
	}

	private void setEligibleTeams() {
		int lossPointCutOff = tradingConfig.getLossPoint();
		List<IConference> conferenceList = leagueObj.getConferences();
		for (IConference conference : conferenceList) {
			List<Division> divisionList = conference.getDivisions();
			for (Division division : divisionList) {
				List<Team> teamList = division.getTeams();
				for (Team team : teamList) {
					if ((team.getLosses() >= lossPointCutOff)) {
						eligibleTeams.add(team);
					}
				}
			}
		}
	}

	public ITradingConfig getTradingConfig() {
		ITradingConfig configTrading = DefaultHockeyFactory.makeConfigTrading(tradingConfig.getLossPoint(),
				tradingConfig.getRandomTradeOfferChance(), tradingConfig.getMaxPlayersPerTrade(),
				tradingConfig.getRandomAcceptanceChance(), tradingConfig.getGmTable());
		return configTrading;
	}

	private boolean isRandomOfferChanceSuccess() {
		IRandomNoGenerator randomFloatGenerator = DefaultHockeyFactory.makeRandomFloatGenerator();
		float randomTradeOfferChance = randomFloatGenerator.generateRandomFloat();
		if (randomTradeOfferChance < tradingConfig.getRandomTradeOfferChance()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isTradePossible(Team team) {
		if (team.isUserTeam()) {
			return false;
		} else if (isRandomOfferChanceSuccess()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Team> getEligibleTeams() {
		return eligibleTeams;
	}
}
