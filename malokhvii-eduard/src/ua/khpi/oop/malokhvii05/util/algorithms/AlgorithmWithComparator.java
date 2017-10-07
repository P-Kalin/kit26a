package ua.khpi.oop.malokhvii05.util.algorithms;

import java.util.Comparator;

public interface AlgorithmWithComparator<T> extends Algorithm<T> {

    void setComparator(Comparator<T> comparator);

    Comparator<T> getComparator();
}
