package ua.khpi.oop.malokhvii05.common.algorithms.sort;

import java.util.Collection;
import java.util.Comparator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ua.khpi.oop.malokhvii05.common.Array;
import ua.khpi.oop.malokhvii05.common.algorithms.AlgorithmWithComparator;

/**
 * Ітерфейс, призначений для оголошення алгоритму сортування для масиву. Усі
 * алгоритми базуються на порівнянні. Інші алгоритми не розглядаються.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @param <T>
 *            Тип даних, елементів масиву для сортування
 */
public interface SortAlgorithm<T> extends AlgorithmWithComparator<T> {

    /**
     * Призначений, для отримання поточного стану компаратора, тобто чи
     * обернений порядок сортування.
     *
     * @return порядок сортування обернений
     * @since 1.0.0
     */
    boolean isReversedOrder();

    /**
     * Призначений, для оновлення поточного порядку сортування за допомогою
     * методу {@link Comparator#reversed}.
     *
     * @param isReversedOrder
     *            новий порядок сортування
     * @since 1.0.0
     */
    void setReversedOrder(boolean isReversedOrder);

    @Nullable
    T[] sort(@Nonnull final Collection<T> collection);

    /**
     * Призначений, для сотування вхідного масиву.
     *
     * @see Array
     * @param array
     *            вхідний масив для сорутвання
     * @since 1.0.0
     */
    void sort(@Nonnull T[] array);
}
