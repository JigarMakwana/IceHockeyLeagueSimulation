/**
 * Author: Jigar Makwana B00842568
 * Generic Triplet
 * Used in Trading to hold three objects of different types
 */
package group11.Hockey.BusinessLogic.Trading.TradingTriplet;

import java.util.Comparator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Triplet<T, U, V> implements Comparator<Triplet>
{
    private final T first;
    private final U second;
    private final V third;
    private static Logger logger = LogManager.getLogger(Triplet.class);

    public Triplet(T first, U second, V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T getFirst() { return first; }
    public U getSecond() { return second; }
    public V getThird() { return third; }

    @Override
    public String toString()
    {
    	logger.info("Entered toString()");
        return "(" + first + ", " + second + ", " + third + ")";
    }

    public static <T, U, V> Triplet <T, U, V> of(T a, U b, V c)
    {
    	logger.info("Entered Triplet()");
        return new Triplet <>(a, b, c);
    }

    @Override
    public int compare(Triplet o1, Triplet o2) {
    	logger.info("Entered compare()");
        return Float.compare((Float)o1.getThird(), (Float)o2.getThird());
    }
}
