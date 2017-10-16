package ua.khpi.oop.malokhvii05.util.algorithms.search;

import ua.khpi.oop.malokhvii05.util.Array;
import ua.khpi.oop.malokhvii05.util.algorithms.NullAlgorithmWithComparator;

/**
 * Призначений, для реалізації заглушки для алгоритму пошуку, на випадок, якщо
 * фабриці алгоритмів передано не зарегестровану назву алгоритму.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @param <T>
 *            Тип даних, елементів масиву, діапазону, та елемента для пошуку
 * @since 1.0.0
 */
public final class NullSearchAlgorithm<T> extends NullAlgorithmWithComparator<T>
        implements SearchAlgorithm<T>, SearchInRangeAlgorithm<T> {

    /**
     * Єдиний об'єкт заглушки алгоритму сортування.
     *
     * @since 1.0.0
     */
    @SuppressWarnings("rawtypes")
    public static final SearchAlgorithm INSTANCE = new NullSearchAlgorithm();

    /**
     * Приватний конструткор, для заборони створення заглушки.
     * 
     * @since 1.0.0
     */
    private NullSearchAlgorithm() {

    }

    @Override
    public int search(final Array<T> array, final T value) {
        return INDEX_NOT_FOUND;
    }

    @Override
    public int getLastFoundIndex() {
        return INDEX_NOT_FOUND;
    }

    @Override
    public int search(final Array<T> array, final T value, final int left,
            final int right) {
        return INDEX_NOT_FOUND;
    }
}
