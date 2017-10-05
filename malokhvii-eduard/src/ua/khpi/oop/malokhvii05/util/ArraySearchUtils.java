package ua.khpi.oop.malokhvii05.util;

import java.util.Comparator;

public final class ArraySearchUtils {

    public static final int INDEX_NOT_FOUND = -1;

    public static <T extends Number> int interpolationSearch(Array<T> array,
            T value) {
        return interpolationSearch(array, value, 0, array.size());
    }

    public static <T extends Number> int interpolationSearch(Array<T> array,
            T value, int left, int right) {
        T leftValue;
        T rightValue;
        T middleValue;

        int arraySize = array.size();

        float average;
        int difference;
        int steps;
        int middle;
        while (left < right && left >= 0 && right <= arraySize) {
            leftValue = array.get(left);
            rightValue = array.get(right - 1);

            average = ((float) rightValue.intValue() - leftValue.intValue())
                    / (right - left);
            middle = left;
            if (average != 0) {
                difference = value.intValue() - leftValue.intValue();
                steps = (int) (difference / average);
                middle = left + steps;

                if (middle >= right) {
                    middle = (left + right) / 2;
                }
            }

            middleValue = array.get(middle);
            if (middleValue.equals(value)) {
                return middle;
            } else if (value.intValue() < middleValue.intValue()) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        if (array.get(left).equals(value)) {
            return left;
        }

        return INDEX_NOT_FOUND;
    }

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
