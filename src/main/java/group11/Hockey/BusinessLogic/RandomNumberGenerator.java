package group11.Hockey.BusinessLogic;

import java.util.Random;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RandomNumberGenerator implements IRandomNoGenerator {

	private static Logger logger = LogManager.getLogger(RandomNumberGenerator.class);

	public float generateRandomFloat() {
		logger.info("Entered generateRandomFloat()");
		Random rand = new Random();
		float randomFloat = rand.nextFloat();
		return randomFloat;
	}

	public int generateRandomInt(int number) {
		int rand = new Random().nextInt(number);
		return rand;
	}
}
