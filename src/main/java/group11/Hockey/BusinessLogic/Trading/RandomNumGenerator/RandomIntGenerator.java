/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.Trading.RandomNumGenerator;

import java.util.Random;

public class RandomIntGenerator implements IRandomIntGenerator {
    public int generateRandomNo(int i) {
        Random rand = new Random();
        int randomInt = rand.nextInt(i);
        return randomInt;
    }
}
