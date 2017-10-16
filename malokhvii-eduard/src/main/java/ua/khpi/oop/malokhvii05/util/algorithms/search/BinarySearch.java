package ua.khpi.oop.malokhvii05.util.algorithms.search;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

/**
 * <p>
 * Призначений, для реалізації алгоритму пошуку в масиві або діапазоні. Ключ у
 * фабриці алгоритмів - "binary-search".
 * </p>
 * <p>
 * Двійковий пошук — алгоритм знаходження заданого значення у впорядкованому
 * масиві, який полягає у порівнянні серединного елемента масиву з шуканим
 * значенням, і повторенням алгоритму для тієї або іншої половини, залежно від
 * результату порівняння.
 * </p>
 * <ul>
 * <li>Назва: Binary Search</li>
 * <li>Автор: ?</li>
 * <li>Найкраща швидкодія: Ω(1)</li>
 * <li>Середня швидкодія: Θ(log(n))</li>
 * <li>Найгірша швидкодія: O(log(n))</li>
 * <li>Просторова складність: O(1)</li>
 * </ul>
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @see SearchAlgorithmFactory
 * @param <T>
 *            Тип даних, елементів масиву, діапазону, та елемента для пошуку
 * @since 1.0.0
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
    public BinarySearch(final Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public final int search(final Array<T> array, final T value, final int left,
            final int right) {
        if (!isValidArray(array) || !isValidRange(array, left, right)) {
            return lastFoundIndex;
        }

        int leftIndex = left;
        int rightIndex = right;
        T middleValue;
        while (left < right) {
            lastFoundIndex = (leftIndex + rightIndex) >>> 1;
            middleValue = array.get(lastFoundIndex);

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
