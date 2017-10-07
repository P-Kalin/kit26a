package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

public class GnomeSort<T> extends AbstractSortAlgorithm<T> {

    private boolean isReversed;

    static {
        SortAlgorithmFactory.registerSortAlgorithm("gnome-sort",
                GnomeSort.class);
    }

    public GnomeSort(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void setSortOrder(SortOrder sortOrder) {
        isReversed = isReversed(sortOrderToKey(sortOrder),
                INTERNAL_ASCENDING_KEY);
        super.setSortOrder(sortOrder);
    }

    @Override
    public void sort(Array<T> array) {
        int index = 1;
        int nextIndex = 2;
        int arraySize = array.size();

        while (index < arraySize) {
            if (isReversed == (comparator.compare(array.get(index - 1),
                    array.get(index)) <= 0)) {
                index = nextIndex;
                nextIndex++;
            } else {
                swap(array, index - 1, index--);
                index = (index == 0) ? nextIndex++ : index;
            }
        }
    }
}
