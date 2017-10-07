package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import ua.khpi.oop.malokhvii05.util.Array;
import ua.khpi.oop.malokhvii05.util.algorithms.NullAlgorithmWithComparator;

final class NullSortAlgorithm<T> extends NullAlgorithmWithComparator<T>
        implements SortAlgorithm<T> {

    @SuppressWarnings("rawtypes")
    public static final SortAlgorithm INSTANCE = new NullSortAlgorithm();

    private NullSortAlgorithm() {

    }

    @Override
    public void setSortOrder(Order sortOrder) {

    }

    @Override
    public Order getSortOrder() {
        return Order.ASCENDING;
    }

    @Override
    public void sort(Array<T> array) {

    }
}
