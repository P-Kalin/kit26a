package ua.khpi.oop.malokhvii05.util.algorithms;

import java.util.Comparator;

/**
 * Абстрактний клас, призначений.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @param <T>
 *            Тип даних, які оброблює алгоритм, а також тип компоратору для
 *            даних
 * @since 1.0.0
 */
public abstract class NullAlgorithmWithComparator<T> extends NullAlgorithm<T>
        implements AlgorithmWithComparator<T> {

    /**
     * Компаратор за змовчуванням, для заглушки.
     *
     * @since 1.0.0
     */
    private final Comparator<T> defaultComparator = new DefaultComparator();

    /**
     * Компаратор за змовчуванням, для заглушки.
     *
     * @author malokhvii-eduard
     * @version 1.0.0
     * @since 1.0.0
     */
    private class DefaultComparator implements Comparator<T> {
        @Override
        public int compare(final T left, final T right) {
            return 0;
        }
    }

    @Override
    public void setComparator(final Comparator<T> comparator) {

    }

    @Override
    public final Comparator<T> getComparator() {
        return defaultComparator;
    }
}
