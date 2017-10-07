package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

public final class InsertionSort<T> extends AbstractSortAlgorithm<T> {

    private boolean isReversed;

    static {
        SortAlgorithmFactory.registerAlgorithm("insertion-sort",
                InsertionSort.class);
    }

    public InsertionSort(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void setSortOrder(Order sortOrder) {
        isReversed = isReversed(sortOrderToKey(sortOrder),
                INTERNAL_ASCENDING_KEY);
        super.setSortOrder(sortOrder);
    }

    @Override
    public void sort(Array<T> array) {
        T key;
        int arraySize = array.size();

        int i;
        int j;
        for (i = 1; i < arraySize; i++) {
            key = array.get(i);
            j = i - 1;
            while (j >= 0 && isReversed == (comparator.compare(array.get(j),
                    key) > 0)) {
                array.set(j + 1, array.get(j));
                j = j - 1;
            }
            array.set(j + 1, key);
        }
    }
}
