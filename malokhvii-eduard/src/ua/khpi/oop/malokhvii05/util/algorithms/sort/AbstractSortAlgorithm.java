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
 */
public abstract class AbstractSortAlgorithm<T>
        extends AbstractAlgorithmWithComparator<T> implements SortAlgorithm<T> {

    /**
     * Внутрішній ключ, порядку сортування від меншого до більшого.
     * Використовується лише у внутрішній реалізації методів.
     */
    protected static final int INTERNAL_ASCENDING_KEY = 1;

    /**
     * Внутрішній ключ, порядку сорутвання від більшого до меншого.
     * Використовується лише у внутрішній реалізації методів.
     */
    protected static final int INTERNAL_DESCENDING_KEY = -1;

    /**
     * Внутрішній ключ порядку сортування.
     */
    protected int sortOrderKey;

    /**
     * Призначений, для ініціалізації об'єкту компаратором для подільшого
     * обчислення.
     *
     * @param comparator
     *            компаратор для порівння під час сорутвання
     */
    public AbstractSortAlgorithm(final Comparator<T> comparator) {
        super(comparator);
        setSortOrder(Order.ASCENDING);
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
     */
    protected void swap(final Array<T> array, final int left, final int right) {
        T temp = array.get(left);
        array.set(left, array.get(right));
        array.set(right, temp);
    }

    /**
     * Призначений, для перевірки чи не обратний порядок сортування.
     *
     * @param sortOrderKey
     *            поточний порядок сортування
     * @param reversedSortOrderKey
     *            обратний порядок сорутвання
     * @return результат перевірки
     */
    protected boolean isReversed(final int sortOrderKey,
            final int reversedSortOrderKey) {
        return sortOrderKey == reversedSortOrderKey ? true : false;
    }

    /**
     * Призначений, для конвертування елементів переліку {@link Order}, у
     * числовий ключ.
     *
     * @param sortOrder
     *            порядок сортування
     * @return порядок сортування у вигляді числового ключу
     */
    protected int sortOrderToKey(final Order sortOrder) {
        return sortOrder == Order.ASCENDING ? INTERNAL_ASCENDING_KEY
                : INTERNAL_DESCENDING_KEY;
    }

    @Override
    public final Order getSortOrder() {
        return sortOrderKey == INTERNAL_ASCENDING_KEY ? Order.ASCENDING
                : Order.DESCENDING;
    }

    @Override
    public void setSortOrder(final Order sortOrder) {
        this.sortOrderKey = sortOrder == Order.ASCENDING
                ? INTERNAL_ASCENDING_KEY
                : INTERNAL_DESCENDING_KEY;
    }

    @Override
    public final boolean isNull() {
        return false;
    }
}
