package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

/**
 * <p>
 * Призначений, для реалізації алгоритму сортування вхідного масиву. Ключ у
 * фабриці алгоритмів - "shell-sort"
 * </p>
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
 * </p>
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
 * @author malokhvii-eduard
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
     * Пирзначений, для інійіалізації об'єкту компоратором для порівняння
     * вхідних даних.
     *
     * @param comparator
     *            компоратор для вхідних даних
     */
    public ShellSort(final Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void sort(final Array<T> array) {
        int arraySize = array.size();
        T comparableValue;
        int gap;

        int i;
        int j;
        for (gap = arraySize >> 1; gap > 0; gap >>= 1) {
            for (i = gap; i < arraySize; i++) {
                comparableValue = array.get(i);
                for (j = i; j >= gap && comparator.compare(array.get(j - gap),
                        comparableValue) == 1; j -= gap) {
                    array.set(j, array.get(j - gap));
                }
                array.set(j, comparableValue);
            }
        }
    }
}
