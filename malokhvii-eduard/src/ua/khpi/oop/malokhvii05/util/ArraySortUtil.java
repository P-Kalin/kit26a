package ua.khpi.oop.malokhvii05.util;

import java.util.Comparator;

public final class ArraySortUtil {

    public static final int ASCENDING_SORT_ORDER = 1;
    public static final int DESCENDING_SORT_ORDER = -1;

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> void bottomUpMerge(Object[] array,
            Object[] mergeBuffer, int left, int chunkSize, boolean isReversed) {
        int right = left + chunkSize;
        int end = Math.min(left + chunkSize * 2 - 1, mergeBuffer.length - 1);

        int leftIndex = left;
        int rightIndex = right;

        for (int i = 0; i <= end - left; i++) {
            if (leftIndex < right && (rightIndex > end
                    || isReversed == ((Comparable<T>) array[leftIndex])
                            .compareTo((T) array[rightIndex]) >= 0)) {
                mergeBuffer[i] = array[leftIndex++];
            } else {
                mergeBuffer[i] = array[rightIndex++];
            }
        }

        for (int i = left; i <= end; i++) {
            array[i] = mergeBuffer[i - left];
        }
    }

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

    @SuppressWarnings("unchecked")
    private static <T> void buttomUpMerge(Object[] array, Object[] mergeBuffer,
            int left, int chunkSize, Comparator<T> comparator,
            boolean isReversed) {
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

    public static <T> void buttomUpMergeSort(Array<T> array, Comparator<T> comparator,
            int sortOrder) {
        boolean isReversed = sortOrder == DESCENDING_SORT_ORDER ? true : false;

        Object[] arrayData = array.getData();
        Object[] mergeBuffer = new Object[array.size()];

        int chunkSize = 1;
        while (chunkSize < mergeBuffer.length) {
            int i = 0;
            while (i < mergeBuffer.length - chunkSize) {
                buttomUpMerge(arrayData, mergeBuffer, i, chunkSize, comparator,
                        isReversed);
                i += chunkSize << 1;
            }
            chunkSize <<= 1;
        }
    }

    public static <T extends Comparable<T>> void buttomUpMergeSort(Array<T> array,
            int sortOrder) {
        boolean isReversed = sortOrder == DESCENDING_SORT_ORDER ? true : false;

        Object[] arrayData = array.getData();
        Object[] mergeBuffer = new Object[array.size()];

        int chunkSize = 1;
        while (chunkSize < mergeBuffer.length) {
            int i = 0;
            while (i < mergeBuffer.length - chunkSize) {
                bottomUpMerge(arrayData, mergeBuffer, i, chunkSize, isReversed);
                i += chunkSize << 1;
            }
            chunkSize <<= 1;
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

    private static <T> int partition(Array<T> array, int left, int right,
            Comparator<T> comparator, int sortOrder) {
        boolean isReversed = sortOrder == ASCENDING_SORT_ORDER ? true : false;
        T highValue = array.get(right);

        int i = (left - 1);
        int j;
        for (j = left; j <= right - 1; j++) {
            if (isReversed == comparator.compare(array.get(j),
                    highValue) <= -1) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, ++i, right);
        return i;
    }

    private static <T extends Comparable<T>> int partition(Array<T> array,
            int left, int right, int sortOrder) {
        boolean isReversed = sortOrder == ASCENDING_SORT_ORDER ? true : false;
        T highValue = array.get(right);

        int i = (left - 1);
        int j;
        for (j = left; j <= right - 1; j++) {
            if (isReversed == array.get(j).compareTo(highValue) <= -1) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, ++i, right);
        return i;
    }

    public static <T> void quickSort(Array<T> array, Comparator<T> comparator,
            int sortOrder) {
        int low = 0;
        int high = array.size() - 1;

        int[] stack = new int[high - low + 1];
        int top = -1;

        stack[++top] = low;
        stack[++top] = high;

        while (top >= 0) {
            high = stack[top--];
            low = stack[top--];

            int partition = partition(array, low, high, comparator, sortOrder);

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

    public static <T extends Comparable<T>> void quickSort(Array<T> array,
            int sortOrder) {
        int low = 0;
        int high = array.size() - 1;

        int[] stack = new int[high - low + 1];
        int top = -1;

        stack[++top] = low;
        stack[++top] = high;

        while (top >= 0) {
            high = stack[top--];
            low = stack[top--];

            int partition = partition(array, low, high, sortOrder);

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
