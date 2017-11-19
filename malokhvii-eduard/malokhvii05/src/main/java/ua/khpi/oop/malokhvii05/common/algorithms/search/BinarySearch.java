package ua.khpi.oop.malokhvii05.common.algorithms.search;

import java.util.Comparator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.Signed;

/**
 * Призначений, для реалізації алгоритму пошуку в масиві або діапазоні. Ключ у
 * фабриці алгоритмів - "binary-search".
 * <p>
 * Двійковий пошук — алгоритм знаходження заданого значення у впорядкованому
 * масиві, який полягає у порівнянні серединного елемента масиву з шуканим
 * значенням, і повторенням алгоритму для тієї або іншої половини, залежно від
 * результату порівняння.
 * <ul>
 * <li>Назва: Binary Search</li>
 * <li>Автор: ?</li>
 * <li>Найкраща швидкодія: Ω(1)</li>
 * <li>Середня швидкодія: Θ(log(n))</li>
 * <li>Найгірша швидкодія: O(log(n))</li>
 * <li>Просторова складність: O(1)</li>
 * </ul>
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see SearchAlgorithmFactory
 * @param <T>
 *            Тип даних, елементів масиву, діапазону, та елемента для пошуку
 */
public class BinarySearch<T> extends AbstractSearchInRangeAlgorithm<T> {

    static {
        SearchAlgorithmFactory.registerAlgorithm("binary-search",
                BinarySearch.class);
        SearchAlgorithmFactory.setDefaultAlgorithm(BinarySearch.class);
    }

    /**
     * Призначений, для ініціалізації об'єкту компаратором для подільшого
     * обчислення.
     *
     * @param comparator
     *            компаратор для порівння під час сорутвання
     * @since 1.0.0
     */
    public BinarySearch(@Nonnull final Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public final @Signed int search(@Nonnull final T[] array,
            @Nullable final T value, @Nonnegative final int left,
            @Nonnegative final int right) {
        if (!isValidArray(array) || !isValidRange(array, left, right)) {
            return lastFoundIndex;
        }

        int leftIndex = left;
        int rightIndex = right;
        T middleValue;
        while (left < right) {
            lastFoundIndex = leftIndex + rightIndex >>> 1;
            middleValue = array[lastFoundIndex];

            if (comparator.compare(middleValue, value) == 1) {
                rightIndex = lastFoundIndex;
            } else if (comparator.compare(middleValue, value) == 0) {
                return lastFoundIndex;
            } else {
                leftIndex = lastFoundIndex + 1;
            }
        }

        return indexNotFound();
    }
}
