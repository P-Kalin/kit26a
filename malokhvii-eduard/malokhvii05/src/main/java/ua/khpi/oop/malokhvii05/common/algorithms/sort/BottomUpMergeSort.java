package ua.khpi.oop.malokhvii05.common.algorithms.sort;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Comparator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * Призначений, для реалізації алгоритму сортування вхідного масиву. Ключ у
 * фабриці алгоритмів - "bottom-up-merge-sort".
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
 * <li>Назва: Merge Sort (Bottom-Up)</li>
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
public final class BottomUpMergeSort<T> extends AbstractSortAlgorithm<T> {

    static {
        SortAlgorithmFactory.registerAlgorithm("bottom-up-merge-sort",
                BottomUpMergeSort.class);
    }

    /**
     * Призначений, для інійіалізації об'єкту компоратором для порівняння
     * вхідних даних.
     *
     * @param comparator
     *            компоратор для вхідних даних
     * @since 1.0.0
     */
    public BottomUpMergeSort(@Nonnull final Comparator<T> comparator) {
        super(comparator);
    }

    /**
     * Призначений, для злиття відсортованого фрагменту масиву у тимчасовий
     * буфер.
     *
     * @param array
     *            вхідний масив
     * @param mergeBuffer
     *            буфер для збереження впорядкованих елементів
     * @param left
     *            початковий індекс
     * @param chunkSize
     *            розмір фрагменту
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    private void mergeSlice(@Nonnull final Object[] array,
            @Nonnull final Object[] mergeBuffer, @Nonnegative final int left,
            @Nonnegative final int chunkSize) {
        final int right = left + chunkSize;
        final int end = Math.min(left + chunkSize * 2 - 1,
                mergeBuffer.length - 1);

        int leftIndex = left;
        int rightIndex = right;

        for (int i = 0; i <= end - left; i++) {
            if (leftIndex < right && (rightIndex > end
                    || comparator.compare((T) array[leftIndex],
                            (T) array[rightIndex]) <= 0)) {
                mergeBuffer[i] = array[leftIndex++];
            } else {
                mergeBuffer[i] = array[rightIndex++];
            }
        }

        for (int i = left; i <= end; i++) {
            array[i] = mergeBuffer[i - left];
        }
    }

    @Override
    public void sort(@Nonnull final T[] array) {
        final Object[] arrayData = checkNotNull(array);
        final Object[] mergeBuffer = new Object[array.length];

        int chunkSize = 1;
        int i;
        while (chunkSize < mergeBuffer.length) {
            i = 0;
            while (i < mergeBuffer.length - chunkSize) {
                mergeSlice(arrayData, mergeBuffer, i, chunkSize);
                i += chunkSize << 1;
            }
            chunkSize <<= 1;
        }
    }
}
