/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading;

import group11.Hockey.BusinessLogic.RandomNumGenerator.IRandomFloatGenerator;
import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeInitializer;
import group11.Hockey.BusinessLogic.Trading.TradingInterfaces.ITradeConfig;
import group11.Hockey.BusinessLogic.RandomNumGenerator.RandomNoFactory;
import group11.Hockey.BusinessLogic.models.*;

import java.util.ArrayList;
import java.util.List;

public class TradeInitializer implements ITradeInitializer {
	private ILeague leagueObj;
	private ITrading tradingConfig;
	private List<ITeam> eligibleTeams = new ArrayList<>();

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
				List<ITeam> teamList = division.getTeams();
				for (ITeam team : teamList) {
					if ((team.getLosses() >= lossPointCutOff)) {
						eligibleTeams.add(team);
					}
				}
			}
		}
	}

    private boolean isRandomOfferChanceSuccess() {
		IRandomFloatGenerator randomFloatGenerator = RandomNoFactory.makeRandomFloatGenerator();
        float randomTradeOfferChance = randomFloatGenerator.generateRandomNo();
        if(randomTradeOfferChance < tradingConfig.getRandomTradeOfferChance()){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ITradeConfig getTradingConfig(){
        ITradeConfig configTrading = TradingFactory.makeTradeConfig(
                tradingConfig.getLossPoint(),
                tradingConfig.getRandomTradeOfferChance(),
                tradingConfig.getMaxPlayersPerTrade(),
                tradingConfig.getRandomAcceptanceChance(),
                tradingConfig.getGmTable()
        );
        return configTrading;
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
}
