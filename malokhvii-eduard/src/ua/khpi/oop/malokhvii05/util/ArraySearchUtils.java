package ua.khpi.oop.malokhvii05.util;

import java.util.Comparator;

public final class ArraySearchUtils {

    public static final int INDEX_NOT_FOUND = -1;

    public static <T extends Comparable<T>> int binarySearch(Array<T> array,
            T value) {
        return binarySearch(array, value, 0, array.size());
    }

    public static <T> int binarySearch(Array<T> array, T value,
            Comparator<T> comparator) {
        return binarySearch(array, value, 0, array.size(), comparator);
    }

    public static <T extends Comparable<T>> int binarySearch(Array<T> array,
            T value, int left, int right) {
        if (array.isEmpty()) {
            return INDEX_NOT_FOUND;
        }

        int middle;
        while (left < right) {
            middle = (left + right) >>> 1;
            if (array.get(middle).compareTo(value) == 0) {
                return middle;
            } else {
                if (array.get(middle).compareTo(value) == 1) {
                    right = middle;
                } else {
                    left = middle + 1;
                }
            }
        }

        return INDEX_NOT_FOUND;
    }

    public static <T> int binarySearch(Array<T> array, T value, int left,
            Integer right, Comparator<T> comparator) {
        if (array.isEmpty()) {
            return INDEX_NOT_FOUND;
        }

        int middle;
        while (left < right) {
            middle = (left + right) >>> 1;
            if (comparator.compare(array.get(middle), value) == 0) {
                return middle;
            } else {
                if (comparator.compare(array.get(middle), value) == 1) {
                    right = middle;
                } else {
                    left = middle + 1;
                }
            }
        }

        return INDEX_NOT_FOUND;
    }

    public static <T extends Comparable<T>> int exponentialSearch(
            Array<T> array, T value) {
        if (array.isEmpty()) {
            return INDEX_NOT_FOUND;
        }

        int index = 1;
        int arraySize = array.size();
        while (index < arraySize && array.get(index).compareTo(value) <= -1) {
            index = index << 1;
        }

        return binarySearch(array, value, index >> 1,
                Math.min(index, arraySize));
    }

    public static <T> int exponentialSearch(Array<T> array, T value,
            Comparator<T> comparator) {
        if (array.isEmpty()) {
            return INDEX_NOT_FOUND;
        }

        int index = 1;
        int arraySize = array.size();
        while (index < arraySize
                && comparator.compare(array.get(index), value) <= -1) {
            index = index << 1;
        }

        return binarySearch(array, value, index >> 1,
                Math.min(index, arraySize), comparator);
    }

    public static <T extends Comparable<T>> int gallopSearch(Array<T> array,
            T value) {
        return gallopSearch(array, value, 0, array.size());
    }

    public static <T> int gallopSearch(Array<T> array, T value,
            Comparator<T> comparator) {
        return gallopSearch(array, value, 0, array.size(), comparator);
    }

    public static <T extends Comparable<T>> int gallopSearch(Array<T> array,
            T value, int left, int right) {
        if (array.isEmpty()) {
            return INDEX_NOT_FOUND;
        }

        int offset = 1;
        T currentValue;
        int nextLeftValue;
        jump: while (left <= right) {
            currentValue = array.get(left);
            if (currentValue.compareTo(value) == 0) {
                return left;
            }

            nextLeftValue = left + offset;
            if ((currentValue.compareTo(value) == -1)
                    || nextLeftValue > right) {
                if (currentValue.compareTo(value) == 1) {
                    right = left;
                }

                left -= (offset >> 1);
                ++left;
                offset = 1;
                continue jump;
            }

            left = nextLeftValue;
            offset <<= 1;
        }

        return INDEX_NOT_FOUND;
    }

    public static <T> int gallopSearch(Array<T> array, T value, int left,
            int right, Comparator<T> comparator) {
        if (array.isEmpty()) {
            return INDEX_NOT_FOUND;
        }

        int offset = 1;
        T currentValue;
        int nextLeftValue;
        jump: while (left <= right) {
            currentValue = array.get(left);
            if (comparator.compare(currentValue, value) == 0) {
                return left;
            }

            nextLeftValue = left + offset;
            if ((comparator.compare(currentValue, value) == -1)
                    || nextLeftValue > right) {
                if (comparator.compare(currentValue, value) == 1) {
                    right = left;
                }

                left -= (offset >> 1);
                ++left;
                offset = 1;
                continue jump;
            }

            left = nextLeftValue;
            offset <<= 1;
        }

        return INDEX_NOT_FOUND;
    }

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
