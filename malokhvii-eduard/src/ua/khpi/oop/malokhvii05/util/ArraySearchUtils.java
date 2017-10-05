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

    public static <T extends Comparable<T>> int linearSearchWitBarrier(
            Array<T> array, T value) {
        if (array.isEmpty()) {
            return INDEX_NOT_FOUND;
        }

        int index;
        if (array.getLast().compareTo(value) != 0) {
            array.setLast(value);
            for (index = 0; array.get(index).compareTo(value) != 0; index++)
                ;
        } else {
            return array.size() - 1;
        }

        if (index < array.size()) {
            return index;
        }

        return INDEX_NOT_FOUND;
    }

    public static <T> int linearSearchWithBarrier(Array<T> array, T value,
            Comparator<T> comparator) {
        if (array.isEmpty()) {
            return INDEX_NOT_FOUND;
        }

        int index;
        if (comparator.compare(array.getLast(), value) != 0) {
            array.setLast(value);
            for (index = 0; comparator.compare(array.get(index), value) != 0;
                    index++)
                ;
        } else {
            return array.size() - 1;
        }

        if (index < array.size()) {
            return index;
        }

        return INDEX_NOT_FOUND;
    }
}
