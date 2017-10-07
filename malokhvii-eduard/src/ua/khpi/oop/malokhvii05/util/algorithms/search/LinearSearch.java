package ua.khpi.oop.malokhvii05.util.algorithms.search;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

public class LinearSearch<T> extends AbstractSearchInRangeAlgorithm<T> {

    static {
        SearchAlgorithmFactory.registerAlgorithm("linear-search",
                LinearSearch.class);
    }

    public LinearSearch(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public int search(Array<T> array, T value, int left, int right) {
        if (!isValidRange(array, left, right)) {
            return lastFoundIndex;
        }

        for (lastFoundIndex = left; lastFoundIndex < right; lastFoundIndex++) {
            if (comparator.compare(array.get(lastFoundIndex), value) == 0) {
                return lastFoundIndex;
            }
        }

        return indexNotFound();
    }
}
