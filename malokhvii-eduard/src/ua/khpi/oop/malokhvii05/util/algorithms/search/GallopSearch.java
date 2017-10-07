package ua.khpi.oop.malokhvii05.util.algorithms.search;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

public class GallopSearch<T> extends AbstractSearchInRangeAlgorithm<T> {

    static {
        SearchAlgorithmFactory.registerAlgorithm("gallop-search",
                GallopSearch.class);
    }

    public GallopSearch(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public int search(Array<T> array, T value, int left, int right) {
        if (!isValidArray(array)) {
            return lastFoundIndex;
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

        return indexNotFound();
    }
}
