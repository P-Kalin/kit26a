package ua.khpi.oop.malokhvii05.util.algorithms;

import java.util.Comparator;

public abstract class AbstractAlgorithmWithComparator<T>
        implements AlgorithmWithComparator<T> {

    protected Comparator<T> comparator;

    public AbstractAlgorithmWithComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public Comparator<T> getComparator() {
        return comparator;
    }
}
