package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

public final class HeapSort<T> extends AbstractSortAlgorithm<T> {

    public HeapSort(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void sort(Array<T> array) {
        int arraySize = array.size();
        int index;
        for (index = arraySize / 2 - 1; index >= 0; index--) {
            heapify(array, arraySize, index);
        }

        for (index = arraySize - 1; index >= 0; index--) {
            swap(array, 0, index);
            heapify(array, index, 0);
        }
    }

    private void heapify(Array<T> array, int heapSize, int index) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < heapSize && (comparator.compare(array.get(left),
                array.get(largest))) == sortOrderKey) {
            largest = left;
        }

        if (right < heapSize && (comparator.compare(array.get(right),
                array.get(largest))) == sortOrderKey) {
            largest = right;
        }

        if (largest != index) {
            swap(array, index, largest);
            heapify(array, heapSize, largest);
        }
    }
}
