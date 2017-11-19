package ua.khpi.oop.malokhvii05.common.algorithms.search;

import java.util.Comparator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.Signed;

/**
 * Призначений, для реалізації алгоритму пошуку в масиві. Ключ у фабриці
 * алгоритмів - "linear-with-barrier-search".
 * <p>
 * Модифікація алгоритму {@link LinearSearch лінійного пошуку}. Мета модифікації
 * полягає у тому що, в кінець масиву записується додатковий елемент зі
 * значенням шуканого елементу. Він називається бар'єром, тому що обмежує
 * перехід за межі масиву. Але тепер масив буде описаний як m[0..n + 1]. Після
 * пошуку необхідно зняти бар'єр.
 * <ul>
 * <li>Назва: Linear With Barrier Search</li>
 * <li>Автор: ?</li>
 * <li>Найкраща швидкодія: Ω(1)</li>
 * <li>Середня швидкодія: Θ(n)</li>
 * <li>Найгірша швидкодія: O(n)</li>
 * <li>Просторова складність: O(1)</li>
 * </ul>
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see SearchAlgorithmFactory
 * @param <T>
 *            Тип даних, елементів масиву, та елемента для пошуку
 */
public class LinearWithBarrierSearch<T> extends AbstractSearchAlgorithm<T> {

    static {
        SearchAlgorithmFactory.registerAlgorithm("linear-with-barrier-search",
                LinearWithBarrierSearch.class);
    }

    /**
     * Призначений, для ініціалізації об'єкту компаратором для подільшого
     * обчислення.
     *
     * @param comparator
     *            компаратор для порівння під час сорутвання
     * @since 1.0.0
     */
    public LinearWithBarrierSearch(@Nonnull final Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public final @Signed int search(@Nonnull final T[] array,
            @Nullable final T value) {
        if (!isValidArray(array)) {
            return lastFoundIndex;
        }

        if (comparator.compare(array[array.length - 1], value) != 0) {
            T lastValue = array[array.length - 1];
            array[array.length - 1] = value;
            for (lastFoundIndex = 0; comparator.compare(array[lastFoundIndex],
                    value) != 0; lastFoundIndex++) {
                ;
            }
            array[array.length - 1] = lastValue;
        } else {
            return array.length - 1;
        }

        if (lastFoundIndex < array.length) {
            return lastFoundIndex;
        }

        return indexNotFound();
    }
}
