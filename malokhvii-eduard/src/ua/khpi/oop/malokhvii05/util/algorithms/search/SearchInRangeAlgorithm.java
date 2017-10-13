package ua.khpi.oop.malokhvii05.util.algorithms.search;

import ua.khpi.oop.malokhvii05.util.Array;
import ua.khpi.oop.malokhvii05.util.algorithms.AlgorithmWithComparator;

/**
 * Ітерфейс, призначений для оголошення алгоритму пошуку у діапазоні. Усі
 * алгоритми базуються на порівнянні. Інші алгоритми не розглядаються.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @param <T>
 *            Тип даних, елементів діапазону та елемента для пошуку
 * @since 1.0.0
 */
public interface SearchInRangeAlgorithm<T> extends AlgorithmWithComparator<T> {

    /**
     * Призначений, для пошуку значення в вхідному діапазоні.
     *
     * @param array
     *            вхідний масив
     * @param left
     *            початковий ідекс діапазону
     * @param right
     *            кінцевий індекс діапазону
     * @param value
     *            значення для пошуку
     * @return індекс знайдего значення, або якщо значення не знайдено тоді
     *         {@link SearchAlgorithm#INDEX_NOT_FOUND}.
     * @since 1.0.0
     */
    int search(Array<T> array, T value, int left, int right);
}
