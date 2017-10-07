package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

public final class TopDownMergeSort<T> extends AbstractSortAlgorithm<T> {

    private boolean isReversed;

    static {
        SortAlgorithmFactory.registerSortAlgorithm("top-down-merge-sort",
                TopDownMergeSort.class);
    }

    public TopDownMergeSort(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void sort(Array<T> array) {
        Object[] arrayData = array.getData();
        Object[] sortedArrayData = mergeSort(array.toArray());
        System.arraycopy(sortedArrayData, 0, arrayData, 0,
                arrayData.length - (arrayData.length - array.size()));
    }

    private Object[] mergeSort(Object[] array) {
        if (array.length <= 1) {
            return array;
        }

        int middle = array.length >> 1;

        Object[] left = new Object[middle];
        Object[] right = new Object[array.length - middle];

        System.arraycopy(array, 0, left, 0, left.length);
        System.arraycopy(array, left.length, right, 0, right.length);

        left = mergeSort(left);
        right = mergeSort(right);

        return mergeSlices(left, right);
    }

    @Override
    public void setSortOrder(SortOrder sortOrder) {
        isReversed = isReversed(sortOrderToKey(sortOrder),
                INTERNAL_ASCENDING_KEY);
        super.setSortOrder(sortOrder);
    }

    @SuppressWarnings("unchecked")
    private Object[] mergeSlices(Object[] left, Object[] right) {
        Object[] mergeBuffer = new Integer[left.length + right.length];

        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;
        while (leftIndex < left.length || rightIndex < right.length) {
            if (leftIndex < left.length && rightIndex < right.length) {
                if (isReversed == (comparator.compare((T) left[leftIndex],
                        (T) right[rightIndex])) < 1) {
                    mergeBuffer[resultIndex++] = left[leftIndex++];
                } else {
                    mergeBuffer[resultIndex++] = right[rightIndex++];
                }
            } else if (leftIndex < left.length) {
                mergeBuffer[resultIndex++] = left[leftIndex++];
            } else if (rightIndex < right.length) {
                mergeBuffer[resultIndex++] = right[rightIndex++];
            }
        }
        return mergeBuffer;
    }
}
