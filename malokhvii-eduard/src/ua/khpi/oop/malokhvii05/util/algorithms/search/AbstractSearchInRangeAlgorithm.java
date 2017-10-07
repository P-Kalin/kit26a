package ua.khpi.oop.malokhvii05.util.algorithms.search;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

public abstract class AbstractSearchInRangeAlgorithm<T> extends
        AbstractSearchAlgorithm<T> implements SearchInRangeAlgorithm<T> {

    public AbstractSearchInRangeAlgorithm(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public int search(Array<T> array, T value) {
        return search(array, value, 0, array.size());
    }

    protected boolean isValidRange(Array<T> array, int left, int right) {
        int arraySize = array.size();
        boolean isValidRange = true;
        if (right < left) {
            isValidRange = false;
        } else if (right > arraySize) {
            isValidRange = false;
        } else if (left < 0) {
            isValidRange = false;
        }
        indexNotFound();
        return isValidRange;
    }
}
