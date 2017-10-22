package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;
import ua.khpi.oop.malokhvii05.util.algorithms.AbstractAlgorithmWithComparator;

/**
 * Абстрактний клас, призначений для об'єднання усіх алгоритмів сорутвання під
 * єдиною реалізацією.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @param <T>
 *            Тип даних, елементів масиву для сортування, та компаратору для
 *            порівняння елементів
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
        this.setReversedOrder(false);
    }

    @Override
    public final boolean isNull() {
        return false;
    }

    @Override
    public boolean isReversedOrder() {
        return this.isReversedOrder;
    }

    @Override
    public void setReversedOrder(final boolean isReversedOrder) {
        if (isReversedOrder != this.isReversedOrder) {
            this.comparator = this.comparator.reversed();
        }
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
        final T temp = array.get(left);
        array.set(left, array.get(right));
        array.set(right, temp);
    }
}
