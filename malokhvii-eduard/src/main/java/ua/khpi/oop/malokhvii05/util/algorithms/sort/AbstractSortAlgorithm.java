package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;
import ua.khpi.oop.malokhvii05.util.algorithms.AbstractAlgorithmWithComparator;

/**
 * Абстрактний клас, призначений для об'єднання усіх алгоритмів сорутвання під
 * єдиною реалізацією.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @param <T>
 *            Тип даних, елементів масиву для сортування, та компаратору для
 *            порівняння елементів
 * @since 1.0.0
 */
public abstract class AbstractSortAlgorithm<T>
        extends AbstractAlgorithmWithComparator<T> implements SortAlgorithm<T> {

    /**
     * Внутрішній ключ порядку сортування.
     *
     * @since 1.0.0
     */
    protected boolean isReversedOrder;

    /**
     * Призначений, для ініціалізації об'єкту компаратором для подільшого
     * обчислення.
     *
     * @param comparator
     *            компаратор для порівння під час сорутвання
     * @since 1.0.0
     */
    public AbstractSortAlgorithm(final Comparator<T> comparator) {
        super(comparator);
        setReversedOrder(false);
    }

    /**
     * Призначений, для обміну значень елементів у масиві.
     *
     * @param array
     *            масив
     * @param left
     *            індекс лівого елементу
     * @param right
     *            індекс правого елементу
     * @since 1.0.0
     */
    protected void swap(final Array<T> array, final int left, final int right) {
        T temp = array.get(left);
        array.set(left, array.get(right));
        array.set(right, temp);
    }

    @Override
    public boolean isReversedOrder() {
        return isReversedOrder;
    }

    @Override
    public void setReversedOrder(boolean isReversedOrder) {
        if (isReversedOrder != this.isReversedOrder) {
            comparator = comparator.reversed();
        }
    }

    @Override
    public final boolean isNull() {
        return false;
    }
}
