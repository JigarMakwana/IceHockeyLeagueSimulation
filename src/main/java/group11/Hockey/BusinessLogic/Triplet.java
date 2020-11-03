package group11.Hockey.BusinessLogic;

import java.util.Comparator;

public class Triplet<T, U, V> implements Comparator<Triplet>
{
    private final T first;
    private final U second;
    private final V third;

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
        return "(" + first + ", " + second + ", " + third + ")";
    }

    public static <T, U, V> Triplet <T, U, V> of(T a, U b, V c)
    {
        return new Triplet <>(a, b, c);
    }

    @Override
    public int compare(Triplet o1, Triplet o2) {
        return Float.compare((Float)o1.getThird(), (Float)o2.getThird());
    }
}
