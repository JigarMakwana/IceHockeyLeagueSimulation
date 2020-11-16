package group11.Hockey.BusinessLogic;

import java.util.Random;

public class RandomNoGenerator implements IRandomNoGenerator{

    public float generateRandomFloat() {
        Random rand = new Random();
        float randomTradeOfferChance = rand.nextFloat();
        return randomTradeOfferChance;
    }
}
