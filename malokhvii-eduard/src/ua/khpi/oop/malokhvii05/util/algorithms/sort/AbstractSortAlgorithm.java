package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

public abstract class AbstractSortAlgorithm<T> implements SortAlgorithm<T> {

    protected static final int INTERNAL_ASCENDING_KEY = 1;
    protected static final int INTERNAL_DESCENDING_KEY = -1;

    protected Comparator<T> comparator;
    protected int sortOrderKey;

    public AbstractSortAlgorithm(Comparator<T> comparator) {
        this.comparator = comparator;
        setSortOrder(SortOrder.ASCENDING);
    }

    protected void swap(Array<T> array, int left, int right) {
        T temp = array.get(left);
        array.set(left, array.get(right));
        array.set(right, temp);
    }

    protected boolean isReversed(int sortOrderKey, int reversedSortOrderKey) {
        return sortOrderKey == reversedSortOrderKey ? true : false;
    }

    protected int sortOrderToKey(SortOrder sortOrder) {
        return sortOrder == SortOrder.ASCENDING ? INTERNAL_ASCENDING_KEY
                : INTERNAL_DESCENDING_KEY;
    }

    @Override
    public Comparator<T> getComparator() {
        return comparator;
    }

    @Override
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public SortOrder getSortOrder() {
        return sortOrderKey == INTERNAL_ASCENDING_KEY ? SortOrder.ASCENDING
                : SortOrder.DESCENDING;
    }

    @Override
    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrderKey = sortOrder == SortOrder.ASCENDING
                ? INTERNAL_ASCENDING_KEY
                : INTERNAL_DESCENDING_KEY;
    }

    @Override
    public boolean isNull() {
        return false;
    }
}
