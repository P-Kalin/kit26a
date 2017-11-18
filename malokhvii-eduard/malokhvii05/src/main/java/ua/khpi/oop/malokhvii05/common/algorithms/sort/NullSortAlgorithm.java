package ua.khpi.oop.malokhvii05.common.algorithms.sort;

import ua.khpi.oop.malokhvii05.common.Array;
import ua.khpi.oop.malokhvii05.common.algorithms.NullAlgorithmWithComparator;

/**
 * Призначений, для реалізації заглушки для алгоритму сортування, на випадок,
 * якщо фабриці алгоритмів передано не зарегестровану назву алгоритму.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @param <T>
 *            Тип даних, елементів масиву для сортування, та компаратору для
 *            порівняння елементів
 */
final class NullSortAlgorithm<T> extends NullAlgorithmWithComparator<T>
        implements SortAlgorithm<T> {

    /**
     * Єдиний об'єкт заглушки алгоритму сортування.
     *
     * @since 1.0.0
     */
    @SuppressWarnings("rawtypes")
    public static final SortAlgorithm INSTANCE = new NullSortAlgorithm();

    /**
     * Приватний конструткор, для заборони створення заглушки.
     *
     * @since 1.0.0
     */
    private NullSortAlgorithm() {

    }

    @Override
    public boolean isReversedOrder() {
        return false;
    }

    @Override
    public void setReversedOrder(final boolean isReversedOrder) {

    }

    @Override
    public void sort(final Array<T> array) {

    }
}
