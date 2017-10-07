package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

public class ShellSort<T> extends AbstractSortAlgorithm<T> {

    static {
        SortAlgorithmFactory.registerAlgorithm("shell-sort", ShellSort.class);
    }

    public ShellSort(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void sort(Array<T> array) {
        int arraySize = array.size();
        T comparableValue;
        int gap;

        int i;
        int j;
        for (gap = arraySize >> 1; gap > 0; gap >>= 1) {
            for (i = gap; i < arraySize; i++) {
                comparableValue = array.get(i);
                for (j = i; j >= gap && comparator.compare(array.get(j - gap),
                        comparableValue) == sortOrderKey; j -= gap) {
                    array.set(j, array.get(j - gap));
                }
                array.set(j, comparableValue);
            }
        }
    }
}
