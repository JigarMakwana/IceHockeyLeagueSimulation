/*
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading.RandomNumGenerator;

import java.util.Random;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RandomFloatGenerator implements IRandomNoGenerator {

	private static Logger logger = LogManager.getLogger(RandomFloatGenerator.class);
    public float generateRandomFloat() {
    	logger.info("Entered generateRandomFloat()");
        Random rand = new Random();
        float randomFloat = rand.nextFloat();
        return randomFloat;
    }
}
