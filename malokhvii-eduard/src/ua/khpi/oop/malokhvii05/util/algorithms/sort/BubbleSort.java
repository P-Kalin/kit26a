package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

/**
 * <p>
 * Призначений, для реалізації алгоритму сортування вхідного масиву. Ключ у
 * фабриці алгоритмів - "bubble-sort". Реалізація є модифікованою.
 * </p>
 * <p>
 * Сортування обміном або сортування бульбашкою є простим алгоритмом сортування.
 * Алгоритм працює таким чином — у поданому наборі даних (списку чи масиві)
 * порівнюються два сусідні елементи. Якщо один з елементів, не відповідає
 * критерію сортування (є більшим, або ж, навпаки, меншим за свого сусіда), то
 * ці два елементи міняються місцями. Алгоритм отримав свою назву від того, що
 * процес сортування за ним нагадує поведінку бульбашок повітря у резервуарі з
 * водою. Оскільки для роботи з елементами масиву він використовує лише
 * порівняння, це сортування на основі порівнянь.
 * </p>
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
 * @author malokhvii-eduard
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
     * Пирзначений, для інійіалізації об'єкту компоратором для порівняння
     * вхідних даних.
     *
     * @param comparator
     *            компоратор для вхідних даних
     */
    public BubbleSort(final Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void sort(final Array<T> array) {
        int externalLoopBarrier = array.size();
        boolean isSwapped;

        int i;
        int j;
        for (i = 0; i < externalLoopBarrier; i++) {
            isSwapped = false;
            for (j = 0; j < externalLoopBarrier - i - 1; j++) {
                if (comparator.compare(array.get(j), array.get(j + 1)) == 1) {
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
