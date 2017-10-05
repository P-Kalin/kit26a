package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

public final class BottomUpMergeSort<T> extends AbstractSortAlgorithm<T> {

    private boolean isReversed;

    public BottomUpMergeSort(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void sort(Array<T> array) {
        Object[] arrayData = array.getData();
        Object[] mergeBuffer = new Object[array.size()];

        int chunkSize = 1;
        while (chunkSize < mergeBuffer.length) {
            int i = 0;
            while (i < mergeBuffer.length - chunkSize) {
                bottomUpMerge(arrayData, mergeBuffer, i, chunkSize);
                i += chunkSize << 1;
            }
            chunkSize <<= 1;
        }
    }

    @Override
    public void setSortOrder(SortOrder sortOrder) {
        isReversed = isReversed(sortOrderToKey(sortOrder),
                INTERNAL_DESCENDING_KEY);
        super.setSortOrder(sortOrder);
    }

    @SuppressWarnings("unchecked")
    private void bottomUpMerge(Object[] array, Object[] mergeBuffer, int left,
            int chunkSize) {
        int right = left + chunkSize;
        int end = Math.min(left + chunkSize * 2 - 1, mergeBuffer.length - 1);

        int leftIndex = left;
        int rightIndex = right;

        for (int i = 0; i <= end - left; i++) {
            if (leftIndex < right && (rightIndex > end
                    || isReversed == (comparator.compare((T) array[leftIndex],
                            (T) array[rightIndex])) >= 0)) {
                mergeBuffer[i] = array[leftIndex++];
            } else {
                mergeBuffer[i] = array[rightIndex++];
            }
        }

        for (int i = left; i <= end; i++) {
            array[i] = mergeBuffer[i - left];
        }
    }
}
