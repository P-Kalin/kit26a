package ua.khpi.oop.malokhvii05.util;

import java.util.Comparator;

public final class ArraySearchUtils {

    public static final int INDEX_NOT_FOUND = -1;

    public static <T extends Comparable<T>> int linearSearch(Array<T> array,
            T value) {
        int index;
        int arraySize = array.size();
        for (index = 0; index < arraySize; index++) {
            if (array.get(index).compareTo(value) == 0) {
                return index;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static <T> int linearSearch(Array<T> array, T value,
            Comparator<T> comporator) {
        int index;
        int arraySize = array.size();
        for (index = 0; index < arraySize; index++) {
            if (comporator.compare(array.get(index), value) == 0) {
                return index;
            }
        }
        return INDEX_NOT_FOUND;
    }
}
