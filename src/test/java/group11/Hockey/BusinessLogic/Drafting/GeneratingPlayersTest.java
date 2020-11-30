package group11.Hockey.BusinessLogic.Drafting;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import group11.Hockey.BusinessLogic.DefaultHockeyFactory;
import group11.Hockey.BusinessLogic.Drafting.IGeneratingPlayers;
import group11.Hockey.BusinessLogic.models.IPlayer;
/**
 * 
 * @author jatinpartaprana
 *
 */
public class GeneratingPlayersTest {
	IPlayer player ;
	
	@Before
	public void setPlayer() {
		IGeneratingPlayers generatePlayer = DefaultHockeyFactory.makeGeneratePlayer();
		player = generatePlayer.populatePlayer("Player");
	}
	
	@Test
	public void generatePlayersTest() {
		IGeneratingPlayers generatePlayer = DefaultHockeyFactory.makeGeneratePlayer();
		Assert.assertTrue(generatePlayer.generatePlayers(2).size() == 2);
	}
	@Test
	public void populatePlayerTest() {
		IGeneratingPlayers generatePlayer = DefaultHockeyFactory.makeGeneratePlayer();
		Assert.assertTrue(generatePlayer.populatePlayer("Player").getPlayerName() == "Player");
	}
	
	@Test
	public void setStatForForwardPlayerTest() {
		IGeneratingPlayers generatePlayer = DefaultHockeyFactory.makeGeneratePlayer();
		generatePlayer.setStatForForwardPlayer(player);
		Assert.assertTrue(player.getPlayerName() == "Player");
	}
	
	@Test
	public void setStatDefensePlayerTest() {
		IGeneratingPlayers generatePlayer = DefaultHockeyFactory.makeGeneratePlayer();
		generatePlayer.setStatDefensePlayer(player);
		Assert.assertTrue(player.getPlayerName() == "Player");
	}
	
	@Test
	public void setStatGoaliePlayerTest() {
		IGeneratingPlayers generatePlayer = DefaultHockeyFactory.makeGeneratePlayer();
		generatePlayer.setStatGoaliePlayer(player);
		Assert.assertTrue(player.getPlayerName() == "Player");
	}
	
	@Test
	public void getNumberInRangeTest() {
		IGeneratingPlayers generatePlayer = DefaultHockeyFactory.makeGeneratePlayer();
		int min = 10;
		int max =20;
		generatePlayer.getNumberInRange(min, max);
		Assert.assertTrue(player.getPlayerName() == "Player");
	}
	
}
