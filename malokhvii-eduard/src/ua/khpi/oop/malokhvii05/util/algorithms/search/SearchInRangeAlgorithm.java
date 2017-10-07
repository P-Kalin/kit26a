package ua.khpi.oop.malokhvii05.util.algorithms.search;

import ua.khpi.oop.malokhvii05.util.Array;
import ua.khpi.oop.malokhvii05.util.algorithms.AlgorithmWithComparator;

public interface SearchInRangeAlgorithm<T> extends AlgorithmWithComparator<T> {

    int search(Array<T> array, T value, int left, int right);
}
