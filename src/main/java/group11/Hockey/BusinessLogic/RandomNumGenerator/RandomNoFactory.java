/**
 * Author: Jigar Makwana B00842568
 */
package group11.Hockey.BusinessLogic.RandomNumGenerator;

public class RandomNoFactory {
    public RandomNoFactory() {
        super();
    }

    public static IRandomFloatGenerator makeRandomFloatGenerator(){ return new RandomFloatGenerator(); }

    public static IRandomIntGenerator makeRandomIntGenerator(){
        return new RandomIntGenerator();
    }
}
