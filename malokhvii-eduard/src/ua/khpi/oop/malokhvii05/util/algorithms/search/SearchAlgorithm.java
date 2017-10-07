package ua.khpi.oop.malokhvii05.util.algorithms.search;

import ua.khpi.oop.malokhvii05.util.Array;
import ua.khpi.oop.malokhvii05.util.algorithms.AlgorithmWithComparator;

public interface SearchAlgorithm<T> extends AlgorithmWithComparator<T> {

    public static final int INDEX_NOT_FOUND = -1;

    int search(Array<T> array, T value);

    int getLastFoundIndex();
}
