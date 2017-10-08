package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

/**
 * <p>
 * Призначений, для реалізації алгоритму сортування вхідного масиву. Ключ у
 * фабриці алгоритмів - "heap-sort".
 * </p>
 * <p>
 * Пірамідальне сортування — алгоритм сортування, працює в найгіршому, в
 * середньому і в найкращому випадку (тобто гарантовано) за Θ(n log n) операцій
 * при сортуванні n елементів. Кількість застосовуваної службової пам'яті не
 * залежить від розміру масиву (тобто, O (1)).
 * </p>
 * <ul>
 * <li>Назва: Heap Sort</li>
 * <li>Автор: J. W. J. Williams</li>
 * <li>Метод: Selection</li>
 * <li>Найкраща швидкодія: Ω(n log(n))</li>
 * <li>Середня швидкодія: Θ(n log(n))</li>
 * <li>Найгірша швидкодія: O(n log(n))</li>
 * <li>Просторова складність: O(1)</li>
 * <li>Стабільний: Ні</li>
 * </ul>
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @see SortAlgorithmFactory
 * @param <T>
 *            Тип даних, елементів масиву для сортування, та компаратору для
 *            порівняння елементів
 */
public final class HeapSort<T> extends AbstractSortAlgorithm<T> {

    static {
        SortAlgorithmFactory.registerAlgorithm("heap-sort", HeapSort.class);
    }

    /**
     * Призначений, для інійіалізації об'єкту компоратором для порівняння
     * вхідних даних.
     *
     * @param comparator
     *            компоратор для вхідних даних
     */
    public HeapSort(final Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void sort(final Array<T> array) {
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

    /**
     * Призначений, для роздування піддерева з корневим елементом, нумерованим
     * індексом.
     *
     * @param array
     *            вхідний масив
     * @param heapSize
     *            розмір під-дерева
     * @param index
     *            позиція під-дерева
     */
    private void heapify(final Array<T> array, final int heapSize,
            final int index) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < heapSize && (comparator.compare(array.get(left),
                array.get(largest))) == 1) {
            largest = left;
        }

        if (right < heapSize && (comparator.compare(array.get(right),
                array.get(largest))) == 1) {
            largest = right;
        }

        if (largest != index) {
            swap(array, index, largest);
            heapify(array, heapSize, largest);
        }
    }
}
