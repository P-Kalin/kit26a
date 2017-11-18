package ua.khpi.oop.malokhvii05.common.algorithms;

/**
 * Загальний інтерфейс алгоритмів, об'єднує під собою усі реалізовані алгоритми.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see AbstractAlgorithmWithComparator
 * @param <T>
 *            Тип даних, які оброблює алгоритм
 */
public interface Algorithm<T> {

    /**
     * Призначений для перевірки чи не є об'єкт алгоритму порожньою обгорткою.
     *
     * @see NullAlgorithm
     * @see NullAlgorithmWithComparator
     * @return результат перевірки
     * @since 1.0.0
     */
    boolean isNull();
}
