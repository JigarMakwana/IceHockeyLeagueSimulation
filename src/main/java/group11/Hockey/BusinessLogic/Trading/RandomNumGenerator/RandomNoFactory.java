package group11.Hockey.BusinessLogic.Trading.RandomNumGenerator;

public class RandomNoFactory {
    public RandomNoFactory() {
        super();
    }

    public static IRandomFloatGenerator makeRandomFloatGenerator(){ return new RandomFloatGenerator(); }

    public static IRandomIntGenerator makeRandomIntGenerator(){
        return new RandomIntGenerator();
    }
}
