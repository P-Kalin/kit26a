package ua.khpi.oop.malokhvii05.util.algorithms.search;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

/**
 * Абстрактний клас, призначений для об'єднання усіх алгоритмів пошуку в
 * діапазоні під єдиною реалізацією.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @param <T>
 *            Тип даних, елементів діапазону та елемента для пошуку
 * @since 1.0.0
 */
public abstract class AbstractSearchInRangeAlgorithm<T> extends
        AbstractSearchAlgorithm<T> implements SearchInRangeAlgorithm<T> {

    /**
     * Призначений, для ініціалізації об'єкту компаратором для подільшого
     * обчислення.
     *
     * @param comparator
     *            компаратор для порівння під час сорутвання
     * @since 1.0.0
     */
    public AbstractSearchInRangeAlgorithm(final Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public final int search(final Array<T> array, final T value) {
        return search(array, value, 0, array.size());
    }

    /**
     * Призначений, для перевірки на коректність вхідного діапазону, чи не
     * виходить він за границі масиву або ліва границя більша за праву гарницю.
     *
     * @param array
     *            вхідний масив
     * @param left
     *            ліва границя діапазону
     * @param right
     *            права границя діапазону
     * @return результат перевірки вхідного діапазону
     * @since 1.0.0
     */
    protected final boolean isValidRange(final Array<T> array, final int left,
            final int right) {
        int arraySize = array.size();
        boolean isValidRange = true;
        if (right < left) {
            isValidRange = false;
        } else if (right > arraySize) {
            isValidRange = false;
        } else if (left < 0) {
            isValidRange = false;
        }
        indexNotFound();
        return isValidRange;
    }
}
