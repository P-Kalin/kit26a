package ua.khpi.oop.malokhvii05.util.algorithms.search;

import ua.khpi.oop.malokhvii05.util.Array;
import ua.khpi.oop.malokhvii05.util.algorithms.NullAlgorithmWithComparator;

public final class NullSearchAlgorithm<T> extends NullAlgorithmWithComparator<T>
        implements SearchAlgorithm<T>, SearchInRangeAlgorithm<T> {

    @SuppressWarnings("rawtypes")
    public static final SearchAlgorithm INSTANCE = new NullSearchAlgorithm();

    private NullSearchAlgorithm() {

    }

    @Override
    public int search(Array<T> array, T value) {
        return INDEX_NOT_FOUND;
    }

    @Override
    public int getLastFoundIndex() {
        return INDEX_NOT_FOUND;
    }

    @Override
    public int search(Array<T> array, T value, int left, int right) {
        return INDEX_NOT_FOUND;
    }
}
