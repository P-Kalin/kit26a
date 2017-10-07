package ua.khpi.oop.malokhvii05.util.algorithms.search;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

public class LinearWithBarrierSearch<T> extends AbstractSearchAlgorithm<T> {

    static {
        SearchAlgorithmFactory.registerAlgorithm("linear-with-barrier-search",
                LinearWithBarrierSearch.class);
    }

    public LinearWithBarrierSearch(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public int search(Array<T> array, T value) {
        if (!isValidArray(array)) {
            return lastFoundIndex;
        }

        if (comparator.compare(array.getLast(), value) != 0) {
            array.setLast(value);
            for (lastFoundIndex = 0;
                    comparator.compare(array.get(lastFoundIndex), value) != 0;
                    lastFoundIndex++)
                ;
        } else {
            return array.size() - 1;
        }

        if (lastFoundIndex < array.size()) {
            return lastFoundIndex;
        }

        return indexNotFound();
    }
}
