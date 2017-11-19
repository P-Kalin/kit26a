package ua.khpi.oop.malokhvii05.common.algorithms;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Comparator;

import javax.annotation.Nonnull;

/**
 * Абстрактний клас, призначений для об'єднання усіх алгоритмів які потребують
 * компоратор для обчислення, під єдиною реалізацією.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @param <T>
 *            Тип даних, які оброблює алгоритм, а також тип компоратору для
 *            даних
 */
public abstract class AbstractAlgorithmWithComparator<T>
        implements AlgorithmWithComparator<T> {

    /**
     * Компоратор вхідних даних.
     *
     * @since 1.0.0
     */
    protected Comparator<T> comparator;

    /**
     * Пирзначений, для інійіалізації об'єкту компоратором для порівняння
     * вхідних даних.
     *
     * @param comparator
     *            компоратор для вхідних даних
     * @since 1.0.0
     */
    public AbstractAlgorithmWithComparator(
            @Nonnull final Comparator<T> comparator) {
        this.comparator = checkNotNull(comparator);
    }

    @Override
    public @Nonnull Comparator<T> getComparator() {
        return this.comparator;
    }

    @Override
    public void setComparator(@Nonnull final Comparator<T> comparator) {
        this.comparator = checkNotNull(comparator);
    }
}
