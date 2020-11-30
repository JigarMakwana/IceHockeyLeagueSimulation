/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.RandomNumGenerator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Random;

public class RandomIntGenerator implements IRandomIntGenerator {
    private static Logger logger = LogManager.getLogger(RandomIntGenerator.class);

    public int generateRandomNo(int i) {
        logger.debug("Entered generateRandomNo()");
        Random rand = new Random();
        int randomInt = rand.nextInt(i);
        return randomInt;
    }
}
