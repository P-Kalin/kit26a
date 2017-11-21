package ua.khpi.oop.malokhvii05.common.algorithms.sort;

import java.util.Comparator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

/**
 * Призначений, для реалізації алгоритму сортування вхідного масиву. Ключ у
 * фабриці алгоритмів - "gnome-sort"
 * <p>
 * Сортування гнома — один із найпростіших алгоритмів сортування (на думку
 * багатьох — найпростіший). Ім'я походить від голландського садового гнома,
 * якого ставлять між квітковими горщиками. Якщо два сусідні від гнома горщики
 * йдуть у правильному порядку, гном йде на одну позицію вперед. Якщо ж вони у
 * неправильному порядку - міняє ці два горщики місцями і йде на одну позицію
 * назад (щоб знову перевірити порядок).
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
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.1
 * @see SortAlgorithmFactory
 * @param <T>
 *            Тип даних, елементів масиву для сортування, та компаратору для
 *            порівняння елементів
 */
public final class GnomeSort<T> extends AbstractSortAlgorithm<T> {

    static {
        SortAlgorithmFactory.registerAlgorithm("gnome-sort", GnomeSort.class);
    }

    /**
     * Призначений, для інійіалізації об'єкту компоратором для порівняння
     * вхідних даних.
     *
     * @param comparator
     *            компоратор для вхідних даних
     * @since 1.0.0
     */
    public GnomeSort(@Nonnull final Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    @CanIgnoreReturnValue
    public boolean sort(@Nonnull final T[] array,
            @Nonnegative final int length) {
        if (!checkArray(array) && !checkLength(array, length)) {
            return false;
        }

        int index = 0;
        while (index < length) {
            if (index == 0) {
                index++;
            } else if (isReversedOrder == (comparator.compare(array[index],
                    array[index - 1]) == -1)) {
                index++;
            } else {
                swap(array, index, index - 1);
                index--;
            }
        }
        return true;
    }
}
