package ua.khpi.oop.malokhvii05.common.algorithms.sort;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Comparator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ua.khpi.oop.malokhvii05.common.algorithms.AbstractAlgorithmWithComparator;

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
    protected static <T> void swap(@Nonnull final T[] array,
            @Nonnegative final int left, @Nonnegative final int right) {
        final T temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

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
    public AbstractSortAlgorithm(@Nonnull final Comparator<T> comparator) {
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
            comparator = comparator.reversed();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public @Nullable T[] sort(@Nonnull final Collection<T> collection) {
        checkNotNull(collection);
        if (collection.isEmpty()) {
            return null;
        }

        T[] array = (T[]) new Object[collection.size()];
        collection.toArray(array);
        sort(array);
        return array;
    }
}
