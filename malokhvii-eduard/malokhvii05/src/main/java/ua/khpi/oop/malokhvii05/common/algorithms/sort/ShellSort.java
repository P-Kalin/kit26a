package ua.khpi.oop.malokhvii05.common.algorithms.sort;

import java.util.Comparator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

/**
 * Призначений, для реалізації алгоритму сортування вхідного масиву. Ключ у
 * фабриці алгоритмів - "shell-sort"
 * <p>
 * Сортування Шелла — це алгоритм сортування, що є узагальненням сортування
 * включенням. Алгоритм базується на двох тезах:
 * <ol>
 * <li>Сортування включенням ефективне для майже впорядкованих масивів.</li>
 * <li>Сортування включенням неефективне, тому що переміщує елемент тільки на
 * одну позицію за раз.</li>
 * </ol>
 * Тому сортування Шелла виконує декілька впорядкувань включенням, кожен раз
 * порівнюючи і переставляючи елементи, що розташовані на різній відстані один
 * від одного.
 * <ul>
 * <li>Назва: Shell Sort</li>
 * <li>Автор: Donald Shell</li>
 * <li>Метод: Insertion</li>
 * <li>Найкраща швидкодія: Ω(n log(n))</li>
 * <li>Середня швидкодія: Θ(n(log(n))^2)</li>
 * <li>Найгірша швидкодія: O(n(log(n))^2)</li>
 * <li>Просторова складність: O(1)</li>
 * <li>Стабільний: Ні</li>
 * </ul>
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see SortAlgorithmFactory
 * @param <T>
 *            Тип даних, елементів масиву для сортування, та компаратору для
 *            порівняння елементів
 */
public final class ShellSort<T> extends AbstractSortAlgorithm<T> {

    static {
        SortAlgorithmFactory.registerAlgorithm("shell-sort", ShellSort.class);
    }

    /**
     * Призначений, для інійіалізації об'єкту компоратором для порівняння
     * вхідних даних.
     *
     * @param comparator
     *            компоратор для вхідних даних
     * @since 1.0.0
     */
    public ShellSort(@Nonnull final Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    @CanIgnoreReturnValue
    public boolean sort(@Nonnull final T[] array,
            @Nonnegative final int length) {
        if (!checkArray(array) && !checkLength(array, length)) {
            return false;
        }

        T comparableValue;
        int gap;

        int i;
        int j;
        for (gap = length >> 1; gap > 0; gap >>= 1) {
            for (i = gap; i < length; i++) {
                comparableValue = array[i];
                for (j = i; j >= gap && comparator.compare(array[j - gap],
                        comparableValue) == 1; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = comparableValue;
            }
        }
        return true;
    }
}
