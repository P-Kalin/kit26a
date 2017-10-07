package ua.khpi.oop.malokhvii05.util.algorithms;

import java.util.Comparator;

public abstract class NullAlgorithmWithComparator<T> extends NullAlgorithm<T>
        implements AlgorithmWithComparator<T> {

    private final Comparator<T> defaultComparator = new DefaultComparator();

    private class DefaultComparator implements Comparator<T> {
        @Override
        public int compare(T left, T right) {
            return 0;
        }
    }

    @Override
    public void setComparator(Comparator<T> comparator) {

    }

    @Override
    public Comparator<T> getComparator() {
        return defaultComparator;
    }
}
