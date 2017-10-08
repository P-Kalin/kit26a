package ua.khpi.oop.malokhvii05.util.algorithms;

import java.util.Comparator;

/**
 * Абстрактний клас, призначений для об'єднання усіх алгоритмів які потребують
 * компоратор для обчислення, під єдиною реалізацією.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @param <T>
 *            Тип даних, які оброблює алгоритм, а також тип компоратору для
 *            даних
 */
public abstract class AbstractAlgorithmWithComparator<T>
        implements AlgorithmWithComparator<T> {

    /**
     * Компоратор вхідних даних.
     */
    protected Comparator<T> comparator;

    /**
     * Пирзначений, для інійіалізації об'єкту компоратором для порівняння
     * вхідних даних.
     *
     * @param comparator
     *            компоратор для вхідних даних
     */
    public AbstractAlgorithmWithComparator(final Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void setComparator(final Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public Comparator<T> getComparator() {
        return comparator;
    }
}
