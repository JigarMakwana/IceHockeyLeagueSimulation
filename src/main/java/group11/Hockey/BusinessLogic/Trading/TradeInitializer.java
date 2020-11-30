/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.RandomNumGenerator.IRandomFloatGenerator;
import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeInitializer;
import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeConfig;
import group11.Hockey.BusinessLogic.RandomNumGenerator.RandomNoFactory;
import group11.Hockey.BusinessLogic.models.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TradeInitializer implements ITradeInitializer {
	private ILeague leagueObj;
	private ITrading tradingConfig;
	private List<ITeam> eligibleTeams = new ArrayList<>();
	private static Logger logger = LogManager.getLogger(TradeInitializer.class);

	public TradeInitializer(ILeague leagueObj) {
		this.leagueObj = leagueObj;
		IGameplayConfig gameConfig = this.leagueObj.getGamePlayConfig();
		this.tradingConfig = gameConfig.getTrading();
		setEligibleTeams();
	}

	@Override
	public ITradeConfig getTradingConfig(){
		return TradingFactory.makeTradeConfig(tradingConfig.getLossPoint(), tradingConfig.getRandomTradeOfferChance(),
				tradingConfig.getMaxPlayersPerTrade(), tradingConfig.getRandomAcceptanceChance(), tradingConfig.getGmTable());
	}

	@Override
	public boolean isTradePossible(ITeam team) {
		if(team.isUserTeam()){
			return false;
		} else if (isRandomOfferChanceSuccess()){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<ITeam> getEligibleTeams() {
		return eligibleTeams;
	}

	private boolean isRandomOfferChanceSuccess() {
		logger.debug("Entered isRandomOfferChanceSuccess()");
		IRandomFloatGenerator randomFloatGenerator = RandomNoFactory.makeRandomFloatGenerator();
		float randomTradeOfferChance = randomFloatGenerator.generateRandomNo();
		if(randomTradeOfferChance < tradingConfig.getRandomTradeOfferChance()){
			return true;
		} else {
			return false;
		}
	}

	private void setEligibleTeams() {
		logger.debug("Entered setEligibleTeams()");
		int lossPointCutOff = tradingConfig.getLossPoint();
		List<IConference> conferenceList = leagueObj.getConferences();
		for (IConference conference : conferenceList) {
			List<IDivision> divisionList = conference.getDivisions();
			for (IDivision division : divisionList) {
				List<ITeam> teamList = division.getTeams();
				for (ITeam team : teamList) {
					if ((team.getLosses() >= lossPointCutOff)) {
						eligibleTeams.add(team);
					}
				}
			}
		}
	}
}
