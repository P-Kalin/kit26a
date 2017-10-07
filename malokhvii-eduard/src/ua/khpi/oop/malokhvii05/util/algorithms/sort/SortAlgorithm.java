package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import ua.khpi.oop.malokhvii05.util.Array;
import ua.khpi.oop.malokhvii05.util.algorithms.AlgorithmWithComparator;

public interface SortAlgorithm<T> extends AlgorithmWithComparator<T> {

    public enum Order {
        ASCENDING, DESCENDING;
    }

    void setSortOrder(Order sortOrder);

    Order getSortOrder();

    void sort(Array<T> array);
}
