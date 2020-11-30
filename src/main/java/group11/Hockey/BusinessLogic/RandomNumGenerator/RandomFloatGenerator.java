/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.RandomNumGenerator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Random;

public class RandomFloatGenerator implements IRandomFloatGenerator {
    private static Logger logger = LogManager.getLogger(RandomFloatGenerator.class);

    public float generateRandomNo() {
        logger.debug("Entered generateRandomNo()");
        Random rand = new Random();
        float randomFloat = rand.nextFloat();
        return randomFloat;
    }
}
