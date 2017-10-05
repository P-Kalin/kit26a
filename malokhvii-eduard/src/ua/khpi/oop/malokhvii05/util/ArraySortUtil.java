package ua.khpi.oop.malokhvii05.util;

import java.util.Comparator;

public final class ArraySortUtil {

    public static final int ASCENDING_SORT_ORDER = 1;
    public static final int DESCENDING_SORT_ORDER = -1;

    public static <T extends Comparable<T>> void bubbleSort(Array<T> array,
            int sortOrder) {
        int externalLoopBarrier = array.size();
        boolean isSwapped;
        int i;
        int j;
        for (i = 0; i < externalLoopBarrier; i++) {
            isSwapped = false;
            for (j = 0; j < externalLoopBarrier - i - 1; j++) {
                if (array.get(j).compareTo(array.get(j + 1)) == sortOrder) {
                    swap(array, j, j + 1);
                    isSwapped = true;
                }
            }

            if (isSwapped == false) {
                break;
            }
        }
    }

    public static <T> void bubbleSort(Array<T> array, int sortOrder,
            Comparator<T> comporator) {
        int externalLoopBarrier = array.size();
        boolean isSwapped;
        int i;
        int j;
        for (i = 0; i < externalLoopBarrier; i++) {
            isSwapped = false;
            for (j = 0; j < externalLoopBarrier - i - 1; j++) {
                if ((comporator.compare(array.get(j),
                        array.get(j + 1)) == sortOrder)) {
                    swap(array, j, j + 1);
                    isSwapped = true;
                }
            }

            if (isSwapped == false) {
                break;
            }
        }
    }

    private static <T> void heapify(Array<T> array, int heapSize, int index,
            Comparator<T> comparator, int sortOrder) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < heapSize && (comparator.compare(array.get(left),
                array.get(largest))) == sortOrder) {
            largest = left;
        }

        if (right < heapSize && (comparator.compare(array.get(right),
                array.get(largest))) == sortOrder) {
            largest = right;
        }

        if (largest != index) {
            swap(array, index, largest);
            heapify(array, heapSize, largest, comparator, sortOrder);
        }
    }

    private static <T extends Comparable<T>> void heapify(Array<T> array,
            int heapSize, int index, int sortOrder) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < heapSize && (array.get(left)
                .compareTo(array.get(largest))) == sortOrder) {
            largest = left;
        }

        if (right < heapSize && (array.get(right)
                .compareTo(array.get(largest))) == sortOrder) {
            largest = right;
        }

        if (largest != index) {
            swap(array, index, largest);
            heapify(array, heapSize, largest, sortOrder);
        }
    }

    public static <T> void heapSort(Array<T> array, Comparator<T> comparator,
            int sortOrder) {
        int arraySize = array.size();
        int index;
        for (index = arraySize / 2 - 1; index >= 0; index--) {
            heapify(array, arraySize, index, comparator, sortOrder);
        }

        for (index = arraySize - 1; index >= 0; index--) {
            swap(array, 0, index);
            heapify(array, index, 0, comparator, sortOrder);
        }
    }

    public static <T extends Comparable<T>> void heapSort(Array<T> array,
            int sortOrder) {
        int arraySize = array.size();
        int index;
        for (index = arraySize / 2 - 1; index >= 0; index--) {
            heapify(array, arraySize, index, sortOrder);
        }

        for (index = arraySize - 1; index >= 0; index--) {
            swap(array, 0, index);
            heapify(array, index, 0, sortOrder);
        }
    }

    public static <T extends Comparable<T>> void shellSort(Array<T> array,
            int sortOrder) {
        int arraySize = array.size();
        T temp;
        int gap;

        int i;
        int j;
        for (gap = arraySize >> 1; gap > 0; gap >>= 1) {
            for (i = gap; i < arraySize; i++) {
                temp = array.get(i);
                for (j = i; j >= gap
                        && array.get(j - gap).compareTo(temp) == sortOrder;
                        j -= gap) {
                    array.set(j, array.get(j - gap));
                }
                array.set(j, temp);
            }
        }
    }

    public static <T> void shellSort(Array<T> array, int sortOrder,
            Comparator<T> comparator) {
        int arraySize = array.size();
        T temp;
        int gap;

        int i;
        int j;
        for (gap = arraySize >> 1; gap > 0; gap >>= 1) {
            for (i = gap; i < arraySize; i++) {
                temp = array.get(i);
                for (j = i; j >= gap && comparator.compare(array.get(j - gap),
                        temp) == sortOrder; j -= gap) {
                    array.set(j, array.get(j - gap));
                }
                array.set(j, temp);
            }
        }
    }

    private static <T> void swap(Array<T> array, int left, int right) {
        T temp = array.get(left);
        array.set(left, array.get(right));
        array.set(right, temp);
    }

    private ArraySortUtil() {

    }
}
