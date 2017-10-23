package ua.khpi.oop.malokhvii05.util.algorithms.search;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;
import ua.khpi.oop.malokhvii05.util.algorithms.AbstractAlgorithmWithComparator;

/**
 * Абстрактний клас, призначений для об'єднання усіх алгоритмів пошуку в масиві
 * під єдиною реалізацією.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @param <T>
 *            Тип даних, елементів масиву та елемента для пошуку
 */
public abstract class AbstractSearchAlgorithm<T> extends
        AbstractAlgorithmWithComparator<T> implements SearchAlgorithm<T> {

    /**
     * Індекс останнього знайденого елементу.
     *
     * @since 1.0.0
     */
    protected int lastFoundIndex;

    /**
     * Призначений, для інійіалізації об'єкту компоратором для порівняння
     * вхідних даних.
     *
     * @param comparator
     *            компоратор для вхідних даних
     * @since 1.0.0
     */
    public AbstractSearchAlgorithm(final Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public final int getLastFoundIndex() {
        return this.lastFoundIndex;
    }

    /**
     * Призначений, для завершення пошуку, якщо не знайдено елемент.
     *
     * @return індекс не знайденого елементу
     * @since 1.0.0
     */
    protected final int indexNotFound() {
        this.lastFoundIndex = SearchAlgorithm.INDEX_NOT_FOUND;
        return this.lastFoundIndex;
    }

    @Override
    public final boolean isNull() {
        return false;
    }

    /**
     * Призначений, для перевірки вхідного масиву, на порожність.
     *
     * @param array
     *            вхідний масив
     * @return результат перевірки
     * @since 1.0.0
     */
    protected final boolean isValidArray(final Array<T> array) {
        if (array.isEmpty()) {
            this.lastFoundIndex = SearchAlgorithm.INDEX_NOT_FOUND;
            return false;
        }
        return true;
    }
}
