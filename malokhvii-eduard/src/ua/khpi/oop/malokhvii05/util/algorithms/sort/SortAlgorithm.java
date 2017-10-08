package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;
import ua.khpi.oop.malokhvii05.util.algorithms.AlgorithmWithComparator;

/**
 * Ітерфейс, призначений для оголошення алгоритму сортування для масиву. Усі
 * алгоритму базуються на порівнянні. Інші алгоритми не розглядаються.
 *
 * @author malokhvii-eduard
 * @param <T>
 *            Тип даних, елементів масиву для сортування
 */
public interface SortAlgorithm<T> extends AlgorithmWithComparator<T> {

    /**
     * Призначений, для отримання поточного стану компаратора, тобто чи
     * обернений порядок сортування.
     *
     * @return порядок сортування обернений
     */
    boolean isReversedOrder();

    /**
     * Призначений, для оновлення поточного порядку сортування за допомогою
     * методу {@link Comparator#reversed}.
     *
     * @param isReversedOrder
     *            новий порядок сортування
     */
    void setReversedOrder(boolean isReversedOrder);

    /**
     * Призначений, для сотування вхідного масиву.
     *
     * @see Array
     * @param array
     *            вхідний масив для сорутвання
     */
    void sort(Array<T> array);
}
