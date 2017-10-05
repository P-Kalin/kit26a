package ua.khpi.oop.malokhvii05.util;

import java.util.Comparator;

public final class ArraySortUtils {

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

    @SuppressWarnings("unchecked")
    private static <T> void bottomUpMerge(Object[] array, Object[] mergeBuffer,
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

    public static <T> void bottomUpMergeSort(Array<T> array,
            Comparator<T> comparator, int sortOrder) {
        boolean isReversed = sortOrder == DESCENDING_SORT_ORDER ? true : false;

        Object[] arrayData = array.getData();
        Object[] mergeBuffer = new Object[array.size()];

        int chunkSize = 1;
        while (chunkSize < mergeBuffer.length) {
            int i = 0;
            while (i < mergeBuffer.length - chunkSize) {
                bottomUpMerge(arrayData, mergeBuffer, i, chunkSize, comparator,
                        isReversed);
                i += chunkSize << 1;
            }
            chunkSize <<= 1;
        }
    }

    public static <T extends Comparable<T>> void bottomUpMergeSort(
            Array<T> array, int sortOrder) {
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

    public static <T extends Comparable<T>> void insertionSort(Array<T> array,
            int sortOrder) {
        boolean isReversed = sortOrder == ASCENDING_SORT_ORDER ? true : false;

        T key;
        int arraySize = array.size();

        int i;
        int j;
        for (i = 1; i < arraySize; i++) {
            key = array.get(i);
            j = i - 1;
            while (j >= 0 && isReversed == (array.get(j).compareTo(key) > 0)) {
                array.set(j + 1, array.get(j));
                j = j - 1;
            }
            array.set(j + 1, key);
        }
    }

    public static <T> void insertionSort(Array<T> array, int sortOrder,
            Comparator<T> comparator) {
        boolean isReversed = sortOrder == ASCENDING_SORT_ORDER ? true : false;

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

    public static <T extends Comparable<T>> void selectionSort(Array<T> array,
            int sortOrder) {
        boolean isReversed = sortOrder == ASCENDING_SORT_ORDER ? true : false;
        int arraySize = array.size();

        int minimumIndex;
        int i;
        int j;
        for (i = 0; i < arraySize - 1; i++) {
            minimumIndex = i;
            for (j = i + 1; j < arraySize; j++) {
                if (isReversed == (array.get(j)
                        .compareTo(array.get(minimumIndex)) < sortOrder)) {
                    minimumIndex = j;
                }
            }
            swap(array, minimumIndex, i);
        }
    }

    public static <T> void selectionSort(Array<T> array, int sortOrder,
            Comparator<T> comporator) {
        boolean isReversed = sortOrder == ASCENDING_SORT_ORDER ? true : false;
        int arraySize = array.size();

        int minimumIndex;
        int i;
        int j;
        for (i = 0; i < arraySize - 1; i++) {
            minimumIndex = i;
            for (j = i + 1; j < arraySize; j++) {
                if (isReversed == (comporator.compare(array.get(j),
                        array.get(minimumIndex)) < 0)) {
                    minimumIndex = j;
                }
            }
            swap(array, minimumIndex, i);
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

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> Object[] topDownMergeSlices(
            Object[] left, Object[] right, boolean isReversed) {
        Object[] mergeBuffer = new Integer[left.length + right.length];

        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;
        while (leftIndex < left.length || rightIndex < right.length) {
            if (leftIndex < left.length && rightIndex < right.length) {
                if (isReversed == ((T) left[leftIndex])
                        .compareTo((T) right[rightIndex]) < 1) {
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

    @SuppressWarnings("unchecked")
    private static <T> Object[] topDownMergeSlices(Object[] left,
            Object[] right, boolean isReversed, Comparator<T> comparator) {
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

    public static <T extends Comparable<T>> void topDownMergeSort(
            Array<T> array, int sortOrder) {
        boolean isReversed = sortOrder == ASCENDING_SORT_ORDER ? true : false;

        Object[] arrayData = array.getData();
        Object[] sortedArrayData = topDownMergeSort(array.toArray(),
                isReversed);
        System.arraycopy(sortedArrayData, 0, arrayData, 0,
                arrayData.length - (arrayData.length - array.size()));
    }

    public static <T> void topDownMergeSort(Array<T> array, int sortOrder,
            Comparator<T> comparator) {
        boolean isReversed = sortOrder == ASCENDING_SORT_ORDER ? true : false;

        Object[] arrayData = array.getData();
        Object[] sortedArrayData = topDownMergeSort(array.toArray(), isReversed,
                comparator);
        System.arraycopy(sortedArrayData, 0, arrayData, 0,
                arrayData.length - (arrayData.length - array.size()));
    }

    private static <T extends Comparable<T>> Object[] topDownMergeSort(
            Object[] array, boolean isReversed) {
        if (array.length <= 1) {
            return array;
        }

        int middle = array.length >> 1;

        Object[] left = new Object[middle];
        Object[] right = new Object[array.length - middle];

        System.arraycopy(array, 0, left, 0, left.length);
        System.arraycopy(array, left.length, right, 0, right.length);

        left = topDownMergeSort(left, isReversed);
        right = topDownMergeSort(right, isReversed);

        return topDownMergeSlices(left, right, isReversed);
    }

    private static <T> Object[] topDownMergeSort(Object[] array,
            boolean isReversed, Comparator<T> comparator) {
        if (array.length <= 1) {
            return array;
        }

        int middle = array.length >> 1;

        Object[] left = new Object[middle];
        Object[] right = new Object[array.length - middle];

        System.arraycopy(array, 0, left, 0, left.length);
        System.arraycopy(array, left.length, right, 0, right.length);

        left = topDownMergeSort(left, isReversed, comparator);
        right = topDownMergeSort(right, isReversed, comparator);

        return topDownMergeSlices(left, right, isReversed, comparator);
    }

    private ArraySortUtils() {

    }
}
