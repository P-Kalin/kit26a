package ua.khpi.oop.malokhvii05.util.algorithms.sort;

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
     * Перелік напрямків сортування вхідного масиву.
     *
     * @author malokhvii-eduard
     * @version 1.0.0
     */
    enum Order {
        /**
         * Сортування масиву від меншого до більшого.
         */
        ASCENDING,
        /**
         * Сортування масиву більшого до меншогою.
         */
        DESCENDING;
    }

    /**
     * Призначений, для оновлення напрямку сортування.
     *
     * @param sortOrder
     *            новий напрямок сортування
     */
    void setSortOrder(Order sortOrder);

    /**
     * Призначений, для отримання напрямку сортування.
     *
     * @return новий напрямок сортування
     */
    Order getSortOrder();

    /**
     * Призначений, для сотування вхідного масиву.
     *
     * @see Array
     * @param array
     *            вхідний масив для сорутвання
     */
    void sort(Array<T> array);
}
