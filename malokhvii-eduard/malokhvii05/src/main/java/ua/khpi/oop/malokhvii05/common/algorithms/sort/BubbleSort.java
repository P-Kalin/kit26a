package ua.khpi.oop.malokhvii05.common.algorithms.sort;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Comparator;

import javax.annotation.Nonnull;

/**
 * Призначений, для реалізації алгоритму сортування вхідного масиву. Ключ у
 * фабриці алгоритмів - "bubble-sort". Реалізація є модифікованою.
 * <p>
 * Сортування обміном або сортування бульбашкою є простим алгоритмом сортування.
 * Алгоритм працює таким чином — у поданому наборі даних (списку чи масиві)
 * порівнюються два сусідні елементи. Якщо один з елементів, не відповідає
 * критерію сортування (є більшим, або ж, навпаки, меншим за свого сусіда), то
 * ці два елементи міняються місцями. Алгоритм отримав свою назву від того, що
 * процес сортування за ним нагадує поведінку бульбашок повітря у резервуарі з
 * водою. Оскільки для роботи з елементами масиву він використовує лише
 * порівняння, це сортування на основі порівнянь.
 * <ul>
 * <li>Назва: Bubble Sort</li>
 * <li>Автор: ?</li>
 * <li>Метод: Exchanging</li>
 * <li>Найкраща швидкодія: Ω(n)</li>
 * <li>Середня швидкодія: Θ(n^2)</li>
 * <li>Найгірша швидкодія: O(n^2)</li>
 * <li>Просторова складність: O(1)</li>
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
public final class BubbleSort<T extends Comparable<T>>
        extends AbstractSortAlgorithm<T> {

    static {
        SortAlgorithmFactory.registerAlgorithm("bubble-sort", BubbleSort.class);
    }

    /**
     * Призначений, для інійіалізації об'єкту компоратором для порівняння
     * вхідних даних.
     *
     * @param comparator
     *            компоратор для вхідних даних
     * @since 1.0.0
     */
    public BubbleSort(@Nonnull final Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void sort(@Nonnull final T[] array) {
        checkNotNull(array);
        boolean isSwapped;

        int i;
        int j;
        for (i = 0; i < array.length; i++) {
            isSwapped = false;
            for (j = 0; j < array.length - i - 1; j++) {
                if (comparator.compare(array[j], array[j + 1]) == 1) {
                    swap(array, j, j + 1);
                    isSwapped = true;
                }
            }

            if (!isSwapped) {
                break;
            }
        }
    }
}
