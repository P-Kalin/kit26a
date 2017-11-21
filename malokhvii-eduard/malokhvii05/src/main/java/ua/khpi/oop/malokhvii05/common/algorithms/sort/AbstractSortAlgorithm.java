package ua.khpi.oop.malokhvii05.common.algorithms.sort;

import java.util.Comparator;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ua.khpi.oop.malokhvii05.common.algorithms.AbstractAlgorithmWithComparator;

/**
 * Абстрактний клас, призначений для об'єднання усіх алгоритмів сорутвання під
 * єдиною реалізацією.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.1
 * @param <T>
 *            Тип даних, елементів масиву для сортування, та компаратору для
 *            порівняння елементів
 */
public abstract class AbstractSortAlgorithm<T>
        extends AbstractAlgorithmWithComparator<T> implements SortAlgorithm<T> {

    /**
     * Призначений, для перевірки вхідного масиву для сортування на коректність.
     *
     * @param array
     *            вхідний масив для сорутвання
     * @return результат перевірки
     * @since 1.0.1
     */
    protected static boolean checkArray(@Nullable final Object[] array) {
        if (array == null || array.length == 0) {
            return false;
        }
        return true;
    }

    /**
     * Призначений, для перевірки вхідного розміру фрагменту масиву для
     * сортування.
     *
     * @param array
     *            масив для сортування
     * @param length
     *            розмір фрагменту для сорутвання
     * @return результат перевірки
     * @since 1.0.1
     */
    protected static boolean checkLength(@Nullable final Object[] array,
            @CheckForSigned final int length) {
        if (array == null || array.length < length) {
            return false;
        }
        return true;
    }

    /**
     * Призначений, для обміну значень елементів у масиві.
     *
     * @param <T>
     *            Тип даних, елементів масиву для переміщення
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
    public final boolean isReversedOrder() {
        return this.isReversedOrder;
    }

    @Override
    public final void setReversedOrder(final boolean isReversedOrder) {
        if (isReversedOrder != this.isReversedOrder) {
            comparator = comparator.reversed();
        }
    }

    @Override
    public final boolean sort(@Nonnull final T[] array) {
        return sort(array, array.length);
    }
}
