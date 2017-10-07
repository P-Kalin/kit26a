package ua.khpi.oop.malokhvii05.util.algorithms.search;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

public class ExponentialSearch<T> extends AbstractSearchAlgorithm<T> {

    private SearchInRangeAlgorithm<T> searchInRangeAlgorithm;

    static {
        SearchAlgorithmFactory.registerAlgorithm("exponential-search",
                ExponentialSearch.class);
    }

    public ExponentialSearch(
            AbstractSearchInRangeAlgorithm<T> searchInRangeAlgorithm) {
        super(searchInRangeAlgorithm.getComparator());
        this.searchInRangeAlgorithm = searchInRangeAlgorithm;
    }

    public ExponentialSearch(Comparator<T> comparator) {
        super(comparator);
        searchInRangeAlgorithm = (AbstractSearchInRangeAlgorithm<T>) SearchAlgorithmFactory
                .getAlgorithm("binary-search", comparator);
    }

    @Override
    public int search(Array<T> array, T value) {
        if (!isValidArray(array)) {
            return lastFoundIndex;
        }

        int index = 1;
        int arraySize = array.size();
        while (index < arraySize
                && comparator.compare(array.get(index), value) <= -1) {
            index = index << 1;
        }

        return searchInRangeAlgorithm.search(array, value, index >> 1,
                Math.min(index, arraySize));
    }

    public SearchInRangeAlgorithm<T> getSearchInRangeAlgorithm() {
        return searchInRangeAlgorithm;
    }

    public void setSearchInRangeAlgorithm(
            SearchInRangeAlgorithm<T> searchInRangeAlgorithm) {
        this.searchInRangeAlgorithm = searchInRangeAlgorithm;
    }
}
