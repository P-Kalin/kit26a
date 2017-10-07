package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;
import ua.khpi.oop.malokhvii05.util.algorithms.AbstractAlgorithmWithComparator;

public abstract class AbstractSortAlgorithm<T>
        extends AbstractAlgorithmWithComparator<T> implements SortAlgorithm<T> {

    protected static final int INTERNAL_ASCENDING_KEY = 1;
    protected static final int INTERNAL_DESCENDING_KEY = -1;

    protected int sortOrderKey;

    public AbstractSortAlgorithm(Comparator<T> comparator) {
        super(comparator);
        setSortOrder(Order.ASCENDING);
    }

    protected void swap(Array<T> array, int left, int right) {
        T temp = array.get(left);
        array.set(left, array.get(right));
        array.set(right, temp);
    }

    protected boolean isReversed(int sortOrderKey, int reversedSortOrderKey) {
        return sortOrderKey == reversedSortOrderKey ? true : false;
    }

    protected int sortOrderToKey(Order sortOrder) {
        return sortOrder == Order.ASCENDING ? INTERNAL_ASCENDING_KEY
                : INTERNAL_DESCENDING_KEY;
    }

    @Override
    public Order getSortOrder() {
        return sortOrderKey == INTERNAL_ASCENDING_KEY ? Order.ASCENDING
                : Order.DESCENDING;
    }

    @Override
    public void setSortOrder(Order sortOrder) {
        this.sortOrderKey = sortOrder == Order.ASCENDING
                ? INTERNAL_ASCENDING_KEY
                : INTERNAL_DESCENDING_KEY;
    }

    @Override
    public boolean isNull() {
        return false;
    }
}
