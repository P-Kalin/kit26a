package ua.khpi.oop.malokhvii05.common.algorithms.sort;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Comparator;

import javax.annotation.Nonnull;

/**
 * Призначений, для реалізації алгоритму сортування вхідного масиву. Ключ у
 * фабриці алгоритмів - "selection-sort".
 * <p>
 * Сортування вибором — простий алгоритм сортування лінійного масиву, на основі
 * вставок. Має ефективність, що робить його неефективним при сортування великих
 * масивів, і в цілому, менш ефективним за подібний алгоритм сортування
 * включенням. Сортування вибором вирізняється більшою простотою, ніж сортування
 * включенням, і в деяких випадках, вищою продуктивністю.
 * <ul>
 * <li>Назва: Selection Sort</li>
 * <li>Автор: ?</li>
 * <li>Метод: Selection</li>
 * <li>Найкраща швидкодія: Ω(n^2)</li>
 * <li>Середня швидкодія: Θ(n^2)</li>
 * <li>Найгірша швидкодія: O(n^2)</li>
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
public final class SelectionSort<T> extends AbstractSortAlgorithm<T> {

    static {
        SortAlgorithmFactory.registerAlgorithm("selection-sort",
                SelectionSort.class);
    }

    /**
     * Призначений, для інійіалізації об'єкту компоратором для порівняння
     * вхідних даних.
     *
     * @param comparator
     *            компоратор для вхідних даних
     * @since 1.0.0
     */
    public SelectionSort(@Nonnull final Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void sort(@Nonnull final T[] array) {
        checkNotNull(array);
        int minimumIndex;
        int i;
        int j;
        for (i = 0; i < array.length - 1; i++) {
            minimumIndex = i;
            for (j = i + 1; j < array.length; j++) {
                if (comparator.compare(array[j], array[minimumIndex]) < 0) {
                    minimumIndex = j;
                }
            }
            swap(array, minimumIndex, i);
        }
    }
}
