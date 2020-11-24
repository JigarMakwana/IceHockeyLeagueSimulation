package group11.Hockey.BusinessLogic;

import java.util.Random;

public class RandomFloatGenerator implements IRandomNoGenerator{

    public float generateRandomFloat() {
        Random rand = new Random();
        float randomFloat = rand.nextFloat();
        return randomFloat;
    }
}
