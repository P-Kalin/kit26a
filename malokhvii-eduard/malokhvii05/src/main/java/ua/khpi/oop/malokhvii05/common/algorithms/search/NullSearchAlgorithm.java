package ua.khpi.oop.malokhvii05.common.algorithms.search;

import javax.annotation.Nullable;
import javax.annotation.Signed;

import ua.khpi.oop.malokhvii05.common.algorithms.NullAlgorithmWithComparator;

/**
 * Призначений, для реалізації заглушки для алгоритму пошуку, на випадок, якщо
 * фабриці алгоритмів передано не зарегестровану назву алгоритму.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @param <T>
 *            Тип даних, елементів масиву, діапазону, та елемента для пошуку
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
    public @Signed int getLastFoundIndex() {
        return SearchAlgorithm.INDEX_NOT_FOUND;
    }

    @Override
    public @Signed int search(@Nullable final T[] array,
            @Nullable final T value) {
        return SearchAlgorithm.INDEX_NOT_FOUND;
    }

    @Override
    public @Signed int search(@Nullable final T[] array,
            @Nullable final T value, final int left, final int right) {
        return SearchAlgorithm.INDEX_NOT_FOUND;
    }
}
