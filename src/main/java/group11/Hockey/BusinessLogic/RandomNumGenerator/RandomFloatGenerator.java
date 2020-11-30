/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.RandomNumGenerator;

import java.util.Random;

public class RandomFloatGenerator implements IRandomFloatGenerator {

    public float generateRandomNo() {
        Random rand = new Random();
        float randomFloat = rand.nextFloat();
        return randomFloat;
    }
}
