package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

/**
 * <p>
 * Призначений, для реалізації алгоритму сортування вхідного масиву. Ключ у
 * фабриці алгоритмів - "insertion-sort".
 * </p>
 * <p>
 * Сортування включенням — простий алгоритм сортування на основі порівнянь. На
 * великих масивах є значно менш ефективним за такі алгоритми, як швидке
 * сортування, пірамідальне сортування та сортування злиттям. Однак, має цілу
 * низку переваг:
 * <ol>
 * <li>простота у реалізації</li>
 * <li>ефективний (зазвичай) на маленьких масивах</li>
 * <li>ефективний при сортуванні масивів, дані в яких вже непогано відсортовані:
 * продуктивність рівна O(n + d), де d — кількість інверсій на практиці</li>
 * <li>ефективніший за більшість інших квадратичних алгоритмів (O(n2)), як то
 * сортування вибором та сортування бульбашкою: його швидкодія рівна n2/4, і в
 * найкращому випадку є лінійною</li>
 * </ol>
 * </p>
 * <ul>
 * <li>Назва: Insertion Sort</li>
 * <li>Автор: ?</li>
 * <li>Метод: Insertion</li>
 * <li>Найкраща швидкодія: Ω(n)</li>
 * <li>Середня швидкодія: Θ(n^2)</li>
 * <li>Найгірша швидкодія: O(n^2)</li>
 * <li>Просторова складність: O(1)</li>
 * <li>Стабільний: Так</li>
 * </ul>
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @see SortAlgorithmFactory
 * @param <T>
 *            Тип даних, елементів масиву для сортування, та компаратору для
 *            порівняння елементів
 */
public final class InsertionSort<T> extends AbstractSortAlgorithm<T> {

    /**
     * Поточний напрямок сортування, тобто обернений чи ні.
     */
    private boolean isReversed;

    static {
        SortAlgorithmFactory.registerAlgorithm("insertion-sort",
                InsertionSort.class);
    }

    /**
     * Пирзначений, для інійіалізації об'єкту компоратором для порівняння
     * вхідних даних.
     *
     * @param comparator
     *            компоратор для вхідних даних
     */
    public InsertionSort(final Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void setSortOrder(final Order sortOrder) {
        isReversed = isReversed(sortOrderToKey(sortOrder),
                INTERNAL_ASCENDING_KEY);
        super.setSortOrder(sortOrder);
    }

    @Override
    public void sort(final Array<T> array) {
        T key;
        int arraySize = array.size();

        int i;
        int j;
        for (i = 1; i < arraySize; i++) {
            key = array.get(i);
            j = i - 1;
            while (j >= 0 && isReversed == (comparator.compare(array.get(j),
                    key) > 0)) {
                array.set(j + 1, array.get(j));
                j = j - 1;
            }
            array.set(j + 1, key);
        }
    }
}
