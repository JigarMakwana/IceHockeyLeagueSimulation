package group11.Hockey.BusinessLogic.Trading.RandomNumGenerator;

public class RandomNoFactory {
    public RandomNoFactory() {
        super();
    }
    public static IRandomNoGenerator makeRandomFloatGenerator(){
        return new RandomFloatGenerator();
    }
}
