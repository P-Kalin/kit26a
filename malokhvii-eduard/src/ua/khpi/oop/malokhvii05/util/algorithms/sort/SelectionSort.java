package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

public final class SelectionSort<T> extends AbstractSortAlgorithm<T> {

    private boolean isReversed;

    static {
        SortAlgorithmFactory.registerAlgorithm("selection-sort",
                SelectionSort.class);
    }

    public SelectionSort(Comparator<T> comparator) {
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
        int arraySize = array.size();

        int minimumIndex;
        int i;
        int j;
        for (i = 0; i < arraySize - 1; i++) {
            minimumIndex = i;
            for (j = i + 1; j < arraySize; j++) {
                if (isReversed == (comparator.compare(array.get(j),
                        array.get(minimumIndex)) < sortOrderKey)) {
                    minimumIndex = j;
                }
            }
            swap(array, minimumIndex, i);
        }
    }
}
