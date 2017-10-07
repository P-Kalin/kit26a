package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

public class BubbleSort<T extends Comparable<T>>
        extends AbstractSortAlgorithm<T> {

    static {
        SortAlgorithmFactory.registerAlgorithm("bubble-sort", BubbleSort.class);
    }

    public BubbleSort(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void sort(Array<T> array) {
        int externalLoopBarrier = array.size();
        boolean isSwapped;

        int i;
        int j;
        for (i = 0; i < externalLoopBarrier; i++) {
            isSwapped = false;
            for (j = 0; j < externalLoopBarrier - i - 1; j++) {
                if ((comparator.compare(array.get(j),
                        array.get(j + 1)) == sortOrderKey)) {
                    swap(array, j, j + 1);
                    isSwapped = true;
                }
            }

            if (isSwapped == false) {
                break;
            }
        }
    }
}
