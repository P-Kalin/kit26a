package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

public final class NullSortAlgorithm<T> implements SortAlgorithm<T> {

    @SuppressWarnings("rawtypes")
    public static final SortAlgorithm INSTANCE = new NullSortAlgorithm();;

    private NullSortAlgorithm() {

    }

    @Override
    public void setComparator(Comparator<T> comparator) {

    }

    @Override
    public Comparator<T> getComparator() {
        return new Comparator<T>() {

            @Override
            public int compare(T left, T right) {
                return -1;
            }

        };
    }

    @Override
    public void setSortOrder(SortOrder sortOrder) {

    }

    @Override
    public SortOrder getSortOrder() {
        return SortOrder.ASCENDING;
    }

    @Override
    public void sort(Array<T> array) {

    }

    @Override
    public boolean isNull() {
        return true;
    }
}
