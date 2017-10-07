package ua.khpi.oop.malokhvii05.util.algorithms.search;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

public final class FibonacciSearch<T> extends AbstractSearchAlgorithm<T> {

    private int fibonacciNumberM2;
    private int fibonacciNumberM1;
    private int currentFibonacciNumber;

    static {
        SearchAlgorithmFactory.registerAlgorithm("fibonacci-search",
                FibonacciSearch.class);
    }

    public FibonacciSearch(Comparator<T> comparator) {
        super(comparator);
    }

    private void prepareFibonacciNumbers(int arraySize) {
        fibonacciNumberM1 = 1;
        fibonacciNumberM2 = 0;
        currentFibonacciNumber = fibonacciNumberM2 + fibonacciNumberM1;

        while (currentFibonacciNumber < arraySize) {
            fibonacciNumberM2 = fibonacciNumberM1;
            fibonacciNumberM1 = currentFibonacciNumber;
            currentFibonacciNumber = fibonacciNumberM2 + fibonacciNumberM1;
        }
    }

    private void cutSubarrayFromOffsetToIndex() {
        currentFibonacciNumber = fibonacciNumberM1;
        fibonacciNumberM1 = fibonacciNumberM2;
        fibonacciNumberM2 = currentFibonacciNumber - fibonacciNumberM1;
    }

    private void cutSubarrayAfterIndexPlusOne() {
        currentFibonacciNumber = fibonacciNumberM2;
        fibonacciNumberM1 -= fibonacciNumberM2;
        fibonacciNumberM2 = currentFibonacciNumber - fibonacciNumberM1;
    }

    @Override
    public int search(Array<T> array, T value) {
        if (!isValidArray(array)) {
            return lastFoundIndex;
        }

        int subarraySize = array.size() - 1;
        prepareFibonacciNumbers(subarraySize);

        int offset = -1;
        T currentValue;
        while (currentFibonacciNumber > 1) {
            lastFoundIndex = Math.min(offset + fibonacciNumberM2,
                    subarraySize - 1);
            currentValue = array.get(lastFoundIndex);
            if (comparator.compare(currentValue, value) == -1) {
                cutSubarrayFromOffsetToIndex();
                offset = lastFoundIndex;
            } else if (comparator.compare(currentValue, value) == 1) {
                cutSubarrayAfterIndexPlusOne();
            } else {
                return lastFoundIndex;
            }
        }

        if (fibonacciNumberM1 != 0
                && comparator.compare(array.get(offset ^ 1), value) == 0) {
            return offset ^ 1;
        }

        return indexNotFound();
    }
}
