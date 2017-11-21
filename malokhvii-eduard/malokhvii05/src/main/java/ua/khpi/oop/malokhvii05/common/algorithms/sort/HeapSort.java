package ua.khpi.oop.malokhvii05.common.algorithms.sort;

import java.util.Comparator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

/**
 * Призначений, для реалізації алгоритму сортування вхідного масиву. Ключ у
 * фабриці алгоритмів - "heap-sort".
 * <p>
 * Пірамідальне сортування — алгоритм сортування, працює в найгіршому, в
 * середньому і в найкращому випадку (тобто гарантовано) за Θ(n log n) операцій
 * при сортуванні n елементів. Кількість застосовуваної службової пам'яті не
 * залежить від розміру масиву (тобто, O(1)).
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
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.1
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
     * @since 1.0.0
     */
    public HeapSort(@Nonnull final Comparator<T> comparator) {
        super(comparator);
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
     * @since 1.0.0
     */
    private void heapify(@Nonnull final T[] array,
            @Nonnegative final int heapSize, @Nonnegative final int index) {
        int parent = index;
        final int leftChild = 2 * index + 1;
        final int rightChild = 2 * index + 2;

        if (leftChild < heapSize
                && comparator.compare(array[leftChild], array[parent]) == 1) {
            parent = leftChild;
        }

        if (rightChild < heapSize
                && comparator.compare(array[rightChild], array[parent]) == 1) {
            parent = rightChild;
        }

        if (parent != index) {
            swap(array, index, parent);
            heapify(array, heapSize, parent);
        }
    }

    @Override
    @CanIgnoreReturnValue
    public boolean sort(@Nonnull final T[] array,
            @Nonnegative final int length) {
        if (!checkArray(array) && !checkLength(array, length)) {
            return false;
        }

        int index;
        for (index = length / 2 - 1; index >= 0; index--) {
            heapify(array, length, index);
        }

        for (index = length - 1; index >= 0; index--) {
            swap(array, 0, index);
            heapify(array, index, 0);
        }
        return true;
    }
}
