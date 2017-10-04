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

    private static <T> void swap(Array<T> array, int left, int right) {
        T temp = array.get(left);
        array.set(left, array.get(right));
        array.set(right, temp);
    }

    private ArraySortUtil() {

    }
}
