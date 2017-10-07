package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

/**
 * <p>
 * Призначений, для реалізації алгоритму сортування вхідного масиву. Ключ у
 * фабриці алгоритмів - "gnome-sort"
 * </p>
 * <p>
 * Сортування гнома — один із найпростіших алгоритмів сортування (на думку
 * багатьох — найпростіший). Ім'я походить від голландського садового гнома,
 * якого ставлять між квітковими горщиками. Якщо два сусідні від гнома горщики
 * йдуть у правильному порядку, гном йде на одну позицію вперед. Якщо ж вони у
 * неправильному порядку - міняє ці два горщики місцями і йде на одну позицію
 * назад (щоб знову перевірити порядок).
 * </p>
 * <ul>
 * <li>Назва: Gnome Sort</li>
 * <li>Автор: Dr. Hamid Sarbazi-Azad</li>
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
public final class GnomeSort<T> extends AbstractSortAlgorithm<T> {

    /**
     * Поточний напрямок сортування, тобто обернений чи ні.
     */
    private boolean isReversed;

    static {
        SortAlgorithmFactory.registerAlgorithm("gnome-sort", GnomeSort.class);
    }

    /**
     * Пирзначений, для інійіалізації об'єкту компоратором для порівняння
     * вхідних даних.
     *
     * @param comparator
     *            компоратор для вхідних даних
     */
    public GnomeSort(final Comparator<T> comparator) {
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
        int index = 0;
        int arraySize = array.size();
        while (index < arraySize) {
            if (index == 0) {
                index++;
            } else if (isReversed == (comparator.compare(array.get(index),
                    array.get(index - 1)) == sortOrderKey)) {
                index++;
            } else {
                swap(array, index, index - 1);
                index--;
            }
        }
    }
}
