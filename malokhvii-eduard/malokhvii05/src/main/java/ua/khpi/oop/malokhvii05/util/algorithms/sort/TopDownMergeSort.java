package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

/**
 * Призначений, для реалізації алгоритму сортування вхідного масиву. Ключ у
 * фабриці алгоритмів - "top-down-merge-sort".
 * <p>
 * Сортування злиттям — алгоритм сортування, в основі якого лежить принцип
 * «Розділяй та володарюй». В основі цього способу сортування лежить злиття двох
 * упорядкованих ділянок масиву в одну впорядковану ділянку іншого масиву.
 * <p>
 * Злиття двох упорядкованих послідовностей можна порівняти з перебудовою двох
 * колон солдатів, вишикуваних за зростом, в одну, де вони також розташовуються
 * за зростом. Якщо цим процесом керує офіцер, то він порівнює зріст солдатів,
 * перших у своїх колонах і вказує, якому з них треба ставати останнім у нову
 * колону, а кому залишатися першим у своїй. Так він вчиняє, поки одна з колон
 * не вичерпається — тоді решта іншої колони додається до нової.
 * <ul>
 * <li>Назва: Merge Sort (Top-Down)</li>
 * <li>Автор: John von Neumann</li>
 * <li>Метод: Merging</li>
 * <li>Найкраща швидкодія: Ω(n log(n))</li>
 * <li>Середня швидкодія: Θ(n log(n))</li>
 * <li>Найгірша швидкодія: O(n log(n))</li>
 * <li>Просторова складність: O(n)</li>
 * <li>Стабільний: Так</li>
 * </ul>
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see SortAlgorithmFactory
 * @param <T>
 *            Тип даних, елементів масиву для сортування, та компаратору для
 *            порівняння елементів
 */
public final class TopDownMergeSort<T> extends AbstractSortAlgorithm<T> {

    static {
        SortAlgorithmFactory.registerAlgorithm("top-down-merge-sort",
                TopDownMergeSort.class);
    }

    /**
     * Призначений, для інійіалізації об'єкту компоратором для порівняння
     * вхідних даних.
     *
     * @param comparator
     *            компоратор для вхідних даних
     * @since 1.0.0
     */
    public TopDownMergeSort(final Comparator<T> comparator) {
        super(comparator);
    }

    /**
     * Призначений, для злиття двох фрагментів масиву.
     *
     * @param left
     *            лівий фрагмент
     * @param right
     *            правий фрагмент
     * @return об'єднаний масив, та відсортований
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    private Object[] mergeSlices(final Object[] left, final Object[] right) {
        final Object[] mergeBuffer = new Integer[left.length + right.length];

        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;
        while (leftIndex < left.length || rightIndex < right.length) {
            if (leftIndex < left.length && rightIndex < right.length) {
                if (this.comparator.compare((T) left[leftIndex],
                        (T) right[rightIndex]) < 1) {
                    mergeBuffer[resultIndex++] = left[leftIndex++];
                } else {
                    mergeBuffer[resultIndex++] = right[rightIndex++];
                }
            } else if (leftIndex < left.length) {
                mergeBuffer[resultIndex++] = left[leftIndex++];
            } else if (rightIndex < right.length) {
                mergeBuffer[resultIndex++] = right[rightIndex++];
            }
        }
        return mergeBuffer;
    }

    /**
     * Призначений, для рекурсивного розділення масиву на рівні частини, та їх
     * сортування.
     *
     * @param array
     *            вхідний масив
     * @return відсортований масив
     * @since 1.0.0
     */
    private Object[] mergeSort(final Object[] array) {
        if (array.length <= 1) {
            return array;
        }

        final int middle = array.length >> 1;

        Object[] left = new Object[middle];
        Object[] right = new Object[array.length - middle];

        System.arraycopy(array, 0, left, 0, left.length);
        System.arraycopy(array, left.length, right, 0, right.length);

        left = this.mergeSort(left);
        right = this.mergeSort(right);

        return this.mergeSlices(left, right);
    }

    @Override
    public void sort(final Array<T> array) {
        final Object[] arrayData = array.getData();
        final Object[] sortedArrayData = this.mergeSort(array.toArray());
        System.arraycopy(sortedArrayData, 0, arrayData, 0,
                arrayData.length - (arrayData.length - array.size()));
    }
}
