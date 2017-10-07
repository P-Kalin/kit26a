package ua.khpi.oop.malokhvii05.util.algorithms.search;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

public class BinarySearch<T> extends AbstractSearchInRangeAlgorithm<T> {

    static {
        SearchAlgorithmFactory.registerAlgorithm("binary-search",
                BinarySearch.class);
        SearchAlgorithmFactory.setDefaultAlgorithm(BinarySearch.class);
    }

    public BinarySearch(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public int search(Array<T> array, T value, int left, int right) {
        if (!isValidArray(array) || !isValidRange(array, left, right)) {
            return lastFoundIndex;
        }

        T middleValue;
        while (left < right) {
            lastFoundIndex = (left + right) >>> 1;
            middleValue = array.get(lastFoundIndex);

            if (comparator.compare(middleValue, value) == 1) {
                right = lastFoundIndex;
            } else if (comparator.compare(middleValue, value) == 0) {
                return lastFoundIndex;
            } else {
                left = lastFoundIndex + 1;
            }
        }

        return indexNotFound();
    }
}
