package ua.khpi.oop.malokhvii05.util.algorithms.search;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;
import ua.khpi.oop.malokhvii05.util.algorithms.AbstractAlgorithmWithComparator;

public abstract class AbstractSearchAlgorithm<T> extends
        AbstractAlgorithmWithComparator<T> implements SearchAlgorithm<T> {

    protected int lastFoundIndex;

    public AbstractSearchAlgorithm(Comparator<T> comparator) {
        super(comparator);
    }

    protected int indexNotFound() {
        lastFoundIndex = INDEX_NOT_FOUND;
        return lastFoundIndex;
    }

    protected boolean isValidArray(Array<T> array) {
        if (array.isEmpty()) {
            lastFoundIndex = INDEX_NOT_FOUND;
            return false;
        }
        return true;
    }

    @Override
    public int getLastFoundIndex() {
        return lastFoundIndex;
    }

    @Override
    public boolean isNull() {
        return false;
    }
}
