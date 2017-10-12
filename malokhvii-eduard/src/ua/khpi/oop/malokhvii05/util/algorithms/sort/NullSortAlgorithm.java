package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import ua.khpi.oop.malokhvii05.util.Array;
import ua.khpi.oop.malokhvii05.util.algorithms.NullAlgorithmWithComparator;

/**
 * Призначений, для реалізації заглушки для алгоритму сортування, на випадок,
 * якщо фабриці алгоритмів передано не зарегестровану назву алгоритму.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @param <T>
 *            Тип даних, елементів масиву для сортування, та компаратору для
 *            порівняння елементів
 */
final class NullSortAlgorithm<T> extends NullAlgorithmWithComparator<T>
        implements SortAlgorithm<T> {

    /**
     * Єдиний об'єкт заглушки алгоритму сортування.
     */
    @SuppressWarnings("rawtypes")
    public static final SortAlgorithm INSTANCE = new NullSortAlgorithm();

    /**
     * Приватний конструткор, для заборони створення заглушки.
     */
    private NullSortAlgorithm() {

    }

    @Override
    public void sort(final Array<T> array) {

    }

    @Override
    public boolean isReversedOrder() {
        return false;
    }

    @Override
    public void setReversedOrder(boolean isReversedOrder) {

    }
}
