package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

public interface SortAlgorithm<T> {

    public enum SortOrder {
        ASCENDING, DESCENDING;
    }

    void setComparator(Comparator<T> comparator);

    Comparator<T> getComparator();

    void setSortOrder(SortOrder sortOrder);

    SortOrder getSortOrder();

    void sort(Array<T> array);

    boolean isNull();
}
