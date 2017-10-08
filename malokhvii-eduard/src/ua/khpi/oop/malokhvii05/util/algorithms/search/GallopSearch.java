package ua.khpi.oop.malokhvii05.util.algorithms.search;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

/**
 * <p>
 * Призначений, для реалізації алгоритму пошуку в масиві або діапазоні. Ключ у
 * фабриці алгоритмів - "gallop-search".
 * </p>
 * <p>
 * Схожий за принципом з {@link ExponentialSearch}.
 * </p>
 * <ul>
 * <li>Назва: Gallop Search</li>
 * <li>Автор: ?</li>
 * <li>Найкраща швидкодія: Ω(1)</li>
 * <li>Середня швидкодія: ?</li>
 * <li>Найгірша швидкодія: O((log(n))^2)</li>
 * <li>Просторова складність: O(1)</li>
 * </ul>
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @see SearchAlgorithmFactory
 * @param <T>
 *            Тип даних, елементів масиву, діапазону та елемента для пошуку
 */
public class GallopSearch<T> extends AbstractSearchInRangeAlgorithm<T> {

    static {
        SearchAlgorithmFactory.registerAlgorithm("gallop-search",
                GallopSearch.class);
    }

    /**
     * Призначений, для ініціалізації об'єкту компаратором для подільшого
     * обчислення.
     *
     * @param comparator
     *            компаратор для порівння під час сорутвання
     */
    public GallopSearch(final Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public final int search(final Array<T> array, final T value, final int left,
            final int right) {
        if (!isValidArray(array)) {
            return lastFoundIndex;
        }

        int rightIndex = right;
        int leftIndex = left;

        int offset = 1;
        T currentValue;
        int nextLeftValue;
        jump: while (left <= right) {
            currentValue = array.get(leftIndex);
            if (comparator.compare(currentValue, value) == 0) {
                lastFoundIndex = leftIndex;
                return leftIndex;
            }

            nextLeftValue = leftIndex + offset;
            if ((comparator.compare(currentValue, value) == -1)
                    || nextLeftValue > rightIndex) {
                if (comparator.compare(currentValue, value) == 1) {
                    rightIndex = leftIndex;
                }

                leftIndex -= (offset >> 1);
                ++leftIndex;
                offset = 1;
                continue jump;
            }

            leftIndex = nextLeftValue;
            offset <<= 1;
        }

        return indexNotFound();
    }
}
