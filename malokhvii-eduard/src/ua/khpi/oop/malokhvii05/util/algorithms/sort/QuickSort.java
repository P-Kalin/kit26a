package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

public final class QuickSort<T> extends AbstractSortAlgorithm<T> {

    private boolean isReversed;

    public QuickSort(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void sort(Array<T> array) {
        int low = 0;
        int high = array.size() - 1;

        int[] stack = new int[high - low + 1];
        int top = -1;

        stack[++top] = low;
        stack[++top] = high;

        while (top >= 0) {
            high = stack[top--];
            low = stack[top--];

            int partition = partition(array, low, high);

            if (partition - 1 > low) {
                stack[++top] = low;
                stack[++top] = partition - 1;
            }

            if (partition + 1 < high) {
                stack[++top] = partition + 1;
                stack[++top] = high;
            }
        }
    }

    @Override
    public void setSortOrder(SortOrder sortOrder) {
        isReversed = isReversed(sortOrderToKey(sortOrder),
                INTERNAL_ASCENDING_KEY);
        super.setSortOrder(sortOrder);
    }

    private int partition(Array<T> array, int left, int right) {
        T highValue = array.get(right);

        int i = (left - 1);
        int j;
        for (j = left; j <= right - 1; j++) {
            if (isReversed == comparator.compare(array.get(j),
                    highValue) <= -1) {
                swap(array, ++i, j);
            }
        }

        swap(array, ++i, right);
        return i;
    }
}
