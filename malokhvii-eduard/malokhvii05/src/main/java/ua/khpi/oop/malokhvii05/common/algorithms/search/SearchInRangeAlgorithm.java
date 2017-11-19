package ua.khpi.oop.malokhvii05.common.algorithms.search;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.Signed;

import ua.khpi.oop.malokhvii05.common.algorithms.AlgorithmWithComparator;

/**
 * Ітерфейс, призначений для оголошення алгоритму пошуку у діапазоні. Усі
 * алгоритми базуються на порівнянні. Інші алгоритми не розглядаються.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @param <T>
 *            Тип даних, елементів діапазону та елемента для пошуку
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
    @Signed
    int search(@Nonnull T[] array, @Nullable T value, @Nonnegative int left,
            @Nonnegative int right);
}
